package sintaxAnalysis;

public class CeldaTablaVariables {

    private int numeroVariable;
    private String id;
    private int Scope;
    private String index = "NA";
    private String type = "ND";

    CeldaTablaVariables(int numeroVariable, String id, int Scope){
        setNumeroVariable(numeroVariable);
        setId(id);
        setScope(Scope);
    }

    public int getNumeroVariable() {
        return numeroVariable;
    }

    public void setNumeroVariable(int numeroVariable) {
        this.numeroVariable = numeroVariable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScope() {
        return Scope;
    }

    public void setScope(int scope) {
        Scope = scope;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
