package dao;

import java.util.Optional;

import entity.User;

public interface UserDao {
	void add(User user);
	
	Optional<User>find(int id);
	
	Optional<User>find(String username);
	
	void delete(int id);
	
	void update(User user);
}
