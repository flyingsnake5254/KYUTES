/*
 * Created by JFormDesigner on Sun Jan 02 18:07:24 CST 2022
 */

package DataClass;

import DataClass.Dialog;
import DataClass.User;
import DataClass.Users;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class AccountDataEdit extends JPanel {
    private User nowUser;
    public AccountDataEdit(User user) {
        this.nowUser = user;
        initComponents();
        pageInitial();

    }
    
    private void pageInitial(){
        lAccount.setText(nowUser.getAccount());
        lConfirmPassword.setVisible(false);
        tfPassword2.setVisible(false);
        bConfirmPassword.setVisible(false);
        tfPassword1.setEditable(false);
        tfPassword1.setText(nowUser.getPassword());
        lName.setText(nowUser.getUserName());
        tfPhone.setText(nowUser.getPhoneNumber());
        tfEmail.setText(nowUser.getUserEmail());
        lIdentity.setText(nowUser.getCNIdentity());
        lCreateTime.setText(nowUser.getCreateTime());
        if(nowUser.getIdentity().equals(Data.USER_IDENTITY_STUDENT)){
            lSuject1.setVisible(true);
            lSuject2.setVisible(true);
            lSuject2.setText(nowUser.getDepartment());
            lGrade1.setVisible(true);
            lGrade2.setVisible(true);
            lGrade2.setText(nowUser.getGrade());
            lGroup1.setVisible(true);
            lGroup2.setVisible(true);
            lGroup2.setText(nowUser.getStudentGroup());
        }
        else{
            lSuject1.setVisible(false);
            lSuject2.setVisible(false);
            lGrade1.setVisible(false);
            lGrade2.setVisible(false);
            lGroup1.setVisible(false);
            lGroup2.setVisible(false);
        }
    }

    private void tfPassword1MousePressed(MouseEvent e) {
        // TODO add your code here
        if(tfPassword1.getText() != null){
            lShowPassword.setText(tfPassword1.getText());
            lShowPassword.setVisible(true);
        }
    }

    private void tfPassword1MouseReleased(MouseEvent e) {
        // TODO add your code here
        lShowPassword.setText("");
        lShowPassword.setVisible(false);
    }

    private void bChangePassword(ActionEvent e) {
        // TODO add your code here
        lConfirmPassword.setVisible(true);
        tfPassword2.setVisible(true);
        bConfirmPassword.setVisible(true);
        bChangePassword.setVisible(false);
        tfPassword1.setEditable(true);
        
    }

    private void bConfirmPassword(ActionEvent e) {
        // TODO add your code here
        if(tfPassword1.getText() != null && tfPassword2.getText() != null 
        && tfPassword1.getText().equals(tfPassword2.getText())){
            User.setPassword(nowUser.getAccount() , tfPassword1.getText());
            Dialog.message("變更成功");
            lConfirmPassword.setVisible(false);
            tfPassword2.setVisible(false);
            tfPassword2.setText("");
            bConfirmPassword.setVisible(false);
            bChangePassword.setVisible(true);
            // user initial
            Users users = new Users();
            this.nowUser = users.getUser(nowUser.getAccount());
            tfPassword1.setText(nowUser.getPassword());
        }
        else{
            Dialog.wrong("輸入錯誤");
        }
    }

    private void bSave(ActionEvent e) {
        // TODO add your code here
        if(tfPhone.getText() != null)
            User.setPhoneNumber(nowUser.getAccount() , tfPhone.getText());
        if(tfEmail.getText() != null)
            User.setUserEmail(nowUser.getAccount() , tfEmail.getText());
        Users users = new Users();
        this.nowUser = users.getUser(nowUser.getAccount());
        Dialog.message("儲存成功");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        lAccount = new JLabel();
        label2 = new JLabel();
        tfPassword1 = new JPasswordField();
        bChangePassword = new JButton();
        label11 = new JLabel();
        lShowPassword = new JLabel();
        lConfirmPassword = new JLabel();
        tfPassword2 = new JPasswordField();
        bConfirmPassword = new JButton();
        label3 = new JLabel();
        lName = new JLabel();
        label4 = new JLabel();
        tfPhone = new JTextField();
        label5 = new JLabel();
        tfEmail = new JTextField();
        label6 = new JLabel();
        lIdentity = new JLabel();
        label7 = new JLabel();
        lCreateTime = new JLabel();
        lSuject1 = new JLabel();
        lSuject2 = new JLabel();
        lGrade1 = new JLabel();
        lGrade2 = new JLabel();
        lGroup1 = new JLabel();
        lGroup2 = new JLabel();
        bSave = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
        0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
        . BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
        red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
        beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 188, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u5e33\u865f\uff1a");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lAccount, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u5bc6\u78bc\uff1a");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfPassword1 ----
            tfPassword1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    tfPassword1MousePressed(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    tfPassword1MouseReleased(e);
                }
            });
            panel1.add(tfPassword1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bChangePassword ----
            bChangePassword.setText("\u66f4\u6539\u5bc6\u78bc");
            bChangePassword.addActionListener(e -> bChangePassword(e));
            panel1.add(bChangePassword, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label11 ----
            label11.setText("   ");
            panel1.add(label11, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lShowPassword, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lConfirmPassword ----
            lConfirmPassword.setText("\u78ba\u8a8d\u5bc6\u78bc\uff1a");
            lConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lConfirmPassword, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfPassword2, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bConfirmPassword ----
            bConfirmPassword.setText("\u78ba\u8a8d\u8b8a\u66f4");
            bConfirmPassword.addActionListener(e -> bConfirmPassword(e));
            panel1.add(bConfirmPassword, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label3 ----
            label3.setText("\u4f7f\u7528\u8005\u540d\u7a31\uff1a");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label3, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lName, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label4 ----
            label4.setText("\u624b\u6a5f\uff1a");
            label4.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label4, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfPhone, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label5 ----
            label5.setText("email\uff1a");
            label5.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label5, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfEmail, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label6 ----
            label6.setText("\u6b0a\u9650\uff1a");
            label6.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label6, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lIdentity, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label7 ----
            label7.setText("\u5e33\u865f\u5275\u5efa\u6642\u9593\uff1a");
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label7, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lCreateTime, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lSuject1 ----
            lSuject1.setText("\u79d1\u7cfb\uff1a");
            lSuject1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lSuject1, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lSuject2, new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lGrade1 ----
            lGrade1.setText("\u5e74\u7d1a\uff1a");
            lGrade1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lGrade1, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lGrade2, new GridBagConstraints(2, 11, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lGroup1 ----
            lGroup1.setText("\u7fa4\u7d44\uff1a");
            lGroup1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lGroup1, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(lGroup2, new GridBagConstraints(2, 12, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSave ----
            bSave.setText("\u5132\u5b58\u8b8a\u66f4");
            bSave.addActionListener(e -> bSave(e));
            panel1.add(bSave, new GridBagConstraints(3, 13, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
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
    private JLabel lAccount;
    private JLabel label2;
    private JPasswordField tfPassword1;
    private JButton bChangePassword;
    private JLabel label11;
    private JLabel lShowPassword;
    private JLabel lConfirmPassword;
    private JPasswordField tfPassword2;
    private JButton bConfirmPassword;
    private JLabel label3;
    private JLabel lName;
    private JLabel label4;
    private JTextField tfPhone;
    private JLabel label5;
    private JTextField tfEmail;
    private JLabel label6;
    private JLabel lIdentity;
    private JLabel label7;
    private JLabel lCreateTime;
    private JLabel lSuject1;
    private JLabel lSuject2;
    private JLabel lGrade1;
    private JLabel lGrade2;
    private JLabel lGroup1;
    private JLabel lGroup2;
    private JButton bSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
