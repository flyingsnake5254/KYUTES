/*
 * Created by JFormDesigner on Mon Dec 20 22:24:40 CST 2021
 */

package Manager;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author peiChun lu
 */
public class AccountManagement extends JPanel {
    
    public AccountManagement() {
        initComponents();
        setComboBox();
        dataTable.setVisible(false);
        b_delete.setVisible(false);
    }
    
    private void setComboBox(){
        cb_year.addItem("全部");
        cb_month.addItem("全部");
        cb_day.addItem("全部");
        cb_identity.addItem("全部");
        cb_identity.addItem("學生");
        cb_identity.addItem("教師");
        cb_identity.addItem("管理者");
        cb_sub.addItem("全部");
        for(String ssub : SecretData.SUBJECTS)
            cb_sub.addItem(ssub);
        cb_grade.addItem("全部");
        cb_grade.addItem("一年級");
        cb_grade.addItem("二年級");
        cb_grade.addItem("三年級");
        cb_grade.addItem("四年級");
        String[] data = LocalDate.now().toString().split("-");
        int y = Integer.parseInt(data[0]);
        for(int i = 0 ; i < 10 ; i ++, y --){
            cb_year.addItem(String.valueOf(y));
        }
        for(int i = 1 ; i <= 12 ; i ++)
            cb_month.addItem(String.valueOf(i));
        for(int i = 1 ; i <= 31 ; i ++)
            cb_day.addItem(String.valueOf(i));
        
        
    }

    private void cb_identity(ActionEvent e) {
        // TODO add your code here
        if(cb_identity.getSelectedItem().toString().equals("學生")){
            l_sub.setVisible(true);
            cb_sub.setVisible(true);
            l_grade.setVisible(true);
            cb_grade.setVisible(true);
        }
        else{
            l_sub.setVisible(false);
            cb_sub.setVisible(false);
            l_grade.setVisible(false);
            cb_grade.setVisible(false);
        }
    }

    private void b_search(ActionEvent e) {
        // TODO add your code here
        // get DB data
        ArrayList<String> accounts, password, phone, email, names, times, identity, subjects, grade, year, month, day;
        accounts = new ArrayList<>();
        password = new ArrayList<>();
        phone = new ArrayList<>();
        email = new ArrayList<>();
        names = new ArrayList<>();
        times = new ArrayList<>();
        identity = new ArrayList<>();
        subjects = new ArrayList<>();
        grade = new ArrayList<>();
        year = new ArrayList<>();
        month = new ArrayList<>();
        day = new ArrayList<>();
        Statement st = new GetDBdata().getStatement();
        try {
            st.execute("select * from user");
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                accounts.add(rs.getString("account"));
                password.add(rs.getString("password"));
                phone.add(rs.getString("phone_number"));
                email.add(rs.getString("user_email"));
                names.add(rs.getString("user_name"));
                times.add(rs.getString("create_time"));
                identity.add(rs.getString("identity"));
                subjects.add(rs.getString("department"));
                grade.add(rs.getString("grade"));
            }
            // year, month, day
            for(int i = 0 ; i < times.size() ; i ++){
                String[] s = times.get(i).split("-");
                year.add(s[0]);
                month.add(s[1]);
                day.add(s[2]);
            }
            ArrayList<Integer> matchIndex = new ArrayList<>();
            // check
            HashMap<String,String> map = new HashMap<>();
            map.put("學生","student");
            map.put("教師","teacher");
            map.put("管理者","manager");
            for(int i = 0 ; i < accounts.size() ; i ++){
                // check account
                if(!tf_account.getText().trim().equals("")){
                    if(!accounts.get(i).equals(tf_account.getText().trim()))
                        continue;
                }
                // check name
                if(!tf_name.getText().trim().equals("")){
                    if(!names.get(i).equals(tf_name.getText().trim()))
                        continue;
                }
                // check year
                if(!cb_year.getSelectedItem().toString().equals("全部")){
                    if(!cb_year.getSelectedItem().toString().equals(year.get(i)))
                        continue;
                }
                // check month
                if(!cb_month.getSelectedItem().toString().equals("全部")){
                    if(!cb_month.getSelectedItem().toString().equals(month.get(i)))
                        continue;
                }
                // check day
                if(!cb_day.getSelectedItem().toString().equals("全部")){
                    if(!cb_day.getSelectedItem().toString().equals(day.get(i)))
                        continue;
                }
                // check identity
                if(!cb_identity.getSelectedItem().toString().equals("全部")){
                    if(!map.get(cb_identity.getSelectedItem().toString()).equals(identity.get(i)))
                        continue;
                }
                if(cb_identity.getSelectedItem().toString().equals("學生")){
                    // check subject
                    if(!cb_sub.getSelectedItem().toString().equals("全部")){
                        if(!cb_sub.getSelectedItem().toString().equals(subjects.get(i)))
                            continue;
                    }
                    // check grade
                    if(!cb_grade.getSelectedItem().toString().equals("全部")){
                        if(!cb_grade.getSelectedItem().toString().equals(grade.get(i)))
                            continue;
                    }
                }
                matchIndex.add(i);
            }
            // show data
            if(matchIndex.size() == 0){
                b_delete.setVisible(false);
                JOptionPane.showMessageDialog(null,
                        "無符合資料","訊息",JOptionPane.DEFAULT_OPTION);
            }
            else{
                // table setting
                b_delete.setVisible(true);
                DefaultTableModel dtm = new DefaultTableModel(matchIndex.size(),9);
                dataTable.setModel(dtm);
                dataTable.getColumnModel().getColumn(0).setHeaderValue("帳號");
                dataTable.getColumnModel().getColumn(1).setHeaderValue("密碼");
                dataTable.getColumnModel().getColumn(2).setHeaderValue("姓名");
                dataTable.getColumnModel().getColumn(3).setHeaderValue("手機");
                dataTable.getColumnModel().getColumn(4).setHeaderValue("E-mail");
                dataTable.getColumnModel().getColumn(5).setHeaderValue("身分");
                dataTable.getColumnModel().getColumn(6).setHeaderValue("科系");
                dataTable.getColumnModel().getColumn(7).setHeaderValue("年級");
                dataTable.getColumnModel().getColumn(8).setHeaderValue("創建時間");
                for(int i = 0 ; i < matchIndex.size() ; i ++){
                    dataTable.setValueAt(accounts.get(matchIndex.get(i)),i,0);
                    dataTable.setValueAt(password.get(matchIndex.get(i)),i,1);
                    dataTable.setValueAt(names.get(matchIndex.get(i)),i,2);
                    dataTable.setValueAt(phone.get(matchIndex.get(i)),i,3);
                    dataTable.setValueAt(email.get(matchIndex.get(i)),i,4);
                    dataTable.setValueAt(identity.get(matchIndex.get(i)),i,5);
                    dataTable.setValueAt(subjects.get(matchIndex.get(i)),i,6);
                    dataTable.setValueAt(grade.get(matchIndex.get(i)),i,7);
                    dataTable.setValueAt(times.get(matchIndex.get(i)),i,8);
                }
                dataTable.setVisible(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void b_delete(ActionEvent e) {
        // TODO add your code here
        int[] s = dataTable.getSelectedRows();
        DefaultTableModel df = (DefaultTableModel) dataTable.getModel();
        Statement st = new GetDBdata().getStatement();
        for(int i = 0 ; i < s.length ; i ++) {
            df.removeRow(s[i]);
            try {
                System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
                st.execute("delete from user where account='" + df.getValueAt(s[i],0) + "'");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        tf_account = new JTextField();
        label2 = new JLabel();
        tf_name = new JTextField();
        label3 = new JLabel();
        cb_year = new JComboBox();
        label4 = new JLabel();
        cb_month = new JComboBox();
        label5 = new JLabel();
        cb_day = new JComboBox();
        label6 = new JLabel();
        label7 = new JLabel();
        cb_identity = new JComboBox();
        l_sub = new JLabel();
        cb_sub = new JComboBox();
        l_grade = new JLabel();
        cb_grade = new JComboBox();
        b_delete = new JButton();
        b_search = new JButton();
        scrollPane1 = new JScrollPane();
        dataTable = new JTable();

        //======== this ========
        setBackground(new Color(102, 255, 153));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
        EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing
        .border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),
        java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))
        throw new RuntimeException();}});

