package org.assessment.restaurant_finder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {	
	
	private static List<Restaurant> restaurants = new ArrayList<>();

	public Restaurant findRestaurantByName(String restaurantName) {
		for(Restaurant r: getRestaurants()) {
			if(r.getName().trim().equalsIgnoreCase(restaurantName.trim())) {
				return r;
			}
		}
		throw new RestaurantNotFoundException("No restaurant found with the name: " + restaurantName);
	}


	public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
		Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
		restaurants.add(newRestaurant);
		return newRestaurant;
	}

	public Restaurant removeRestaurant(String restaurantName) throws RestaurantNotFoundException {
		Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
		restaurants.remove(restaurantToBeRemoved);
		return restaurantToBeRemoved;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
}
