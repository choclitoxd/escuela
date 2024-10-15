module co.edu.uniquindio.preparcil2.preparcial2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens co.edu.uniquindio.preparcil2.preparcial2 to javafx.fxml;
    exports co.edu.uniquindio.preparcil2.preparcial2;

    exports co.edu.uniquindio.preparcil2.preparcial2.controller;
    opens co.edu.uniquindio.preparcil2.preparcial2.controller to javafx.fxml;

    opens co.edu.uniquindio.preparcil2.preparcial2.model to javafx.fxml;
    exports co.edu.uniquindio.preparcil2.preparcial2.model;

    opens co.edu.uniquindio.preparcil2.preparcial2.viewController to javafx.fxml;
    exports co.edu.uniquindio.preparcil2.preparcial2.viewController;
}