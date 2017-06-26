package br.agenda3.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		Configuration c = new Configuration();

		/* todos os javabeans aqui */
		c.addAnnotatedClass(br.agenda3.model.Usuario.class);

		c.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.Oracle10gDialect");
		c.setProperty(AvailableSettings.DRIVER, "oracle.jdbc.driver.OracleDriver");
		c.setProperty(AvailableSettings.USER, "agendaspringhibernate");
		c.setProperty(AvailableSettings.PASS, "oracle");
		c.setProperty(AvailableSettings.URL, "jdbc:oracle:thin:@localhost:1521:xe");

		c.setProperty("hibernate.hbm2ddl.auto", "update");

		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();

		sessionFactory = c.buildSessionFactory(registry);

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
