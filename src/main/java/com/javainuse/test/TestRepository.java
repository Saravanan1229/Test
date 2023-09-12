package com.javainuse.test;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<TestModel, Integer>{
	
	
	public TestModel findByName(String name);

	List<TestModel> findByRole(String role);
}
