import java.util.ArrayList;

// Class representing a player in the game
public class Player {
    private final String name;
    private int health;
    private int attackpwr;
    private ArrayList<Supply> inventory;
    private int speed;

    // Constructor to initialize a new player with default values
    public Player(String name) {
        this.name = name;
        health = 200;
        attackpwr = 20;
        speed = 20;
        inventory = new ArrayList<>();
    }

    // Getter for player's health
    public int getHealth() {
        return health;
    }

    // Setter for player's health
    public void setHealth(int health) {
        this.health = health;
    }

    // Getter for player's attack power
    public int getAttackpwr() {
        return attackpwr;
    }

    // Getter for player's speed
    public int getSpeed() {
        return speed;
    }

    // Getter for player's inventory
    public ArrayList<Supply> getInventory() {
        return inventory;
    }

    // Method to use a supply item from the inventory
    public String use(Supply supply) {
        if (supply.isType()) { // Healing type supply
            health += 50;
            inventory.remove(supply);
            int maxHealth = 200;
            if (health > maxHealth)
                health = maxHealth;
            return "You have healed 50 health. Your current health is " + health;
        } else { // Damage type supply
            attackpwr += 5;
            inventory.remove(supply);
            return "Your new attack power is " + attackpwr;
        }
    }

    // Method to add a supply item to the inventory
    public String addToInv(Supply supply) {
        inventory.add(supply);
        return "Added " + supply.getName() + " to inventory";
    }
}
