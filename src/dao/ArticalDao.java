package dao;

import java.util.List;
import java.util.Optional;

import entity.Artical;
import entity.User;

public interface ArticalDao {
	
	void add(Artical artical);
	
	Optional<Artical>find(int id);
	
	void update(Artical artical);
	
	void delete(int id);
	
	List<Artical>listAllOf(User author);
	
	List<Artical>listAll();
}
