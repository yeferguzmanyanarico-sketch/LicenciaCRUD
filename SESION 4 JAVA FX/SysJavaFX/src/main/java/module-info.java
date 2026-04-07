module pe.edu.upeu {
// ═══ Módulos JavaFX requeridos ═══
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
// ═══ Abrir paquetes a JavaFX para reflexión (FXML + controllers) ═══
    opens pe.edu.upeu to javafx.fxml;
    opens pe.edu.upeu.controller to javafx.fxml;
// ═══ Exportar paquete principal ═══
    exports pe.edu.upeu;
}