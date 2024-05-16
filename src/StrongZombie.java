public class StrongZombie extends Zombie{
    public StrongZombie(String type, int health, int attack, int speed){
        super(type,health+20,attack+10,speed-5);
    }
}