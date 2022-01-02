/*
 * Created by JFormDesigner on Sat Dec 18 14:48:49 CST 2021
 */

package SystemManager;

import DataClass.Data;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class InputCSVfilePanel extends JPanel {
    public InputCSVfilePanel() {
        initComponents();
        comboBoxInitial();
    }

    private void comboBoxInitial(){
        selectIdentity.addItem("");
        selectIdentity.addItem(Data.USER_IDENTITY_STUDENT_CN);
        selectIdentity.addItem(Data.USER_IDENTITY_EXAM_MANAGER_CN);
        selectIdentity.addItem(Data.USER_IDENTITY_SYSTEM_MANAGER_CN);
    }


    private void cbSelectIdentity(ActionEvent e) {
        // TODO add your code here
        if(selectIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_STUDENT_CN)){
            panel1.removeAll();
            CSVfileStudentPanel studentPanel = new CSVfileStudentPanel();
            studentPanel.setSize(panel1.getWidth(),panel1.getHeight());
            studentPanel.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
            panel1.add(studentPanel);
            System.out.println(this);
            this.invalidate();
            this.validate();
        }
        else if(selectIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_EXAM_MANAGER_CN)){
            panel1.removeAll();
            CSVfileExamManagerPanel teacherPanel = new CSVfileExamManagerPanel();
            teacherPanel.setSize(panel1.getWidth(),panel1.getHeight());
            teacherPanel.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
            panel1.add(teacherPanel);
            this.invalidate();
            this.validate();
        }
        else if(selectIdentity.getSelectedItem().toString().equals(Data.USER_IDENTITY_SYSTEM_MANAGER_CN)){
            panel1.removeAll();
            CSVfileSystemManagerPanel managerPanel = new CSVfileSystemManagerPanel();
            managerPanel.setSize(panel1.getWidth(),panel1.getHeight());
            managerPanel.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
            panel1.add(managerPanel);
            this.invalidate();
            this.validate();
        }
        else{
            panel1.removeAll();
            EmptyPanel emptyPanel = new EmptyPanel();
            emptyPanel.setSize(panel1.getWidth(),panel1.getHeight());
            emptyPanel.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
            panel1.add(emptyPanel);
            this.invalidate();
            this.validate();
        }
    }

    private void selectIdentityMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        label1 = new JLabel();
        selectIdentity = new JComboBox();
        panel1 = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(892, 621));
        setBackground(new Color(204, 204, 204));
        setBorder(null);
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 1056, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {42, 0, 0, 627, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("\u8eab\u5206");
        add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- selectIdentity ----
        selectIdentity.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        selectIdentity.addActionListener(e -> cbSelectIdentity(e));
        add(selectIdentity, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(204, 204, 204));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 1145, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 652, Short.MAX_VALUE)
            );
        }
        add(panel1, new GridBagConstraints(2, 2, 2, 2, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JLabel label1;
    private JComboBox selectIdentity;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
