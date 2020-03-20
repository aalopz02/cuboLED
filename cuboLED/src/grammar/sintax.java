package grammar;

import java.util.ArrayList;

import java_cup.sym;
import parser.Grafo;

public class sintax {

    private Grafo grafo;
    private ArrayList<String> idVariables;
    private ArrayList<String> idFunctions;
    private ArrayList<Integer> tokenExpected = new ArrayList<>();
    private ArrayList<String> tiposPermidos = new ArrayList<>();
    private ArrayList<ArrayList<String>> tipos;

    public sintax(Grafo grafo, ArrayList<String> idsVariables , ArrayList<String> idsFunc, ArrayList<ArrayList<String>> tipos){
        this.grafo = grafo;
        this.idFunctions = idsFunc;
        this.idVariables = idsVariables;
        this.tipos = tipos;
        tiposPermidos.add("int");
        tiposPermidos.add("float");
        tiposPermidos.add("bool");
        tiposPermidos.add("list");
    }

    private void createError(String err){
        Grafo aux = new Grafo();
        aux.setType(Symbols.ERROR);
        aux.setToken(err);
        aux.setLine(grafo.getLine());
        grafo = aux;
    }

    private String checkForLEXERR(){
        Grafo aux = grafo;
        while (aux.getNext() != null){
            if (aux.getType() == Symbols.ERROR){
                return "Token: " + aux.getToken() + ", no reconocido en: " + aux.getLine();
            }
            if (aux.getType() == Symbols.ERRORASSIG){
                return "Error durante asiganción en: " + aux.getLine();
            }
            aux = aux.getNext();
        }

        return "ok";
    }

    private void checkDCL(){
        if (idVariables.contains(grafo.getToken())){
            int indexID = tipos.get(0).indexOf(grafo.getToken());//Saca indice de id
            String firstType = tipos.get(1).get(indexID);//Busca con el indice el tipo asignado a ese id
            int flagNuevo = 0;
            for (int i = indexID; i < tipos.get(0).size(); i++){
                System.out.println(tipos.get(0).get(i));
                if (tipos.get(0).get(i).equals(grafo.getToken())){
                    System.out.println("encontro id igual");
                    if (!tipos.get(1).get(i).equals(firstType)) {
                        if (idVariables.contains(tipos.get(1).get(i))) {
                            String idVar2 = tipos.get(0).get(i);
                            String tipoVar2 = tipos.get(1).get(tipos.get(0).indexOf(idVar2));
                            if (flagNuevo == 0){
                                firstType = tipoVar2;
                            } else {
                                if (!firstType.equals(tipoVar2)) {
                                    createError("Variable: " + tipos.get(1).get(i) + ", definida anteriormente como: " + firstType);
                                    break;
                                }
                            }
                        } else {
                            createError("Variable: " + tipos.get(1).get(i) + "no definida");
                            break;
                        }
                    }
                    flagNuevo = 1;
                }

            }

        } else {
            createError("ID no definido");
        }
    }

    public String iniciar(){
        String lexRes = checkForLEXERR();
        if (!lexRes.equals("ok")) {
            return lexRes;
        }
        tokenExpected.add(Symbols.ID);
        tokenExpected.add(Symbols.PTOCOMA);
        while (grafo.getNext() != null){
            switch (grafo.getType()){
                case Symbols.ID: {
                    if (tokenExpected.contains(Symbols.ID)) {
                        if (grafo.getPrev().getType() == Symbols.PTOCOMA) {
                            checkDCL();
                        }
                    } else return "Identificador: " + grafo.getToken() + "inesperado en: " + grafo.getLine();
                }

            }
            grafo =  grafo.getNext();
        }
        return "ok";
    }

    public void imprimir(){
        while (grafo.getNext() != null){
            System.out.print("tipo: ");
            System.out.print(grafo.getType());
            System.out.print(", token: " + grafo.getToken());
            System.out.println("");
            grafo = grafo.getNext();
        }
        System.out.println("Variables: ");
        for (String idVariable : idVariables) {
            System.out.println(idVariable);
        }
        System.out.println("Tipos: ");
        for (int i = 0; i < tipos.get(0).size(); i++) {
            System.out.print(tipos.get(0).get(i));
            System.out.print(", tipo: ");
            System.out.print(tipos.get(1).get(i));
            System.out.println("");
        }
        System.out.println("FUNC: ");
        for (String idFunc : idFunctions) {
            System.out.println(idFunc);
        }
    }
}
