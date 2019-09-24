package analizador.lexico.c;

import Lectura.LeerArchivo;
import Estructuras.ListasR;

public class ClasificaRebuilt {

    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    String archivo = "", token;
    LeerArchivo leer = new LeerArchivo();
    ListasR listaTab;
    PalabraReservada palR;
    int actual = 0;

    public ClasificaRebuilt() {
        leer.leerArchivo();
        archivo = leer.datos();
        palR = new PalabraReservada();
        crearListasT();
    }

    private void crearListasT() {
        listaTab = new ListasR();
    }

    public String pedirToken() {
        q0(archivo);
        System.out.println(token);
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
                if (!palR.ExistePalabraReservada(token)) {
                    token = "ID";
                }
                actual = actual + movs;
                break;
            } else if ((tipo.esMinuscula(conv.getAscii()) == true)
                    || (tipo.esMayuscula(conv.getAscii()) == true)
                    || (tipo.esNumero(conv.getAscii()) == true)) {
                movs++;
            } else {
                token = crearCadena(actual - 1, actual + movs, archivo);
                listaTab.agregarElementoLSimbolosR(token, tipoPalabra(token), listaTab.buscaRepR(token) + 1, calValToken(token), -1, null);
                if (!palR.ExistePalabraReservada(token)) {
                    token = "ID";
                }
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
                listaTab.agregarElementoLSimbolosR(token, "Número", listaTab.buscaRepR(token) + 1, 500, token, null);
                actual = actual + movs;
                break;
            } else if (tipo.esNumero(conv.getAscii()) == true) {
                movs++;
            } else {
                token = crearCadena(actual - 1, actual + movs, archivo);
                actual = actual + movs - 1;
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

    public static void main(String[] args) {
        ClasificaRebuilt obj = new ClasificaRebuilt();
//        LeerArchivo leer = new LeerArchivo();
//        leer.leerArchivo();

        //String archivo = "Este .9 Este 0 00 .9 es 002 00t un Ar rA rA3 rA3.s 4.3e4 archivo archivo2 archi. 0. de prueba 12 12.12 0 0 00 edwsd 12 12.1 . . .. edsd # # ##  ";
        //String archivo2 = ";";
        //String archivo3 = leer.datos();
        //obj.q0(archivo2);
        //        obj.im(archivo);
        while (!obj.pedirToken().equals("end")) {
            obj.pedirToken();
        }
        obj.imprimeTablas();
    }
}
