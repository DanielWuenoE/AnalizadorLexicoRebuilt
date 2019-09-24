package analizador.lexico.c;

import Lectura.LeerArchivo;

public class ClasificaRebuilt {

    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    String archivo = "", token;
    LeerArchivo leer = new LeerArchivo();
    int actual = 0;
    
    public ClasificaRebuilt() {
        leer.leerArchivo();
        archivo = leer.datos();
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
                token = crearCadena(actual-1, actual+1, archivo);
//                q1Identificador(archivo);
            } else if (tipo.esParentesis2(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual-1, actual+1, archivo);
//                q1Identificador(archivo);
            } else if (tipo.esComa(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual-1, actual+1, archivo);
//                q1Identificador(archivo);
            } else if (tipo.esPyC(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual-1, actual+1, archivo);
//                q1Identificador(archivo);
            } else if (tipo.esDPuntos(conv.getAscii()) == true) {
                actual++;
                q3Asignacion(archivo);
            } else if (tipo.esMas(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual - 1, actual+1, archivo);
//                q1Identificador(archivo);
            } else if (tipo.esMenos(conv.getAscii()) == true) {
                actual++;
                token = crearCadena(actual - 1, actual+1, archivo);
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
                actual = actual + movs;
                break;
            } else if ((tipo.esMinuscula(conv.getAscii()) == true)
                    || (tipo.esMayuscula(conv.getAscii()) == true)
                    || (tipo.esNumero(conv.getAscii()) == true)) {
                movs++;
            } else {
                token = crearCadena(actual - 1, actual + movs, archivo);
                actual = actual + movs-1;
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
                actual = actual + movs;
                break;
            } else if (tipo.esIgual(conv.getAscii()) == true) {
                movs++;
                //NOTA: SOLO DEBE HABER UNO
            } else {
                token = crearCadena(actual - 1, actual + movs, archivo);
                actual = actual + movs-1;
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
//            obj.pedirToken();
        }
         while (!obj.pedirToken().equals("end")) {
//            obj.pedirToken();
        }
    }
}
