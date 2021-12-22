/*
 * Created by JFormDesigner on Sat Dec 18 14:52:07 CST 2021
 */

package Manager;

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
    private String[] cbIdentityItems = {"學生","教師","管理者"};
    private String[] cbGradeItems = {"一年級","二年級","三年級","四年級"};
    public CreateManually() {
        initComponents();
        for(String s : cbIdentityItems)
            cb_identity.addItem(s);
        for(String s : SecretData.SUBJECTS)
            cb_subject.addItem(s);
        for(String s : cbGradeItems)
            cb_grade.addItem(s);


    }

    private void cb_identity(ActionEvent e) {
        // TODO add your code here
        if(cb_identity.getSelectedItem().toString().equals("學生")){
            l_subject.setVisible(true);
            cb_subject.setVisible(true);
            l_grade.setVisible(true);
            cb_grade.setVisible(true);
        }
        else{
            l_subject.setVisible(false);
            cb_subject.setVisible(false);
            l_grade.setVisible(false);
            cb_grade.setVisible(false);
        }
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        String defaultPassword = tf_password.getText().trim();
        String sNumber = tf_num.getText().trim();
        if(defaultPassword.equals("") || sNumber.equals("")){
            JOptionPane.showMessageDialog(null
                    ,"必要欄位不可為空"
                    ,"錯誤",JOptionPane.ERROR_MESSAGE);
        }
        else{
            int number = Integer.parseInt(sNumber);
            Statement st = new GetDBdata().getStatement();
            ArrayList<String> accounts = new ArrayList<>();
            ArrayList<String> newAccounts = new ArrayList<>();
            boolean createState = true;
            try {
                st.execute("select * from user");
                ResultSet rs = st.getResultSet();
                while(rs.next())
                    accounts.add(rs.getString("account"));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            // 產生隨機帳號
            Random random = new Random();
            while(newAccounts.size() < number){
                IntStream intStream = random.ints(1,10000001,99999999);
                int[] arr = intStream.toArray();
                if(accounts.indexOf(String.valueOf(arr[0])) == -1 && newAccounts.indexOf(String.valueOf(arr[0])) == -1)
                    newAccounts.add(String.valueOf(arr[0]));
            }
            // 學生
            if(cb_identity.getSelectedItem().toString().equals("學生")){
                String sSub, sGrade;
                sSub = cb_subject.getSelectedItem().toString();
                sGrade = cb_grade.getSelectedItem().toString();
                String sql = "insert into user (" +
                        "account,password,department,grade,create_time,identity) values ";
                for(int i = 0 ; i < number ; i ++){
                    String sql2 = "('" + newAccounts.get(i) + "'";
                    sql2 += ",'" + defaultPassword + "'";
                    sql2 += ",'" + sSub + "'";
                    sql2 += ",'" + sGrade + "'";
                    sql2 += ",'" + LocalDate.now() + "'";
                    sql2 += ",'student')";
                    try {
                        st.execute(sql + sql2);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                "創建失敗","錯誤"
                        ,JOptionPane.ERROR_MESSAGE);
                        createState = false;
                    }
                }
                if(createState){
                    JOptionPane.showMessageDialog(
                            null,
                            "創建成功",
                            "訊息",JOptionPane.DEFAULT_OPTION
                    );
                }
            }
            // 教師
            else if(cb_identity.getSelectedItem().toString().equals("教師")
            || cb_identity.getSelectedItem().toString().equals("管理者")){
                String iden;
                if(cb_identity.getSelectedItem().toString().equals("教師"))
                    iden = "teacher";
                else
                    iden = "manager";
                String sql = "insert into user (" +
                        "account,password,create_time,identity) values ";
                for(int i = 0 ; i < number ; i ++){
                    String sql2 = "('" + newAccounts.get(i) + "'";
                    sql2 += ",'" + defaultPassword + "'";
                    sql2 += ",'" + LocalDate.now() + "'";
                    sql2 += ",'" + iden + "')";
                    try {
                        st.execute(sql + sql2);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                "創建失敗","錯誤"
                                ,JOptionPane.ERROR_MESSAGE);
                        createState = false;
                    }
                }
                if(createState){
                    JOptionPane.showMessageDialog(
                            null,
                            "創建成功",
                            "訊息",JOptionPane.DEFAULT_OPTION
                    );
                }
            }
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label6 = new JLabel();
        label1 = new JLabel();
        cb_identity = new JComboBox();
        l_subject = new JLabel();
        cb_subject = new JComboBox();
        l_grade = new JLabel();
        cb_grade = new JComboBox();
        button1 = new JButton();
        label4 = new JLabel();
        tf_password = new JTextField();
        label5 = new JLabel();
        tf_num = new JTextField();

        //======== this ========
        setBackground(new Color(204, 204, 204));
        setBorder(null);
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
        . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
        . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
        awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder( )) )
        ;  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;
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

        //---- cb_identity ----
        cb_identity.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        cb_identity.addActionListener(e -> cb_identity(e));
        add(cb_identity, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- l_subject ----
        l_subject.setText("\u79d1\u7cfb");
        l_subject.setHorizontalAlignment(SwingConstants.RIGHT);
        l_subject.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(l_subject, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- cb_subject ----
        cb_subject.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(cb_subject, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- l_grade ----
        l_grade.setText("\u5e74\u7d1a");
        l_grade.setHorizontalAlignment(SwingConstants.RIGHT);
        l_grade.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(l_grade, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- cb_grade ----
        cb_grade.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(cb_grade, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- button1 ----
        button1.setText("\u5275\u5efa\u5e33\u865f");
        button1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 12));
        button1.addActionListener(e -> button1(e));
        add(button1, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("*\u9810\u8a2d\u5bc6\u78bc");
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        label4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- tf_password ----
        tf_password.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(tf_password, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label5 ----
        label5.setText("*\u4eba\u6578");
        label5.setHorizontalAlignment(SwingConstants.RIGHT);
        label5.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- tf_num ----
        tf_num.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        add(tf_num, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label6;
    private JLabel label1;
    private JComboBox cb_identity;
    private JLabel l_subject;
    private JComboBox cb_subject;
    private JLabel l_grade;
    private JComboBox cb_grade;
    private JButton button1;
    private JLabel label4;
    private JTextField tf_password;
    private JLabel label5;
    private JTextField tf_num;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
