package sintaxAnalysis;

import java.util.ArrayList;

public class CeldaTablaProc {

    private String id;
    private ArrayList<String> param = null;
    private boolean dcl;
    private ArrayList<CeldaTablaIgualdades> var = null;
    private int lineNumber;

    CeldaTablaProc(String id, boolean dcl){
        this.id = id;
        this.dcl = dcl;
        if (!dcl) {
            var = new ArrayList<>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getParam() {
        return param;
    }

    public ArrayList<CeldaTablaIgualdades> getVars() {
        return var;
    }

    public void setParam(String paramIn) {
        if (param == null){
            param = new ArrayList<>();
        } param.add(paramIn);
    }

    public void setParam(ArrayList<String> contenido, int scope){
         if (contenido.size() != 0){
             CeldaTablaIgualdades cell = new CeldaTablaIgualdades(scope,contenido);
             var.add(cell);
         }

    }

    public boolean isDcl() {
        return dcl;
    }

    public void setDcl(boolean dcl) {
        this.dcl = dcl;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
