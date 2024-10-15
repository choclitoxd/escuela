package co.edu.uniquindio.preparcil2.preparcial2.model;

import co.edu.uniquindio.preparcil2.preparcial2.exception.EstudianteException;
import co.edu.uniquindio.preparcil2.preparcial2.model.service.IEscuelaService;

import java.util.ArrayList;
import java.util.List;

public class Escuela implements IEscuelaService {
    private List<Estudiante> listaEstudiantes = new ArrayList();

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    @Override
    public boolean verificarEstudianteExistente(String codigoEstuadiante, boolean crear) throws EstudianteException {
        if(estudianteExiste(codigoEstuadiante) && crear){
            throw new EstudianteException("El estudiante con el codigo: " + codigoEstuadiante + " ya existe");
        }else if(!estudianteExiste(codigoEstuadiante) && !crear){
            throw new EstudianteException("El estudiante con el id: " + codigoEstuadiante + " no existe");
        }else{
            return false;
        }
    }

    @Override
    public boolean verificarEstudianteExistenteEliminar(String codigoEstudiante) throws EstudianteException {
        if(!estudianteExiste(codigoEstudiante)){
            throw new EstudianteException("El estudiante con el id: " + codigoEstudiante + " no existe");
        }else{
            return false;
        }
    }

    private boolean estudianteExiste(String codigo) {
        for(Estudiante estudiante : getListaEstudiantes()){
            if (estudiante.getCodigo().equalsIgnoreCase(codigo)){
                return true;
            }
        }
        return false;
    }
}
