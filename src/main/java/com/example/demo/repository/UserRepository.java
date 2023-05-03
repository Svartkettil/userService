package com.example.demo.repository;

import com.example.demo.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends ListCrudRepository {

    User findById(Long id);
}
