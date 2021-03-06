package org.assessment.restaurant_finder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    
    @BeforeEach
    public void repeatingCode() {
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	Restaurant mockedRestaurant = Mockito.spy(restaurant);
    	Mockito.when(mockedRestaurant.getCurrentTime()).thenReturn(LocalTime.of(10, 30), LocalTime.of(12, 30), LocalTime.of(22, 00));
    	assertTrue(mockedRestaurant.isRestaurantOpen());
    	assertTrue(mockedRestaurant.isRestaurantOpen());
    	assertTrue(mockedRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	Restaurant mockedRestaurant = Mockito.spy(restaurant);
    	Mockito.when(mockedRestaurant.getCurrentTime()).thenReturn(LocalTime.of(4, 30), LocalTime.of(10, 29), LocalTime.of(22, 01));
    	assertFalse(mockedRestaurant.isRestaurantOpen());
    	assertFalse(mockedRestaurant.isRestaurantOpen());
    	assertFalse(mockedRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    

    //>>>>>>>>>>>>>>>>>>>>>>>>>CALCULATE TOTAL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    @Test
    public void total_should_be_0_when_no_items_are_selected() {
    	int expectedTotal = 0;
    	assertEquals(expectedTotal, restaurant.calculateTotal());
    }
    
    @Test
    public void total_should_be_119_when_first_item_is_selected() {
    	int expectedTotal = 119;
    	assertEquals(expectedTotal, restaurant.calculateTotal("Sweet corn soup"));
    }
    
    @Test
    public void total_should_be_388_when_both_the_items_are_selected() {
    	int expectedTotal = 119 + 269; // 388
    	assertEquals(expectedTotal, restaurant.calculateTotal("Sweet corn soup", "Vegetable lasagne"));
    }
    
    //<<<<<<<<<<<<<<<<<<<<<<<<<CALCULATE TOTAL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(ItemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
