package py.com.bomberos.modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import py.com.bomberos.modelo.entidades.TipoIncidente;

public class TipoIncidenteDAO extends GenericDAO<TipoIncidente> {

	public TipoIncidenteDAO() {
		super(TipoIncidente.class);
	}

	@Override
	public List<TipoIncidente> recuperarPorFiltro(String filtro) {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery(
					"SELECT t FROM TipoIncidente t WHERE t.descripcion LIKE :filtro",
					TipoIncidente.class)
					.setParameter("filtro", "%" + filtro + "%")
					.getResultList();
		} finally {
			em.close();
		}
	}

}
