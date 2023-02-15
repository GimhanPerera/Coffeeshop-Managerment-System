/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaapplication10;

/**
 *
 * @author Gimhan
 */
public class LoyaltyCardPanel extends javax.swing.JPanel {

    /**
     * Creates new form LoyaltyCardPanel
     */
    public LoyaltyCardPanel() {
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

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(173, 85, 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 30));

        jButton1.setText("Search");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"34D87E", "0773428472", "Unblock", "Lakmal Withana"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Card ID", "Customer Mobile Number", "Status", "Issued by"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 800, 310));

        jButton2.setText("Add");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 440, -1, -1));

        jButton3.setText("Remove");
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 550, -1, -1));

        jButton4.setText("View All");
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, -1, -1));

        jButton5.setText("Block");
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 480, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Loyalty Card Details");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer Telephone Number", "Loyalty Card ID" }));
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 240, -1));
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 160, -1));

        jLabel2.setText("Search by");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Blocked", "Not Issued" }));
        add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 120, -1));

        jButton6.setText("Filter");
        add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 100, -1));

        jRadioButton1.setText("Filter Food");
        add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Search Food");
        add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 620));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_background;
    // End of variables declaration//GEN-END:variables
}
