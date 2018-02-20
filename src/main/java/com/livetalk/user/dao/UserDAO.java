package com.livetalk.user.dao;

import java.sql.SQLException;

import com.livetalk.user.modal.User;

public interface UserDAO {

	public User findByEmail(String email) throws SQLException;
}
