package com.project.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Entity.ContactFormEntity;

@Repository
public interface ContactFormCrud extends JpaRepository<ContactFormEntity, Integer> {

	@Override
	public <S extends ContactFormEntity> S save(S entity);
	
	@Override
	public List<ContactFormEntity> findAll();

	@Override
	public void deleteById(Integer id);
}
