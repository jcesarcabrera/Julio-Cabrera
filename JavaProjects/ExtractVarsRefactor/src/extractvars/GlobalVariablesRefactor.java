/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extractvars;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author camilo
 */
public class GlobalVariablesRefactor {

    /**
     * @param args the command line arguments
     */
    Node etiHi;

    public ArrayList<String> Variables() throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, XPathExpressionException // TODO code application logic here
    {
        String direccion = "C:\\Proyectos\\Unzip\\TIBCO.xml";
        String registros = "";

        // TODO code application logic here
        // Construimos nuestro DocumentBuilder  
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Procesamos el fichero XML y obtenemos nuestro objeto Document  
        Document doc = documentBuilder.parse(new InputSource(new FileInputStream(direccion)));

        // Buscamos una etiqueta dentro del XML con Namespaces  
        NodeList listaNodosNS = doc.getElementsByTagNameNS("http://www.tibco.com/xmlns/dd", "name");

        for (int i = 0; i < listaNodosNS.getLength(); i++) {
            Node nodo = listaNodosNS.item(i);
            if (nodo instanceof Element) {
                registros += nodo.getAttributes().getNamedItem("value").getTextContent();
            }
        }

        // Buscamos una etiqueta mediante XPath.  
        // Implementación de XPath por defecto en Java  
        etiHi = (Node) (XPathFactory.newInstance().newXPath().evaluate("/DeploymentDescriptors/NameValuePairs[2]", doc, XPathConstants.NODE));

        NodeList hijosr = etiHi.getChildNodes();
        // System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        // System.out.println(hijosr.toString());
        // System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //  int iterador = 0;
        //  String[] array = new String[1000];
        ArrayList<String> resultado = new ArrayList<>();
        for (int i = 0; i < hijosr.getLength(); i++) {

            Node nodo = hijosr.item(i);

//            if(nodo != null && (nodo.getNodeName().equals("NameValuePair") || nodo.getNodeName().equals("NameValuePairPassword") 
//                    || nodo.getNodeName().equals("NameValuePairInteger")) ){
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//            System.out.println("Nodo Actual: "+nodo.getTextContent());
//            System.out.println("-------------------------------------------------------------------------------------------");
//            }
            if (nodo instanceof Element) {

                // Nombre del Nodo
                // if (nodo.getNodeName() != null && nodo.getNodeName().equals("name")) {          
                //registros += "VariablesGlobales";               
                // System.out.println("Nombre del Nodo: "+nodo.getNodeName().toString());
                // }
                if (nodo.getNodeName() != null && nodo.getNodeName().equals("NameValuePair")) {
                    if (nodo.getChildNodes().item(1).getNodeName().equals("name")) {
                        //        array[iterador] = nodo.getChildNodes().item(1).getTextContent();
                        resultado.add(nodo.getChildNodes().item(1).getTextContent());
                        //     iterador++;
                        // System.out.println("INFOO"+nodo.getTextContent());
                    }
                }

                if (nodo.getNodeName() != null && nodo.getNodeName().equals("NameValuePairPassword")) {
                    if (nodo.getChildNodes().item(1).getNodeName().equals("name")) {
                        //     array[iterador] = nodo.getChildNodes().item(1).getTextContent();
                        resultado.add(nodo.getChildNodes().item(1).getTextContent());
                        //   iterador++;
                        // System.out.println("passs"+nodo.getTextContent());
                    }
                }

                if (nodo.getNodeName() != null && nodo.getNodeName().equals("NameValuePairInteger")) {
                    if (nodo.getChildNodes().item(1).getNodeName().equals("name")) {
                        //     array[iterador] = nodo.getChildNodes().item(1).getTextContent();
                        resultado.add(nodo.getChildNodes().item(1).getTextContent());
                        //     iterador++;
                        //     System.out.println("interr" + nodo.getTextContent());
                    }
                }

                if (nodo.getNodeName() != null && nodo.getNodeName().equals("NameValuePairBoolean")) {
                    if (nodo.getChildNodes().item(1).getNodeName().equals("name")) {
                        //  array[iterador] = nodo.getChildNodes().item(1).getTextContent();
                        resultado.add(nodo.getChildNodes().item(1).getTextContent());
                        //    iterador++;
                        //  System.out.println("BOOO"+nodo.getTextContent());
                    }
                }

            }
        }
        return resultado;
    }

