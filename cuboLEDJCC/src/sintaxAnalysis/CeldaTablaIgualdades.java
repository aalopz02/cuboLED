package sintaxAnalysis;

import java.util.ArrayList;
import java.util.List;

import Estructuras.*;
import jdk.internal.cmm.SystemResourcePressureImpl;

public class CeldaTablaIgualdades {

    private int numeroVariable;
    private ArrayList<String> contenido;
    private int Scope;
    private Lista lista;
    String valor;

    CeldaTablaIgualdades(int numeroVariable, int Scope, String contenido){
        if (numeroVariable > 0){
            setNumeroVariable(numeroVariable-1);
        } else {
            setNumeroVariable(numeroVariable);
        }
        setScope(Scope);
        this.contenido = new ArrayList<String>();
        this.contenido.add(contenido);
    }

    CeldaTablaIgualdades(int numeroVariable, int Scope, ArrayList<String> contenido){
        if (numeroVariable > 0){
            setNumeroVariable(numeroVariable-1);
        } else {
            setNumeroVariable(numeroVariable);
        }
        setScope(Scope);
        setContenido(contenido);
    }

    CeldaTablaIgualdades(int Scope, ArrayList<String> contenido){
        setScope(Scope);
        setContenido(contenido);
    }

    CeldaTablaIgualdades(int numeroVariable, int Scope, String contenido,  int x, int y){
        setNumeroVariable(numeroVariable);
        setScope(Scope);
        this.contenido = new ArrayList<String>();
        String matriz3d = "[";
        for (int k = 0; k < y; k++){
            String matriz = "[";
            for (int i = 0; i < x; i++){
                String lista = "[";
                for (int j = 0; j < x; j++){
                    lista+="false";
                    if (j < x){
                        lista+=",";
                    }
                }
                lista+="]";
                matriz+=lista;
                if (i < x){
                    matriz+=",";
                }
            }
            matriz += "]";
            matriz3d+=matriz;
            if (k < y){
                matriz3d+=",";
            }
        }
        matriz3d+="]";
        System.out.println(matriz3d);
        this.contenido.add(matriz3d);
    }

    public int getNumeroVariable() {
        return numeroVariable;
    }

    public void setNumeroVariable(int numeroVariable) {
        this.numeroVariable = numeroVariable;
    }

    public int getScope() {
        return Scope;
    }

    public void setScope(int scope) {
        Scope = scope;
    }

    public ArrayList<String> getContenido() {
        return contenido;
    }

    public void setContenido(ArrayList<String> contenido) {
        this.contenido = contenido;
    }

    public void InsertMatriz(){
        lista.addList(new Lista());
    }

    public void InsertLista(){
        lista.addBool();
    }

    public void checkList() {
        if (contenido.get(0).isEmpty()){
            return;
        }
        if (contenido.get(0).equals("createList")) {
            this.lista = new Lista(Integer.parseInt(contenido.get(1)));
            System.out.println("NEWLIST");
            contenido = new ArrayList<>();
            contenido.add("LIST");
            return;
        }
        if (contenido.get(0).charAt(0) != '[') {
            System.out.println("NOLIST");
            return;
        }
        for (String valor : contenido) {
            if (valor.equals("len")) return;
            valor = valor.replace("true", "B");
            valor = valor.replace("false", "B");
            valor = valor.substring(1);
            valor = valor.substring(0, valor.length() - 1);
            System.out.println(valor);
            this.valor = valor;
            lista = countElements();
            contenido = new ArrayList<>();
            contenido.add("LIST");
        }
    }

    public Lista countElements() {
        Lista listaReturn = new Lista();
        while (!valor.isEmpty()) {
            if (valor.charAt(0) == '[') {
                valor = valor.substring(1);
                listaReturn.addList(countElements());
            } else if (valor.charAt(0) == 'B') {
                valor = valor.substring(1);
                listaReturn.addBool();
            } else if (valor.charAt(0) == ',') {
                valor = valor.substring(1);
            } else if (valor.charAt(0) == ']') {
                valor = valor.substring(1);
                return listaReturn;
            }
        }
        return listaReturn;
    }

    public Lista getLista() {
        return lista;
    }

}
