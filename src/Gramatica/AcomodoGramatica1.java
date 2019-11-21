package Gramatica;

import Lectura.LeerArchivo_1;
import Lectura.NoEsDeString;

public class AcomodoGramatica1 {
    
    
    /*
        declaración de todos los arreglos a utilizar:
            * gramaticaCompleta: Almacena toda la producciones linea linea
            * todasLasProducciones: Almacena todas las produciones separando partes derechas y izquierdas
            * ladosDerechos: Almacena solo los lados derechos de las producciones
            * simbolosNoTerminales: Almacena todos los simbolos no terminales de las produciones
            * simbolosTerminales: Almacena todos los simbolos terminales de las produciones
            * temp: arreglo de uso temporal para almacenar los diferentes partes de las producciones derechas
            * leerA: Declaración de clase que nos permite buscar y leer el archivo a utilizar
            * nes: Declaración de clase de soporte para separar las producciones (split) y quitar espacios del inicio y final de las palabras (trim)
    */
    private String[] gramaticaCompleta;
    private String[][] todasLasProducciones;
    private String[] ladosDerechos;
    public String[] simbolosNoTerminales;
    public String[] simbolosTerminales;
    private String[] temp;
    private final LeerArchivo_1 leerA;
    private final NoEsDeString nes;
    
    /*
        Inicialización de las clases a tilizar
    */
    public AcomodoGramatica1() {
        leerA = new LeerArchivo_1();
        nes = new NoEsDeString();
    }

    /*
        Llamamos al método que nos permite leer el archivo seleccionado
    */
    private void leerGramatica() {
        leerA.leerArchivo();
    }
    
    /*
        metodo que contiene la secuencia del programa de lectura de Gramatica
            * leerGramatica: llama a metodo que nos permite leer el archivo de la gramatica
            * separarProducciones: llamada a metodo que separa las produciones line a linea 
            * definirArreglos: llama al método que nos permitira definer el tamaño de los arreglos
            * todasLasProducciones: llama al método que almacenara todas las producciones de la gramatica
            * ladosDerechos: llama al método que almacena las partes derechas de la gramatica
            * simbolosNoTerminales: llama al método que separa las producciones por simbolos no terminales
            * simbolosTerminales: llama al método que separa las producciones por simbolos terminales
    */
    public void ini() {
        leerGramatica();
        separarProducciones();
        definirArreglos();
        todasLasProducciones();
        ladosDerechos();
        simbolosNoTerminales();
        simbolosTerminales();
        //System.out.println(nes.cantidadDeCadenas());
        //System.out.println(nes.NoEsTrim(gramatica));
    }
    
    /*
        separa todas las producciones respecto a un salto de linea
    */
    private void separarProducciones() {
        gramaticaCompleta = nes.noEsEsplit(leerA.datos(), "\n");
    }
    
    /*
        Se define el tamaño de los arreglos respecto al tamaño de la gramática leída (número de lineas)
    */
    private void definirArreglos() {
        todasLasProducciones = new String[nes.cantidadDeCadenas()][nes.cantidadDeCadenas()];
        ladosDerechos = new String[nes.cantidadDeCadenas()];
        simbolosNoTerminales = new String[nes.cantidadDeCadenas()];
        simbolosTerminales = new String[nes.cantidadDeCadenas()+30];
        temp = new String[3];
    }
    
    /*
        se separan todas las producciones respecto al simbolo "?" separando por partes los lados derechos y lados izquierdos
    */
    private void todasLasProducciones() {
        for (int i = 0; i < gramaticaCompleta.length-1; i++) { // avanza en la gramática hasta finalizar las lineas a leer
            temp = nes.noEsEsplit(gramaticaCompleta[i], "?"); // separa por el símbolo "?" y almacena las partes en el arreglo temp
            todasLasProducciones[i][0] = (temp[0]).trim(); // lado izquierdo de la producción
            todasLasProducciones[i][1] = (temp[1]).trim(); // lado derecho de la producción
        }
    }
    
    /*
        toma las producciones y almacena solo los lados derechos
    */
    private void ladosDerechos() {
        for (int i = 0; i < todasLasProducciones.length; i++) { // mientras existan producciones
            ladosDerechos[i] = todasLasProducciones[i][1]; // las partes derechas se encuentran en el segundo espacio del arreglo [i][1]
        }
    }
    
