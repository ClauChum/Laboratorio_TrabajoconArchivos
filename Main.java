/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author palit
 */

public class Main {
    
    private void EscrituraBinario(String nombre, byte edad, int telefono){
        byte separador = '!';
        try {
            RandomAccessFile archivo = new RandomAccessFile("TareAgenda.bin","rw");
            archivo.seek(archivo.length());
            archivo.writeUTF(nombre);
            archivo.writeByte(edad);
            archivo.writeInt(telefono);
            archivo.writeByte(separador);
            archivo.close();
        } catch (FileNotFoundException exit) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exit);
        } catch (IOException exit) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exit);
        } 
    }
    
    private void LecturaBinario(){
        try {
            RandomAccessFile archivo = new RandomAccessFile("TareAgenda.bin","r");            
            String nombree;
            byte[] separador = new byte[1];
            byte nombre[] = new byte[30];
            byte edad=0;
            int telefono=0;
            boolean masregistros=true;
            while(masregistros==true){
                try{
                nombree = archivo.readUTF();
                edad = archivo.readByte();
                telefono = archivo.readInt();
                archivo.read(separador);
                System.out.println('\n' + "Nombre: " + new String(nombree) + ", " + "Telefono: " + telefono + ", " + "Edad: " + edad);
                } catch (IOException ex) {
                   masregistros = false;
                   }
            }
            archivo.close();
        } catch (FileNotFoundException exit) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exit);
        } catch (IOException exit) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exit);
        }        
    }
    
    private void EscrituraTamano(String Nombre, byte Edad, int Telefono){
        try {
            RandomAccessFile archivo = new RandomAccessFile("TareAgendaa.bin","rw");
            archivo.seek(archivo.length());
            archivo.writeBytes(Nombre);
            archivo.writeByte(Edad);
            archivo.writeInt(Telefono);
            archivo.close();
        } catch (FileNotFoundException exit) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exit);
        } catch (IOException exit) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exit);
        } 
    }
    
    private void LecturaTamano(){
        try {
            RandomAccessFile archivo = new RandomAccessFile("TareAgendaa.bin","r");            
            byte nombre[] = new byte[30];
            byte edad=0;
            int telefono=0;
            while(archivo.read(nombre) != -1){
                edad = archivo.readByte();
                telefono = archivo.readInt();
                System.out.println('\n' + "Nombre: " + new String(nombre) + ", " + "Telefono: " + telefono + ", " + "Edad: " + edad);
            }
            archivo.close();
        } catch (FileNotFoundException exit) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exit);
        } catch (IOException exit) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exit);
        }        
    }
  
    public static void main(String[] args) {
        // TODO code application logic here
     Scanner scan = new Scanner(System.in);
     Scanner scan1 = new Scanner(System.in);
     Main main  = new Main();
        char[] Nombre = new char[30];
        for(int i=0;i < 30;i++){
           Nombre[i] = '0';
        }
        byte opcionmenu;
        
        do{
            System.out.println("1.Ingresar datos");
            System.out.println("2.Ver datos");
            System.out.println("3.salir");
            opcionmenu = scan1.nextByte();
            
            
            switch(opcionmenu)
            {
                case 1:
                    scan.nextLine();
                System.out.println("Nombre: ");
                String nombre = scan.nextLine();
                String nombreV;
                char[] NombreV = new char[nombre.length()];
                for(int i=0;i<nombre.length();i++){
                    Nombre[i] = nombre.charAt(i);
                    NombreV[i] = nombre.charAt(i);
                }
                nombreV = String.valueOf(NombreV);
                nombre = String.valueOf(Nombre);
                System.out.println("Telefono:  ");
                int telefono = scan.nextInt();
                System.out.println("Edad: ");
                byte edad = scan.nextByte();
                main.EscrituraBinario(nombreV, edad, telefono);
                main.EscrituraTamano(nombre, edad, telefono);
                System.out.println("Datos Almacenados"); 
                    break;
                    
                case 2:
                System.out.println("\n ----- Datos agenda separador binario: ----- ");
                main.LecturaBinario();
                System.out.println("\n ----- Datos agenda separador tamaño: ----- ");
                main.LecturaTamano();
                    break;
            }       
        System.out.println("");
        }while(opcionmenu != 3);
    }
}

