package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //is important 
@RequestMapping("/users")
public class UsersRepositoryImpl implements UsersRepositoryCustom{
	
	private final MongoOperations operations;

	@Autowired
	public UsersRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "MongoOperations must not be null!");
		this.operations = operations;
	}
	
	
	//when user wants to login this method is called 
	@Override
	@RequestMapping( value = "/registeredUsers" ,params = {"userName","password"} )
	@ResponseBody
	public Users getUserByUsernamePassword(@RequestParam("userName") String userName, @RequestParam("password") String password) {
		
		 Query query  = new Query(Criteria.where("userName").is(userName).and("password").is(password));
			
		 Users user = operations.findOne(query, Users.class);
		 
		 return user;
	} 
	
	

	
//returns all the packages for a given user	
	@Override
	@RequestMapping( value = "/{id}/packages" )
	@ResponseBody
	public List<BoxPackage> getUserPackages(@PathVariable("id") String id) {
		
		 Query query  = new Query(Criteria.where("ownerId").is(id));
			
		 List<BoxPackage> packages = operations.find(query, BoxPackage.class);
		 
		 return packages;
	}

//selects list of the deliveryGuy based on the rating	
	@Override
	@RequestMapping( value = "/deliveryGuys", params = "rating" )
	@ResponseBody
	public List<Users> getUserByRating(@RequestParam("rating") double rating) {
		
		 Query query  = new Query(Criteria.where("rating").gte(rating));
			
		 List<Users> deliveryGuy = operations.find(query, Users.class);
		 
		 return deliveryGuy;
	}
	

	// when a package owner wants to update the rating of delivery guy, this method is called
	@Override
	@RequestMapping( value = "/deliveryGuys/rating", method = RequestMethod.PUT )
	@ResponseBody
	public Users updateRating(@RequestBody Users user) {
		Users user2 = new Users();
		 //Query query  = new Query(Criteria.where("id").is(id),new Update().set(rating, value));
		Query query  = new Query(Criteria.where("id").is(user.getId()));
		 Users deliveryGuy = operations.findOne(query, Users.class);
		
		 deliveryGuy.setRating((deliveryGuy.getRating() + user.getRating())/2);
		 operations.save(deliveryGuy);
		 
		 return deliveryGuy;
	}
	


// when user wants to unregister himself from the repository this method is called 
@Override
@RequestMapping( value = "/obsoleteUsers/{userName}", method = RequestMethod.DELETE )
@ResponseBody
public void  deleteUser(@PathVariable("userName") String userName) {	
	
	Users user2 = new Users();
	 //Query query  = new Query(Criteria.where("id").is(id),new Update().set(rating, value));
	Query query  = new Query(Criteria.where("userName").is(userName));
	
	  user2 = operations.findOne(query, Users.class);
	  operations.remove(user2);
	 
	 
}

}

