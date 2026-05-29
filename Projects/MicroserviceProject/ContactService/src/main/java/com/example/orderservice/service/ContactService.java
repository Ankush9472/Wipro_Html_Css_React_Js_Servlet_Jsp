package com.example.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderservice.beans.Contact;
import com.example.orderservice.repo.ContactRepo;

@Service
public class ContactService {
	
	
	@Autowired
	ContactRepo repo;
	
//	@Autowired
//	RestTemplate restTemplate;
	

	
	
	public List<Contact> getContactByUserById(Integer userId)
	{
		
		return repo.findByUserId(userId);
	}
	

}
