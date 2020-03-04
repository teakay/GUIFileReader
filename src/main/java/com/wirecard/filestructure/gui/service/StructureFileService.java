package com.wirecard.filestructure.gui.service;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.entity.StructureFileDetail;
import com.wirecard.filestructure.gui.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

public class StructureFileService {

    private static String QUERY_GET_ALL_STRUCTURE_FILE_LIST = "from StructureFile s order by s.createdDate";
    private static String QUERY_DELETE_ROW_BY_ID = "delete from StructureFile s where s.id = :id";
    private static String QUERY_DELETE_DETAIL_ROW_BY_ID = "delete from StructureFileDetail sd where sd.structureFile.id = :headerId";
    private static String QUERY_GET_DETAIL_BY_PARENT_ID = "from StructureFileDetail sd where sd.structureFile.id = :id order by sd.detailType, sd.sequenceNo";
    private static String QUERY_GET_STRUCTURE_FILE_BY_ID = "from StructureFile s where s.id = :id";
    private static String QUERY_GET_STRUCTURE_FILE_BY_NAME = "from StructureFile s where s.structureName = :structureName";

    public static List getStructureFileList() throws Exception {
        List<Vector> returnList = new ArrayList<Vector>();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_ALL_STRUCTURE_FILE_LIST);
            List resultList = q.list();

            for (int i = 0; i < resultList.size(); i++) {
                StructureFile structureFile = (StructureFile) resultList.get(i);
                Vector dataVector = new Vector();
                dataVector.add(0,structureFile.getId());
                dataVector.add(1,structureFile.getStructureName());
                dataVector.add(2, structureFile.getExtension());
                dataVector.add(3, structureFile.getProductName());
                dataVector.add(4, structureFile.getProjectName());
                dataVector.add(5, structureFile.getCreatedDate());

                returnList.add(dataVector);
            }
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        return returnList;

    }

    public static List<StructureFile> getStructureFileListModel() throws Exception {
        List<StructureFile> resultList = new ArrayList<StructureFile>();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_ALL_STRUCTURE_FILE_LIST);
            resultList = q.list();


            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        return resultList;

    }

    public static List getStructureFileAndDetailByStructureName(String structureName) throws Exception {
        List returnList = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_STRUCTURE_FILE_BY_NAME);
            q.setString("structureName", structureName);
            StructureFile structureFile = (StructureFile)q.uniqueResult();

            Query q2 = session.createQuery(QUERY_GET_DETAIL_BY_PARENT_ID);
            q2.setString("id", structureFile.getId());
            returnList = q2.list();

            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        return returnList;
    }

    public static void deleteById(List<Vector> data) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            for(int i = 0; i < data.size(); i++) {
                String id = (String)data.get(i).get(0);
                session.beginTransaction();

                Query q = session.createQuery(QUERY_DELETE_ROW_BY_ID);
                q.setString("id", id);
                q.executeUpdate();

                Query q2 = session.createQuery(QUERY_DELETE_DETAIL_ROW_BY_ID);
                q2.setString("headerId", id);
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

    public static void saveData(Map dataMap) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String structureName = (String) dataMap.get("structureName");
            String extension = (String) dataMap.get("extension");
            String productName = (String) dataMap.get("productName");
            String projectName = (String) dataMap.get("projectName");

            StructureFile structureFile = new StructureFile(structureName, extension,  productName, projectName, new Date());

            List<Vector> headerData = (List<Vector>) dataMap.get("headerData");
            List<Vector> detailData = (List<Vector>) dataMap.get("detailData");
            List<Vector> footerData = (List<Vector>) dataMap.get("footerData");

            session.beginTransaction();

            session.save(structureFile);
            session.flush();
            session.clear();

            for (int i = 0; i < headerData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail(structureFile, "header",
                        (Integer) headerData.get(i).get(1), (String) headerData.get(i).get(2),
                        (String) headerData.get(i).get(3), (String) headerData.get(i).get(4), (Integer) headerData.get(i).get(5),
                        (String) headerData.get(i).get(6),(String) headerData.get(i).get(7));
                session.save(sfd);
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            for (int i = 0; i < detailData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail(structureFile, "detail",
                        (Integer) detailData.get(i).get(1), (String) detailData.get(i).get(2),
                        (String) detailData.get(i).get(3), (String) detailData.get(i).get(4), (Integer) detailData.get(i).get(5),
                        (String) detailData.get(i).get(6),(String) detailData.get(i).get(7));
                session.save(sfd);
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            for (int i = 0; i < footerData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail(structureFile, "footer",
                        (Integer) footerData.get(i).get(1), (String) footerData.get(i).get(2),
                        (String) footerData.get(i).get(3), (String) footerData.get(i).get(4), (Integer) footerData.get(i).get(5),
                        (String) footerData.get(i).get(6),(String) footerData.get(i).get(7));
                session.save(sfd);
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

    public static List getDetail(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery(QUERY_GET_DETAIL_BY_PARENT_ID);
        q.setString("id",id);

        List result =  q.list();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public static void updateData(Map dataMap) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String structureName = (String) dataMap.get("structureName");
            String extension = (String) dataMap.get("extension");

            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_STRUCTURE_FILE_BY_ID);
            q.setString("id", (String)dataMap.get("parentId"));

            StructureFile structureFile = (StructureFile) q.uniqueResult();
            structureFile.setStructureName((String)dataMap.get("fileName"));
            structureFile.setProductName((String)dataMap.get("productName"));
            structureFile.setProjectName((String)dataMap.get("projectName"));

            session.saveOrUpdate(structureFile);

            List<Vector> headerData = (List<Vector>) dataMap.get("headerData");
            List<Vector> detailData = (List<Vector>) dataMap.get("detailData");
            List<Vector> footerData = (List<Vector>) dataMap.get("footerData");

            for (int i = 0; i < headerData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail((String)headerData.get(i).get(0),structureFile,
                        "header", (Integer) headerData.get(i).get(1), (String) headerData.get(i).get(2),
                        (String) headerData.get(i).get(3), (String) headerData.get(i).get(4), (Integer) headerData.get(i).get(5),
                        (String) headerData.get(i).get(6), (String) headerData.get(i).get(7));
                session.saveOrUpdate(sfd);
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            for (int i = 0; i < detailData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail((String)detailData.get(i).get(0),structureFile, "detail",
                        (Integer) detailData.get(i).get(1), (String) detailData.get(i).get(2),
                        (String) detailData.get(i).get(3), (String) detailData.get(i).get(4), (Integer) detailData.get(i).get(5),
                        (String) detailData.get(i).get(6),(String) detailData.get(i).get(7));
                session.saveOrUpdate(sfd);
                if (i % 10 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            for (int i = 0; i < footerData.size(); i++) {
                StructureFileDetail sfd = new StructureFileDetail((String)footerData.get(i).get(0),structureFile, "footer",
                        (Integer) footerData.get(i).get(1), (String) footerData.get(i).get(2),
                        (String) footerData.get(i).get(3), (String) footerData.get(i).get(4), (Integer) footerData.get(i).get(5),
                        (String) footerData.get(i).get(6), (String) footerData.get(i).get(7));
                session.saveOrUpdate(sfd);
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
    public static StructureFile getStructureFileByName(String structureName) throws Exception {
        StructureFile structureFile = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            session.beginTransaction();

            Query q = session.createQuery(QUERY_GET_STRUCTURE_FILE_BY_NAME);
            q.setString("structureName", structureName);
            structureFile = (StructureFile)q.uniqueResult();

            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
        return structureFile;
    }
}
