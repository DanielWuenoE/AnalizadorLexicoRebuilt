package Estructuras;

public class TestLista {
    
    ListasR al;
            
    TestLista() {
        al = new ListasR();
    }
    
    public void test(String token, String tipo) {
        al.agregarElementoLPostfija(token, tipo);
    }
    
    public void imp() {
        System.out.println(al.ExistePalabraT("0"));
        al.mostrarListaPostfija();
        System.out.println(al.buscaIdent("!"));
        System.out.println(al.ultimoEnFila());
    }
    
    private void avanza() {
        //ListasR.NodoTPostfija = al.inicioPostfija;
    }
    
    public static void main(String[] args) {
        TestLista t = new TestLista();
        t.test(":=", "Identificador");
        t.test("3", "Sim. Esp.");
        t.test("2", "Identificador");
        t.test("#", "Sim. Esp.");
        t.test("7", "Identificador");
        t.test("!", "Sim. Esp.");
        t.imp();
        
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