/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Chef;

import DBconnection.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gimhan
 */
public class chefMenu extends javax.swing.JPanel {

    /**
     * Creates new form chefMenu
     */
    public chefMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_menu0 = new javax.swing.JTable();
        jComboBox_category = new javax.swing.JComboBox<>();
        lbl_foodType = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_menu0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Today Available Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_menu0.setPreferredSize(new java.awt.Dimension(300, 140));
        jTable_menu0.setRowHeight(35);
        jScrollPane1.setViewportView(jTable_menu0);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 850, 475));

        jComboBox_category.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jComboBox_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coffees", "Cakes", "Buns" }));
        jComboBox_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_categoryActionPerformed(evt);
            }
        });
        add(jComboBox_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 140, -1));

        lbl_foodType.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lbl_foodType.setText("Food type");
        add(lbl_foodType, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_categoryActionPerformed
        try {
            switch(jComboBox_category.getSelectedIndex()){
            case 0:
                getFood((byte)1);
                break;
            case 1:
                getFood((byte)2);
                break;
            case 2:
                getFood((byte)3);
                break;
        }
            } catch (Exception ex) {
                    Logger.getLogger(chefMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
    }//GEN-LAST:event_jComboBox_categoryActionPerformed
    
    public void getFood(byte i) throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection(); 
        try{ 
            Statement stmt = null;
            switch(i)
            {
                case 1:
                stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select FOOD_NAME,UNIT_PRICE from FOOD WHERE CATEGORY='COFFEE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String price=rs.getString("UNIT_PRICE");
                    String tbData[]={foodName,"",price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu0.getModel(); 
                    tblModel.addRow(tbData);
                }
                    break;
                case 2:
                rs = stmt.executeQuery("select FOOD_NAME,UNIT_PRICE from FOOD WHERE CATEGORY='CAKE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String price=rs.getString("UNIT_PRICE");
                    String tbData[]={foodName,"",price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu0.getModel(); 
                    tblModel.addRow(tbData);
                }
                break;
                case 3:
                rs = stmt.executeQuery("select FOOD_NAME,UNIT_PRICE from FOOD WHERE CATEGORY='BUN'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String price=rs.getString("UNIT_PRICE");
                    String tbData[]={foodName,"",price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu0.getModel();
                    tblModel.addRow(tbData);
                }
                break;
            }
                               
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            System.out.println(ex);
        }
        finally{
            c.close(); 
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox_category;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_menu0;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_foodType;
    // End of variables declaration//GEN-END:variables
}
