// Class representing a generic zombie in the game
public class Zombie {
    private String type;
    private int health;
    private int attack;
    private int speed;
    private final String behavior = "You will not survive this fight";

    // Constructor to initialize a new zombie with its attributes
    public Zombie(String type, int health, int attack, int speed) {
        this.type = type;
        this.health = health;
        this.attack = attack;
        this.speed = speed;
    }

    // Getter for the zombie's health
    public int getHealth() {
        return health;
    }

    // Getter for the zombie's behavior
    public String getBehavior() {
        return behavior;
    }

    // Setter for the zombie's health
    public void setHealth(int health) {
        this.health = health;
    }

    // Getter for the zombie's attack power
    public int getAttack() {
        return attack;
    }

    // Getter for the zombie's speed
    public int getSpeed() {
        return speed;
    }

    // Override toString method to return the type of the zombie
    public String toString() {
        return type;
    }
}
