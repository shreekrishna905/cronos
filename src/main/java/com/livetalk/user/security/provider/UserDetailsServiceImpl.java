package com.livetalk.user.security.provider;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.livetalk.user.dao.UserDAO;
import com.livetalk.user.modal.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try{
			user = userDAO.findByEmail(email);	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(user==null){
			 throw new UsernameNotFoundException("User " + email + " not found");	
		}
			 return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), 
	                 user.isEnabled(), true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
        							.map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authorities;
    }

}
