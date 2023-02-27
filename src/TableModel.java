import javax.swing.event.TableModelListener;
import java.util.ArrayList;


public class TableModel implements javax.swing.table.TableModel {
    ArrayList<String> ColumnNames;
    String[][] data;

     public TableModel(ArrayList<String> ColumnNames, String[][] data){
         this.ColumnNames=ColumnNames;
         this.data=data;
     }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
         int lenth=0;
         try { lenth=data[0].length;}catch (Exception r){}
        return lenth;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String name="nullColum";
        try {name=   ColumnNames.get(columnIndex);}catch (Exception f){}

       return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

         return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         if (columnIndex==1)return "<html>"+
                 data[rowIndex][columnIndex]
                 +"</html>";

        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex]= (String) aValue;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }


    public  String[][] getTableData(){
         return data;
    }
}
