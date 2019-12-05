package Parseo;

import Estructuras.PilaTercetos;

public class Terceto {
    PilaTercetos pilaT = new PilaTercetos();
    
    public void Terceto(String elm1, String elm2, String elm3) {
        pilaT.push(new TercetosE(elm1, elm2, elm3));
    }
    
    public void Terceto(String elm1, String elm2, String elm3, String elm4) {
        pilaT.push(new TercetosE(elm1, elm2, elm3, elm4));
    }
    
    public void imprimeTodo() {
        pilaT.imprime();
    }
}