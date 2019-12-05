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
        return al.inicioPostfija;
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
        t.test("!", "Sim. Esp.");
        t.imp();
        System.out.println("\n");
        
        System.out.println(t.tomaLaListaDelInicio().siguiente.tokenPosfija);
        System.out.println(t.tomaLaListaDelInicio().siguiente.siguiente.tokenPosfija);
        System.out.println(t.tomaLaListaDelInicio().siguiente.siguiente.anterior.tokenPosfija);
        
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