/* Generated By:JavaCC: Do not edit this line. SyntaxChecker.java */
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;


public class SyntaxChecker implements SyntaxCheckerConstants {

        private static int parentesis = 0;
        private static int mainDefinido = 0;
        private static int scope = 0;
        private static int inMain = 0;

    public static void main(String[] args) {
        try {
                        File file = new File("eje.txt");
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String st;
                        String in = "";
                        while ((st = br.readLine()) != null) {
                                in += st;
                                in += '\u005cn';
                        }
                        System.out.println(in);
            new SyntaxChecker(new java.io.StringReader(in)).INICIAR();
            System.out.println("Syntax is okay");
        } catch (Throwable e) {
            // Catching Throwable is ugly but JavaCC throws Error objects!
            System.out.println("Syntax check failed: " + e.getMessage());
        }
    }

        static void checkMainDefined(int llamada){
                if (mainDefinido == 0){
                        if (llamada ==  1){
                                System.out.println("Main method not defined");
                                ParseException e = generateParseException();
                                System.out.println(e.toString());
                        } else {
                                mainDefinido = 1;
                        }
                } else {
                        System.out.println("Main method already defined");
                        ParseException e = generateParseException();
                        System.out.println(e.toString());
                }
        }

        static void checkDefinicionProc(){
                if (scope != 0){
                        System.out.println("Illegal declaration of method");
                        ParseException e = generateParseException();
                        System.out.println(e.toString());
                }
        }

        static void checkMainDCL() {
                if (inMain == 1){
                        System.out.println("Illegal declaration in main method");
                        ParseException e = generateParseException();
                        System.out.println(e.toString());
                }
        }

  static final public void INICIAR() throws ParseException {
    Constantes();
    S();
  }

  static final public void S() throws ParseException {
    Sder();
  }

