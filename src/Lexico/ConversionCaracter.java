package Lexico;

public class ConversionCaracter {
    
    private int ascii = 0;
    private char carac;
    
    public void convertirCaracter(char caracter){       //convierte caracter a ascii
        ascii = (int) caracter;
    }
    
    public int getAscii(){                              //devuelve el valor en ascii
        return ascii;
    }
    
    public int getAscii(char caracter){                              //devuelve el valor en ascii
        return (int) caracter;
    }
    
    public void convertirAscii (int numero) {           //convierte ascii a caracter
        carac = (char)numero;
    }
    
    public char getCaracter(){                          //devuelve el caracter
        return carac;
    }
}
