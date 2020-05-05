package sintaxAnalysis;

import java.util.ArrayList;

public class CeldaTablaProc {

    private String id;
    private ArrayList<String> param = null;
    private boolean dcl;

    CeldaTablaProc(String id, boolean dcl){
        this.id = id;
        this.dcl = dcl;
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

    public void setParam(String paramIn) {
        if (param == null){
            param = new ArrayList<>();
            param.add(paramIn);
        } else {
            param.add(paramIn);
        }
    }

    public void setParam(ArrayList<String> contenido, int scope){

    }

    public boolean isDcl() {
        return dcl;
    }

    public void setDcl(boolean dcl) {
        this.dcl = dcl;
    }
}
