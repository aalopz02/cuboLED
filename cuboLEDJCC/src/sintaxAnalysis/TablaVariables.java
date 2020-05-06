package sintaxAnalysis;

import java.util.ArrayList;

public class TablaVariables {

    private ArrayList<CeldaTablaVariables> tabla = new ArrayList<>();
    private ArrayList<CeldaTablaIgualdades> tablaIgualdades = new ArrayList<>();
    private ArrayList<CeldaTablaProc> tablaProc = new ArrayList<CeldaTablaProc>();

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
            if (cellAuxIg.getNumeroVariable() != -99) {
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
                System.out.println("");
            }

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
}
