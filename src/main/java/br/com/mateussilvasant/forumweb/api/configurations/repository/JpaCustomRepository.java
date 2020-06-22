package br.com.mateussilvasant.forumweb.api.configurations.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class JpaCustomRepository<T, I  extends Serializable> extends SimpleJpaRepository<T, I> implements IRepository<T, I> {

    private final EntityManager entityManager;

    public JpaCustomRepository(JpaEntityInformation<T,?> information, EntityManager entityManager){
        super(information,entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void refresh(T t) {
        entityManager.refresh(t);
    }


}