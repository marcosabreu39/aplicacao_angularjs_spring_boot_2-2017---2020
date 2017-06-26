package br.agenda3.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.agenda3.config.HibernateUtil;
import br.agenda3.model.Usuario;

@Repository
public class UsuarioDao implements GenericDao<Usuario> {

	private Session session;

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Usuario find(Serializable id) {
		session = sessionFactory.openSession();
		return (Usuario) session.load(Usuario.class, id);
	}

	@Override
	public void persist(Usuario usuario) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(usuario);
		session.getTransaction().commit();
	}

	@Override
	public void merge(Usuario usuario) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.merge(usuario);
		session.getTransaction().commit();

	}

	@Override
	public void remove(Usuario usuario) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(usuario);
		session.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean searchByAttribute(String attributeName, String attributeValue) {
		boolean finded = true;
		
		session = sessionFactory.openSession();
		Query query = session.createQuery("from Usuario where " + attributeName + " = :" + attributeName);
		query.setParameter(attributeName, attributeValue);

		List<Usuario> result = query.list();

		finded = result.isEmpty() ? false : finded;

		session.close();

		return finded;

	}

	public Usuario findCredentials(Usuario usuario) {

		session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT u FROM Usuario u where u.login =: login and u.senha =:senha");
		session.close();

		return usuario;

	}

}
