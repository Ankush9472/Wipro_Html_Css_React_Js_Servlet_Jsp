
package com.wipro.springExample;

import org.springframework.stereotype.Service;

@Service
public class Manager implements Allocator{

	@Override
	public void taskAllocation(String user) {
		// TODO Auto-generated method stub
		
		System.out.println("The task is allocated by Manager to " + user);
		
	}

	
}