    /*
        Compara si ya existe en el arreglo evitando duplicados y almacenando las partes izquierdas
    */
    private void simbolosNoTerminales() {
        int i = 0, j = 0; // para avanzar en el los arreglos J para la lectura de la producción y i para almacenar la producción
        while (j < todasLasProducciones.length) { // mientras existan producciones
            if (!existeEnArreglo(simbolosNoTerminales, todasLasProducciones[j][0])) { // se manda el arreglo donde se buscaran coincidencias y la producción a buscar para evitar duplicados
                simbolosNoTerminales[i] = todasLasProducciones[j][0].trim(); // almacena el lado derecho de las producciones
                i++; // se suma uno cada que se agrega una producción nueva a los no terminales
            }
            j++; // avanza una producción cada que termina de leer la actual
        }
    }
    
    /*
        Busca en los no terminales cada palabra de las producciones derechas para definir si es terminal
    */
    private void simbolosTerminales() {
        int i = 0, j = 0; // para avanzar en el los arreglos J para la lectura de la producción y i para almacenar la producción
//            imprime(simbolosNoTerminales);
        while (j < todasLasProducciones.length) { // mientras existan producciones
            temp = nes.noEsEsplit(todasLasProducciones[j][1], " "); // separa las producciones derechas por espacios
            for (String tempDerecha : temp) {           //  949 - vacio - 'ε' for que almacena cada aprte de las partes derecha de cada producción
                if (!existeEnArreglo(simbolosNoTerminales, tempDerecha)&& ((int)tempDerecha.charAt(0) != 949) // mientras no exista en los no terminales y no sea un vacio
                            && !existeEnArreglo(simbolosTerminales, tempDerecha)) { // y no exista en los terminales
                    simbolosTerminales[i] = tempDerecha;//todasLasProducciones[j][1]; // se almacena la parte derecha
                    i++; // se suma uno cada que se agrega una producción nueva a los no terminales
                }
            }
            j++; // avanza una producción cada que termina de leer la actual
        }
    }
    
    /*
        Metodo que recibe un arreglo y una palabra para evaluar si esta palabra se encuentra en el arreglo
    */
    private boolean existeEnArreglo(String[] arreglo, String buscar) {
        for (String buscarEn : arreglo) { // almacena todo el aareglo en una variable temporal para recorrer una a una cada valor del arreglo
            if (buscarEn != null) // mientras no sea null
                if (buscarEn.equals(buscar)) // compara si la variable temporal coincide con la palabra a evaluar
                    return true; // si se encuentra la palabra a evaluar se envia un true
        }
        return false; // si no se encuentra la palabra a evaluar se envia un false
    }
    
    /*
        imprime la gramatica completa
    */
    private void imprimeGramaticaCompleta() {
        for (String[] prod : todasLasProducciones) // mientras existan producciones
            //System.out.println(prod[0] + "\t->\t" + prod[1]);
            System.out.printf("%-30s %2s %-1s %n", prod[0], "->\t\t", prod[1]); //formatea la salida
    }
    
    /*
        imprime el titulo y manda a llamar al método pertinente de cada impresión
    */
    public void imprime() {
        System.out.println("\tGramática completa");
        imprimeGramaticaCompleta();
        System.out.println("\n\tLados Derechos");
        imprime(ladosDerechos);
        System.out.println("\n\tSímbolos No Terminales");
        imprime(simbolosNoTerminales);
        System.out.println("\n\tSímbolos Terminales");
        imprime(simbolosTerminales);
    }
    
    /*
        imprime el arreglo que recibe
    */
    private void imprime(String[] arreglo) {
        for (String prod : arreglo) { // almacena temporalmente cada valor del arreglo
            if (prod != null) // mientras el valor no sea null
                System.out.println(prod); // imprime el valor actual del ciclo
        }
    }
}

class test1 {
    public static void main(String[] args) {
        AcomodoGramatica1 ag = new AcomodoGramatica1();
        ag.ini(); // metodo que inicia la secuencia de la función de la gramática
        ag.imprime(); // manda a imprimir los arreglos generados 
    }
}