package Test;

//import Errores.ErrorGenerico;
import CodIntermedio.Generacion;
import Errores.ErrorGenerico;
import Errores.ParentesisAperturaException;
import Errores.ParentesisCierreException;
import Estructuras.ListasR;
import Lexico.ClasificaRebuilt;
import Parseo.ShuntingYard;
import Sintactico.MatrizPredictiva;
import Semantico.ValidacionTipos;
import Parseo.Terceto;

public class Test {

    private void analizadores() throws ParentesisCierreException, ParentesisAperturaException, ErrorGenerico {
        ListasR tabla = new ListasR();
        ClasificaRebuilt lexico = new ClasificaRebuilt(tabla);
        MatrizPredictiva sintactico = new MatrizPredictiva(lexico);
        if (sintactico.LlDiver()) {
            ValidacionTipos semantico = new ValidacionTipos(tabla, lexico);
            semantico.idenficiaVT();

            Generacion cod = new Generacion(lexico, tabla);
            cod.Generar();
            cod.terceto.imprimeTodo();
            cod.generaArchivoCuartetos();
        }


//        inicia la fase de Generación de Código intermedio
//        ShuntingYard ap = new ShuntingYard(tabla, lexico);
//        tabla.mostrarListaSimbolosR();
    }

    public static void main(String[] args) throws ParentesisCierreException, ParentesisAperturaException, ErrorGenerico {
        Test t = new Test();
        t.analizadores();
    }
}
