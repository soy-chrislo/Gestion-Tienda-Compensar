import java.util.ArrayList;

public class Empleado {
    private String nombre;
    private int numIdentificacion;
    private int edad;
    /*
     * 0 - Jornada Diurna
     * 1 - Jornada Nocturna
     * */
    private int jornada;
    private int tiempoTrabajado;

    public Empleado() {
        this.nombre = "";
        this.numIdentificacion = 0;
        this.edad = 0;
        this.jornada = 0;
        this.tiempoTrabajado = 0;
    }

    public Empleado(String nombre, int numIdentificacion, int edad, int jornada, int tiempoTrabajado) {
        this.nombre = nombre;
        this.numIdentificacion = numIdentificacion;
        this.edad = edad;
        this.jornada = jornada;
        this.tiempoTrabajado = tiempoTrabajado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(int numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public int getTiempoTrabajado() {
        return tiempoTrabajado;
    }

    public void setTiempoTrabajado(int tiempoTrabajado) {
        this.tiempoTrabajado = tiempoTrabajado;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", numIdentificacion=" + numIdentificacion +
                ", edad=" + edad +
                ", jornada=" + jornada +
                ", tiempoTrabajado=" + tiempoTrabajado +
                '}';
    }
}

class RegistrosEmpleados implements Registros {
    //private Archivo archivo;

    private ArrayList<Empleado> empleados = new ArrayList<>();
    public RegistrosEmpleados(){
        //archivo = new Archivo();
        try {
            //archivo.crearArchivo();
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Error al ejecutar crearArchivo() de archivo en el constructor de RegistrosEmpleados().");
        }
    }

    @Override
    public void agregarRegistro(Object obj) {
        empleados.add((Empleado) obj);
        Archivo.ARCHIVO_EMPLEADOS.crearRegistro((Empleado) obj);
    }

    @Override
    public void eliminarRegistro(Object obj) {
        empleados.remove((Empleado) obj);
    }

    @Override
    public void editarRegistro(int index, Object obj){
        /* Pese a editarse el registro, realmente se borra y asigna un objeto nuevo a la misma ubicación. */
        eliminarRegistro(empleados.get(index));
        empleados.add(index, (Empleado) obj);
    }

    public Empleado traerRegistro(int index){
        return empleados.get(index);
    }

    public ArrayList<Empleado> traerRegistros() {
        return empleados;
    }

    public ArrayList<String> getNombresEmpleados(){
        ArrayList<String> nombresEmpleados = new ArrayList<>();
        for (Empleado empleado : empleados) {
            nombresEmpleados.add(empleado.getNombre());
        }
        return nombresEmpleados;
    }

    public ArrayList<Integer> getNumerosIdentificacion(){
        ArrayList<Integer> numerosIdentificacion = new ArrayList<>();
        for (Empleado empleado : empleados) {
            numerosIdentificacion.add(empleado.getNumIdentificacion());
        }
        return numerosIdentificacion;
    }

    public ArrayList<Integer> getEdades(){
        ArrayList<Integer> edades = new ArrayList<>();
        for (Empleado empleado : empleados) {
            edades.add(empleado.getEdad());
        }
        return edades;
    }

    public ArrayList<Integer> getJornadas(){
        ArrayList<Integer> jornadas = new ArrayList<>();
        for (Empleado empleado : empleados) {
            jornadas.add(empleado.getJornada());
        }
        return jornadas;
    }

    public ArrayList<Integer> getTiemposTrabajados(){
        ArrayList<Integer> tiemposTrabajados = new ArrayList<>();
        for (Empleado empleado : empleados) {
            tiemposTrabajados.add(empleado.getTiempoTrabajado());
        }
        return tiemposTrabajados;
    }
}