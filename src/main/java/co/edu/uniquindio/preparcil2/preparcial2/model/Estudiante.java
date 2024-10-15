package co.edu.uniquindio.preparcil2.preparcial2.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private static int contador = 0;
    private String nombre;
    private String codigo;
    private List<String> listaNotas = new ArrayList<>();
    public Estudiante(String nombre) {
        this.nombre = nombre;
        this.codigo = String.valueOf(contador++);
    }
    public Estudiante() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<String> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<String> listaNotas) {
        this.listaNotas = listaNotas;
    }
}
