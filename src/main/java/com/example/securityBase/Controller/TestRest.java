package com.example.securityBase.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityBase.SecurityUserRepo.SecUser;
import com.example.securityBase.SecurityUserRepo.SecUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@RestController
public class TestRest {

	@Autowired
	SecUserRepository secUserRepository;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@PostConstruct
	public void inits() throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		SecUser usr = new SecUser();
		usr.setUsername("Greedy");
		usr.setPassword("12341");
		System.out.println("=============================");
		System.out.println(mapper.writeValueAsString(usr));
		secUserRepository.save(usr);
	}
	
	@GetMapping("/base")
	public String welcome()
	{
		return "<h1> Hello welcome to spring boot securit example</h1>";
	}
	
	@GetMapping("/base1")
	public String welcome1()
	{
		return "welcome without htmla";
	}
	
	@GetMapping("/psa")
	public Principal  retrivePrincipal(Principal principal) throws JsonProcessingException
	{
		
		System.out.println(mapper.writeValueAsString(principal));
		return principal;
	}
	

	@PutMapping("/stsd")
	public String addandgetSomSecUser( @RequestBody SecUser data) throws Exception
	{
		System.out.println("------------------------");
		System.out.println("INside sTso  --[[");
		System.out.println("valu dd: "+mapper.writeValueAsString(data));
		SecUser usr = new SecUser();
		usr.setUsername(data.getUsername());
		usr.setPassword(data.getPassword());
		usr.setSecretQuestion(data.getSecretQuestion());
		System.out.println(mapper.writeValueAsString(usr));
		secUserRepository.save(usr);
		
		return secUserRepository.findAll().toString();
	}
	
	@GetMapping("/getmyusrs")
	public String getusers()
	{
		return secUserRepository.findAll().toString();
	}
	
	@DeleteMapping("/base2")
	public String deleteusers(@RequestBody String dataId)
	{
		System.out.println("Inside delete request Base2");
		System.out.println("DataId: "+dataId);
		return secUserRepository.findAll().toString();
	}

	@PatchMapping("/patches")
	public String getsomepatch(@RequestBody String body)
	{
		System.out.println("Inside patches method get some patches...");
		return "This is our patch here it is : "+body;
	}
	
	@PostMapping("/archos")
	public String addpostSomedatas(@RequestBody String data)
	{
		System.out.println("Inside the archos method");
		System.out.println("your data is : "+data);
		return "that data received thanks yo"+data;
	}
}
