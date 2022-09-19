import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionesRegistroEmpleados implements ActionListener {
    private boolean validoRegistro, validoNombre, validoCedula, validoEdad, validoTiempoTrabajado;
    private JButton btnAgregarInstancia, btnMostrarInstancia, btnRegresarInstancia;
    private JTextField txtNombreInstancia, txtCedulaInstancia, txtEdadInstancia, txtTiempoTrabajadoInstancia;
    private JComboBox cmbJornadaInstancia;
    private JFrame frameInstancia;

    public AccionesRegistroEmpleados(JButton btnAgregar, JButton btnMostrar, JButton btnRegresar, JTextField txtNombre, JTextField txtCedula, JTextField txtEdad, JTextField txtTiempoTrabajado, JComboBox cmbJornada, JFrame frame) {
        btnAgregarInstancia = btnAgregar;
        btnMostrarInstancia = btnMostrar;
        btnRegresarInstancia = btnRegresar;
        txtNombreInstancia = txtNombre;
        txtCedulaInstancia = txtCedula;
        txtEdadInstancia = txtEdad;
        txtTiempoTrabajadoInstancia = txtTiempoTrabajado;
        cmbJornadaInstancia = cmbJornada;
        frameInstancia = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregarInstancia) {
            System.out.println("Agregar");
            if (estanLlenos()){
                if (validarRegistros()){
                    realizarRegistro();
                    System.out.println("Registro realizado.");
                }
            }
        }
        if (e.getSource() == btnMostrarInstancia) {
            System.out.println("Mostrar");
            VentanaRegistrosEmpleados ventanaRegistrosEmpleados = new VentanaRegistrosEmpleados(registrosEmpleados.traerRegistros());
            //dispose();
                 /*String[] nombresColumnas = {"Nombre", "Cedula", "Edad", "Jornada", "Tiempo Trabajado"};
                Object[][] datos = new String[registrosEmpleados.traerRegistros().size()][5];
                int i = 0;
                for (Empleado empleado : registrosEmpleados.traerRegistros()){
                    datos[i][0] = empleado.getNombre();
                    datos[i][1] = String.valueOf(empleado.getNumIdentificacion());
                    datos[i][2] = String.valueOf(empleado.getEdad());
                    datos[i][3] = String.valueOf(empleado.getJornada());
                    datos[i][4] = String.valueOf(empleado.getTiempoTrabajado());

                    i++;
                }
                JTable tabla = new JTable(datos, nombresColumnas);
                JScrollPane scrollPane = new JScrollPane(tabla);
                JFrame frame = new JFrame("Registro de Empleados");
                frame.add(scrollPane);
                frame.setSize(500, 400);
                frame.setVisible(true);*/
            frameInstancia.dispose();
        }
        if (e.getSource() == btnRegresarInstancia){
            frameInstancia.dispose();
            new VentanaMenuInicio();
        }
    }

    private String regexName, regexCedula, regexEdad, regexTiempoTrabajado;
    public boolean validarRegistros(){
        validoRegistro = false;
        validoNombre = false;
        validoCedula = false;
        validoEdad = false;
        validoTiempoTrabajado = false;

        regexName = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
        // "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\h+[A-Z][A-Za-z]*)*$"
        /*
         * The pattern matches:
         * - ^ Start of string
         * - [A-Z] Match an uppercase char A-Z
         * - (?=.{1,29}$) Assert 1-29 chars to the right till the end of the string
         * - [A-Za-z]* Optionally match a char A-Za-z
         * - (?:\h+[A-Z][A-Za-z]*)* Optionally repeat 1+ horizontal whitespace chars followed by again an uppercase char A-Z and optional chars A-Za-z
         * - $ End of string
         *
         * Creditos: https://stackoverflow.com/questions/66910411/regular-expression-to-validate-a-name
         * */
        regexCedula = "^[0-9]{1,10}$";
        regexEdad = "^[0-9]{1,3}$";
        regexTiempoTrabajado = "^[0-9]{1,2}$";

        validoNombre = txtNombreInstancia.getText().matches(regexName);
        validoCedula = txtCedulaInstancia.getText().matches(regexCedula);
        validoEdad = txtEdadInstancia.getText().matches(regexEdad);
        validoTiempoTrabajado = txtTiempoTrabajadoInstancia.getText().matches(regexTiempoTrabajado);

        if (!validoNombre || !validoCedula || !validoEdad || !validoTiempoTrabajado){
            AdvertenciaValidacion advertenciaValidacion = new AdvertenciaValidacion(validoNombre, validoCedula, validoEdad, validoTiempoTrabajado);
        }

        if (validoNombre && validoCedula && validoEdad && validoTiempoTrabajado){
            validoRegistro = true;
        }

        System.out.println("Validando datos");
        return validoRegistro;
    }

    private boolean estanDatos, estaNombre, estaCedula, estaEdad, estaTiempoTrabajado;
    public boolean estanLlenos(){
        estanDatos = false;
        estaNombre = false;
        estaCedula = false;
        estaEdad = false;
        estaTiempoTrabajado = false;

        if (!txtNombreInstancia.getText().isEmpty()) {
            estaNombre = true;
        }
        if (!txtCedulaInstancia.getText().isEmpty()) {
            estaCedula = true;
        }
        if (!txtEdadInstancia.getText().isEmpty()) {
            estaEdad = true;
        }
        if (!txtTiempoTrabajadoInstancia.getText().isEmpty()) {
            estaTiempoTrabajado = true;
        }
        if (!estaNombre || !estaCedula || !estaEdad || !estaTiempoTrabajado){
            AdvertenciaRegistros advertenciaRegistros = new AdvertenciaRegistros(estaNombre, estaCedula, estaEdad, estaTiempoTrabajado);
        }
        if (estaNombre && estaCedula && estaEdad && estaTiempoTrabajado){
            estanDatos = true;
        }
        return estanDatos;
    }

    RegistrosEmpleados registrosEmpleados = new RegistrosEmpleados();

    public void realizarRegistro(){
        registrosEmpleados.agregarRegistro(new Empleado(txtNombreInstancia.getText(), Integer.parseInt(txtCedulaInstancia.getText()), Integer.parseInt(txtEdadInstancia.getText()), cmbJornadaInstancia.getSelectedIndex(), Integer.parseInt(txtTiempoTrabajadoInstancia.getText())));
    }
    class AdvertenciaRegistros extends JFrame {
        private JTextArea lblAdvertencia;
        public AdvertenciaRegistros(boolean estaNombre, boolean estaCedula, boolean estaEdad, boolean estaTiempoTrabajado) {
            setTitle("Advertencia");
            setSize(400, 200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            setLayout(new FlowLayout());

            lblAdvertencia = new JTextArea();
            lblAdvertencia.setText("\nLos siguientes campos no pueden estar vacios:\n");
            lblAdvertencia.setSize(360, 150);
            lblAdvertencia.setWrapStyleWord(true);
            lblAdvertencia.setLineWrap(true);
            lblAdvertencia.setOpaque(false);
            lblAdvertencia.setEditable(false);
            lblAdvertencia.setFocusable(false);
            lblAdvertencia.setFont(new Font("Arial", Font.BOLD, 15));
            add(lblAdvertencia);

            if (!estaNombre) {
                lblAdvertencia.append("\n• Nombre");
            }
            if (!estaCedula) {
                lblAdvertencia.append("\n• Cedula");
            }
            if (!estaEdad) {
                lblAdvertencia.append("\n• Edad");
            }
            if (!estaTiempoTrabajado) {
                lblAdvertencia.append("\n• Tiempo Trabajado");
            }
            setVisible(true);
        }
    }

    class AdvertenciaValidacion extends JFrame {
        private JTextArea lblAdvertencia;
        public AdvertenciaValidacion(boolean validoNombre, boolean validoCedula, boolean validoEdad, boolean validoTiempoTrabajado) {
            setTitle("Advertencia");
            setSize(400, 350);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            setLayout(new FlowLayout());

            lblAdvertencia = new JTextArea();
            lblAdvertencia.setText("\nLos siguientes campos no son validos:\n");
            lblAdvertencia.setSize(360, 150);
            lblAdvertencia.setWrapStyleWord(true);
            lblAdvertencia.setLineWrap(true);
            lblAdvertencia.setOpaque(false);
            lblAdvertencia.setEditable(false);
            lblAdvertencia.setFocusable(false);
            lblAdvertencia.setFont(new Font("Arial", Font.BOLD, 15));
            add(lblAdvertencia);

            if (!validoNombre) {
                lblAdvertencia.append("\n• Nombre");
                lblAdvertencia.append("\n     [Solo puede contener letras y espacios]");
                lblAdvertencia.append("\n     [Iniciales en mayúscula]");
                lblAdvertencia.append("\n     [Máximo 29 caracteres]");
            }
            if (!validoCedula) {
                lblAdvertencia.append("\n• Cedula");
                lblAdvertencia.append("\n     [Solo puede contener numeros]");
                lblAdvertencia.append("\n     [Mínimo 10 caracteres]");
            }
            if (!validoEdad) {
                lblAdvertencia.append("\n• Edad");
                lblAdvertencia.append("\n     [Solo puede contener numeros]");
                lblAdvertencia.append("\n     [Mínimo 3 caracteres]");
            }
            if (!validoTiempoTrabajado) {
                lblAdvertencia.append("\n• Tiempo Trabajado");
                lblAdvertencia.append("\n     [Solo puede contener numeros]");
                lblAdvertencia.append("\n     [Mínimo 2 caracteres]");
            }
            setVisible(true);
        }
    }
}