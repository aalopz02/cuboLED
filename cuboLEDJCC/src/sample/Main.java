package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sintaxAnalysis.SyntaxChecker;

import java.io.*;

import static javafx.scene.text.TextAlignment.CENTER;

public class Main extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;
        window.setTitle("TECube8");

        Button startButton = new Button("Start");
        startButton.setPrefHeight(40);
        startButton.setPrefWidth(60);
        Label label1 = new Label("TECube8");
        startButton.setOnAction(e->window.setScene(scene2));
        VBox layout1= new VBox(20);
        layout1.getChildren().addAll(label1, startButton);

        StackPane root=new StackPane();
        root.setId("pane");
        root.getChildren().addAll(startButton);
        scene1 = new Scene(root, 800, 700);
        scene1.getStylesheets().addAll(this.getClass().getResource("Window1.css").toExternalForm());


        HBox topMenu = new HBox(0);
        Button newButton = new Button("New");
        Button pushButton = new Button("Push");
        Button exitButton = new Button("Exit");

        Region space = new Region();
        space.setPrefHeight(25.0);
        space.setPrefWidth(380.0);

        topMenu.getChildren().addAll(newButton,pushButton,space,exitButton);
        topMenu.setMargin(newButton,new Insets(10, 10, 10, 10));
        topMenu.setMargin(pushButton,new Insets(10, 10, 10, 10));
        topMenu.setMargin(space,new Insets(10, 10, 10, 10));
        topMenu.setMargin(exitButton,new Insets(10, 10, 10, 10));

        TextArea userText= new TextArea();
        userText.setPrefHeight(200.0);
        userText.setPrefWidth(200.0);
        userText.setPromptText("Wating for instructions...");

        Label answer = new Label();
        answer.setPrefHeight(100.0);
        answer.setPrefWidth(200.0);
        answer.setText("Information Panel");
        answer.setTextAlignment(CENTER);


        newButton.setOnAction(e->userText.setPromptText("Waitin for instructions..."));
        newButton.setOnAction(e->userText.setText(""));
        //el userText.getText() es para jalar el String que
        // le entra por el Text Area, entonces acá en lugar de un system se debe enviar ese string a donde lo ocupes
        pushButton.setOnAction(e-> {
            String errLog = SyntaxChecker.initAnalisys(userText.getText());
            answer.setText("");
            answer.setText(errLog);
        });
        //Pasarselo a donde lo necesite Andres
        //cuando verifica la sintaxis lo envia al label
        //pushButton.setOnAction(answer.setText(responseOfSyntax)); ese response of syntax es la respues que da el interprete,
        //digamos si da error o si dice que es valido se debe enviar por el set text
        exitButton.setOnAction(e->window.close());

        BorderPane layout2 =new BorderPane();
        layout2.setTop(topMenu);
        layout2.setCenter(userText);
        layout2.setBottom(answer);
        scene2= new Scene(layout2,635,500);
        scene2.getStylesheets().add("sample/Gui.css");

        window.setScene(scene1);
        window.show();
    }


}