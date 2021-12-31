/*
 * Created by JFormDesigner on Tue Dec 28 10:18:30 CST 2021
 */

package Teacher;

import java.awt.event.*;

import DataClass.*;

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
        Sujects1 allSujects = new Sujects1();
        ArrayList<String> arrSuject = allSujects.getNames();
        for(int i = 0 ; i < arrSuject.size() ; i ++){
            cbSuject.addItem(arrSuject.get(i));
        }
        table1.setVisible(false);
        bDelete.setVisible(false);
    }

    String selectSuject = "";
    private void bSearch(ActionEvent e) {
        // TODO add your code here
        selectSuject = cbSuject.getSelectedItem().toString();
        Sujects sujects = new Sujects();
        Suject suject = sujects.getSuject(selectSuject);
        DefaultTableModel df = new DefaultTableModel(suject.getQuestionBankNumber(),2);
        table1.setModel(df);
        table1.getColumnModel().getColumn(0).setHeaderValue("題庫名稱");
        table1.getColumnModel().getColumn(1).setHeaderValue("題目數量");
        int tableIndex = 0;
        for(QuestionBank qb : suject.getQuestionBanks()){
            table1.setValueAt(qb.getName(), tableIndex,0);
            table1.setValueAt(qb.getQuestionNumber() , tableIndex , 1);
            tableIndex ++;
        }
        table1.setVisible(true);
        bDelete.setVisible(true);
    }

    private void bDelete(ActionEvent e) {
        // TODO add your code here
        // get select question bank
        int[] selectIndex = table1.getSelectedRows();
        ArrayList<String> selectQB = new ArrayList<>();
        for(int i = 0 ; i < selectIndex.length ; i ++)
            selectQB.add(table1.getValueAt(selectIndex[i],0).toString());

        for(int i = 0 ; i < selectQB.size() ; i ++){
            Sujects sujects = new Sujects();
            Suject suject = sujects.getSuject(selectSuject);
            suject.deleteQuestionBank(selectQB.get(i));

            for(int j = 0 ; j < table1.getRowCount() ; j ++){
                if(table1.getValueAt(j , 0).toString().equals(selectQB.get(i))){
                    DefaultTableModel df = (DefaultTableModel) table1.getModel();
                    df.removeRow(j);
                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        cbSuject = new JComboBox();
        bSearch = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        bDelete = new JButton();

        //======== this ========
        setBackground(new Color(255, 102, 102));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );

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
            panel1.add(cbSuject, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSearch ----
            bSearch.setText("\u67e5\u8a62");
            bSearch.addActionListener(e -> bSearch(e));
            panel1.add(bSearch, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 2, 5, 2, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bDelete ----
            bDelete.setText("\u522a\u9664");
            bDelete.addActionListener(e -> bDelete(e));
            panel1.add(bDelete, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0,
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
    private JComboBox cbSuject;
    private JButton bSearch;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton bDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
