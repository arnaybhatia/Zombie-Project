// Class representing a supply item in the game
public class Supply {
    private final String name;
    private boolean type; // True if healing, false if damage

    // Constructor to initialize the supply item with its type
    public Supply(boolean type) {
        this.type = type;
        if (type) {
            name = "Health Potion";
        } else {
            name = "Sword";
        }
    }

    // Getter for the name of the supply item
    public String getName() {
        return name;
    }

    // Getter to check the type of the supply item
    public boolean isType() {
        return type;
    }

    // Setter to change the type of the supply item
    public void setType(boolean type) {
        this.type = type;
    }

    // Override toString method to return the name of the supply item
    public String toString() {
        return getName();
    }
}
