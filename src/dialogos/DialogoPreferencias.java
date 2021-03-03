package dialogos;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class DialogoPreferencias extends JDialog{
    private JPanel panel1;
    private JButton AceptarButton;
    private JButton CancelarButton;
    private JRadioButton rbSpanish;
    private JRadioButton rbEnglish;

    public DialogoPreferencias(){
        initDialog();
        cargarConfiguracion();
        pack();
        setVisible(true);
    }

    private void initDialog() {
        setContentPane(panel1);
        setModal(true);
        getRootPane().setDefaultButton(AceptarButton);
        setTitle("Configuracion");
        setLocationRelativeTo(null);



        AceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        CancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        panel1.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        guardarConfiguracion();
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void guardarConfiguracion(){
        Properties propiedades = new Properties();
        String idioma;
        String pais;
        if(rbSpanish.isSelected()){
            idioma = "es";
            pais = "ES";
        } else {
            idioma = "en";
            pais = "UK";
        }
        propiedades.setProperty("idioma", idioma);
        propiedades.setProperty("pais", pais);

        try {
            propiedades.store(new FileWriter("data/preferencias.conf"), "Fichero de preferencias");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarConfiguracion() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("data/preferencias.conf"));

            String pais = properties.getProperty("pais");

            if (pais.equals("ES")) {
                rbSpanish.setSelected(true);
            } else {
                rbEnglish.setSelected(true);
            }

        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("Fichero de configuración inexistente: no existen configuraciones previas guardadas");

        }
    }
}
