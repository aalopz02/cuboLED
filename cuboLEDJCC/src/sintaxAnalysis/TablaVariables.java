package sintaxAnalysis;

import java.util.ArrayList;

import static sintaxAnalysis.SyntaxChecker.generateParseException;

public class TablaVariables {

    private ArrayList<CeldaTablaVariables> tabla = new ArrayList<>();
    private ArrayList<CeldaTablaIgualdades> tablaIgualdades = new ArrayList<>();
    private ArrayList<CeldaTablaProc> tablaProc = new ArrayList<CeldaTablaProc>();
    private ArrayList<String> validTypes = new ArrayList<>();
    private ArrayList<String> errors = new ArrayList<>();


    TablaVariables() {
        validTypes.add("BOOL");
        validTypes.add("NUM");
        validTypes.add("LIST");
        errors.add("Not defined");
        errors.add("Not in scope");
        errors.add("Can not mix types");
        errors.add("Already defined as");
        errors.add("Is not of type list");
    }

    public void agregarIndiceAcceso(String index){
        tabla.get(tabla.size()-1).setIndex(index);
    }

    public void agregarProc(String id, boolean dcl){
        CeldaTablaProc cell = new CeldaTablaProc(id, dcl);
        tablaProc.add(cell);
    }

    public void agregarParamProc(String paramIn){
        tablaProc.get(tablaProc.size()-1).setParam(paramIn);
    }

    public void agregarParamProc(ArrayList<String> paramsCall, int scope){
        tablaProc.get(tablaProc.size()-1).setParam(paramsCall,scope);
    }

    public void agregarVariable(int numeroVariable, String id, int Scope){
        CeldaTablaVariables cell = new CeldaTablaVariables(numeroVariable, id, Scope);
        tabla.add(cell);
    }

    public void agregarIgualdad(int numeroVariable, int Scope, ArrayList<String> contenido){
        if (numeroVariable == -1){
            agregarParamProc(contenido, Scope);
        } else {
            CeldaTablaIgualdades cell = new CeldaTablaIgualdades(numeroVariable,Scope,contenido);
            tablaIgualdades.add(cell);
        }
    }

    public void agregarIgualdad(int numeroVariable, int Scope, String contenido){
        CeldaTablaIgualdades cell = new CeldaTablaIgualdades(numeroVariable,Scope,contenido);
        tablaIgualdades.add(cell);
    }

    public ArrayList<CeldaTablaVariables> getTabla() {
        return tabla;
    }

    public void imprimirIDS(){
        for (int i = 0; i < tabla.size(); i++){
            CeldaTablaVariables cellAux = tabla.get(i);
                System.out.print("Ids: ");
                System.out.print(cellAux.getId());
                if (!cellAux.getIndex().equals("NA")){
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
                for (int j = 0; j < aux.size(); j++){
                    System.out.print(aux.get(j));
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
        for (int i = 0; i < tablaProc.size(); i++){
            CeldaTablaProc cell = tablaProc.get(i);
            if (cell.isDcl()) {
                System.out.print("IDPROC: ");
                System.out.print(cell.getId());
                System.out.print(", Param: ");
                if (cell.getParam() == null) {
                    System.out.println("NA");
                } else {
                    ArrayList<String> params = cell.getParam();
                    System.out.println();
                    for (int j = 0; j < params.size(); j++) {
                        System.out.println(params.get(j));
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
                    for (int j = 0; j < params.size(); j++) {
                        CeldaTablaIgualdades aux = params.get(j);
                        ArrayList<String> auxParams = aux.getContenido();
                        System.out.println();
                        for (int k = 0; k < auxParams.size(); k++) {
                            System.out.print(auxParams.get(k));
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

    private void generateError(int errType, String cause) throws ParseException{
        if (errType <= 1) {
            System.out.print("Variable: ");
            System.out.println(cause);
            System.out.println(errors.get(errType));
        } else if (errType == 2) {
            System.out.println(errors.get(errType));
            System.out.println(cause);
        } else if (errType == 3) {
            String[] erDescription = cause.split("-");
            System.out.print("Variable: " + erDescription[0] + ", ");
            System.out.println(errors.get(errType) + ": " + erDescription[1]);
            System.out.println("New type: " + erDescription[2]);
        } else if (errType == 4){
            System.out.println(cause);
            System.out.println(errors.get(errType));
        }
        ParseException e = generateParseException();
        throw e;
    }

    public String checkShape(String in){
        String[] dividido = in.split("\\.");
        if (dividido[dividido.length-1].equals("shapeF") || dividido[dividido.length - 1].equals("shapeC")){
            return dividido[0];
        }
        return "";
    }

    public void checkVariables() throws ParseException {
        inferTypes();
    }

    private void inferTypes() throws ParseException {
        ArrayList<String> variablesDefinidas = new ArrayList<>();
        ArrayList<Integer> scopeVars = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        for (int i = 0; i < tabla.size(); i ++) {
            CeldaTablaVariables variable = tabla.get(i);
            String idVar = variable.getId();
            int scopeVar = variable.getScope();
            int numbVar = variable.getNumeroVariable();
            CeldaTablaIgualdades igualdad = tablaIgualdades.get(i);
            ArrayList<String> contenidoIg = igualdad.getContenido();
            String type = "";
            String tiposOp = "NA";
            for (String valor : contenidoIg) {
                if (!validTypes.contains(valor)) {
                    if (!variablesDefinidas.contains(valor)) {
                        String checkShapeOut = checkShape(valor);
                        if (!checkShapeOut.isEmpty()){
                            if (variablesDefinidas.contains(checkShapeOut)){
                                if (types.get(variablesDefinidas.indexOf(checkShapeOut)).equals("LIST")){
                                    type = "NUM";
                                } else {
                                    generateError(4,checkShapeOut);
                                }
                            } else {
                                generateError(0,checkShapeOut);
                            }
                        } else {
                            generateError(0, valor);
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
                } else if (!type.equals(tiposOp)){
                    generateError(2,tiposOp+", "+type);
                }
            }
            if (variablesDefinidas.contains(idVar)) {
                String oldType = types.get(variablesDefinidas.indexOf(idVar));
                if (!tiposOp.equals(oldType)) {
                    generateError(3,idVar+"-"+oldType+"-"+tiposOp);
                }
            } else {
                variablesDefinidas.add(idVar);
            }
            System.out.print("Nombre: " + idVar);
            scopeVars.add(scopeVar);
            System.out.print(", Scope: " +scopeVar);
            types.add(type);
            System.out.println(", Type: " + type);
        }
    }
}
