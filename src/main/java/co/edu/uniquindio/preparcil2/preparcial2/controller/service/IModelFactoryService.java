package co.edu.uniquindio.preparcil2.preparcial2.controller.service;

import co.edu.uniquindio.preparcil2.preparcial2.model.Estudiante;

public interface IModelFactoryService {

    boolean crearEstuadiante(Estudiante estudiante);

    boolean actualizarEstuadiante(Estudiante estudiante);

    boolean eliminarEstudiante(Estudiante estudiante);
}
