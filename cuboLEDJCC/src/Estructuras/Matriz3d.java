package Estructuras;

import java.util.ArrayList;

public class Matriz3d {

    private ArrayList<Cara> matriz = new ArrayList<>();

    public Matriz3d(Cara cara){

    }

    public int getSize(){
        return matriz.size();
    }

    public void addElement(Cara cara){
        matriz.add(cara);
    }

}
