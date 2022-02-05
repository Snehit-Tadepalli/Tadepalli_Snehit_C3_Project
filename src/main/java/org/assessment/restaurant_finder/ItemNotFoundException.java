package org.assessment.restaurant_finder;

@SuppressWarnings("serial")
public class ItemNotFoundException extends Throwable {
    public ItemNotFoundException(String itemName) {
        super(itemName);
    }
}

