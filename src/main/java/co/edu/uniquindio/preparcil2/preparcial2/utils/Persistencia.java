package co.edu.uniquindio.preparcil2.preparcial2.utils;

import co.edu.uniquindio.preparcil2.preparcial2.model.Escuela;
import co.edu.uniquindio.preparcil2.preparcial2.model.Estudiante;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public static final String RUTA_ARCHIVO_MODELO_BILLETERA_BINARIO = "persistencia/model.dat";
    public static final String RUTA_ARCHIVO_ESTUADIANTES = "persistencia/archivoEstudiantes.txt";
    public static final String RUTA_ARCHIVO_LOG = "persistencia/log/BilleteraLog.txt";
    public static final String RUTA_ARCHIVO_MODELO_BILLETERA_XML = "persistencia/model.xml";

    public static void cargarDatosArchivos(Escuela escuela) throws FileNotFoundException, IOException {
        //cargar archivo de Usuarios
        List<Estudiante> estudiantesCargados = cargarEstudiantes();
        if(estudiantesCargados.size() > 0)
            escuela.getListaEstudiantes().addAll(estudiantesCargados);

        //cargar archivos empleados

        //cargar archivo transcciones

        //cargar archivo empleados

        //cargar archivo prestamo
    }
    public static void guardarEstudiantes(List<Estudiante> listaEstudiantes) throws IOException {
        String contenido = "";
        for(Estudiante estudiante:listaEstudiantes) {
            contenido+= estudiante.getCodigo()+
                    ";"+estudiante.getNombre();
            for (String nota:estudiante.getListaNotas()){
                contenido+=";"+nota;
            }
            contenido+="\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUADIANTES, contenido, false);
    }
    public static List<Estudiante> cargarEstudiantes() throws FileNotFoundException, IOException
    {
        List<Estudiante> estudiantes =new ArrayList<>();
        List<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ESTUADIANTES);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);//1;juan;uni1@;12454;Armenia;125444
            Estudiante estudiante = new Estudiante();
            estudiante.setCodigo(linea.split(";")[0]);
            estudiante.setNombre(linea.split(";")[1]);
            for (int j = 0; j < linea.split(";").length; j++) {
                estudiante.getListaNotas().add(linea.split(";")[j]);
            }
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }
    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    public static void imprimirEstudiante(Estudiante estudiante) throws IOException{
        String RUTA_ARCHIVO_ESTUADIANTES = "persistencia/archivo"+ estudiante.getNombre() +".txt";
        String contenido = "";
        contenido+= estudiante.getCodigo()+
                ";"+estudiante.getNombre();
        for (String nota:estudiante.getListaNotas()){
            contenido+=";"+nota;
        }
        contenido+="\n";
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUADIANTES, contenido, false);
    }
}
