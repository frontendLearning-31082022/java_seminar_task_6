import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

public class Menus {
    FastParserWatcherLite fastParserWatcherLite;

    Menus(FastParserWatcherLite fastParserWatcherLite){
        this.fastParserWatcherLite=fastParserWatcherLite;
    }
     JMenuBar bottom_menus(){
        JMenuBar menuBar = new JMenuBar();


       JMenu menuFile = new JMenu("Файл");
       menuBar.add(menuFile);

        JMenuItem open = new JMenuItem(new AbstractAction("Импорт link/descrp.json") {
            @Override
            public boolean accept(Object sender) {return super.accept(sender);}

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser=new JFileChooser();
                fileChooser.setDialogTitle("Выберите папку c папками ID");
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                fileChooser.setCurrentDirectory(new File(  new File("resources/notebooks_data").getAbsolutePath()));
                int result = fileChooser.showOpenDialog(null);

                if (result != JFileChooser.APPROVE_OPTION )return;

                String path= fileChooser.getSelectedFile().toPath().toString();
                Object[] namesNdata= Operations.importDescrpJson(path);
                fastParserWatcherLite.storageData.loadNewData((String[]) namesNdata[0],
                        (String[][]) namesNdata[1],
                        (HashMap<String, String>) namesNdata[2]);

                fastParserWatcherLite.jtableData.loadData(fastParserWatcherLite.storageData);
                new String();
            }

        });
       menuFile.add(open);


         JMenuItem menuHelp = new JMenuItem(new AbstractAction("Справка") {
             @Override
             public boolean accept(Object sender) {return super.accept(sender);}

             @Override
             public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(null,"CTRL+F фильтр по слову в колонке\n " +
                         "Контекстное меню - правая клавиша мыши по записи в таблице\n" +
                         "Для фильтрации итд - сперва щелкнуть на ячейку");
             }

         });
         menuBar.add(menuHelp);


       return menuBar;
    }

    protected JPopupMenu contextMenu(java.awt.event.MouseEvent evt) {
        JPopupMenu menu=new JPopupMenu();

        JMenuItem itemClearAllFilters=itemClearAllFilters(evt);
        menu.add(itemClearAllFilters);
        menu.add(itemLess(evt));
        menu.add(itemBigger(evt));
        menu.add(itemFilterInRange(evt));



        return menu;
    }

    protected JMenuItem itemBigger(MouseEvent evt) {
        int colNum= fastParserWatcherLite.jtableData.getSelectedColumn();
        JMenuItem menuItem  = new JMenuItem("Фильтр больше числа");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String dig=JOptionPane.showInputDialog("Введите число");


                fastParserWatcherLite.storageData.filterBiggerLess(dig,true,colNum);
                fastParserWatcherLite.jtableData.loadData(fastParserWatcherLite.storageData);

            }
        });
        return menuItem;
    }

    protected JMenuItem itemFilterInRange(MouseEvent evt) {
        int colNum= fastParserWatcherLite.jtableData.getSelectedColumn();
        JMenuItem menuItem  = new JMenuItem("Фильтр диапазона");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String min=JOptionPane.showInputDialog("Мин диапазон");
                String max=JOptionPane.showInputDialog("Макс диапазон");

                fastParserWatcherLite.storageData.filterInRange(min,max,colNum);
                fastParserWatcherLite.jtableData.loadData(fastParserWatcherLite.storageData);

            }
        });
        return menuItem;
    }



    protected JMenuItem itemLess(MouseEvent evt) {
        int colNum= fastParserWatcherLite.jtableData.getSelectedColumn();

        JMenuItem menuItem  = new JMenuItem("Фильтр меньше числа");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String dig=JOptionPane.showInputDialog("Введите число");

                fastParserWatcherLite.storageData.filterBiggerLess(dig,false,colNum);
                fastParserWatcherLite.storageData.clearAllFilters();
                fastParserWatcherLite.jtableData.loadData(fastParserWatcherLite.storageData);

            }
        });
        return menuItem;
    }

    protected JMenuItem itemClearAllFilters(MouseEvent evt) {
        JMenuItem menuItem  = new JMenuItem("Очистить все фильтры");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                fastParserWatcherLite.storageData.clearAllFilters();
                fastParserWatcherLite.jtableData.loadData(fastParserWatcherLite.storageData);

            }
        });
        return menuItem;
    }
}
