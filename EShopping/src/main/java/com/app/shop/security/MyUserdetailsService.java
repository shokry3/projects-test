package com.app.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.UserRepository;
import com.app.shop.model.pojo.User;


@Service
public class MyUserdetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username).get();
		if(user == null) {
			throw new UsernameNotFoundException("user 404");
		}
		return new UserPrincipal(user);
	}

}
