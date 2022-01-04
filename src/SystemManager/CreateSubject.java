/*
 * Created by JFormDesigner on Tue Dec 21 16:59:05 CST 2021
 */

package SystemManager;

import javax.swing.plaf.*;
import DataClass.GetDBdata;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class CreateSubject extends JPanel {
    public CreateSubject() {
        initComponents();
    }

    private void bCreate(ActionEvent e) {
        // TODO add your code here
        String subjectName = tfSubjectName.getText().trim();
        if(subjectName.equals(""))
            JOptionPane.showMessageDialog(
                    null,
                    "科目名稱不可為空",
                    "錯誤",
                    JOptionPane.ERROR_MESSAGE
            );
        else{
            // get subject
            boolean createState = true;
            ArrayList<String> subjects = new ArrayList<>();
            Statement st = new GetDBdata().getStatement();
            try {
                st.execute("select * from suject");
                ResultSet rs = st.getResultSet();
                while(rs.next())
                    subjects.add(rs.getString("name"));
                if(subjects.contains(subjectName)) {
                    createState = false;
                    JOptionPane.showMessageDialog(
                            null,
                            "此科目已被建立",
                            "錯誤",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                else{
                    // add to subject table
                    Statement st2 = new GetDBdata().getStatement();
                    st2.execute("insert into suject (name,bank_num,question_num) " +
                            "values " +
                            "('"+subjectName+"','0','0')");
                    // 建立題庫
                    st2.execute("create table "+subjectName+"(" +
                            "name varchar(30) not null default ''," +
                            "question_num varchar(30) not null default '0'," +
                            "primary key(name))");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if(createState)
                JOptionPane.showMessageDialog(
                        null,
                        "創建成功",
                        "訊息",
                        JOptionPane.DEFAULT_OPTION
                );

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        label1 = new JLabel();
        tfSubjectName = new JTextField();
        bCreate = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );

        //======== panel1 ========
        {
            panel1.setBackground(new Color(214, 214, 214));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {43, 0, 166, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {47, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //---- label1 ----
            label1.setText("\u79d1\u76ee");
            label1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- tfSubjectName ----
            tfSubjectName.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            panel1.add(tfSubjectName, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- bCreate ----
            bCreate.setText("\u5275\u5efa");
            bCreate.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 14));
            bCreate.addActionListener(e -> bCreate(e));
            panel1.add(bCreate, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
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
    private JTextField tfSubjectName;
    private JButton bCreate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
