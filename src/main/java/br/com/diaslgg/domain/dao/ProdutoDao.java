package br.com.diaslgg.domain.dao;

import br.com.diaslgg.core.manager.EntityFactory;
import br.com.diaslgg.domain.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

public class ProdutoDao implements IProdutoDao{

    private final EntityFactory entityFactory;

    public ProdutoDao () {
        this.entityFactory = new EntityFactory();
    }

    @Override
    public Produto save(Produto produto) {

        EntityManager entityManager = null;

        try {
            entityManager = this.entityFactory.createFactory();
            entityManager.getTransaction().begin();
            entityManager.persist(produto);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if(entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return produto;
    }

    @Override
    public Produto find(Long id) {
        EntityManager entityManager = null;

        Produto produto = null;

        try {
            entityManager = this.entityFactory.createFactory();
            entityManager.getTransaction().begin();
            produto = entityManager.find(Produto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return produto;
    }

    @Override
    public List<Produto> findAll() {
        EntityManager entityManager = null;

        List<Produto> produtos = null;

        try {
            entityManager = this.entityFactory.createFactory();
            entityManager.getTransaction().begin();
            produtos = entityManager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return produtos;
    }

    @Override
    @Transactional
    public Produto exclude(Produto produto) {

        EntityManager entityManager = null;

        Produto produtoToExclude = null;

        try {
            entityManager = this.entityFactory.createFactory();
            entityManager.getTransaction().begin();
            produtoToExclude = entityManager.merge(produto);
            entityManager.remove(produtoToExclude);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
                throw e;
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return produtoToExclude;
    }
}
