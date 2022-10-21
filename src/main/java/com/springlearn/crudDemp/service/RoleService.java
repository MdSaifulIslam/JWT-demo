package com.springlearn.crudDemp.service;

import java.util.List;
import java.util.Optional;

import com.springlearn.crudDemp.entity.Role;

public interface RoleService {

	public List<Role> findAll();

	public Role save(Role role);
	
	public Optional<Role> findById(Integer id);

}
