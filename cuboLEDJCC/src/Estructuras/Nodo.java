package Estructuras;

public class Nodo {

    private String tipo;
    private String contenido;
    private Nodo prev = null;
    private Nodo next = null;

    Nodo(String tipo, String contenido){
        this.contenido = contenido;
        this.tipo = tipo;
    }

    public Nodo getNext() {
        return next;
    }

    public Nodo getPrev() {
        return prev;
    }

    public String getContenido() {
        return contenido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public void setPrev(Nodo prev) {
        this.prev = prev;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

