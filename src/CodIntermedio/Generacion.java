package CodIntermedio;

import Errores.ErrorGenerico;
import Errores.ParentesisAperturaException;
import Errores.ParentesisCierreException;
import Lexico.ClasificaRebuilt;
import Parseo.ShuntingYard;
import Parseo.Terceto;
import Parseo.Simplificacion;
import Estructuras.ListasR;

public class Generacion {

    ListasR tabla;
    public Terceto terceto = new Terceto();
    ClasificaRebuilt lexico;
    
    Simplificacion simple;
    ShuntingYard shunt;

    public Generacion(ClasificaRebuilt lexico, ListasR lista) {
        this.lexico = lexico;
        this.tabla = lista;
        simple = new Simplificacion(lista);
        shunt = new ShuntingYard(lista ,lexico);
    }
    public void Generar() throws ParentesisCierreException, ParentesisAperturaException, ErrorGenerico {
        String t = "", var = "", operacion = ""; //Declarar operacion como nueva lista
        lexico.reiniciarLectura();
        while (!t.equals("$")) {
            t = lexico.pedirToken();
            lexico.retrocedeToken(t);
//            System.out.println("Token: "+t);
            if (t.equals("begin")) {
                terceto.Terceto("Inicio", "", "", "");
//                System.out.println("Es begin");
            } else if (t.equals("read")) {
                t = saltoToken(2);
                terceto.Terceto(lexico.tokenAnt2, t, "", "");
//                System.out.println("Es read");
                t = saltoToken(2);
            } else if (t.equals("write")) {
                t = saltoToken(2);
                terceto.Terceto(lexico.tokenAnt2, t, "", "");
//                System.out.println("Es write");
                t = saltoToken(2);
            } else if (t.equals(":=")) {
                // Revisar uno antes :=
                var = lexico.tokenAnt;
//                System.out.println("Asignacion encontrada");
                t = saltoToken(1);
                operacion += t+" ";             //NOTA: Falta cambio por lista
                t = saltoToken(1);
                operacion += t+" ";
//                System.out.println("Operacion: "+operacion);
                if (t.equals(";")) {                 //es asignacion
//                    System.out.println("Es asignacion");
                    terceto.Terceto("=", lexico.tokenAnt, "", var);
                    operacion = "";
                } else {
//                    System.out.println("No es asignacion");
                    t = saltoToken(1);
                    operacion += t+" ";
                    t = saltoToken(1);
                    operacion += t+" ";
                    if (t.equals(";")) {             //es operacion simple
//                        System.out.println("Es operacion simple");
                        terceto.Terceto(lexico.tokenAnt2, lexico.tokenAnt, lexico.tokenAnt3, var);
                        operacion = "";
                    } else {                        //es operacion compuesta
//                        System.out.println("Es compuesta");
                        while (!t.equals(";")) {
                            t = saltoToken(1);
                            if (!t.equals(";")) 
                                operacion += t+" ";
                        }
//                        lexico.definirExpresion(operacion);//Enviar expresion a lexico
                        shunt.shuntingYard(operacion);//Llamar a ShutingYard
                        //Correr Simplificacion con lista resultante
                        //(regresar ultima variable temporal)
//                        System.out.println("Operacion: "+operacion);
                        terceto.Terceto("=", simple.simplificacionSentencia(), "", var);
//                        System.out.println("Generacion operacion: "+operacion);
                        operacion = "";
                    }
                }

            } else if (t.equals("end")) {
                terceto.Terceto("Fin", "", "", "");
//                System.out.println("Es end");
            }
        }
        System.out.println("Generación de codigo intermedio exitosa");
    }

    public String saltoToken(int tokens) {
        String t = "";
        for (int i = 0; i < tokens; i++) {
            t = lexico.pedirToken();
            lexico.retrocedeToken(t);
        }
        return t;
    }

    public static void main(String[] args) {

//        Generacion obj = new Generacion();
//
//        obj.Generar();
    }
}
