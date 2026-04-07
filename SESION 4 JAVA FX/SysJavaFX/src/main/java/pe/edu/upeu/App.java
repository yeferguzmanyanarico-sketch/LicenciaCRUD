package pe.edu.upeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * Hello world!
 *
 */
public class App extends Application {
    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vfxml/vfxml.fxml"));
        Scene scene = new Scene(loader.load(), 480, 680);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setTitle("CALCULADORA BASICA DE YEFER");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
}
