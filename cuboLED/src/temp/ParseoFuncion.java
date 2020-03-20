package temp;

import org.json.simple.JSONArray;
import server.ServerThread;
import org.json.simple.JSONObject;
import java.io.IOException;

public class ParseoFuncion {
    private JSONObject json = new JSONObject();
    public JSONArray comandos = new JSONArray();

    public void Move(String dir){
        comandos.add(dir);
    }

    public void Rotar(){
        comandos.add("R");
    }

    public void Compilado() throws IOException {
        json.put("command",comandos);
        ServerThread.server.POST(json.toString());
    }

}