/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Manager;
import DBconnection.mgrDashboard;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardPanel extends javax.swing.JPanel {

    /**
     * Creates new form DashboardPanel
     */
    public DashboardPanel() {
        try {
            initComponents();
            mgrDashboard obj=new mgrDashboard();
            lbl_ocount.setText(Integer.toString(obj.orderCount()));
            lbl_monthlyincome.setText(Integer.toString(obj.monthlyIncome()));
            lbl_actCardCount.setText(Integer.toString(obj.getActiveLoyaltycardCount()));
            lbl_todayIncome.setText(Integer.toString(obj.todayIncome()));
            lbl_best_beverage.setText(obj.bestBevLastMonth());
            lbl_best_nonbeverage.setText(obj.bestNonBevLastMonth());
        } catch (Exception ex) {
            Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_up = new javax.swing.JPanel();
        jPanel_Down = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_todayIncome = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_monthlyincome = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_best_nonbeverage = new javax.swing.JLabel();
        lbl_best_beverage = new javax.swing.JLabel();
        lbl_actCardCount = new javax.swing.JLabel();
        lbl_ocount = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1170, 740));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_up.setBackground(new java.awt.Color(173, 85, 2));
        jPanel_up.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel_up, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 30));

        jPanel_Down.setBackground(new java.awt.Color(173, 85, 2));
        jPanel_Down.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel_Down, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 1170, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setText("Dashboard");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel1.setText("Active Loyalty Cards");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 510, -1, -1));

        lbl_todayIncome.setFont(new java.awt.Font("Segoe UI Semibold", 1, 40)); // NOI18N
        lbl_todayIncome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_todayIncome.setText("6380");
        add(lbl_todayIncome, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 120, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel3.setText("Today Income");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel4.setText("Monthly Income");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel5.setText("of This Month"); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel7.setText("Best non-Beverage item"); // NOI18N
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel6.setText(" of This Month");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel8.setText("Best Beverage item");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, -1, -1));

        lbl_monthlyincome.setFont(new java.awt.Font("Segoe UI Semibold", 1, 40)); // NOI18N
        lbl_monthlyincome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_monthlyincome.setText("45380");
        add(lbl_monthlyincome, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, 120, 60));

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel9.setText("Today Order Count");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, -1, -1));

        lbl_best_nonbeverage.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lbl_best_nonbeverage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_best_nonbeverage.setText("Chicken Sandwich");
        add(lbl_best_nonbeverage, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 290, 60));

        lbl_best_beverage.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        lbl_best_beverage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_best_beverage.setText("Espresso");
        add(lbl_best_beverage, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 240, 60));

        lbl_actCardCount.setFont(new java.awt.Font("Segoe UI Semibold", 1, 40)); // NOI18N
        lbl_actCardCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_actCardCount.setText("4");
        add(lbl_actCardCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 430, 120, 60));

        lbl_ocount.setFont(new java.awt.Font("Segoe UI Semibold", 1, 40)); // NOI18N
        lbl_ocount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ocount.setText("4");
        add(lbl_ocount, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 120, 60));

        lbl_background.setBackground(new java.awt.Color(255, 255, 255));
        lbl_background.setOpaque(true);
        lbl_background.setPreferredSize(new java.awt.Dimension(980, 680));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 770));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel_Down;
    private javax.swing.JPanel jPanel_up;
    private javax.swing.JLabel lbl_actCardCount;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_best_beverage;
    private javax.swing.JLabel lbl_best_nonbeverage;
    private javax.swing.JLabel lbl_monthlyincome;
    private javax.swing.JLabel lbl_ocount;
    private javax.swing.JLabel lbl_todayIncome;
    // End of variables declaration//GEN-END:variables
}
