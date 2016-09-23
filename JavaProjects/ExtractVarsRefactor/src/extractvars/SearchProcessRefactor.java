/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extractvars;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Camilin
 */
public class SearchProcessRefactor {

    /**
     *
     * @param f
     * @param separador
     */
    ArrayList<String> process = new ArrayList<>();
    ArrayList<String> resources = new ArrayList<>();
    ArrayList<String> resultado = new ArrayList<>();

    public ArrayList<String> getFiles() {
        return resultado;
    }

    public static List<File> listardirectorio(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
               // System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listardirectorio(file.getAbsolutePath()));
            }
        }
        //System.out.println(fList);
        return resultList;
    }

    public void obtenerlistadepurada(String Directorio) {
        List<File> listado = listardirectorio(Directorio);
        //System.out.println(listado.size());
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getName().endsWith(".process")) {
                process.add(listado.get(i).getAbsolutePath());
               // System.out.println(listado.get(i).getAbsolutePath());
            }
            // En este if se deben agregar las extensiones de los recursos compartidos como bases de datos coenciones sftp ftp etc
            if (listado.get(i).getName().endsWith(".sharedvariable")
                    || listado.get(i).getName().endsWith(".jobsharedvariable")
                    || listado.get(i).getName().endsWith(".sharedjmscon")
                    || listado.get(i).getName().endsWith(".sharedsftp")
                    || listado.get(i).getName().endsWith(".sharedftp")
                    || listado.get(i).getName().endsWith(".sharedjdbc")
                    || listado.get(i).getName().endsWith(".sharedhttp")
                    || listado.get(i).getName().endsWith(".sharedvariable")
                    || listado.get(i).getName().endsWith(".sharedparse")
                    || listado.get(i).getName().endsWith(".sharedjmsapp")
                    ) {
                resources.add(listado.get(i).getAbsolutePath());
               // System.out.println(listado.get(i).getAbsolutePath());
            }
        }

          for (int i = 0; i < resources.size(); i++) {
            for (int j = 0; j < process.size(); j++) {
              File archivo = new File(process.get(j));
              
                //System.out.println("buscando: "+ obtenerpathcorto(resources.get(i)) +" Proceso: "+ process.get(j));
                //boolean aa= FileSearch.find(archivo, obtenerpathcorto(resources.get(i)));
                //System.out.println("Encontrado:? "+aa);
                if (FileSearch.find(archivo, obtenerpathcorto(resources.get(i)))) {
                    resultado.add(resources.get(i));
                    break;
                }
            }
        }
        for (int i = 0; i < process.size(); i++) {
            resultado.add(process.get(i));
        }
    }
    
    
    
    public String obtenerpathcorto(String ruta){    
        String rest;            
        String[] split = ruta.split("\\\\");
        int tamaño=ruta.split("\\\\").length;
      //  System.out.println(tamaño);
        if(tamaño>=2){
        rest = split[tamaño-2]+"/"+split[tamaño-1]; 
     //   System.out.println("resultado: "+rest);
        }
        else{
        rest = split[tamaño-1]; 
     //   System.out.println("resultado: "+rest);
        }
     return rest;   
    }
}
