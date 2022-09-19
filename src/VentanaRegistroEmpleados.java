import javax.swing.*;
import java.awt.*;

public class VentanaRegistroEmpleados extends JFrame {
    private CasillasRegistro casillasRegistro;
    private BotonesRegistros botonesRegistros;
    public VentanaRegistroEmpleados() {
        setIconImage(new ImageIcon(getClass().getResource("logo-ucompensar.png")).getImage());
        setTitle("Registro de Empleados");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        casillasRegistro = new CasillasRegistro();
        add(casillasRegistro, BorderLayout.CENTER);

        botonesRegistros = new BotonesRegistros(this);
        add(botonesRegistros, BorderLayout.SOUTH);

        setVisible(true);

    }

    // Constructor para cuando se edita un registro, evidentemente recibe el objeto para colocar sus propiedades en los JTextField correspondientes.
    public VentanaRegistroEmpleados(Empleado empleado){
        setIconImage(new ImageIcon(getClass().getResource("logo-ucompensar.png")).getImage());
        setTitle("Registro de Empleados");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        casillasRegistro = new CasillasRegistro(empleado);
        add(casillasRegistro, BorderLayout.CENTER);

        botonesRegistros = new BotonesRegistros(this);
        add(botonesRegistros, BorderLayout.SOUTH);

        setVisible(true);
    }
    private JLabel lblNombre, lblCedula, lblEdad, lblJornada, lblTiempoTrabajado, lblBeneficios;
    private JTextField txtNombre, txtCedula, txtEdad, txtTiempoTrabajado, txtBeneficios;
    private JComboBox cmbJornada;
    private class CasillasRegistro extends JPanel {

        public CasillasRegistro() {
            setLayout(new GridLayout(6, 2));

            lblNombre = new JLabel("Nombre Empleado:", SwingConstants.CENTER);
            lblCedula = new JLabel("Cedula Empleado:", SwingConstants.CENTER);
            lblEdad = new JLabel("Edad Empleado:", SwingConstants.CENTER);
            lblJornada = new JLabel("Jornada Empleado:", SwingConstants.CENTER);
            lblTiempoTrabajado = new JLabel("Años Laborados Empleado:", SwingConstants.CENTER);
            lblBeneficios = new JLabel("Beneficios Empleado:", SwingConstants.CENTER);

            txtNombre = new JTextField();
            txtCedula = new JTextField();
            txtEdad = new JTextField();
            cmbJornada = new JComboBox();
            txtTiempoTrabajado = new JTextField();
            txtBeneficios = new JTextField();

            cmbJornada.addItem("Diurna");
            cmbJornada.addItem("Nocturna");

            txtBeneficios.setEnabled(false);

            add(lblNombre);
            add(txtNombre);
            add(lblCedula);
            add(txtCedula);
            add(lblEdad);
            add(txtEdad);
            add(lblJornada);
            add(cmbJornada);
            add(lblTiempoTrabajado);
            add(txtTiempoTrabajado);
            add(lblBeneficios);
            add(txtBeneficios);
        }
        public CasillasRegistro(Empleado empleado){
            setLayout(new GridLayout(6, 2));

            lblNombre = new JLabel("Nombre Empleado:", SwingConstants.CENTER);
            lblCedula = new JLabel("Cedula Empleado:", SwingConstants.CENTER);
            lblEdad = new JLabel("Edad Empleado:", SwingConstants.CENTER);
            lblJornada = new JLabel("Jornada Empleado:", SwingConstants.CENTER);
            lblTiempoTrabajado = new JLabel("Años Laborados Empleado:", SwingConstants.CENTER);
            lblBeneficios = new JLabel("Beneficios Empleado:", SwingConstants.CENTER);

            txtNombre = new JTextField();
            txtNombre.setText(empleado.getNombre());
            txtCedula = new JTextField();
            txtCedula.setText(String.valueOf(empleado.getNumIdentificacion()));
            txtEdad = new JTextField();
            txtEdad.setText(String.valueOf(empleado.getEdad()));
            cmbJornada = new JComboBox();
            txtTiempoTrabajado = new JTextField();
            txtTiempoTrabajado.setText(String.valueOf(empleado.getTiempoTrabajado()));
            txtBeneficios = new JTextField();

            cmbJornada.addItem("Diurna");
            cmbJornada.addItem("Nocturna");

            txtBeneficios.setEnabled(false);

            add(lblNombre);
            add(txtNombre);
            add(lblCedula);
            add(txtCedula);
            add(lblEdad);
            add(txtEdad);
            add(lblJornada);
            add(cmbJornada);
            add(lblTiempoTrabajado);
            add(txtTiempoTrabajado);
            add(lblBeneficios);
            add(txtBeneficios);
        }
    }


    private JButton btnAgregar, btnMostrar, btnRegresar;

    private class BotonesRegistros extends JPanel {
        public BotonesRegistros(JFrame frameInstancia) {
            btnAgregar = new JButton("Agregar");
            btnAgregar.addActionListener(new AccionesRegistroEmpleados(btnAgregar, btnMostrar, btnRegresar, txtNombre, txtCedula, txtEdad, txtTiempoTrabajado, cmbJornada, frameInstancia));
            add(btnAgregar);

            btnMostrar = new JButton("Mostrar");
            btnMostrar.addActionListener(new AccionesRegistroEmpleados(btnAgregar, btnMostrar, btnRegresar, txtNombre, txtCedula, txtEdad, txtTiempoTrabajado, cmbJornada, frameInstancia));
            add(btnMostrar);

            btnRegresar = new JButton("Regresar");
            btnRegresar.addActionListener(new AccionesRegistroEmpleados(btnAgregar, btnMostrar, btnRegresar, txtNombre, txtCedula, txtEdad, txtTiempoTrabajado, cmbJornada, frameInstancia));
            add(btnRegresar);
        }
    }
    /* Se puede incorporar la clase AccionesRegistroEmpleados como una interna de BotonesRegistro, otro día con más paciencia...*/
}