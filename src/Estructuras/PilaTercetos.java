package Estructuras;

import Parseo.TercetosE;

public class PilaTercetos {
    private NodoTercetos inicio, fin;
    
    public PilaTercetos() {
        inicio = null;
    }
    
    public class NodoTercetos {
        NodoTercetos sig;
        TercetosE dato;
        
        public NodoTercetos(TercetosE dato) {
            this.dato = dato;
        }
        
        public void toTercetosE() { //conversion de posicion de memoria a string
            dato.mostrarTerceto();
        }
    }
    
    public void push(TercetosE dato) {
        NodoTercetos nuevo = new NodoTercetos(dato);
        push(nuevo);
    }
    
    private void push(NodoTercetos dato) {
        if (inicio != null) {
            dato.sig = inicio;
            inicio = dato;
        } else
            inicio = fin = dato;
    }
    
    public void pop() {
        NodoTercetos temp = inicio;
        if (inicio != null) {
            inicio = temp.sig;
            temp.sig = null;
        } else 
            System.out.println("Pila vacia");
    }
    
    public TercetosE popConRetorno() {
        NodoTercetos temp = inicio;
        TercetosE t;
        if (inicio != null) {
            t = inicio.dato;
            inicio = temp.sig;
            temp.sig = null;
            return t;
        } else {
            System.out.println("Pila vacia");
            return null;
        }
    }
    
    public TercetosE peak() {
        if (inicio != null) {
            return  inicio.dato;
        } else
            return null;
    }
    
    public TercetosE peek() {
        if (inicio != null) {
            return  inicio.dato;
        } else
            return null;
    }
    
    public boolean isEmpty() {
        return inicio != null;
    }
    
    public void imprimeTerceto() {
        NodoTercetos temp = inicio;
        while (temp != null) {
            temp.dato.mostrarTerceto();
            temp = temp.sig;
        }
    }
    
    public void imprimeCuarteto() {
        NodoTercetos temp = inicio;
        while (temp != null) {
            temp.dato.mostrarCuarteto();
            temp = temp.sig;
        }
    }
    
    public PilaTercetos invierte(PilaTercetos p) {
        PilaTercetos invertida = new PilaTercetos();

        while(p.isEmpty()) {
            invertida.push(p.popConRetorno());
        }
        return invertida;
    }
    
    public String armaCadenaCuartetos(PilaTercetos pila) {
        String cuartetos = "";
        NodoTercetos temp = pila.inicio;
        while (temp != null) {
            cuartetos += temp.dato.regresaCuarteto() + "\n";
            temp = temp.sig;
        }
        return cuartetos;
    }
}

class testT {
//    public static void main(TercetosE[] args) {
//        Pila p = new Pila();
//        p.push("1");
//        p.push("2");
//        p.push("3");
//        p.push("4");
//        System.out.println(":" + p.peak());
//        p.pop();
//        p.imprime();
//    }
}