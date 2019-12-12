package Parseo;

//import Estructuras.ListasR.NodoTPostfija;
import Estructuras.ListasR.NodoTToken;
import Estructuras.ListasR;
import Parseo.Terceto;

public class Simplificacion {

    Terceto terceto = new Terceto();
    NodoTToken t;
    ListasR s;

    public Simplificacion(ListasR tabla) {
        this.s = tabla;
    }

    public void simplificacionSentencia() {
        t = s.finTok;
//        System.out.println("token inicio: "+s.inicioTok.palabra);
//        System.out.println(t.tokenPosfija);
        //String simple = "";
        int nodos = contarNodos(s);
        int op = numeroOperadores(s), op1 = op, op2 = 0, c = 0;      //busqueda de operadores en sentencia
//        System.out.println("token fin: " + t.tokenPosfija);
        System.out.println("token fin: " + t.palabra);
//        s = invertirCadena(s);                      //inversion de la cadena
        while (t != null) {      //revision de sentenciad
            System.out.println("Ingresando al while");
            c++;
            System.out.println("op: " + op);
            System.out.println("op2: " + op2);
            System.out.println("token actual: " + t.palabra);
            if (t.tipoTok.equals("Simb. Esp.")) {
                op--;

                if (op == op2) {    //si el contador es la posicion buscada
                    System.out.println("\u001B[32m Ingresando condicion operador");
                    op2++;      //aumenta posicion
//                    System.out.println(t.anterior.tipoTok);
//                    System.out.println(t.anterior.anterior.tipoTok);

                    if ((t.anterior.tipoTok.trim().equals("Números") && t.anterior.anterior.tipoTok.trim().equals("Números"))) {     //si hay dos numeros despues del signo
                        System.out.println("\u001B[31m Ingresando condicion numeros");
                        terceto.Terceto(t.palabra, t.anterior.palabra, t.anterior.anterior.palabra, "t" + op);
                        System.out.println("Termino insercion en pila");
                        if (numeroOperadores(s) != 1) {
                            s.borraTokenPostfija(nodos - (c - 1));
                            s.borraTokenPostfija(nodos - (c));
                            s.borraTokenPostfija(nodos - (c + 1));
                            s.insertaTokenPostfija("t" + op, "Números", c - 2);
                        }
                        System.out.println("\u001B[33m Borrado e insercion");
                        terceto.imprimeTodo();

                        t = s.finTok;
                        op = op1;
                        System.out.println("\u001B[35m Inicia lista");
                        s.mostrarListaTokensPostfija();
                        System.out.println("\u001B[35m Termina lista");
                    }
                } else {
                    t = t.anterior;
                }
            } else if (t.tipoTok.equals("Números")) {
                System.out.println("es un numero");
                t = t.anterior;
            } else {
                System.out.println("es de tipo: " + t.tipoTok);
                t = t.anterior;
                System.out.println("op: " + op);
            }
        }

    }

    public void imprimeTodo() {
//        terceto.Terceto("9", "98", "83");
        terceto.imprimeTodo();
    }

    public void ImprimirListaActual() {
        t = s.inicioTok;
        while (t != null) {
            System.out.println(t.palabra + "\t"
                    + t.tipoTok + "\t");
            t = t.siguiente;
        }
    }

    public int contarNodos(ListasR l) {
        NodoTToken t = l.inicioTok;
        int count = 0;
        while (t != null) {
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
        NodoTToken a = e.inicioTok;
        int contador = 0;
        while (a != null) {      //NOTA: CAMBIAR LENGHT POR TOKENS DE LISTA
            if (a.tipoTok.equalsIgnoreCase("Simb. Esp.") && a.tipoTok != null) {
                contador++;
            }
            a = a.siguiente;
        }
//        System.out.println(contador);
        return contador;
    }

    public static void main(String[] args) {
//        Simplificacion tabla = new Simplificacion();
//
//        ListasR t = new ListasR();
//        ClasificaRebuilt c = new ClasificaRebuilt(t);
//        ShuntingYard ap = new ShuntingYard(t, c);

//        s.invertirCadena("cad-e+n:=a++");
//        s.numeroOperadores("cad-e+n:=a++");
    }
}
