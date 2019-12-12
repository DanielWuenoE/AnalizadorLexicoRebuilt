package Test;

//import Errores.ErrorGenerico;
import CodIntermedio.Generacion;
import Estructuras.ListasR;
import Lexico.ClasificaRebuilt;
import Parseo.ShuntingYard;
import Sintactico.MatrizPredictiva;
import Semantico.ValidacionTipos;
import Parseo.Terceto;

public class Test {

    private void analizadores() {
        ListasR tabla = new ListasR();
        ClasificaRebuilt lexico = new ClasificaRebuilt(tabla);
        MatrizPredictiva sintactico = new MatrizPredictiva(lexico);
        if (sintactico.LlDiver()) {
            ValidacionTipos semantico = new ValidacionTipos(tabla, lexico);
            semantico.idenficiaVT();

            Generacion cod = new Generacion(lexico);
            cod.Generar();
            cod.terceto.imprimeTodo();
        }
        System.out.println("_________________________________________________________________");

        lexico.definirExpresion("( (2 + 1 ) - ( 5 + 3 ) ) - 2");
        String tok = lexico.pedirTokenExpresion();
        System.out.println(tok);
        do {
            tok = lexico.pedirTokenExpresion();
            System.out.println(tok);
        } while (tok != null);

//        inicia la fase de Generación de Código intermedio
//        ShuntingYard ap = new ShuntingYard(tabla, lexico);
//        tabla.mostrarListaSimbolosR();
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.analizadores();
    }
}
