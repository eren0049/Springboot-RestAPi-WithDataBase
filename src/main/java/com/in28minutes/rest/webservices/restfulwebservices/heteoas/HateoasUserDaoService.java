package com.in28minutes.rest.webservices.restfulwebservices.heteoas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
/**
 * 
 * This Class is pre Database using. There is a List of Users to Save, Delete,etc.
 */
@Component
public class HateoasUserDaoService {
	// Database > JPA or Hibernate 
	// Static List > UserDaoService 
	//	---------- Variables ----------	  //
	private static Integer usersCount=0;
	
	private static final List<HateoasUser> users = new ArrayList<>();
	
	static {
		users.add(new HateoasUser(++usersCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new HateoasUser(++usersCount,"Eve",LocalDate.now().minusYears(25)));
		users.add(new HateoasUser(++usersCount,"Jim",LocalDate.now().minusYears(20)));
	}
	
	//	---------- Methods ----------	//
	/**
	 * finds all User
	 * @return
	 */
	public List<HateoasUser> findAll(){
		return users;
	}
	
	/**
	 * finds User only w/ given specific ID from List at above
	 * There is .orElse(null): This returns null if no matched ID founded.
	 * @param id
	 * @return
	 */
	public HateoasUser findOne(int id){
		Predicate<? super HateoasUser> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	/*/
	 * adds new User to List above.
	 */
	public HateoasUser save(HateoasUser user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	/**
	 * Deletes user from List at above
	 * @param id
	 */
	public void deleteByID(int id){
		Predicate<? super HateoasUser> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
