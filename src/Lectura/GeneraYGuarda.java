package Lectura;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
//import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileNameExtensionFilter;

public class GeneraYGuarda extends javax.swing.JFrame {
    
    private static File archivo = null;

    public void GuardarEnArchivo(String cadena, String nombre) {
            archivo = new File("src/Archivos/" + nombre + ".l");
            System.out.println("Guardado En: " + archivo);
            try {
                //Si Existe el fichero lo borra
                if (archivo.exists())
                    archivo.delete();
                
                BufferedWriter wr = new BufferedWriter(new FileWriter(archivo));
                FileWriter escribirArchivo = new FileWriter(archivo, true);
                BufferedWriter buffer = new BufferedWriter(escribirArchivo);
                buffer.write(cadena);
                buffer.newLine();
                buffer.close();
                wr.close();
                escribirArchivo.close();
            } catch (Exception ex) {
                //Captura un posible error le imprime en pantalla 
                System.out.println(ex.getMessage());
            }
    }
}