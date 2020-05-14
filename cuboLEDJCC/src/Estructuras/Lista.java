package Estructuras;

import java.util.ArrayList;

public class Lista {

    private int size = 0;
    private ArrayList<Lista> lista = null;

    public Lista(){

    }

    public int getSize(){
        return size;
    }

    public void addList(Lista lista){
        if (this.lista == null) {
            this.lista = new ArrayList<Lista>();
        }
        this.lista.add(lista);
        this.size ++;
    }

    public void addBool(){
        this.size++;
    }

}
