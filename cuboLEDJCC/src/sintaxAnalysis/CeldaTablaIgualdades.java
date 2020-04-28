package sintaxAnalysis;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CeldaTablaIgualdades {

    private int numeroVariable;
    private ArrayList<String> contenido;
    private int Scope;

    CeldaTablaIgualdades(int numeroVariable, int Scope, String contenido){
        setNumeroVariable(numeroVariable);
        setScope(Scope);
        this.contenido = new ArrayList<String>();
        this.contenido.add(contenido);
    }

    CeldaTablaIgualdades(int numeroVariable, int Scope, ArrayList<String> contenido){
        setNumeroVariable(numeroVariable);
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
}
