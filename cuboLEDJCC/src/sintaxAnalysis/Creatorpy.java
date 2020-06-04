package sintaxAnalysis;

import Estructuras.Nodo;
import Estructuras.Grafo;

import java.io.*;
import java.util.ArrayList;


public class Creatorpy {

    private static Grafo grafo;
    private static String filePy = "src/temp/cubo_compi1.py";
    private static String templateFile = "src/temp/template.py";
    private static Nodo aux;
    private static int lineStart = 38;
    private static int scope = 0;
    private static PrintWriter pw;
    private static FileWriter fichero;
    private static String template;

    private static void writePROC() {
        while (aux.getTipo().equals("OPENSCOPE")) {
            pw.print(aux.getContenido());
            aux = aux.getNext();
        }
        pw.print(":");
        pw.println("");
        scope ++;
    }

    private static void rewriteFile() {
        File file = new File(templateFile);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            template = "";
            while ((st = br.readLine()) != null) {
                template += st;
                template += '\n';
            }
            System.out.println(template);
        } catch (IOException e){
            System.out.println("Error al leer template");
        }

    }

    public static boolean initWriter(Grafo in) throws IOException {
        rewriteFile();
        fichero = new FileWriter(filePy);
        grafo = in;
        aux = grafo.getInicial();
        try {
            pw = new PrintWriter(fichero);
            pw.print(template);
            pw.print("asdcvascscsd");
            while (aux != null) {
                if (aux.getTipo().equals("PRODCL")){
                    writePROC();
                }
                if (aux.getTipo().equals("ENDLINE")) {
                    pw.println(" ");
                    aux = aux.getNext();
                } else {
                    pw.print(aux.getContenido());
                    aux = aux.getNext();
                }
            }
            fichero.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}