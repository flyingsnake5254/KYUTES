/*
 * Created by JFormDesigner on Sun Jan 02 19:09:03 CST 2022
 */

package Student;

import java.awt.event.*;
import DataClass.*;
import DataClass.Dialog;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class Practice extends JPanel {
    private User nowUser;
    public Practice(User nowUser) {
        this.nowUser = nowUser;
        initComponents();
        pageInitial();
    }
    
    private void pageInitial(){
        Groups groups = new Groups();

        if(nowUser.getStudentGroup().equals("")){
            Dialog.wrong("未加入群組");
        }
        else{
            Group group = groups.getGroup(nowUser.getStudentGroup());
            for(Suject s : group.getExamSujects())
                cbSuject.addItem(s.getName());

            lQuestionNum.setVisible(false);
            spQuestionNum.setVisible(false);
            lQB.setVisible(false);
            table1.setVisible(false);
            bStart.setVisible(false);
        }

    }


    private void cbSuject(ActionEvent e) {
        // TODO add your code here
        if(cbSuject.getSelectedItem() != null){
            String selectSuject = cbSuject.getSelectedItem().toString();
            Sujects sujects = new Sujects();
            Suject suject = sujects.getSuject(selectSuject);
            ArrayList<QuestionBank> questionBanks = suject.getQuestionBanks();
            DefaultTableModel df = new DefaultTableModel(questionBanks.size() , 1){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table1.setModel(df);
            table1.getColumnModel().getColumn(0).setHeaderValue("題庫名稱");
            
            for(int i = 0 ; i < questionBanks.size() ; i ++){
                table1.setValueAt(questionBanks.get(i).getName() , i , 0);
            }

            lQuestionNum.setVisible(true);
            spQuestionNum.setVisible(true);
            lQB.setVisible(true);
            table1.setVisible(true);
            bStart.setVisible(true);
        }
    }

    String selectSuject = "";
    ArrayList<String> qbName;

    private void bStart(ActionEvent e) {
        // TODO add your code here
        int[] selectIndex = table1.getSelectedRows();
        if(selectIndex.length == 0){
            Dialog.wrong("尚未選擇題庫");
        }
        else{
            selectSuject = cbSuject.getSelectedItem().toString();
            qbName = new ArrayList<>();
            for(int i = 0 ; i < selectIndex.length ; i ++){
                qbName.add(table1.getValueAt(selectIndex[i] , 0).toString());
            }
            int questionNum = Integer.parseInt(spQuestionNum.getValue().toString());
            System.out.println("_____________" + questionNum);
            new PracticeFrame(nowUser , selectSuject , qbName , questionNum);
        }
    }

    

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        lSuject = new JLabel();
        cbSuject = new JComboBox();
        lQuestionNum = new JLabel();
        spQuestionNum = new JSpinner();
        lQB = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        bStart = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
        border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER
        , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font
        .BOLD ,12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
        new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er"
        .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 89, 89, 79, 0, 97, 522, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 72, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- lSuject ----
            lSuject.setText("\u79d1\u76ee");
            panel1.add(lSuject, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- cbSuject ----
            cbSuject.addActionListener(e -> cbSuject(e));
            panel1.add(cbSuject, new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lQuestionNum ----
            lQuestionNum.setText("\u7df4\u7fd2\u984c\u6578");
            panel1.add(lQuestionNum, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- spQuestionNum ----
            spQuestionNum.setModel(new SpinnerNumberModel(1, 1, null, 1));
            panel1.add(spQuestionNum, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lQB ----
            lQB.setText("\u984c\u5eab");
            panel1.add(lQB, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 4, 6, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bStart ----
            bStart.setText("\u958b\u59cb\u7df4\u7fd2");
            bStart.addActionListener(e -> bStart(e));
            panel1.add(bStart, new GridBagConstraints(6, 5, 1, 1, 0.0, 0.0,
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
    private JLabel lSuject;
    private JComboBox cbSuject;
    private JLabel lQuestionNum;
    private JSpinner spQuestionNum;
    private JLabel lQB;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton bStart;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
