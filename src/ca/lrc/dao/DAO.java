package ca.lrc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import ca.lrc.beans.Report;

public class DAO {
	Configuration configuration = new Configuration().configure();
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).build();
	SessionFactory sessionFactory = configuration
			.buildSessionFactory(serviceRegistry);

	public void saveUptimeReport(Report uptimeReport) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(uptimeReport);

		session.getTransaction().commit();
		session.close();
	}

	public List<Report> getReports() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from UptimeReport");
		List<Report> uptimeReportList = (List<Report>) query.list();

		session.getTransaction().commit();
		session.close();

		return uptimeReportList;
	}

	public List<Report> getReportById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("UptimeReport.byId");
		query.setInteger("id", id);

		List<Report> uptimeReportList = (List<Report>) query.list();

		session.getTransaction().commit();
		session.close();

		return uptimeReportList;
	}
}
