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
    private static int lineStart = 408;
    private static int scope = 0;
    private static PrintWriter pw;
    private static FileWriter fichero;
    private static String templateRest;
    private static String templateFuncs;
    private static String mainHeader;
    private static ArrayList<String> constantes;

    private static void rewriteFile() {
        File file = new File(templateFile);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            templateFuncs = "";
            templateRest = "";
            mainHeader = "";
            int line = 0;
            while ((st = br.readLine()) != null) {
                templateFuncs += st;
                templateFuncs += '\n';
                line++;
                if (line >= lineStart){
                    break;
                }
            }
            while ((st = br.readLine()) != null) {
                templateRest += st;
                templateRest += '\n';
            }
        } catch (IOException e){
            System.out.println("Error al leer template");
        }
    }

    private static void write(String in){
        for(int i = 0; i != scope; i++){
            pw.print("\t");
        }
        pw.print(in);
    }

    private static void writeFOR(){
        write("for ");
        aux = aux.getNext();
        pw.print(aux.getContenido());
        aux = aux.getNext();
        pw.print(" in ");
        aux = aux.getNext();
        pw.print("range(0,");
        if (aux.getTipo().equals("NUM")){
            pw.print(aux.getContenido());
        } else {
            pw.print("getLen(");
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

    private static void listFunc(){
        pw.print("=");
        if (aux.getContenido().equals("Neg")){
            pw.print("neg_func(");
        }
        if (aux.getContenido().equals("T")){
            pw.print("hacer_true(");
        }
        if (aux.getContenido().equals("F")){
            pw.print("hacer_false(");
        }
        if (aux.getPrev().getTipo().equals("INDEXACC")){
            pw.print(aux.getPrev().getPrev().getContenido());
        }
        pw.print(aux.getPrev().getContenido());
        pw.print(")");
        aux = aux.getNext();
    }

    private static void writeDel(){
        write("delete(");
        pw.print(aux.getContenido());
        aux = aux.getNext();
        aux = aux.getNext();
        pw.print(",");
        pw.print(aux.getContenido());
        pw.print(",");
        aux = aux.getNext();
        if (!aux.getTipo().equals("ENDLINE")){
            pw.print(aux.getContenido());
            aux = aux.getNext();
        } else {
            pw.print("0");
        }
        pw.print(")");
    }

    private static void writeNewList(){
        pw.print("[]");
        pw.println("");
        write("crear_lista(");
        pw.print(aux.getPrev().getPrev().getContenido());
        pw.print(",");
        aux = aux.getNext();
        pw.print(aux.getContenido());
        pw.print(",");
        aux = aux.getNext();
        if (aux.getContenido().contains("true") || aux.getContenido().contains("false")){
            aux.setContenido(aux.getContenido().replace("true","True"));
            aux.setContenido(aux.getContenido().replace("false","False"));
        }
        pw.print(aux.getContenido());
        pw.print(")");
        aux = aux.getNext();
    }

    public static void writeInsert(){
        write(aux.getNext().getContenido());
        pw.write(aux.getContenido());
        aux=aux.getNext();
        aux=aux.getNext();
        pw.write(",");
        pw.write(aux.getContenido());
        aux=aux.getNext();
        pw.write(",");
        if (aux.getContenido().contains("true") || aux.getContenido().contains("false")){
            aux.setContenido(aux.getContenido().replace("true","True"));
            aux.setContenido(aux.getContenido().replace("false","False"));
        }
        pw.write(aux.getContenido());
        pw.write(")");
        aux=aux.getNext();
    }

    public static void writeInsertMat(){
        write(aux.getNext().getContenido());
        pw.write(aux.getContenido());
        aux=aux.getNext();
        aux=aux.getNext();
        pw.write(",");
        if (aux.getContenido().contains("true") || aux.getContenido().contains("false")){
            aux.setContenido(aux.getContenido().replace("true","True"));
            aux.setContenido(aux.getContenido().replace("false","False"));
        }
        pw.write(aux.getContenido());
        aux=aux.getNext();
        pw.write(",");
        pw.write(aux.getContenido());
        pw.write(",");
        aux =aux.getNext();
        if (aux.getTipo().equals("ENDINSERT")) {
            aux.setContenido("-1");
            pw.write(aux.getContenido());
        } else {
            pw.write(aux.getContenido());
            aux=aux.getNext();
        }
        pw.write(")");
        aux=aux.getNext();
    }

    private static void writeDelay(){
        aux=aux.getNext();
        if (aux.getTipo().equals("ENDLINE")){
            write("delayDefault()");
            return;
        }else{
            write(aux.getPrev().getContenido());
            pw.print("(");
        }
        pw.print(aux.getContenido());
        pw.print(",");
        aux=aux.getNext();
        if (aux.getContenido().equals("Seg")){
            pw.print(1);
        } else if (aux.getContenido().equals("Mil")){
            pw.print(2);
        } else {
            pw.print(3);
        }
        pw.print(")");
        aux=aux.getNext();
    }

    private static void writeConstantes(){
        pw.print("timer = " + constantes.get(0));
        pw.println("");
        pw.print("rango_timer = ");
        if (constantes.get(1).equals("Seg")){
            pw.print(1);
        } else if (constantes.get(1).equals("Mil")){
            pw.print(2);
        } else {
            pw.print(3);
        }
        pw.println("");
        pw.print(constantes.get(4)+"=matriz_cubo");
        pw.println("");
    }

    private static void writeShape(){
        pw.print(aux.getContenido());
        pw.print("(");
        pw.print(aux.getPrev().getPrev().getContenido());
        pw.print(")");
        aux=aux.getNext();
    }

    private static void getIndex(String contenido){
        String[] indexAux;
        contenido = contenido.replace("]", "");
        indexAux = contenido.split("\\[");
        for (String in:indexAux){
            System.out.println(in);
        }
        if (indexAux.length==1){
            pw.print("False,False,False");
        } else if (indexAux.length==2){
            pw.print("False,False,"+indexAux[1]);
        } else if (indexAux.length==3){
            pw.print("False," + indexAux[1] + "," + indexAux[2]);
        } else if (indexAux.length==4){
            pw.print(indexAux[1] + "," + indexAux[2] + "," + indexAux[3]);
        }
        pw.print(",");
        pw.print("Indices_" + indexAux[0]);
    }

    private static void writeBlink(){
        write("blink_fun(");
        aux=aux.getNext();
        getIndex(aux.getContenido());
        pw.print(",");
        aux=aux.getNext();
        if (aux.getTipo().equals("NUM")){
            pw.print(aux.getContenido());
            pw.print(",");
            aux=aux.getNext();
            if (aux.getContenido().equals("Seg")){
                pw.print(1);
            } else if (constantes.get(1).equals("Mil")){
                pw.print(2);
            } else {
                pw.print(3);
            }
            aux=aux.getNext();
            pw.print(",");

        } else {
            pw.print("-1");
            pw.print(",");
            pw.print("-1");
            pw.print(",");
        }
        aux.setContenido(aux.getContenido().replace("true","True"));
        aux.setContenido(aux.getContenido().replace("false","False"));
        pw.print(aux.getContenido());
        aux=aux.getNext();
        pw.print(")");
    }

    private static String convertIndex(String contenido){
        String[] indexAux;
        contenido = contenido.replace("]", "");
        indexAux = contenido.split("\\[");
        String aux = "[";
        if (indexAux.length==1){
            return indexAux[0];
        }
        for (int i = 1; i < indexAux.length; i++){
            aux+=indexAux[i];
            if (i < indexAux.length-1){
                aux+=",";
            }
        }
        return aux+"]";
    }

    public static boolean initWriter(Grafo in,ArrayList<String> constantesIn) throws IOException {
        constantes = constantesIn;
        rewriteFile();
        File f = new File(filePy);
        if(f. exists()) {
            f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fichero = new FileWriter(filePy);
        grafo = in;
        aux = grafo.getInicial();
        boolean flagNL = true;
        boolean inMain = false;
        try {
            pw = new PrintWriter(fichero);
            pw.print(templateFuncs);
            try{
                writeConstantes();
            } catch (IndexOutOfBoundsException e){
                return false;
            }
            pw.println("");
            while (aux != null) {
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
                if (aux.getTipo().equals("NEWLIST")){
                    writeNewList();
                }
                if (aux.getTipo().equals("FUNCLIST")){
                    listFunc();
                }
                if (aux.getTipo().equals("CALL")){
                    aux = aux.getNext();
                }
                if (aux.getTipo().equals("DELAY")){
                    writeDelay();
                }
                if (aux.getTipo().equals("OPENSCOPE")){
                    scope++;
                    aux.setContenido(":\n");
                    flagNL=true;
                }
                if (aux.getTipo().equals("BLINK")){
                    writeBlink();
                }
                if (aux.getTipo().equals("CONVERT")){
                    aux.setContenido(convertIndex(aux.getContenido()));
                }
                if (aux.getNext() != null){
                    if (aux.getNext().getTipo().equals("FUN.DEL")){
                        writeDel();
                    }else if (aux.getNext().getTipo().equals("FUN.INSERTLIST")){
                        writeInsert();
                    } else if (aux.getNext().getTipo().equals("FUN.INSERTMAT")){
                        writeInsertMat();
                    } else if (aux.getNext().getNext() != null){
                        if (aux.getNext().getNext().getTipo().equals("SHAPE")){
                            aux = aux.getNext().getNext();
                            writeShape();
                        }
                    }
                }
                if (aux.getTipo().equals("CLOSESCOPE") || aux.getTipo().equals("ENDLINE")){
                    if (aux.getTipo().equals("CLOSESCOPE")) {
                        scope--;
                    }
                    flagNL=true;
                    pw.println("");
                } else {
                    if (aux.getContenido().contains("true") || aux.getContenido().contains("false")){
                        aux.setContenido(aux.getContenido().replace("true","True"));
                        aux.setContenido(aux.getContenido().replace("false","False"));
                    }
                    if (aux.getTipo().equals("ID") && aux.getNext().getTipo().equals("INDEX")){
                        aux = aux.getNext();
                    }
                    if (flagNL && !aux.getTipo().equals("OPENSCOPE")) {
                        write("");
                        pw.print(aux.getContenido());
                        flagNL=false;
                    } else{
                        if (aux.getContenido().contains("<>")) aux.setContenido(aux.getContenido().replace("<>","!="));
                        pw.print(aux.getContenido());
                    }

                }
            }
            pw.println(mainHeader);
            pw.print(templateRest);
            fichero.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}