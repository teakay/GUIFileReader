package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.StructureFileDetail;
import com.wirecard.filestructure.gui.service.StructureFileService;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTableController extends AbstractController {
    protected StructureFileService structureFileService;

    public List<StructureFile> getStructureList() throws Exception {
        try {
            return structureFileService.getStructureFileListModel();
        } catch (Exception e) {
            throw e;
        }
    }

    public Map getColumnFromStructure(String structureName) throws Exception {
        Map resultMap = new HashMap();
        try {
            List structureDetailList = structureFileService.getStructureFileAndDetailByStructureName(structureName);
            List headerList = new ArrayList();
            List detailList = new ArrayList();
            List footerList = new ArrayList();
//
//            List headerLengthList = new ArrayList();
//            List detailLengthList = new ArrayList();
//            List footerLengthList = new ArrayList();

            for(int i = 0; i < structureDetailList.size(); i++){
                StructureFileDetail sfd = (StructureFileDetail)structureDetailList.get(i);
                if("header".equals(sfd.getDetailType())){
                    headerList.add(sfd.getSequenceNo()-1,sfd);
//                    headerLengthList.add(sfd.getSequenceNo()-1,sfd.getDataLength());
                }else if("detail".equals(sfd.getDetailType())){
                    detailList.add(sfd.getSequenceNo()-1, sfd);
//                    detailLengthList.add(sfd.getSequenceNo()-1,sfd.getDataLength());
                }else if("footer".equals(sfd.getDetailType())){
                    footerList.add(sfd.getSequenceNo()-1, sfd);
//                    footerLengthList.add(sfd.getSequenceNo()-1,sfd.getDataLength());
                }
            }

            resultMap.put("header",headerList);
            resultMap.put("detail",detailList);
            resultMap.put("footer",footerList);

//            resultMap.put("headerLength",headerLengthList);
//            resultMap.put("detailLenght",detailLengthList);
//            resultMap.put("footerLength",footerLengthList);

        } catch (Exception e) {
            throw e;
        }
        return resultMap;
    }

    public void saveAsFile(String structureName, JTable headerTable, JTable detailTable, JTable footerTable, String filePath) throws Exception {
        try {
            List structureDetailList = structureFileService.getStructureFileAndDetailByStructureName(structureName);

            Map headerMap = new HashMap();
            Map detailMap = new HashMap();
            Map footerMap = new HashMap();

            for(int i = 0; i < structureDetailList.size(); i++){
                StructureFileDetail sfd = (StructureFileDetail)structureDetailList.get(i);
                if("header".equals(sfd.getDetailType())){
                    headerMap.put(sfd.getFieldName(),sfd.getDataLength());
                }else if("detail".equals(sfd.getDetailType())){
                    detailMap.put(sfd.getFieldName(),sfd.getDataLength());
                }else if("footer".equals(sfd.getDetailType())){
                    footerMap.put(sfd.getFieldName(),sfd.getDataLength());
                }
            }

            StringBuffer stringBuffer = new StringBuffer();

            //loop header
            for(int row = 0; row < headerTable.getRowCount(); row++){
                for(int col = 0; col < headerTable.getColumnCount(); col++){
                    String cellValue = (String) headerTable.getValueAt(row,col);
                    String columnName = (String) headerTable.getColumnName(col);
                    int colLength = ((Integer)headerMap.get(columnName)).intValue();
                    stringBuffer.append(String.format("%-"+colLength+"s",cellValue.trim()));
                }
            }
            stringBuffer.append("\n");


            //loop detail
            for(int row = 0; row < detailTable.getRowCount(); row++){
                for(int col = 0; col < detailTable.getColumnCount(); col++){
                    String cellValue = (String) detailTable.getValueAt(row,col);
                    String columnName = (String) detailTable.getColumnName(col);
                    int colLength = ((Integer)detailMap.get(columnName)).intValue();
                    stringBuffer.append(String.format("%-"+colLength+"s",cellValue.trim()));
                }
                stringBuffer.append("\n");
            }

            //loop footer
            for(int row = 0; row < footerTable.getRowCount(); row++){
                for(int col = 0; col < footerTable.getColumnCount(); col++){
                    String cellValue = (String) footerTable.getValueAt(row,col);
                    String columnName = (String) footerTable.getColumnName(col);
                    int colLength = ((Integer)footerMap.get(columnName)).intValue();
                    stringBuffer.append(String.format("%-"+colLength+"s",cellValue.trim()));
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(stringBuffer.toString());
            writer.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
