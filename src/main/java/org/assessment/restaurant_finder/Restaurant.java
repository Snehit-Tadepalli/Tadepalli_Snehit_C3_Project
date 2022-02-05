package org.assessment.restaurant_finder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
    	LocalTime nowTime = getCurrentTime();
    	if(nowTime.getHour() >= openingTime.getHour() && nowTime.getHour() <= closingTime.getHour()) {
    		if(nowTime.getHour() == openingTime.getHour() && nowTime.getMinute() >= openingTime.getMinute()) {
    			return true;
        	} else if(nowTime.getHour() == closingTime.getHour() && nowTime.getMinute() <= closingTime.getMinute()) {
        		return true;
        	} else if(nowTime.getHour() > openingTime.getHour() && nowTime.getHour() < closingTime.getHour()) {
        		return true;
        	} else {
        		return false;
        	}
    	} else {
    		return false;
    	}
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().trim().equalsIgnoreCase(itemName.trim()))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws ItemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new ItemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
