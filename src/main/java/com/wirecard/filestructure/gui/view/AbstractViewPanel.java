package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.model.InstructionFileTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class AbstractViewPanel extends JPanel {

    private static final long serialVersionUID = -7421931849472129096L;


    protected void addRow(JTable table, InstructionFileTableModel model){
        Vector vector = new Vector();
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);

        int index = table.getSelectedRow();
        if(index == -1){
            if(table.getRowCount() == 0) {
                index = 0;
            }else{
                index = table.getRowCount();
            }
        }else{
            index = table.getSelectedRow()+1;
        }

        vector.set(1,new Integer(index + 1));
        model.addRow(index,vector);
    }

    protected void removeRow(JTable table, InstructionFileTableModel model){
        model.removeRow(table.getSelectedRows());
    }

    protected void removeRow(JTable table){
        int[] row = table.getSelectedRows();
        DefaultTableModel model = (DefaultTableModel)table.getModel();

        Vector vector = new Vector();
        vector.addAll(model.getDataVector());

        List selectedRows = new ArrayList();

        for(int i = 0; i < row.length; i++){
            selectedRows.add(i,vector.get(row[i]));
        }
        vector.removeAll(selectedRows);

        Vector newVector = new Vector();
        newVector.addAll(vector);
        int rowCount = table.getRowCount();
        for(int i = 0; i < rowCount; i++){
            model.removeRow(0);
        }

        for(int i = 0; i < newVector.size(); i++) {
            model.addRow((Vector)newVector.get(i));
        }
    }

    protected AbstractViewPanel getContainer(){
        return this;
    }

}
