package com.spring.micro.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.micro.service.entity.Hotel;
import com.spring.micro.service.entity.Rating;
import com.spring.micro.service.entity.User;
import com.spring.micro.service.exception.ExceptionClass;
import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
import com.spring.micro.service.external.feignservices.HotelService;
import com.spring.micro.service.repository.UserRepository;
import com.spring.micro.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		//implement the REST-SERVICE using RestTemplate
		//List<User> userList= userRepository.findAll();
		return userRepository.findAll();
	}

	/*
	 * This code is an implementation of a method called **`getUserById`** which
	 * fetches a user object from the database using the given user id. After
	 * getting the user object, the code sends a GET request to the RATING-SERVICE
	 * to fetch ratings for the user. The ratings are received in the form of an
	 * array and then converted to an ArrayList. The code then sends a GET request
	 * to the HOTEL-SERVICE to fetch hotels related to each rating, and adds them to
	 * the corresponding rating object. Finally, the code sets the list of ratings
	 * to the user object and returns it.
	 * 
	 * To achieve this, the code uses a RestTemplate to make the REST API calls to
	 * the RATING-SERVICE and HOTEL-SERVICE.
	 * 
	 * The response from the service is an array of **`Rating`** objects, which is
	 * then converted to an **`ArrayList`** using the **`Arrays.stream()`** method
	 * and **`toList()`** method.
	 * 
	 * Next, it uses the **`stream`** method of the **`ArrayList`** to iterate
	 * through each **`Rating`** object in the list and fetches the corresponding
	 * hotel details by calling the **`getForEntity`** method of **`RestTemplate`**
	 * with the URL of the hotel service and the hotel ID. The response from the
	 * hotel service is deserialized into a **`Hotel`** object, which is then set to
	 * the **`Hotel`** field of the **`Rating`** object. Finally, the updated
	 * **`Rating`** object is returned.
	 * 
	 * Finally, the method sets the **`ArrayList`** of ratings to the **`ratings`**
	 * field of the **`User`** object and returns the **`User`** object. Overall,
	 * this code fetches the user ratings and the corresponding hotel details using
	 * a **`RestTemplate`** object to call remote services. It also uses the
	 * **`@LoadBalanced`** annotation for the **`RestTemplate`** bean in the
	 * configuration class to make it discoverable by the service registry.
	 */
	@Override
	public User getUserById(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new ExceptionClass().new ResourceNotFoundException("No User found with this credentials"));
		
		// now fetch rating for the user from RATING-SERVICE
		//http://localhost:8083/ratings/users/1
		// in order to call the rating service in user we will use here
		// the Resttemplate @Autowired private RestTemplate restTemplate;
		
		// we cannot directly convert this into ArrayList so here we get it into an array then convert the array to arraylist
		Rating[] userRatings =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		
		//Array to ArrayList
		List<Rating> userRatingsList = Arrays.asList(userRatings);
		logger.info("{}", userRatingsList);
		
		//now get the hotels and set it to the related ratings
		List<Rating> ratingList = userRatingsList.stream().map(rating -> {
			//api call to hotel service to get the hotel for url : localhost:8082/hotels/hotel-by-id/2 this is hard coded
			//ResponseEntity<Hotel> responseEntity =  restTemplate.getForEntity("http://localhost:8082/hotels/hotel-by-id/"+rating.getHotelId(), Hotel.class);
			//below is dynamic i.e it will not use the hostname or the ip address instead it will take the service name like [HOTEL-SERVICE]
			//and if we use the service name we have to mention a annotation
			// @LoadBalanced in the config class for the bean we have declared for RestTemplate
			//here we are using Resttemplate
			
			//ResponseEntity<Hotel> responseEntity =  restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/hotel-by-id/"+rating.getHotelId(), Hotel.class);
			//Hotel hotel = responseEntity.getBody();
			
			//Now we will be using Feign client that we have implemented in HotelService in feignservice package
			Hotel hotel = hotelService.getHotelById(rating.getHotelId());
			
			//set the hotel to Rating
			rating.setHotel(hotel);
			
			//return the rating
			return rating;
			
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	}

	@Override
	public void deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserById(Long userId) {
		// TODO Auto-generated method stub
		
	}

}
