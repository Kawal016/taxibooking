package com.project.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Entity.BookingFormEntity;

@Repository
public interface BookingFormCrud extends JpaRepository<BookingFormEntity, Integer> {

	@Override
	public <S extends BookingFormEntity> S save(S entity);
	
	@Override
	public List<BookingFormEntity> findAll();
	
	@Override
	public void deleteById(Integer id);

}
