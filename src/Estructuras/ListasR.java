package Estructuras;

public class ListasR<dato> {
    private NodoTSimR inicioSim, finSim;
    private NodoTErrores inicioErr, finErr;
    private NodoTToken inicioTok, finTok;
    private NodoTReservadas inicioR, finR;
    
    /*
            Supuesta tabla de símbolos
    Token   |   Tipo Token    |  Valor Token   |   Valor Identf  |  Tipo Identif  |  #Veces Repite  |
      w        identificador         115                0                int              3
    */
    
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
        public String palabra; String tipoTok; int tokenTok;
        public NodoTToken siguiente;
        
        public NodoTToken (String palabra, String tipoTok, int tokenTok) {
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
        public void agregarElementoLSimbolosR(String token, String tipoToken, dato vecesRepite, dato valorToken, dato valorIdentificador, String tipoIdentificador) {
            //if (!ExistePalabraT(token)) {
                NodoTSimR agregarElemento = new NodoTSimR(token, tipoToken, valorToken, valorIdentificador, tipoIdentificador, vecesRepite);
                if (inicioSim != null) {  // Existe el inicio
                    finSim.siguiente = agregarElemento;  //Agregar al final de la
                    finSim = agregarElemento;
                } else {
                    inicioSim = finSim = agregarElemento; //Crea la lista con su primer Nodo
                }
            //} else
                siExiste(token, vecesRepite);
        }
        
        public void siExiste(String token, dato vecesRepite) {
            NodoTSimR recorrer = inicioSim;
            while (recorrer != null) {
                if (recorrer.token.equals(token)) {
//                    System.out.println("vr: " + vecesRepite);
                    recorrer.vecesRepite = vecesRepite;
                    break;
                }
                recorrer = recorrer.siguiente;
            }
        }
        
        
        /*
                Errores
            Palabra  Error
        */
        public void agregarElementoLErroresR(String palabra) {
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
        public void agregarElementoLTokensR(String palabra, String tipo, int token) {
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
        public void agregarElementoLReservadasR(String palabra, int token) {
            NodoTReservadas agregarElemento = new NodoTReservadas(palabra, token);
            if (inicioR != null) {
                finR.siguiente = agregarElemento;
                finR = agregarElemento;
            } else {
                inicioR = finR = agregarElemento;
            }
        }
        
        public void mostrarListaErroresR() {
            NodoTErrores recorrer = inicioErr;
            while (recorrer != null) {
                System.out.println(recorrer.error);
                recorrer = recorrer.siguiente;
            }
        }
        
        public void mostrarListaSimbolosR() {
            NodoTSimR recorrer = inicioSim;
            System.out.println("Token   |   Tipo Token    |  Valor Token   |   Valor Identf  |  Tipo Identif  |  #Veces Repite");
            while (recorrer != null) {
                System.out.printf("%-10s %13s %10s %17s %18s %15s %n",
                                   recorrer.token,
                                   recorrer.tipoToken,
                                   recorrer.valorToken,
                                   recorrer.valorIdentificador,
                                   recorrer.tipoIdentificador,
                                   recorrer.vecesRepite);
                recorrer = recorrer.siguiente;
            }
        }
        
        public void mostrarListaTokensR() {
            NodoTToken recorrer = inicioTok;
            while (recorrer != null) {
                System.out.println(recorrer.palabra + "\t" +
                                   recorrer.tipoTok + "\t" +
                                   recorrer.tokenTok);
                recorrer = recorrer.siguiente;
            }
        }
        
        public void mostrarListaReservadasR() {
            NodoTReservadas recorrer = inicioR;
            while (recorrer != null) {
                System.out.println(recorrer.palabraR + "\t" +
                                   recorrer.tokenR + "\t");
                recorrer = recorrer.siguiente;
            }
        }
        
        public boolean ExistePalabraT(String token) {
            NodoTSimR recorrer = inicioSim;
            while (recorrer != null) {
                if (recorrer.token.equals(token))
                    return true;
                else
                    recorrer = recorrer.siguiente;
            }
            return false;
        }
        
        public int buscaIdent(String token) {
            NodoTSimR recorrer = inicioSim;
            while (recorrer != null) {
                if (recorrer.token.equals(token))
                    return (int) recorrer.valorToken;
                recorrer = recorrer.siguiente;
            }
            return 0;
        }
        //busca columa token y cambia valores valor identificador - esto si sirve :3
        public void buscaYReplaza(String token, dato newValor) {
            NodoTSimR recorrer = inicioSim;
            while (recorrer != null) {
                if (recorrer.token.equals(token)) {
                    recorrer.valorIdentificador = newValor;
                }
                recorrer = recorrer.siguiente;
            }
        }
        
        //borrar
        public int buscaRepR(String token) {
            NodoTSimR recorrer = inicioSim;
            int sumEn = 0;
            while (recorrer != null) {
                if (recorrer.token.equals(token)) {
                    sumEn = (int) recorrer.vecesRepite;
                }
                recorrer = recorrer.siguiente;
            }
            return sumEn;
        }
        
        public int ultimoEnFila() {
            NodoTSimR recorrer = inicioSim;
            int idValue = 0;
            while (recorrer != null) {
                if (recorrer.tipoToken.equals("Identificador"))
                    idValue = (int) recorrer.valorToken;
                recorrer = recorrer.siguiente;
            }
            return idValue;
        }
        
        //manda la lista de Tokens completa()
        int contaSimR = 0;
        public String listToken() {
            NodoTSimR recorrer = inicioSim;
            String tokenR = "";
            int contameEsta = 0;
            while (recorrer != null && contaSimR >= contameEsta) {
                contameEsta++;
                tokenR = recorrer.token;
                recorrer = recorrer.siguiente;
            }
            
            recorrer = inicioSim; contameEsta = 0;
            while (recorrer != null && contaSimR >= contameEsta + 1) {
                contameEsta++;
                recorrer = recorrer.siguiente;
            }
            if (recorrer == null) {
                contaSimR = 0;
                return null;
            }
            contaSimR++;
            return tokenR;
        }
        
        // manda la lista de ER usada para guardar orden en Prefijo de las operaciones
        int contaSimRP = 0;
        public String listPrefija() {
            NodoTToken recorrer = inicioTok;
            String tokenR = "";
            int contameEsta = 0;
            while (recorrer != null && contaSimR >= contameEsta) {
                contameEsta++;
                tokenR = recorrer.palabra;
                recorrer = recorrer.siguiente;
            }
            
            recorrer = inicioTok; contameEsta = 0;
            while (recorrer != null && contaSimR >= contameEsta + 1) {
                contameEsta++;
                recorrer = recorrer.siguiente;
            }
            if (recorrer == null) {
                contaSimR = 0;
                return null;
            }
            contaSimR++;
            return tokenR;
        }
}
