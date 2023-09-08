/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas;

/**
 *
 * @author estephaniamartinez
 */
public class PilaA <T> implements PilaADT<T>{
    private T[] pila;
    private int tope;
    private final int MAX_PILA = 20;

    public PilaA() {
        pila = (T[]) new Object[MAX_PILA];
        tope =-1;
        
    }
    
     public PilaA(int max) {
        pila = (T[]) new Object[max];
        tope =-1;
        
    }
    
     
    

    @Override
    public void push(T dato) {
        if(tope == pila.length-1)//No hay espacio
            expande();
        tope++;
        pila[tope] = dato;
        
    }
    
    private void expande(){
        T[] masGrande = (T[]) new Object[pila.length*2];
        
        for(int i =0; i<pila.length; i++)//Puede hacerse también al tope
            masGrande[i] = pila[i];
        pila = masGrande;
            
        
        
    }


    public T pop() {    //En vez de regresar un nulo lanzo una excepción 
       if(isEmpty())
           throw new ExcepcionColeccionVacia("La pila está vacia");
       T resultado = pila[tope];
       pila[tope]=null;
       tope--;
       
       return resultado;
                
    }

    @Override
    public boolean isEmpty() {
        return tope == -1;
    }

    @Override
    public T peek() {
        if(isEmpty())
           throw new ExcepcionColeccionVacia("La pila está vacia");
       return pila[tope];

                
    }
    
    
 
    
    
}
