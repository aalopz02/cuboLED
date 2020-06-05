package sintaxAnalysis;

import Estructuras.Lista;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

import java.net.Inet4Address;
import java.util.ArrayList;

import static sintaxAnalysis.SyntaxChecker.S;
import static sintaxAnalysis.SyntaxChecker.generateParseException;

public class TablaVariables {

    private ArrayList<CeldaTablaVariables> tabla = new ArrayList<>();
    private ArrayList<CeldaTablaIgualdades> tablaIgualdades = new ArrayList<>();
    private ArrayList<CeldaTablaProc> tablaProc = new ArrayList<CeldaTablaProc>();
    private ArrayList<String> validTypes = new ArrayList<>();
    private ArrayList<String> errors = new ArrayList<>();
    private ArrayList<String> variablesDefinidas = new ArrayList<>();
    private ArrayList<Integer> scopeVars = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();
    private ArrayList<Lista> matrices = new ArrayList<>();
    private ArrayList<Integer> indexMatriz = new ArrayList<>();
    public String log = "OK";
    private ArrayList<Integer> addExtra = new ArrayList<>();

    TablaVariables() {
        validTypes.add("BOOL"); //0
        validTypes.add("NUM"); //1
        validTypes.add("LIST");  //2
        errors.add("Not defined");  //0
        errors.add("Not in scope");//1
        errors.add("Can not mix types");//2
        errors.add("Already defined as");//3
        errors.add("Is not of type list");//4
        errors.add("Index out of range");//5
        errors.add("Needed int");//6
        errors.add("Not expected");//7
        errors.add("Expected 1 or 0 in MatrizInsert"); //8
        errors.add("Cannot compare"); //9
        errors.add("Not defined in for loop"); //10
        errors.add("Cannot be used as iterable object"); //11
        errors.add("Method with signature"); //12
        errors.add("Expected 1 or 0 in Delete"); //13
    }

    public void agregarIndiceAcceso(String index) {
        tabla.get(tabla.size() - 1).setIndex(index);
    }

    public void agregarProc(int line, String id, boolean dcl) {
        CeldaTablaProc cell = new CeldaTablaProc(id, dcl);
        cell.setLineNumber(line);
        tablaProc.add(cell);
    }

    public void agregarProc(String id, boolean dcl) {
        CeldaTablaProc cell = new CeldaTablaProc(id, dcl);
        tablaProc.add(cell);
    }

    public void agregarParamProc(String paramIn) {
        tablaProc.get(tablaProc.size() - 1).setParam(paramIn);
    }

    public void agregarParamProc(ArrayList<String> paramsCall, int scope) {
        tablaProc.get(tablaProc.size() - 1).setParam(paramsCall, scope);
    }

    public void agregarVariable(int numeroVariable, String id, int Scope) {
        CeldaTablaVariables cell = new CeldaTablaVariables(numeroVariable, id, Scope);
        tabla.add(cell);
    }

    public void agregarIgualdad(int numeroVariable, int Scope, ArrayList<String> contenido) {
        if (numeroVariable == -1) {
            agregarParamProc(contenido, Scope);
        } else {
            CeldaTablaIgualdades cell = new CeldaTablaIgualdades(numeroVariable, Scope, contenido);
            tablaIgualdades.add(cell);
        }
    }

    public void agregarIgualdad(int numeroVariable, int Scope, String contenido) {
        CeldaTablaIgualdades cell = new CeldaTablaIgualdades(numeroVariable, Scope, contenido);
        tablaIgualdades.add(cell);
    }

