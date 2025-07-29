package com.project.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Entity.addServiceEntity;

@Repository
public interface addServiceCrud extends JpaRepository<addServiceEntity,Integer> {

	@Override
     public <S extends addServiceEntity> S save(S entity);
     
     @Override
    public List<addServiceEntity> findAll();
 
    @Override
    public void deleteById(Integer id);
}
