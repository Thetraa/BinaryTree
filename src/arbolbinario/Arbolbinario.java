/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author joel
 */
class nodo{
    int valor;
    nodo izq;
    nodo der;

    public nodo(int valor) {
        this.valor = valor;
        this.der = null;
        this.izq = null;
    }
    
}
public class Arbolbinario {
    public static nodo insert(nodo r, int value) {
    if (r == null) {
        return new nodo(value);
    }
 
    if (value < r.valor) {
        r.izq = insert(r.izq, value);
    } else if (value > r.valor) {
        r.der = insert(r.der, value);
    } else {
        // value already exists
        return r;
    }
 
    return r;
}
    public  static nodo CargaArchivo(String archivo_csv){
        nodo n = null;
        try {
            Scanner sc = new Scanner(new File(archivo_csv));
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isEmpty()){
                    continue;
                }
                String[] arreglo = linea.split(",");
                for (String a : arreglo) {
                    n = insert(n, Integer.parseInt(a));
                }
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        
        return n;
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        nodo as=CargaArchivo(args[0]);
        System.out.println("Nodo a buscar: "+args[1]);
        System.out.println("preorder");
        preorder(as, Integer.parseInt(args[1]));
        System.out.println("-------------------------");
        System.out.println("inorder");
        inorder(as, Integer.parseInt(args[1]));
        System.out.println("------------------------.");
        System.out.println("postorder");
        postorder(as, Integer.parseInt(args[1]));
    }
    public static void preorder(nodo n, int busca){
        if (n!=null){
            if (busca==n.valor) {
                System.out.print("-> ");
            }
            despliega(n);
            preorder(n.izq, busca);
            preorder(n.der, busca);
        }
    }
   public static void despliega(nodo n){
       System.out.println(n.valor);
   }
   public static void inorder(nodo n, int busca){
        if (n!=null){
            inorder(n.izq, busca);
            if (busca==n.valor) {
                System.out.print("-> ");
            }
            despliega(n);
            inorder(n.der, busca);
        }
    }
   public static void postorder(nodo n,int busca){
        if (n!=null){
            postorder(n.izq, busca);
            postorder(n.der, busca);
            if (busca==n.valor) {
                System.out.print("-> ");
            }
            despliega(n);
        }
    }
}
