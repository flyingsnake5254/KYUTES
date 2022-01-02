/*
 * Created by JFormDesigner on Sat Dec 18 19:14:10 CST 2021
 */

package SystemManager;

import DataClass.Data;
import DataClass.Dialog;
import DataClass.User;
import DataClass.Users;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class CSVfileStudentPanel extends JPanel {
    public CSVfileStudentPanel() {
        initComponents();

    }

    private void bSubmit(ActionEvent e) {
        // TODO add your code here
        startCreateAccount();
    }
    ArrayList<String> accounts, passwords, department, grades, names, phones, emails;
    Scanner scanner;
    private void wrongTitle(){
        Dialog.wrong("欄位名稱錯誤");
    }
    private void startCreateAccount(){
        // 欄位名稱為空
        String sAccount, sPasswd, sSub, sGrade, sName, sPhone, sEmail;
        sAccount = tfAccount.getText().trim();
        sPasswd = tfPassword.getText().trim();
        sSub = tfSubject.getText().trim();
        sGrade = tfGrade.getText().trim();
        sName = tfName.getText().trim();
        sPhone = tfPhone.getText().trim();
        sEmail = tfEmail.getText().trim();
        if(sAccount.equals("") || sPasswd.equals("") || sSub.equals("") || sGrade.equals("")){
            Dialog.wrong("必要欄位不可為空");
        }
        else{
            // 檢查是否匯入檔案
            if(csvFile == null){
                Dialog.wrong("尚未匯入檔案");
            }
            else{
                boolean state = true;
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
                    int iAccount, iPasswd, iSub, iGrade, iName, iPhone, iEmail;
                    iAccount = title.indexOf(sAccount);
                    iPasswd = title.indexOf(sPasswd);
                    iSub = title.indexOf(sSub);
                    iGrade = title.indexOf(sGrade);
                    iName = title.indexOf(sName);
                    iPhone = title.indexOf(sPhone);
                    iEmail = title.indexOf(sEmail);
                    if(iAccount == -1 || iSub == -1 || iGrade == -1 || iPasswd == -1){
                        wrongTitle();
                    }
                    else{
                        if((!sName.equals("") && iName == -1)
                        || (!sPhone.equals("") && iPhone == -1)
                        || (!sEmail.equals("") && iEmail == -1)){
                            wrongTitle();
                        }
                        else{
                            boolean createState = true;
                            accounts = new ArrayList<>();
                            passwords = new ArrayList<>();
                            department = new ArrayList<>();
                            grades = new ArrayList<>();
                            phones = new ArrayList<>();
                            names = new ArrayList<>();
                            emails = new ArrayList<>();
                            while(scanner.hasNext()){
                                String[] inputData = scanner.next().trim().split(",");
                                accounts.add(inputData[iAccount]);
                                passwords.add(inputData[iPasswd]);
                                department.add(inputData[iSub]);
                                grades.add(inputData[iGrade]);
                                if(iName != -1)
                                    names.add(inputData[iName]);
                                if(iPhone != -1)
                                    phones.add(inputData[iPhone]);
                                if(iEmail != -1)
                                    emails.add(inputData[iEmail]);
                            }
                            scanner.close();
                            boolean hasCreated = false;
                            Users users = new Users();
                            for(int i = 0 ; i < accounts.size() ; i ++){
                                if(users.accountHasCreated(accounts.get(i))){
                                    hasCreated = true;
                                    break;
                                }
                            }
                            if(hasCreated)
                                Dialog.wrong("存在已建立之帳號");
                            else{
                                // write to database
                                for(int i = 0 ; i < accounts.size() ; i ++){
                                    User.createAccount(accounts.get(i));
                                    User.setPassword(accounts.get(i),passwords.get(i));
                                    User.setDepartment(accounts.get(i) , department.get(i));
                                    User.setGrade(accounts.get(i) , grades.get(i));
                                    User.setIdentity(accounts.get(i) , Data.USER_IDENTITY_STUDENT);
                                    User.setCreateTime(accounts.get(i) , LocalDate.now() + "");
                                    if(names.size() != 0)
                                        User.setUserName(accounts.get(i) , names.get(i));
                                    if(phones.size() != 0)
                                        User.setUserName(accounts.get(i) , phones.get(i));
                                    if(emails.size() != 0)
                                        User.setUserName(accounts.get(i) , emails.get(i));
                                }
                                if(createState)
                                    Dialog.message("創建成功");
                            }
                        }
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Dialog.wrong("未知錯誤");
                    state = false;
                }
            }
        }
    }

    private File csvFile = null;
    private void bInputFile(ActionEvent e) {
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
                    lFileName.setText(csvFile.getName());
                }
                else{
                    Dialog.wrong("不支援此類型檔案");
                }
            }
            else{
                Dialog.wrong("不支援此類型檔案");
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label9 = new JLabel();
        label10 = new JLabel();
        tfAccount = new JTextField();
        label11 = new JLabel();
        tfPassword = new JTextField();
        label12 = new JLabel();
        tfSubject = new JTextField();
        label13 = new JLabel();
        tfGrade = new JTextField();
        tfName = new JTextField();
        label1 = new JLabel();
        tfPhone = new JTextField();
        label2 = new JLabel();
        tfEmail = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        bInputFile = new JButton();
        lFileName = new JLabel();
        bSubmit = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(783, 451));
        setBackground(new Color(204, 204, 204));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing.
        border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax. swing .border . TitledBorder. CENTER
        ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dialo\u0067", java .awt . Font
        . BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener(
        new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "borde\u0072"
        .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

        //======== panel1 ========
        {
            panel1.setBackground(new Color(204, 204, 204));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {71, 270, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label9 ----
            label9.setText("\u6b04\u4f4d\u540d\u7a31");
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            label9.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(label9, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label10 ----
            label10.setText("*\u5e33\u865f");
            label10.setHorizontalAlignment(SwingConstants.RIGHT);
            label10.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(label10, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfAccount ----
            tfAccount.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(tfAccount, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label11 ----
            label11.setText("*\u5bc6\u78bc");
            label11.setHorizontalAlignment(SwingConstants.RIGHT);
            label11.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(label11, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfPassword ----
            tfPassword.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(tfPassword, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label12 ----
            label12.setText("*\u79d1\u7cfb");
            label12.setHorizontalAlignment(SwingConstants.RIGHT);
            label12.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(label12, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfSubject ----
            tfSubject.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(tfSubject, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label13 ----
            label13.setText("*\u5e74\u7d1a");
            label13.setHorizontalAlignment(SwingConstants.RIGHT);
            label13.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(label13, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfGrade ----
            tfGrade.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(tfGrade, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfName ----
            tfName.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(tfName, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label1 ----
            label1.setText("\u59d3\u540d");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            label1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(label1, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfPhone ----
            tfPhone.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(tfPhone, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u624b\u6a5f");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            label2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(label2, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- tfEmail ----
            tfEmail.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(tfEmail, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("E-mail");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            label3.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(label3, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label4 ----
            label4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            label4.setText("  ");
            panel1.add(label4, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bInputFile ----
            bInputFile.setText("\u532f\u5165CSV");
            bInputFile.addActionListener(e -> bInputFile(e));
            panel1.add(bInputFile, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lFileName ----
            lFileName.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            panel1.add(lFileName, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSubmit ----
            bSubmit.setText("\u5275\u5efa\u5e33\u865f");
            bSubmit.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
            bSubmit.addActionListener(e -> bSubmit(e));
            panel1.add(bSubmit, new GridBagConstraints(3, 11, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JLabel label9;
    private JLabel label10;
    private JTextField tfAccount;
    private JLabel label11;
    private JTextField tfPassword;
    private JLabel label12;
    private JTextField tfSubject;
    private JLabel label13;
    private JTextField tfGrade;
    private JTextField tfName;
    private JLabel label1;
    private JTextField tfPhone;
    private JLabel label2;
    private JTextField tfEmail;
    private JLabel label3;
    private JLabel label4;
    private JButton bInputFile;
    private JLabel lFileName;
    private JButton bSubmit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
