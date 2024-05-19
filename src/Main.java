import java.util.*;
public class Main {
    public static Object[][] map=new Object[6][6];
    public static void generateMap(Object[][] map){
        String start="START";
        String end = "END";
        map[0][0]= start;
        map[5][5]=end;
        for(int i =0; i<map.length;i++){
            for(int j=0; j<map[i].length; j++){
                int number= (int) (Math.random()*101);
                if(map[i][j]==null){
                    if(number<=20){
                        map[i][j]= new Zombie("Zombie",100,5,5);
                    }
                    else if (number<=40) {
                        map[i][j]= new FastZombie("Fast Zombie",100,5,5);
                    }
                    else if(number<=60){
                        map[i][j]= new StrongZombie("Strong Zombie",100,5,5);
                    }
                    else if(number<=80){
                        map[i][j]= new Supply(true);
                    }
                    else if(number<=100){
                        map[i][j]= new Supply(false);
                    }
                }
            }
        }
    }
    public static void printMap(Object[][] map) {
        System.out.println();
        System.out.print("      ");
        for (int i = 0; i < map[0].length; i++) {
            System.out.printf("%-15s ", "Col " + i);
        }
        System.out.println();

        for (int i = 0; i < map.length; i++) {
            System.out.print("Row "+i+" ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%-15s ", map[i][j].toString());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int row=0;
        int col=0;
        String p1="YOU";
        String past="Empty";
        System.out.println("Generating Map ..........");
        for(int i=0;i<101;i+=5){
            System.out.print(i+"% ");
            try {
                Thread.sleep(25); // Pause for 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i%20==0 && i!=0)
                System.out.println();
        }
        generateMap(map);
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input your name:");
        String input=sc.nextLine();
        Player player= new Player(input);
        System.out.println("Here is how this works. A strong zombie, fast zombie, or supply might be in each cell. You need to navigate from the top-left corner to the bottom-right corner. Capital or lowercase letters don't matter when inputting.");
        System.out.println("");
        map[0][0]=p1;
        while(true){
            printMap(map);
            System.out.println("You are currently at (" + row + ", " + col + "). What would you like to do? Move (M) or use an item from your inventory(U)?");
            input=sc.nextLine();
            if(input.toLowerCase().equals("m")){
                System.out.println("Would you like to move up(u), down(d), left(l), or right(r)?");
                input=sc.nextLine();
                if(input.toLowerCase().equals("u")){
                    if(row!=0){
                        map[row][col]=past;
                        row--;
                        doTheThingWhenYouMove(row,col,player);
                        map[row][col]=p1;
                    }
                    else{
                        System.out.println("You cannot move up!");
                    }
                }
                else if(input.toLowerCase().equals("d")){
                    if(row!=5){
                        map[row][col]=past;
                        row++;
                        doTheThingWhenYouMove(row,col,player);
                        map[row][col]=p1;
                    }
                    else{
                        System.out.println("You cannot move down!");
                    }
                }
                else if(input.toLowerCase().equals("l")){
                    if(col!=0){
                        map[row][col]=past;
                        col--;
                        doTheThingWhenYouMove(row,col,player);
                        map[row][col]=p1;
                    }
                    else{
                        System.out.println("You cannot move left!");
                    }
                }
                else if(input.toLowerCase().equals("r")){
                    if(col!=5){
                        map[row][col]=past;
                        col++;
                        doTheThingWhenYouMove(row,col,player);
                        map[row][col]=p1;
                    }
                }
            }
            else if(input.toLowerCase().equals("u")){
                System.out.println(player.getInventory());
            }
            if(row==5 && col==5){
                System.out.println("YOU WIN!!!!!!!!!");
                break;
            }
        }
    }

    public static void doTheThingWhenYouMove(int row,int col, Player player){
        if(map[row][col] instanceof Zombie){
            System.out.println(attackZombie(player, (Zombie) map[row][col]));
        }
        if(map[row][col] instanceof Supply){
            Scanner sc1=new Scanner(System.in);
            System.out.println("Would you like to store this in your inventory(i) or use it(u)?");
            String input=sc1.nextLine();
            if(input.equals("i")){
                System.out.println(player.addToInv((Supply) map[row][col]));
            }
            else if(input.equals("u")){
                System.out.println(player.use((Supply) map[row][col]));
            }
        }
    }

    public static String attackZombie(Player player, Zombie zombie){
        if(player.getSpeed() > zombie.getSpeed()) {
            zombie.setHealth(zombie.getHealth() - player.getAttackpwr());
            if (zombie.getHealth() > 0) {
                player.setHealth(player.getHealth() - zombie.getAttack());
            }
        } else {
            player.setHealth(player.getHealth() - zombie.getAttack());
            if (player.getHealth() > 0) {
                zombie.setHealth(zombie.getHealth() - player.getAttackpwr());
            }
        }

        if(zombie.getHealth() <= 0) {
            zombie.setHealth(0);
            return "Zombie died. You have " + player.getHealth() + " health left.";
        } else if (player.getHealth() <= 0) {
            player.setHealth(0);
            return "You died. Zombie has " + zombie.getHealth() + " health left.";
        }

        return attackZombie(player, zombie);
    }

}