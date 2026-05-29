package com.company.SpringDI;

@FunctionalInterface
public interface Allocator {
	
	// This method is implemented by many manager class
	// While implementing the interface
	
	void taskAllocation(String user);

	

}
