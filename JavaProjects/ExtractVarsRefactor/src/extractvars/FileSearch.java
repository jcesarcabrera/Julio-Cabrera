package extractvars;

import java.io.*;
import java.util.Scanner;

public class FileSearch {
//    public static void main(String[] args) {
//        // Testing only
//        File f = new File(args[0]);
//        String search = args[1];
//        System.out.printf("Result of searching for %s in %s was %b\n", search, f.getName(), FileSearch.find(f, search));
//    }

    public static boolean find(File f, String searchString) {
        boolean result = false;
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(f));
       //     System.out.println("1");
            while(in.hasNextLine() && !result) {
       //     System.out.println("2");
                result = in.nextLine().indexOf(searchString) >= 0;
       //         System.out.println("3");
            }
        }
        catch(IOException e) {
        }
        finally {
            try { in.close() ; } catch(Exception e) { /* ignore */ }  
        }
        return result;
    }
}