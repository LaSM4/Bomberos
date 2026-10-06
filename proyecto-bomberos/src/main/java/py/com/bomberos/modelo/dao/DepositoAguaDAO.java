package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.DepositoAgua;

public class DepositoAguaDAO extends GenericDAO<DepositoAgua> {
	public DepositoAguaDAO() {
		super(DepositoAgua.class);
	}

	@Override
	public List<DepositoAgua> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT d FROM DepositoAgua d " +
							"WHERE d.direccion LIKE :filtro OR d.estado LIKE :filtro",
					DepositoAgua.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}
}