package hello;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UsersRepositoryCustom {

	public Users getUserByUsernamePassword(String userName, String password);
//	public Users getUser(@RequestParam("id") String id);
	public List<BoxPackage> getUserPackages(@PathVariable("id") String id);
	List<Users> getUserByRating(@RequestParam("rating") double rating);
//	public void upgateRating(@PathVariable("id") String id,@RequestParam("rating") double rating);
	public Users updateRating(@RequestBody Users user);
	public void  deleteUser(@PathVariable("userName") String userName);
	
}
