package pe.edu.upeu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * Hello world!
 *
 */
public class App extends Application {
    public void start(Stage stage) throws Exception {
        // UI en código puro
        Label lblNum1 = new Label("Numero 1");
        Label lblNum2 = new Label("Numero 2");
        TextField txtNum1 = new TextField("0");
        TextField txtNum2 = new TextField("0");
        Button btnSuma = new Button("Suma");
        Button btnResta = new Button("Resta");
        Label lblResultado = new Label("Resultado:");
        Label txtResult = new Label("0");

        // Estilos aplicados en código (sin CSS externo)
        btnSuma.getStyleClass().setAll("btn","btn-primary");
        btnResta.getStyleClass().setAll("btn","btn-danger");

        // Acciones
        btnSuma.setOnAction(e -> {
            double a = Double.parseDouble(txtNum1.getText());
            double b = Double.parseDouble(txtNum2.getText());
            txtResult.setText(String.valueOf(a + b));
        });
        btnResta.setOnAction(e -> {
            double a = Double.parseDouble(txtNum1.getText());
            double b = Double.parseDouble(txtNum2.getText());
            txtResult.setText(String.valueOf(a - b));
        });

        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));
        grid.add(lblNum1, 0, 0);
        grid.add(lblNum2, 1, 0);
        grid.add(txtNum1, 0, 1);
        grid.add(txtNum2, 1, 1);
        grid.add(btnSuma, 0, 2);
        grid.add(btnResta, 1, 2);
        grid.add(lblResultado, 0, 3);
        grid.add(txtResult, 1, 3);

        Scene scene = new Scene(grid, 480, 680);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Calc Basic");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args ) {
        launch(args);
    }
}
