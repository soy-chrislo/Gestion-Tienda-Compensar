import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Archivo {
    FileWriter fileWriter;
    File file, fileProductos;
    Scanner scanner;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    public static Archivo ARCHIVO_EMPLEADOS = new Archivo();
    public static Archivo ARCHIVO_PRODUCTOS = new Archivo();


    public Archivo(){
        crearArchivo();
    }

    public void crearArchivo(){
        file = new File("empleados.txt");
        fileProductos = new File("productos.txt");
        try {
            file.createNewFile();
            fileProductos.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void eliminarArchivo(){
        try {
            boolean result = file.delete();
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    // Podría recibir un array de string o un string del formato "nombre - edad - cedula - jornada - tiempoTrabajado".
    // Más adelante, se tomaría cada valor con el char('-').
    public void crearRegistro(ArrayList<String> registros){
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al inicializar fileWriter en crearRegistro().");
        }
        for (String registro : registros){
            try {
                fileWriter.write(registro + " - ");
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Error en crearRegistro().");
            }
        }
        try {
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al ejecutar .close() de fileWriter en crearRegistro().");
        }
    }

    public void crearRegistro(String registros){
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al inicializar fileWriter en crearRegistro().");
        }
        try {
            fileWriter.write(registros + "\n");
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al ejecutar .close() de fileWriter en crearRegistro().");
        }
    }
    public void crearRegistro(Empleado empleado){
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al inicializar fileWriter en crearRegistro().");
        }
        try {
            fileWriter.write(empleado.getNombre() + " - " + empleado.getNumIdentificacion() + " - " + empleado.getEdad() + " - " + empleado.getJornada() + " - " + empleado.getTiempoTrabajado() + "\n");
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al ejecutar .close() de fileWriter en crearRegistro().");
        }
    }

    public void crearRegistro(Producto producto){
        try {
            fileWriter = new FileWriter(fileProductos, true);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al inicializar fileWriter en crearRegistro().");
        }
        try {
            fileWriter.write(producto.getNombre() + " - " + String.valueOf(producto.getTipoProducto()) + " - " +  String.valueOf(producto.getNumUnidades()) + " - " + String.valueOf(producto.getValorUnitario()) + "\n");
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al ejecutar .close() de fileWriter en crearRegistro().");
        }
    }

    private ArrayList<Producto> productos;
    public ArrayList<Producto> importarProductos(){
        productos = new ArrayList<>();

        try {
            scanner = new Scanner(fileProductos);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Error al inicializar scanner en importarProductos().");
        }
        while(scanner.hasNextLine()){
            productos.add(extraerDatosProducto(scanner.nextLine()));
        }
        scanner.close();
        return productos;
    }
    private ArrayList<Empleado> empleados;
    public ArrayList<Empleado> importarRegistros(){
        empleados = new ArrayList<>();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        while (scanner.hasNextLine()){
            empleados.add(extraerDatos(scanner.nextLine()));
        }
        scanner.close();
        return empleados;
    }
    public Producto extraerDatosProducto(String datos) {
        String[] datosProducto = datos.split(" - ");
        Producto producto = new Producto(datosProducto[0], Integer.parseInt(datosProducto[1]), Integer.parseInt(datosProducto[2]), Double.parseDouble(datosProducto[3]));
        return producto;
    }
    private String nombre, cedula, edad, jornada, tiempoTrabajado;

    public Empleado extraerDatos(String datos){
        // Este método recibe un String con el formato en tabla de los datos: "Chris - 1005814 - 19 - diurna - 2".
        // Retornará un objeto de tipo Empleado con los datos de sus respectivas propiedades.
        nombre = "";
        cedula = "";
        edad = "";
        jornada = "";
        tiempoTrabajado = "";
        String registro = datos;
        // Se re-asignan los valores dentro del método, ya que al estar al nivel de la clase (como atributo) con cada ejecución del método es modificado y su valor se mantiene.
        int contadorDatos = 0;
        // Cuando contadorDatos es igual a 1, se almacena el nombre,
        //String datosTemp = "Chris - 1005814 - 19 - diurna - 2";


        for (int i = 0 ; i < registro.length() ; i++){
            if (contadorDatos == 0){
                // Este es el nombre.
                if (registro.charAt(i) == '-'){
                    contadorDatos++;
                    continue;
                    // La idea del break es que salga de la condición contarDatos == 0 y pase a la siguiente, que no asigne '-' a la variable de nombre, en este punto, ya nombre debería estar completo.
                    // Break rompe el ciclo for, se usá continue para saltar la iteración.
                }
                nombre += registro.charAt(i);
            }
            if (contadorDatos == 1){
                if (registro.charAt(i) == '-'){
                    contadorDatos++;
                    continue;
                }
                // Esta es la cedula.
                cedula += registro.charAt(i);
            }
            if (contadorDatos == 2){
                if (registro.charAt(i) == '-'){
                    contadorDatos++;
                    continue;
                }
                // Esta es la edad.
                edad += registro.charAt(i);
            }
            if (contadorDatos == 3){
                if (registro.charAt(i) == '-'){
                    contadorDatos++;
                    continue;
                }
                // Esta es la jornada.
                jornada += registro.charAt(i);
            }
            if (contadorDatos == 4){
                if (registro.charAt(i) == '-'){
                    contadorDatos++;
                    continue;
                }
                // Este es el tiempo trabajado (en años)
                tiempoTrabajado += registro.charAt(i);
            }
        }
        int jornadaInt = jornada.trim().equals("diurna") ? 0 : 1;
        return new Empleado(nombre.trim(), Integer.parseInt(cedula.trim()), Integer.parseInt(edad.trim()), Integer.parseInt(jornada.trim()), Integer.parseInt(tiempoTrabajado.trim()));
    }

    /* Un registro representa cada una de las líneas almacenadas en registro.txt.
    * El index representa la posición de la línea en el ARCHIVO_EMPLEADOS.
    * */

    String registro;
    public String obtenerRegistro(int index){
        registro = "";
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            registro = Files.readAllLines(Paths.get(file.getPath())).get(index);
        } catch (IOException e){
            e.printStackTrace();
        }
        scanner.close();
        return registro.trim();
    }

    public String obtenerProducto(int index){
        registro = "";
        try {
            scanner = new Scanner(fileProductos);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            registro = Files.readAllLines(Paths.get(fileProductos.getPath())).get(index);
        } catch (IOException e){
            e.printStackTrace();
        }
        scanner.close();
        return registro.trim();
    }
    public void actualizarRegistro() {

    }

    /* Ya que ubicar las lineas de un ARCHIVO_EMPLEADOS es mas complejo, se eliminará por medio de un String que equivalga a la misma línea en cuestión.
    * Esto quiere decir, que se debe agregar la función de no repetir datos, en ese caso, se podría hacer con el número de identificación.
    * TODO: Agregar una función que verifique si la cédula ya existe. (ya esta registrada)
    * */
    // Todo apunta a que la opción más conveniente es eliminar el ARCHIVO_EMPLEADOS de registro y crear uno nuevo. Utilizando la condición de que no debe agregarse el registro que previamente se "eliminó".
    // La lógica esta en que se duplica el registro, y en el nuevo no se incluye el registro que se "debía" eliminar.

    /*
    * Créditos: https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
    * */
    File tempFile;
    String lineaRemover, lineaActual;
    public void eliminarRegistro(String registro){
        lineaActual = "";
        lineaRemover = "";
        tempFile = new File("temp.txt");
        try {
            scanner = new Scanner(file);
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedWriter = new BufferedWriter(new FileWriter(tempFile, true));
        } catch (IOException e){
            e.printStackTrace();
        }
        lineaRemover = registro;
        try {
            while ((lineaActual = bufferedReader.readLine()) != null){
                if (!lineaActual.equals(lineaRemover)){
                    bufferedWriter.write(lineaActual);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            scanner.close();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        };

        boolean result1 = file.delete();
        boolean result2 = tempFile.renameTo(file);

        // Solución a medias, se asigna la referencia de tempFile a file, y esto genera que el nuevo ARCHIVO_EMPLEADOS de registro sea "temp.txt".
        //file = tempFile; NO
        System.out.println("Se ha eliminado el registro.");
    }

    public void eliminarProducto(String registro){
        lineaActual = "";
        lineaRemover = "";
        tempFile = new File("temp.txt");
        try {
            scanner = new Scanner(fileProductos);
            bufferedReader = new BufferedReader(new FileReader(fileProductos));
            bufferedWriter = new BufferedWriter(new FileWriter(tempFile, true));
        } catch (IOException e){
            e.printStackTrace();
        }
        lineaRemover = registro;
        try {
            while ((lineaActual = bufferedReader.readLine()) != null){
                if (!lineaActual.equals(lineaRemover)){
                    bufferedWriter.write(lineaActual);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            scanner.close();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        };

        boolean result1 = fileProductos.delete();
        boolean result2 = tempFile.renameTo(fileProductos);

        // Solución a medias, se asigna la referencia de tempFile a file, y esto genera que el nuevo ARCHIVO_EMPLEADOS de registro sea "temp.txt".
        //file = tempFile; NO
        System.out.println("Se ha eliminado el registro.");
    }
}
