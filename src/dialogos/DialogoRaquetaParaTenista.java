package dialogos;

import base.Raqueta;
import base.Tenista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogoRaquetaParaTenista extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<Raqueta> listRaquetasConTenista;
    private JList<Raqueta> listRaquetasSinTenista;
    private JButton conseguirButton;
    private JButton quitarButton;
    private DefaultListModel<Raqueta> conTenistaDlm;
    private DefaultListModel<Raqueta> sinTenistaDlm;
    private JLabel tenistaLbl;
    private Tenista tenista;
    private List<Raqueta> listaTemporalConTenista;
    private List<Raqueta> listaTemporalSinTenista;

    public DialogoRaquetaParaTenista(Tenista tenista, List<Raqueta> raquetasTotales) {
        this.tenista = tenista;

        //Creo dos listas temporales. Si finalmente acepto los cambios, se hacen efectivas
        listaTemporalConTenista = new ArrayList<>(tenista.getRaquetas());

        listaTemporalSinTenista = new ArrayList<>(raquetasTotales);

        //Elimino las raquetas que ya tiene el tenista de la lista de sin tenista
        listaTemporalSinTenista.removeAll(listaTemporalConTenista);

        conTenistaDlm = new DefaultListModel<>();
        sinTenistaDlm = new DefaultListModel<>();
        listRaquetasConTenista.setModel(conTenistaDlm);
        listRaquetasSinTenista.setModel(sinTenistaDlm);

        listarRaquetasConTenista();
        listarRaquetasSinTenista();

        initUI();
    }

    /**
     * Metodo para listar las raquetas que tienen tenista
     */

    private void listarRaquetasConTenista(){
        Collections.sort(listaTemporalConTenista);

        conTenistaDlm.clear();
        for(Raqueta raqueta : listaTemporalConTenista){
            conTenistaDlm.addElement(raqueta);
        }
    }

    /**
     * Metodo para listar las raquetas que no tienen tenista
     */

    private void listarRaquetasSinTenista(){
        Collections.sort(listaTemporalSinTenista);

        sinTenistaDlm.clear();
        for(Raqueta raqueta : listaTemporalSinTenista){
            sinTenistaDlm.addElement(raqueta);
        }
    }

    /**
     * Metodo para conseguir seleccionar un tenista para la raqueta
     */

    private void conseguirTenistaARaquetasSeleccionadas() {
        //Obtengo todas las raquetas seleccionadas de la lista de sin tenista
        List<Raqueta> seleccionadas = listRaquetasSinTenista.getSelectedValuesList();
        //Los elimino de su lista
        listaTemporalSinTenista.removeAll(seleccionadas);
        //Y los annado a la lista del tenista
        listaTemporalConTenista.addAll(seleccionadas);

        listarRaquetasConTenista();
        listarRaquetasSinTenista();
    }

    /**
     * Metodo para quitar tenista a las raquetas seleccionadas
     */

    private void quitarTenistaARaquetasSeleccionadas() {
        List<Raqueta> seleccionadas = listRaquetasConTenista.getSelectedValuesList();
        listaTemporalConTenista.removeAll(seleccionadas);
        listaTemporalSinTenista.addAll(seleccionadas);

        listarRaquetasConTenista();
        listarRaquetasSinTenista();
    }

    private void onOK() {
        realizarCambios();
        dispose();
    }

    private void realizarCambios() {
        //Los unicos cambios que afectan son los relativos a la lista definitiva de raquetas del tenista

        //Si finalmente acepto los cambios debo recorrer a las raquetas antiguas del tenista y quitarles el tenista
        //Y  recorrerlos para indicarles el nuevo tenista con tenista y cambiarles el tenista

        for(Raqueta raqueta : tenista.getRaquetas()){
            raqueta.setTenista(null);
        }

        for(Raqueta raqueta : listaTemporalConTenista){
            raqueta.establecerTenistaSafe(tenista);
        }

        tenista.setRaquetas(listaTemporalConTenista);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void initUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);
        setTitle(tenista.getLicencia() + " - " + tenista.getNombre() + " " + tenista.getApellidos());

        tenistaLbl.setText(tenista.toString());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        conseguirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitarTenistaARaquetasSeleccionadas();
            }
        });
        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conseguirTenistaARaquetasSeleccionadas();
            }
        });

        pack();
        setVisible(true);
    }
}
