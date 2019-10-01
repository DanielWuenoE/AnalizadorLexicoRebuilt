package analizador.lexico.c;

public class Tipos {
    
    public boolean esEspacio(int ascii){
        if(ascii == 32 || ascii == 3 || ascii == 9)
            return true;
        else
            return false;
    }
    
    public boolean esCero(int ascii){
        if(ascii == 48 )
            return true;
        else
            return false;
    }
    
    public boolean esMayuscula(int ascii){              //verifica si el caracter es mayuscula
        if(ascii >= 65 && ascii <= 90)
            return true;
        else
            return false;
    }
    
    public boolean esMinuscula(int ascii){              //verifica si el caracter es minuscula
        if(ascii >= 97 && ascii <= 122)
            return true;
        else
            return false;
    }
    
    public boolean esNumero(int ascii){                 //verifica si el caracter es numero
        if(ascii >= 49 && ascii <= 57)
            return true;
        else
            return false;
    }
    
    public boolean esParentesis1(int ascii){
        if(ascii == 40 )
            return true;
        else
            return false;
    }
    
    public boolean esParentesis2(int ascii){
        if(ascii == 41 )
            return true;
        else
            return false;
    }
    
    public boolean esComa(int ascii){
        if(ascii == 44 )
            return true;
        else
            return false;
    }
    
    public boolean esPyC(int ascii){
        if(ascii == 59 )
            return true;
        else
            return false;
    }
    
    public boolean esIgual(int ascii){
        if(ascii == 61 )
            return true;
        else
            return false;
    }
    
    public boolean esDPuntos(int ascii){
        if(ascii == 58 )
            return true;
        else
            return false;
    }
    
    public boolean esMas(int ascii){
        if(ascii == 43 )
            return true;
        else
            return false;
    }
    
    public boolean esMenos(int ascii){
        if(ascii == 45 )
            return true;
        else
            return false;
    }
}
