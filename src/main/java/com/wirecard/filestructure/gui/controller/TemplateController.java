package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.entity.Template;
import com.wirecard.filestructure.gui.service.TemplateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TemplateController extends AbstractController{
    private TemplateService templateService;

    public List getTemplateList() throws Exception {
        List returnList = new ArrayList();
        try {
            List templateList = templateService.getTemplateList();
            for (int i = 0; i < templateList.size(); i++) {
                Template template = (Template) templateList.get(i);

                Vector vector = new Vector();
                vector.add(template.getId());
                vector.add(template.getName());
                vector.add(template.getCreatedDate());

                returnList.add(vector);
            }

            return returnList;
        }catch (Exception e){
            throw e;
        }
    }

    public void deleteTemplate(List ids) throws Exception {
        try {
            templateService.deleteByListId(ids);
        } catch (Exception e) {
            throw e;
        }
    }
}
