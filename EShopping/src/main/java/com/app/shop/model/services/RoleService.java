package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.RoleRepository;
import com.app.shop.model.pojo.Role;
import com.app.shop.model.services.srinterface.IRoleService;

@Service
public class RoleService implements IRoleService {
	@Autowired
	RoleRepository RoleRepo;

	@Override
	public Optional<Role> getRoleById(int id) {
		try {
			return RoleRepo.findById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Optional<Role> getByRolename(String roleName) {
		return RoleRepo.findByName(roleName);
	}

	@Override
	public List<Role> getAllRoles() {
		return RoleRepo.findAll();
	}

	@Override
	public Role updateRole(Role role, Role updatedRole) {
		role.setName(updatedRole.getName());
		return this.saveRole(role);
	}

	@Override
	public Role addRole(Role role) {
		return this.saveRole(role);
	}

	@Override
	public void deleteRole(Role role) {
		// Optional<Role> Role = storeRepo.findById(id);
		if (role != null) {
			RoleRepo.delete(role);
		}
	}

	@Override
	public Role saveRole(Role role) {
		try {
			role = RoleRepo.save(role);
			return role;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
