import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FastParserWatcherLite extends JFrame {

    public StorageData storageData;
    public JtableData jtableData;

    FastParserWatcherLite() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

//        JLabel label = new JLabel("JFrame By Example");
//        JButton button = new JButton();
//        button.setText("Button");
//        panel.add(label);
//        panel.add(button);
        storageData=new StorageData();

        Menus menus = new Menus(this);
        jtableData = new JtableData(storageData,menus);


        JScrollPane scrollPane = new JScrollPane(jtableData);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(1000,1000);

        panel.add(scrollPane);


        this.setJMenuBar(menus.bottom_menus());
        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);
        this.setSize(1000, 1000);
//        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.pack();
        this.setVisible(true);
    }


    public static void main(String[] args) {

        FastParserWatcherLite fastParserWatcherLite = new FastParserWatcherLite();

    }

}
