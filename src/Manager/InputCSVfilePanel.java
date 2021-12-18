/*
 * Created by JFormDesigner on Sat Dec 18 14:48:49 CST 2021
 */

package Manager;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class InputCSVfilePanel extends JPanel {
    static String[] comboBoxItems = {"","學生","教師","管理者"};
    public InputCSVfilePanel() {
        initComponents();
        for(String s : comboBoxItems)
            selectIdentity.addItem(s);
    }

    File csvFile;

    private void b_inputCSV(ActionEvent e) {
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

    private void selectIdentity(ActionEvent e) {
        // TODO add your code here
        if(selectIdentity.getSelectedItem().toString().equals("學生")){
            panel1.removeAll();
            CSVfileStudentPanel studentPanel = new CSVfileStudentPanel();
            studentPanel.setSize(panel1.getWidth(),panel1.getHeight());
            studentPanel.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
            panel1.add(studentPanel);
            System.out.println(this);
            this.invalidate();
            this.validate();
        }
        else if(selectIdentity.getSelectedItem().toString().equals("教師")){
            panel1.removeAll();
            CSVfileTeacherPanel teacherPanel = new CSVfileTeacherPanel();
            teacherPanel.setSize(panel1.getWidth(),panel1.getHeight());
            teacherPanel.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
            panel1.add(teacherPanel);
            this.invalidate();
            this.validate();
        }
        else if(selectIdentity.getSelectedItem().toString().equals("管理者")){
            panel1.removeAll();
            CSVfileManagerPanel managerPanel = new CSVfileManagerPanel();
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

    private void selectIdentityItemStateChanged(ItemEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        b_inputCSV = new JButton();
        l_fileName = new JLabel();
        selectIdentity = new JComboBox();
        panel1 = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(892, 621));
        setBackground(new Color(153, 255, 153));
        setBorder(null);
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
        ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
        .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
        . Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
        propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
        ;} } );

        //---- b_inputCSV ----
        b_inputCSV.setText("\u532f\u5165CSV");
        b_inputCSV.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        b_inputCSV.addActionListener(e -> b_inputCSV(e));

        //---- l_fileName ----
        l_fileName.setHorizontalTextPosition(SwingConstants.LEADING);

        //---- selectIdentity ----
        selectIdentity.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.PLAIN, 16));
        selectIdentity.addActionListener(e -> selectIdentity(e));
        selectIdentity.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectIdentityMouseClicked(e);
            }
        });
        selectIdentity.addItemListener(e -> selectIdentityItemStateChanged(e));

        //======== panel1 ========
        {

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 822, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 467, Short.MAX_VALUE)
            );
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(b_inputCSV)
                                    .addGap(41, 41, 41)
                                    .addComponent(l_fileName, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
                                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(selectIdentity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(b_inputCSV)
                        .addComponent(l_fileName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(selectIdentity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - peiChun lu
    private JButton b_inputCSV;
    private JLabel l_fileName;
    private JComboBox selectIdentity;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
