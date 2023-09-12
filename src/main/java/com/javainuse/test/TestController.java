package com.javainuse.test;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;





@RestController
//@RequestMapping("/emp")
//@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
	
	@Autowired
	TestService testService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@RequestMapping("/")
	public String process()
	{
		return "hello";
		
	}
	
	@RequestMapping(value="/getTest", method = RequestMethod.GET)
	public List<TestModel> getList()
	{
		
		return testService.getAllTestList();	
	}   
	

	
	@RequestMapping(value="/saveTest", method = RequestMethod.POST)
	public String saveTest(@RequestBody TestModel test)
	{

		testService.saveTest(test);
		return "Your data save suceessfuly";
	}
	
	@RequestMapping(value="/checkUser", method = RequestMethod.POST)
	public String checkUser(@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,HttpServletResponse response)
	{
		String msg="";
		TestModel userObj = testService.findbyName(username);
		
		if(!Objects.isNull(userObj))
		{
			//password = passwordEncoder.encode(password);
			if(password.equals(userObj.getPassword()))
			{
				msg = "Login Success";
			}else
			{
				msg = "Password Error..!";
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}else {
			msg = "Username is not found..!";
		}
		return msg;
		
	}
	

	
	
	@RequestMapping(value="/updateTest1",method =RequestMethod.PUT)
	public String updateTest(@PathVariable ("id") String id, @RequestBody
	TestModel product) {
		return id;
	
	}
	

	@RequestMapping(value="/updateTest", method = RequestMethod.PUT)
	public String testUpdate(@RequestBody TestModel testUpdate)
	{
		
		testUpdate.setPassword(passwordEncoder.encode(testUpdate.getPassword()));
		String encoded = new BCryptPasswordEncoder().encode(testUpdate.getPassword());
		//String decode = new BCryptPasswordEncoder()
		testService.saveTest(testUpdate);
		Optional<TestModel> opt = testService.findById(testUpdate.getId());
		if(!opt.isEmpty())
		{
			testService.saveTest(testUpdate);
			return "update successfully..!";
		}else
		{
			return "data not found & update failure..!";
		}
		
		
	}
	
	@RequestMapping(value="/deleteTest", method = RequestMethod.DELETE)
	public String deleteTest(@RequestBody TestModel deleteTest)
	{
		Optional<TestModel> opt = testService.findById(deleteTest.getId());
		if(!opt.isEmpty())
		{
			testService.deleteTest(deleteTest);
			return "deleted successfully..!";
		}else
		{
			return "data not found & deleted failure..!";
		}
		
	}
	
	@RequestMapping(value="/deleteTestById/{deleteId}", method = RequestMethod.DELETE)
	public String deleteTest(@PathVariable("deleteId") int deleteId)
	{
		Optional<TestModel> opt = testService.findById(deleteId);
		if(!opt.isEmpty())
		{
			testService.deleteTestById(deleteId);
			return "deleted successfully by ID..!";
		}else
		{
			return "data not found & deleted by id failure..!";
		}
		
	}
	
	
	
	@GetMapping("/role/{role}")
	public List<TestModel>getByRole(@PathVariable String role){
			
		return testService.getByRole(role);
	}
	
	
	
	 }

	


