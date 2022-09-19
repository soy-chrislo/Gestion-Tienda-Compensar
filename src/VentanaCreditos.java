import javax.swing.*;
import java.awt.*;

public class VentanaCreditos extends JFrame {
    public VentanaCreditos() {
        setIconImage(new ImageIcon(getClass().getResource("logo-ucompensar.png")).getImage());
        setTitle("Créditos");
        setSize(500, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 3));
        setResizable(false);

        add(new PanelImagen());
        add(new PanelInformacion());

        setVisible(true);
    }

    private class PanelImagen extends JPanel {
        private Image imagen;
        private JLabel lblImagen;

        public PanelImagen() {
            imagen = new ImageIcon(getClass().getResource("ccro.jpg")).getImage().getScaledInstance(145, 160, Image.SCALE_SMOOTH);
            lblImagen = new JLabel();
            lblImagen.setIcon(new ImageIcon(imagen));
            add(lblImagen);
        }
    }

    private class PanelInformacion extends JPanel {
        private JTextPane txtInformacion;

        public PanelInformacion() {
            setLayout(new FlowLayout());
            txtInformacion = new JTextPane();
            txtInformacion.setEditable(false);
            txtInformacion.setText("Información del desarrollador:\n\n" +
                    "Nombre: Christian Camilo Rubio Ortiz\n" +
                    "Correo: ccrubio@ucompensar.edu.co\n\n" +
                    "Programa desarrollado para la asignatura\nAlgoritmos y Programación II del programa\nde Ingeniería de Software de la \nFundación Universitaria Compensar."
                    );

//            String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 200, "Programa desarrollado para la asignatura Algoritmos y Programación II del programa de Ingeniería de Software de la Fundación Universitaria Compensar."

            add(txtInformacion);
        }
    }
}
