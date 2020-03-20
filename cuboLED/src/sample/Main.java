package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import grammar.parser;
import parser.Grafo;
import grammar.sintax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("InterpreTEC");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        File  file = new File("D:\\proyects\\cuboLED\\src\\grammar\\eje.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

//        String st;
//        String arc = "";
//        while ((st = br.readLine()) != null){
//            arc += st;
//            arc += "\n";
//        }
//        parser parseo = new parser(new java.io.StringReader(arc));
//        parseo.init();
//        parseo.next_token();
//        sintax analisisSintx = new sintax(parseo.getGrafo(), parseo.getIdVars(), parseo.getIdsFunc(), parseo.getTypes());
//        System.out.println(analisisSintx.iniciar());
//        analisisSintx.imprimir();
        launch(args);
    }
}
