package com.wirecard.filestructure.gui.model;

import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StructureTableModel extends AbstractTableModel {
    private String[] columnNames  = Constants.FILE_STRUCTURE_TABLE_COLUMN;
    private List<Object[]> data = new ArrayList<Object[]>();


//    public StructureTableModel(){
//        data.add(new Object[]{null,null,null,false});
//    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if(columnIndex == 3) {
            return true;
        }else{
            return false;
        }
    }

    public Class getColumnClass(int col){
       if(col == 0){
           return String.class;
       }else if(col == 1){
           return String.class;
       }else if(col == 2){
           return String.class;
       }else if(col == 3){
           return Boolean.class;
       }else{
           return String.class;
       }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue;
        fireTableDataChanged();
    }

}
