import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroProductos extends JFrame {
    private CasillasRegistro casillasRegistro;
    private CasillasRegistro.BotonesRegistros botonesRegistros;
    public VentanaRegistroProductos(){
        setIconImage(new ImageIcon(getClass().getResource("logo-ucompensar.png")).getImage());

        setTitle("Registro Productos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        casillasRegistro = new CasillasRegistro();
        add(casillasRegistro, BorderLayout.CENTER);

        botonesRegistros = casillasRegistro.getBotonesRegistros();
        add(botonesRegistros, BorderLayout.SOUTH);

        setVisible(true);
    }

    public VentanaRegistroProductos(Producto producto){
        setIconImage(new ImageIcon(getClass().getResource("logo-ucompensar.png")).getImage());
        setTitle("Registro de Empleados");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        casillasRegistro = new CasillasRegistro(producto);
        add(casillasRegistro, BorderLayout.CENTER);

        botonesRegistros = casillasRegistro.getBotonesRegistros();
        add(botonesRegistros, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class CasillasRegistro extends JPanel {
        private JLabel lblNombre, lblTipoProducto, lblNumUnidades, lblValorUnitario, lblValorIVA, lblValorTotal;
        private JTextField txtNombre, txtNumUnidades, txtValorUnitario, txtValorIVA, txtValorTotal;
        /* Tanto txtValorIVA como txtValorTotal son desactivados y estos se definen al ingresar el txtValorUnitario.*/
        private JComboBox cmbTipoProducto;
        public CasillasRegistro(){
            setLayout(new GridLayout(6, 2));

            lblNombre = new JLabel("Nombre Producto:", SwingConstants.CENTER);
            lblTipoProducto = new JLabel("Tipo Producto:", SwingConstants.CENTER);
            lblNumUnidades = new JLabel("Numero Unidades:", SwingConstants.CENTER);
            lblValorUnitario = new JLabel("Valor Unitario:", SwingConstants.CENTER);
            lblValorIVA = new JLabel("Valor IVA:", SwingConstants.CENTER);
            lblValorTotal = new JLabel("Valor Total:", SwingConstants.CENTER);

            txtNombre = new JTextField();
            txtNumUnidades = new JTextField();
            txtValorUnitario = new JTextField();
            txtValorIVA = new JTextField();
            txtValorTotal = new JTextField();

            cmbTipoProducto = new JComboBox();
            /*
             * 0 - Aseo
             * 1 - Papeleria
             * 2 - Víveres
             * 3 - Mascotas
             * 4 - Otros
             * */
            cmbTipoProducto.addItem("Aseo");
            cmbTipoProducto.addItem("Papeleria");
            cmbTipoProducto.addItem("Víveres");
            cmbTipoProducto.addItem("Mascotas");
            cmbTipoProducto.addItem("Otros");

            txtValorIVA.setEnabled(false);
            txtValorTotal.setEnabled(false);

            add(lblNombre);
            add(txtNombre);
            add(lblTipoProducto);
            add(cmbTipoProducto);
            add(lblNumUnidades);
            add(txtNumUnidades);
            add(lblValorUnitario);
            add(txtValorUnitario);
            add(lblValorIVA);
            add(txtValorIVA);
            add(lblValorTotal);
            add(txtValorTotal);
        }
        public CasillasRegistro(Producto producto){
            setLayout(new GridLayout(6, 2));

            lblNombre = new JLabel("Nombre Producto:", SwingConstants.CENTER);
            lblTipoProducto = new JLabel("Tipo Producto:", SwingConstants.CENTER);
            lblNumUnidades = new JLabel("Numero Unidades:", SwingConstants.CENTER);
            lblValorUnitario = new JLabel("Valor Unitario:", SwingConstants.CENTER);
            lblValorIVA = new JLabel("Valor IVA:", SwingConstants.CENTER);
            lblValorTotal = new JLabel("Valor Total:", SwingConstants.CENTER);

            txtNombre = new JTextField();
            txtNombre.setText(producto.getNombre());
            txtNumUnidades = new JTextField();
            txtNumUnidades.setText(String.valueOf(producto.getNumUnidades()));
            txtValorUnitario = new JTextField();
            txtValorUnitario.setText(String.valueOf(producto.getValorUnitario()));
            txtValorIVA = new JTextField();
            txtValorTotal = new JTextField();

            cmbTipoProducto = new JComboBox();
            /*
             * 0 - Aseo
             * 1 - Papeleria
             * 2 - Víveres
             * 3 - Mascotas
             * 4 - Otros
             * */
            cmbTipoProducto.addItem("Aseo");
            cmbTipoProducto.addItem("Papeleria");
            cmbTipoProducto.addItem("Víveres");
            cmbTipoProducto.addItem("Mascotas");
            cmbTipoProducto.addItem("Otros");

            txtValorIVA.setEnabled(false);
            txtValorTotal.setEnabled(false);

            add(lblNombre);
            add(txtNombre);
            add(lblTipoProducto);
            add(cmbTipoProducto);
            add(lblNumUnidades);
            add(txtNumUnidades);
            add(lblValorUnitario);
            add(txtValorUnitario);
            add(lblValorIVA);
            add(txtValorIVA);
            add(lblValorTotal);
            add(txtValorTotal);
        }

        public BotonesRegistros getBotonesRegistros() {
            return new BotonesRegistros();
        }

        public class BotonesRegistros extends JPanel {
            private JButton btnAgregar, btnMostrar, btnRegresar;

            public BotonesRegistros(){
                btnAgregar = new JButton("Agregar");
                btnAgregar.addActionListener(new AccionesRegistroProductos());
                add(btnAgregar);

                btnMostrar = new JButton("Mostrar");
                btnMostrar.addActionListener(new AccionesRegistroProductos());
                add(btnMostrar);

                btnRegresar = new JButton("Regresar");
                btnRegresar.addActionListener(new AccionesRegistroProductos());
                add(btnRegresar);
            }

            private class AccionesRegistroProductos implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == btnAgregar){
                        if(estanLlenos()){
                            if(validarRegistros()){
                                realizarRegistro();
                                System.out.println("Registro realizado");
                            }
                        }
                    }
                    if (e.getSource() == btnMostrar){
                        System.out.println("Mostrar");
                        dispose();
                        new VentanaRegistrosProductos();
                    }
                    if (e.getSource() == btnRegresar){
                        dispose();
                        new VentanaMenuInicio();
                    }

                }

                boolean estanDatos, estanNombre, estanNumUnidades, estanValorUnitario;
                public boolean estanLlenos() {
                    estanDatos = false;
                    estanNombre = false;
                    estanNumUnidades = false;
                    estanValorUnitario = false;

                    if (!txtNombre.getText().isEmpty()) {
                        estanNombre = true;
                    }
                    if (!txtNumUnidades.getText().isEmpty()) {
                        estanNumUnidades = true;
                    }
                    if (!txtValorUnitario.getText().isEmpty()) {
                        estanValorUnitario = true;
                    }

                    if (!estanNombre || !estanNumUnidades || !estanValorUnitario){
                        // Lanzar Advertencia Registros.
                    }
                    if (estanNombre && estanNumUnidades && estanValorUnitario){
                        estanDatos = true;
                    }
                    return estanDatos;
                }
                private String regexNumUnidades, regexValorUnitario;
                private boolean validoNumUnidades, validoValorUnitario;

                public boolean validarRegistros(){
                    validoNumUnidades = false;
                    validoValorUnitario = false;

                    regexNumUnidades = "[0-9]+";
                    regexValorUnitario = "[0-9]+[.][0-9]+";

                    validoNumUnidades = txtNumUnidades.getText().matches(regexNumUnidades);
                    validoValorUnitario = txtValorUnitario.getText().matches(regexValorUnitario);

                    if (!validoNumUnidades || !validoValorUnitario){
                        // Lanzar Advertencia Validación.
                    }
                    if (validoNumUnidades && validoValorUnitario){
                        estanDatos = true;
                    }
                    return estanDatos;
                }

                public void realizarRegistro(){
                    String nombreProducto = txtNombre.getText();
                    int tipoProducto = cmbTipoProducto.getSelectedIndex();
                    int numUnidades = Integer.parseInt(txtNumUnidades.getText());
                    double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
                    double valorIVA = valorUnitario * 0.19;
                    double valorTotal = valorUnitario + valorIVA;

                    txtValorIVA.setText(String.valueOf(valorIVA));
                    txtValorTotal.setText(String.valueOf(valorTotal));

                    Producto producto = new Producto(nombreProducto, tipoProducto, numUnidades, valorUnitario);

                    Archivo.ARCHIVO_PRODUCTOS.crearRegistro(producto);
                }
            }
        }
    }

}
