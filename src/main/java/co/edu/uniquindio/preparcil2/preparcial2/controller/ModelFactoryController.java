package co.edu.uniquindio.preparcil2.preparcial2.controller;

import co.edu.uniquindio.preparcil2.preparcial2.controller.service.IModelFactoryService;
import co.edu.uniquindio.preparcil2.preparcial2.exception.EstudianteException;
import co.edu.uniquindio.preparcil2.preparcial2.model.Escuela;
import co.edu.uniquindio.preparcil2.preparcial2.model.Estudiante;
import co.edu.uniquindio.preparcil2.preparcial2.utils.EscuelaUtils;
import co.edu.uniquindio.preparcil2.preparcial2.utils.Persistencia;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.List;

public class ModelFactoryController implements IModelFactoryService {
    Escuela escuela;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }
    public ModelFactoryController(){
        cargarDatosBase();
        salvarDatosPrueba();
    }
    private void cargarDatosBase(){
        escuela = EscuelaUtils.inicializarDatos();
    }
    public List<Estudiante> obtenerEstudiantes() {
        return escuela.getListaEstudiantes();
    }
    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarEstudiantes(escuela.getListaEstudiantes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void cargarDatosDesdeArchivos() {
        escuela = new Escuela();
        try {
            Persistencia.cargarDatosArchivos(escuela);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean crearEstuadiante(Estudiante estudiante) {
        try {
            if (!escuela.verificarEstudianteExistente(estudiante.getCodigo(), true)) {
                escuela.getListaEstudiantes().add(estudiante);
                registrarAccionesSistema("Estudiante agregado: "+ estudiante.getNombre(),1,"agregarEstudiante");
                salvarDatosPrueba();
            }
            return true;
        }catch (EstudianteException e){
            mostrarMensaje("Notificación estudainte", "Error al crear el estudiante",  e.getMessage(), Alert.AlertType.ERROR);
            registrarAccionesSistema(e.getMessage(),3,"agregarEstudiante");
            return false;
        }
    }

    @Override
    public boolean actualizarEstuadiante(Estudiante estudiante) {
        return false;
    }

    public void imprimirEstudiante(Estudiante estudiante) {
        try {
            Persistencia.imprimirEstudiante(estudiante);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean eliminarEstudiante(Estudiante estudiante) {
        return false;
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
