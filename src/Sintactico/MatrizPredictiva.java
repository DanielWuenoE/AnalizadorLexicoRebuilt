package Sintactico;

import Controlador.Monarquia;
import Estructuras.Pila;
import Gramatica.AcomodoGramatica;
import Lexico.ClasificaRebuilt;
//import Lexico.ClasificaRebuilt;

public class MatrizPredictiva {

    Pila pila;
    AcomodoGramatica gramatica;
    //ClasificaRebuilt lexico;
    Monarquia what;
    boolean error = false;

    MatrizPredictiva(Monarquia what) {
        pila = new Pila();
        gramatica = new AcomodoGramatica();
        gramatica.ini();  // pedimos la gramatica y se trabaja
        //what = new ClasificaRebuilt(); // pedimos el programa a analizar
        this.what = what;
    }

    public int matriz(int x, int y) {
        /* |beg|end| id| :=| ; |rea| ( | ) |wri| , |int| + | - | $| */
        int[][] matrizPredictiva = {{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    { 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0},
                                    { 0, 4, 3, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0},
                                    { 0, 0, 5, 0, 0, 6, 0, 0, 7, 0, 0, 0, 0, 0},
                                    { 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    { 0, 0, 0, 0, 0, 0, 0,10, 0, 9, 0, 0, 0, 0},
                                    { 0, 0,11, 0, 0, 0,11, 0, 0, 0,11, 0, 0, 0},
                                    { 0, 0, 0, 0,12, 0, 0,13, 0, 0, 0, 0, 0, 0},
                                    { 0, 0,14, 0, 0, 0,14, 0, 0, 0,14, 0, 0, 0},
                                    { 0, 0, 0, 0,16, 0, 0,16, 0,16, 0,15,15, 0},
                                    { 0, 0,18, 0, 0, 0,17, 0, 0, 0,19, 0, 0, 0},
                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,20,21, 0},
                                    {22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        return matrizPredictiva[x][y];
    }

    public int obtenProduccionMatrizP(String x, String a) {
        int posX = gramatica.indiceNoTerminal(x); // 0
        int posY = gramatica.indiceTerminal(a);  // 0
        return matriz(posX, posY);
    }

    public boolean noEsTerminal(String buscar) {
        for (String buscarEn : gramatica.simbolosNoTerminales) {
            if (buscarEn != null) {
                if (buscarEn.equals(buscar)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void LlDiver() { //a sera el lexema enviado del analizador what
        pila.push(gramatica.simboloInicial());
        String x = pila.peak(); // tope de la pila
//        System.out.println("x: "+x);
        String a = what.pedirToken(); // pedir la primer palabra
//        System.out.println("pedir token 1");
//        System.out.println("a: "+a);
//        System.out.println("inicia pila:");
//        pila.imprime();
//        System.out.println("termina pila");
//                    System.out.print(a+" ");
        while (pila.isEmpty()) {
            if (noEsTerminal(x)) {
                if (obtenProduccionMatrizP(x, a) != 0) {
//                    System.out.println("no-matriz: "+obtenProduccionMatrizP(x, a));
                    pila.pop(); //y un ciclo push();
                    cicloPush(obtenProduccionMatrizP(x, a)); // derecha a izquierda
//                            System.out.println("inicia pila:");
//        pila.imprime();
//        System.out.println("termina pila");
//                    System.out.println("despues");
                    x = pila.peak();
//                    System.out.println("x: "+x);
                } else {
//                    System.out.println("error 1");
                    errorSintactico(a);
                    what.imprimeTablas();
                    break;
                }
            } else {
                if (x.equals(a)) {
                    pila.pop();
                    x = pila.peak();
//                    System.out.println("x: "+x);
                    a = what.pedirToken();
//                    System.out.println("pedir token 2");
//                    System.out.println("a: "+a);
//                    System.out.print(a+" ");
                } else if (x.equals("ε")) {
                    pila.pop();
//                    pila.pop();
                    x = pila.peak();
//                    System.out.println("vacio x: "+x);
                } else {
//                    System.out.println("error 2");
                    errorSintactico(a);
                    what.imprimeTablas();
                    break;
                }
            }
        }
        if (error == false) {
            if (!a.equals("$")) {
                what.retroceder();
//        System.out.println("inicia pila:");
//        pila.imprime();
//        System.out.println("termina pila");
//                System.out.println("");
                LlDiver();
            } else {
                System.out.println("Análisis terminado");
                what.imprimeTablas();
            }
        }
    }

    private void cicloPush(int produccion) {
        String[] deriva = gramatica.produccionDerecha(produccion);    // la produccion a ingresar
        int i = deriva.length;
        while (i != 0) {
            pila.push(deriva[i - 1]);
            i = i - 1;
        }
    }

    private void errorSintactico(String x) {
        if (!x.equals("$")) {
            System.out.println("\nError sintactico en: " + x);
            error = true;
        }
    }
}

class test {

    public static void main(String[] args) {
        //MatrizPredictiva m = new MatrizPredictiva();
        //m.LlDiver();
        //m.obtenProduccionMatrizP(0, 7);
    }
}
