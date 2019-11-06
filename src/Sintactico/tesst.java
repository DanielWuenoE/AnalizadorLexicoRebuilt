package Sintactico;

import Lexico.ClasificaRebuilt;
import Semantico.ValidacionTipos;

public class tesst {
    
    public static void main(String[] args) {
        
        ClasificaRebuilt Lexico;
        ValidacionTipos Semantico;

        Lexico = new ClasificaRebuilt();
        Semantico = new ValidacionTipos();
        
        MatrizPredictiva m = new MatrizPredictiva(Lexico);
        
        m.LlDiver();    
    }
}