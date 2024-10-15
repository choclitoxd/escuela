package co.edu.uniquindio.preparcil2.preparcial2.controller.service;

import co.edu.uniquindio.preparcil2.preparcial2.model.Estudiante;

public interface IEstudianteControllerService {

    boolean crearUsuario(Estudiante estudiante);

    boolean actualizarUsuario(Estudiante estudiante);

    boolean eliminarUsuario(Estudiante estudiante);
}
