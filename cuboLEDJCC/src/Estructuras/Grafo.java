package Estructuras;

public class Grafo {

    private Nodo inicial = new Nodo("INIT","NA");
    private Nodo actual;

    public Grafo(){
       actual = inicial;
    }

    public void addNodo(String tipo, String contenido){
        Nodo aux = new Nodo(tipo,contenido);
        aux.setPrev(actual);
        actual.setNext(aux);
        actual = aux;
    }

    public Nodo getInicial(){
        return inicial;
    }

    public Nodo getPrev(){
        return actual.getPrev();
    }

    public Nodo getNext(){
        return actual.getNext();
    }
}
