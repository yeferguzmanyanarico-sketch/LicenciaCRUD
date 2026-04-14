module pe.edu.upeu {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens pe.edu.upeu.controller to javafx.fxml;

    exports pe.edu.upeu;
}