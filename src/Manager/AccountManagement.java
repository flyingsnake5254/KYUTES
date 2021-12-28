/*
 * Created by JFormDesigner on Mon Dec 20 22:24:40 CST 2021
 */

package Manager;

import DataClass.*;

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
        comboBoxInitial();
        dataTable.setVisible(false);
        bDelete.setVisible(false);
        dataTable.getTableHeader().setReorderingAllowed(false);

    }
    
    private void comboBoxInitial(){
        cbYear.addItem("全部");
        cbMonth.addItem("全部");
        cbDay.addItem("全部");
        cbSub.addItem("全部");
        cbGrade.addItem("全部");
        cbIdentity.addItem("全部");
        for(String s : Data.USER_IDENTITIES_CN)
            cbIdentity.addItem(s);
        
        for(String s : Data.SUBJECTS)
            cbSub.addItem(s);
        
        for(String s : Data.STUDENT_GRADES)
            cbGrade.addItem(s);
        
        String[] data = LocalDate.now().toString().split("-");
        int y = Integer.parseInt(data[0]);
        for(int i = 0 ; i < 10 ; i ++, y --){
            cbYear.addItem(String.valueOf(y));
        }
        for(int i = 1 ; i <= 12 ; i ++)
            cbMonth.addItem(String.valueOf(i));
        for(int i = 1 ; i <= 31 ; i ++)
            cbDay.addItem(String.valueOf(i));
        
        
    }

    private void cbIdentity(ActionEvent e) {
        // TODO add your code here
        if(cbIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_STUDENT_CN)){
            lSub.setVisible(true);
            cbSub.setVisible(true);
            lGrade.setVisible(true);
            cbGrade.setVisible(true);
        }
        else{
            lSub.setVisible(false);
            cbSub.setVisible(false);
            lGrade.setVisible(false);
            cbGrade.setVisible(false);
        }
    }

    private void bSearch(ActionEvent e) {
        // TODO add your code here
        Users users = new Users();
        ArrayList<User> userArrayList= users.getUsers();
        int tableIndex = 0;
        DefaultTableModel dtm = new DefaultTableModel(userArrayList.size(),9);
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
        for(int i = 0 ; i < userArrayList.size() ; i ++) {
            // check account
            if(!tfAccount.getText().trim().equals("")){
                if(!userArrayList.get(i).getAccount().equals(tfAccount.getText().trim()))
                    continue;
            }
            // check name
            if(!tfName.getText().trim().equals("")){
                if(!userArrayList.get(i).getUserName().equals(tfName.getText().trim()))
                    continue;
            }
            // check year
            if(!cbYear.getSelectedItem().toString().equals("全部")){
                if(!cbYear.getSelectedItem().toString().equals(userArrayList.get(i).getYear()))
                    continue;
            }
            // check month
            if(!cbMonth.getSelectedItem().toString().equals("全部")){
                if(!cbMonth.getSelectedItem().toString().equals(userArrayList.get(i).getMonth()))
                    continue;
            }
            // check day
            if(!cbDay.getSelectedItem().toString().equals("全部")){
                if(!cbDay.getSelectedItem().toString().equals(userArrayList.get(i).getDay()))
                    continue;
            }
            // check identity
            if(!cbIdentity.getSelectedItem().toString().equals("全部")){
                if(!cbIdentity.getSelectedItem().toString().equals(userArrayList.get(i).getCNIdentity()))
                    continue;
            }
            if(cbIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_STUDENT_CN)){
                // check department
                if(!cbSub.getSelectedItem().toString().equals("全部")){
                    if(!cbSub.getSelectedItem().toString().equals(userArrayList.get(i).getDepartment()))
                        continue;
                }
                // check grade
                if(!cbGrade.getSelectedItem().toString().equals("全部")){
                    if(!cbGrade.getSelectedItem().toString().equals(userArrayList.get(i).getGrade()))
                        continue;
                }
            }
            dataTable.setValueAt(userArrayList.get(i).getAccount(),tableIndex,0);
            dataTable.setValueAt(userArrayList.get(i).getPassword(),tableIndex,1);
            dataTable.setValueAt(userArrayList.get(i).getUserName(),tableIndex,2);
            dataTable.setValueAt(userArrayList.get(i).getPhoneNumber(),tableIndex,3);
            dataTable.setValueAt(userArrayList.get(i).getUserEmail(),tableIndex,4);
            dataTable.setValueAt(userArrayList.get(i).getCNIdentity(),tableIndex,5);
            dataTable.setValueAt(userArrayList.get(i).getDepartment(),tableIndex,6);
            dataTable.setValueAt(userArrayList.get(i).getGrade(),tableIndex,7);
            dataTable.setValueAt(userArrayList.get(i).getCreateTime(),tableIndex,8);
            tableIndex ++;

        }
        dataTable.setVisible(true);
        bDelete.setVisible(true);

    }

    private void bDelete(ActionEvent e) {
        // TODO add your code here
        Users users = new Users();
        int[] s = dataTable.getSelectedRows();
        ArrayList<String> deleteAccount = new ArrayList<>();
        // get selected account
        for(int i = 0 ; i < s.length ; i ++){
            deleteAccount.add(dataTable.getModel().getValueAt(s[i],0).toString());
        }

        for(int i = 0 ; i < deleteAccount.size() ; i ++){
            User user = users.getUser(deleteAccount.get(i));
            if(!user.getStudentGroup().equals("")) {
                Group group = new Group(user.getStudentGroup());
                group.updateGroupPeopleNumber(-1);
            }
            User.deleteUser(user.getAccount());
            for(int j = 0 ; j < dataTable.getRowCount() ; j ++){
                if(dataTable.getValueAt(j,0).toString().equals(user.getAccount())){
                    DefaultTableModel df = (DefaultTableModel) dataTable.getModel();
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
        tfAccount = new JTextField();
        label2 = new JLabel();
        tfName = new JTextField();
        label3 = new JLabel();
        cbYear = new JComboBox();
        label4 = new JLabel();
        cbMonth = new JComboBox();
        label5 = new JLabel();
        cbDay = new JComboBox();
        label6 = new JLabel();
        label7 = new JLabel();
        cbIdentity = new JComboBox();
        lSub = new JLabel();
        cbSub = new JComboBox();
        lGrade = new JLabel();
        cbGrade = new JComboBox();
        bSearch = new JButton();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        dataTable = new JTable();
        bDelete = new JButton();

        //======== this ========
        setBackground(new Color(102, 255, 153));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
        .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax
        . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,
        12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans
        .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e.
        getPropertyName () ) )throw new RuntimeException( ) ;} } );

        //======== panel1 ========
        {
            panel1.setBackground(new Color(204, 204, 204));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {82, 50, 170, 50, 170, 51, 109, 25, 83, 0, 114, 123, 151, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {34, 0, 32, 521, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u5e33\u865f");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            label1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfAccount, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label2 ----
            label2.setText("\u59d3\u540d");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            label2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(tfName, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("\u6642\u9593");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            label3.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label3, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbYear, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label4 ----
            label4.setText("\u5e74");
            label4.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label4, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbMonth, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label5 ----
            label5.setText("\u6708");
            label5.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label5, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbDay, new GridBagConstraints(10, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- label6 ----
            label6.setText("\u65e5");
            label6.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label6, new GridBagConstraints(13, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- label7 ----
            label7.setText("\u8eab\u5206");
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            label7.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label7, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- cbIdentity ----
            cbIdentity.addActionListener(e -> cbIdentity(e));
            panel1.add(cbIdentity, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lSub ----
            lSub.setText("\u79d1\u7cfb");
            lSub.setHorizontalAlignment(SwingConstants.RIGHT);
            lSub.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(lSub, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbSub, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lGrade ----
            lGrade.setText("\u5e74\u7d1a");
            lGrade.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            lGrade.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(lGrade, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(cbGrade, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bSearch ----
            bSearch.setText("\u641c\u5c0b");
            bSearch.addActionListener(e -> bSearch(e));
            panel1.add(bSearch, new GridBagConstraints(8, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //======== panel2 ========
            {

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(dataTable);
                }

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 941, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 953, Short.MAX_VALUE)
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                                .addContainerGap()))
                        .addGap(0, 516, Short.MAX_VALUE)
                );
            }
            panel1.add(panel2, new GridBagConstraints(1, 3, 11, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- bDelete ----
            bDelete.setText("\u522a\u9664\u5e33\u865f");
            bDelete.setBackground(new Color(255, 51, 51));
            bDelete.addActionListener(e -> bDelete(e));
            panel1.add(bDelete, new GridBagConstraints(11, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JPanel panel1;
    private JLabel label1;
    private JTextField tfAccount;
    private JLabel label2;
    private JTextField tfName;
    private JLabel label3;
    private JComboBox cbYear;
    private JLabel label4;
    private JComboBox cbMonth;
    private JLabel label5;
    private JComboBox cbDay;
    private JLabel label6;
    private JLabel label7;
    private JComboBox cbIdentity;
    private JLabel lSub;
    private JComboBox cbSub;
    private JLabel lGrade;
    private JComboBox cbGrade;
    private JButton bSearch;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable dataTable;
    private JButton bDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
