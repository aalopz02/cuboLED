package sintaxAnalysis;

import java.util.ArrayList;

public class TablaVariables {

    private ArrayList<CeldaTablaVariables> tabla = new ArrayList<>();
    private ArrayList<CeldaTablaIgualdades> tablaIgualdades = new ArrayList<>();

    public void agregarIndiceAcceso(String index){
        tabla.get(tabla.size()-1).setIndex(index);
    }

    public void agregarVariable(int numeroVariable, String id, int Scope){
        CeldaTablaVariables cell = new CeldaTablaVariables(numeroVariable, id, Scope);
        tabla.add(cell);
    }

    public void agregarIgualdad(int numeroVariable, int Scope, ArrayList<String> contenido){
        CeldaTablaIgualdades cell = new CeldaTablaIgualdades(numeroVariable,Scope,contenido);
        tablaIgualdades.add(cell);
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
            System.out.println("");
        }
    }
}
