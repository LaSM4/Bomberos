package py.com.bomberos.test;

import py.com.bomberos.modelo.entidades.Bombero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bomberosPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Bombero b = new Bombero();
        b.setNombre("Liz");
        b.setApellido("Torales");

        em.persist(b);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}