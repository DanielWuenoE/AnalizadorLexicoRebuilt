package Parseo;

import Estructuras.ListasR.NodoTPostfija;
import Estructuras.ListasR;

public class Simplificacion {
    
    NodoTPostfija t;
            


    public void simplificacionSentencia(ListasR s) {
        t = s.tomaLaListaDelFin();
        String simple = "";
        int nodos = contarNodos(s);
        int op = numeroOperadores(s), op1 = op, op2 = 0;      //busqueda de operadores en sentencia
//        s = invertirCadena(s);                      //inversion de la cadena
        while (t.anterior != null) {      //revision de sentenciad
            
            if (op == op2) {    //si el contador es la posicion buscada
                op2++;      //aumenta posicion
                if ((t.anterior.tipoToken.equals("Numero")||t.anterior.tipoToken.equals("Identificador"))
                        && (t.anterior.anterior.tipoToken.equals("Numero")||t.anterior.anterior.tipoToken.equals("Identificador"))) {     //si hay dos numeros despues del signo
                    
                }
            }else{
                t = t.anterior;
            }
        }
    }
    
    public int contarNodos(ListasR l){
        NodoTPostfija t = l.tomaLaListaDelInicio();
        int count = 0;
        while (t.siguiente != null) {
            count++;
            t = t.siguiente;
        }
        return count;
    }
//    
//    NodoTPostfija t;
//            
//
//
//    public void simplificacionSentencia(ListasR s) {
//        t = s.inicioPostfija.tokenPosfija;
//        String simple = "";
//        int op = numeroOperadores(s), op2 = 0;      //busqueda de operadores en sentencia
////        s = invertirCadena(s);                      //inversion de la cadena
//        for (int i = 0; i < s.length(); i++) {      //revision de sentenciad
//            
//            if (op == op2) {    //si el contador es la posicion buscada
//                op2++;      //aumenta posicion
//                if () {     //si hay dos numeros despues del signo
//
//                } else if () {   //si hay un numero antes y uno despues (crear v. temp.)
//
//                }
//            }
//        }
//    }

    public int numeroOperadores(ListasR e) {
        NodoTPostfija a = e.tomaLaListaDelInicio();
        int contador = 0;
        while (a.siguiente != null) {      //NOTA: CAMBIAR LENGHT POR TOKENS DE LISTA
            if (a.tipoToken.equalsIgnoreCase("Simb. Esp.")) {
                contador++;
            }
            a = a.siguiente;
        }
//        System.out.println(contador);
        return contador;
    }

    public static void main(String[] args) {
//        Simplificacion s = new Simplificacion();
//        s.invertirCadena("cad-e+n:=a++");
//        s.numeroOperadores("cad-e+n:=a++");
//    }
}
