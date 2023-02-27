
import MethodsSepar.JWL_WriteToFIleMethods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

public class Operations {

    static void  catchErrors(Exception e,String msg){
       String sad="somethingTODO";
    }

    static Object[] importDescrpJson(String path){

        HashMap<String,String> namesCollector=new HashMap<>();
        LinkedList<Map<String, String> > listData=new LinkedList();
        HashMap<String,String>imgPaths=new HashMap<>();

        Gson gson=new Gson();
        for (File file : new File(path).listFiles()) {
            File jsonData=new File(file.getAbsolutePath()+"\\descrp.json");
            if (!jsonData.exists())continue;

            File img=new File(file.getAbsolutePath()+"\\img.jpg");
            if (img.exists())imgPaths.put(file.getName(),img.getAbsolutePath());

            try {
                String str= JWL_WriteToFIleMethods.ReadFromTXT_String(jsonData.getAbsolutePath());
                Type type = new TypeToken<Map<String, String>>(){}.getType();
                Map<String, String> oneArticle = gson.fromJson(str, type);
                oneArticle.put("id",file.getName());
                namesCollector.putAll(oneArticle);
                listData.add(oneArticle);
                new String();
            } catch (Exception e) {
                catchErrors(e,null);
            }
            new String();

        }

        for (int i = 0; i < listData.size(); i++) {

        }

        


        String[] namesColums=  sortNamesColumns(namesCollector);
        
        ArrayList<String[]>data=new ArrayList<>();
        while (listData.size()>0){
            Map<String, String> line=listData.pop();
            ArrayList<String>line1=new ArrayList<>();
            for (String namesColum : namesColums) {
                line1.add(line.get(namesColum));
            }
            data.add(line1.toArray(new String[0]));
        }

        Object[] namesNdata=new Object[3];
        namesNdata[0]=namesColums;
        namesNdata[1]=data.toArray(new String[][] {});
        namesNdata[2]=imgPaths;


        return namesNdata;
    }


    static String[] sortNamesColumns(HashMap<String, String> namesInput){
        HashMap<String, String> names= (HashMap<String, String>) namesInput.clone();
        ArrayList<String> result=new ArrayList();

        result.add("id");
        result.add("name");
        result.add("price");

        names.remove("id");
        names.remove("name");
        names.remove("price");

        result.addAll( names.keySet());

        return result.toArray(new String[0]);
    }

}
