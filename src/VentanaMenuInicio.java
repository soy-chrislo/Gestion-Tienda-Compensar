import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenuInicio extends JFrame {

//    private Image icon = new ImageIcon(getClass().getResource("logo-ucompensar.png")).getImage();
    private Image icon = Toolkit.getDefaultToolkit().getImage("resource/logo-ucompensar.png");
    private JMenuBar menuBar;
    private JMenu menuAyuda;
    private JMenuItem itemNosotros;

    public VentanaMenuInicio() {
        setIconImage(icon);
        setTitle("Tienda Compensar");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new PanelTitulo(), BorderLayout.NORTH);
        add(new PanelInteractivo(), BorderLayout.CENTER);
        add(new PanelBotones(), BorderLayout.SOUTH);

        menuAyuda = new JMenu("Ayuda");
        itemNosotros = new JMenuItem("Nosotros");
        itemNosotros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaCreditos();
            }
        });
        menuAyuda.add(itemNosotros);
        menuBar = new JMenuBar();
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);

        setVisible(true);
    }



    class PanelTitulo extends JPanel {
        GridBagConstraints gridBagConstraints;
        JLabel lblTitulo;
        Font fontTitulo;

        public PanelTitulo(){
            setLayout(new GridBagLayout());
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.insets = new Insets(5, 5, 5, 5);

            fontTitulo = new Font("Arial", Font.BOLD, 19);

            lblTitulo = new JLabel("Gestión Tienda Compensar", SwingConstants.CENTER);
            lblTitulo.setFont(fontTitulo);



            ubicarGrilla(0, 0, 3, 3);
            add(lblTitulo, gridBagConstraints);

            setVisible(true);
        }

        public void ubicarGrilla(int x, int y, int width, int height){
            gridBagConstraints.gridx = x;
            gridBagConstraints.gridy = y;
            gridBagConstraints.gridwidth = width;
            gridBagConstraints.gridheight = height;

        }
    }
    class PanelInteractivo extends JPanel {
        JLabel lblBienvenida, lblEmpleados, lblProductos;
        JButton btnRegistrarEmpleado, btnRegistrosEmpleados, btnRegistrarProducto, btnRegistrosProductos;
        GridBagConstraints gridBagConstraints;

        Font fontSubtitulo;
        /*
         * Será ubicado en el BorderLayout.CENTER de MainFrame().
         * Su función será ser el panel de interacción principal de la aplicación, permitiendo:
         * - Abrir ventana para registrar o ver los registros de empleados.
         * - Abrir ventana para registrar o ver los registros de los productos.
         * */
        public PanelInteractivo(){
            gridBagConstraints = new GridBagConstraints();
            fontSubtitulo = new Font("Arial", Font.PLAIN, 16);

            //lblBienvenida = new JLabel("Gestor Tienda Compensar", SwingConstants.CENTER);
            lblEmpleados = new JLabel("Empleados", SwingConstants.CENTER);
            lblEmpleados.setFont(fontSubtitulo);
            lblProductos = new JLabel("Productos", SwingConstants.CENTER);
            lblProductos.setFont(fontSubtitulo);

            btnRegistrarEmpleado = new JButton("Registrar");
            btnRegistrarEmpleado.setSize(60, 60);
            btnRegistrarEmpleado.addActionListener(new AccionesBotones());
            btnRegistrosEmpleados = new JButton("Registros");
            btnRegistrosEmpleados.addActionListener(new AccionesBotones());
            btnRegistrarProducto = new JButton("Registrar");
            btnRegistrarProducto.addActionListener(new AccionesBotones());
            btnRegistrosProductos = new JButton("Registros");
            btnRegistrosProductos.addActionListener(new AccionesBotones());

            setLayout(new GridBagLayout());
            gridBagConstraints.insets = new Insets(15, 10, 15, 10);

            ubicarGrilla(0, 0, 2, 1);
            /* El atributo `fill` se le asigna una constante, este corresponde al tamaño que debe adoptar el componente si su area es más grande que el mismo.*/
            //gridBagConstraints.fill = GridBagConstraints.BOTH;
            add(lblEmpleados, gridBagConstraints);

            ubicarGrilla(0, 1, 1, 1);
            //btnRegistrarEmpleado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(btnRegistrarEmpleado, gridBagConstraints);

            ubicarGrilla(1, 1, 1, 1);
            add(btnRegistrosEmpleados, gridBagConstraints);

            ubicarGrilla(0, 2, 2, 1);
            add(lblProductos, gridBagConstraints);

            ubicarGrilla(0, 3, 1, 1);
            add(btnRegistrarProducto, gridBagConstraints);

            ubicarGrilla(1, 3, 1, 1);
            add(btnRegistrosProductos, gridBagConstraints);


            setVisible(true);
        }

        public void ubicarGrilla(int x, int y, int width, int height){
            gridBagConstraints.gridx = x;
            gridBagConstraints.gridy = y;
            gridBagConstraints.gridwidth = width;
            gridBagConstraints.gridheight = height;

        }
        private class AccionesBotones implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRegistrarEmpleado){
                    dispose();
                    new VentanaRegistroEmpleados();
                }
                if (e.getSource() == btnRegistrosEmpleados){
                    dispose();
                    new VentanaRegistrosEmpleados();
                }
                if (e.getSource() == btnRegistrarProducto){
                    dispose();
                    new VentanaRegistroProductos();
                }
                if (e.getSource() == btnRegistrosProductos){
                    dispose();
                    new VentanaRegistrosProductos();
                }
            }
        }
    }

    class PanelBotones extends JPanel{
        private JButton btnCreditos, btnSalir;
        public PanelBotones(){
            setLayout(new FlowLayout());

            btnCreditos = new JButton("Créditos");
            btnCreditos.addActionListener(new AccionesBotones());
            btnSalir = new JButton("Salir");
            btnSalir.addActionListener(new AccionesBotones());

            add(btnCreditos);
            add(btnSalir);

        }
        private class AccionesBotones implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnCreditos){
                    new VentanaCreditos();
                }
                if (e.getSource() == btnSalir){
                    System.exit(0);
                }
            }
        }
    }
}