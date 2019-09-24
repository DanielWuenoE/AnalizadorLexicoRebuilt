package Sintactico;

import Estructuras.Pila;
import Gramatica.AcomodoGramatica;
import analizador.lexico.c.ClasificaRebuilt;

public class MatrizPredictiva {
    
    Pila pila;
    AcomodoGramatica gramatica;
    ClasificaRebuilt lexico;
    boolean error = false;
    
    MatrizPredictiva() {
        pila = new Pila();
        gramatica = new AcomodoGramatica();
        gramatica.ini();  // pedimos la gramatica y se trabaja
        lexico = new ClasificaRebuilt(); // pedimos el programa a analizar
    }
    
    public int matriz(int x, int y) {
        int[][] matrizPredictiva = {{ 1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                    { 0,  0,  2,  0,  0,  2,  0,  0,  2,  0,  0,  0,  0,  0},
                                    { 0,  4,  3,  0,  0,  3,  0,  0,  3,  0,  0,  0,  0,  0},
                                    { 0,  0,  5,  0,  0,  6,  0,  0,  7,  0,  0,  0,  0,  0},
                                    { 0,  0,  8,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                    { 0,  0,  0,  0,  0,  0,  0, 10,  0,  9,  0,  0,  0,  0},
                                    { 0,  0, 11,  0,  0,  0, 11,  0,  0,  0, 11,  0,  0,  0},
                                    { 0,  0,  0,  0, 12,  0,  0, 13,  0,  0,  0,  0,  0,  0},
                                    { 0,  0, 14,  0,  0,  0, 14,  0,  0,  0, 14,  0,  0,  0},
                                    { 0,  0,  0,  0, 16,  0,  0, 16,  0, 16,  0, 15, 15,  0},
                                    { 0,  0, 18,  0,  0,  0, 17,  0,  0,  0, 19,  0,  0,  0},
                                    { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 20, 21,  0},
                                    {22,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}};
        return matrizPredictiva[x][y];
    }
    
    public int obtenProduccionMatrizP(String x, String a) {
        int posX = gramatica.indiceNoTerminal(x); // 0
        int posY = gramatica.indiceTerminal(a);  // 0
        return matriz(posX, posY);
    }
    
    public boolean noEsTerminal(String buscar) {
        for (String buscarEn : gramatica.simbolosNoTerminales) {
            if (buscarEn != null)
                if (buscarEn.equals(buscar))
                    return true;
        }
        return false;
    }
    
    public void LlDiver() { //a sera el lexema enviado del analizador lexico
        pila.push(gramatica.simboloInicial());
        String x = pila.peak(); // tope de la pila
        System.out.println("x: "+x);
        String a = lexico.pedirToken(); // pedir la primer palabra
        System.out.println("a: "+a);
//                    System.out.print(a+" ");
        while(pila.isEmpty()) {
            if (noEsTerminal(x)) {
                if(obtenProduccionMatrizP(x, a) != 0) {
                    pila.pop(); //y un ciclo push();
                    cicloPush(obtenProduccionMatrizP(x, a)); // derecha a izquierda
                    x = pila.peak();
                    System.out.println("x: "+x);
                } else {
                    errorSintactico(a);
                    break;
                }
            } else {
                if(x.equals(a)) {
                    pila.pop();
                    x = pila.peak();
                    System.out.println("x: "+x);
                    a = lexico.pedirToken();
                    System.out.println("a: "+a);
//                    System.out.print(a+" ");
                } 
//                else if(x.equals("ε")) {
//                    pila.pop();
//                    pila.pop();
//                    x = pila.peak();
//                }
                else {
                    errorSintactico(a);
                    break;
                }
            }
        }
//        if(error == false){
//        if (!a.equals("$")) {
//            System.out.println("");
//            LlDiver();
//        } else
            System.out.println("Análisis terminado");
//        }
    }
    
    private void cicloPush(int produccion) {
        String[] deriva = gramatica.produccionDerecha(produccion);    // la produccion a ingresar
        int i = deriva.length;
        while (i != 0) {
            pila.push(deriva[i-1]);
            i=i-1;
        }
    }
    
    private void errorSintactico(String x) {
        if(!x.equals("$")){
            System.out.println("\nError sintactico en: " + x);
            error = true;
        }
    }
}

class test {
    public static void main(String[] args) {
        MatrizPredictiva m = new MatrizPredictiva();
        m.LlDiver();
        //m.obtenProduccionMatrizP(0, 7);
    }
}