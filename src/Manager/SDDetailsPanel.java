/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gimhan
 */
public class SDDetailsPanel extends javax.swing.JPanel {

    /**
     * Creates new form sdDetailsPanel
     */
    public SDDetailsPanel() {
        initComponents();
        lbl_Error.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdo_stuff = new javax.swing.JRadioButton();
        rdo_customer = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        lbl_searchtitle = new javax.swing.JLabel();
        lbl_Error = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(173, 85, 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 1170, 30));

        jPanel1.setBackground(new java.awt.Color(173, 85, 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Customer/Stuff Details");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        buttonGroup1.add(rdo_stuff);
        rdo_stuff.setText("Stuff Details");
        rdo_stuff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_stuffActionPerformed(evt);
            }
        });
        add(rdo_stuff, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, -1, -1));

        buttonGroup1.add(rdo_customer);
        rdo_customer.setSelected(true);
        rdo_customer.setText("Customer Details");
        rdo_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_customerActionPerformed(evt);
            }
        });
        add(rdo_customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 350, 100, 40));

        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 620, 110, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 910, 420));

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 569, 110, 40));

        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
        });
        add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 220, 40));

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 110, 40));

        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 290, 100, 40));

        lbl_searchtitle.setText("Search by Customer Telephone Number");
        add(lbl_searchtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 300, -1));

        lbl_Error.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Error.setText("Error msg");
        add(lbl_Error, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 330, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        lbl_Error.setVisible(false);
        AddStuffMember obj=new AddStuffMember();
        obj.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void rdo_stuffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_stuffActionPerformed
        lbl_searchtitle.setText("Search by ctuff ID");
    }//GEN-LAST:event_rdo_stuffActionPerformed

    private void rdo_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_customerActionPerformed
        lbl_searchtitle.setText("Search by Customer Telephone Number");
    }//GEN-LAST:event_rdo_customerActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            lbl_Error.setVisible(false);
            clearTable();
            //getFoodFromSearch(jComboBox_searchby.getSelectedIndex(),txt_search.getText());
            //jTable_food.setRowSelectionInterval(0, 0);
        } catch (Exception ex) {
            Logger.getLogger(FoodDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
            lbl_Error.setVisible(true);
            lbl_Error.setText("No result. Please check entered value");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        lbl_Error.setVisible(false);
        clearTable();
        //need to code
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        lbl_Error.setVisible(false);
        clearTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        lbl_Error.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        txt_search.setText("");
    }//GEN-LAST:event_txt_searchMouseClicked
    
    public void clearTable(){
        DefaultTableModel tblModel =(DefaultTableModel)jTable1.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_Error;
    private javax.swing.JLabel lbl_searchtitle;
    private javax.swing.JRadioButton rdo_customer;
    private javax.swing.JRadioButton rdo_stuff;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
