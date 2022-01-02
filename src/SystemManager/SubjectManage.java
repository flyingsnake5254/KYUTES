/*
 * Created by JFormDesigner on Tue Dec 21 16:04:45 CST 2021
 */

package SystemManager;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author peiChun lu
 */
public class SubjectManage extends JPanel {
    public SubjectManage() {
        initComponents();
        pageInitial();
    }

    private void pageInitial(){
        SubjectSearch subjectSearch = new SubjectSearch();
        subjectSearch.setSize(new Dimension(tabbedPane1.getWidth(), tabbedPane1.getHeight()));
        tabbedPane1.add("測驗科目查詢",subjectSearch);
        CreateSubject createSubject = new CreateSubject();
        createSubject.setSize(new Dimension(tabbedPane1.getWidth(),tabbedPane1.getHeight()));
        tabbedPane1.add("創建測驗科目",createSubject);
        GroupSujectAdd groupSujectAdd = new GroupSujectAdd();
        groupSujectAdd.setSize(new Dimension(tabbedPane1.getWidth(),tabbedPane1.getHeight()));
        tabbedPane1.add("新增群組測驗科目",groupSujectAdd);
        GroupSujectDelete groupSujectDelete = new GroupSujectDelete();
        groupSujectDelete.setSize(new Dimension(tabbedPane1.getWidth(),tabbedPane1.getHeight()));
        tabbedPane1.add("查詢與刪除群組測驗科目",groupSujectDelete);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - peiChun lu
        panel1 = new JPanel();
        tabbedPane1 = new JTabbedPane();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
        . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder
        . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .
        awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder( )) )
        ;  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
        ;

        //======== panel1 ========
        {
            panel1.setBackground(new Color(204, 204, 204));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
            );
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
    private JTabbedPane tabbedPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
