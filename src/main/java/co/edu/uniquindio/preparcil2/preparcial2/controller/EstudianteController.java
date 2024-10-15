package co.edu.uniquindio.preparcil2.preparcial2.controller;

import co.edu.uniquindio.preparcil2.preparcial2.controller.service.IEstudianteControllerService;
import co.edu.uniquindio.preparcil2.preparcial2.model.Estudiante;

import java.io.IOException;
import java.util.List;

public class EstudianteController implements IEstudianteControllerService {
    ModelFactoryController modelFactoryController;

    public EstudianteController() {
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public boolean crearUsuario(Estudiante estudiante) {
        return modelFactoryController.crearEstuadiante(estudiante);
    }

    @Override
    public boolean actualizarUsuario(Estudiante estudiante) {
        return false;
    }

    @Override
    public boolean eliminarUsuario(Estudiante estudiante) {
        return false;
    }
    public List<Estudiante> obtenerEstudiantes() {
        return modelFactoryController.obtenerEstudiantes();
    }

    public void imprimirDatosEstudiante(Estudiante estudiante) {
        modelFactoryController.imprimirEstudiante(estudiante);
    }
}
