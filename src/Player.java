public class Player {
    private String name;
    private int health;
    private int attackpwr;
    private Object[] inventory;
    private int speed = 20;
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Object[] getInventory() {
        return inventory;
    }

    public void setInventory(Object[] inventory) {
        this.inventory = inventory;
    }

    public int getAttackpwr() {
        return attackpwr;
    }

    public void setAttackpwr(int attackpwr) {
        this.attackpwr = attackpwr;
    }
}
