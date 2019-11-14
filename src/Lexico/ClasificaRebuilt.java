package Lexico;

import Lectura.LeerArchivo;
import Estructuras.ListasR;
import Semantico.ValidacionTipos;

public class ClasificaRebuilt {

    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    String archivo = "", token;
    LeerArchivo leer = new LeerArchivo();
    ListasR listaTab;
    PalabraReservada palR;
    ValidacionTipos validaT;
    int actual = 0;
    public String tokenAnt = "", tokenAnt2 = "";
    private String token1 = "";

    public ClasificaRebuilt(ListasR tabla) {
        leer.leerArchivo();
        archivo = leer.datos();
        palR = new PalabraReservada();
        //validaT = new ValidacionTipos();
        this.listaTab = tabla;
    }
    
    public void retrocedeToken(String token){
        tokenAnt2 = tokenAnt;
        tokenAnt = token1;
        token1 = token;        
    }

    public void retroceder() {
        if (actual < (archivo.length() - 6)) {
            actual = actual - 6;
            listaTab.agregarElementoLSimbolosR("begin", tipoPalabra("begin"), listaTab.buscaRepR("begin") - 1, calValToken("begin"), -1, null);
        }
    }
    
    public void reiniciarLectura(){
        actual = 0;
    }

    public String pedirToken() {
        q0(archivo);
//        System.out.println(token);
        
//        System.out.println("Token anterior: "+tokenAnt);
        return token;
    }

