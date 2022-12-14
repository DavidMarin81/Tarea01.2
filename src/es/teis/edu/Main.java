/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.teis.edu;

import es.teis.data.exceptions.LecturaException;
import es.teis.model.Partido;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class Main {

    private static String ELECCIONES_INPUT_FILE = Paths.get("src", "docs", "elecciones.xml").toString();
    private static String ELECCIONES_OUTPUT_FILE = Paths.get("src", "docs", "elecciones_output.dat").toString();

    private static float UMBRAL_PORCENTAJE = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//completa aquí: 
        DOMXMLService dom = new DOMXMLService();
        PartidoObjectPersistencia pop = new PartidoObjectPersistencia();

        ArrayList<Partido> partidos = new ArrayList<>();

        try {
            partidos = dom.leerPartidos(ELECCIONES_INPUT_FILE, UMBRAL_PORCENTAJE);
            mostrar(partidos);
            pop.escribir(partidos, ELECCIONES_OUTPUT_FILE);
            pop.leerTodo(ELECCIONES_OUTPUT_FILE);
        } catch (LecturaException ex) {
            //Se imprimen las pilas de los errores
            ex.printStackTrace();
            System.out.println("Se ha producido un error de lectura");
        }

    }

    private static void mostrar(ArrayList<Partido> partidos) {
        System.out.println("Se han leído: ");
        for (Partido partido : partidos) {
            System.out.println(partido);
        }
    }

}
