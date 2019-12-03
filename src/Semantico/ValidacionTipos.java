package Semantico;

import Lexico.Tipos;
import Lexico.ConversionCaracter;
import Estructuras.ListasR;
import Lexico.ClasificaRebuilt;
import Lexico.PalabraReservada;

public class ValidacionTipos {

    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    PalabraReservada pr = new PalabraReservada();
    ListasR tabla;
    ClasificaRebuilt lexico;

    public ValidacionTipos(ListasR tabla, ClasificaRebuilt lexico) {
        this.tabla = tabla;
        this.lexico = lexico;
    }

    public boolean validacionInt(String token) {
        boolean flag = false;
        for (int i = 0; i < token.length(); i++) {
            conv.convertirCaracter(token.charAt(i));
            if (!tipo.esNumero(conv.getAscii())) {
                System.out.println("Error semántico en: " + token + "\nSe esperaba un dato de tipo entero");
                flag = true;
                return flag;
            }
        }return flag;
    }

    public void idenficiaVT() {
        String t = "", var = "";
        lexico.reiniciarLectura();
        while (!t.equals("$")) {
            t = lexico.pedirToken();
            lexico.retrocedeToken(t);
            if (t.equals(":=")) {
                // Revisar uno antes :=
                var = lexico.tokenAnt;

                t = lexico.pedirToken();
                lexico.retrocedeToken(t);
                t = lexico.pedirToken();
                lexico.retrocedeToken(t);
//                System.out.println("Var: " + var + " = " + lexico.tokenAnt);
                if (t.equals(";") && !validacionInt(lexico.tokenAnt)) {
                    validacionInt(lexico.tokenAnt);
                    tabla.buscaYReplaza(var, lexico.tokenAnt);
                }
               
//            if (pr.getValorPalabraReservada(lexico.tokenAnt) == 203) {
//                // el tipo es int/INT
//                //tabla.agregarElementoLTokensR(tabla, t, token);
//            }
//            // Revisar uno despues de :=
//            if (validacionInt(t)) {
//                // es un entero
//            }
               
            }

        }
        System.out.println("Análisis semántico terminado con éxito\n");
    }

    public static void main(String[] args) {
        //ValidacionTipos var = new ValidacionTipos();

//        var.validacionInt("123e");
//        System.out.println("");
//        var.validacionInt("1234");
//        System.out.println("");
//        var.validacionInt("1e23");
    }
}
