// Class representing a strong zombie which is a specific type of zombie
public class StrongZombie extends Zombie {
    // Constructor for StrongZombie which adjusts health, attack, and speed values from the base Zombie
    public StrongZombie(String type, int health, int attack, int speed) {
        super(type, health + 20, attack + 10, speed - 5);
    }
}
