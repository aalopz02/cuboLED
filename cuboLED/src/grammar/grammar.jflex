package grammar;

import java.io.*;
import java.util.ArrayList;
import parser.Grafo;
import java_cup.sym;
%%

%public
%class parser
%standalone
%unicode
%line
%column
%eofclose
%cup

%{
    public Grafo grafo;
    private int scope = 0;
	private ArrayList<String> idsVariables = new ArrayList<String>();
	private ArrayList<String> idsFunc = new ArrayList<String>();
	private ArrayList<String> varsToCheck = new ArrayList<String>();
	private ArrayList<ArrayList<String>> varTypes = new ArrayList<>();
	private int flagVarsType = 0;
	private int flagOperacion = 0;
	
    public void init(){
        grafo = new Grafo();
        grafo.setType(Symbols.INICIO);
        grafo.setScope(scope);
		varTypes.add(new ArrayList<String>());
		varTypes.add(new ArrayList<String>());
    }

	public ArrayList<ArrayList<String>> getTypes(){
		return varTypes;
	}

	public ArrayList<String> getIdVars(){
		return idsVariables;
	} 
	
	public ArrayList<String> getIdsFunc(){
		return idsFunc;
	}
	
	public Grafo getGrafo(){
		while(grafo.getPrev() != null){
			grafo = grafo.getPrev();
		}
		return grafo;
	}
	
	private void initType(String id){
		varsToCheck.add(id);
	}

	private void setTYPE(int sym){
		String id = varsToCheck.remove(0);
		String tipo = "";
		if (sym == Symbols.NUM) {
			tipo = "int";
		}
		if (sym == Symbols.FLOAT) {
			tipo = "float";
		}
		if (sym == Symbols.BOOL) {
			tipo = "bool";
		}
		if (sym == Symbols.ID) {
			tipo = yytext();
		}
		if (sym == Symbols.LIST) {
			tipo = "list";
		}
		System.out.print("Variable: ");
		System.out.print(id);
		System.out.print(", tipo: ");
		System.out.print(tipo);
		System.out.println("");
		varTypes.get(0).add(id);
		varTypes.get(1).add(tipo);
	}

    private void addToken(int sym){
        Grafo nuevo = new Grafo();
        nuevo.setPrev(grafo);
        nuevo.setType(sym);
        nuevo.setToken(yytext());
		if (sym == Symbols.OPER) {
			flagOperacion = 1;
		}
        if (grafo.getType() == Symbols.OPENBRACKETS){
			scope = scope + 1;
		}
		if (grafo.getType() == Symbols.CLOSEBRACKETS){
			scope = scope - 1;
		}
		if (sym == Symbols.ID && flagVarsType == 1 && flagOperacion == 0) {
			setTYPE(sym);
		}
		if (sym == Symbols.LIST && flagVarsType == 1) {
			setTYPE(sym);
		}
		if (((grafo.getType() == Symbols.PTOCOMA && sym == Symbols.ID) || (grafo.getType() == Symbols.COMA && sym == Symbols.ID)) && flagVarsType == 0){
			initType(yytext());
		}
		if (sym == Symbols.EQUALS) {
			flagVarsType = 1;
		}
		if (((sym == Symbols.NUM && flagVarsType == 1) || (sym == Symbols.FLOAT && flagVarsType == 1)) && flagOperacion == 0) {
			setTYPE(sym);	 
		}
		if (sym == Symbols.BOOL && flagVarsType == 1) {
			setTYPE(sym);
		}
		if ((sym == Symbols.PTOCOMA && flagOperacion == 1) || (sym == Symbols.COMA && flagOperacion == 1)) {
			flagOperacion = 0;
		}
		if (sym == Symbols.PTOCOMA && flagVarsType == 1) {
			if (!varsToCheck.isEmpty()) {
				nuevo.setType(Symbols.ERRORASSIG);
			} else {
				flagVarsType = 0;
			}
		}
		nuevo.setScope(scope);
		nuevo.setLine(yyline);
		nuevo.setPrev(grafo);
		grafo.setNext(nuevo);
		grafo = nuevo;
    }

	private void imprimir(int sym, String type){
		System.out.println(type);
		System.out.print("token: " + yytext());
		System.out.print(", line: " + yyline);
		System.out.print(", column: " + yycolumn);
		System.out.println("");
		if (sym == Symbols.INICIO){return;}
		if (sym == Symbols.ERROR) {
			System.out.println("er");
		}
		if (sym == Symbols.ID){
			if (grafo.getType() ==  Symbols.INITFUNC){
				idsFunc.add(yytext());
			} else {
				idsVariables.add(yytext());
			}
		}
		addToken(sym);
	}
	
%}

LineTerminator = \n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]
bool = true|false
Identifier = [a-z][[_]|[:jletter:]|[:jletterdigit:]|[@]|[#]|[_]]*
DecIntegerLiteral = 0 | [1-9][0-9]*
DecFloat = [0 | [1-9][0-9]*][.][0 | [1-9][0-9]*]*
Comment = [-][-][[:jletterdigit:][WhiteSpace]]*

%%
	"list"				{imprimir(Symbols.LIST, "list");}
	","					{imprimir(Symbols.COMA, "coma");}
	";"					{imprimir(Symbols.PTOCOMA, "ptoComa");}
	"="					{imprimir(Symbols.EQUALS, "equals");}
	{LineTerminator}    {}
	{WhiteSpace}        {}
	"type"				{imprimir(Symbols.TYPE, "type");}
	"("					{imprimir(Symbols.OPENPAR, "openPar");}
	")"					{imprimir(Symbols.CLOSEPAR, "closePar");}
	"{"             	{imprimir(Symbols.OPENBRACKETS, "openbrackets");}
	"}"             	{imprimir(Symbols.CLOSEBRACKETS, "closebrackets");}
	"+"					|
	"*"					|
	"**"				|
	"/"					|
	"//"				|
	"%"					{imprimir(Symbols.OPER, "oper");}
	"-"					{imprimir(Symbols.MINUS, "minus");}
	{Comment}           {imprimir(0, "Comment");}
	{DecIntegerLiteral}	{imprimir(Symbols.NUM, "Num");}
	{DecFloat}			{imprimir(Symbols.FLOAT, "Float");}
	{bool}				{imprimir(Symbols.BOOL, "bool");}
	{Identifier}		{imprimir(Symbols.ID, "id");}
	
	[^]                 {imprimir(-99,"");}
	