package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.StructureFileDetail;
import com.wirecard.filestructure.gui.entity.Template;
import com.wirecard.filestructure.gui.entity.TemplateDetail;
import com.wirecard.filestructure.gui.service.StructureFileService;
import com.wirecard.filestructure.gui.service.TemplateService;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class FileCreatorController extends DefaultTableController {
    private TemplateService templateService;

    public List getTemplateListByStructure(String structureName) throws Exception {
        List resultList = new ArrayList();
        try {
            StructureFile structureFile = structureFileService.getStructureFileByName(structureName);
            resultList = templateService.getTemplateListByStructure(structureFile.getId());
        } catch (Exception e) {
            throw e;
        }
        return resultList;
    }
    public Map getTemplateDetailByName(String templateName) throws Exception {
        Map resultMap = new HashMap();

        List<Vector> headerList = new ArrayList<Vector>();
        List<Vector> detailList = new ArrayList<Vector>();
        List<Vector> footerList = new ArrayList<Vector>();
        try {
            Template template = templateService.getTemplateByName(templateName);
            List templateDetailList = templateService.getTemplateDetailListByTemplateId(template.getId());


            for (int i = 0; i < templateDetailList.size(); i++) {
                TemplateDetail templateDetail = (TemplateDetail) templateDetailList.get(i);
                if ("header".equals(templateDetail.getDataType())) {
                    if (headerList.isEmpty() ||
                            (headerList.size() < templateDetail.getRowNo())) {
                        headerList.add(templateDetail.getRowNo() - 1, new Vector());
                    }
                    headerList.get(templateDetail.getRowNo() - 1).add(templateDetail.getDataSequenceNo() - 1, templateDetail);
                } else if ("detail".equals(templateDetail.getDataType())) {
                    if (detailList.isEmpty() ||
                            (detailList.size() < templateDetail.getRowNo())) {
                        detailList.add(templateDetail.getRowNo() - 1, new Vector());
                    }
                    detailList.get(templateDetail.getRowNo() - 1).add(templateDetail.getDataSequenceNo() - 1, templateDetail);
                } else if ("footer".equals(templateDetail.getDataType())) {
                    if (footerList.isEmpty() ||
                            (footerList.size() < templateDetail.getRowNo())) {
                        footerList.add(templateDetail.getRowNo() - 1, new Vector());
                    }
                    footerList.get(templateDetail.getRowNo() - 1).add(templateDetail.getDataSequenceNo() - 1, templateDetail);
                }
            }

            resultMap.put("header", headerList);
            resultMap.put("detail", detailList);
            resultMap.put("footer", footerList);
            return resultMap;
        }catch (Exception e){
            throw e;
        }
    }
}