  static final public void Sder() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      Inicial();
      S();
      break;
    case 25:
      ifFunction();
      S();
      break;
    case 29:
      Delay_Function();
      S();
      break;
    case 32:
      Blink_Function();
      S();
      break;
    case 28:
      Procedure();
      S();
      break;
    case 27:
      Call();
      S();
      break;
    case 26:
      forFunction();
      S();
      break;
    default:
      jj_la1[0] = jj_gen;
      Empty();
    }
  }

  static final public void Inicial() throws ParseException {
    Identificadores();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 7:
      jj_consume_token(7);
      OperacionesListas();
      break;
    default:
      jj_la1[1] = jj_gen;
      Igualdad();
    }
    jj_consume_token(43);
  }

  static final public void Identificadores() throws ParseException {
                         Token id;
    id = jj_consume_token(ID);
        System.out.print("id: ");
        System.out.print(id.image);
        System.out.println("");
    IdentificadoresAux();
  }

  static final public void IdentificadoresAux() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      Identificadores();
      break;
    case 4:
      jj_consume_token(4);
                                                            System.out.println("="); checkMainDCL();
      break;
    default:
      jj_la1[3] = jj_gen;
      Listas();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 4:
        jj_consume_token(4);
                                                                                                                       System.out.println("="); checkMainDCL();
        break;
      default:
        jj_la1[2] = jj_gen;
        Empty();
      }
    }
  }

  static final public void IgualdadValoresOperables() throws ParseException {
                                  Token aux;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      aux = jj_consume_token(ID);
                                                           System.out.println(aux);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPERADORES:
        aux = jj_consume_token(OPERADORES);
                                                                                                                     System.out.println(aux);
        IgualdadAux();
        break;
      case 7:
        jj_consume_token(7);
        FuncionesShape();
        break;
      default:
        jj_la1[4] = jj_gen;
        Listas();
      }
      break;
    case NUM:
      aux = jj_consume_token(NUM);
                                                                       System.out.println(aux);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPERADORES:
        aux = jj_consume_token(OPERADORES);
                                                                                                                      System.out.println(aux);
        IgualdadAux();
        break;
      default:
        jj_la1[5] = jj_gen;
        Empty();
      }
      break;
    case 1:
      aux = jj_consume_token(1);
                                                                     System.out.println(aux);
      IgualdadAux();
      jj_consume_token(2);
                                                                                                                  System.out.println(")");
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPERADORES:
        aux = jj_consume_token(OPERADORES);
                                                                                                      System.out.println(aux);
        IgualdadValoresOperables();
        break;
      default:
        jj_la1[6] = jj_gen;
        IgualdadValoresOperables();
      }
      break;
    default:
      jj_la1[7] = jj_gen;
      Empty();
    }
  }

  static final public void IgualdadAux() throws ParseException {
                     Token aux;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOL:
      aux = jj_consume_token(BOOL);
                                                  System.out.println(aux);
      break;
    case 1:
      aux = jj_consume_token(1);
                                                                                     System.out.println(aux);
      IgualdadValoresOperables();
      jj_consume_token(2);
                                                                                                                                               System.out.println(")");
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPERADORES:
        aux = jj_consume_token(OPERADORES);
                                                                                                                                      System.out.println(aux);
        IgualdadValoresOperables();
        break;
      default:
        jj_la1[8] = jj_gen;
        IgualdadValoresOperables();
      }
      break;
    case 44:
      aux = jj_consume_token(44);
                                                                                      System.out.println(aux);
      ValoresListas();
      jj_consume_token(45);
                                                                                                                                     System.out.println("]");
      break;
    case LIST:
      aux = jj_consume_token(LIST);
                                                                                        System.out.println(aux);
      CrearLista();
      Listas();
      break;
    case LENGTH:
      FuncionLen();
      break;
    default:
      jj_la1[9] = jj_gen;
      IgualdadValoresOperables();
    }
  }

  static final public void Igualdad() throws ParseException {
                  Token aux;
    IgualdadAux();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      Igualdad();
      break;
    default:
      jj_la1[10] = jj_gen;
      Empty();
    }
  }

  static final public void Listas() throws ParseException {
                Token aux;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 44:
      aux = jj_consume_token(44);
                                         System.out.println(aux);
      AccesoLista();
      jj_consume_token(45);
                                                                                      System.out.println("]");
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 7:
        jj_consume_token(7);
        FuncionesListas();
        break;
      default:
        jj_la1[11] = jj_gen;
        Empty();
      }
      Listas();
      break;
    case LIST:
      aux = jj_consume_token(LIST);
                                                                        System.out.println(aux);
      CrearLista();
      Listas();
      break;
    default:
      jj_la1[12] = jj_gen;
      Empty();
    }
  }

  static final public void AccesoLista() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 46:
      jj_consume_token(46);
      jj_consume_token(3);
      Numeros();
      break;
    case NUM:
    case LENGTH:
    case ID:
      Numeros();
      AccesoListaAux();
      break;
    default:
      jj_la1[13] = jj_gen;
      ValoresListas();
    }
  }

  static final public void AccesoListaAux() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 46:
      jj_consume_token(46);
      Numeros();
      break;
    case 3:
      jj_consume_token(3);
      Numeros();
      break;
    case NUM:
    case LENGTH:
    case ID:
      Numeros();
      break;
    default:
      jj_la1[14] = jj_gen;
      Empty();
    }
  }

  static final public void ValoresListas() throws ParseException {
                       Token aux;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOL:
      aux = jj_consume_token(BOOL);
                                                   System.out.println(aux);
      ValoresListasAux();
      break;
    default:
      jj_la1[15] = jj_gen;
      Listas();
      ValoresListasAux();
    }
  }

  static final public void ValoresListasAux() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      ValoresListas();
      break;
    default:
      jj_la1[16] = jj_gen;
      Empty();
    }
  }

  static final public void FuncionesListas() throws ParseException {
                         Token aux;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NEG:
      aux = jj_consume_token(NEG);
      break;
    case T:
      aux = jj_consume_token(T);
      break;
    case F:
      aux = jj_consume_token(F);
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                                                                             System.out.println(aux);
  }

  static final public void CrearLista() throws ParseException {
                    Token aux;
    jj_consume_token(1);
    aux = jj_consume_token(RANGO);
    jj_consume_token(1);
                                                         System.out.println(aux);
    RangeParam();
  }

  static final public void RangeParam() throws ParseException {
                    Token n;
    Numeros();
    RangeParamVal();
  }

  static final public void RangeParamVal() throws ParseException {
                       Token val;
    jj_consume_token(3);
    val = jj_consume_token(BOOL);
                                                       System.out.println(val.image);
    jj_consume_token(2);
    jj_consume_token(2);
  }

  static final public void OperacionesListas() throws ParseException {
                           Token aux;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INSERT:
      aux = jj_consume_token(INSERT);
                                                         System.out.println(aux.image);
      jj_consume_token(1);
      valoresInsert();
      jj_consume_token(2);
      break;
    case DELETE:
      aux = jj_consume_token(DELETE);
                                                                                                  System.out.println(aux.image);
      jj_consume_token(1);
      valoresDel();
      jj_consume_token(2);
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void valoresInsert() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
    case LIST:
    case 44:
      Listas();
      InsertMatriz();
      break;
    case NUM:
    case LENGTH:
    case ID:
      InsertListas();
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void InsertMatriz() throws ParseException {
    Listas();
    jj_consume_token(3);
    Numeros();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      Numeros();
      break;
    default:
      jj_la1[20] = jj_gen;
      Empty();
    }
  }

  static final public void valoresDel() throws ParseException {
    Numeros();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      Numeros();
      break;
    default:
      jj_la1[21] = jj_gen;
      Empty();
    }
  }

  static final public void InsertListas() throws ParseException {
                      Token val;
    Numeros();
    jj_consume_token(3);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOL:
      val = jj_consume_token(BOOL);
                                                                 System.out.println(val.image);
      break;
    default:
      jj_la1[22] = jj_gen;
      Listas();
    }
  }

  static final public void Numeros() throws ParseException {
                 Token index;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
    case ID:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUM:
        index = jj_consume_token(NUM);
        break;
      case ID:
        index = jj_consume_token(ID);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 7:
          jj_consume_token(7);
          FuncionesShape();
          break;
        default:
          jj_la1[23] = jj_gen;
          Empty();
        }
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                                                                                                  System.out.println(index.image);
      break;
    case LENGTH:
      FuncionLen();
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void FuncionesShape() throws ParseException {
                        Token aux;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SHAPEC:
      aux = jj_consume_token(SHAPEC);
      break;
    case SHAPEF:
      aux = jj_consume_token(SHAPEF);
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                                                                         System.out.println(aux.image);
  }

  static final public void FuncionLen() throws ParseException {
                    Token aux;
    aux = jj_consume_token(LENGTH);
                                                  System.out.println(aux.image);
    jj_consume_token(1);
    jj_consume_token(ID);
    Listas();
    jj_consume_token(2);
  }

  static final public void Iterable() throws ParseException {
                 Token aux;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      aux = jj_consume_token(ID);
                                          System.out.println(aux.image);
      Iterable_Aux();
      break;
    case NUM:
      jj_consume_token(NUM);
      break;
    case LENGTH:
      FuncionLen();
      break;
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Iterable_Aux() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 44:
      jj_consume_token(44);
                               System.out.print("[");
      Adentro_Lista();
      jj_consume_token(45);
                                                                              System.out.print("]");
      Iterable_Aux();
      break;
    case 7:
      jj_consume_token(7);
      FuncionesShape();
      break;
    default:
      jj_la1[28] = jj_gen;
      Empty();
    }
  }

  static final public void ifFunction() throws ParseException {
                    Token aux;
    aux = jj_consume_token(25);
                                              System.out.println(aux);
    Iterable();
    aux = jj_consume_token(OPERADOR_COMPARADOR);
                                                                                                                System.out.println(aux);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOL:
      aux = jj_consume_token(BOOL);
                                                                               System.out.println(aux);
      break;
    case NUM:
    case LENGTH:
    case ID:
      Iterable();
      break;
    default:
      jj_la1[29] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(5);
    S();
    jj_consume_token(6);
  }

  static final public void Constantes() throws ParseException {
    Timer();
    Rango_Timer();
    Dim_Filas();
    Dim_Columnas();
    Cubo();
  }

  static final public void Timer() throws ParseException {
               Token n;
    jj_consume_token(TIMER);
    jj_consume_token(4);
    n = jj_consume_token(NUM);
    jj_consume_token(43);
        System.out.print("Timer: ");
        System.out.print(n.image);
        System.out.println("");
  }

  static final public void Rango_Timer() throws ParseException {
                     Token rango;
    jj_consume_token(RANGO_TIMER);
    jj_consume_token(4);
    rango = jj_consume_token(OPCIONESRANGO);
    jj_consume_token(43);
        System.out.print("Rango_Timer: ");
        System.out.print(rango.image);
        System.out.println("");
  }

  static final public void Dim_Filas() throws ParseException {
                   Token filas;
    jj_consume_token(DIM_FILAS);
    jj_consume_token(4);
    filas = jj_consume_token(NUM);
    jj_consume_token(43);
        System.out.print("Filas: ");
        System.out.print(filas.image);
        System.out.println("");
  }

  static final public void Dim_Columnas() throws ParseException {
                      Token columnas;
    jj_consume_token(DIM_COLUMNAS);
    jj_consume_token(4);
    columnas = jj_consume_token(NUM);
    jj_consume_token(43);
        System.out.print("Columnas: ");
        System.out.print(columnas.image);
        System.out.println("");
  }

  static final public void Cubo() throws ParseException {
              Token i; Token j;
    jj_consume_token(CUBO);
    jj_consume_token(4);
    i = jj_consume_token(NUM);
    jj_consume_token(3);
    j = jj_consume_token(NUM);
    jj_consume_token(43);
        System.out.print("Cubo: ");
        System.out.print(i);
        System.out.print(", ");
        System.out.print(j);
        System.out.println("");
  }

  static final public void Empty() throws ParseException {

  }

  static final public void Delay_Function() throws ParseException {
                              System.out.println("Delay");
    jj_consume_token(29);
    jj_consume_token(1);
    Delay_Expression();
    jj_consume_token(2);
    jj_consume_token(43);
  }

  static final public void Delay_Expression() throws ParseException {
                          Token num; Token range;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
      num = jj_consume_token(NUM);
      jj_consume_token(3);
      range = jj_consume_token(OPCIONESRANGO);
                System.out.println("(");
                System.out.println(num);
                System.out.println(",");
                System.out.println(range);
                System.out.println(")");
      break;
    default:
      jj_la1[30] = jj_gen;
      Empty();
                System.out.println("(");
                System.out.println(")");
    }
  }

  static final public void Blink_Function() throws ParseException {
                             System.out.println("Blink");
    jj_consume_token(32);
    jj_consume_token(1);
                                                                           System.out.println("(");
    Blink_Expression();
    jj_consume_token(2);
                                                                                                                               System.out.println(")");
    jj_consume_token(43);
  }

  static final public void Blink_Expression() throws ParseException {
                          Token id; Token bool;
    id = jj_consume_token(ID);
                                                            System.out.println(id);
    jj_consume_token(44);
                                                                                            System.out.println("[");
    Adentro_Lista();
    jj_consume_token(45);
                                                                                                                                             System.out.println("]");
    jj_consume_token(3);
                                                                                                                                                                              System.out.println(",");
    Blink_Expression_Aux();
  }

  static final public void Blink_Expression_Aux() throws ParseException {
                              Token bool; Token num;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
      num = jj_consume_token(NUM);
                                                                  System.out.println(num);
      Blink_Expression_Aux1();
      break;
    case BOOL:
      bool = jj_consume_token(BOOL);
                                                                                                                                     System.out.println(bool);
      break;
    default:
      jj_la1[31] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Blink_Expression_Aux1() throws ParseException {
                               Token range; Token bool;
    jj_consume_token(3);
                                                               System.out.println(",");
    range = jj_consume_token(OPCIONESRANGO);
                                                                                                                   System.out.println(range);
    jj_consume_token(3);
                                                                                                                                                      System.out.println(",");
    bool = jj_consume_token(BOOL);
                                                                                                                                                                                               System.out.println(bool);
  }

  static final public void Adentro_Lista() throws ParseException {
                       Token num;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
      num = jj_consume_token(NUM);
      break;
    case 46:
      num = jj_consume_token(46);
      break;
    default:
      jj_la1[32] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                                                              System.out.println(num);
    Adentro_Lista_Aux1();
  }

  static final public void Adentro_Lista_Aux1() throws ParseException {
                            Token num;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 46:
      jj_consume_token(46);
                                               System.out.println(":");
      num = jj_consume_token(NUM);
                                                                                     System.out.println(num);
      break;
    case 3:
      jj_consume_token(3);
      num = jj_consume_token(NUM);
                                                                                                                                  System.out.println(num);
      break;
    default:
      jj_la1[33] = jj_gen;
      Empty();
    }
  }

  static final public void Procedure() throws ParseException {
    jj_consume_token(28);
                                    System.out.println("Procedure"); checkDefinicionProc();
    NombreRutina();
  }

  static final public void NombreRutina() throws ParseException {
                     Token id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      id = jj_consume_token(ID);
                                           System.out.println(id);
      normalProc();
      break;
    case MAIN:
      jj_consume_token(MAIN);
                                                                                             System.out.println("Main"); checkMainDefined(0); inMain = 1;
      mainProc();
      break;
    default:
      jj_la1[34] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void normalProc() throws ParseException {
    jj_consume_token(1);
    Parametros();
    jj_consume_token(2);
    jj_consume_token(5);
                                                 System.out.println("Scope aumenta"); scope = 1;
    S();
    jj_consume_token(6);
                                                                                                           scope = 0;
  }

  static final public void mainProc() throws ParseException {
    jj_consume_token(1);
    jj_consume_token(2);
    jj_consume_token(5);
                                  scope = 1;
    S();
    jj_consume_token(6);
                                                       inMain = 0; scope = 0;
  }

  static final public void Call() throws ParseException {
             Token id;
    jj_consume_token(27);
                                  System.out.println("Call");
    id = jj_consume_token(ID);
                                                                  System.out.print(id);
    jj_consume_token(1);
    Igualdad();
    jj_consume_token(2);
    jj_consume_token(43);
  }

  static final public void Parametros() throws ParseException {
                    Token id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      id = jj_consume_token(ID);
                                            System.out.println(id);
      ParametrosAux();
      break;
    default:
      jj_la1[35] = jj_gen;
      Empty();
    }
  }

  static final public void ParametrosAux() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      ParametrosAux1();
      break;
    default:
      jj_la1[36] = jj_gen;
      Empty();
    }
  }

  static final public void ParametrosAux1() throws ParseException {
                        Token id;
    id = jj_consume_token(ID);
                                               System.out.println(id);
    ParametrosAux();
  }

  static final public void forFunction() throws ParseException {
                    Token id;
    jj_consume_token(26);
                                        System.out.println("for");
    id = jj_consume_token(ID);
                                                                               System.out.print(id);
    jj_consume_token(30);
                                                                                                               System.out.print("in");
    Iterable();
    Step();
    jj_consume_token(5);
                                                                                                                                                                  System.out.println("{");
    S();
    jj_consume_token(6);
                                                                                                                                                                                                       System.out.println("}");
  }

  static final public void Step() throws ParseException {
             Token num;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 31:
      jj_consume_token(31);
                                   System.out.print("Step");
      num = jj_consume_token(NUM);
                                                                           System.out.print(num);
      break;
    default:
      jj_la1[37] = jj_gen;
      Empty();
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public SyntaxCheckerTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[38];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x3e000000,0x80,0x10,0x18,0x80,0x0,0x0,0x102,0x0,0x1010202,0x8,0x80,0x10000,0x1000100,0x1000108,0x200,0x8,0x380000,0xc00000,0x1010108,0x8,0x8,0x200,0x80,0x100,0x1000100,0x0,0x1000100,0x80,0x1000300,0x100,0x300,0x100,0x8,0x20000,0x0,0x8,0x80000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x9,0x0,0x0,0x0,0x10,0x10,0x10,0x8,0x10,0x1000,0x0,0x0,0x1000,0x4008,0x4008,0x0,0x0,0x0,0x0,0x1008,0x0,0x0,0x0,0x0,0x8,0x8,0x6,0x8,0x1000,0x8,0x0,0x0,0x4000,0x4000,0x8,0x8,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public SyntaxChecker(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SyntaxChecker(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new SyntaxCheckerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 38; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 38; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public SyntaxChecker(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SyntaxCheckerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 38; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 38; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public SyntaxChecker(SyntaxCheckerTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 38; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(SyntaxCheckerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 38; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[47];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 38; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 47; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
