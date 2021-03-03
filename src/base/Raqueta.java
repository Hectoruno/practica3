package base;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Raqueta implements Comparable<Raqueta>, Serializable {
    private String codigo;
    private String marca;
    private String modelo;
    private Double precio;
    private Icon foto;
    private Tenista tenista;
    private List<Tenista> tenistas;

    public Raqueta(String codigo, String marca, String modelo, Double precio, Icon foto, Tenista tenista) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.foto = foto;
        this.tenista = tenista;
        tenistas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Icon getFoto() {
        return foto;
    }

    public void setFoto(Icon foto) {
        this.foto = foto;
    }

    public Tenista getTenista() {
        return tenista;
    }

    public void setTenista(Tenista tenista) {
        this.tenista = tenista;
    }
    public void establecerTenistaSafe(Tenista tenista) {
        //Si antes ya tenia tenista, lo elimino de su lista
        if (this.tenista != null) {
            this.tenista.getRaquetas().remove(this);
        }
        this.tenista = tenista;
        //Tambien annado este raqueta a la lista de raquetas de su tenista
        if (tenista != null) {
            tenista.getRaquetas().add(this);
            Collections.sort(tenista.getRaquetas());
        }
    }

    @Override
    public String toString() {
        return codigo + " " + marca + " " + modelo;
    }

    @Override
    public int compareTo(Raqueta raqueta) {
        if(modelo.equals(raqueta.modelo)){
            return marca.compareTo(raqueta.marca);
        }

        return modelo.compareTo(raqueta.modelo);
    }
}
