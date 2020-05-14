package sintaxAnalysis;

import java.util.ArrayList;
import Estructuras.*;

public class CeldaTablaIgualdades {

    private int numeroVariable;
    private ArrayList<String> contenido;
    private int Scope;
    private Matriz3d matriz3d;
    private Cara cara;
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

    public void checkList(){
        for (String valor:contenido) {
            valor = valor.replace("BOOL","B");
            System.out.println(valor);
            this.valor = valor;
            lista = countElements();
            int a =1;
            a++;
        }
    }

    public Lista countElements() {
        Lista listaReturn = new Lista();
        while (valor.charAt(0) != ']'){
            if (valor.charAt(0) == '[') {
                valor = valor.substring(1);
                listaReturn.addList(countElements());
            } else if (valor.charAt(0) == 'B') {
                valor = valor.substring(1);
                listaReturn.addBool();
            } else if (valor.charAt(0) == ',') {
                valor = valor.substring(1);
            }
        }
        valor = valor.substring(1);
        return listaReturn;
    }

}
