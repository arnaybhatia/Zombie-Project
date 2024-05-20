// Class representing a fast zombie which is a specific type of zombie
public class FastZombie extends Zombie {
    // Constructor for FastZombie which adjusts health, attack, and speed values from the base Zombie
    public FastZombie(String type, int health, int attack, int speed) {
        super(type, health - 10, attack - 5, speed + 20);
    }
}
