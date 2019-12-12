package Estructuras;

import Estructuras.ListasR.NodoTToken;

public class TestLista {
    
    ListasR al, NodoTPostfija;
            
    TestLista() {
        al = new ListasR();
    }
    
    public void test(String token, String tipo, int s) {
        al.agregarElementoLTokensR(token, tipo, s);
    }
    
    public NodoTToken tomaLaListaDelInicio() {
        return al.inicioTok;
    }
    
    public NodoTToken tomaLaListaDelFin() {
        return al.finTok;
    }
    
    public void borra(int b) {
        al.borraTokenPostfija(b);
    }
    
    public void ingresa(String d, String t, int i) {
        al.insertaTokenPostfija(d, t, i);
    }
    
    public void imp() {
        //System.out.println(al.ExistePalabraT("0"));
        al.mostrarListaTokensPostfija();
        //System.out.println(al.buscaIdent("!"));
        //System.out.println(al.ultimoEnFila());
    }
    
    public static void main(String[] args) {
        TestLista t = new TestLista();
        t.test(":=", "Identificador", 1);
        t.test("!", "Sim. Esp.", 2);
        t.test("{", "Identificador", 3);
        t.test(":=", "Sim. Esp.", 4);
        t.test("+", "Sim. Esp.", 5);
        t.test("#", "Identificador", 6);
        t.test("¿", "Sim. Esp.", 7);
        t.imp();
        System.out.println("\n");
        
//        t.borra(-1);
//        t.borra(3);
//        t.borra(1);
        t.ingresa("ss", "os", 7);
//        t.ingresa("ss", "os", -1);
        
        t.imp();
        System.out.println("\n");
        
        NodoTToken c = t.tomaLaListaDelFin();
        
        while (c!= null) {
            System.out.println(c.palabra);
            c = c.anterior;
        }
        
//        System.out.println(palabra);
        
        
//        char c[] = {
//                    ',',
//                    '(',
//                    ')',
//                    '+',
//                    '-',
//                    ':',
//                    ';',
//                    '='};
//            for (char ci : c)
//                System.out.println( Integer.valueOf(ci) );
    }
}