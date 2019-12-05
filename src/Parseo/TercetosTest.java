package Parseo;

import Estructuras.PilaTercetos;

public class TercetosTest {
    public static void main(String[] args) {
        PilaTercetos p = new PilaTercetos();
        p.push(new TercetosE("3", "9", "7"));
        p.push(new TercetosE("1", "1", "8"));
        p.push(new TercetosE("5", "7", "3"));
        
        TercetosE t1 = p.popConRetorno();
        p.peek().mostrarTerceto();
        System.out.println(p.peek().getElem3().toString());
        
        System.out.println(t1.getElem1() + ", " + t1.getElem2() + ", " + t1.getElem3());
        p.imprime();
    }
}