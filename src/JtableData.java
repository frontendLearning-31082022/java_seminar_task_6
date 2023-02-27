import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class JtableData extends JTable {
     String data[][] = new String[][] {{"загрузите данные с помощью меню", "", ""}};
     ArrayList<String> columnNames =new ArrayList<>();
    StorageData storageData;

    Menus menus;

    JtableData(StorageData storageData, Menus menus) {
        super(new String[][] {{"загрузите данные с помощью меню", "", ""}},new String[] {"ID"});

        this.storageData=storageData;
        TableModel tableModel = new TableModel(columnNames,data);

        this.menus=menus;

        this.setModel(tableModel);

        this.repaint();

        addKeyListeners(this);

        addMouseListenrs();


        this.setRowHeight(30);


        for (int i = 0; i < this.getColumnModel().getColumnCount(); i++)this.getColumnModel().getColumn(i).setPreferredWidth(400);

        this.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        this.setSize(1000,1000);
//        this.setPreferredScrollableViewportSize(new Dimension(1,1));

//        this.getColumnModel().getColumn(0).setPreferredWidth(300);
//        this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

    }

    private void addMouseListenrs() {

        JtableData context=this;
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int row = context.rowAtPoint(evt.getPoint());
                int col = context.columnAtPoint(evt.getPoint());

                if ( evt.getButton()==3){

                    context.menus.contextMenu(evt).show(evt.getComponent(),evt.getX(),evt.getY());

                }


            }
        });
    }

    private void addKeyListeners(JtableData jtableData) {
        JtableData context=jtableData;

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent ev) {

//                if (ev.isShiftDown() && ev.getKeyCode()==127) {
//                    JMenuItem contextMenu= MenuItemsDELETEnameEquals(null);
//                    contextMenu.doClick();
//                }

//                if (ev.getKeyCode() ==127 || ev.getKeyCode() ==46){
//                    makeDELETEpress(); }

//                if (ev.getKeyCode() ==10 ){
//                    int row = table.getSelectedRow();
//                    int ColUrl=getColNumURL();
//                    String urlVal= (String) table.getValueAt(row,ColUrl);
//
//                    makeENTER(ev,urlVal);
//                }

                if (ev.isControlDown() && ev.getKeyCode()==70) {
                    int col=context.getSelectedColumn();

                    String filter=JOptionPane.showInputDialog("Слово для фильтрации");
                    context.storageData.filterByWord(filter,col);
                    context.loadData(storageData);

                    context.repaint();

                    new String();
                }

            }
            //доп события мыши
            @Override
            public void keyTyped(KeyEvent ev) {
            }
            @Override
            public void keyReleased(KeyEvent ev) {
//                 int row = table.getSelectedRow();
//                 if (row != -1) {
//                     showImage(row);
//                 }
            }
        });
    }

//    private getID(){
//        int index=0;
//        this.getColumn("id")
//        this.columnNames.f
//    }

//    public void loadData(String[] names, String[][] data) {
//        this.columnNames=new ArrayList<>(Arrays.asList(names));
//        this.data=data;
//
//
//        this.setModel(new TableModel(columnNames,this.data));
////        for (int i = 0; i < this.getColumnModel().getColumnCount(); i++)this.getColumnModel().getColumn(i).setMinWidth(300);
//        for (int i = 0; i < this.getColumnModel().getColumnCount(); i++)this.getColumnModel().getColumn(i).setWidth(1000);
//        for (int i = 0; i < this.getColumnModel().getColumnCount(); i++)this.getColumnModel().getColumn(i).setPreferredWidth(1000);
//
//        resizeColumns();
//        this.repaint();
//    }

    private void resizeColumns() {
        this.getColumnModel().getColumn(this.getColumn("id").getModelIndex()).setPreferredWidth(30);
        this.getColumnModel().getColumn(this.getColumn("name").getModelIndex()).setPreferredWidth(100);
//        this.getColumnModel().getColumn(this.getColumn("id").getModelIndex()).setPreferredWidth(30);
        new String();
    }

    public void loadData(StorageData storageData) {

        this.columnNames=storageData.names;
        this.data=storageData.data;


        this.setModel(new TableModel(this.columnNames,this.data));

//        int idColumn= (int) this.getColumn("id").getModelIndex();
//        for (int i = 0; i < this.data.length; i++) {
//            String key= data[i][idColumn];
//            ImageIcon icon = new ImageIcon(storageData.imgPaths.get(key));
//            this.setValueAt(icon, i, 0);
//        }


//        for (int i = 0; i < this.getColumnModel().getColumnCount(); i++)this.getColumnModel().getColumn(i).setMinWidth(300);
        for (int i = 0; i < this.getColumnModel().getColumnCount(); i++)this.getColumnModel().getColumn(i).setPreferredWidth(100);


        resizeColumns();
        this.repaint();
    }
}
