package com.example.Resource.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Resource.Entity.UserEntity;

@Repository  
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