    public void checkProc() throws ParseException {
        CeldaTablaProc call = tablaProc.get(tablaProc.size()-1);
        String idProcCall = call.getId();
        int cantParamCall;
        if (call.getVars() != null){
            cantParamCall = call.getVars().size();
        } else {
            cantParamCall = 0;
        }
        boolean flagProcFound = false;
        int indexProc = 0;
        for (int i = 0; i < tablaProc.size();i++){
            CeldaTablaProc aux = tablaProc.get(i);
            if (aux.getId().equals(idProcCall)){
                if (aux.getParam() == null && cantParamCall == 0){
                    flagProcFound = true;
                    break;
                }
                if (aux.getParam() == null){
                    break;
                }
                if (aux.getParam().size() == cantParamCall){
                    indexProc = i;
                    flagProcFound = true;
                    break;
                }
            }
        }
        if (flagProcFound){
            ArrayList<CeldaTablaIgualdades> params = call.getVars();
            for (int i = 0; i < cantParamCall; i++){
                String idVar = tablaProc.get(indexProc).getParam().get(i);
                CeldaTablaVariables cellV = new CeldaTablaVariables(-5,idVar,0);
                CeldaTablaIgualdades cellIg = params.get(i);
                cellIg.checkList();
                variablesDefinidas.add(cellV.getId());
                if (cellIg.getContenido().get(0).equals("NUM")){
                    types.add("NUM");
                } else if (cellIg.getContenido().get(0).equals("BOOL")){
                    types.add("BOOL");
                } else {
                    types.add("LIST");
                }
                scopeVars.add(0);
                indexMatriz.add(variablesDefinidas.indexOf(idVar));
                matrices.add(variablesDefinidas.indexOf(idVar), cellIg.getLista());
            }
        } else {
            generateError(12,idProcCall+"-"+String.valueOf(cantParamCall));
        }
    }

    public ArrayList<CeldaTablaVariables> getTabla() {
        return tabla;
    }

    public void imprimirIDS() {
        for (int i = 0; i < tabla.size(); i++) {
            CeldaTablaVariables cellAux = tabla.get(i);
            System.out.print("Ids: ");
            System.out.print(cellAux.getId());
            if (!cellAux.getIndex().equals("NA")) {
                System.out.print(", Index: ");
                System.out.print(cellAux.getIndex());
            }
            System.out.print(", Scope: ");
            System.out.print(cellAux.getScope());
            System.out.print(", NUMVAR: ");
            System.out.print(cellAux.getNumeroVariable());
            System.out.println("");

            CeldaTablaIgualdades cellAuxIg = tablaIgualdades.get(i);
            System.out.print("Contenido: ");
            ArrayList<String> aux = cellAuxIg.getContenido();
            for (String s : aux) {
                System.out.print(s);
                System.out.print("-");
            }
            System.out.print(", ScopeIg: ");
            System.out.print(cellAuxIg.getScope());
            System.out.print(", NUMVARIG: ");
            System.out.print(cellAuxIg.getNumeroVariable());
            System.out.println();
            System.out.println("Lista");
            cellAuxIg.checkList();
            System.out.println();
        }
        for (CeldaTablaProc cell : tablaProc) {
            if (cell.isDcl()) {
                System.out.print("IDPROC: ");
                System.out.print(cell.getId());
                System.out.print(", Param: ");
                if (cell.getParam() == null) {
                    System.out.println("NA");
                } else {
                    ArrayList<String> params = cell.getParam();
                    System.out.println();
                    for (String param : params) {
                        System.out.println(param);
                    }
                }
            } else {
                System.out.print("Call to: ");
                System.out.print(cell.getId());
                System.out.print(", Param: ");
                if (cell.getVars().isEmpty()) {
                    System.out.println();
                    System.out.print("NA");
                } else {
                    ArrayList<CeldaTablaIgualdades> params = cell.getVars();
                    for (CeldaTablaIgualdades aux : params) {
                        ArrayList<String> auxParams = aux.getContenido();
                        System.out.println();
                        for (String auxParam : auxParams) {
                            System.out.print(auxParam);
                            System.out.print("-");
                        }
                        System.out.print(" Scope: ");
                        System.out.print(aux.getScope());
                    }
                }
            }
            System.out.println("");
        }

    }

    private void generateError(int errType, String cause) throws ParseException {
        if (errType <= 1) {
            log = "Variable: " + cause + '\n';
            log += errors.get(errType) + '\n';
        } else if (errType == 2) {
            log = errors.get(errType) + '\n';
            log += cause+'\n';
        } else if (errType == 3) {
            String[] erDescription = cause.split("-");
            log = "Variable: " + erDescription[0] + ", " + errors.get(errType) + ": " + erDescription[1] + '\n';
            log += "New type: " + erDescription[2] + '\n';
        } else if (errType == 4) {
            log = cause + '\n';
            log += errors.get(errType) + '\n';
        } else if (errType == 5) {
            log = errors.get(errType) + '\n';
            log += "Tried to access: " + cause + '\n';
        } else if (errType == 6) {
            log = errors.get(errType) + '\n';
            log += "Provided with: " + cause + '\n';
        } else if (errType == 7) {
            log = "Expresion: " + cause + '\n';
            log += errors.get(errType) + '\n';
        } else if (errType == 8) {
            log = errors.get(errType) + '\n';
            log += "Provided with: " + cause + '\n';
        } else if (errType == 9) {
            log = errors.get(errType) + '\n';
            String[] erDescription = cause.split("-");
            log += erDescription[0] + " with " + erDescription[1] + '\n';
        } else if (errType == 10) {
            log = cause + ", " + errors.get(errType) + '\n';
        } else if (errType == 11) {
            log = cause + ", " + errors.get(errType) + '\n';
        } else if (errType == 12){
            log = errors.get(errType) + ": " + '\n';
            String[] erDescription = cause.split("-");
            log += "id: " + erDescription[0] + " and " + erDescription[1] + " params" + '\n';
            log += "Not defined" + '\n';
        } else if (errType == 13){
            log = errors.get(errType) + '\n';
            String[] erDescription = cause.split("-");
            log += "for list: " + erDescription[0] + " provided with: " + erDescription[1] + '\n';
        }
        System.out.println(log);
        SyntaxException e = new SyntaxException(log);
        throw e;
    }

