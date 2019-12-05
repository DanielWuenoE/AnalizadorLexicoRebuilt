package Test;

    //import Errores.ErrorGenerico;
    import Estructuras.ListasR;
    import Lexico.ClasificaRebuilt;
import Parseo.ShuntingYard;
    import Sintactico.MatrizPredictiva;
    import Semantico.ValidacionTipos;

public class Test {
    
    private void analizadores() {
        ListasR tabla = new ListasR();
        ClasificaRebuilt lexico = new ClasificaRebuilt(tabla);
        MatrizPredictiva sintactico = new MatrizPredictiva(lexico);
        sintactico.LlDiver();
        ValidacionTipos semantico = new ValidacionTipos(tabla, lexico);
        semantico.idenficiaVT();
        
        // inicia la fase de Generación de Código intermedio
        ShuntingYard ap = new ShuntingYard(tabla, lexico);
        tabla.mostrarListaSimbolosR();
    }
    
    public static void main(String[] args) {
        Test t = new Test();
        t.analizadores();
    }
}