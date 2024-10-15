package co.edu.uniquindio.preparcil2.preparcial2.utils;

import co.edu.uniquindio.preparcil2.preparcial2.model.Escuela;
import co.edu.uniquindio.preparcil2.preparcial2.model.Estudiante;

public class EscuelaUtils {
    public static Escuela inicializarDatos() {

        Escuela escuela = new Escuela();

        Estudiante estudiante = new Estudiante("Juan");
        estudiante.getListaNotas().add("5.0");
        estudiante.getListaNotas().add("1.0");
        estudiante.getListaNotas().add("3.0");
        escuela.getListaEstudiantes().add(estudiante);

        estudiante = new Estudiante("Patricio");
        estudiante.getListaNotas().add("2.0");
        estudiante.getListaNotas().add("1.0");
        estudiante.getListaNotas().add("3.0");
        escuela.getListaEstudiantes().add(estudiante);

        estudiante = new Estudiante("Manuela");
        estudiante.getListaNotas().add("5.0");
        estudiante.getListaNotas().add("4.0");
        estudiante.getListaNotas().add("3.0");
        escuela.getListaEstudiantes().add(estudiante);
        System.out.println("Información de la Escuela creada");
        return escuela;

    }
}