    public String checkShape(String in, boolean flagDCL) {
        String[] dividido = in.split("\\.");
        if (dividido[dividido.length - 1].equals("shapeF")) {
            if (flagDCL) {
                return dividido[0];
            }
            return "f" + dividido[0];
        } else if (dividido[dividido.length - 1].equals("shapeC")) {
            if (flagDCL) {
                return dividido[0];
            }
            return "c" + dividido[0];
        }
        return "";
    }

    public String checkLen(ArrayList<String> contenido, int indice) {
        String variable = contenido.get(indice + 1);
        if (variable.contains("[")) {
            //verifica con indice
            return "NUM";
        }
        return "NUM";
    }

    public void checkVariables() throws ParseException {
        inferTypes();
    }

    private Lista lookForMatriz(int indiceId) {
        return matrices.get(indexMatriz.indexOf(indiceId));
    }

    private String checkIndexAux(ArrayList<Integer> indices, String id) throws ParseException {
        Lista lista = lookForMatriz(variablesDefinidas.indexOf(id));
        if (indices.size() == 1) {
            try {
                if (lista.getContenido().charAt(indices.get(0)) == 'B') {
                    return "BOOL";
                } else {
                    return "LIST";
                }
            } catch (IndexOutOfBoundsException e) {
                generateError(5, id);
            }
        }
        if (indices.size() == 2) {
            try {
                if (lista.getContenido().charAt(indices.get(0)) == 'L') {
                    int indiceSubLista = 0;
                    for (int d2 = 0; d2 < indices.get(0) - 2; d2++) {
                        if (lista.getContenido().charAt(indices.get(d2)) == 'L') {
                            indiceSubLista++;
                        }
                    }
                    if (lista.getLista().get(indiceSubLista).getContenido().charAt(indices.get(1)) == 'B') {
                        return "BOOL";
                    } else {
                        return "LIST";
                    }
                } else {
                    generateError(4, "BOOL");
                }
            } catch (IndexOutOfBoundsException e) {
                generateError(5, id);
            }
        }
        if (indices.size() == 3) {
            try {
                if (lista.getContenido().charAt(indices.get(0)) == 'L') {
                    int indiceSubLista = 0;
                    for (int d2 = 0; d2 < indices.get(0) - 2; d2++) {
                        if (lista.getContenido().charAt(indices.get(d2)) == 'L') {
                            indiceSubLista++;
                        }
                    }
                    if (lista.getLista().get(indiceSubLista).getContenido().charAt(indices.get(1)) == 'L') {
                        int indiceSubLista3 = 0;
                        for (int d3 = 0; d3 < indices.get(1) - 2; d3++) {
                            if (lista.getContenido().charAt(indices.get(d3)) == 'L') {
                                indiceSubLista3++;
                            }
                        }
                        if (lista.getLista().get(indiceSubLista).getLista().get(indiceSubLista3).getSize() >= indices.get(2)) {
                            return "BOOL";
                        } else {
                            generateError(5, id);
                        }
                    } else {
                        generateError(4, "BOOL");
                    }
                } else {
                    generateError(4, "BOOL");
                }
            } catch (IndexOutOfBoundsException e) {
                generateError(5, id);
            }
        }
        return "BOOL";
    }

