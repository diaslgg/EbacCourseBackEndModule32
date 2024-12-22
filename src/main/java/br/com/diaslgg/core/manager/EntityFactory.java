package br.com.diaslgg.core.manager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityFactory {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("ExemploJPA");
    }

    public EntityManager createFactory(){
        return entityManagerFactory.createEntityManager();
    }

    public void closeFactory() {
        entityManagerFactory.close();
    }

}
