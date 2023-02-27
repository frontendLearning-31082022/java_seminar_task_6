package MethodsSepar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class JWL_WriteToFIleMethods {

    public static String ReadFromTXT_String(String PathToTXT) throws Exception{
        File file = new File(PathToTXT);

        BufferedReader br = null;

//        br = new BufferedReader(new FileReader(file));

        br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF-8"));

        String output="";
        String st;
        while (true) {
            if (!((st = br.readLine()) != null)) break;
            output=output+st+System.lineSeparator();
        }

        return output;
    }

}
