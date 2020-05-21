package com.app.shop.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shop.model.pojo.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

   Optional<Role> findByName(String name);
}
