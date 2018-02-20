package com.livetalk.user.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.livetalk.user.modal.User;

@Service("userDAO")
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly=true)
	public User findByEmail(String email) {
		List results =  entityManager.createQuery("select u from User u where u.email= :email")
											.setParameter("email", email)
											.getResultList();
		if (results.isEmpty()) {
		    return null; 
		} 
		    return (User) results.get(0);
	}

}
