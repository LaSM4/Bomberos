package py.com.bomberos.modelo.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import py.com.bomberos.modelo.util.JPAUtil;

public abstract class GenericDAO<T> {

	protected Class<T> clase;

	public GenericDAO(Class<T> clase) {
		this.clase = clase;
	}

	protected EntityManager getEntityManager() {
		return JPAUtil.getEntityManager();
	}

	public void guardar(T entity) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
			if (id == null || (id instanceof Number && ((Number) id).longValue() == 0)) {
				em.persist(entity);
			} else {
				em.merge(entity);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public void eliminar(T entity) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			T managed = em.merge(entity);
			em.remove(managed);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public T recuperarPorId(Serializable id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(clase, id);
		} finally {
			em.close();
		}
	}

	public List<T> recuperarTodo() {
		EntityManager em = getEntityManager();
		try {
			String jpql = "from " + clase.getName() + " order by id desc";
			TypedQuery<T> query = em.createQuery(jpql, clase);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public abstract List<T> recuperarPorFiltro(String filtro);

}
