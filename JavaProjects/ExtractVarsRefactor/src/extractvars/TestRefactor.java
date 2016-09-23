/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractvars;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author camilo prieto, jpalvis cocreador del codigo y asesor experto (MAESTRO)
 */
public class TestRefactor {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, FileNotFoundException, XPathExpressionException, TransformerException {
        Boolean Deployment = false;
        Boolean DirLedger = false;
        Boolean DirTrace = false;
        Boolean Domain = false;
        Boolean HawkEnabled = false;
        Boolean JmsProviderUrl = false;
        Boolean JmsSslProviderUrl = false;
        Boolean RemoteRvDaemon = false;
        Boolean RvDaemon = false;
        Boolean RvNetwork = false;
        Boolean RvService = false;
        Boolean RvaHost = false;
        Boolean RvaPort = false;
        Boolean TIBHawkDaemon = false;
        Boolean TIBHawkNetwork = false;
        Boolean TIBHawkService = false;
        Boolean MessageEncoding = false;
        
        
        UnZip descomprimir = new UnZip();
        String PathSalida = "C:\\nprieto\\UnrarEar";
        String PathOrigen = "C:\\nprieto\\DaviviendaHCS.ear";
        descomprimir.unZipIt(PathOrigen, PathSalida);
        File dir = new File(PathSalida);
        File[] ficheros = dir.listFiles();
        for (int i = 0; i < ficheros.length; i++) {
            if (ficheros[i].getName().endsWith(".par")) {
                descomprimir.unZipIt(ficheros[i].getAbsolutePath(), PathSalida+"\\_par");
            }
            if (ficheros[i].getName().endsWith(".sar")) {
                descomprimir.unZipIt(ficheros[i].getAbsolutePath(), PathSalida+"\\_sar");
            }
        }        
//      Esta la debemos cambiar es el directorio donde quedo el ear descomprimido    
        //String sDirectorio = "C:\\nprieto\\UnrarEar\\both";
//        File directorio = new File(sDirectorio);
        SearchProcessRefactor a = new SearchProcessRefactor();
        // a.obtenerlista(sDirectorio);
        //a.listarDirectorio(directorio, "");
        //a.obtenerpathcorto("C:\\nprieto\\UnrarEar\\both\\_sar\\GetNoveltysFromBank\\JDBCTIBCO-PR.sharedjdbc");
        a.obtenerlistadepurada(PathSalida);
//        System.out.println(a.getFiles().size());
//        for (int i = 0; i < a.getFiles().size(); i++) {
//            System.out.println(a.getFiles().get(i));
//        }

        GlobalVariablesRefactor gv = new GlobalVariablesRefactor();
        for (int i = 0; i < gv.Variables().size(); i++) {
            for (int j = 0; j < a.getFiles().size(); j++) {
                File archivo = new File(a.getFiles().get(j));
                if (FileSearch.find(archivo, "%%" + gv.Variables().get(i) + "%%")
                        || FileSearch.find(archivo, ":GlobalVariables/" + gv.Variables().get(i))) {
                    gv.getVars(gv.Variables().get(i));
                    if ("Deployment".equals(gv.Variables().get(i))) {
                        Deployment = true;
                    }
                    if ("DirLedger".equals(gv.Variables().get(i))) {
                        DirLedger = true;
                    }
                    if ("DirTrace".equals(gv.Variables().get(i))) {
                        DirTrace = true;
                    }
                    if ("Domain".equals(gv.Variables().get(i))) {
                        Domain = true;
                    }
                    if ("HawkEnabled".equals(gv.Variables().get(i))) {
                        HawkEnabled = true;
                    }
                    if ("JmsProviderUrl".equals(gv.Variables().get(i))) {
                        JmsProviderUrl = true;
                    }
                    if ("JmsSslProviderUrl".equals(gv.Variables().get(i))) {
                        JmsSslProviderUrl = true;
                    }
                    if ("RemoteRvDaemon".equals(gv.Variables().get(i))) {
                        RemoteRvDaemon = true;
                    }
                    if ("RvDaemon".equals(gv.Variables().get(i))) {
                        RvDaemon = true;
                    }
                    if ("RvNetwork".equals(gv.Variables().get(i))) {
                        RvNetwork = true;
                    }
                    if ("RvService".equals(gv.Variables().get(i))) {
                        RvService = true;
                    }
                    if ("RvaHost".equals(gv.Variables().get(i))) {
                        RvaHost = true;
                    }
                    if ("RvaPort".equals(gv.Variables().get(i))) {
                        RvaPort = true;
                    }
                    if ("TIBHawkDaemon".equals(gv.Variables().get(i))) {
                        TIBHawkDaemon = true;
                    }
                    if ("TIBHawkNetwork".equals(gv.Variables().get(i))) {
                        TIBHawkNetwork = true;
                    }
                    if ("TIBHawkService".equals(gv.Variables().get(i))) {
                        TIBHawkService = true;
                    }
                    if ("MessageEncoding".equals(gv.Variables().get(i))) {
                        MessageEncoding = true;
                    }
                    break;
                }
            }
        }

        if (Deployment == false) {
            gv.getVars("Deployment");
        }
        if (DirLedger == false) {
            gv.getVars("DirLedger");
        }
        if (DirTrace == false) {
            gv.getVars("DirTrace");
        }
        if (Domain == false) {
            gv.getVars("Domain");
        }
        if (HawkEnabled == false) {
            gv.getVars("HawkEnabled");
        }
        if (JmsProviderUrl == false) {
            gv.getVars("JmsProviderUrl");
        }
        if (JmsSslProviderUrl == false) {
            gv.getVars("JmsSslProviderUrl");
        }
        if (RemoteRvDaemon == false) {
            gv.getVars("RemoteRvDaemon");
        }
        if (RvDaemon == false) {
            gv.getVars("RvDaemon");
        }
        if (RvNetwork == false) {
            gv.getVars("RvNetwork");
        }
        if (RvService == false) {
            gv.getVars("RvService");
        }
        if (RvaHost == false) {
            gv.getVars("RvaHost");
        }
        if (RvaPort == false) {
            gv.getVars("RvaPort");
        }
        if (TIBHawkDaemon == false) {
            gv.getVars("TIBHawkDaemon");
        }
        if (TIBHawkNetwork == false) {
            gv.getVars("TIBHawkNetwork");
        }
        if (TIBHawkService == false) {
            gv.getVars("TIBHawkService");
        }
        if (MessageEncoding == false) {
            gv.getVars("MessageEncoding");
        }  
    }
}
