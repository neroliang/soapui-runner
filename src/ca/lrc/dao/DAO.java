package ca.lrc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import ca.lrc.beans.Result;

public class DAO {
	Configuration configuration = new Configuration().configure();
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).build();
	SessionFactory sessionFactory = configuration
			.buildSessionFactory(serviceRegistry);

	public void saveResult(Result result) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(result);

		session.getTransaction().commit();
		session.close();
	}

	public List<Result> getResults() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Result");
		List<Result> resultList = (List<Result>) query.list();

		session.getTransaction().commit();
		session.close();

		return resultList;
	}

	public List<Result> getResultById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("Result.byId");
		query.setInteger("id", id);

		List<Result> resultList = (List<Result>) query.list();

		session.getTransaction().commit();
		session.close();

		return resultList;
	}
}
