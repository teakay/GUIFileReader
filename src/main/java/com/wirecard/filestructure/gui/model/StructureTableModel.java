package com.wirecard.filestructure.gui.model;

import com.wirecard.filestructure.gui.utils.Constants;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StructureTableModel extends AbstractTableModel {
    private String[] columnNames  = Constants.FILE_STRUCTURE_TABLE_COLUMN;
    private List<Vector> data = new ArrayList<Vector>();


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
        return data.get(rowIndex).get(columnIndex);
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if(columnIndex == 6) {
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
           return String.class;
       }
       else if(col == 4){
           return String.class;
       }
       else if(col == 5){
           return String.class;
       }
       else{
           return String.class;
       }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex).set(columnIndex, aValue);
        fireTableDataChanged();
    }

    public void addRow(Vector dataRow){
        data.add(dataRow);
        fireTableDataChanged();
    }

    public void addRow(List dataRow){
        data.addAll(dataRow);
        fireTableDataChanged();
    }

    public void updateRow(){
        fireTableDataChanged();
    }

    public void deleteRow(List dataRow){
        data.removeAll(dataRow);
        fireTableDataChanged();
    }
    public void deleteRow(int index){
        data.remove(index);
        fireTableDataChanged();
    }
    public void deleteRow(){
        data.remove(getRowCount() - 1);
        fireTableDataChanged();
    }
    public Vector getRowData(int row){
        return data.get(row);
    }
    public List getElements(int[] index){
        List<Vector> selectedRows = new ArrayList<Vector>();
        for(int i = 0; i < index.length; i++) {
            selectedRows.add(data.get(index[i]));
        }
        return selectedRows;
    }
}
