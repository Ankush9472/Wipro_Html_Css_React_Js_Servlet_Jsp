package com.company.SpringDI;

import org.springframework.stereotype.Component;

import com.wipro.SpringDependencyInjection.Allocator;

@Component
public class DelegateConstructorDI {
	
	
	private final Allocator allocator;
	
	
	public DelegateConstructorDI(Allocator allocator)
	{
		this.allocator=allocator;
		
		System.out.println("Inside delegate constructor -Allocator "+ allocator.getClass().getSimpleName());
	};
	
	public void notifyUser()
	{
		allocator.taskAllocation("Ankush");
		//manager.taskAllocation("Ankush");
	}

}
