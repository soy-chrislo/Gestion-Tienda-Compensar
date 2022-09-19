import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistrosProductos extends JFrame {
    private PanelTabla panelTabla;
    public VentanaRegistrosProductos(){
        setIconImage(new ImageIcon(getClass().getResource("logo-ucompensar.png")).getImage());
        setTitle("Registro Productos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        panelTabla = new PanelTabla();
        add(panelTabla, BorderLayout.CENTER);

        PanelBotones panelBotones = new PanelBotones();
        add(panelBotones, BorderLayout.SOUTH);


        setVisible(true);
    }



    private class PanelTabla extends JPanel {
        private String columnas[] = {"Nombre", "Categoria", "Cantidad", "Precio"};
        private Object[][] datosFila;
        private JTable tabla;
        private int i;

        public PanelTabla(){
            setLayout(new BorderLayout());
            datosFila = new Object[Archivo.ARCHIVO_PRODUCTOS.importarProductos().size()][4];
            for (Producto producto : Archivo.ARCHIVO_PRODUCTOS.importarProductos()) {
                /*
                 * 0 - Aseo
                 * 1 - Papeleria
                 * 2 - Víveres
                 * 3 - Mascotas
                 * 4 - Otros
                 * */
                String categoriaProducto = "";
                switch(producto.getTipoProducto()){
                    case 0 -> categoriaProducto = "Aseo";
                    case 1 -> categoriaProducto = "Papeleria";
                    case 2 -> categoriaProducto = "Víveres";
                    case 3 -> categoriaProducto = "Mascotas";
                    case 4 -> categoriaProducto = "Otros";
                }
                datosFila[i][0] = producto.getNombre();
                datosFila[i][1] = categoriaProducto;
                datosFila[i][2] = producto.getNumUnidades();
                datosFila[i][3] = producto.getValorUnitario();
                i++;
            }
            tabla = new JTable(datosFila, columnas);
            add(new JScrollPane(tabla), BorderLayout.CENTER);
        }

        public JTable getTabla() {
            return tabla;
        }
    }

    private class PanelBotones extends JPanel {
        private JButton btnRegistrar, btnEditar, btnEliminar, btnRegresar;
        public PanelBotones() {
            setLayout(new FlowLayout());
            btnRegistrar = new JButton("Registrar");
            btnRegistrar.addActionListener(new AccionesRegistrosProductos());
            add(btnRegistrar);

            btnEditar = new JButton("Editar");
            btnEditar.addActionListener(new AccionesRegistrosProductos());
            add(btnEditar);

            btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(new AccionesRegistrosProductos());
            add(btnEliminar);

            btnRegresar = new JButton("Regresar");
            btnRegresar.addActionListener(new AccionesRegistrosProductos());
            add(btnRegresar);
        }

        private class AccionesRegistrosProductos implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRegistrar) {
                    dispose();
                    new VentanaRegistroProductos();
                }
                if (e.getSource() == btnEditar) {
                    if(panelTabla.getTabla().getSelectedRow() >= 0){
                        Producto producto = Archivo.ARCHIVO_EMPLEADOS.extraerDatosProducto(Archivo.ARCHIVO_EMPLEADOS.obtenerProducto(panelTabla.getTabla().getSelectedRow()));
                        Archivo.ARCHIVO_PRODUCTOS.eliminarProducto(Archivo.ARCHIVO_PRODUCTOS.obtenerProducto(panelTabla.getTabla().getSelectedRow()));
                        dispose();
                        new VentanaRegistroProductos(producto);
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un producto para editar");
                    }
                }
                if (e.getSource() == btnEliminar) {
                    if(panelTabla.getTabla().getSelectedRow() >= 0){
                        Archivo.ARCHIVO_PRODUCTOS.eliminarProducto(Archivo.ARCHIVO_PRODUCTOS.obtenerProducto(panelTabla.getTabla().getSelectedRow()));
                        dispose();
                        new VentanaRegistrosProductos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione el registro a eliminar.");
                    }
                }
                if (e.getSource() == btnRegresar) {
                    dispose();
                    new VentanaMenuInicio();
                }
            }
        }
    }
}
