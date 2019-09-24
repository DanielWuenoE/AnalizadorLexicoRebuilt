package Estructuras;

public class TestLista {
    
    ListasR al;
            
    TestLista() {
        al = new ListasR();
    }
    
    public void test(String token) {
        al.agregarElementoLSimbolosR(token, "Simb. Esp.", 9, 0, null, al.buscaRepR(token) + 1);
    }
    
    public void imp() {
        System.out.println(al.ExistePalabraT("0"));
        al.mostrarListaSimbolosR();
        System.out.println(al.buscaIdent("!"));
        System.out.println(al.ultimoEnFila());
    }
    
    public static void main(String[] args) {
        TestLista t = new TestLista();
//        t.test(":=");
//        t.test("!");
//        t.test("!");
//        t.test(":=");
//        t.test("#");
//        t.test("#");
//        t.test("!");
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