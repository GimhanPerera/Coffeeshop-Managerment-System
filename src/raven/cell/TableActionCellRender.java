/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Gimhan
 */
public class TableActionCellRender extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int i1) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, i1); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        PanelAction action=new PanelAction();
        if(isSeleted==false && row%2==0)
            action.setBackground(Color.WHITE);
        else{
            action.setBackground(com.getBackground());
        }
        
        return action;
    }
    
    
}
