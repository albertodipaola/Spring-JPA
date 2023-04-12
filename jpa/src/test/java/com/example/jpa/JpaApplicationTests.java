package com.example.jpa;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.jpa.model.Prodotto;
import com.example.jpa.model.Variante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Test
	void persistObjectProdotto(){
		TypedQuery<Prodotto> query = entityManager.createQuery("SELECT p FROM Prodotto p", Prodotto.class);

		Prodotto p = new Prodotto();
		p.setNome("maglia");
		p.setDescrizione("qualcosa");
		p.setPrezzo(12.34f);
		p.setPrezzoNetto(12.34f);

		List<Prodotto> allBeforeExist = query.getResultList();
		Assertions.assertThat(allBeforeExist).hasSize(0);
		entityManager.persist(p);

		List<Prodotto> allAfterExist = query.getResultList();
		Assertions.assertThat(allAfterExist).hasSize(1);

	}

	@Test
	void checkPrezzoNetto(){
		Prodotto p = new Prodotto();
		p.setNome("maglia");
		p.setDescrizione("qualcosa");
		p.setPrezzo(12.34f);
		p.setPrezzoNetto(20f);

		entityManager.persist(p);
		TypedQuery<Prodotto> query = entityManager.createQuery("SELECT p FROM Prodotto p WHERE p.id=1", Prodotto.class);
		Prodotto res = query.getSingleResult();
		//Prodotto res = query.getResultList().get(0);
		Assertions.assertThat(res).extracting("prezzoNetto").isEqualTo(20f);
	}

	@Test
	void ManyToOne(){
		TypedQuery<Prodotto> queryP = entityManager.createQuery("SELECT p FROM Prodotto p WHERE p.id=1", Prodotto.class);
		Prodotto p = queryP.getSingleResult();

		TypedQuery<Variante> queryV = entityManager.createQuery("SELECT v FROM Variante v WHERE v.id=1", Variante.class);
		Variante v = queryV.getSingleResult();

		Assertions.assertThat(v).extracting("prodotto").isEqualTo(p);

	}
}
