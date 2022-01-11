/*
 * Created by JFormDesigner on Fri Dec 31 11:44:08 CST 2021
 */

package ExamManager;

import java.awt.event.*;
import javax.swing.plaf.*;

import DataClass.*;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class SearchAndDeleteQuestion extends JPanel {
    public SearchAndDeleteQuestion() {
        initComponents();
        pageInitial();
    }
    
    public void pageInitial(){
        table1.setVisible(false);
        bDelete.setVisible(false);

        // set suject comboBox
        Sujects sujects = new Sujects();
        for(Suject suject : sujects.getSujects()){
            cbSuject.addItem(suject.getName());
        }

//        // set question bank comboBox
//        String selectSuject = cbSuject.getSelectedItem().toString();
//        Suject suject = sujects.getSuject(selectSuject);
//        for(QuestionBank qb : suject.getQuestionBanks())
//            cbQB.addItem(qb.getName());
        
        // set question type
        cbType.addItem("全部");
        for(String type : Data.QUESTION_TYPES)
            cbType.addItem(type);
        
        // set degree of difficulty
        cbDifficulty.addItem("全部");
        for(String degree : Data.DEGREE_OF_DIFFICULTY)
            cbDifficulty.addItem(degree);
    }

    private void cbSuject(ActionEvent e) {
        // TODO add your code here
        String selectSuject = cbSuject.getSelectedItem().toString();
        Sujects sujects = new Sujects();
        Suject suject = sujects.getSuject(selectSuject);
        cbQB.removeAllItems();
        for(QuestionBank qb : suject.getQuestionBanks())
            cbQB.addItem(qb.getName());
    }

    String selectSuject = "" , selectQB = "";
    private void bSearch(ActionEvent e) {
        // TODO add your code here
        selectSuject = cbSuject.getSelectedItem().toString();
        selectQB = cbQB.getSelectedItem().toString();
        String questionType = cbType.getSelectedItem().toString();
        String degree = cbDifficulty.getSelectedItem().toString();
        Sujects sujects = new Sujects();
        Suject suject = sujects.getSuject(selectSuject);
        QuestionBank questionBank = suject.getQuestionBank(selectQB);

        ArrayList<Question> showQuestions = new ArrayList<>();
        for(Question q : questionBank.getQuestions()){
            if(!cbType.getSelectedItem().toString().equals("全部") && !q.getQuestionType().equals(questionType))
                continue;
            if(!cbDifficulty.getSelectedItem().toString().equals("全部") && !q.getDegreeOfDifficulty().equals(degree))
                continue;
            showQuestions.add(q);
        }

        // table initial
        DefaultTableModel df = new DefaultTableModel(questionBank.getQuestionNumber() , 4);
        table1.setModel(df);
        table1.getColumnModel().getColumn(0).setHeaderValue("類型");
        table1.getColumnModel().getColumn(1).setHeaderValue("難易度");
        table1.getColumnModel().getColumn(2).setHeaderValue("內容");
        table1.getColumnModel().getColumn(3).setHeaderValue("答案");

        for(int i = 0 ; i < showQuestions.size() ; i ++){
            table1.setValueAt(showQuestions.get(i).getQuestionType() , i , 0);
            table1.setValueAt(showQuestions.get(i).getDegreeOfDifficulty() , i , 1);
            table1.setValueAt(showQuestions.get(i).getContent() , i , 2);
            table1.setValueAt(showQuestions.get(i).getAnswer() , i , 3);
        }
        table1.setVisible(true);
        bDelete.setVisible(true);
    }

    private void bDelete(ActionEvent e) {
        // TODO add your code here
        // get select questions
        int[] selectIndex = table1.getSelectedRows();
        ArrayList<String> selectQuestionContent = new ArrayList<>();
        for(int i = 0 ; i < selectIndex.length ; i ++){
            selectQuestionContent.add(table1.getValueAt(selectIndex[i] , 2).toString());
        }


        for(int i = 0 ; i < selectQuestionContent.size() ; i ++){
            Sujects sujects = new Sujects();
            Suject suject = sujects.getSuject(selectSuject);
            suject.deleteQuestion(selectQB , selectQuestionContent.get(i));

            for(int j = 0 ; j < table1.getRowCount() ; j ++){
                DefaultTableModel df = (DefaultTableModel) table1.getModel();
                if(table1.getValueAt(j , 2).equals(selectQuestionContent.get(i))) {
                    df.removeRow(j);
                    break;
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
        label2 = new JLabel();
        cbQB = new JComboBox();
        label3 = new JLabel();
        cbType = new JComboBox();
        label4 = new JLabel();
        cbDifficulty = new JComboBox();
        bSearch = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        bDelete = new JButton();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
        . EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax
        . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,
        12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans
        . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .
        getPropertyName () )) throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setBackground(new Color(214, 214, 214));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 211, 0, 181, 0, 0, 355, 77, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 527, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u79d1\u76ee");
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- cbSuject ----
            cbSuject.addActionListener(e -> cbSuject(e));
            panel1.add(cbSuject, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u984c\u5eab");
            panel1.add(label2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbQB, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("\u984c\u76ee\u985e\u578b");
            panel1.add(label3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbType, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label4 ----
            label4.setText("\u96e3\u6613\u5ea6");
            panel1.add(label4, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbDifficulty, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSearch ----
            bSearch.setText("\u67e5\u8a62");
            bSearch.addActionListener(e -> bSearch(e));
            panel1.add(bSearch, new GridBagConstraints(8, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 3, 8, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bDelete ----
            bDelete.setText("\u522a\u9664");
            bDelete.addActionListener(e -> bDelete(e));
            panel1.add(bDelete, new GridBagConstraints(8, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private JLabel label2;
    private JComboBox cbQB;
    private JLabel label3;
    private JComboBox cbType;
    private JLabel label4;
    private JComboBox cbDifficulty;
    private JButton bSearch;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton bDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
