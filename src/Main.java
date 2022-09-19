import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
* Al manejar registros de empleados y productos, la clase Archivo pasará a heredar a las clases ArchivoEmpleados y ArchivoProductos.
* - Deben poseer los mismos métodos ya que las interacciones siguen siendo las mismas, solo que cambian los tipos de datos a almacenar.
*
* TODO: Retomar el estudio de los diferentes tipos de layout, en especial GridLayout vs GridBagLayout.
* */


public class Main {
    public static void main(String[] args) {
        /*RegistrosEmpleados registrosEmpleados = new RegistrosEmpleados();
        registrosEmpleados.agregarRegistro(new Empleado("Chris", 12312, 19, 0, 2));
        registrosEmpleados.agregarRegistro(new Empleado("John", 12312, 21, 1, 1));
        registrosEmpleados.agregarRegistro(new Empleado("Jane", 12312, 32, 0, 4));
        registrosEmpleados.agregarRegistro(new Empleado("Juan", 12312, 25, 0, 5));

        for (Empleado empleado : registrosEmpleados.traerRegistros()){
            System.out.println(empleado.toString());
        }

        registrosEmpleados.editarRegistro(0, new Empleado("Chris", 1005814566, 19, 0, 2));

        for (Empleado empleado : registrosEmpleados.traerRegistros()){
            System.out.println(empleado.toString());
        }

        System.out.println(registrosEmpleados.traerRegistro(0).toString());*/

        VentanaMenuInicio mainframe = new VentanaMenuInicio();
        //VentanaRegistroEmpleados interfazRegistroEmpleados = new VentanaRegistroEmpleados();
    }
}









class InterfazRegistroProductos extends JFrame {
    public InterfazRegistroProductos() {
        setTitle("Registro de Empleados");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}












