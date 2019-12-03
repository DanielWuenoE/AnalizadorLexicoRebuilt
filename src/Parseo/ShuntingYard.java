package Parseo;

import Errores.ParentesisAperturaException;
import Errores.ParentesisCierreException;
import Estructuras.ListasR;
import Estructuras.Pila;
import Lexico.ClasificaRebuilt;
import Lexico.ConversionCaracter;
import Lexico.Tipos;
import Errores.ErrorGenerico;

public class ShuntingYard {
    /* Expresión
            (2*((3*4)+9))
    */
    
    ListasR tablas = new ListasR();
    ClasificaRebuilt lexico = new ClasificaRebuilt(tablas);
    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    Pila pila = new Pila();
    
    public void separarTokens() throws ErrorGenerico {
        int i = 0;
        String a = lexico.pedirToken();
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
        String token = tablas.listToken();
        int contadorParentesis = 0;
        while (token != null) {
            //System.out.println(token);
            conv.convertirCaracter(token.charAt(0));
            if (tipo.esNumero(conv.getAscii()) == true 
                    || tipo.esMayuscula(conv.getAscii()) == true
                    || tipo.esMinuscula(conv.getAscii()) == true) {
                //System.out.print("\t-" + tipo.esNumero(conv.getAscii()) + "\n");
                tablas.agregarElementoLTokensR(token, null, -1);
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
                        tablas.agregarElementoLTokensR(pila.popConRetorno(), null, -1);
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
                                tablas.agregarElementoLTokensR(pila.popConRetorno(), null, -1);
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
            
            token = tablas.listToken(); // pide el proximo token
        }
        
        while(pila.isEmpty()) {
            if(!pila.peek().equals("(")) {
                tablas.agregarElementoLTokensR(pila.popConRetorno(), null, -1);
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
        String token = tablas.listToken();
        System.out.println("Original:");
        while (token != null) {
            System.out.print(token + " ");
            token = tablas.listToken();
        }
        System.out.print("\n");
    }
    
    private void expresionPrefija() {
        String token = tablas.listPrefija();
        System.out.println("Prefija: (Árbol de Sintaxis abstracta?");
        while (token != null) {
            System.out.print(token + " ");
            token = tablas.listPrefija();
        }
        System.out.print("\n");
    }
    
    // lo mismo que el de arriba pero en un string
    private String expresionPrefijaString() {
        String cadena = "";
        String token = tablas.listPrefija();
        while (token != null) {
            cadena += token + " ";
            token = tablas.listPrefija();
        }
        //System.out.print("\n");
        return cadena;
    }
    
    public static void main(String[] args) throws ParentesisCierreException, ParentesisAperturaException, ErrorGenerico {
        ShuntingYard ap = new ShuntingYard();
        //try {
            ap.separarTokens();
            ap.mostrarTokens();
            ap.shuntingYard();
            ap.expresionOriginal();
            ap.expresionPrefija();
            System.out.println(ap.expresionPrefijaString());
        //} catch (ParentesisAperturaException | ParentesisCierreException e) {
            //e.printStackTrace();
        //}
    }
}