package base;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tenista implements Comparable<Tenista>, Serializable {
    private String licencia;
    private String nombre;
    private String apellidos;
    private LocalDate edad;
    private List<Raqueta> raquetas;
    private List<Entrenador> entrenadores;

    public Tenista(String licencia, String nombre, String apellidos, LocalDate edad) {
        this.licencia = licencia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        raquetas = new ArrayList<>();
        entrenadores = new ArrayList<>();

    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getEdad() {
        return edad;
    }

    public void setEdad(LocalDate edad) {
        this.edad = edad;
    }

    public List<Raqueta> getRaquetas() {
        return raquetas;
    }

    public void setRaquetas(List<Raqueta> raquetas) {
        this.raquetas = raquetas;
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    @Override
    public String toString() {
        return licencia + " " + nombre + " " + apellidos;
    }

    @Override
    public int compareTo(Tenista tenista) {
        if(tenista == null){
            throw new NullPointerException("tenista es null");
        }

        if(tenista.getClass() != Tenista.class){
            throw new ClassCastException("el objeto no es de tipo Tenista");
        }

        if(apellidos.equalsIgnoreCase(tenista.apellidos)){
            return nombre.compareTo(tenista.nombre);
        }
        return apellidos.compareTo(tenista.apellidos);
    }
}
