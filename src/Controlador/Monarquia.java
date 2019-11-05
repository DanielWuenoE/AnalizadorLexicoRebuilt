package Controlador;

// Escribir referencia aquí --

import Estructuras.ListasR;
import Lexico.ClasificaRebuilt;
import Semantico.ValidacionTipos;

public class Monarquia<dato> {
    ValidacionTipos Semantico;
    ClasificaRebuilt Lexico;
    ListasR TablaS;
    
    public Monarquia(ValidacionTipos Semantico, ClasificaRebuilt Lexico, ListasR TablaS) {
        this.Lexico = Lexico;
        this.Semantico = Semantico;
        
        this.TablaS = TablaS;
    }
    
    private void avante() {
    }
    
    public String pedirToken() {
        return Lexico.pedirToken();
    }
    
    public void imprimeTablas() {
        Lexico.imprimeTablas();
    }
    
    public void retroceder() {
        Lexico.retroceder();
    }
    
    public void llenaTabla(String token, String tipoToken, dato valorToken, dato valorIdentificador, String tipoIdentificador, dato vecesRepite) {
        TablaS.agregarElementoLSimbolosR(token, tipoToken, vecesRepite, valorToken, valorIdentificador, tipoIdentificador);
    }
}