        //======== panel1 ========
        {
            panel1.setBackground(new Color(153, 153, 255));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 50, 170, 50, 170, 51, 0, 0, 0, 0, 0, 0, 50, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {34, 0, 32, 31, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u5e33\u865f");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            label1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tf_account, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u59d3\u540d");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            label2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tf_name, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("\u6642\u9593");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            label3.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label3, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cb_year, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label4 ----
            label4.setText("\u5e74");
            label4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label4, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cb_month, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label5 ----
            label5.setText("\u6708");
            label5.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label5, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cb_day, new GridBagConstraints(10, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label6 ----
            label6.setText("\u65e5");
            label6.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label6, new GridBagConstraints(11, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label7 ----
            label7.setText("\u8eab\u5206");
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            label7.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label7, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- cb_identity ----
            cb_identity.addActionListener(e -> cb_identity(e));
            panel1.add(cb_identity, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- l_sub ----
            l_sub.setText("\u79d1\u7cfb");
            l_sub.setHorizontalAlignment(SwingConstants.RIGHT);
            l_sub.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(l_sub, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cb_sub, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- l_grade ----
            l_grade.setText("\u5e74\u7d1a");
            l_grade.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            l_grade.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(l_grade, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cb_grade, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- b_delete ----
            b_delete.setText("\u522a\u9664\u5e33\u865f");
            b_delete.setBackground(new Color(255, 51, 51));
            b_delete.addActionListener(e -> b_delete(e));
            panel1.add(b_delete, new GridBagConstraints(12, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- b_search ----
            b_search.setText("\u641c\u5c0b");
            b_search.addActionListener(e -> b_search(e));
            panel1.add(b_search, new GridBagConstraints(13, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(dataTable);
            }
            panel1.add(scrollPane1, new GridBagConstraints(1, 3, 13, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 1050, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
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
    private JTextField tf_account;
    private JLabel label2;
    private JTextField tf_name;
    private JLabel label3;
    private JComboBox cb_year;
    private JLabel label4;
    private JComboBox cb_month;
    private JLabel label5;
    private JComboBox cb_day;
    private JLabel label6;
    private JLabel label7;
    private JComboBox cb_identity;
    private JLabel l_sub;
    private JComboBox cb_sub;
    private JLabel l_grade;
    private JComboBox cb_grade;
    private JButton b_delete;
    private JButton b_search;
    private JScrollPane scrollPane1;
    private JTable dataTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
