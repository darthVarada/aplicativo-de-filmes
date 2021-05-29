package projeto_semestral.victor_projeto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class FilmeDao {

	protected EntityManagerFactory factory;
	protected EntityManager entityManager;
	
	public FilmeDao() {
		factory = Persistence.createEntityManagerFactory("projeto-semestral");
	}

	public Filme salvar(Filme entity) {
		entityManager = factory.createEntityManager();
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		entityManager.persist(entity);
		entityManager.flush();
		t.commit();
		entityManager.close();
		return entity;
	}

	public Filme atualizar(Filme entity) {
		entityManager = factory.createEntityManager();
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		entityManager.merge(entity);
		entityManager.flush();
		t.commit();
		entityManager.close();
		return entity;
	}

	public void remover(long id) {
		entityManager = factory.createEntityManager();
		Filme entity = entityManager.find(Filme.class, id);
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Filme mergedEntity = entityManager.merge(entity);
		entityManager.remove(mergedEntity);
		entityManager.flush();
		tx.commit();
		entityManager.close();
	}

	public List<Filme> getList() {
		entityManager = factory.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Filme> query = builder.createQuery(Filme.class);
		query.from(Filme.class);
		List<Filme> filme = entityManager.createQuery(query).getResultList();
		entityManager.close();
		return filme;
	}
	
}
