package com.javainuse.test.veri;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
@Transactional
public class EmpServiceImpl implements EmpService{
     @Autowired
	EmpRepository empRepo;

	@Override
	public List<EmpModel> getAllEmpList() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

	@Override
	public void saveEmp(EmpModel emp) {
		// TODO Auto-generated method stub
		
		empRepo.save(emp);
		
	}



	@Override
	public void save(EmpModel emp) {
		// TODO Auto-generated method stub
		empRepo.save(emp);
		
	}



	
}
