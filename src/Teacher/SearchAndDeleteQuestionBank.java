/*
 * Created by JFormDesigner on Tue Dec 28 10:18:30 CST 2021
 */

package Teacher;

import java.awt.event.*;

import DataClass.Suject;
import DataClass.Sujects;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class SearchAndDeleteQuestionBank extends JPanel {
    public SearchAndDeleteQuestionBank() {
        initComponents();
        pageInitial();
    }

    private void pageInitial(){
        Sujects allSujects = new Sujects();
        ArrayList<String> arrSuject = allSujects.getNames();
        for(int i = 0 ; i < arrSuject.size() ; i ++){
            cb_suject.addItem(arrSuject.get(i));
        }
        table1.setVisible(false);
        b_delete.setVisible(false);
    }

    String selectSuject = "";
    private void b_search(ActionEvent e) {
        // TODO add your code here
        selectSuject = cb_suject.getSelectedItem().toString();
        Suject suject = new Suject(selectSuject);
        ArrayList<String> bankName = suject.getNames();
        ArrayList<Integer> questionNum = suject.getQuestion_nums();
        // table setting
        DefaultTableModel df = new DefaultTableModel(bankName.size(),2);
        table1.setModel(df);
        table1.getColumnModel().getColumn(0).setHeaderValue("題庫名稱");
        table1.getColumnModel().getColumn(1).setHeaderValue("題目數量");
        for(int i = 0 ; i < bankName.size() ; i ++){
            table1.setValueAt(bankName.get(i),i,0);
            table1.setValueAt(questionNum.get(i),i,1);
        }
        table1.setVisible(true);
        label1.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        cb_suject = new JComboBox();
        b_search = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        b_delete = new JButton();

        //======== this ========
        setBackground(new Color(255, 102, 102));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
        propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});

        //======== panel1 ========
        {
            panel1.setBackground(new Color(102, 255, 102));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {42, 0, 195, 0, 576, 68, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {47, 0, 514, 0, 28, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u79d1\u76ee");
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cb_suject, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- b_search ----
            b_search.setText("\u67e5\u8a62");
            b_search.addActionListener(e -> b_search(e));
            panel1.add(b_search, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 2, 5, 2, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- b_delete ----
            b_delete.setText("\u522a\u9664");
            panel1.add(b_delete, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JLabel label1;
    private JComboBox cb_suject;
    private JButton b_search;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton b_delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
