package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.pojo.Role;

public interface IRoleService {
	
	Optional<Role> getRoleById(int id);

	Optional<Role> getByRolename(String rolename);

	List<Role> getAllRoles();

	Role updateRole(Role Role, Role updatedRole);

	Role addRole(Role role);

	void deleteRole(Role role);

	Role saveRole(Role role);

}