    public void getVars(String ww) throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException, TransformerException {

        NodeList lista = etiHi.getChildNodes();

        for (int i = 0; i < lista.getLength(); i++) {
            Node nodo = lista.item(i);
            if (nodo instanceof Element) {
                //     System.out.println (nodo.getChildNodes().toString());

                if (nodo.getNodeName() != null && nodo.getNodeName().equals("NameValuePair")) {
                    if (nodo.getChildNodes().item(1).getNodeName().equals("name")) {
                        // String compare ="";                                     
                        if (nodo.getChildNodes().item(1).getFirstChild().getNodeValue().equals(ww)) {
                            // System.out.println(nodo.getChildNodes().item(3).getFirstChild());
                            //  System.out.println(nodo.getChildNodes().item(1).getFirstChild());
                            //DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                            //DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                            //Document doc = docBuilder.parse("c:\\sda.xml");  

                            //Transformer transformer = TransformerFactory.newInstance().newTransformer();
                            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            //StreamResult result = new StreamResult(new StringWriter());
                            // DOMSource source = new DOMSource(nodo);
                            //  String xmlString = result.getWriter().toString();
                            //   System.out.println(" XML "+xmlString);
                            TransformerFactory transfac = TransformerFactory.newInstance();
                            Transformer trans = transfac.newTransformer();
                            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                            trans.setOutputProperty(OutputKeys.INDENT, "yes");

// Print the DOM node
                            StringWriter sw = new StringWriter();
                            StreamResult result = new StreamResult(sw);
                            DOMSource source = new DOMSource(nodo);
                            trans.transform(source, result);
                            String xmlString = sw.toString();
                            System.out.print(xmlString);

                        }
                    }
                }
                if (nodo.getNodeName() != null && nodo.getNodeName().equals("NameValuePairPassword")) {
                    if (nodo.getChildNodes().item(1).getNodeName().equals("name")) {
                        if (nodo.getChildNodes().item(1).getFirstChild().getNodeValue().equals(ww)) {
                            TransformerFactory transfac = TransformerFactory.newInstance();
                            Transformer trans = transfac.newTransformer();
                            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                            trans.setOutputProperty(OutputKeys.INDENT, "yes");

// Print the DOM node
                            StringWriter sw = new StringWriter();
                            StreamResult result = new StreamResult(sw);
                            DOMSource source = new DOMSource(nodo);
                            trans.transform(source, result);
                            String xmlString = sw.toString();
                            System.out.print(xmlString);
                        }
                    }
                }
                if (nodo.getNodeName() != null && nodo.getNodeName().equals("NameValuePairInteger")) {
                    if (nodo.getChildNodes().item(1).getNodeName().equals("name")) {
                        if (nodo.getChildNodes().item(1).getFirstChild().getNodeValue().equals(ww)) {
                            TransformerFactory transfac = TransformerFactory.newInstance();
                            Transformer trans = transfac.newTransformer();
                            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                            trans.setOutputProperty(OutputKeys.INDENT, "yes");

//Print the DOM node
                            StringWriter sw = new StringWriter();
                            StreamResult result = new StreamResult(sw);
                            DOMSource source = new DOMSource(nodo);
                            trans.transform(source, result);
                            String xmlString = sw.toString();
                            System.out.print(xmlString);
                        }
                    }
                }
                if (nodo.getNodeName() != null && nodo.getNodeName().equals("NameValuePairBoolean")) {
                    if (nodo.getChildNodes().item(1).getNodeName().equals("name")) {
                        if (nodo.getChildNodes().item(1).getFirstChild().getNodeValue().equals(ww)) {
                            TransformerFactory transfac = TransformerFactory.newInstance();
                            Transformer trans = transfac.newTransformer();
                            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                            trans.setOutputProperty(OutputKeys.INDENT, "yes");

//Print the DOM node
                            StringWriter sw = new StringWriter();
                            StreamResult result = new StreamResult(sw);
                            DOMSource source = new DOMSource(nodo);
                            trans.transform(source, result);
                            String xmlString = sw.toString();
                            System.out.print(xmlString);
                        }
                    }
                }
            }
        }
    }
}
