package study.rnTest.service.compute;

import study.rnTest.entity.compute.ComputeResultLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ComputeResultsLogService {
    private SessionFactory sessionFactory;
    private Session currSession;
    private static ComputeResultsLogService instance = null;

    private ComputeResultsLogService() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(ComputeResultLog.class)
                .buildSessionFactory();
    }

    public static ComputeResultsLogService getInstance() {
        if (instance == null) {
            instance = new ComputeResultsLogService();
        }

        return instance;
    }

    public void openSession() {
        currSession = sessionFactory.openSession();
    }

    public void closeSession() {
        currSession.close();
    }

    public void saveComputeResultLog(ComputeResultLog resultLog) {
        Transaction transaction = currSession.beginTransaction();
        currSession.save(resultLog);
        transaction.commit();
    }

    public List<ComputeResultLog> findAll() {
        return currSession.createQuery("from ComputeResultLog").list();
    }
}
