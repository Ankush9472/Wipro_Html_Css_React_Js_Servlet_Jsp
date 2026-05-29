package com.example.orderservice.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.orderservice.beans.User;

@Repository
public interface UserRepo extends CrudRepository<User , Integer> {

}
