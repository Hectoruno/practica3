import mvc.gui.Controlador;
import mvc.gui.Vista;
import mvc.modelo.Modelo;
import util.Util;

import java.util.Locale;

public class Principal {
    public static void main(String[] args) {
        //Cargar propiedades de idioma
        Locale locale = Util.obtenerLocale();
        Locale.setDefault(locale);

        Util.crearSiNoExisteDirectorioDatos();

        Vista vista = new Vista();
        Modelo modelo = new Modelo();

        Controlador controlador = new Controlador(vista, modelo);
    }

}
