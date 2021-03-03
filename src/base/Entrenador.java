package base;

import java.io.Serializable;
import java.time.LocalDate;

public class Entrenador implements Comparable<Entrenador>, Serializable {
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate edad;
    private Tenista tenista;

    public Entrenador(String dni, String nombre, String apellidos, LocalDate edad, Tenista tenista) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.tenista = tenista;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public Tenista getTenista() {
        return tenista;
    }

    public void setTenista(Tenista tenista) {
        this.tenista = tenista;
    }

    @Override
    public String toString() {
        return dni + " " + nombre + " " + apellidos;
    }

    @Override
    public int compareTo(Entrenador entrenador){
        if(entrenador == null){
            throw new NullPointerException("entrenador es null");
        }

        if(entrenador.getClass() != Entrenador.class){
            throw new ClassCastException("el objeto no es de tipo Entrenador");
        }

        if(apellidos.equalsIgnoreCase(entrenador.apellidos)){
            return nombre.compareTo(entrenador.nombre);
        }
        return apellidos.compareTo(entrenador.apellidos);
    }


}
