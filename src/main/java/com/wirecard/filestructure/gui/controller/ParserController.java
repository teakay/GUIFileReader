package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.StructureFileDetail;
import com.wirecard.filestructure.gui.entity.Template;
import com.wirecard.filestructure.gui.entity.TemplateDetail;
import com.wirecard.filestructure.gui.service.StructureFileService;
import com.wirecard.filestructure.gui.service.TemplateService;
import com.wirecard.filestructure.gui.view.AbstractViewPanel;
import com.wirecard.filestructure.gui.view.MainFrame;
import com.wirecard.filestructure.gui.view.StructureFileViewPanel;
import com.wirecard.filestructure.gui.view.TemplateView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParserController extends DefaultTableController {
    private TemplateService templateService;


    public Map parseFile(String filePathAndName, Map columnMap) throws IOException {
        Map returnMap = new HashMap();

        List headerLengthList = (List)columnMap.get("headerLength");
        List detailLengthList = (List)columnMap.get("detailLenght");
        List footerLengthList = (List)columnMap.get("footerLength");

        Vector header = new Vector();
        Vector detail = new Vector();
        Vector footer = new Vector();

        List detailList = new ArrayList();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePathAndName));
            String line = "";

            while ((line = bufferedReader.readLine())!=null){
                String recordType = line.substring(0,1);
                Integer firstindex = 0;
                Integer lastindex = 0;
                if("H".equals(recordType)){
                    int size = headerLengthList.size();
                    for(int n = 0; n < size; n++){
                        Integer length = (Integer)headerLengthList.get(n);
                        lastindex = firstindex + length;
                        String substring = line.substring(firstindex,lastindex);
                        firstindex = lastindex;
                        header.add(substring);
                    }
                }else if(!"H".equals(recordType) && !"F".equals(recordType)){
                    int size = detailLengthList.size();
                    for(int n = 0; n < size; n++){
                        Integer length = (Integer)detailLengthList.get(n);
                        lastindex = firstindex + length;
                        String substring = line.substring(firstindex,lastindex);
                        firstindex = lastindex;
                        detail.add(substring);
                    }
                    detailList.add(detail);
                    detail = new Vector();
                }else if("F".equals(recordType)){
                    int size = footerLengthList.size();
                    for(int n = 0; n < size; n++){
                        Integer length = (Integer)footerLengthList.get(n);
                        lastindex = firstindex + length;
                        String substring = line.substring(firstindex,lastindex);
                        firstindex = lastindex;
                        footer.add(substring);
                    }
                }
            }

            returnMap.put("header",header);
            returnMap.put("detail",detailList);
            returnMap.put("footer",footer);
        } catch (FileNotFoundException e) {
            throw  e;
        } catch (IOException e) {
            throw e;
        }
        return  returnMap;
    }

    public void saveAsTemplate(String structureName, JTable headerTable, JTable detailTable, JTable footerTable, String templateName) throws Exception {
        System.out.println(templateName);

        List templateDetailList = new ArrayList();

        int headerRow = headerTable.getRowCount();
        int headerColumn = headerTable.getColumnCount();

        int detailRow = detailTable.getRowCount();
        int detailCol = detailTable.getColumnCount();

        int footerRow = footerTable.getRowCount();
        int footerCol = footerTable.getColumnCount();

        StructureFile structureFile = null;
        try {
            structureFile = structureFileService.getStructureFileByName(structureName);

            Template template = new Template(templateName, structureFile, new Date());

            for(int row = 0; row < headerRow; row++){
                for(int col = 0; col < headerColumn; col++){
                    TemplateDetail templateDetail = new TemplateDetail(template,"header",
                            new Integer(row + 1),col + 1, headerTable.getColumnName(col),(String)headerTable.getValueAt(row,col));

                    templateDetailList.add(templateDetail);
                }
            }

            for(int row = 0; row < detailRow; row++){
                for(int col = 0; col < detailCol; col++){
                    TemplateDetail templateDetail = new TemplateDetail(template,"detail",
                            new Integer(row + 1), col + 1, detailTable.getColumnName(col),(String)detailTable.getValueAt(row,col));

                    templateDetailList.add(templateDetail);
                }
            }

            for(int row = 0; row < footerRow; row++){
                for(int col = 0; col < footerCol; col++){
                    TemplateDetail templateDetail = new TemplateDetail(template,"footer",
                            new Integer(row + 1),col + 1, footerTable.getColumnName(col),(String)footerTable.getValueAt(row,col));

                    templateDetailList.add(templateDetail);
                }
            }

            templateService.saveTemplate(template, templateDetailList);
        } catch (Exception e) {
           throw e;
        }
    }

    public void goToTemplateView(AbstractViewPanel view){
        removeView(view);
        TemplateView templateView = new TemplateView(new TemplateController());
        addView(templateView);

        MainFrame mainFrame = (MainFrame) SwingUtilities.getRoot(view);
        mainFrame.setContentScrollPane(templateView);
    }
}
