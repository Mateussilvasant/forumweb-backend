package br.com.mateussilvasant.forumweb.api.configurations.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<T,I  extends Serializable> extends JpaRepository<T,I> {
    public void refresh(T t);
}