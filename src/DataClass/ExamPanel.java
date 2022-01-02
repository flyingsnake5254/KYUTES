/*
 * Created by JFormDesigner on Mon Jan 03 00:06:26 CST 2022
 */

package DataClass;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class ExamPanel extends JPanel {
    private Question q;
    private int num;
    ButtonGroup bg = new ButtonGroup();
    public ExamPanel(Question q , int num) {
        this.q = q;
        this.num = num;
        initComponents();
        tfNum.setEditable(false);
        tfContent.setEditable(false);
        lHiddenAns.setVisible(false);
        lHiddenAns.setForeground(Color.BLUE);
        bg.add(radioButton1);
        bg.add(radioButton2);
        bg.add(radioButton3);
        bg.add(radioButton4);
        
        start();
    }
    
    private void start(){
        tfNum.setText(String.valueOf(num));
        tfContent.setText(q.getContent());
        if(q.getQuestionType().equals(Data.QUESTION_TYPE_RIGHT_OR_WRONG)){
            radioButton1.setVisible(true);
            radioButton1.setText("O");
            radioButton2.setVisible(true);
            radioButton2.setText("X");
            radioButton3.setVisible(false);
            radioButton4.setVisible(false);
            tfAns.setVisible(false);
        }
        else if(q.getQuestionType().equals(Data.QUESTION_TYPE_MULTIPLE_CHOICE)){
            radioButton1.setVisible(true);
            radioButton2.setVisible(true);
            radioButton3.setVisible(true);
            radioButton4.setVisible(true);
            tfAns.setVisible(false);

            radioButton1.setText(q.getC1());
            radioButton2.setText(q.getC2());
            radioButton3.setText(q.getC3());
            radioButton4.setText(q.getC4());
        }
        else{
            radioButton1.setVisible(false);
            radioButton2.setVisible(false);
            radioButton3.setVisible(false);
            radioButton4.setVisible(false);
            tfAns.setVisible(true);
        }
        lHiddenAns.setText(q.getAnswer());
    }

    private void radioButton1(ActionEvent e) {
        // TODO add your code here
        if(!q.getQuestionType().equals(Data.QUESTION_TYPE_FILL_IN_THE_QUESTION)
        && radioButton1.isSelected()){
            lHiddenAns.setText(radioButton1.getText());
        }
    }

    private void radioButton2(ActionEvent e) {
        // TODO add your code here
        if(!q.getQuestionType().equals(Data.QUESTION_TYPE_FILL_IN_THE_QUESTION)
                && radioButton2.isSelected()){
            lHiddenAns.setText(radioButton2.getText());
        }
    }

    private void radioButton3(ActionEvent e) {
        // TODO add your code here
        if(!q.getQuestionType().equals(Data.QUESTION_TYPE_FILL_IN_THE_QUESTION)
                && radioButton3.isSelected()){
            lHiddenAns.setText(radioButton3.getText());
        }
    }

    private void radioButton4(ActionEvent e) {
        // TODO add your code here
        if(!q.getQuestionType().equals(Data.QUESTION_TYPE_FILL_IN_THE_QUESTION)
                && radioButton4.isSelected()){
            lHiddenAns.setText(radioButton4.getText());
        }
    }


    private void tfAns(ActionEvent e) {
        // TODO add your code here
        if(q.getQuestionType().equals(Data.QUESTION_TYPE_FILL_IN_THE_QUESTION)
                && radioButton4.isSelected()){
            if(tfAns.getText() == null){
                lHiddenAns.setText("");
            }
            else{
                lHiddenAns.setText(tfAns.getText());
            }
                
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        tfNum = new JTextField();
        tfContent = new JTextField();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        radioButton4 = new JRadioButton();
        tfAns = new JTextField();
        lHiddenAns = new JLabel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
        propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 254, 401, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 132, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            panel1.add(tfNum, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfContent, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- radioButton1 ----
            radioButton1.addActionListener(e -> radioButton1(e));
            panel1.add(radioButton1, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- radioButton2 ----
            radioButton2.addActionListener(e -> radioButton2(e));
            panel1.add(radioButton2, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- radioButton3 ----
            radioButton3.addActionListener(e -> radioButton3(e));
            panel1.add(radioButton3, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- radioButton4 ----
            radioButton4.addActionListener(e -> radioButton4(e));
            panel1.add(radioButton4, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfAns ----
            tfAns.addActionListener(e -> tfAns(e));
            panel1.add(tfAns, new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lHiddenAns, new GridBagConstraints(1, 7, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
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
    private JTextField tfNum;
    private JTextField tfContent;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JTextField tfAns;
    private JLabel lHiddenAns;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
