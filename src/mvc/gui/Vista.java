package mvc.gui;

import base.Entrenador;
import base.Raqueta;
import base.Tenista;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class Vista {
    JFrame frame;
    JPanel contentPane;
    JButton cargarFicheroBtn;
    JButton guardarFicheroBtn;
    JButton mostrarRelacionesBtn;
    JButton preferenciasBtn;
    JTabbedPane tabbedPane1;
    JTextField licenciaTenistaTxt;
    JTextField nombreTenistaTxt;
    JTextField apellidosTenistaTxt;
    JTextField codigoRaquetaTxt;
    JTextField marcaRaquetaTxt;
    JTextField modeloRaquetaTxt;
    JTextField precioRaquetaTxt;
    JButton nuevoTenistaBtn;
    JButton modificarTenistaBtn;
    JButton eliminarTenistaBtn;
    JList listTenista;
    JList listRaquetasTenista;
    JButton altaRaquetabtn;
    JComboBox TenistaParaRaquetaCb;
    JButton nuevaRaquetaBtn;
    JButton modificarRaquetaBtn;
    JButton eliminarRaquetaBtn;
    JList listRaquetas;
    DatePicker tenistaDatePicker;
    JButton seleccionarFotoRaquetaBtn;
    JButton eliminarFotoRaquetaBtn;
    JLabel fotoRaquetaLbl;
    JTextField textDni;
    JTextField textNombreEntrenador;
    JTextField textApellidosEntrenador;
    JComboBox comboBoxEntrenador;
    DatePicker datePickerEntrenador;
    JButton nuevoEntrenadorBoton;
    JButton modificarEntrenadorBoton;
    JButton eliminarEntrenadorBoton;
    JButton informeBtn;
    JButton graficaBtn;
    JList listEntrenador;
    JButton barrasBtn;
    JButton informeBasicoBtn;
    ResourceBundle resourceBundle;
    DefaultListModel<Tenista> tenistaDlm;
    DefaultListModel<Raqueta> raquetasParaTenistaDlm;
    DefaultListModel<Raqueta> raquetasDlm;
    DefaultComboBoxModel<Tenista> tenistaParaRaquetaDcbm;
    DefaultListModel<Entrenador>entrenadorDlm;
    DefaultComboBoxModel<Tenista> tenistaParaEntrenadorDcbm;

    public Vista() {
        resourceBundle = ResourceBundle.getBundle("ResourceBundleIdiomas");
        frame = new JFrame("Gestion Tenistas");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarModelos();
        activarControlPorTeclado();

        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);


    }
    /**
     * Metodo para iniciar las listas y combobox
     */

    private void iniciarModelos() {
        raquetasParaTenistaDlm = new DefaultListModel<>();
        raquetasDlm = new DefaultListModel<>();
        tenistaDlm = new DefaultListModel<>();
        tenistaParaRaquetaDcbm = new DefaultComboBoxModel<>();
        entrenadorDlm = new DefaultListModel<>();
        tenistaParaEntrenadorDcbm = new DefaultComboBoxModel<>();

        listTenista.setModel(tenistaDlm);
        listRaquetasTenista.setModel(raquetasParaTenistaDlm);
        listRaquetas.setModel(raquetasDlm);
        TenistaParaRaquetaCb.setModel(tenistaParaRaquetaDcbm);
        listEntrenador.setModel(entrenadorDlm);
        comboBoxEntrenador.setModel(tenistaParaRaquetaDcbm);
    }
    /**
     * Metodo para activar el control por teclado de los botones
     */
    private void activarControlPorTeclado(){
        //Acceso Mnemónico
        modificarTenistaBtn.setMnemonic(KeyEvent.VK_U);
        nuevoTenistaBtn.setMnemonic(KeyEvent.VK_Y);
        eliminarTenistaBtn.setMnemonic(KeyEvent.VK_O);
        nuevaRaquetaBtn.setMnemonic(KeyEvent.VK_A);
        modificarRaquetaBtn.setMnemonic(KeyEvent.VK_W);
        eliminarRaquetaBtn.setMnemonic(KeyEvent.VK_T);
        nuevoEntrenadorBoton.setMnemonic(KeyEvent.VK_B);
        modificarEntrenadorBoton.setMnemonic(KeyEvent.VK_D);
        eliminarEntrenadorBoton.setMnemonic(KeyEvent.VK_F);
        altaRaquetabtn.setMnemonic(KeyEvent.VK_Z);
        eliminarFotoRaquetaBtn.setMnemonic(KeyEvent.VK_V);
        seleccionarFotoRaquetaBtn.setMnemonic(KeyEvent.VK_1);

        guardarFicheroBtn.setMnemonic(KeyEvent.VK_G);
        cargarFicheroBtn.setMnemonic(KeyEvent.VK_C);
        mostrarRelacionesBtn.setMnemonic(KeyEvent.VK_R);
        graficaBtn.setMnemonic(KeyEvent.VK_M);
        barrasBtn.setMnemonic(KeyEvent.VK_K);
        informeBtn.setMnemonic(KeyEvent.VK_I);
        informeBasicoBtn.setMnemonic(KeyEvent.VK_L);
        preferenciasBtn.setMnemonic(KeyEvent.VK_P);

        //Boton por defecto de la aplicacion
        nuevoTenistaBtn.getRootPane().setDefaultButton(nuevoTenistaBtn);
    }
}
