package parser;

import grammar.Symbols;

public class Grafo {

    private Grafo next = null;
    private Grafo prev = null;
    private int scope;
    private String token;
    private int type;
    private int line;

    public void setPrev(Grafo prev) {
        this.prev = prev;
    }
    public void setNext(Grafo next){
        this.next = next;
    }
    public void setScope(int scope){
        this.scope = scope;
    }
    public void setToken(String token){
        this.token = token;
    }
    public void setType(int type){
        this.type = type;
    }
    public Grafo getPrev() {
        return prev;
    }
    public Grafo getNext() {
        return next;
    }
    public int getScope() {
        return scope;
    }
    public String getToken() {
        return token;
    }
    public int getType() {
        return type;
    }
    public void setLine(int line) { this.line = line; }
    public int getLine() { return line; }

}

