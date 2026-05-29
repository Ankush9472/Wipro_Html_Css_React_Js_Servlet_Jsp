package com.configurationstyle;

@FunctionalInterface
public interface Allocator {

	void taskAllocation(String user);
}
