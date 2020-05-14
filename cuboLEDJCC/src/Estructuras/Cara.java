package Estructuras;

import java.util.ArrayList;

public class Cara {

    private ArrayList<Lista> cara = new ArrayList<>();

    public Cara() {

    }

    public int getSize(){
        return cara.size();
    }

    public void addElement(Lista lista){
        cara.add(lista);
    }

}
