/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.edu;

import es.teis.data.exceptions.LecturaException;
import es.teis.dataXML.IXMLService;
import es.teis.model.Partido;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author a20davidmg
 */
public class DOMXMLService implements IXMLService {

    @Override
    public ArrayList<Partido> leerPartidos(String ruta, float umbral) throws LecturaException {

        long id = 0L;
        String nombre = "";
        int votos = 0;
        float porcentaje = 0f;

        Partido partido = null;
        ArrayList<Partido> partidos = new ArrayList<>();

        try {
            File inputFile = new File("src\\docs\\elecciones.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(IXMLService.PARTIDO_TAG);
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    porcentaje = Float.parseFloat(eElement.getElementsByTagName("votos_porciento").item(0).getTextContent());
                    
                    if (porcentaje > umbral) {
                        id = Long.valueOf(eElement.getAttribute("id"));
                        nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                        votos = Integer.parseInt(eElement.getElementsByTagName("votos_numero").item(0).getTextContent());
                        
                        partido = new Partido(id, nombre, votos, porcentaje);
                        partidos.add(partido);
                    }

                }

            }

        } catch (Exception ex) {
            throw new LecturaException("Error", ruta);
        }

        return partidos;
    }

}
