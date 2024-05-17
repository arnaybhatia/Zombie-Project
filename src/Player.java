import javax.swing.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private final int maxHealth=200;
    private int attackpwr;

    public ArrayList<Supply> getInventory() {
        return inventory;
    }

    private ArrayList<Supply> inventory;
    private int speed;
    public Player(String name) {
        this.name = name;
        health=200;
        attackpwr=20;
        speed=20;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackpwr() {
        return attackpwr;
    }

    public void setAttackpwr(int attackpwr) {
        this.attackpwr = attackpwr;
    }

    public int getSpeed() {
        return speed;
    }
    public String use(Supply supply){
        if(supply.isType()){
            health+=50;
            if(health>maxHealth)
                health=maxHealth;
            return "You have healed 50 health. Your current health is "+health;
        }
        else{
            attackpwr+=5;
            return "Your new attack power is "+attackpwr;
        }
    }
    public String addToInv(Supply supply){
        inventory.add(supply);
        return "Added " + supply.getName() + " to inventory";
    }
}
