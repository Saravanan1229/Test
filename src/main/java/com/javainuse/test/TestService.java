package com.javainuse.test;

import java.util.List;
import java.util.Optional;

public interface TestService {
	
	List<TestModel> getAllTestList();
	
	void saveTest(TestModel test);
	
	void deleteTest(TestModel deleteTest);
	
	void deleteTestById(int deleteId);

	Optional<TestModel> findById(int id);
	
	public TestModel findbyName(String name);

	List<TestModel> getByRole(String role);

}
