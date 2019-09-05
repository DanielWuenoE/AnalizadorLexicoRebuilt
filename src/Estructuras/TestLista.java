package Estructuras;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class TestLista {
    
    public void test() {
        Listas al = new Listas();
        al.agregarElementoLErrores("un error");
        al.agregarElementoLErrores("dos error");
        al.agregarElementoLSimbolos("int", "folder", 10);
        al.agregarElementoLSimbolos("float", "cub", 20.9);
        al.agregarElementoLTokens("fileR", "identificador", 201);
        al.agregarElementoLTokens(20.9, "float", 201);
        al.agregarElementoLReservadas("boolean", 300);
        al.agregarElementoLReservadas("int", 500);
        
        al.mostrarListaReservadas();
    }
    
    public static void main(String[] args) {
        TestLista t = new TestLista();
        //t.test();
        char c[] = {
                    ',',
                    '(',
                    ')',
                    '+',
                    '-',
                    ':',
                    ';',
                    '='};
            for (char ci : c)
                System.out.println( Integer.valueOf(ci) );
    }
}