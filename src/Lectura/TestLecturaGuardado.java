package Lectura;

public class TestLecturaGuardado {
   
    public static void main(String[] args) {
        GeneraYGuarda gg = new GeneraYGuarda();
        gg.GuardarEnArchivo("texto de prueba \n salto de linea", "extension");
    }
}