    private String checkIndex(String id, String index) throws ParseException {
        if (index.equals("NA")) return types.get(variablesDefinidas.indexOf(id));
        ArrayList<Integer> indices = new ArrayList<>();
        String[] indexAux;
        if (!index.equals("")) {
            id += index;
        }
        id = id.replace("]", "");
        indexAux = id.split("\\[");
        id = indexAux[0];
        boolean flagPrimerIter = true;
        for (String subindex : indexAux) {
            try {
                indices.add(Integer.parseInt(subindex));
            } catch (NumberFormatException e) {
                String checkShapeOut = checkShape(subindex, false);
                if (!checkShapeOut.isEmpty()) {
                    if (variablesDefinidas.contains(checkShapeOut.substring(1))) {
                        if (types.get(variablesDefinidas.indexOf(checkShapeOut.substring(1))).equals("LIST")) {
                            Lista listaShape = lookForMatriz(variablesDefinidas.indexOf(checkShapeOut.substring(1)));
                            if (checkShapeOut.charAt(0) == 'c') {
                                indices.add(listaShape.shapeC());
                            } else {
                                indices.add(listaShape.shapeF());
                            }
                        } else {
                            generateError(4, checkShapeOut.substring(1));
                        }
                    } else {
                        generateError(0, checkShapeOut);
                    }
                } else {
                    if (variablesDefinidas.contains(subindex)) {
                        if (flagPrimerIter) {
                            if (!types.get(variablesDefinidas.indexOf(subindex)).equals("LIST")) {
                                generateError(4, subindex);
                            }
                        } else {
                            if (types.get(variablesDefinidas.indexOf(subindex)).equals("NUM")) {
                                indices.add(0);
                            } else {
                                generateError(6, types.get(variablesDefinidas.indexOf(subindex)));
                            }
                        }
                    } else {
                        if (subindex.contains(":") || subindex.contains(",")) {
                            subindex = subindex.replace(",", "");
                            if (subindex.contains("true") || subindex.contains("false")) {
                                generateError(7, "BOOL");
                            }
                            String[] range = subindex.split(":");
                            for (String indx : range) {
                                if (!indx.isEmpty()) {
                                    System.out.println("Index: " + indx);
                                    ArrayList<Integer> indexRange = new ArrayList<>();
                                    indexRange.add(Integer.parseInt(indx));
                                    checkIndexAux(indexRange, id);
                                }
                            }
                            return "LIST";
                        } else {
                            generateError(0, subindex);
                        }
                    }
                }
            }
            flagPrimerIter = false;
        }
        for (Integer in : indices) {
            System.out.println("Index: " + in);
        }
        return checkIndexAux(indices, id);
    }

    private String checkInsert(CeldaTablaIgualdades igualdad) throws ParseException {
        ArrayList<Integer> indexInsert = new ArrayList<>();
        ArrayList<String> contenido = igualdad.getContenido();
        String tipoInsert = contenido.get(1);
        if (tipoInsert.equals("InsertMatriz")) {
            int columRow = Integer.parseInt(contenido.get(3));
            if (!(columRow == 1 || columRow == 0)) {
                generateError(8, String.valueOf(columRow));
            } else {
                igualdad.InsertMatriz();
            }
        } else {
            igualdad.InsertLista();
        }
        return "LIST";
    }

    private String checkDel(CeldaTablaIgualdades igualdad, String id) throws ParseException {
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(Integer.parseInt(igualdad.getContenido().get(1)));
        checkIndexAux(aux,id);
        if (igualdad.getContenido().size() == 3){
            if (!igualdad.getContenido().get(2).equals("1") && !igualdad.getContenido().get(2).equals("0")){
                generateError(13,id+"-"+igualdad.getContenido().get(2));
            }
        }
        return "LIST";
    }

