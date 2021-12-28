/*
 * Created by JFormDesigner on Sat Dec 18 14:52:07 CST 2021
 */

package Manager;

import DataClass.Data;
import DataClass.Dialog;
import DataClass.User;
import DataClass.Users;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class CreateManually extends JPanel {

    public CreateManually() {
        initComponents();
        comboBoxInitial();
    }
    
    private void comboBoxInitial(){
        for(String s : Data.USER_IDENTITIES_CN)
            cbIdentity.addItem(s);
        for(String s : Data.SUBJECTS)
            cbSubject.addItem(s);
        for(String s : Data.STUDENT_GRADES)
            cbGrade.addItem(s);
    }

    private void cbIdentity(ActionEvent e) {
        // TODO add your code here
        if(cbIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_STUDENT_CN)){
            lSubject.setVisible(true);
            cbSubject.setVisible(true);
            lGrade.setVisible(true);
            cbGrade.setVisible(true);
        }
        else{
            lSubject.setVisible(false);
            cbSubject.setVisible(false);
            lGrade.setVisible(false);
            cbGrade.setVisible(false);
        }
    }

    private void bCreateAccount(ActionEvent e) {
        // TODO add your code here
        String defaultPassword = tfPassword.getText().trim();
        String sNumber = tfNum.getText().trim();
        if(defaultPassword.equals("") || sNumber.equals("")){
            Dialog.wrong("必要欄位不可為空");
        }
        else{
            // 帳號數
            int number = Integer.parseInt(sNumber);
            Users users = new Users();
            ArrayList<String> newAccounts = new ArrayList<>();
            
            // 產生隨機帳號
            Random random = new Random();
            while(newAccounts.size() < number){
                IntStream intStream = random.ints(1,10000001,99999999);
                int[] arr = intStream.toArray();
                if(!users.accountHasCreated(String.valueOf(arr[0])) && newAccounts.indexOf(String.valueOf(arr[0])) == -1)
                    newAccounts.add(String.valueOf(arr[0]));
            }
            // 學生
            if(cbIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_STUDENT_CN)){
                String sDepartment, sGrade;
                sDepartment = cbSubject.getSelectedItem().toString();
                sGrade = cbGrade.getSelectedItem().toString();
                for(int i = 0 ; i < number ; i ++){
                    User.createAccount(newAccounts.get(i));
                    User.setPassword(newAccounts.get(i) , defaultPassword);
                    User.setDepartment(newAccounts.get(i) , sDepartment);
                    User.setGrade(newAccounts.get(i) , sGrade);
                    User.setCreateTime(newAccounts.get(i) , LocalDate.now() + "");
                    User.setIdentity(newAccounts.get(i) , Data.USER_IDENTITY_STUDENT);
                }
                Dialog.message("創建成功");
            }
            // 教師
            else if(cbIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_EXAM_MANAGER_CN)
            || cbIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_SYSTEM_MANAGER_CN)){
                String iden;
                if(cbIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_EXAM_MANAGER_CN))
                    iden = Data.USER_IDENTITY_EXAM_MANAGER;
                else
                    iden = Data.USER_IDENTITY_SYSTEM_MANAGER;
              
                for(int i = 0 ; i < number ; i ++){
                    User.createAccount(newAccounts.get(i));
                    User.setPassword(newAccounts.get(i) , defaultPassword);
                    User.setCreateTime(newAccounts.get(i) , LocalDate.now() + "");
                    User.setIdentity(newAccounts.get(i) , iden);
                }
                Dialog.message("創建成功");
            }
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label6 = new JLabel();
        label1 = new JLabel();
        cbIdentity = new JComboBox();
        lSubject = new JLabel();
        cbSubject = new JComboBox();
        lGrade = new JLabel();
        cbGrade = new JComboBox();
        bCreateAccount = new JButton();
        label4 = new JLabel();
        tfPassword = new JTextField();
        label5 = new JLabel();
        tfNum = new JTextField();

        //======== this ========
        setBackground(new Color(204, 204, 204));
        setBorder(null);
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
        ( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
        . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
        propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {98, 107, 71, 225, 74, 138, 93, 197, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 119, 0, 0, 0, 0, 34, 0, 516, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label6 ----
        label6.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(label6, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label1 ----
        label1.setText("\u8eab\u5206");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(label1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- cbIdentity ----
        cbIdentity.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        cbIdentity.addActionListener(e -> cbIdentity(e));
        add(cbIdentity, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- lSubject ----
        lSubject.setText("\u79d1\u7cfb");
        lSubject.setHorizontalAlignment(SwingConstants.RIGHT);
        lSubject.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(lSubject, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- cbSubject ----
        cbSubject.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(cbSubject, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- lGrade ----
        lGrade.setText("\u5e74\u7d1a");
        lGrade.setHorizontalAlignment(SwingConstants.RIGHT);
        lGrade.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(lGrade, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- cbGrade ----
        cbGrade.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(cbGrade, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- bCreateAccount ----
        bCreateAccount.setText("\u5275\u5efa\u5e33\u865f");
        bCreateAccount.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 12));
        bCreateAccount.addActionListener(e -> bCreateAccount(e));
        add(bCreateAccount, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("*\u9810\u8a2d\u5bc6\u78bc");
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        label4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- tfPassword ----
        tfPassword.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(tfPassword, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label5 ----
        label5.setText("*\u4eba\u6578");
        label5.setHorizontalAlignment(SwingConstants.RIGHT);
        label5.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- tfNum ----
        tfNum.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(tfNum, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label6;
    private JLabel label1;
    private JComboBox cbIdentity;
    private JLabel lSubject;
    private JComboBox cbSubject;
    private JLabel lGrade;
    private JComboBox cbGrade;
    private JButton bCreateAccount;
    private JLabel label4;
    private JTextField tfPassword;
    private JLabel label5;
    private JTextField tfNum;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
