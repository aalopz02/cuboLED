package Estructuras;

import java.util.ArrayList;

public class Lista {

    private int size = 0;
    private ArrayList<Lista> lista = null;
    private int id = 0;

    public Lista(){

    }

    public Lista(int size){
        this.size = size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getSize(){
        return size;
    }

    public ArrayList<Lista> getLista(){
        return lista;
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

    public int shapeF(){
        return size;
    }

    public int shapeC(){
        return lista.size();
    }
}
