package Parseo;

import Estructuras.PilaTercetos;

public class TercetosE {
        PilaTercetos p = new PilaTercetos();
        
	private String elem1;
	private String elem2;
	private String elem3;
        private String elem4;
	private String label; //Para marcar a donde saltar (Comentario)
	
        
	public TercetosE(String e1, String e2, String e3) {
		 elem1 = e1;
		 elem2 = e2;
		 elem3 = e3;
	}
        
        public TercetosE(String e1, String e2, String e3, String e4) {
		 elem1 = e1;
		 elem2 = e2;
		 elem3 = e3;
                 elem4 = e4;
	}
	 
	 public String getElem1(){
		 return elem1;
	 }
	 
	 public String getElem2(){
		 return elem2;
	 }
	 
	 public String getElem3(){
		 return elem3;
	 }
         
         public String getElem4(){
		 return elem4;
	 }
	 
	 public void setElem1(String e1){
		 elem1 = e1;
	 }
	 
	 public void setElem2(String e2){
		 elem2 = e2;
	 }
	 
	 public void setElem3(String e3){
		 elem3 = e3;
	 }
         
         public void setElem4(String e4){
		 elem3 = e4;
	 }
	 
         public void setEtiquetaSalto(String lbl){
		 label = lbl;
	 }
         
	 public String getEtiquetaSalto(){
		 return label;
	 }
	 
	 public void mostrarTerceto(){
		 System.out.println("( " + elem1 + ", " + elem2 + ", " + elem3 + " )"); //vemos los Tercetos
	 }
         
         public void mostrarCuarteto(){
		 System.out.println("( " + elem1 + ", " + elem2 + ", " + elem3 + ", " + elem4 + " )"); //vemos los Cuartetos
	 }
}