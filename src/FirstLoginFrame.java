/*
 * Created by JFormDesigner on Sun Jan 02 17:43:44 CST 2022
 */

import DataClass.Data;
import DataClass.Dialog;
import DataClass.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class FirstLoginFrame extends JFrame {
    private User user;
    public FirstLoginFrame(User user) {
        initComponents();
        this.user = user;
        pageInitial();
        frameInitial();
    }
    private void frameInitial(){
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String path = System.getProperty("user.dir");
        this.setIconImage(new ImageIcon(path+"\\image\\nknu_logo.png").getImage());
        this.setVisible(true);
        this.setLocationRelativeTo(this.getOwner());
    }
    private void pageInitial(){
        if(!user.getUserName().equals(""))
            tfName.setText(user.getUserName());
        if(!user.getPhoneNumber().equals(""))
            tfPhone.setText(user.getPhoneNumber());
        if(!user.getUserEmail().equals(""))
            tfEmail.setText(user.getUserEmail());

    }

    private void bConfirm(ActionEvent e) {
        // TODO add your code here
        if(tfName.getText() == null || tfName.getText().trim().equals("")
                || tfPassword1.getText() == null || tfPassword1.getText().trim().equals("")
                || tfPassword2.getText() == null || tfPassword2.getText().trim().equals("")){
            DataClass.Dialog.wrong("必要欄位不可為空");
        }
        else{
            if(tfPassword1.getText().equals(tfPassword2.getText())){
                User.setUserName(user.getAccount() , tfName.getText());
                if(tfPhone.getText() != null)
                    User.setPhoneNumber(user.getAccount() , tfPhone.getText());
                if(tfEmail.getText() != null)
                    User.setUserEmail(user.getAccount() , tfEmail.getText());
                User.setPassword(user.getAccount() , tfPassword1.getText());

                // next activity
                if(user.getIdentity().equals(Data.USER_IDENTITY_SYSTEM_MANAGER))
                    new SystemManagerPage(user);
                else if(user.getIdentity().equals(Data.USER_IDENTITY_EXAM_MANAGER))
                    new ExamManagerPage(user);
                else if(user.getIdentity().equals(Data.USER_IDENTITY_STUDENT))
                    new StudentPage(user);
                user.setOnline(true);
                this.dispose();
            }
            else{
                Dialog.wrong("確認密碼錯誤");
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        tfName = new JTextField();
        label2 = new JLabel();
        tfPhone = new JTextField();
        label3 = new JLabel();
        tfEmail = new JTextField();
        label4 = new JLabel();
        tfPassword1 = new JPasswordField();
        label5 = new JLabel();
        tfPassword2 = new JPasswordField();
        bConfirm = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
            .border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder
            .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.
            awt.Font.BOLD,12),java.awt.Color.red),panel1. getBorder()))
            ;panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}})
            ;
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 193, 74, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("*\u59d3\u540d");
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfName, new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u624b\u6a5f");
            panel1.add(label2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfPhone, new GridBagConstraints(2, 2, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("e-mail");
            panel1.add(label3, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfEmail, new GridBagConstraints(2, 3, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label4 ----
            label4.setText("*\u65b0\u5bc6\u78bc");
            panel1.add(label4, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfPassword1, new GridBagConstraints(2, 4, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label5 ----
            label5.setText("*\u78ba\u8a8d\u5bc6\u78bc");
            panel1.add(label5, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfPassword2, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bConfirm ----
            bConfirm.setText("\u78ba\u5b9a");
            bConfirm.addActionListener(e -> bConfirm(e));
            panel1.add(bConfirm, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JLabel label1;
    private JTextField tfName;
    private JLabel label2;
    private JTextField tfPhone;
    private JLabel label3;
    private JTextField tfEmail;
    private JLabel label4;
    private JPasswordField tfPassword1;
    private JLabel label5;
    private JPasswordField tfPassword2;
    private JButton bConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
