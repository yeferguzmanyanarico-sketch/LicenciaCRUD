package pe.edu.upeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pe.edu.upeu.enums.TipoVehiculo;
import pe.edu.upeu.model.Cliente;

import java.util.ArrayList;
import java.util.List;


public class App extends Application {
    public static void main( String[] args ) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/maingui.fxml"));
        Scene scene = new Scene(loader.load(), 400,600);
        stage.setScene(scene);
        stage.show();


















    }
}















//*System.out.println(TipoVehiculo.SEDAN.toString());
//
//        for (TipoVehiculo tv : TipoVehiculo.values()){
//            System.out.println(tv.toString());
//
//        }
//
//        List<Cliente> clientes = new ArrayList<>();
//        clientes.add(new Cliente("02", "Javier","987456123","gsfdfa@gmail"));
//        clientes.add(new Cliente("03", "jos","445554658","gsfdfa@gmail"));
//        clientes.add(new Cliente("04", "cbscja","924546325","gsfdfa@gmail"));
//        clientes.add(new Cliente("05", "Carlos","936325544","gsfdfa@gmail"));
//
//        for (Cliente c:clientes){
//            System.out.println(c.toString());
//        }
//
//
//        Cliente p = new Cliente("01", "YEFER YANARICO");
//
//        System.out.println(p.toString());*//