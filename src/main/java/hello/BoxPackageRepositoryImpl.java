 package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@Controller  
@RequestMapping("/packages")
public class BoxPackageRepositoryImpl  implements BoxPackageRepositoryCustom
{

	private final MongoOperations operations;

	@Autowired
	public BoxPackageRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "MongoOperations must not be null!");
		this.operations = operations;
	}

// returns the owner information of the package using owner name 
	@RequestMapping(value = "/owners", params = "userName" )
	@ResponseBody
	public BoxPackage yourCustomMethod(@RequestParam("userName") String userName) {
		
		 Query query  = new Query(Criteria.where("userName").is(userName));

		
		Users newUser = operations.findOne(query,Users.class);
		BoxPackage ownerPackage = null;
		query = new Query(Criteria.where("ownerId").is(newUser.getId()));
		ownerPackage = operations.findOne(query, BoxPackage.class);
		
		return ownerPackage;
	}

	// returns information of the package owner using package id
	@Override
	@RequestMapping(value = "/owners", params = "id" )
	@ResponseBody
	public Users selectUserInfo(@RequestParam("id") String id) {
		
		//finds owner id of a given package, quesries users for owner information
		 Query query  = new Query(Criteria.where("id").is(id));
		
		BoxPackage boxInfo = operations.findOne(query,BoxPackage.class);
		
		query = new Query(Criteria.where("id").is(boxInfo.getOwnerId()));
		Users userInfo = operations.findOne(query, Users.class);
		
		
		return userInfo;
	}

	@Override
	@RequestMapping(value = "/userInfo_collection", params = "id" )
	@ResponseBody
	public ArrayList<Users> selectUserInfo_collection(String id) {
		// query BoxPackages by given package id
		 Query query  = new Query(Criteria.where("id").is(id));
			
			BoxPackage boxInfo = operations.findOne(query,BoxPackage.class);
			
			query = new Query(Criteria.where("id").is(boxInfo.getOwnerId()));
			Users userInfo = operations.findOne(query, Users.class);
			
			ArrayList<Users> userCollection = new ArrayList<Users>();
			userCollection.add(userInfo);
			
		
		return userCollection;
	}
	//returns all the packages with a given latitude and longitude 
	@Override
	@RequestMapping( value = "/destinations" ,params = {"lang","lant"} )
	@ResponseBody
	public HashMap<String,List<BoxPackage>> getPackageByDestination(float lang, float lant) {
		
	 Query query  = new Query(Criteria.where("lang").is(lang).and("lant").is(lant));
			
		 List<BoxPackage> allBoxes = operations.find(query,BoxPackage.class);
		 
		 HashMap<String,List<BoxPackage>> testMap = new HashMap();
		 testMap.put("packages", allBoxes);
		 
		 return testMap;
	} 

	
	// when package is delivered it can be removed from the repository=> this method is called 
	@Override
	@RequestMapping( value = "/obsoletePackages/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void obsoletePackage(@PathVariable("id") String id) {
		
	 

		 Query query  = new Query(Criteria.where("id").is(id));
		 System.out.println("Value is selected");	
		 
		 BoxPackage box = operations.findOne(query,BoxPackage.class);
		 operations.remove(box);
		 System.out.println("object us deleted");
		 
		 
	} 


}
