package com.wirecard.filestructure.gui;

import com.wirecard.filestructure.gui.entity.StructureFile;
import com.wirecard.filestructure.gui.utils.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class TestDB {
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return  sessionFactory;
    }
    public static void justForTest(String[] args){
        configureSessionFactory();

        Session session = null;
        Transaction trx = null;

        session = sessionFactory.openSession();
        trx = session.beginTransaction();

        StructureFile sf = new StructureFile("PAY file","txt",new Date());
        session.save(sf);

        session.flush();
        trx.commit();

        StructureFile sf2 = new StructureFile("PAY file","txt",new Date());
        session.save(sf2);

        session.flush();
        trx.commit();

        session.close();

//        Query q = session.createQuery("from StructureFile s order by s.createdDate");
//        List resultList = q.list();
//        for(int i = 0; i < resultList.size(); i++){
//            System.out.print(resultList.get(i));
//        }
//        session.flush();
//        trx.commit();

    }
}
