package sample;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.*;
import javafx.scene.control.Label;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import grammar.parser;
import parser.ToFile;
import parser.Interprete;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import parser.Grafo;
import server.Server;
import server.ServerThread;
import temp.ParseoFuncion;

public class Controller implements Initializable {

    @FXML
    private Label fileName;
    @FXML
    private JFXTextArea Error_TextArea;
    @FXML
    private JFXTextArea TextComp;

    @FXML
    void pressNuevo(MouseEvent event) throws  IOException {
        Error_TextArea.setText("");
        TextComp.setText("");
        fileName.setText("new");
    }

    @FXML
    void pressCargar(MouseEvent event) throws IOException {
        String aux, text = "";
        JFileChooser JFC = new JFileChooser();
        JFC.showOpenDialog(null);
        File archivo = JFC.getSelectedFile();
        if (archivo != null){
            FileReader files = new FileReader(archivo);
            BufferedReader reader = new BufferedReader(files);
            while ((aux = reader.readLine()) != null){
                text += aux + '\n';
            }
            reader.close();
            TextComp.setText(text);
            fileName.setText(archivo.getName());
        }else{
            System.out.println("Archivo invalido");
        }

    }

    @FXML
    void pressGuardar(MouseEvent event) throws IOException{
        String aux, text = "";
        JFileChooser JFC = new JFileChooser();
        JFC.showSaveDialog(null);
        File archivo = JFC.getSelectedFile();
        if (archivo != null){
            FileWriter FW = new FileWriter(archivo + ".txt");
            BufferedWriter BW = new BufferedWriter(FW);
            String s = TextComp.getText();
            BW.write(s);
            BW.close();
            FW.close();
            JOptionPane.showMessageDialog(null,
                    "El archivo se ha guardado",
                    "Información",JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private String printLines(InputStream ins) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        return in.readLine();
    }

    private ArrayList<String> runProcess(String command) throws IOException {
        ArrayList<String> listaOut = new ArrayList<>();
        Process pro = Runtime.getRuntime().exec(command);
        listaOut.add(printLines(pro.getErrorStream()));
        listaOut.add(printLines(pro.getInputStream()));
        return listaOut;
    }

    private void getMoves(String in) throws IOException{
        ParseoFuncion send = new ParseoFuncion();
        String[] parts = in.split(";");
        String part;
        for (int i = 0; i < parts.length ; i++) {
            part = parts[i];
            switch (part) {
                case "AF":
                    send.Move("AF");
                    break;
                case "F":
                    send.Move("F");
                    break;
                case "DFA":
                    send.Move("DFA");
                    break;
                case "IFA":
                    send.Move("IFA");
                    break;
                case "DFB":
                    send.Move("DFB");
                    break;
                case "IFB":
                    send.Move("IFB");
                    break;
                case "A":
                    send.Move("A");
                    break;
                case "DAA":
                    send.Move("DAA");
                    break;
                case "IAA":
                    send.Move("IAA");
                    break;
                case "DAB":
                    send.Move("DAB");
                    break;
                case "IAB":
                    send.Move("IAB");
                    break;
                case "AA":
                    send.Move("AA");
                    break;
                case "R":
                    send.Rotar();
                    break;
            }
        }
        System.out.println(send.comandos);
        //send.Compilado();
    }

    @FXML
    void pressCompilar (MouseEvent e) throws IOException{
        String codigo = TextComp.getText();
        if (codigo.isEmpty() || codigo.equals("Inicio:")){
            return;
        }
        parser parseo = new parser(new java.io.StringReader(codigo));
        parseo.init();
        //parseo.next_token();
        //Error_TextArea.setText(parseo.error);
//        String param = "";
//        if (parseo.error.equals("parseo listo")){
//            param = parseo.checkParam();
//            Error_TextArea.appendText("\n");
//            Error_TextArea.appendText(param);
//        }
//        if (parseo.error.equals("parseo listo") && param.equals("parametros bien")){
//            Grafo x = parseo.list;
//            if (x != null){
//                Interprete interprete = new Interprete(x);
//                boolean flag = interprete.start();
//                Error_TextArea.appendText("\n");
//                ArrayList<String> listaOut;
//                if (flag){
//                    Error_TextArea.appendText("compilacion terminada\n");
//                    ToFile writer = new ToFile("D:\\proyects\\cuboLED\\src\\parser\\javaAddOn.txt");
//                    String dir = System.getProperty("user.dir");
//                    runProcess("javac D:\\proyects\\cuboLED\\src\\temp\\temp.java");
////                    listaOut = runProcess("java -cp " + dir + "\\src temp.temp");
//                    if (listaOut.get(0) != null){
//                        Error_TextArea.appendText(listaOut.get(0));
//                        System.out.println(listaOut.get(0));
//                    } else {
//                        System.out.println(listaOut);
//                        if (!listaOut.get(1).isEmpty()){
//                            getMoves(listaOut.get(1));
//                        }
//                        File file = new File("D:\\proyects\\cuboLED\\src\\temp\\temp.java");
//                        FileOutputStream clear = new FileOutputStream(file, false);
//                        clear.write('\n');
//                    }
//                } else {
//                    Error_TextArea.appendText("error en compilacion de programa");
//                }
//            }
//        }
    }
    public void initialize (URL url, ResourceBundle rb){
//        ServerThread.initServer();
    }
}
