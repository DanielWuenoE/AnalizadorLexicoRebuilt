package analizador.lexico.c;

import Lectura.LeerArchivo;

public class ClasificaRebuilt {
    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    String archivo = "", token;
    LeerArchivo leer = new LeerArchivo();
    int actual = 0;
    
        public String pedirToken() {
        q0(archivo);
        return token;
    }
        
         public void q0(String archivo) {
        if (actual < archivo.length()) {
            conv.convertirCaracter(archivo.charAt(actual));
            if (tipo.esEspacio(conv.getAscii())) {
                if (actual+1 < archivo.length()) {
                    actual++;
                    q0(archivo);
                }
            } else if ((tipo.esMinuscula(conv.getAscii()) == true)||(tipo.esMayuscula(conv.getAscii()) == true)) {
                actual++;
//                q1Identificador(archivo);
            } else if (tipo.esParentesis1(conv.getAscii()) == true) {
                actual++;
//                q1Identificador(archivo);
            } else if (tipo.esParentesis2(conv.getAscii()) == true) {
                actual++;
//                q1Identificador(archivo);
            } else if (tipo.esComa(conv.getAscii()) == true) {
                actual++;
//                q1Identificador(archivo);
            } else if (tipo.esPyC(conv.getAscii()) == true) {
                actual++;
//                q1Identificador(archivo);
            } else if (tipo.esDPuntos(conv.getAscii()) == true) {
                actual++;
//                q1Identificador(archivo);
            } else if (tipo.esMas(conv.getAscii()) == true) {
                actual++;
//                q1Identificador(archivo);
            } else if (tipo.esMenos(conv.getAscii()) == true) {
                actual++;
//                q1Identificador(archivo);
            } else {
                actual++;
//                q2ErrorLexico(archivo, 0);
            }
        }
    }
}