    public void q0(String archivo) {
        if (actual < archivo.length()) {
            conv.convertirCaracter(archivo.charAt(actual));
            if (tipo.esEspacio(conv.getAscii())) {
                if (actual + 1 < archivo.length()) {
                    actual++;
                    q0(archivo);
                }
                //NOTA: CREO FALTA AGREGAR ELSE
            } else if ((tipo.esMinuscula(conv.getAscii()) == true) || (tipo.esMayuscula(conv.getAscii()) == true)) {
                actual++;
                q1Identificador(archivo);
            } else if (tipo.esNumero(conv.getAscii()) == true) {
                actual++;
                q2NumeroEntero(archivo);
            } else if (tipo.esParentesis1(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual - 1, actual + 1, archivo);
                /*Token|Tipo Token|Valor Token|Valor Identf|Tipo Identif|#Veces Repite|*/
                listaTab.agregarElementoLSimbolosR(token, "Simb. Esp.", listaTab.buscaRepR(token) + 1, (int) (token.charAt(0)) + 300, 0, null);
//                q1Identificador(archivo);
            } else if (tipo.esParentesis2(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual - 1, actual + 1, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Simb. Esp.", listaTab.buscaRepR(token) + 1, (int) (token.charAt(0)) + 300, 0, null);
//                q1Identificador(archivo);
            } else if (tipo.esComa(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual - 1, actual + 1, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Simb. Esp.", listaTab.buscaRepR(token) + 1, (int) (token.charAt(0)) + 300, 0, null);
//                q1Identificador(archivo);
            } else if (tipo.esPyC(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual - 1, actual + 1, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Simb. Esp.", listaTab.buscaRepR(token) + 1, (int) (token.charAt(0)) + 300, 0, null);
//                q1Identificador(archivo);
            } else if (tipo.esDPuntos(conv.getAscii()) == true) {
                actual++;
                q3Asignacion(archivo);
            } else if (tipo.esMas(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual - 1, actual + 1, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Simb. Esp.", listaTab.buscaRepR(token) + 1, (int) (token.charAt(0)) + 300, 0, null);
//                q1Identificador(archivo);
            } else if (tipo.esMenos(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual - 1, actual + 1, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Simb. Esp.", listaTab.buscaRepR(token) + 1, (int) (token.charAt(0)) + 300, 0, null);
//                q1Identificador(archivo);
            } else {
                actual++;
                qErrorLexico(archivo, 0);
            }
        }
    }

    public void q1Identificador(String archivo) {
        int movs = 1;
        for (int i = actual; i < archivo.length(); i++) {
            conv.convertirCaracter(archivo.charAt(i));
            if (tipo.esEspacio(conv.getAscii())) {
                token = crearCadena(actual - 1, actual + movs, archivo);
                listaTab.agregarElementoLSimbolosR(token, tipoPalabra(token), listaTab.buscaRepR(token) + 1, calValToken(token), -1, null);
                actual = actual + movs;
                break;
            } else if ((tipo.esMinuscula(conv.getAscii()) == true)
                    || (tipo.esMayuscula(conv.getAscii()) == true)
                    || (tipo.esNumero(conv.getAscii()) == true)) {
                movs++;
            } else {
                token = crearCadena(actual - 1, actual + movs, archivo);
                listaTab.agregarElementoLSimbolosR(token, tipoPalabra(token), listaTab.buscaRepR(token) + 1, calValToken(token), -1, null);
                actual = actual + movs - 1;
//                qErrorLexico(archivo, movs);
                break;
            }
        }
    }

    public void q2NumeroEntero(String archivo) {
        int movs = 1;
        for (int i = actual; i < archivo.length(); i++) {
            conv.convertirCaracter(archivo.charAt(i));
            if (tipo.esEspacio(conv.getAscii())) {
                token = crearCadena(actual - 1, actual + movs, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Número", listaTab.buscaRepR(token) + 1, 500, token, "Int");
                //token = "int";
                //validaT.validacionInt(token); //LLama a metodo/s del analizador Sincatico
                actual = actual + movs;
                break;
            } else if (tipo.esNumero(conv.getAscii()) == true) {
                movs++;
            } else {
                token = crearCadena(actual - 1, actual + movs, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Número", listaTab.buscaRepR(token) + 1, 500, token, "Int");
//                token = "int";
                   token = crearCadena(actual - 1, actual + movs, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Número", listaTab.buscaRepR(token) + 1, 500, token, "Int");
//                token = "int";    actual = actual + movs - 1;
//                qErrorLexico(archivo, movs);
                break;
            }
        }
    }

    public void q3Asignacion(String archivo) {
        int movs = 1;
        for (int i = actual; i < archivo.length(); i++) {
            conv.convertirCaracter(archivo.charAt(i));
            if (tipo.esEspacio(conv.getAscii())) {
                token = crearCadena(actual - 1, actual + movs, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Simb. Esp.", listaTab.buscaRepR(token) + 1, (int) (token.charAt(0)) + 300, 0, null);
                actual = actual + movs;
                break;
            } else if (tipo.esIgual(conv.getAscii()) == true) {
                movs++;
                //NOTA: SOLO DEBE HABER UNO
            } else {
                token = crearCadena(actual - 1, actual + movs, archivo);
                listaTab.agregarElementoLSimbolosR(token, "Simb. Esp.", listaTab.buscaRepR(token) + 1, (int) (token.charAt(0)) + 300, 0, null);
                actual = actual + movs - 1;
//                qErrorLexico(archivo, movs);
                break;
            }
        }
    }

    public void qErrorLexico(String archivo, int movs) {
        for (int i = actual + movs; i < archivo.length(); i++) {
            conv.convertirCaracter(archivo.charAt(i));
            if (tipo.esEspacio(conv.getAscii()) == true) {
                movs++;
                token = crearCadena(actual - 1, actual + movs, archivo);
                actual = actual + movs;
                break;
            } else {
                movs++;
            }
        }

    }

    public String crearCadena(int i, int f, String archivo) {
        String cad = "";

        for (int j = i; j < f - 1; j++) {
            cad = cad + archivo.charAt(j);
        }

        return cad;
    }

    public void imprimeTablas() {
        listaTab.mostrarListaSimbolosR();
    }

    public String tipoPalabra(String token) {
        if (palR.ExistePalabraReservada(token)) {
            return "Palabra Re.";
        } else {
            return "Identificador";
        }
    }

    public int calValToken(String Token) {
        if (palR.ExistePalabraReservada(token)) {
            return palR.getValorPalabraReservada(Token);
        } else {
            if (listaTab.ExistePalabraT(token)) {
                listaTab.buscaRepR(token);
            } else {
                if (listaTab.ExistePalabraT(token)) {
                    return listaTab.buscaIdent(token);
                } else {
                    return listaTab.ultimoEnFila() + 1;
                }
                //return -1;
            }
            return 0;
        }
    }
    
    
    /// esto deeria ir en el Semantico, ignoren 
    public String cambiazo(String token) {
        conv.convertirCaracter(token.charAt(0));
        if (!palR.ExistePalabraReservada(token) 
                && (tipo.esMinuscula(conv.getAscii())
                    || (tipo.esMinuscula(conv.getAscii()))
                )
            )
        {
            return "ID";
        } else if (tipo.esNumero(conv.getAscii())) {
            return "int";
        } 
        return token;
    }

    public static void main(String[] args) {
//        ClasificaRebuilt obj = new ClasificaRebuilt();
//        while (!obj.pedirToken().equals("end")) {
//            obj.pedirToken();
//        }
//        obj.imprimeTablas();
     
    }
}
