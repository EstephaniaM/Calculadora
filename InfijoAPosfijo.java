
package Calculadora;

import Pilas.PilaA;
import java.util.ArrayList;

public class InfijoAPosfijo {
    
    public InfijoAPosfijo(){
    }
    //Metodo auxiliar para asignar el valor de los operadores para jerarquia
    private static int jerarquia(String operador){
        int jerarquia=0; 
        switch (operador){
            case "*":
                jerarquia = 3;
                break;
            case "/":
                jerarquia = 3;
                break;
            case "+":
                jerarquia = 2;
                break;
            case "-":
                jerarquia = 2;
                break;
            case "^":
                jerarquia = 4;
                break;
            case "(":
                jerarquia = 1;
                break;
            default: 
                jerarquia = 0;
                break;
        }
        return jerarquia;
    }
    //Método auxiliar para revisar si el char es operador
    private static boolean checarOperador(String c){
        boolean resp = false; 
        if(c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/")||c.equals("^"))
            resp = true;
        return resp;  
    }
    //Método auxiliar para revisar si el char es un parentesis
    private static boolean parentesis(String c){
        boolean resp = false; 
        if(c.equals("(")||c.equals(")"))
            resp = true;
        return resp;   
    }
  
    //Metodo para convertir en posfijo basado en un string
    public static ArrayList<String> conviertePosfijo(ArrayList<String> infijo){
        PilaA <String> pila = new PilaA (100);  //Pila en donde guarda los operadores
        ArrayList<String> posfijo = new ArrayList(); //ArrayList del resultado
        String c;
        for(int i=0; i<infijo.size();i++){
            c = infijo.get(i);
            if(checarOperador(c)){ //Si el char es un operador revisa la jerarquia de pila.peek() y saca o mete de la pila
                while(!pila.isEmpty() && jerarquia(pila.peek())>=jerarquia(c))
                    posfijo.add(pila.pop());
                pila.push(c);
            }
            if(!checarOperador(c) && !parentesis(c)) //Si no es operador o parentesis, agrega al ArrayList posfijo
               posfijo.add(c);     
            else {
                if(c.equals("(")) //Agrega a la pila directo
                    pila.push(c);
                if(c.equals(")")){
                    while(!pila.peek().equals("(")) //Saca todo de la pila hasta que no encuentre el parentesis abierto
                        posfijo.add(pila.pop());
                    pila.pop();
                } 
            }   
        } //Una vez que se termino de revisar el ArrayList infijo, vacia la pila de operadores
        while(!pila.isEmpty())
            posfijo.add(pila.pop());
        return posfijo;
    }
    public static double evaluaPosfijo(ArrayList<String> posfijo){
        
        PilaA <Double> pila = new PilaA(100);
        int i=0; 
        boolean bandera = true;
        String c;
        while(i<posfijo.size() && bandera){
           c = posfijo.get(i);
           if(!checarOperador(c))
               pila.push(Double.parseDouble(c));
           else {
                double n1 = pila.pop();
                double n2 = pila.pop();
                switch(c){
                    case "+":
                        pila.push(n1+n2);
                        break;
                    case "-":
                        pila.push(n2-n1);
                        break;
                    case "/":
                        if(n1==0)
                            bandera = false;
                        else
                            pila.push(n2/n1);
                        break;
                    case "^":
                        pila.push(Math.pow(n2, n1));
                        break;
                    default:
                        pila.push(n1*n2);
                        break;
                }    
            }
           i++;
        }
        return pila.pop();
    }

    
    public static void main (String[]args){
        //Checando metodos auxiliares: Jerarquia
        System.out.println("Jerarquia: "+jerarquia("a"));
        System.out.println("Jerarquia: "+jerarquia("*"));
        System.out.println("Jerarquia: "+jerarquia("/"));
        System.out.println("Jerarquia: "+jerarquia("-"));
        System.out.println("Jerarquia: "+jerarquia("^"));
        System.out.println("Jerarquia: "+jerarquia("+"));
        System.out.println("Jerarquia: "+jerarquia("("));
        //Checando programa posfijo
        ArrayList<String> array = new ArrayList();
        array.add("a");
        array.add("+");
        array.add("b");
        array.add("*");
        array.add("d");
        array.add("/");
        array.add("z");
        System.out.println("Checando posfijo: "+conviertePosfijo(array));
        ArrayList<String> array2 = new ArrayList();
        array2.add("a");
        array2.add("+");
        array2.add("(");
        array2.add("b");
        array2.add("-");
        array2.add("c");
        array2.add(")");
        System.out.println("Checando posfijo: "+conviertePosfijo(array2));
        ArrayList<String> array3 = new ArrayList();
        array3.add("2");
        array3.add("+");
        array3.add("3");
        array3.add("*");
        array3.add("4");
        array3.add("-");
        array3.add("1");
        System.out.println("Convierte posfijo: "+conviertePosfijo(array3));
        ArrayList<String> array4 = new ArrayList();
        array4.add("2");
        array4.add("3");
        array4.add("+");
        System.out.println("Checando evaluacion: "+evaluaPosfijo(array4));
        ArrayList<String> array5 = new ArrayList();
        array5.add("2");
        array5.add("3");
        array5.add("/");
        array5.add("4");
        array5.add("+");
        array5.add("1");
        array5.add("-");
        System.out.println("Checando evaluacion: "+evaluaPosfijo(array5));
        ArrayList<String> array6 = new ArrayList();
        array6.add("2");
        array6.add("3");
        array6.add("4");
        array6.add("*");
        array6.add("5");
        array6.add("/");
        array6.add("+");
        System.out.println("Checando evaluacion: "+evaluaPosfijo(array6));
    }
}  

