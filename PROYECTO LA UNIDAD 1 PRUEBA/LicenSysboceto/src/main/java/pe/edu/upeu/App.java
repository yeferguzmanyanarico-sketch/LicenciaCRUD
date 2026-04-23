package pe.edu.upeu;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    /** Punto de entrada del programa */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // ── Cargar la vista desde el archivo FXML ─────────────────────
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/licencia_view.fxml")
        );

        // ── Crear la escena con el tamaño inicial ─────────────────────
        Scene escena = new Scene(loader.load(), 1050, 680);

        // ── Configurar la ventana principal ───────────────────────────
        stage.setScene(escena);
        stage.setTitle("🪪 Gestión de Licencias de Conducir");
        stage.setMinWidth(800);
        stage.setMinHeight(560);
        stage.centerOnScreen();
        stage.show();
    }
}