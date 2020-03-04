package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.entity.Template;
import com.wirecard.filestructure.gui.service.TemplateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TemplateController extends AbstractController{
    private TemplateService templateService;

    public List getTemplateList(){
        List returnList = new ArrayList();

        List templateList = templateService.getTemplateList();
        for(int i = 0; i < templateList.size(); i++){
            Template template = (Template)templateList.get(i);

            Vector vector = new Vector();
            vector.add(template.getId());
            vector.add(template.getName());
            vector.add(template.getCreatedDate());

            returnList.add(vector);
        }

        return  returnList;
    }

    public void deleteTemplate(List ids){
        try {
            templateService.deleteByListId(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
