package Lectura;

public class NoEsDeString {
    private int numero;
    
    public int cantidadDeCadenas() {
        return numero;
    }
    
    //Método Split
    public String[] noEsEsplit(String Cad, String comodin) {
        //cuantos separadores existen en la cadena
        numero = 0;
        for (int i = 0; i < Cad.length(); i++) {
            if (Cad.charAt(i) == comodin.charAt(0)) {
                numero++;
            }
        }
        String[] vectemp = new String[numero + 1];
        int i = 0;
        while (i < numero) {
            int posicion = Cad.indexOf(comodin);
            
            vectemp[i] = Cad.substring(0, posicion);
            Cad = Cad.substring(posicion + 1);
            i++;
        }
        vectemp[i] = Cad;
        return (vectemp);
        //Victor Viera Balanta;
    }
    
    public String NoEsTrim(String palabra) {
        int inicio = 0, fin = 0;
        // recorre la palabra hasta encontrar un carcater diferente de espacio
        for(int i = 0, c = palabra.length(); i < c; i++) {
            if(palabra.charAt(i) == ' ')
                inicio++;
            else
                break;
        }
        
        for(int i = palabra.length() - 1; i >= 0; i--) {
            if(palabra.charAt(i) == ' ')
                fin++;
            else 
                break;
        }        
        return palabra.substring(inicio, palabra.length() - fin);
    }
}