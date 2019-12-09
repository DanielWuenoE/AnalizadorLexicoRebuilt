package Parseo;

import Errores.ParentesisAperturaException;
import Errores.ParentesisCierreException;
import Estructuras.ListasR;
import Estructuras.Pila;
import Lexico.ClasificaRebuilt;
import Lexico.ConversionCaracter;
import Lexico.Tipos;
import Errores.ErrorGenerico;
import Parseo.Terceto;

public class ShuntingYard {
    /* Expresión
            (2*((3*4)+9))
    */
    
    //ListasR tablas = new ListasR();
    //ClasificaRebuilt lexico = new ClasificaRebuilt(tablas);
    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    Pila pila = new Pila();
    ClasificaRebuilt lexico;
    ListasR tabla;
    
    public ShuntingYard(ListasR tabla, ClasificaRebuilt lexico) {
        this.lexico = lexico;
        this.tabla = tabla;
    }
    
    public void separarTokens() throws ErrorGenerico {
        int i = 0;
        String a = lexico.pedirToken();
        if (deliberaOperaciones(a) == true)
        while (!a.equals("$")) {
            if (i++ > 100) {
                kill();
                System.exit(0);
            }
            a = lexico.pedirToken();
        }
    }
    
    public void mostrarTokens() {
        lexico.imprimeTablas();
    }
    
    // Mata la aplicación 
    public void kill() throws ErrorGenerico {
        mostrarTokens();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    throw new ErrorGenerico("Si ves este mensaje algo salio mal");
                } catch (ErrorGenerico ex) {
                    ex.getStackTrace();
                }
            }
        });
    }
    
    private void shuntingYard() throws ParentesisCierreException, ParentesisAperturaException {
        String token = tabla.listToken();
        int contadorParentesis = 0;
        while (token != null) {
            //System.out.println(token);
            conv.convertirCaracter(token.charAt(0));
            if (tipo.esNumero(conv.getAscii()) == true 
                    || tipo.esMayuscula(conv.getAscii()) == true
                    || tipo.esMinuscula(conv.getAscii()) == true) {
                //System.out.print("\t-" + tipo.esNumero(conv.getAscii()) + "\n");
                tabla.agregarElementoLTokensR(token, "Números", -1);
            } else if (tipo.esParentesis1(conv.getAscii()) == true) {
                //System.out.print("\t-" + tipo.esParentesis1(conv.getAscii()) + "\n");
                pila.push(token);
                contadorParentesis++;
            } else if(tipo.esMas(conv.getAscii()) == true || tipo.esMenos(conv.getAscii()) == true) {
                //System.out.print("\t-" + tipo.esMas(conv.getAscii()) + "\n");
                //System.out.print("\t-" + tipo.esMenos(conv.getAscii()) + "\n");
                if(pila.isEmpty()) {
                    //System.out.println("\t signo:");
                    if(comparaPrecedencia(token, pila.peek())) {
                        //System.out.print("\t-" + comparaPrecedencia(token, pila.peak()) + "\n");
                        tabla.agregarElementoLTokensR(pila.popConRetorno(), "Simb. Esp.", -1);
                        pila.push(token);
                    } else {
                        //System.out.print("\t" + token + "\n");
                        pila.push(token);
                    }
                } else {
                    pila.push(token);
                }
            } else if (tipo.esParentesis2(conv.getAscii()) == true) {
                //System.out.print("\t-" + tipo.esParentesis2(conv.getAscii()) + "\n");
                contadorParentesis--;
                //System.out.println("pila: " + pila.isEmpty());
                if(pila.isEmpty()) {
                    while(true) {
                        if(pila.isEmpty()) {
                            if(!pila.peek().equals("(")) {
                                //System.out.println("\t" + pila.peak());
                                tabla.agregarElementoLTokensR(pila.popConRetorno(), "Simb. Esp.", -1);
                            } else {
                                pila.pop();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    throw new ParentesisCierreException("Parentesis de cierre sobrante");
                }
            }
            
            if(contadorParentesis < 0) {
                throw new ParentesisCierreException("Parentesis de cierre sobrante");
            }
            
            token = tabla.listToken(); // pide el proximo token
        }
        
        while(pila.isEmpty()) {
            if(!pila.peek().equals("(")) {
                tabla.agregarElementoLTokensR(pila.popConRetorno(), "Simb. Esp.", -1);
            } else {
                throw new ParentesisAperturaException("Parentesis de apertura sobrante");
            }
        }
    }
    
    private boolean comparaPrecedencia(String opExp, String opTopPila) {
        return darPrecedenciaOperacion(opExp) < darPrecedenciaPila(opTopPila);
    }
    
    private int darPrecedenciaOperacion(String op) {
        /*
            ^       4
            * / %   2
            + -     1
        */
        switch(op) {
            case "^":  // la fase de análisis no los acepta así que si se intenta usar dara error
                return 4;
            case "*": case "/": case "%": // la fase de análisis no los acepta así que si se intentan usar dara error
                return 2;
            case "+": case "-":
                return 1;
            default:
                return 0;
        }
    }
    
    private int darPrecedenciaPila(String op) {
        /*
            ^       3
            * / %   2
            + -     1
        */
        switch(op) {
            case "^": // la fase de análisis no los acepta así que si se intenta usar dara error
                return 3;
            case "*": case "/": case "%": // la fase de análisis no los acepta así que si se intentan usar dara error
                return 2;
            case "+": case "-":
                return 1;
            default:
                return 0;
        }
    }
    
    private void expresionOriginal() {
        String token = tabla.listToken();
        System.out.println("Original:");
        while (token != null) {
            System.out.print(token + " ");
            token = tabla.listToken();
        }
        System.out.print("\n");
    }
    
    private void expresionPrefija() {
        String token = tabla.listPrefija();
        System.out.println("Prefija: (Árbol de Sintaxis abstracta?");
        while (token != null) {
            System.out.print(token + " ");
            token = tabla.listPrefija();
        }
        System.out.print("\n");
    }
    
    // lo mismo que el de arriba pero en un string
    private String expresionPrefijaString() {
        String cadena = "";
        String token = tabla.listPrefija();
        while (token != null) {
            cadena += token + " ";
            token = tabla.listPrefija();
        }
        //System.out.print("\n");
        return cadena;
    }
    
    private boolean deliberaOperaciones(String token) {
        if (token.equals(":")) {
            
        }
        return true;
    }
    
    public static void main(String[] args) throws ParentesisCierreException, ParentesisAperturaException, ErrorGenerico {
        ListasR t = new ListasR();
        Simplificacion sm = new Simplificacion();
        ClasificaRebuilt c = new ClasificaRebuilt(t);
        ShuntingYard ap = new ShuntingYard(t, c);
        try {
            ap.separarTokens();
            ap.mostrarTokens();
            ap.shuntingYard();
            ap.expresionOriginal();
            System.out.println("abajo sale el postfijo");
            ap.expresionPrefija();
            System.out.println(ap.expresionPrefijaString());
            System.out.println("Tokens");
            t.mostrarListaTokensR();
            System.out.println("Posfija");
            t.mostrarListaPostfija();
            sm.simplificacionSentencia(t);
            
            System.out.println("Imprimiendo tercetos");
            sm.imprimeTodo();
        } catch (ParentesisAperturaException | ParentesisCierreException e) {
            e.printStackTrace();
        }
    }
}