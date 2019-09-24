package Estructuras;

public class TestLista {
    
    ListasR al;
            
    TestLista() {
        al = new ListasR();
    }
    
    public void test(String token, String tipo) {
        al.agregarElementoLSimbolosR(token, tipo, (int)(token.charAt(0)), 0, null, al.buscaRepR(token) + 1);
    }
    
    public void imp() {
        System.out.println(al.ExistePalabraT("0"));
        al.mostrarListaSimbolosR();
        System.out.println(al.buscaIdent("!"));
        System.out.println(al.ultimoEnFila());
    }
    
    public static void main(String[] args) {
        TestLista t = new TestLista();
        t.test(":=", "Identificador");
        t.test("!", "Sim. Esp.");
        t.test("!", "Identificador");
        t.test(":=", "Sim. Esp.");
        t.test("#", "Sim. Esp.");
        t.test("#", "Identificador");
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