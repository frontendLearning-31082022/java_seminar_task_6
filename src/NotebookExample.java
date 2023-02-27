import java.util.HashMap;

public class NotebookExample {
    String name;
    Integer price=null;
    String id;
    HashMap<String,String>fields=new HashMap<>();

    public NotebookExample(HashMap<String, String> fields) {
        this.id=fields.get("id");
        this.name=fields.get("name");
        try {
            this.price= Integer.parseInt(fields.get("price").replaceAll("\\D",""));
        }catch (Exception r){}

        this.fields=fields;

    }

    @Override
    public String toString() {
        return "Привет, я ноутбук "+this.name+", продаюсь за "+price+"!";
    }
}
