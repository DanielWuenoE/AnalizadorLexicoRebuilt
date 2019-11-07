package Lexico;

//**********            Enumerado Palabras Reservadas            **********//
    
enum PalabrasReservadas { // lista de palabras reservadas a usar
        READ (200),
        ID (201),
        WRITE (202),
        INT (203),
        END (204),
        BEGIN (205);
    
    private final int valor;  // valor numérico de cada palabra reservada
    
    private PalabrasReservadas (int valor){  // constructor de la clase enum PalabrasReservadas
        this.valor = valor;
    }
    
    public int getValor(){ // método para conseguir el valor numérico de cada palabrareservada
        return valor;
    }
}

public class PalabraReservada {
    // método que recibe un string (palabra reservada) y verifica si existe en el enumerado
    public boolean ExistePalabraReservada(String pReservada) {
        // ciclo que recorre el enumerado hasta encontrar la palabra recibida
        for (PalabrasReservadas pr : PalabrasReservadas.values()) {
                if (pr.name().toLowerCase().equals(pReservada.toLowerCase()))
                    return true; // si se encuentra regresa un true
        }
        return false; // en caso de que no existe se regresa un false
    }
    // método que devuelve el valor numerico de una palabra reservada
    public int getValorPalabraReservada(String pReservada) {
        // ciclo que recorre el enumerado hasta encontrar la palabra recibida
        for (PalabrasReservadas pr : PalabrasReservadas.values()) {
                if (pr.name().toLowerCase().equals(pReservada.toLowerCase()))
                    return pr.getValor(); // si la encuentra devuelve su valor numérico
        }
        return -1; // en caso contrario masna un -1 que seria el caso de error (no encontrado)
    }
    
    public void reservadas() {
        for (PalabrasReservadas pr : PalabrasReservadas.values()) {
            System.out.println(pr.name() + "\t" + pr.getValor());
        }
    }
    
    public String tipoPalabra(String token) {
        if (ExistePalabraReservada(token))
            return "Palabra Re.";
        else
            return "Identificador";
    }
}

class test {
    public static void main(String[] args) {
        PalabraReservada pr = new PalabraReservada();
        System.out.println(pr.tipoPalabra("bololean"));
        System.out.println("Existe: " + pr.ExistePalabraReservada("int"));
        System.out.println("Palabra: " + pr.getValorPalabraReservada("int"));
        
    }
}