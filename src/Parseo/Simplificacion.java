//package Parseo;
//
//public class Simplificacion {
//    public void simplificacionSentencia(Lista s){
//        Lista t;
//        String simple = "";
//        int op = numeroOperadores(s), op2 = 0;      //busqueda de operadores en sentencia
////        s = invertirCadena(s);                      //inversion de la cadena
//        for (int i = 0; i < s.length(); i++) {      //revision de sentencia
//            t=s.sigToken();
//            if ((t=='-')||(t=='+')) {   //si encuentra un signo
//                op--;       //disminuye el contador
//            }
//            if(op==op2){    //si el contador es la posicion buscada
//                op2++;      //aumenta posicion
//                if () {     //si hay dos numeros despues del signo
//                    
//                }else if () {   //si hay un numero antes y uno despues (crear v. temp.)
//                    
//                }
//            }
//        }
//    }
////    
////    public String invertirCadena(String c){
////        String cc = "";
////        for (int i = c.length()-1; i >= 0; i--) {
////            cc = cc + c.charAt(i);
////        }
//////        System.out.println(cc);
////        return cc;
////    }
//    
//    public int numeroOperadores(String e){
//        int contador = 0;
//        for (int i = 0; i < e.length(); i++) {      //NOTA: CAMBIAR LENGHT POR TOKENS DE LISTA
//            if ((e.charAt(i)=='-')||(e.charAt(i)=='+')) {
//                contador++;
//            }
//        }
////        System.out.println(contador);
//        return contador;
//    }
//    
//    public static void main(String[] args) {
//        Simplificacion s = new Simplificacion();
//        s.invertirCadena("cad-e+n:=a++");
//        s.numeroOperadores("cad-e+n:=a++");
//    }
//}
