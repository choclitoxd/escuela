package co.edu.uniquindio.preparcil2.preparcial2.viewController;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.preparcil2.preparcial2.controller.EstudianteController;
import co.edu.uniquindio.preparcil2.preparcial2.model.Estudiante;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EstudianteViewController {
    EstudianteController estudianteController;
    ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    Estudiante estudianteSeleccionado;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Estudiante, String> tbColCodigo;

    @FXML
    private TableColumn<Estudiante, String> tbColNombre;

    @FXML
    private TableColumn<Estudiante, String> tbColNota1;

    @FXML
    private TableColumn<Estudiante, String> tbColNota2;

    @FXML
    private TableColumn<Estudiante, String> tbColNota3;

    @FXML
    private TableView<Estudiante> tbEstuadiante;

    @FXML
    private TextField txtBuscarEstuadiante;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNota1;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota3;
    @FXML
    void initialize() {
        estudianteController = new EstudianteController();
        initView();
    }
    private void initView() {
        initDataBinding();
        obtenerEstudiante();
        tbEstuadiante.getItems().clear();
        tbEstuadiante.getItems().addAll(listaEstudiantes);
        listenerSelection();
        initSearch();
    }
    private void listenerSelection() {
        tbEstuadiante.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            estudianteSeleccionado = newValue;
            mostrarInformacionUsuario(estudianteSeleccionado);
        });
    }

    private void mostrarInformacionUsuario(Estudiante estudianteSeleccionado) {

        if (estudianteSeleccionado != null) {
            txtNombre.setText(estudianteSeleccionado.getNombre());
            txtNota1.setText(String.valueOf(estudianteSeleccionado.getListaNotas().get(0)));
            txtNota2.setText(String.valueOf(estudianteSeleccionado.getListaNotas().get(1)));
            txtNota3.setText(String.valueOf(estudianteSeleccionado.getListaNotas().get(2)));
        }
    }

    private void obtenerEstudiante() {
        listaEstudiantes.addAll(estudianteController.obtenerEstudiantes());
    }

    private void initDataBinding() {
        tbColCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbColNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbColNota1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaNotas().get(0)));
        tbColNota2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaNotas().get(1)));
        tbColNota3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaNotas().get(2)));
    }

    @FXML
    void onImprimir(ActionEvent event) {
       if(estudianteSeleccionado != null){
           estudianteController.imprimirDatosEstudiante(estudianteSeleccionado);
           mostrarMensaje("Notificación estudiante", "Estudiante guardado", "El Estudiante se ah guardado con exito", Alert.AlertType.INFORMATION);
       }else{
           mostrarMensaje("Notificación estudiante", "Estudiante no se ah guardado", "El Estudiante no se ah guardado con exito", Alert.AlertType.INFORMATION);
       }
    }
    @FXML
    void onGuardar(ActionEvent event) {
        crearUsuario();
    }

    private void crearUsuario() {
        if (validarCampos()) {
            Estudiante estudiante = contruirEstudiante();
            if (estudianteController.crearUsuario(estudiante)) {
                mostrarMensaje("Notificación estudiante", "Estudiante creado", "El Estudiante se ha creado con exito", Alert.AlertType.INFORMATION);
                listaEstudiantes.add(estudiante);
                tbEstuadiante.getItems().clear();
                tbEstuadiante.getItems().addAll(listaEstudiantes);
                tbEstuadiante.getSortOrder().add(tbColCodigo);
                limpiarCampos();
            }
        }else {
            mostrarMensaje("Notificación Usuario", "Error al crear el usuario", "Por favor llene todos los campos", Alert.AlertType.ERROR);
        }
    }

    private Estudiante contruirEstudiante() {
        Estudiante estudiante = new Estudiante(txtNombre.getText());
        estudiante.getListaNotas().add(String.valueOf(Double.valueOf(txtNota1.getText())));
        estudiante.getListaNotas().add(String.valueOf(Double.valueOf(txtNota2.getText())));
        estudiante.getListaNotas().add(String.valueOf(Double.valueOf(txtNota3.getText())));
        return estudiante;
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtNota1.getText().isEmpty() ||
                txtNota2.getText().isEmpty() || txtNota3.getText().isEmpty()) {
            return false;
        } else{
            return true;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
    }
    private void initSearch(){

        FilteredList<Estudiante> filteredData = new FilteredList<>(listaEstudiantes, b->true);
        txtBuscarEstuadiante.textProperty().addListener((ObservableList,oldValue,newValue)->{
            filteredData.setPredicate(estudianteSeleccionado ->{
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String loweCaseFilter = newValue.toLowerCase();
                if (estudianteSeleccionado.getCodigo().toLowerCase().contains(loweCaseFilter)){
                    return true;
                }
                return estudianteSeleccionado.getNombre().toLowerCase().contains(loweCaseFilter);
            });
        });
        SortedList<Estudiante> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbEstuadiante.comparatorProperty());
        tbEstuadiante.setItems(sortedData);
    }
}
