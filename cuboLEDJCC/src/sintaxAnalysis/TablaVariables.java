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
                System.out.print(cellAux.getIndex());
            }
            System.out.print(", Scope: ");
            System.out.print(cellAux.getScope());
            System.out.print(", NUMVAR: ");
            System.out.print(cellAux.getNumeroVariable());
            System.out.println("");
        }
    }

    public void imprimirIG(){
        for (int i = 0; i < tablaIgualdades.size(); i++){
            CeldaTablaIgualdades cellAux = tablaIgualdades.get(i);
            System.out.print("Contenido: ");
            ArrayList<String> aux = cellAux.getContenido();
            for (int j = 0; j < aux.size(); j++){
                System.out.print(aux.get(j));
                System.out.print("-");
            }
            System.out.print(", Scope: ");
            System.out.print(cellAux.getScope());
            System.out.print(", NUMVAR: ");
            System.out.print(cellAux.getNumeroVariable());
            System.out.println("");
        }
    }
}
