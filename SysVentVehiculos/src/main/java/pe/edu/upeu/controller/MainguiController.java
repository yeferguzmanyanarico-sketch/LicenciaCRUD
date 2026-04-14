package pe.edu.upeu.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Map;


public class MainguiController {
    @FXML BorderPane bp;
    @FXML
    MenuBar menuBar;
    @FXML
    MenuItem menuItem1,menuItem2, menuItem3;
    @FXML
    TabPane tabPane;

    @FXML
    public void initialize(){
        MenuItemListener mit = new MenuItemListener();
        menuItem1.setOnAction(mit::handle);
        menuItem2.setOnAction(mit::handle);
        menuItem3.setOnAction(mit::handle);


    }

    class MenuItemListener{
        Map<String, String[]> menuConfig = Map.of(
                "menuItem1", new String[]{"/view/main_cliente.fxml", "Reg. Cliente", "T"},
                "menuItem2", new String[]{"/view/main_cliente.fxml", "Reg. venta", "T"},
                "menuItem3", new String[]{"/view/login.fxml", "Salir", "C"}
        );

        public void handle(ActionEvent e){
            String id=((MenuItem)e.getSource()).getId();
            if(menuConfig.containsKey(id)){
                String[] items=menuConfig.get(id);
                if(items[2].equals("C")){
                    Platform.exit();
                    System.exit(0);
                }else{
                    abrirTabPaneFXML(items[0],items[1]);
                }
            }

        }
        private void abrirTabPaneFXML(String fxmlPath, String tittle){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = fxmlLoader.load();
                ScrollPane scrollPane = new ScrollPane(root);
                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(true);
                Tab newTab = new Tab(tittle, scrollPane);
                tabPane.getTabs().clear();
                tabPane.getTabs().add(newTab);
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }
    }

}
