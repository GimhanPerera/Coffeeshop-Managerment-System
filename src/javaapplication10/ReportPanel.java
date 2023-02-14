/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaapplication10;

/**
 *
 * @author Gimhan
 */
public class ReportPanel extends javax.swing.JPanel {

    /**
     * Creates new form ReportPanel
     */
    public ReportPanel() {
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

        lbl_time = new javax.swing.JLabel();
        lbl_report = new javax.swing.JLabel();
        combox_reportType = new javax.swing.JComboBox<>();
        combox_timeFrame = new javax.swing.JComboBox<>();
        btn_generate = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_time1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_time.setText("Time frame");
        add(lbl_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, 20));

        lbl_report.setText("Report Type");
        add(lbl_report, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, 20));

        combox_reportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income Report", "Beverages Report", "Food Report" }));
        add(combox_reportType, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 230, 30));

        combox_timeFrame.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Monthly", "Yearly" }));
        add(combox_timeFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 230, 30));

        btn_generate.setText("Generate");
        add(btn_generate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, -1, -1));

        btn_print.setText("Print");
        add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 180, -1, -1));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 910, 390));

        lbl_time1.setText("Time frame");
        add(lbl_time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, 20));

        jTextField1.setText("2023-02-05");
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 160, -1));

        jButton1.setText("...");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 50, -1));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 620));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_generate;
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> combox_reportType;
    private javax.swing.JComboBox<String> combox_timeFrame;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_report;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JLabel lbl_time1;
    // End of variables declaration//GEN-END:variables
}
