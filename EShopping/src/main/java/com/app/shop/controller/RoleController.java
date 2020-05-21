package com.app.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shop.exceptionhandel.ResourceNotFoundException;
import com.app.shop.model.pojo.Role;
import com.app.shop.model.services.srinterface.IRoleService;

@RestController
@RequestMapping("/store/api/r/")
public class RoleController {
	
	@Autowired
	IRoleService roleService;

	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getAllRoles() {
		return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<Role> getRole(@PathVariable("id") String id) throws ResourceNotFoundException {
		Role role = new Role();
		try {
			Integer num = Integer.parseInt(id);
			role = roleService.getRoleById(num)
					.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + id));
		} catch (NumberFormatException e) {
			role = roleService.getByRolename("" + id)
					.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + id));
		}
		return ResponseEntity.ok().body(role);
	}

	@PostMapping(path = "/roles", consumes = { "application/json" })
	public ResponseEntity<Role> addRole(@Valid @RequestBody Role role) {
		Role addedRole = roleService.addRole(role);
		return ResponseEntity.ok().body(addedRole);
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable("id") int id, @Valid @RequestBody Role RoleDetails)
			throws ResourceNotFoundException {
		Role role = roleService.getRoleById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + id));
		final Role updatedRole = roleService.updateRole(role, RoleDetails);
		return ResponseEntity.ok(updatedRole);
	}

	@DeleteMapping("/roles/{id}")
	public Map<String, Boolean> deletRole(@PathVariable("id") int id) throws ResourceNotFoundException {
		Role role = roleService.getRoleById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + id));

		roleService.deleteRole(role);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}



}
