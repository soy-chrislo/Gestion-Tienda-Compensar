import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaRegistrosEmpleados extends JFrame {
    private PanelTabla panelTabla;
    ArrayList<Empleado> empleados;

    public VentanaRegistrosEmpleados(ArrayList<Empleado> empleados) {
        setIconImage(new ImageIcon(getClass().getResource("logo-ucompensar.png")).getImage());

        this.empleados = empleados;
        setTitle("Registros de Empleados");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        panelTabla = new PanelTabla(Archivo.ARCHIVO_EMPLEADOS.importarRegistros());
        add(panelTabla, BorderLayout.CENTER);

        PanelBotones panelBotones = new PanelBotones(this, panelTabla);
        add(panelBotones, BorderLayout.SOUTH);


        setVisible(true);
    }
    public VentanaRegistrosEmpleados() {
        setTitle("Registro de Empleados");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        panelTabla = new PanelTabla(Archivo.ARCHIVO_EMPLEADOS.importarRegistros());
        add(panelTabla, BorderLayout.CENTER);

        PanelBotones panelBotones = new PanelBotones(this, panelTabla);
        add(panelBotones, BorderLayout.SOUTH);


        setVisible(true);
    }



    class PanelBotones extends JPanel {
        private JButton btnRegistrar, btnEditar, btnEliminar, btnRegresar;
        private JFrame instanceJFrame;
        private JTable tablaInstancia;
        public PanelBotones(JFrame frame, PanelTabla panelTablaInstancia) {
            instanceJFrame = frame;
            tablaInstancia = panelTablaInstancia.getTabla();

            setLayout(new FlowLayout());
            btnRegistrar = new JButton("Registrar");
            btnRegistrar.addActionListener(new AccionesRegistroEmpleados(frame, panelTablaInstancia));
            add(btnRegistrar);

            btnEditar = new JButton("Editar");
            btnEditar.addActionListener(new AccionesRegistroEmpleados(frame, panelTablaInstancia));
            add(btnEditar);

            btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(new AccionesRegistroEmpleados(frame, panelTablaInstancia));
            add(btnEliminar);

            btnRegresar = new JButton("Regresar");
            btnRegresar.addActionListener(new AccionesRegistroEmpleados(frame, panelTablaInstancia));
            add(btnRegresar);
        }
        class AccionesRegistroEmpleados implements ActionListener {
            JFrame frameInstancia;
            PanelTabla panelTablaInstancia;

            public AccionesRegistroEmpleados(JFrame frame, PanelTabla panelTablaInstancia) {

                frameInstancia = frame;
                this.panelTablaInstancia = panelTablaInstancia;

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRegistrar) {

                    new VentanaRegistroEmpleados();
                    instanceJFrame.dispose();
                }
                if (e.getSource() == btnEditar) {
                    System.out.println("Editar");
                    /*System.out.println("Número Columna: " + tablaInstancia.getSelectedColumn());
                    System.out.println("Número Fila: " + tablaInstancia.getSelectedRow());
                    System.out.println(Archivo.archivo.obtenerRegistro(tablaInstancia.getSelectedRow()));*/

                    if (tablaInstancia.getSelectedRow() >= 0){
                        // Si el usuario selecciono alguna casilla.
                        Empleado empleado = Archivo.ARCHIVO_EMPLEADOS.extraerDatos(Archivo.ARCHIVO_EMPLEADOS.obtenerRegistro(tablaInstancia.getSelectedRow()));
                        System.out.println(empleado.toString());
                        /* Se transforma el String a Empleado ya que así es más fácil acceder a sus propiedades.
                        * TODO: Solución a Medias - Al editar realizamos borrado del registro pero este se mantiene en el VentanaRegistroEmpleados(empleado) y el usuario es quien lo guarda, NO IMPORTA el orden.
                        */
                        Archivo.ARCHIVO_EMPLEADOS.eliminarRegistro(Archivo.ARCHIVO_EMPLEADOS.obtenerRegistro(tablaInstancia.getSelectedRow()));

                        /*
                        *  TODO: Faltaría instaurar el backend, que guardaría el indice del empleado según la línea (por medio del indice de la fila en la tabla), crear el nuevo archivo y cuando se llegue al número de iteraciones igual al índice, se coloca el nuevo registro MODIFICADO.
                        */
                        dispose();
                        new VentanaRegistroEmpleados(empleado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione el registro a modificar.");
                    }

                    /* El método obtenerRegistro() funciona retornando el string de todos los datos del registro dado como indice. (En este caso el de la fila seleccionada).*/
                    /*
                    * TODO: Si se da en editar y no esta seleccionada una fila, se le indica por un cuadro de dialogo.
                    *  - v2: Mejor agregarle un ComboBox y que seleccione el registro que desee editar. (Nombre - Cedula)
                    * */
                }
                if (e.getSource() == btnEliminar){
                    System.out.println("Eliminar");
                    if (tablaInstancia.getSelectedRow() >= 0){
                        Archivo.ARCHIVO_EMPLEADOS.eliminarRegistro(Archivo.ARCHIVO_EMPLEADOS.obtenerRegistro(tablaInstancia.getSelectedRow()));
                        /*
                         * Para actualizar la tabla se opto por cerrar la ventana y crear una nueva instancia.
                         * TODO: Actualizar la tabla sin tener que cerrar la ventana e instanciar una nueva.
                         * */
                        dispose();
                        new VentanaRegistrosEmpleados(empleados);

                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar.");
                    }
                }
                if (e.getSource() == btnRegresar) {
                    frameInstancia.dispose();
                    new VentanaMenuInicio();
                }
            }
        }

    }
    class PanelTabla extends JPanel {
        private ArrayList<Empleado> empleadosRegistro;
        private String[] nombresColumnas = {"Nombre", "Cedula", "Edad", "Jornada", "Tiempo Trabajado"};
        private Object[][] datosFila;
        private JTable tabla;
        private int i;
        public PanelTabla(ArrayList<Empleado> empleados) {
            empleadosRegistro = empleados;
            // Colocamos los valore a la tabla por medio del arreglo de Empleados pasado por el constructor.
            /*datosFila = new String[empleados.size()][5];
            i = 0;
            for (Empleado empleado : empleados){
                datosFila[i][0] = empleado.getNombre();
                datosFila[i][1] = String.valueOf(empleado.getNumIdentificacion());
                datosFila[i][2] = String.valueOf(empleado.getEdad());
                datosFila[i][3] = String.valueOf(empleado.getJornada());
                datosFila[i][4] = String.valueOf(empleado.getTiempoTrabajado());

                i++;
            }*/

            datosFila = new String[empleadosRegistro.size()][5];
            i = 0;
            for (Empleado empleado : empleadosRegistro){
                datosFila[i][0] = empleado.getNombre();
                datosFila[i][1] = String.valueOf(empleado.getNumIdentificacion());
                datosFila[i][2] = String.valueOf(empleado.getEdad());
                // Cambia el formato númerico de jornada a letras.
                datosFila[i][3] = String.valueOf(empleado.getJornada() == 0 ? "Diurna" : "Nocturna");
                datosFila[i][4] = String.valueOf(empleado.getTiempoTrabajado());

                i++;
            }

            tabla = new JTable(datosFila, nombresColumnas);
            JScrollPane scrollPane = new JScrollPane(tabla);
            add(scrollPane);
        }
        public JTable getTabla(){
            return tabla;
        }
    }
}