package Estructuras;

public class ListasR<dato> {
    private NodoTSimR inicioSim, finSim;
    private NodoTErrores inicioErr, finErr;
    private NodoTToken inicioTok, finTok;
    private NodoTReservadas inicioR, finR;
    
    /*
            Supuesta tabla de símbolos
    Token   |   Tipo Token    |  Valor Token   |   Valor Identf  |  Tipo Identif  |  #Veces Repite  |
    */
    
    /* Hola */
    
    class NodoTSimR<dato> {
        public String token, tipoToken, tipoIdentificador;
        dato valorToken, valorIdentificador, vecesRepite;
        public NodoTSimR siguiente;
        
        public NodoTSimR(String token, String tipoToken, dato valorToken, dato valorIdentificador, String tipoIdentificador, dato vecesRepite) {
            this.token = token;
            this.tipoToken = tipoToken;
            this.valorToken = valorToken;
            this.valorIdentificador = valorIdentificador;
            this.tipoIdentificador = tipoIdentificador;
            this.vecesRepite = vecesRepite;
        }
    }
    
    class NodoTErrores {
        public String error;
        public NodoTErrores siguiente;
        
        public NodoTErrores (String error) {
            this.error = error;
        }
    }
    
    class NodoTToken {
        public dato palabra; String tipoTok; int tokenTok;
        public NodoTToken siguiente;
        
        public NodoTToken (dato palabra, String tipoTok, int tokenTok) {
            this.palabra = palabra;
            this.tipoTok = tipoTok;
            this.tokenTok = tokenTok;
        }
    }
    
    class NodoTReservadas {
        public String palabraR; int tokenR;
        public NodoTReservadas siguiente;
        
        public NodoTReservadas (String palabraR, int tokenR) {
            this.palabraR = palabraR;
            this.tokenR = tokenR;
        }
    }
        
        /* Inserta nuevos elementos
                Símbolos
            Tipo    Identificador   Valor
        */
        public void agregarElementoLSimbolos(String token, String tipoToken, dato valorToken, dato valorIdentificador, String tipoIdentificador, dato vecesRepite) {
            NodoTSimR agregarElemento = new NodoTSimR(token, tipoToken, valorToken, valorIdentificador, tipoIdentificador, vecesRepite);
            if (inicioSim != null) {  // Existe el inicio
                finSim.siguiente = agregarElemento;  //Agregar al final de la
                finSim = agregarElemento;
            } else {
                inicioSim = finSim = agregarElemento; //Crea la lista con su primer Nodo
            }
        }
        
        
        /*
                Errores
            Palabra  Error
        */
        public void agregarElementoLErrores(String palabra) {
            NodoTErrores agregarElemento = new NodoTErrores(palabra);
            if (inicioErr != null) {
                finErr.siguiente = agregarElemento;
                finErr = agregarElemento;
            } else {
                inicioErr = finErr = agregarElemento;
            }
        }
        
        /*
                Tokens
            Palabra  Tipo   Token
        */
        public void agregarElementoLTokens(dato palabra, String tipo, int token) {
            NodoTToken agregarElemento = new NodoTToken(palabra, tipo, token);
            if (inicioTok != null) {
                finTok.siguiente = agregarElemento;
                finTok = agregarElemento;
            } else {
                inicioTok = finTok = agregarElemento;
            }
        }
        
        /*
                Reservadas
            Palabra  Token
        */
        public void agregarElementoLReservadas(String palabra, int token) {
            NodoTReservadas agregarElemento = new NodoTReservadas(palabra, token);
            if (inicioR != null) {
                finR.siguiente = agregarElemento;
                finR = agregarElemento;
            } else {
                inicioR = finR = agregarElemento;
            }
        }
        
        public void mostrarListaErrores() {
            NodoTErrores recorrer = inicioErr;
            while (recorrer != null) {
                System.out.println(recorrer.error);
                recorrer = recorrer.siguiente;
            }
        }
        
        public void mostrarListaSimbolos() {
            NodoTSimR recorrer = inicioSim;
            while (recorrer != null) {
                System.out.println(recorrer.token + "\t" +
                                   recorrer.tipoToken + "\t" +
                                   recorrer.valorToken + "\t" +
                                   recorrer.valorIdentificador + "\t" +
                                   recorrer.tipoIdentificador + "\t" +
                                   recorrer.vecesRepite);
                recorrer = recorrer.siguiente;
            }
        }
        
        public void mostrarListaTokens() {
            NodoTToken recorrer = inicioTok;
            while (recorrer != null) {
                System.out.println(recorrer.palabra + "\t" +
                                   recorrer.tipoTok + "\t" +
                                   recorrer.tokenTok);
                recorrer = recorrer.siguiente;
            }
        }
        
        public void mostrarListaReservadas() {
            NodoTReservadas recorrer = inicioR;
            while (recorrer != null) {
                System.out.println(recorrer.palabraR + "\t" +
                                   recorrer.tokenR + "\t");
                recorrer = recorrer.siguiente;
            }
        }
}