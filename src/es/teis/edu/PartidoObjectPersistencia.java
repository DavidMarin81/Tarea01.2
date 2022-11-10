/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.edu;

import es.teis.data.IPersistencia;
import es.teis.model.Partido;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Marín
 */
//Se tiene que implementar la interface Serializable
public class PartidoObjectPersistencia implements IPersistencia, Serializable {

    @Override
    public void escribir(ArrayList<Partido> partidos, String ruta) {
        try (
                 FileOutputStream fos = new FileOutputStream(ruta);  ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            for (Partido p : partidos) {
                //Instanceof Partido o Serializable???
                if (p instanceof Partido) {
                    oos.writeObject(p);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PartidoObjectPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Partido> leerTodo(String ruta) {
        ArrayList<Partido> partidos = new ArrayList<>();
        try (
                 FileInputStream fis = new FileInputStream(ruta);  
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            Object aux;
            do {
                aux = ois.readObject();
                if (aux instanceof Serializable) {
                    partidos.add((Partido) aux);
                }
            } while (aux != null);

        } catch (EOFException ex) {
//            Fin de lectura de fichero
//            Se imprimen los datos leidos del xml "elecciones_output.xml"
//            System.out.println("\nDatos del fichero elecciones_output.xml");
//            for (Partido p : partidos) {
//                System.out.println(p.toString());
//            }
        } catch (IOException ex) {
            Logger.getLogger(PartidoObjectPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(PartidoObjectPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partidos;
    }

}
