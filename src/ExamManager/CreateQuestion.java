/*
 * Created by JFormDesigner on Thu Dec 30 07:42:56 CST 2021
 */

package ExamManager;

import java.awt.event.*;
import javax.swing.plaf.*;

import DataClass.*;
import DataClass.Dialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class CreateQuestion extends JPanel {
    public CreateQuestion() {
        initComponents();
        pageInitial();
    }

    ButtonGroup bgChoices , bgDegreeOfDifficulty;
    public void pageInitial(){
        taContent.setLineWrap(true);
        // set question type comboBox
        for(String s : Data.QUESTION_TYPES)
            cbType.addItem(s);

        // set suject
        Sujects sujects = new Sujects();
        for(Suject s : sujects.getSujects())
            cbSuject.addItem(s.getName());

        // set choices radio button group
        bgChoices = new ButtonGroup();
        bgChoices.add(rb1);
        bgChoices.add(rb2);
        bgChoices.add(rb3);
        bgChoices.add(rb4);

        // set degree of difficulty radio button group
        bgDegreeOfDifficulty = new ButtonGroup();
        bgDegreeOfDifficulty.add(rbEasy);
        bgDegreeOfDifficulty.add(rbMedium);
        bgDegreeOfDifficulty.add(rbDiffirult);

        // set choice
        lChoice1.setText("O");
        lChoice2.setText("X");
        lChoice3.setVisible(false);
        lChoice4.setVisible(false);
        rb3.setVisible(false);
        rb4.setVisible(false);
        tf3.setVisible(false);
        tf4.setVisible(false);
    }

    String selectSuject = "";
    private void cbSuject(ActionEvent e) {
        // TODO add your code here
        selectSuject = cbSuject.getSelectedItem().toString();
        Sujects sujects = new Sujects();
        Suject suject = sujects.getSuject(selectSuject);
        cbQB.removeAllItems();
        for(QuestionBank qb : suject.getQuestionBanks())
            cbQB.addItem(qb.getName());
    }

    String selectType = "";
    private void cbType(ActionEvent e) {
        // TODO add your code here
        selectType = cbType.getSelectedItem().toString();
        if(selectType.equals(Data.QUESTION_TYPE_RIGHT_OR_WRONG)){
            lChoice1.setVisible(true);
            lChoice1.setText("O");
            rb1.setVisible(true);
            tf1.setVisible(false);

            lChoice2.setVisible(true);
            lChoice2.setText("X");
            rb2.setVisible(true);
            tf2.setVisible(false);

            lChoice3.setVisible(false);
            rb3.setVisible(false);
            tf3.setVisible(false);

            lChoice4.setVisible(false);
            rb4.setVisible(false);
            tf4.setVisible(false);


        }
        else if(selectType.equals(Data.QUESTION_TYPE_MULTIPLE_CHOICE)){
            lChoice1.setVisible(true);
            lChoice1.setText("選項 A");
            rb1.setVisible(true);
            tf1.setVisible(true);
            tf1.setText("");

            lChoice2.setVisible(true);
            lChoice2.setText("選項 B");
            rb2.setVisible(true);
            tf2.setVisible(true);
            tf2.setText("");

            lChoice3.setVisible(true);
            lChoice3.setText("選項 C");
            rb3.setVisible(true);
            tf3.setVisible(true);
            tf3.setText("");

            lChoice4.setVisible(true);
            lChoice4.setText("選項 D");
            rb4.setVisible(true);
            tf4.setVisible(true);
            tf4.setText("");


        }
        else if(selectType.equals(Data.QUESTION_TYPE_FILL_IN_THE_QUESTION)){
            lChoice1.setVisible(true);
            lChoice1.setText("答案：");
            rb1.setVisible(false);
            tf1.setVisible(true);
            tf1.setText("");

            lChoice2.setVisible(false);
            rb2.setVisible(false);
            tf2.setVisible(false);

            lChoice3.setVisible(false);
            rb3.setVisible(false);
            tf3.setVisible(false);

            lChoice4.setVisible(false);
            rb4.setVisible(false);
            tf4.setVisible(false);
        }
    }

    private void bCreate(ActionEvent e) {
        // TODO add your code here
        System.out.println("sherlock");
        if(cbQB.getSelectedItem() == null)
            Dialog.wrong("請選擇題庫");
        else if(taContent.getText().trim().equals(""))
            Dialog.wrong("題目內容不可為空");
        else if((cbType.getSelectedItem().toString().equals(Data.QUESTION_TYPE_RIGHT_OR_WRONG))
        && (!rb1.isSelected() && !rb2.isSelected())){
            Dialog.wrong("尚未設置答案");
        }
        else if((cbType.getSelectedItem().toString().equals(Data.QUESTION_TYPE_MULTIPLE_CHOICE))
        && (tf1.getText().trim().equals("") || tf2.getText().trim().equals("")
                || tf3.getText().trim().equals("") || tf4.getText().trim().equals(""))){
            Dialog.wrong("選項內容不可為空");
        }
        else if((cbType.getSelectedItem().toString().equals(Data.QUESTION_TYPE_MULTIPLE_CHOICE))
        && (!rb1.isSelected() && !rb2.isSelected()
                && !rb3.isSelected() && !rb4.isSelected())){
            Dialog.wrong("尚未設置答案");
        }
        else if(cbType.getSelectedItem().toString().equals(Data.QUESTION_TYPE_FILL_IN_THE_QUESTION)
        && (tf1.getText().trim().equals(""))){
            Dialog.wrong("尚未設置答案");
        }
        else if(!rbEasy.isSelected() && !rbMedium.isSelected() && !rbDiffirult.isSelected())
            Dialog.wrong("尚未設置題目難易度");
        else{
            // 開始建立題目
            String qbName = cbQB.getSelectedItem().toString();
            String sujectName = cbSuject.getSelectedItem().toString();
            String questionType = cbType.getSelectedItem().toString();
            String content = taContent.getText();
            String choice1 = "" , choice2 = "" , choice3 = "" , choice4 = "";
            choice1 = tf1.getText();
            choice2 = tf2.getText();
            choice3 = tf3.getText();
            choice4 = tf4.getText();

            String diff = "";
            if(rbEasy.isSelected())
                diff = rbEasy.getText();
            else if(rbMedium.isSelected())
                diff = rbMedium.getText();
            else if(rbDiffirult.isSelected())
                diff = rbDiffirult.getText();

            // set answer
            String answer = "";
            if(questionType.equals(Data.QUESTION_TYPE_RIGHT_OR_WRONG)){
                if(rb1.isSelected()) answer = "O";
                else if(rb2.isSelected()) answer = "X";
            }
            else if(questionType.equals(Data.QUESTION_TYPE_MULTIPLE_CHOICE)){
                if(rb1.isSelected()) answer = tf1.getText();
                else if(rb2.isSelected()) answer = tf2.getText();
                else if(rb3.isSelected()) answer = tf3.getText();
                else if(rb4.isSelected()) answer = tf4.getText();
            }
            else if(questionType.equals(Data.QUESTION_TYPE_FILL_IN_THE_QUESTION))
                answer = tf1.getText();

            if(QuestionBank.questionHasCreated(sujectName , qbName , content)){
                Dialog.wrong("此題目已存在");
            }
            else{
                Question.createQuestion(sujectName,qbName,questionType,content,answer,choice1,
                        choice2,choice3,choice4,diff);
                Dialog.message("建立成功");
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
        label4 = new JLabel();
        cbType = new JComboBox();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        taContent = new JTextArea();
        label5 = new JLabel();
        lChoice1 = new JLabel();
        rb1 = new JRadioButton();
        tf1 = new JTextField();
        lChoice2 = new JLabel();
        rb2 = new JRadioButton();
        tf2 = new JTextField();
        lChoice3 = new JLabel();
        rb3 = new JRadioButton();
        tf3 = new JTextField();
        lChoice4 = new JLabel();
        rb4 = new JRadioButton();
        tf4 = new JTextField();
        label11 = new JLabel();
        label6 = new JLabel();
        rbEasy = new JRadioButton();
        rbMedium = new JRadioButton();
        rbDiffirult = new JRadioButton();
        bCreate = new JButton();

        //======== this ========
        setBackground(new Color(214, 214, 214));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
        .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
        .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
        Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
        ), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
        public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
        )))throw new RuntimeException();}});

        //======== panel1 ========
        {
            panel1.setBackground(new Color(214, 214, 214));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 102, 0, 141, 192, 248, 74, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u79d1\u76ee");
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- cbSuject ----
            cbSuject.addActionListener(e -> cbSuject(e));
            panel1.add(cbSuject, new GridBagConstraints(2, 1, 4, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u984c\u5eab");
            panel1.add(label2, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbQB, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label4 ----
            label4.setText("\u984c\u76ee\u985e\u578b");
            panel1.add(label4, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- cbType ----
            cbType.addActionListener(e -> cbType(e));
            panel1.add(cbType, new GridBagConstraints(2, 2, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("\u984c\u76ee\u5167\u5bb9");
            panel1.add(label3, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(taContent);
            }
            panel1.add(scrollPane1, new GridBagConstraints(2, 3, 9, 2, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label5 ----
            label5.setText("\u9078\u9805\u8207\u7b54\u6848");
            panel1.add(label5, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lChoice1 ----
            lChoice1.setText("\u9078\u9805 A");
            lChoice1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lChoice1, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(rb1, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tf1, new GridBagConstraints(3, 6, 6, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lChoice2 ----
            lChoice2.setText("\u9078\u9805 B");
            lChoice2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lChoice2, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(rb2, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tf2, new GridBagConstraints(3, 7, 6, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lChoice3 ----
            lChoice3.setText("\u9078\u9805 C");
            lChoice3.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lChoice3, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(rb3, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tf3, new GridBagConstraints(3, 8, 6, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lChoice4 ----
            lChoice4.setText("\u9078\u9805 D");
            lChoice4.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lChoice4, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(rb4, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tf4, new GridBagConstraints(3, 9, 6, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(label11, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label6 ----
            label6.setText("\u96e3\u6613\u5ea6");
            panel1.add(label6, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- rbEasy ----
            rbEasy.setText("\u7c21\u55ae");
            panel1.add(rbEasy, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- rbMedium ----
            rbMedium.setText("\u4e2d\u7b49");
            panel1.add(rbMedium, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- rbDiffirult ----
            rbDiffirult.setText("\u56f0\u96e3");
            panel1.add(rbDiffirult, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bCreate ----
            bCreate.setText("\u5efa\u7acb");
            bCreate.addActionListener(e -> bCreate(e));
            panel1.add(bCreate, new GridBagConstraints(10, 15, 1, 1, 0.0, 0.0,
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
    private JLabel label2;
    private JComboBox cbQB;
    private JLabel label4;
    private JComboBox cbType;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextArea taContent;
    private JLabel label5;
    private JLabel lChoice1;
    private JRadioButton rb1;
    private JTextField tf1;
    private JLabel lChoice2;
    private JRadioButton rb2;
    private JTextField tf2;
    private JLabel lChoice3;
    private JRadioButton rb3;
    private JTextField tf3;
    private JLabel lChoice4;
    private JRadioButton rb4;
    private JTextField tf4;
    private JLabel label11;
    private JLabel label6;
    private JRadioButton rbEasy;
    private JRadioButton rbMedium;
    private JRadioButton rbDiffirult;
    private JButton bCreate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
