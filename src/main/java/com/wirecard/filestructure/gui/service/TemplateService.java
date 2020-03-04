package com.wirecard.filestructure.gui.service;

import com.wirecard.filestructure.gui.entity.Template;
import com.wirecard.filestructure.gui.entity.TemplateDetail;
import com.wirecard.filestructure.gui.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class TemplateService {
    private static String QUERY_GET_ALL_TEMPLATE_LIST = "from Template t ";
    private static String QUERY_GET_ALL_TEMPLATE_LIST_BY_STRUCTURE = "from Template t where t.structureFile.id = :structureId";
    private static String QUERY_GET_TEMPLATE_BY_NAME = "from Template t where t.name= :name";
    private static String QUERY_GET_TEMPLATE_DETAIL_BY_TEMPLATEID = "from TemplateDetail td where td.template.id = :templateId order by td.dataType, td.rowNo";
    private static String QUERY_DELETE_TEMPLATE_DETAIL_BY_PARENTID = "delete from TemplateDetail td where td.template.id = :templateId";
    private static String QUERY_DELETE_TEMPLATE_BY_ID = "delete from Template t where t.id = :id";

    public static void saveTemplate(Template template, List templateDetail) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            session.save(template);

            for (int i = 0; i < templateDetail.size(); i++) {
                session.save((TemplateDetail) templateDetail.get(i));
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
    }

    public static List getTemplateList() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_ALL_TEMPLATE_LIST);
            List resultList = q.list();

            session.getTransaction().commit();
            session.close();
            return  resultList;

        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw  e;
        }
    }

    public static List getTemplateListByStructure(String structureId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_ALL_TEMPLATE_LIST_BY_STRUCTURE);
            q.setString("structureId", structureId);
            List resultList = q.list();

            session.getTransaction().commit();
            session.close();

            return resultList;
        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
    }

    public static List getTemplateDetailListByTemplateId(String templateId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery(QUERY_GET_TEMPLATE_DETAIL_BY_TEMPLATEID);
        q.setString("templateId",templateId);
        List resultList = q.list();

        session.getTransaction().commit();
        session.close();

        return  resultList;
    }

    public static Template getTemplateByName(String templateName) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_TEMPLATE_BY_NAME);
            q.setString("name", templateName);
            Template template = (Template) q.uniqueResult();

            session.getTransaction().commit();
            session.close();

            return template;
        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
    }

    public static void deleteByListId(List ids) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            for(int i = 0; i < ids.size(); i++) {
                String id = (String)ids.get(i);
                session.beginTransaction();

                Query q = session.createQuery(QUERY_DELETE_TEMPLATE_DETAIL_BY_PARENTID);
                q.setString("templateId", id);
                q.executeUpdate();

                Query q2 = session.createQuery(QUERY_DELETE_TEMPLATE_BY_ID);
                q2.setString("id", id);
                q2.executeUpdate();

                session.getTransaction().commit();
                session.close();
            }
        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
    }
}
