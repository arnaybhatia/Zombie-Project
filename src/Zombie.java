public class Zombie {
    private String type;
    private int health;
    private int attack;
    private int speed;
    private final String behavior= "You will not survive this fight";

    public Zombie(String type, int health, int attack, int speed){
        this.type=type;
        this.health=health;
        this.attack=attack;
        this.speed=speed;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }

}
