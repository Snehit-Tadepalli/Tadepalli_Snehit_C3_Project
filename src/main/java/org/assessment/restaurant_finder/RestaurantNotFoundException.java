package org.assessment.restaurant_finder;

@SuppressWarnings("serial")
public class RestaurantNotFoundException extends RuntimeException {
	RestaurantNotFoundException(String message) {
		super(message);
	}
}
