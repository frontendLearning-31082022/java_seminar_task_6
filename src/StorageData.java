import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StorageData {

    private ArrayList namesALL;
    private  String[][] dataALL;
    private HashMap<String,String>imgPathsALL;

    public ArrayList names;
    public  String[][] data;
    public HashMap<String,String>imgPaths;


    StorageData(){}

    public void loadNewData(String[] names, String[][] data, HashMap<String,String>imgPaths) {
        this.dataALL=data;
        this.namesALL=new ArrayList<>(Arrays.asList(names));
        this.imgPathsALL=imgPaths;


        this.data=data;
        this.names=new ArrayList<>(Arrays.asList(names));
        this.imgPaths=imgPaths;

        useLikeNotebookObject();

    }


    public void filterByWord(String word,int index) {
         data=  Arrays.stream(this.dataALL).filter(strings -> (strings[index]==null?"":strings[index]).toLowerCase().indexOf(word)>-1).toArray(String[][]::new);

        new String();
    }

    void useLikeNotebookObject(){
        ArrayList<NotebookExample>listNotebooks=new ArrayList<>();
        for (int i = 0; i < this.dataALL.length; i++) {
            HashMap<String,String>fields=new HashMap<>();
            for (int i1 = 0; i1 < this.namesALL.size(); i1++) {
                String nameField= (String) namesALL.get(i1);
                fields.put(nameField,dataALL[i][i1]);
            }
            listNotebooks.add(new NotebookExample(fields));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(listNotebooks.get(i));
        }

    }

    public void clearAllFilters() {
        data=dataALL;
    }

    public void filterInRange(String from,String to ,int colNum) {
        int fromD= Integer.parseInt(from);
        int toD= Integer.parseInt(to);

        data=  Arrays.stream(this.dataALL).filter(strings ->{
            try {
                String val= strings[colNum];
                val=val.replaceAll("\\.\\d*","");
                val=val.replaceAll("\\D","");
                int res= Integer.parseInt(val);
                if (res>toD)return false;
                if (res<fromD)return false;

                return true;
            }catch (Exception f){
                return false;
            }
        }).toArray(String[][]::new);

    }

    public void filterBiggerLess(String dig, boolean BiggerRegime,int colNum) {
        int digConv= Integer.parseInt(dig);

        data=  Arrays.stream(this.dataALL).filter(strings ->{
            try {
                String val= strings[colNum];
                val=val.replaceAll("\\.\\d*","");
                val=val.replaceAll("\\D","");
                int res= Integer.parseInt(val);
                if (res>digConv && BiggerRegime)return true;
                if (res<digConv && !BiggerRegime)return true;

                return false;
            }catch (Exception f){
                return false;
            }
        }).toArray(String[][]::new);

    }
}
