package com.company.SpringDI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.SpringDependencyInjection.Allocator;

@Component
public class DelegateFieldDI{

  @Autowired	
	// Here delegate depends upon interface only  -- 
  private  Allocator allocator; // depends on abstraction // optional use
	
  // dependency injection will be done by Spring using field injection
 
  
  
  public void notifyUser() {
	    allocator.taskAllocation("Niti");
	    System.out.println("field injection --> allocator used: " 
	        + allocator.getClass().getSimpleName());
	}

}