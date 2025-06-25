package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ingredient {
    private final StringProperty name;
    private final int amount;
    private final StringProperty unit;

    public Ingredient(String name, int amount, String unit) {
        this.name = new SimpleStringProperty(name);
        this.amount = amount;
        this.unit = new SimpleStringProperty(unit);
    }

	public StringProperty getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public StringProperty getUnit() {
		return unit;
	}
    
}