    private void inferTypes() throws ParseException {
        for (int i = 0; i < tabla.size(); i++) {
            CeldaTablaVariables variable = tabla.get(i);
            String idVar = variable.getId();
            int scopeVar = variable.getScope();
            int numbVar = variable.getNumeroVariable();
            CeldaTablaIgualdades igualdad = tablaIgualdades.get(i);
            ArrayList<String> contenidoIg = igualdad.getContenido();
            String type = "";
            String tiposOp = "NA";
            boolean flagAccess = false;
            if (numbVar == -2) {
                igualdad = tablaIgualdades.get(i + 1);
                contenidoIg = igualdad.getContenido();
            }
            if (numbVar != -99) {
                for (int j = 0; j < contenidoIg.size(); j++) {
                    String valor = contenidoIg.get(j);
                    if (valor.equals("insert")) {
                        tiposOp = checkInsert(igualdad);
                        flagAccess = true;
                        if (!variablesDefinidas.contains(idVar)) {
                            generateError(0, idVar);
                        }
                        break;
                    }
                    if (valor.equals("del")) {
                        tiposOp = checkDel(igualdad,idVar);
                        flagAccess = true;
                        if (!variablesDefinidas.contains(idVar)) {
                            generateError(0, idVar);
                        }
                        break;
                    }
                    if (valor.equals("Neg") || valor.equals("T") || valor.equals("F")) {
                        tiposOp = checkIndex(idVar, variable.getIndex());
                        flagAccess = true;
                        if (!variablesDefinidas.contains(idVar)) {
                            generateError(0, idVar);
                        }
                        break;
                    }
                    if (!variable.getIndex().equals("NA")) {
                        tiposOp = checkIndex(idVar, variable.getIndex());
                        flagAccess = true;
                    }
                    if (!validTypes.contains(valor) && numbVar != -1) {
                        if (!variablesDefinidas.contains(valor)) {
                            if (!valor.contains("[")) {
                                String checkShapeOut = checkShape(valor, true);
                                if (!checkShapeOut.isEmpty()) {
                                    if (variablesDefinidas.contains(checkShapeOut)) {
                                        if (types.get(variablesDefinidas.indexOf(checkShapeOut)).equals("LIST")) {
                                            type = "NUM";
                                        } else {
                                            generateError(4, checkShapeOut);
                                        }
                                    } else {
                                        generateError(0, checkShapeOut);
                                    }
                                } else {
                                    if (valor.equals("len")) {
                                        type = checkLen(contenidoIg, j);
                                        contenidoIg = new ArrayList<>();
                                        contenidoIg.add(type);
                                    } else {
                                        generateError(0, valor);
                                    }
                                }
                            } else {
                                type = checkIndex(valor, "");
                                if (type.substring(0, 3).equals("ERR")) {
                                    generateError(5, type.substring(3));
                                }
                            }
                        } else {
                            int indiceAssig = variablesDefinidas.indexOf(valor);
                            int scopeVarIg = scopeVars.get(indiceAssig);
                            if (!(scopeVarIg <= scopeVar)) {
                                generateError(1, valor);
                            }
                            type = types.get(indiceAssig);
                        }
                    } else {
                        type = valor;
                    }
                    if (tiposOp.equals("NA")) {
                        tiposOp = type;
                    } else if (!type.equals(tiposOp) && numbVar != -2) {
                        generateError(2, tiposOp + ", " + type);
                    }
                }
                if (variablesDefinidas.contains(idVar)) {
                    if (numbVar != -1) {
                        String oldType = types.get(variablesDefinidas.indexOf(idVar));
                        if (!tiposOp.equals(oldType) && !flagAccess) {
                            if (numbVar == -2) {
                                if (!(tiposOp.equals("BOOL") && oldType.equals("LIST"))) {
                                    generateError(9, oldType + "-" + tiposOp);
                                }
                            } else {
                                if (numbVar != -5){
                                    generateError(3, idVar + "-" + oldType + "-" + tiposOp);
                                }
                            }
                        }
                        if (tiposOp.equals("LIST") && !flagAccess) {
                            matrices.add(variablesDefinidas.indexOf(idVar), igualdad.getLista());
                        }
                    } else {
                        System.out.println(tiposOp);
                        if (tiposOp.isEmpty()) {
                            tiposOp = types.get(variablesDefinidas.indexOf(idVar));
                        }
                        System.out.println(tiposOp);
                        if (!tiposOp.equals("LIST") && !tiposOp.equals("NUM")) {
                            generateError(11, idVar);
                        }
                    }
                } else {
                    if (numbVar == -1 && igualdad.getNumeroVariable() == -99) {
                        generateError(10, idVar);
                    }
                    variablesDefinidas.add(idVar);
                    if (numbVar == -1 && igualdad.getNumeroVariable() == -1) {
                        type = "NUM";
                    }
                    types.add(type);
                    scopeVars.add(scopeVar);
                    indexMatriz.add(variablesDefinidas.indexOf(idVar));
                    matrices.add(variablesDefinidas.indexOf(idVar), igualdad.getLista());
                }
                if (!flagAccess) {
                    System.out.print("Nombre: " + idVar);
                    System.out.print(", Scope: " + scopeVar);
                    System.out.println(", Type: " + type);
                } else {
                    System.out.println("Acceso: " + idVar);
                }
            }

        }
    }

}
