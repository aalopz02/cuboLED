package sintaxAnalysis;

import Estructuras.Nodo;
import Estructuras.Grafo;
import java.io.*;
import java.util.ArrayList;


public class Creatorpy {

    //tablaVariables.imprimirIDS();
    public static void Crear( Nodo aux) {
        while (aux != null) {
            //System.out.println("Tipo: " + aux.getTipo());
            //System.out.println("Contenido: " + aux.getContenido());

            aux = aux.getNext();

            /* Agregar .py al grafo*/
            FileWriter fichero = null;
            PrintWriter pw = null;
            try {
                fichero = new FileWriter("C:/Users/1001001164/Documents/GitHub/cuboLED/cuboLEDJCC/src/sintaxAnalysis/prueba.txt");
                pw = new PrintWriter(fichero);

                while (aux != null) {

                    if (aux.getTipo().equals("ENDLINE")) {
                        pw.println(" ");
                        aux = aux.getNext();
                    }else{
                        pw.print(aux.getContenido());
                        aux = aux.getNext();

                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                        /*  aprovechamos el finally
                        para asegurarnos que se cierra el fichero.*/
                    if (null != fichero)
                        fichero.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
