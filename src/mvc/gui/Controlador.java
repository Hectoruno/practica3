package mvc.gui;

import base.Entrenador;
import base.Raqueta;
import base.Tenista;
import dialogos.DialogoPreferencias;
import dialogos.DialogoRaquetaParaTenista;
import dialogos.DialogoRelaciones;
import excepciones.DatosIntroducidosIncorrectosException;
import excepciones.ElementoNoSeleccionadoException;
import mvc.modelo.Modelo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class Controlador implements ActionListener, FocusListener, ListSelectionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        anadirActionListeners(this);
        anadirFocusListeners(this);
        anadirListSelectionListeners(this);
    }
    private void anadirListSelectionListeners(ListSelectionListener listener) {
        vista.listRaquetas.addListSelectionListener(listener);
        vista.listRaquetasTenista.addListSelectionListener(listener);
        vista.listTenista.addListSelectionListener(listener);
        vista.listEntrenador.addListSelectionListener(listener);

    }
    private void anadirActionListeners(ActionListener listener){
        vista.modificarTenistaBtn.addActionListener(listener);
        vista.nuevoTenistaBtn.addActionListener(listener);
        vista.eliminarTenistaBtn.addActionListener(listener);
        vista.cargarFicheroBtn.addActionListener(listener);
        vista.guardarFicheroBtn.addActionListener(listener);
        vista.preferenciasBtn.addActionListener(listener);
        vista.eliminarRaquetaBtn.addActionListener(listener);
        vista.nuevaRaquetaBtn.addActionListener(listener);
        vista.modificarRaquetaBtn.addActionListener(listener);
        vista.altaRaquetabtn.addActionListener(listener);
        vista.seleccionarFotoRaquetaBtn.addActionListener(listener);
        vista.eliminarFotoRaquetaBtn.addActionListener(listener);
        vista.nuevoEntrenadorBoton.addActionListener(listener);
        vista.modificarEntrenadorBoton.addActionListener(listener);
        vista.eliminarEntrenadorBoton.addActionListener(listener);
        vista.graficaBtn.addActionListener(listener);
        vista.informeBtn.addActionListener(listener);
        vista.informeBasicoBtn.addActionListener(listener);
        vista.barrasBtn.addActionListener(listener);

    }
    private void anadirFocusListeners(FocusListener listener){
        vista.licenciaTenistaTxt.addFocusListener(listener);
        vista.nombreTenistaTxt.addFocusListener(listener);
        vista.apellidosTenistaTxt.addFocusListener(listener);
        vista.modeloRaquetaTxt.addFocusListener(listener);
        vista.codigoRaquetaTxt.addFocusListener(listener);
        vista.marcaRaquetaTxt.addFocusListener(listener);
        vista.textDni.addFocusListener(listener);
        vista.textNombreEntrenador.addFocusListener(listener);
        vista.textApellidosEntrenador.addFocusListener(listener);

    }


    /**
     * Metodo que utilizamos para activar los botones mediante comandos
     * @param actionEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String comando = actionEvent.getActionCommand();
        try {
            switch (comando) {
                case "EliminarTenista": {
                    eliminarTenista();
                    break;
                }

                case "NuevoTenista": {
                    nuevoTenista();
                    break;
                }

                case "ModificarTenista": {
                    modificarTenista();
                    break;
                }

                case "NuevaRaqueta": {
                    nuevaRaqueta();
                    break;
                }

                case "EliminarRaqueta": {
                    eliminarRaqueta();
                    break;
                }

                case "ModificarRaqueta": {
                    modificarRaqueta();
                    break;
                }
                case "NuevoEntrenador": {
                    nuevoEntrenador();
                    break;
                }
                case "ModificarEntrenador": {
                    modificarEntrenador();
                    break;
                }
                case "EliminarEntrenador": {
                    eliminarEntrenador();
                    break;
                }

                case "AltaRaqueta":{
                    altaRaquetas();
                    break;
                }

                case "Guardar": {
                    guardarDatos();
                    break;
                }

                case "Cargar": {
                    cargarDatos();
                    break;
                }

                case "Preferencias": {
                    DialogoPreferencias dialogoPreferencias = new DialogoPreferencias();
                    break;
                }

                case "MostrarRelaciones": {
                    DialogoRelaciones dialogoRelaciones = new DialogoRelaciones();
                    break;
                }

                case "SeleccionarFoto": {
                    seleccionarFoto();
                    break;
                }

                case "EliminarFoto":{
                    vista.fotoRaquetaLbl.setIcon(null);
                    break;
                }
                case "Grafica":{
                    mostrarGraficoTarta();
                    break;
                }
                case "Barras":{
                    mostrarGraficoBarras();
                    break;
                }
                case "Informe":{
                    mostrarInforme();
                    break;
                }
                case "InformeBasico":{
                    mostrarInformeBasico();
                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            Util.mensajeError("Error de entrada/salida");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ElementoNoSeleccionadoException e) {
            e.printStackTrace();
            Util.mensajeError(e.getMessage());
        } catch (DatosIntroducidosIncorrectosException e) {
            e.printStackTrace();
            //Mostrar mensajes localizados (i18n)
            Util.mensajeError(e.getLocalizedMessage());
        }
    }
    /**
     * Metodo que lista tenistas en el JList
     */
    private void listarTenistasJlist(){
        vista.tenistaDlm.clear();
        for(Tenista tenista : modelo.getTenistas()){
            vista.tenistaDlm.addElement(tenista);
        }
    }
    /**
     * Metodo que da de alta nuevas raquetas
     * @throws ElementoNoSeleccionadoException
     */
    private void altaRaquetas() throws ElementoNoSeleccionadoException {
        if(vista.listTenista.isSelectionEmpty()){
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un tenista para modificar");
        }
        Tenista tenista = (Tenista) vista.listTenista.getSelectedValue();
        List<Raqueta> raquetasTotales = modelo.getRaquetas();
        DialogoRaquetaParaTenista dialogo = new DialogoRaquetaParaTenista(tenista, raquetasTotales);

        //Despues de los cambios relisto sus alumnos
        listarRaquetaParaTenista(tenista);
    }
    /**
     * Metodo que lista tenistas en un combobox
     */
    private void listarTenistasComboBox(){
        vista.tenistaParaRaquetaDcbm.removeAllElements();
        vista.tenistaParaRaquetaDcbm.addElement(null);
        for(Tenista tenista : modelo.getTenistas()){
            vista.tenistaParaRaquetaDcbm.addElement(tenista);
        }
    }
    /**
     * Metodo que lista una raqueta para un determinado tenista
     * @param tenista tenista
     */
    private void listarRaquetaParaTenista(Tenista tenista) {
        vista.raquetasParaTenistaDlm.clear();
        for(Raqueta raqueta : tenista.getRaquetas()){
            vista.raquetasParaTenistaDlm.addElement(raqueta);
        }
    }
    /**
     * Metodo para modificar un tenista
     * @throws ElementoNoSeleccionadoException
     * @throws DatosIntroducidosIncorrectosException
     */
    private void modificarTenista() throws ElementoNoSeleccionadoException, DatosIntroducidosIncorrectosException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourceBundleIdiomas");

        if(vista.listTenista.isSelectionEmpty()){
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un tenista para modificar");
        }

        if( !datosTenistaCorrectos()) {
            //Localizar el mensaje de una excepcion
            DatosIntroducidosIncorrectosException exception =
                    new DatosIntroducidosIncorrectosException("Datos de tenista incorrectos: datos en blanco");

            throw exception;
        }

        Tenista tenista = (Tenista) vista.listTenista.getSelectedValue();

        if(!vista.licenciaTenistaTxt.getText().equalsIgnoreCase(tenista.getLicencia())
                && modelo.existeLicenciaTenista(vista.licenciaTenistaTxt.getText())){

            //Localizar el mensaje de una excepcion
            DatosIntroducidosIncorrectosException exception =
                    new DatosIntroducidosIncorrectosException("Datos de tenista incorrectos: licencia repetida");

            throw exception;
        }

        tenista.setNombre(vista.nombreTenistaTxt.getText());
        tenista.setApellidos(vista.apellidosTenistaTxt.getText());
        tenista.setLicencia(vista.licenciaTenistaTxt.getText());
        tenista.setEdad(vista.tenistaDatePicker.getDate());

        listarTenistasComboBox();
        listarTenistasJlist();

    }
    /**
     * Metodo que comprueba que los campos de tenista no estén vacíos
     */
    private boolean datosTenistaCorrectos() {
        if(vista.nombreTenistaTxt.getText().isEmpty()
                || vista.apellidosTenistaTxt.getText().isEmpty()
                || vista.licenciaTenistaTxt.getText().isEmpty()){
            return false;
        }

        return true;
    }
    /**
     * Metodo para crear un nuevo objeto tenista
     * @throws DatosIntroducidosIncorrectosException
     */
    private void nuevoTenista() throws DatosIntroducidosIncorrectosException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourceBundleIdiomas");

        if(!datosTenistaCorrectos()) {
            //Localizar el mensaje de una excepcion
            DatosIntroducidosIncorrectosException exception =
                    new DatosIntroducidosIncorrectosException("Datos de tenista incorrectos: datos en blanco");

            throw exception;
        }
        if(modelo.existeLicenciaTenista(vista.licenciaTenistaTxt.getText())) {
            //Localizar el mensaje de una excepcion
            DatosIntroducidosIncorrectosException exception =
                    new DatosIntroducidosIncorrectosException("Datos de tenista incorrectos: licencia repetida");

            throw exception;
        }

        Tenista tenista = new Tenista(vista.licenciaTenistaTxt.getText(), vista.nombreTenistaTxt.getText(), vista.apellidosTenistaTxt.getText(),vista.tenistaDatePicker.getDate());
        modelo.anadirTenista(tenista);
        listarTenistasComboBox();
        listarTenistasJlist();

        //Lo selecciono en su lista
        vista.listTenista.setSelectedValue(tenista, true);

    }
    /**
     * Metodo que elimina un objeto tenista
     * @throws ElementoNoSeleccionadoException
     */
    private void eliminarTenista() throws ElementoNoSeleccionadoException {

        if(vista.listTenista.isSelectionEmpty()){
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un tenista para eliminar");
        }

        List<Tenista> listatenista = vista.listTenista.getSelectedValuesList();
        for (Tenista tenista : listatenista) {
            modelo.eliminarTenista(tenista);
        }

        listarTenistasComboBox();
        listarTenistasJlist();
    }
    /**
     * Metodo que muestra los datos de tenista
     */
    private void mostrarDatosTenista() {
        Tenista tenista = (Tenista) vista.listTenista.getSelectedValue();
        if(tenista == null){
            borrarCamposTenista();
        } else {
            vista.licenciaTenistaTxt.setText(tenista.getLicencia());
            vista.nombreTenistaTxt.setText(tenista.getNombre());
            vista.apellidosTenistaTxt.setText(tenista.getApellidos());
            vista.tenistaDatePicker.setDate(tenista.getEdad());
            listarRaquetaParaTenista(tenista);
        }
    }
    /**
     * Metodo que vacía los campos de tenista
     */
    private void borrarCamposTenista() {
        vista.licenciaTenistaTxt.setText("");
        vista.nombreTenistaTxt.setText("");
        vista.apellidosTenistaTxt.setText("");
        vista.raquetasParaTenistaDlm.clear();
    }
    /**
     * Metodo para seleccionar una foto e introducirla en el objeto raqueta
     */
    private void seleccionarFoto() {
        JFileChooser selector = new JFileChooser();
        int opt = selector.showOpenDialog(null);
        if(opt == JFileChooser.APPROVE_OPTION){

            File file = selector.getSelectedFile();

            ImageIcon icono = new ImageIcon(file.getPath());

            ImageIcon iconoEscalado = Util.escalarImageIcon(icono, 90, 90);

            vista.fotoRaquetaLbl.setIcon(iconoEscalado);
        }
    }
    /**
     * Metodo que lista raquetas en un JList
     */
    private void listarRaquetas(){
        vista.raquetasDlm.clear();
        for(Raqueta raqueta : modelo.getRaquetas()){
            vista.raquetasDlm.addElement(raqueta);
        }
    }
    /**
     * Metodo para modificar un objeto raqueta
     * @throws DatosIntroducidosIncorrectosException
     * @throws ElementoNoSeleccionadoException
     */
    private void modificarRaqueta() throws DatosIntroducidosIncorrectosException, ElementoNoSeleccionadoException {
        if(vista.listRaquetas.isSelectionEmpty()){
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un raqueta para modificar");
        }

        if(!datosRaquetaCorrectos()){
            throw new DatosIntroducidosIncorrectosException("Datos de raqueta incorrectos: datos en blanco");
        }

        Raqueta raqueta = (Raqueta) vista.listRaquetas.getSelectedValue();

        if(!vista.codigoRaquetaTxt.getText().equalsIgnoreCase(raqueta.getCodigo()) && modelo.existeCodigoRaqueta(vista.codigoRaquetaTxt.getText())){
            throw new DatosIntroducidosIncorrectosException("Datos de raqueta incorrectos: código repetido");
        }

        raqueta.setCodigo(vista.codigoRaquetaTxt.getText());
        raqueta.setMarca(vista.marcaRaquetaTxt.getText());
        raqueta.setModelo(vista.modeloRaquetaTxt.getText());
        raqueta.setPrecio(Double.parseDouble(vista.precioRaquetaTxt.getText()));
        raqueta.setFoto(vista.fotoRaquetaLbl.getIcon());
        raqueta.setTenista((Tenista) vista.TenistaParaRaquetaCb.getSelectedItem());

        vista.listRaquetas.requestFocus();
    }
    /**
     * Metodo que comprueba que los datos de raqueta no están vacíos
     */
    private boolean datosRaquetaCorrectos() {
        if(vista.codigoRaquetaTxt.getText().isEmpty()
                || vista.marcaRaquetaTxt.getText().isEmpty()
                || vista.modeloRaquetaTxt.getText().isEmpty()
                || vista.precioRaquetaTxt.getText().isEmpty()){
            return false;
        }

        return true;
    }
    /**
     * Metodo para eliminar un objeto raqueta
     * @throws ElementoNoSeleccionadoException
     */
    private void eliminarRaqueta() throws ElementoNoSeleccionadoException {
        if(vista.listRaquetas.isSelectionEmpty()){
            throw new ElementoNoSeleccionadoException("No se ha seleccionado una raqueta para eliminar");
        }
        List<Raqueta> listaraqueta = vista.listRaquetas.getSelectedValuesList();
        for (Raqueta raqueta : listaraqueta) {
            modelo.eliminarRaqueta(raqueta);
            modelo.eliminarTenista(raqueta.getTenista());
        }
        listarRaquetas();

    }
    /**
     * Metodo que crea un nuevo objeto raqueta
     * @throws DatosIntroducidosIncorrectosException
     */
    private void nuevaRaqueta() throws DatosIntroducidosIncorrectosException {
        if(!datosRaquetaCorrectos()) {
            throw new DatosIntroducidosIncorrectosException("Datos de raqueta incorrectos: datos en blanco");
        }

        if(modelo.existeCodigoRaqueta(vista.codigoRaquetaTxt.getText())){
            throw new DatosIntroducidosIncorrectosException("Datos de raqueta incorrectos: código repetido");
        }

        Raqueta raqueta = new Raqueta(vista.codigoRaquetaTxt.getText(), vista.marcaRaquetaTxt.getText(),
                vista.modeloRaquetaTxt.getText(), Double.parseDouble(vista.precioRaquetaTxt.getText()),vista.fotoRaquetaLbl.getIcon(),
                (Tenista) vista.TenistaParaRaquetaCb.getSelectedItem());

        //Annado a la raqueta tambien el valor de la imagen
        raqueta.setFoto(vista.fotoRaquetaLbl.getIcon());

        modelo.anadirRaqueta(raqueta);

        listarRaquetas();
        //Lo selecciono en su lista
        vista.listRaquetas.setSelectedValue(raqueta, true);
    }
    /**
     * Metodo que selecciona una raqueta de la lista
     */
    private void seleccionarRaquetaDeLista() {
        Raqueta raqueta = (Raqueta) vista.listRaquetasTenista.getSelectedValue();
        if(raqueta != null){
            //Vacio la seleccion de la lista de raquetas de tenista
            vista.listRaquetasTenista.clearSelection();
            //Voy a la pestaña de raquetas
            vista.tabbedPane1.setSelectedIndex(1);
            //Selecciono dicha raqueta del JList de raquetas (por lo que se mostraran sus datos)
            vista.listRaquetas.setSelectedValue(raqueta, true);
        }
    }
    /**
     * Metodo que muestra los datos de raqueta
     */
    private void mostrarDatosRaqueta() {
        Raqueta raqueta = (Raqueta) vista.listRaquetas.getSelectedValue();
        if(raqueta == null){
            borrarCamposRaqueta();
        } else {
            vista.codigoRaquetaTxt.setText(raqueta.getCodigo());
            vista.marcaRaquetaTxt.setText(raqueta.getMarca());
            vista.modeloRaquetaTxt.setText(raqueta.getModelo());
            vista.precioRaquetaTxt.setText(raqueta.getPrecio().toString());
            vista.fotoRaquetaLbl.setIcon(raqueta.getFoto());
            vista.TenistaParaRaquetaCb.setSelectedItem(raqueta.getTenista());
        }
    }
    /**
     * Metodo que vacía los campos de raqueta
     */
    private void borrarCamposRaqueta() {
        vista.codigoRaquetaTxt.setText("");
        vista.marcaRaquetaTxt.setText("");
        vista.modeloRaquetaTxt.setText("");
        vista.precioRaquetaTxt.setText("");
        vista.fotoRaquetaLbl.setIcon(null);
        vista.TenistaParaRaquetaCb.setSelectedItem(null);

    }


    private void listarEntrenadores(){
        vista.entrenadorDlm.clear();
        for(Entrenador entrenador : modelo.getEntrenadores()){
            vista.entrenadorDlm.addElement(entrenador);
        }
    }
    /**
     * Metodo para modificar un objeto entrenador
     * @throws DatosIntroducidosIncorrectosException
     * @throws ElementoNoSeleccionadoException
     */
    private void modificarEntrenador() throws DatosIntroducidosIncorrectosException, ElementoNoSeleccionadoException {
        if(vista.listEntrenador.isSelectionEmpty()){
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un entrenador para modificar");
        }

        if(!datosEntrenadorCorrectos()){
            throw new DatosIntroducidosIncorrectosException("Datos de entrenador incorrectos: datos en blanco");
        }

        Entrenador entrenador = (Entrenador) vista.listEntrenador.getSelectedValue();

        if(!vista.textDni.getText().equalsIgnoreCase(entrenador.getDni()) && modelo.existeDniEntrenador(vista.textDni.getText())){
            throw new DatosIntroducidosIncorrectosException("Datos de entrenador incorrectos: dni repetido");
        }

        entrenador.setDni(vista.textDni.getText());
        entrenador.setNombre(vista.textNombreEntrenador.getText());
        entrenador.setApellidos(vista.textApellidosEntrenador.getText());
        entrenador.setEdad(vista.datePickerEntrenador.getDate());
        entrenador.setTenista((Tenista) vista.comboBoxEntrenador.getSelectedItem());

        vista.listEntrenador.requestFocus();
    }
    /**
     * Metodo que comprueba que los datos de entrenador no están vacíos
     */
    private boolean datosEntrenadorCorrectos() {
        if(vista.textDni.getText().isEmpty()
                || vista.textNombreEntrenador.getText().isEmpty()
                || vista.textApellidosEntrenador.getText().isEmpty()
                || vista.datePickerEntrenador.getDate().toString().isEmpty()){
            return false;
        }

        return true;
    }
    /**
     * Metodo para eliminar un objeto raqueta
     * @throws ElementoNoSeleccionadoException
     */
    private void eliminarEntrenador() throws ElementoNoSeleccionadoException {
        if(vista.listEntrenador.isSelectionEmpty()){
            throw new ElementoNoSeleccionadoException("No se ha seleccionado un entrenador para eliminar");
        }
        List<Entrenador> listaEntrenador = vista.listEntrenador.getSelectedValuesList();
        for (Entrenador entrenador : listaEntrenador) {
            modelo.eliminarEntrenador(entrenador);
            modelo.eliminarTenista(entrenador.getTenista());
        }

        listarEntrenadores();

    }
    /**
     * Metodo que crea un nuevo objeto raqueta
     * @throws DatosIntroducidosIncorrectosException
     */
    private void nuevoEntrenador() throws DatosIntroducidosIncorrectosException {
        if(!datosEntrenadorCorrectos()) {
            throw new DatosIntroducidosIncorrectosException("Datos de entrenador incorrectos: datos en blanco");
        }

        if(modelo.existeDniEntrenador(vista.textDni.getText())){
            throw new DatosIntroducidosIncorrectosException("Datos de entrenador incorrectos: dni repetido");
        }

        Entrenador entrenador = new Entrenador(vista.textDni.getText(), vista.textNombreEntrenador.getText(),
                vista.textApellidosEntrenador.getText(), vista.datePickerEntrenador.getDate(),
                (Tenista) vista.comboBoxEntrenador.getSelectedItem());


        modelo.anadirEntrenador(entrenador);

        listarEntrenadores();
        //Lo selecciono en su lista
        vista.listEntrenador.setSelectedValue(entrenador, true);
    }
    /**
     * Metodo que selecciona una entrenador de la lista
     */
    private void seleccionarEntrenadorDeLista() {
        Entrenador entrenador = (Entrenador) vista.listEntrenador.getSelectedValue();
        if(entrenador != null){
            //Vacio la seleccion de la lista de entrenador de tenista
            vista.listEntrenador.clearSelection();
            //Voy a la pestaña de entrenador
            vista.tabbedPane1.setSelectedIndex(1);
            //Selecciono dicha entrenador del JList de entrenador (por lo que se mostraran sus datos)
            vista.listEntrenador.setSelectedValue(entrenador, true);
        }
    }
    /**
     * Metodo que muestra los datos de entrenador
     */
    private void mostrarDatosEntrenador() {
        Entrenador entrenador = (Entrenador) vista.listEntrenador.getSelectedValue();
        if(entrenador == null){
            borrarCamposEntrenador();
        } else {
            vista.textDni.setText(entrenador.getDni());
            vista.textNombreEntrenador.setText(entrenador.getNombre());
            vista.textApellidosEntrenador.setText(entrenador.getApellidos());
            vista.datePickerEntrenador.setDate(entrenador.getEdad());
            vista.comboBoxEntrenador.setSelectedItem(entrenador.getTenista());
        }
    }
    /**
     * Metodo que vacía los campos de entrenador
     */
    private void borrarCamposEntrenador() {
        vista.textDni.setText("");
        vista.textNombreEntrenador.setText("");
        vista.textApellidosEntrenador.setText("");
        vista.tenistaDatePicker.setText("");
        vista.comboBoxEntrenador.setSelectedItem(null);

    }



    /**
     * Metodo que carga los datos
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void cargarDatos() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        int opt = fileChooser.showOpenDialog(vista.frame);
        if(opt == JFileChooser.APPROVE_OPTION){
            modelo.cargarDatos(fileChooser.getSelectedFile());
        }
        listarTenistasComboBox();
        listarTenistasJlist();
        listarRaquetas();
        listarEntrenadores();
    }
    /**
     * Metodo que guarda datos
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void guardarDatos() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        int opt = fileChooser.showSaveDialog(vista.frame);
        if(opt == JFileChooser.APPROVE_OPTION){
            modelo.guardarDatos(fileChooser.getSelectedFile());
        }
    }
    @Override
    public void focusGained(FocusEvent focusEvent) {
        //Para seleccionar el texto de un campo de texto al pulsar sobre el
        if(focusEvent.getSource().getClass() == JTextField.class){
            JTextField campoTexto = (JTextField) focusEvent.getSource();
            campoTexto.selectAll();
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }

    /**
     * Metodo que responde a las selecciones de elementos en los JList
     * @param e ListSelectionEvent
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == vista.listTenista){
            mostrarDatosTenista();
        } else if( e.getSource() == vista.listRaquetas){
            mostrarDatosRaqueta();
        } else if (e.getSource() == vista.listRaquetasTenista){
            seleccionarRaquetaDeLista();
        } else if (e.getSource() == vista.listEntrenador){
            mostrarDatosEntrenador();
        }
    }
    /**
     * Metodo que muestra un grafico de tarta
     */

    public void mostrarGraficoTarta(){
        DefaultPieDataset dataset = new DefaultPieDataset();

        for(Tenista tenista : modelo.getTenistas()){
            dataset.setValue(tenista.getNombre() + " - " + tenista.getApellidos(),tenista.getRaquetas().size());
        }

        JFreeChart grafica = ChartFactory.createPieChart("Raquetas por tenista",dataset,true,true,true);
        ChartFrame ventana = new ChartFrame("Cantidad de raquetas por tenista", grafica);
        ventana.pack();
        ventana.setSize(700,700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }
    /**
     * Metodo que muestra un grafico de barras
     */

    public void mostrarGraficoBarras() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Entrenador entrenador : modelo.getEntrenadores()) {
            int edad = 2021 - entrenador.getEdad().getYear();
            dataset.addValue(edad, "Edad entrenador", entrenador.getNombre());
        }

        JFreeChart grafica = ChartFactory.createBarChart("Edad por entrenador", "Entrenador", "Edad", dataset, PlotOrientation.VERTICAL, true, true, true);

        ChartFrame ventana = new ChartFrame("Gráfica Edad", grafica);
        ventana.setSize(700, 700);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }
    /**
     * Metodo que muestra un informe de las raquetas con su tenista
     */
    private void mostrarInforme(){
        JasperReport report = null;
        try {
            report = (JasperReport) JRLoader.loadObject(getClass().getResource("/informeTenista.jasper"));
        } catch (JRException e) {
            e.printStackTrace();
        }
        JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(modelo.getRaquetas());
        JasperPrint printer = null;
        try {
            printer = JasperFillManager.fillReport(report, null, coleccion);
        } catch (JRException e) {
            e.printStackTrace();
        }
        JasperViewer.viewReport(printer, false);
    }
    /**
     * Metodo que para mostrar el informe de Entrenadores
     */
    private void mostrarInformeBasico() {
        JasperReport report = null;
        try {
            report = (JasperReport) JRLoader.loadObject(getClass().getResource("/informeEntrenador.jasper"));
        } catch (JRException e) {
            e.printStackTrace();
        }
        JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(modelo.getEntrenadores());
        JasperPrint printer = null;
        try {
            printer = JasperFillManager.fillReport(report, null, coleccion);
        } catch (JRException e) {
            e.printStackTrace();
        }
        JasperViewer.viewReport(printer, false);
    }
}