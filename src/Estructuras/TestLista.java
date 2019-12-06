package Estructuras;

import Estructuras.ListasR.NodoTPostfija;

public class TestLista {
    
    ListasR al, NodoTPostfija;
            
    TestLista() {
        al = new ListasR();
    }
    
    public void test(String token, String tipo) {
        al.agregarElementoLPostfija(token, tipo);
    }
    
    public NodoTPostfija tomaLaListaDelInicio() {
        return al.tomaLaListaDelInicio();
    }
    
    public NodoTPostfija tomaLaListaDelFin() {
        return al.tomaLaListaDelFin();
    }
    
    public void borra(int b) {
        al.borrar_x(b);
    }
    
    public void ingresa(String d, String t, int i) {
        al.insertar_x(d, t, i);
    }
    
    public void imp() {
        //System.out.println(al.ExistePalabraT("0"));
        al.mostrarListaPostfija();
        //System.out.println(al.buscaIdent("!"));
        //System.out.println(al.ultimoEnFila());
    }
    
    public static void main(String[] args) {
        TestLista t = new TestLista();
        t.test(":=", "Identificador");
        t.test("!", "Sim. Esp.");
        t.test("{", "Identificador");
        t.test(":=", "Sim. Esp.");
        t.test("#", "Sim. Esp.");
        t.test("#", "Identificador");
        t.test("¿", "Sim. Esp.");
        t.imp();
        System.out.println("\n");
        
        //t.NodoTPostfija.borrar_x(3);
        t.NodoTPostfija.insertar_x("ss", "os", 9);
        
        String dk = t.tomaLaListaDelFin().tokenPosfija;
        
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