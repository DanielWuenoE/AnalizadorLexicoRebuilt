package Test;

    import Estructuras.ListasR;
    import Lexico.ClasificaRebuilt;
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
    }
    
    public static void main(String[] args) {
        Test t = new Test();
        t.analizadores();
        
        
    }
}