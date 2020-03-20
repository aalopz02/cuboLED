package parser;

import java.io.IOException;

public class Interprete {

    private Grafo grafo;
    private String path = "D:\\proyects\\cuboLED\\src\\parser\\javaAddOn.txt";
    private String write = "";
    private ToFile writer;
    private int expLine = 43;
    private int methdLine = 36;

    public Interprete(Grafo grafo){
        this.grafo = grafo;
        this.write = "";
    }

    private String condicional(String token){
        switch (token){
            case "<>":
                return "!=";
            case "=":
                return "==";
            case "<":
                return "<";
            case ">":
                return ">";
            case ">=":
                return ">=";
            case "<=":
                return "<=";

            default: return "";
        }
    }

    private void structWhile(){
        Grafo aux = grafo.getNext();
        int scope = grafo.getScope();
        String id;
//        while (true){
//            if (aux.getType().equals("idWhile"+String.valueOf(scope))) {
//                id = aux.getToken();
//                aux.setType("idWhile");
//                break;
//            }
//            aux = aux.getNext();
//        }
        write += grafo.getToken();
//        write += id;
        aux = aux.getNext();
        write += condicional(aux.getToken());
        aux = aux.getNext();
        write += aux.getToken();
        write += ")";
    }

    private void structFor(){
        Grafo aux = grafo.getNext();
        write += grafo.getToken();
        write += aux.getToken();
        write += "= 0;";
        String id = "";
        for (int i = 0; i < aux.getToken().length(); i++){
            if (aux.getToken().charAt(i) == '='){
                break;
            }
            id += aux.getToken().charAt(i);
        }
        aux = aux.getNext();
        write += id ;
        write += "<";
        write += aux.getNext().getToken();
        write += ";";
        write += id;
        write += "++)";
    }

    private boolean writeStart() throws IOException {
//        while(grafo.getType() != "div"){
//            if (grafo.getType() == null){
//                break;
//            }
//            if (grafo.getType().equals("while")){
//                structWhile();
//            } else if (grafo.getType().equals("for")) {
//                structFor();
//            } else if (grafo.getType().equals("cond")) {
//                write += condicional(grafo.getToken());
//            } else if (grafo.getType().equals("idWhile") || grafo.getType().equals("condWhile") || grafo.getType().equals("nWhile")
//            || grafo.getType().equals("idFor") || grafo.getType().equals("nFor") || grafo.getType().equals("")){
//                write = write;
//            } else {
//                write += grafo.getToken();
//            }
//            if (grafo.getNext() == null || grafo.getNext().getType().equals("methods")){
//                break;
//            } else {
//                grafo = grafo.getNext();
//            }
//        }
        return writer.add_line(expLine,write);
    }

    private boolean writeDCL() throws IOException {
        grafo = grafo.getNext();
        while (grafo.getScope() == 0){
            if (grafo.getToken().equals("public int ")){
                expLine ++;
                methdLine ++;
            }
            write += grafo.getToken();
            if (grafo.getNext() != null){
                grafo = grafo.getNext();
            } else {
                break;
            }
        }
        return writer.add_line(16,write);
    }

    private boolean writeFunc() throws IOException {
        while(true){
            if (grafo.getNext() == null){
                break;
            } else {
                grafo = grafo.getNext();
            }
//            if (grafo.getType().equals("while")){
//                structWhile();
//            } else if (grafo.getType().equals("for")) {
//                structFor();
//            }else if (grafo.getType().equals("cond")) {
//                    write += condicional(grafo.getToken());
//            } else if (grafo.getType().equals("idWhile") || grafo.getType().equals("condWhile") || grafo.getType().equals("nWhile")
//                        || grafo.getType().equals("idFor") || grafo.getType().equals("nFor") || grafo.getType().equals("")){
//                    write = write;
//            } else {
//                write += grafo.getToken();
//            }
        }
        return writer.add_line(methdLine,write);
    }

    public boolean start() throws IOException {
        while (true){
            if (grafo.getPrev() == null){
                break;
            } else{
                grafo = grafo.getPrev();
            }
        }
        writer = new ToFile(path);
        boolean variables = writeDCL();
        path = "D:\\proyects\\cuboLED\\src\\temp\\temp.java";
        writer = new ToFile(path);
        write = "";
        boolean exp = writeStart();
        write = "";
        boolean func = writeFunc();
        return variables && exp && func;
    }
}
