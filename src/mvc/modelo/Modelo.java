package mvc.modelo;

import base.Entrenador;
import base.Raqueta;
import base.Tenista;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Modelo {
    private List<Tenista> tenistas;
    private List<Raqueta> raquetas;
    private List<Entrenador> entrenadores;
    public Modelo() {
        tenistas = new ArrayList<>();
        raquetas = new LinkedList<>();
        entrenadores = new LinkedList<>();
    }

    public List<Tenista> getTenistas() {
        return tenistas;
    }

    public List<Raqueta> getRaquetas() {
        return raquetas;
    }

    public List<Entrenador> getEntrenadores(){return entrenadores;}

    /**
     * Metodo que añade un tenista a la lista
     * @param tenista Tenista
     */

    public void anadirTenista(Tenista tenista){
        tenistas.add(tenista);
        Collections.sort(tenistas);
    }

    /**
     * Metodo que añade una raqueta a la lista
     * @param raqueta Raqueta
     */

    public void anadirRaqueta(Raqueta raqueta){
        raquetas.add(raqueta);
        Collections.sort(raquetas);

        if(raqueta.getTenista() != null){
            raqueta.getTenista().getRaquetas().add(raqueta);
            Collections.sort(raqueta.getTenista().getRaquetas());
        }
    }
    /**
     * Metodo que añade un entrenador a la lista
     * @param entrenador Entrenador
     */
    public void anadirEntrenador(Entrenador entrenador){
        entrenadores.add(entrenador);
        Collections.sort(entrenadores);

        if(entrenador.getTenista() != null){
            entrenador.getTenista().getEntrenadores().add(entrenador);
            Collections.sort(entrenador.getTenista().getEntrenadores());
        }
    }
    /**
     * Metodo que elimina una raqueta de la lista
     * @param raqueta Raqueta
     */

    public void eliminarRaqueta(Raqueta raqueta){
        if(raqueta.getTenista() != null){
            raqueta.getTenista().getRaquetas().remove(raqueta);
        }
        raquetas.remove(raqueta);
    }
    /**
     * Metodo que elimina un entrenador de la lista
     * @param entrenador Entrenador
     */
    public void eliminarEntrenador(Entrenador entrenador){
        if(entrenador.getTenista() != null){
            entrenador.getTenista().getEntrenadores().remove(entrenador);
        }
        entrenadores.remove(entrenador);
    }
    /**
     * Metodo que elimina un tenista de la lista
     * @param tenista Tenista
     */
    public void eliminarTenista(Tenista tenista){
        tenistas.remove(tenista);
    }

    /**
     * Metodo para cargar los datos
     * @param file File
     * @throws IOException si hay errores en E/S del programa
     * @throws ClassNotFoundException no encuentra la clase
     */

    public void cargarDatos(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream deserializador = new ObjectInputStream(fis);
        tenistas = (List<Tenista>) deserializador.readObject();
        raquetas = (List<Raqueta>) deserializador.readObject();
        entrenadores = (List<Entrenador>) deserializador.readObject();

        deserializador.close();
    }
    /**
     * Metodo para cargar los datos
     * @param file File
     * @throws IOException si hay errores en E/S del programa
     */

    public void guardarDatos(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream serializador = new ObjectOutputStream(fos);

        serializador.writeObject(tenistas);
        serializador.writeObject(raquetas);
        serializador.writeObject(entrenadores);

        serializador.close();
    }

    /**
     * Metodo que comprueba si existe un tenista con la misma licencia
     * @param licencia String
     * @return devuelve true si existe la licencia o false cuando no existe
     */

    public boolean existeLicenciaTenista(String licencia) {
        for(Tenista tenista : tenistas){
            if(tenista.getLicencia().equalsIgnoreCase(licencia)){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que comprueba si existe una raqueta con el mismo código
     * @param codigo String
     * @return devuelve true si existe el codigo o false cuando no existe
     */

    public boolean existeCodigoRaqueta(String codigo){
        for(Raqueta raqueta : raquetas){
            if(raqueta.getCodigo().equalsIgnoreCase(codigo)){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que comprueba si existe un entrenador con el mismo dni
     * @param dni String
     * @return devuelve true si existe el dni o false cuando no existe
     */

    public boolean existeDniEntrenador(String dni){
        for(Entrenador entrenador : entrenadores){
            if(entrenador.getDni().equalsIgnoreCase(dni)){
                return true;
            }
        }
        return false;
    }

}
