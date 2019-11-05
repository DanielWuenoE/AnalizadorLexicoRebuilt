package Semantico;

import Lexico.Tipos;
import Lexico.ConversionCaracter;

public class ValidacionTipos {
    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    
    public boolean validacionInt(String token){
        boolean flag = false;
        for (int i = 0; i < token.length(); i++) {
            conv.convertirCaracter(token.charAt(i));
            if (!tipo.esNumero(conv.getAscii())) {
                System.out.println("Error semántico en: "+token+"\nSe esperaba un dato de tipo entero");
                flag = true;
                return flag;
            }
        }
        if (!flag) {
            System.out.println("Análisis semántico terminado con éxito");
            flag = false;
            return flag;
        }
        return flag;
    }
    
    public void idenficiaVT(String codigo) {
        
    }
    
    public static void main(String[] args) {
        ValidacionTipos var = new ValidacionTipos();
        
        var.validacionInt("123e");
        System.out.println("");
        var.validacionInt("1234");
        System.out.println("");
        var.validacionInt("1e23");
    }
}
