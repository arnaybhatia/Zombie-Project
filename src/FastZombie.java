public class FastZombie extends Zombie{
    public FastZombie(String type, int health, int attack, int speed){
        super(type,health-10,attack-5,speed+20);
    }
}