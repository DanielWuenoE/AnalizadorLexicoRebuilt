package Lexico;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Clasificar {
    
    ConversionCaracter conv = new ConversionCaracter();
    Tipos tipo = new Tipos();
    Cadena cad = new Cadena();
    
    private TableModel simbolo, token, error, reservada;
    
    public void im() {
        imprimir(simbolo, 20, 3);
    }

    public void crearTablaSimbolos(int noFilas) {
        simbolo = new DefaultTableModel(noFilas, 3);       //creación de la tabla, columnas: palabra reservada(tipo) - identificador - valor
    }

    public void crearTablaTokens(int noFilas) {
        token = new DefaultTableModel(noFilas, 2);       //creación de la tabla, columnas: token - tipo
    }

    public void crearTablaErrores(int noFilas) {
        error = new DefaultTableModel(noFilas, 2);       //creación de la tabla, columnas: token - error
    }

    public void crearTablaReservadas(int noFilas) {
        reservada = new DefaultTableModel(noFilas, 2);       //creación de la tabla, columnas: palabra reservada
    }

    public void insertarValor(TableModel tabla, int fila, int col, String val) {
        tabla.setValueAt(val, fila, col);
    }
    
    private int filaSimbolo = 0, filaError = 0;
    
    public void clasificarFila(String fila) {                                   //clasifica una fila
        String[] palabraArray = new NoEsSplit().noEsEsplit(fila, " ");          //separa por espacios la linea

        for (String palabra : palabraArray) {                                   //lee palabra a palabra de la linea antes separada
            if (new PalabrasReservadas().buscaPalabraReservada(palabra) == true) {
                insertarValor(simbolo, filaSimbolo, 0, palabra);                  //inserta palabra reservada en tabla
                filaSimbolo++;
            } else {
                int tipo = clasificarPalabra(palabra);                                      // envia la palabra a clasificar
                switch(tipo){
                    case 1:
                        insertarValor(simbolo, filaSimbolo - 1, 2, palabra);
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        insertarValor(error, filaError, 2, palabra);
                        filaError ++;
                        break;
                }
            }

        }
    }
    
    public int clasificarPalabra(String palabra) {               //clasifica una palabra

        int caso = 0;

        for (int i = 0; i < palabra.length(); i++) {
            conv.convertirCaracter(palabra.charAt(i));                      //conversion a Ascii

            if (tipo.esMinuscula(conv.getAscii()) == true) {
                caso = 1;
            } else if (tipo.esNumero(conv.getAscii()) == true) {
                caso = 2;
            } else {
                caso = 3;
            }

            switch (caso) {
                case 1:
                    if (esIdentificador(palabra) == true) {
                        return 1;
                    } else {
                        return 4;
                    }
                case 2:
                int numCaso = esNumeroCad(palabra);
                    
                    switch(numCaso) {
                        case 1:
                            return 2;  // numero entero
                        case 2:
                            return 5; // numero flotante
                        case 3:
                            return 4; // palabra desconocida
                    }
                    break;
                case 3:
                    if (esCaracterCad(palabra) == true) {
                        return 3;
                    } else {
                        return 4;
                    }
            }
        }
        return 4;       //ojo aqui, no se el resultado
    }
    
    public boolean esIdentificador(String palabra) {           //verifica si la cadena es identificador

        for (int i = 0; i < palabra.length(); i++) {

            conv.convertirCaracter(palabra.charAt(i));                       //conversion a Ascii
            if (tipo.esMayuscula(conv.getAscii()) == true) {          //es mayuscula?
                conv.convertirAscii(conv.getAscii());
                //System.out.print(conv.getCaracter());               //impresion del caracter
            } else if (tipo.esMinuscula(conv.getAscii()) == true) {    //es minusula?
                conv.convertirAscii(conv.getAscii());
                //System.out.print(conv.getCaracter());               //impresion del caracter
            } else if (tipo.esNumero(conv.getAscii()) == true) {       //es numero?
                conv.convertirAscii(conv.getAscii());
                //System.out.print(conv.getCaracter());       //impresion del caracter
            } else {                                           //es especial
                //imprimirDesconocido(act);
                return false;
            }
        }                          //hay mas simbolos?
        return true;
    }
    
    public int esNumeroCad(String palabra) {               //verifica si la cadena es un numero
        for (int i = 0; i < palabra.length(); i++) {
            conv.convertirCaracter(palabra.charAt(i));               //conversion a Ascii
            if (tipo.esNumero(conv.getAscii()) == true) {     //es numero?
                conv.convertirAscii(conv.getAscii());
                //System.out.print(conv.getCaracter());       //impresion del caracter
            } else {                                           //no es número
                //imprimirDesconocido(act);
                if (esFlotanteCad(palabra)) {
                    return 2;  //es un número flotante
                }
                return 3;  // no es un número valido
            }
        }                            //hay mas simbolos?
        return 1;  // es un entero
    }
    
    public boolean esFlotanteCad(String palabra) {  //Verifica si la cadena es un número flotante
        boolean uno = false;
        for (int i = 0; i < palabra.length(); i++) {
            conv.convertirCaracter(palabra.charAt(i));    //convierte a Ascii
            if (conv.getAscii(palabra.charAt(i)) == 46 && uno == false) { // es un punto
                uno = true;
                conv.convertirAscii(conv.getAscii());
                //System.out.print(conv.getCaracter());       //impresion del caracter
            } else if (tipo.esNumero(conv.getAscii()) == true) {  // es número
                conv.convertirAscii(conv.getAscii());
                //System.out.print(conv.getCaracter());       //impresion del caracter
            } else {
                //imprimirDesconocido(act);
                return false;
            }
        }
        return true;
    }
    
    public boolean esCaracterCad(String palabra) {            //verifica si la cadena es un caracter
        for (int i = 0; i < palabra.length(); i++) {
            conv.convertirCaracter(palabra.charAt(i));                   //conversion a Ascii
            if (tipo.esCaracter(conv.getAscii()) == true) {       //es un caracter especial?
                if (i > 1) {
                    //imprimirDesconocido(act);
                    return false;
                } else {
                    conv.convertirAscii(conv.getAscii());
                    //System.out.print(conv.getCaracter());       //impresion del caracter
                    return true;
                }
            } else {
                //imprimirDesconocido(act);
                return false;
            }
        }
        return false;
    }
    
    public void imprimir(TableModel table, int filas, int columnas) {
       
        System.out.println("col1 \t col2");
        System.out.println("_____________________");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("\t" + table.getValueAt(i, j));
            }
            System.out.println("");
        }
    }
    
    // -------------- Métodos con nodo ---------------//
    
    public boolean esIdentificador(Cadena.Simbolo token) {           //verifica si la cadena es identificador
        Cadena.Simbolo act = token;    
        while(act != null){
            conv.convertirCaracter(act.simbolo);                       //conversion a Ascii
            if(tipo.esMayuscula(conv.getAscii()) == true) {          //es mayuscula?
                conv.convertirAscii(conv.getAscii());
                System.out.print(conv.getCaracter());               //impresion del caracter
            }else if(tipo.esMinuscula(conv.getAscii()) == true) {    //es minusula?
                conv.convertirAscii(conv.getAscii());
                System.out.print(conv.getCaracter());               //impresion del caracter
            }else if(tipo.esNumero(conv.getAscii()) == true) {       //es numero?
                conv.convertirAscii(conv.getAscii());
                System.out.print(conv.getCaracter());       //impresion del caracter
            }else{                                           //es especial
                imprimirDesconocido(act);
                return false;
            }
            act = act.sig;
        }                          //hay mas simbolos?
        return true;
    }
    
    public int esNumeroCad(Cadena.Simbolo token) {               //verifica si la cadena es un numero
        Cadena.Simbolo act = token;
        while(act != null) {
            conv.convertirCaracter(act.simbolo);               //conversion a Ascii
            if(tipo.esNumero(conv.getAscii()) == true) {     //es numero?
                conv.convertirAscii(conv.getAscii());
                System.out.print(conv.getCaracter());       //impresion del caracter
            } else {                                           //no es número
                //imprimirDesconocido(act);
                if (esFlotanteCad(act)) {  // diferente de numero, verificar si coincide en float
                    return 2;  //es un número flotante
                }
                return 3;  // no es un número valido
            }
            act = act.sig;
        }                            //hay mas simbolos?
        return 1;  // es entero
    }
    
    public boolean esFlotanteCad(Cadena.Simbolo token) {  //Verifica si la cadena es un número flotante
        Cadena.Simbolo act = token;
        boolean uno = false;
        while (act != null) {
            conv.convertirCaracter(act.simbolo);    //convierte a Ascii
//            if (tipo.esNumero(conv.getAscii()) == true) { // es número
//                conv.convertirAscii(conv.getAscii());
//                System.out.print(conv.getCaracter());       //impresion del caracter
//            } else 
            if (conv.getAscii(act.simbolo) == 46 && uno == false) { // es un punto
                uno = true;
                conv.convertirAscii(conv.getAscii());
                System.out.print(conv.getCaracter());       //impresion del caracter
            } else if (tipo.esNumero(conv.getAscii()) == true) {  // es número
                conv.convertirAscii(conv.getAscii());
                System.out.print(conv.getCaracter());       //impresion del caracter
            } else {
                imprimirDesconocido(act);
                return false;
            }
            act = act.sig;
        }
        return true;
    }
    
    public boolean esCaracterCad(Cadena.Simbolo token) {            //verifica si la cadena es un caracter
        Cadena.Simbolo act = token;
        conv.convertirCaracter(act.simbolo);                   //conversion a Ascii
        if(tipo.esCaracter(conv.getAscii()) == true) {       //es un caracter especial?
            if(act.sig != null){
                imprimirDesconocido(act);
                return false;
            } else { 
                conv.convertirAscii(conv.getAscii());
                System.out.print(conv.getCaracter());       //impresion del caracter
                return true;
            }
        } else {
            imprimirDesconocido(act);
            return false;
        }
    }
    
    public void clasificar(Cadena.Simbolo token) {               //clasifica un token
        Cadena.Simbolo act = token;
        int caso = 0;
        
        if(act != null) {
            conv.convertirCaracter(act.simbolo);           //conversion a Ascii

            if(tipo.esMinuscula(conv.getAscii()) == true) {
                caso = 1;
            } else if(tipo.esNumero(conv.getAscii()) == true) {
                caso = 2;
            } else
                caso = 3;
            
            switch(caso) {
                case 1:
                    if(esIdentificador(token) == true)
                        imprimir("Identificador\n");
                    else
                        imprimir("Palabra desconocida\n");
                    break;
                case 2:
                    int numCaso = esNumeroCad(token);
                    
                    switch(numCaso) {
                        case 1:
                            imprimir("Numero\n");
                            break;
                        case 2:
                            imprimir("Flotante\n");
                            break;
                        case 3:
                            imprimir("Palabra desconocidoz\n");
                            break;
                    }
                    break;
                case 3:
                    if(esCaracterCad(token) == true)
                        imprimir("Caracter\n");
                    else
                        imprimir("Palabra desconocida\n");
                    break;
            }
        } else
            imprimir("No hay cadena\n");
        }
    
    public void imprimirDesconocido(Cadena.Simbolo token) {
        Cadena.Simbolo act = token;
        while(act != null){
            conv.convertirCaracter(act.simbolo);
            conv.convertirAscii(conv.getAscii());
            System.out.print(conv.getCaracter());
            act = act.sig;
        }
    }
    
    public void imprimir(String s)  {
        System.out.printf("%30s", s);
    }
    
    // -------------- Fin métodos con nodo ---------------//
}