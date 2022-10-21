package com.springlearn.crudDemp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlearn.crudDemp.dao.RoleRepository;
import com.springlearn.crudDemp.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role save(Role role) {
		return roleRepository.save(role);

	}
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
		
	}

}
