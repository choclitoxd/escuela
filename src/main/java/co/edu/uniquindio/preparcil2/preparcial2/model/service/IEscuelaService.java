package co.edu.uniquindio.preparcil2.preparcial2.model.service;


import co.edu.uniquindio.preparcil2.preparcial2.exception.EstudianteException;

public interface IEscuelaService {

    public boolean verificarEstudianteExistente(String codigoEstuadiante, boolean crear) throws EstudianteException;
    public boolean verificarEstudianteExistenteEliminar(String codigoEstudiante) throws EstudianteException;

}
