package Parseo;

public class TercetosTest {
    public static void main(String[] args) {
        
        Terceto t = new Terceto();
        
        t.Terceto("9", "98", "83");
        t.Terceto("7", "13", "23");
        t.Terceto("2", "81", "09");
        
        t.imprimeTodo();
    }
}