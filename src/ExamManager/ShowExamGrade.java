/*
 * Created by JFormDesigner on Mon Jan 03 02:03:59 CST 2022
 */

package ExamManager;

import javax.swing.plaf.*;
import DataClass.ExamGrade;
import DataClass.ExamGrades;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class ShowExamGrade extends JFrame {
    private String id;
    public ShowExamGrade(String id) {
        initComponents();
        this.id = id;
        this.setVisible(true);
        pageInitial();
    }
    
    private void pageInitial(){
        ExamGrades examGrades = new ExamGrades(id);
        ArrayList<ExamGrade> eg = examGrades.getExamGrades();
        DefaultTableModel df = new DefaultTableModel(eg.size() , 3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(df);
        table1.getColumnModel().getColumn(0).setHeaderValue("帳號");
        table1.getColumnModel().getColumn(1).setHeaderValue("姓名");
        table1.getColumnModel().getColumn(2).setHeaderValue("成績");
        
        for(int i = 0 ; i < eg.size() ; i ++){
            table1.setValueAt(eg.get(i).getAccount() , i , 0);
            table1.setValueAt(eg.get(i).getUser().getUserName() , i , 1);
            table1.setValueAt(eg.get(i).getExamGrade() , i , 2);
        }
        
        table1.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(214, 214, 214));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (
            new javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn"
            , javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
            , new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 )
            , java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 773, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 447, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
