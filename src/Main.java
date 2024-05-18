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
    public static void printMap(Object[][] map){
        for(int i =0; i<map.length;i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }
    public static void main(String[] args) {
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
        System.out.println("");
        printMap(map);
    }
}