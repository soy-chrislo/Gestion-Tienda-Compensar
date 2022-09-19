import java.util.ArrayList;

public class Producto {
    /* nombre del producto, tipo de producto, número de unidades, valor unitario, valor del IVA y el valor total.*/
    private String nombre;
    /*
     * 0 - Aseo
     * 1 - Papeleria
     * 2 - Víveres
     * 3 - Mascotas
     * 4 - Otros
     * */
    private int tipoProducto;
    private int numUnidades;
    private double valorUnitario;


    public Producto() {
        this.nombre = "";
        this.tipoProducto = 0;
        this.numUnidades = 0;
        this.valorUnitario = 0;
    }

    public Producto(String nombre, int tipoProducto, int numUnidades, double valorUnitario) {
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
        this.numUnidades = numUnidades;
        this.valorUnitario = valorUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(int numUnidades) {
        this.numUnidades = numUnidades;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}

class RegistrosProductos implements Registros {
    private ArrayList<Producto> productos = new ArrayList<>();

    @Override
    public void agregarRegistro(Object obj) {
        productos.add((Producto) obj);
    }

    @Override
    public void eliminarRegistro(Object obj) {
        productos.remove((Producto) obj);
    }

    @Override
    public void editarRegistro(int index, Object obj){
        /* Pese a editarse el registro, realmente se borra y asigna un objeto nuevo a la misma ubicación. */
        eliminarRegistro(productos.get(index));
        productos.add(index, (Producto) obj);
    }

    public Producto traerRegistro(int index){
        return productos.get(index);
    }

    public ArrayList<Producto> traerRegistros() {
        return productos;
    }

    public ArrayList<String> getNombreProductos(){
        ArrayList<String> nombresProductos = new ArrayList<>();
        for (Producto producto : productos) {
            nombresProductos.add(producto.getNombre());
        }
        return nombresProductos;
    }

    public ArrayList<Integer> getTipoProductos(){
        ArrayList<Integer> tiposProductos = new ArrayList<>();
        for (Producto producto : productos) {
            tiposProductos.add(producto.getTipoProducto());
        }
        return tiposProductos;
    }

    public ArrayList<Integer> getNumUnidadesProductos(){
        ArrayList<Integer> numUnidadesProductos = new ArrayList<>();
        for (Producto producto : productos) {
            numUnidadesProductos.add(producto.getNumUnidades());
        }
        return numUnidadesProductos;
    }

    public ArrayList<Double> getValorUnitarioProductos(){
        ArrayList<Double> valorUnitarioProductos = new ArrayList<>();
        for (Producto producto : productos) {
            valorUnitarioProductos.add(producto.getValorUnitario());
        }
        return valorUnitarioProductos;
    }
}
