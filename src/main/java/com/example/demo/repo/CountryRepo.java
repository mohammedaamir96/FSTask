package com.example.demo.repo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Countries;

@Repository
public interface CountryRepo extends JpaRepository<Countries, String>{
	
	List<Countries> findByName(@Param("name") String name);
	
	List<Countries> findByState(@Param("state") String State);

	List<Countries> findByNameOrState(String name, String state);
	
	void saveAll(List<Countries> country) throws SQLException;
	
	//@Query("SELECT c from Countries c where c.name = :name BY LIMIT(5);")
	//List<Countries> findByNameLimit(String name);

}
