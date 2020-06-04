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
    private static String templateCOMS;
    private static String templateFuncs;
    public static String Tabs = "";


    private static void rewriteFile() {
        File file = new File(templateFile);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            templateCOMS = "";
            int line = 0;
            while ((st = br.readLine()) != null) {
                templateCOMS += st;
                templateCOMS += '\n';
                line++;
                if (line >= 22){
                    break;
                }
            }
            templateFuncs = "";
            while ((st = br.readLine()) != null) {
                templateFuncs += st;
                templateFuncs += '\n';
            }
        } catch (IOException e){
            System.out.println("Error al leer template");
        }

    }

    private static void writeFOR(){
        pw.print("for ");
        aux = aux.getNext();
        pw.print(aux.getContenido());
        aux = aux.getNext();
        pw.print(" in ");
        aux = aux.getNext();
        pw.print(" range(0,");
        if (aux.getTipo().equals("NUM")){
            pw.print(aux.getContenido());
        } else {
            pw.print("len(");
            pw.print(aux.getContenido());
            pw.print(")");
        }
        aux = aux.getNext();
        pw.print(",");
        aux = aux.getNext();
        pw.print(aux.getContenido());
        pw.print(")");
        aux = aux.getNext();
    }

    private static void writeIF(){

    }

    public static boolean initWriter(Grafo in) throws IOException {
        rewriteFile();
        fichero = new FileWriter(filePy);
        grafo = in;
        aux = grafo.getInicial();
        try {
            pw = new PrintWriter(fichero);
            pw.print(templateFuncs);
            pw.println("");
            while (aux != null) {
                for(int i = 0; i != scope; i++){
                    Tabs += "\t";
                }
                aux = aux.getNext();
                if (aux == null){
                    break;
                }
                System.out.println("TIPO: " + aux.getTipo());
                System.out.println("CONTENIDO: " + aux.getContenido());
                if (aux.getTipo().equals("INIT")){
                    aux = aux.getNext();
                }
                if (aux.getTipo().equals("FOR")){
                    writeFOR();
                }
                if (aux.getTipo().equals("OPENSCOPE")){
                    scope++;
                    aux.setContenido(":\n");
                }
                if (aux.getTipo().equals("CLOSESCOPE") || aux.getTipo().equals("ENDLINE")){
                    pw.println("");
                } else {
                    if (aux.getContenido().contains("true") || aux.getContenido().contains("false")){
                        aux.setContenido(aux.getContenido().replace("true","True"));
                        aux.setContenido(aux.getContenido().replace("false","False"));
                    }
                    if (aux.getTipo().equals("ID") && aux.getNext().getTipo().equals("INDEX")){
                        aux = aux.getNext();
                    }
                    pw.print(aux.getContenido());
                }
            }
            pw.println("");
            pw.print(templateCOMS);
            fichero.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}