package com.javainuse.test.veri;


import java.io.FileOutputStream;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@RestController
public class EmpController {

	@Autowired
	EmpService empService;
	
	@Autowired
	EmpRepository empRepo;
	
	
//	@RequestMapping("/")
//	public String process()
//	{
//		return "hello";
//		
//	}
	
	@RequestMapping(value="/info", method = RequestMethod.GET)
	public List<EmpModel> getList()
	{
		
		return empService.getAllEmpList();	
	}   
	
	@RequestMapping(value="/saveEmp", method = RequestMethod.POST)
	public void saveEmp(@RequestBody EmpModel emp)
	{
		empService.saveEmp(emp);
	}
	
	
//	@RequestMapping(value="/Empcheck", method = RequestMethod.POST)
//	public String Empcheck(@RequestParam(value="email") String email,
//			@RequestParam(value="phoneno") String phoneno)
//	{
//		String msg="";
//		EmpModel userObj = empService.findbyemail(email);
//		if(!Objects.isNull(userObj))
//		{
//			if(phoneno.equals(userObj.getphoneno()))
//			{
//				msg = "Login Success";
//			}else
//			{
//				msg = "Password Error..!";
//			}
//		}else {
//			msg = "Username is not found..!";
//		}
//		return msg;
//		
//	}
	
	
	
	@PostMapping("/file")
	public String uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("compName") String compName, @RequestParam("phoneNo")String phone)  throws Exception{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getName());
		System.out.println(file.getContentType());
		if(file.getContentType().contains("jpg") || file.getContentType().contains("jpeg"))
		{
			
			System.out.println(file.getSize());
			
			EmpModel e = new EmpModel();
			e.setComname(compName);
			e.setPhoneno(phone);
			
			 final String imagePath = "D:\\file upload\\"; 
		        FileOutputStream output = new FileOutputStream(imagePath+file.getOriginalFilename());
		        output.write(file.getBytes());
		        
		        e.setImageData(file.getBytes());
		        
		        empService.saveEmp(e);
		        return imagePath+file.getOriginalFilename()
		        +"  "+ "Save Successfuly...!";
		}else
		{
			return "file type error;";
		}
	}

	
	@GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Optional<EmpModel> imageOptional = empRepo.findById(id);

        if (imageOptional.isPresent()) {
            EmpModel imageEntity = imageOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(imageEntity.getImageData().length);
            return new ResponseEntity<>(imageEntity.getImageData(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	}	

