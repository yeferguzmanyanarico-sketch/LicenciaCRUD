package pe.edu.upeu;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pe.edu.upeu.util.ConexionDB;

public class App extends Application {

    public static void main( String[] args ) {
        launch(args);
    }
    @Override
    public void start (Stage stage) throws Exception{

        // ✅ NUEVO: Inicializar la base de datos al arrancar
        // Si licencias.db no existe, lo crea automáticamente
        // Si la tabla no existe, la crea automáticamente
        ConexionDB.getInstance();
        System.out.println("🚀 Aplicación iniciada con SQLite");

        //CARGA LA VISTA FXML

        FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/view/licencia_view.fxml"));

        // crear escena con el tamaño inicial
        Scene escena= new Scene(loader.load(), 1050, 680);

        // ── Configurar la ventana principal ───────────────────────────
        stage.setScene(escena);
        stage.setTitle("🪪 GESTIÓN DE LICENCIAS DE CONDUCIR");
        stage.setMinWidth(800);
        stage.setMinHeight(560);
        stage.centerOnScreen();

        // NUEVO: CERRAR LA CONEXION CUANDO EL USUARIO CIERRE LA VENTANA
        stage.setOnCloseRequest(event -> {
            ConexionDB.getInstacia().cerrar();
            System.out.println("aplicacion cerrada. DB desconectada");
        });
        stage.show();

    }
}