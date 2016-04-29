package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void gravar(Produto produto){
		entityManager.persist(produto);
	}

	public List<Produto> listar() {
		return entityManager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto find(Integer produtoId) {
		return entityManager.createQuery("select distinct(p) from Produto p join fetch p.precos where p.id = :produtoId", Produto.class)
							.setParameter("produtoId", produtoId)
							.getSingleResult();
	}
	
	// metodo simulando um relatorio. Usado para teste.
	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco){
		return entityManager.createQuery("select sum(preco.valor) from Produto p inner join p.precos preco where preco.tipo = :tipoPreco", BigDecimal.class)
							.setParameter("tipoPreco", tipoPreco)
							.getSingleResult();
	}
}