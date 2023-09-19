r
package Calculadora;

import Pilas.PilaA;
import java.util.ArrayList;
/**
 * 
 * @author Grupo3 Estructura de Datos Otoño 2023 Grupo 001
 */
public class InfijoAPosfijo {
    
    public InfijoAPosfijo(){
    }
   /**
    * Método auxiliar para asignar el valor de los operadores y obtener una jerarquía
    * <pre>
    * Utiliza un switch para identificar el tipo de operador
    * Cada caso asigna un valor distinto de acuerdo a la jerarquía de las operaciones
    * <pre>
    * @param operador String
    * @return int valor asignado al operador 
    */
    
    private static int jerarquia(String operador){
        int jerarquia=0; 
        switch (operador){
            case "*": //Tanto la multiplicacion como la división son de la misma jerarquía 
                jerarquia = 3;
                break;
            case "/":
                jerarquia = 3;
                break;
            case "+": //La suma y la resta son de la misma jerarquía
                jerarquia = 2;
                break;
            case "-":
                jerarquia = 2;
                break;
            case "^": //La potencia tiene la máxima jerarquía después del paréntesis
                jerarquia = 4;
                break;
            case "(": //Menor jerarquía para que lo saque al momento de encontrar su contraparte
                jerarquia = 1;
                break;
            default: 
                jerarquia = 0;
                break;
        }
        return jerarquia;
    }
    /**
     * Método auxiliar para revisar si el String es operador
     * @param c String
     * @return boolean <ul>
     * <li> true: si es un operador "+","-","*","/","^"
     * <li> false: si no es un operador
     * <ul>
     */

    private static boolean checarOperador(String c){
        boolean resp = false; 
        if(c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/")||c.equals("^"))
            resp = true;
        return resp;  
    }
    /**
     * Método auxiliar para revisar si el String es un paréntesis
     * @param c String
     * @return boolean <ul>
     * <li> true: si el String es "(" o ")"
     * <li> false: si el String no es "(" o ")"
     * <ul>
     */
    private static boolean checarParentesis(String c){
        boolean resp = false; 
        if(c.equals("(")||c.equals(")"))
            resp = true;
        return resp;   
    }
    /**
     * Método para convertir una expresión en String de infijo a posfijo
     * @param infijo El ArrayList en el que está guardada la expresión en infijo como escrita por el cliente
     * @return ArrayList de String: expresión convertida a posfijo
     * @see parentesis
     * @see checarOperador
     */

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
    /**
     * Método para evaluar una expresión en posfijo
     * <pre>
     * Toma una expresión en posfijo y a través de pilas evalúa las operaciones
     * A través de un switch se identifica al operador y asigna la operación a realizar correspondiente
     * La única operación restringida es la división por cero a través de una bandera
     * <pre>
     * @param posfijo ArrayList de la expresión infija convertida a posfija 
     * @return Double: resultado final de la operación ingresada por el usuario
     * @see checarOperador
     */
    
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
        //Checando programa posfijo: utilizando otro ArrayList en infijo
        ArrayList<String> array2 = new ArrayList();
        array2.add("a");
        array2.add("+");
        array2.add("(");
        array2.add("b");
        array2.add("-");
        array2.add("c");
        array2.add(")");
        System.out.println("Checando posfijo: "+conviertePosfijo(array2));
        //Checando programa posfijo: utilizando otro ArrayList en infijo
        ArrayList<String> array3 = new ArrayList();
        array3.add("2");
        array3.add("+");
        array3.add("3");
        array3.add("*");
        array3.add("4");
        array3.add("-");
        array3.add("1");
        System.out.println("Convierte posfijo: "+conviertePosfijo(array3));
        //Checando programa para evaluar posfijo: utilizando un posfijo sencillo
        ArrayList<String> array4 = new ArrayList();
        array4.add("2");
        array4.add("3");
        array4.add("+");
        System.out.println("Checando evaluacion: "+evaluaPosfijo(array4));
        //Checando programa para evaluar posfijo: utilizando posfijo más complicado
        ArrayList<String> array5 = new ArrayList();
        array5.add("2");
        array5.add("3");
        array5.add("/");
        array5.add("4");
        array5.add("+");
        array5.add("1");
        array5.add("-");
        System.out.println("Checando evaluacion: "+evaluaPosfijo(array5));
        //Checando programa para evaluar posfijo: utilizando un tercer posfijo
        ArrayList<String> array6 = new ArrayList();
        array6.add("2");
        array6.add("3");
        array6.add("4");
        array6.add("*");
        array6.add("5");
        array6.add("/");
        array6.add("+");
        System.out.println("Checando evaluacion: "+evaluaPosfijo(array6));
        //Checando programa para evaluar posfijo: se prueban los números negativos 
        ArrayList<String> array7 = new ArrayList();
        array6.add("-2");
        array6.add("3");
        array6.add("-4");
        array6.add("*");
        array6.add("5");
        array6.add("/");
        array6.add("+");
        System.out.println("Checando evaluacion: "+evaluaPosfijo(array7));
        //NOTA: Tomar en consideración que el programa puede confundir números negativos con signo de resta
    }
} 

