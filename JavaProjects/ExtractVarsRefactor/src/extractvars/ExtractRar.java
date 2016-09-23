/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extractvars;
import java.io.File;

/**
 *
 * @author Camilin
 */
public class ExtractRar {

static String p[] = new String[255];
      


    public static void main(String[] args) throws Exception{
          int x = 1;
        try {
             File folder2 = new File("C:\\nprieto\\UnrarEar\\");
            folder2.mkdir();
            
            File folder = new File("C:\\nprieto\\UnrarEar\\both\\");
            folder.mkdir();
            
            
//           try 
//           {
//            String[] cmd = {"rd C:\\UnrarEar /S/Q"};
//            System.out.println("---"+cmd.toString());
//                Runtime.getRuntime().exec(cmd);
//               
//           }
//           catch( IOException e){
//                System.out.println("HOLA "+e);
//           }

            String[] cmd= new String[] {"C:\\Program Files (x86)\\WinRAR\\winrar.exe", "x" , "C:/nprieto/AVVillasHCS.ear", "C:\\nprieto\\UnrarEar\\"};
            Runtime.getRuntime().exec(cmd);
            
            Thread.sleep(20000);
            File dir = new File("C:\\nprieto\\UnrarEar\\");
            File[] ficheros=dir.listFiles();
            
                                
            
            for(int i =1;i<ficheros.length;i++){
              
              
              
            //System.out.println(ficheros[i]);
            if (ficheros[i].getName().endsWith(".par")){
            p[x]=ficheros[i].getPath();
            File foldera = new File("C:\\nprieto\\UnrarEar\\both\\_par\\");
            foldera.mkdir();
            String[] s = {"C:\\Program Files (x86)\\WinRAR\\winrar.exe", "x",ficheros[i].getPath().toString(),"C:\\nprieto\\UnrarEar\\both\\_par\\"};
            Runtime.getRuntime().exec(s);
            System.out.println(p[x].toString());
            x++;
            }
            
            
            
            if (ficheros[i].getName().endsWith(".sar")){
            p[x]=ficheros[i].getPath();
            File folder1 = new File("C:\\nprieto\\UnrarEar\\both\\_sar\\");
            folder1.mkdir();
            String[] s = {"C:\\Program Files (x86)\\WinRAR\\winrar.exe", "x",ficheros[i].getPath().toString(),"C:\\nprieto\\UnrarEar\\both\\_sar\\"};
            Runtime.getRuntime().exec(s);
            System.out.println(p[x].toString());
            x++;
            }
            }
            
            
            
        } catch (IndexOutOfBoundsException ioe) {
            System.out.println(ioe);
        }
    }
     public static String[] getFiles(){
        return p;
    }
}

