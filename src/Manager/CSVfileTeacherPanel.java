/*
 * Created by JFormDesigner on Sat Dec 18 21:01:26 CST 2021
 */

package Manager;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class CSVfileTeacherPanel extends JPanel {
    public CSVfileTeacherPanel() {
        initComponents();
    }

    private File csvFile = null;
    private void b_inputFile(ActionEvent e) {
        // TODO add your code here
        JFileChooser jFileChooser = new JFileChooser();
        int state = jFileChooser.showOpenDialog(null);
        if(state == JFileChooser.APPROVE_OPTION){
            csvFile = jFileChooser.getSelectedFile();
            String fileType;
            int index;
            if((index = csvFile.getName().lastIndexOf(".")) != -1){
                fileType = csvFile.getName().substring(index + 1);
                if(fileType.equals("csv")){
                    l_fileName.setText(csvFile.getName());
                }
                else{
                    JOptionPane.showMessageDialog(null,"不支援此類型檔案","錯誤",JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"不支援此類型檔案","錯誤",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void b_submit(ActionEvent e) {
        // TODO add your code here
        startCreateAccount();
    }
    ArrayList<String> accounts, passwords, subjects, grades, names, phones, emails;
    Scanner scanner;
    boolean createState;
    private void wrongTitle(){
        JOptionPane.showMessageDialog(
                null,
                "欄位名稱錯誤",
                "錯誤",
                JOptionPane.ERROR_MESSAGE
        );
    }
    private void startCreateAccount(){
        // 欄位名稱為空
        String sAccount, sPasswd, sName, sPhone, sEmail;
        sAccount = tf_account.getText().trim();
        sPasswd = tf_password.getText().trim();
        sName = tf_name.getText().trim();
        sPhone = tf_phone.getText().trim();
        sEmail = tf_email.getText().trim();
        createState = true;
        if(sAccount.equals("") || sPasswd.equals("")){
            JOptionPane.showMessageDialog(null
                    ,"必要欄位不可為空"
                    ,"錯誤",JOptionPane.ERROR_MESSAGE);
        }
        else{
            // 檢查是否匯入檔案
            if(csvFile == null){
                JOptionPane.showMessageDialog(
                        null,
                        "尚未匯入檔案",
                        "錯誤",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else{
                try {
                    scanner = new Scanner(csvFile);
                    ArrayList<String> title = new ArrayList<>();
                    // get CSV file Title
                    if(scanner.hasNext()){
                        String[] input = scanner.next().trim().split(",");
                        if(input[0].length() > 0)
                            input[0] = input[0].substring(1);
                        for(int i = 0 ; i < input.length ; i ++){
                            title.add(input[i]);
                        }
                    }
                    // get index
                    int iAccount, iPasswd, iName, iPhone, iEmail;
                    iAccount = title.indexOf(sAccount);
                    iPasswd = title.indexOf(sPasswd);
                    iName = title.indexOf(sName);
                    iPhone = title.indexOf(sPhone);
                    iEmail = title.indexOf(sEmail);
                    if(iAccount == -1 || iPasswd == -1){
                        wrongTitle();
                    }
                    else{
                        if((!sName.equals("") && iName == -1)
                                || (!sPhone.equals("") && iPhone == -1)
                                || (!sEmail.equals("") && iEmail == -1)){
                            wrongTitle();
                        }
                        else{
                            accounts = new ArrayList<>();
                            passwords = new ArrayList<>();
                            subjects = new ArrayList<>();
                            grades = new ArrayList<>();
                            phones = new ArrayList<>();
                            names = new ArrayList<>();
                            emails = new ArrayList<>();
                            while(scanner.hasNext()){
                                String[] inputData = scanner.next().trim().split(",");
                                accounts.add(inputData[iAccount]);
                                passwords.add(inputData[iPasswd]);
                                if(iName != -1)
                                    names.add(inputData[iName]);
                                if(iPhone != -1)
                                    phones.add(inputData[iPhone]);
                                if(iEmail != -1)
                                    emails.add(inputData[iEmail]);
                            }
                            scanner.close();
                            boolean hasCreated = false;

                            Statement st = new GetDBdata().getStatement();
//
                            // write to database
                            String sql = "insert into user (account,password,identity,create_time";
                            if(names.size() != 0)
                                sql += ",user_name";
                            if(phones.size() != 0)
                                sql += ",phone_number";
                            if(emails.size() != 0)
                                sql += ",user_email";
                            sql += ") values ";
                            for(int i = 0 ; i < accounts.size() ; i ++){
                                String sql2 = "(";
                                sql2 += "'" + accounts.get(i) + "'";
                                sql2 += ",'" + passwords.get(i) + "'";
                                sql2 += ",'" + "teacher" + "'";
                                sql2 += ",'" + LocalDate.now() + "'";
                                if(names.size() != 0)
                                    sql2 += ",'" + names.get(i) + "'";
                                if(phones.size() != 0)
                                    sql2 += ",'" + phones.get(i) + "'";
                                if(emails.size() != 0)
                                    sql2 += ",'" + emails.get(i) + "'";
                                sql2 += ")";
                                st.execute(sql + sql2);
                            }
                            if(createState)
                                JOptionPane.showMessageDialog(null
                                ,"創建成功","訊息",JOptionPane.DEFAULT_OPTION);
                        }
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(
                            null,
                            "未知錯誤",
                            "錯誤",
                            JOptionPane.ERROR_MESSAGE
                    );
                    createState = false;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    JOptionPane.showMessageDialog(null,"存在已被創建之帳號"
                            ,"錯誤"
                            ,JOptionPane.ERROR_MESSAGE);
                    createState = false;
                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label1 = new JLabel();
        label2 = new JLabel();
        tf_account = new JTextField();
        label3 = new JLabel();
        tf_password = new JTextField();
        label4 = new JLabel();
        tf_name = new JTextField();
        label5 = new JLabel();
        tf_phone = new JTextField();
        label6 = new JLabel();
        tf_email = new JTextField();
        label7 = new JLabel();
        b_inputFile = new JButton();
        l_fileName = new JLabel();
        label9 = new JLabel();
        b_submit = new JButton();
        label10 = new JLabel();
        panel1 = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(783, 451));
        setBackground(new Color(204, 204, 204));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 182, 0, 468, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 136, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u6b04\u4f4d\u540d\u7a31");
        label1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("*\u5e33\u865f");
        label2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(tf_account, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("*\u5bc6\u78bc");
        label3.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(tf_password, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("\u59d3\u540d");
        label4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(tf_name, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label5 ----
        label5.setText("\u624b\u6a5f");
        label5.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        label5.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(tf_phone, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label6 ----
        label6.setText("E-mail");
        label6.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        label6.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(tf_email, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label7 ----
        label7.setText(" ");
        add(label7, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_inputFile ----
        b_inputFile.setText("\u532f\u5165CSV");
        b_inputFile.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        b_inputFile.addActionListener(e -> b_inputFile(e));
        add(b_inputFile, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(l_fileName, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(label9, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- b_submit ----
        b_submit.setText("\u5275\u5efa\u5e33\u865f");
        b_submit.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        b_submit.addActionListener(e -> b_submit(e));
        add(b_submit, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        add(label10, new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(204, 204, 204));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 852, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 503, Short.MAX_VALUE)
            );
        }
        add(panel1, new GridBagConstraints(0, 0, 4, 13, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label1;
    private JLabel label2;
    private JTextField tf_account;
    private JLabel label3;
    private JTextField tf_password;
    private JLabel label4;
    private JTextField tf_name;
    private JLabel label5;
    private JTextField tf_phone;
    private JLabel label6;
    private JTextField tf_email;
    private JLabel label7;
    private JButton b_inputFile;
    private JLabel l_fileName;
    private JLabel label9;
    private JButton b_submit;
    private JLabel label10;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
