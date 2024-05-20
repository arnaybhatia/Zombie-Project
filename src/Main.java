import java.util.*;

// Main class to run the game
public class Main {
    public static Object[][] map = new Object[6][6]; // Game map represented as a 2D array

    // Method to generate the game map with zombies and supplies
    public static void generateMap(Object[][] map) {
        String start = "START";
        String end = "END";
        map[0][0] = start; // Set start point
        map[5][5] = end;   // Set end point
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int number = (int) (Math.random() * 101); // Generate a random number between 0 and 100
                if (map[i][j] == null) { // Check if the current cell is empty
                    if (number <= 20) {
                        map[i][j] = new Zombie("Zombie", 100, 10, 5); // 20% chance to place a regular zombie
                    } else if (number <= 40) {
                        map[i][j] = new FastZombie("Fast Zombie", 100, 10, 5); // 20% chance to place a fast zombie
                    } else if (number <= 60) {
                        map[i][j] = new StrongZombie("Strong Zombie", 100, 10, 5); // 20% chance to place a strong zombie
                    } else if (number <= 80) {
                        map[i][j] = new Supply(true); // 20% chance to place a healing supply
                    } else if (number <= 100) {
                        map[i][j] = new Supply(false); // 20% chance to place a damage supply
                    }
                }
            }
        }
    }

    // Method to print the game map
    public static void printMap(Object[][] map) {
        System.out.println();
        System.out.print("      ");
        for (int i = 0; i < map[0].length; i++) {
            System.out.printf("%-15s ", "Col " + i); // Print column headers
        }
        System.out.println();

        for (int i = 0; i < map.length; i++) {
            System.out.print("Row " + i + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%-15s ", map[i][j].toString()); // Print each cell's content
            }
            System.out.println();
        }
    }

    // Main method to start the game
    public static void main(String[] args) {
        int row = 0; // Player's starting row
        int col = 0; // Player's starting column
        String p1 = "YOU"; // Representation of the player on the map
        String past = "Empty"; // Placeholder for cells the player has moved from
        System.out.println("Generating Map ..........");
        for (int i = 0; i < 101; i += 5) {
            System.out.print(i + "% ");
            try {
                Thread.sleep(25); // Pause for 0.025 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 20 == 0 && i != 0)
                System.out.println();
        }
        generateMap(map); // Generate the game map
        Scanner sc = new Scanner(System.in); // Scanner for user input
        System.out.println("Please input your name:");
        String input = sc.nextLine(); // Read player's name
        Player player = new Player(input); // Create a new player with the given name
        System.out.println("Here is how this works. A strong zombie, fast zombie, or supply might be in each cell. You need to navigate from the top-left corner to the bottom-right corner. Capital or lowercase letters don't matter when inputting.");
        System.out.println("");
        map[0][0] = p1; // Place the player at the starting position
        while (true) { // Main game loop
            printMap(map); // Print the game map
            System.out.println("You are currently at (" + row + ", " + col + "). What would you like to do? Move (M) or use an item from your inventory(U)?");
            input = sc.nextLine(); // Read player's choice
            if (input.toLowerCase().equals("m")) { // If the player wants to move
                System.out.println("Would you like to move up(u), down(d), left(l), or right(r)?");
                input = sc.nextLine(); // Read direction
                if (input.toLowerCase().equals("u")) {
                    if (row != 0) { // Check if move is valid
                        map[row][col] = past; // Mark current position as visited
                        row--; // Move up
                        doTheThingWhenYouMove(row, col, player); // Handle actions at the new position
                        map[row][col] = p1; // Update player's position on the map
                    } else {
                        System.out.println("You cannot move up!"); // Print error if move is invalid
                    }
                } else if (input.toLowerCase().equals("d")) {
                    if (row != 5) { // Check if move is valid
                        map[row][col] = past; // Mark current position as visited
                        row++; // Move down
                        doTheThingWhenYouMove(row, col, player); // Handle actions at the new position
                        map[row][col] = p1; // Update player's position on the map
                    } else {
                        System.out.println("You cannot move down!"); // Print error if move is invalid
                    }
                } else if (input.toLowerCase().equals("l")) {
                    if (col != 0) { // Check if move is valid
                        map[row][col] = past; // Mark current position as visited
                        col--; // Move left
                        doTheThingWhenYouMove(row, col, player); // Handle actions at the new position
                        map[row][col] = p1; // Update player's position on the map
                    } else {
                        System.out.println("You cannot move left!"); // Print error if move is invalid
                    }
                } else if (input.toLowerCase().equals("r")) {
                    if (col != 5) { // Check if move is valid
                        map[row][col] = past; // Mark current position as visited
                        col++; // Move right
                        doTheThingWhenYouMove(row, col, player); // Handle actions at the new position
                        map[row][col] = p1; // Update player's position on the map
                    } else {
                        System.out.println("You cannot move right!"); // Print error if move is invalid
                    }
                }
            } else if (input.toLowerCase().equals("u")) { // If the player wants to use an item
                System.out.println(player.getInventory()); // Print the player's inventory
                if (player.getInventory().size() == 0) {
                    System.out.println("You have nothing in your inventory"); // Print error if inventory is empty
                } else {
                    System.out.println("Please give the number of what you would like to use. Index starts at 0.");
                    int number = sc.nextInt(); // Read item index

                    try {
                        System.out.println(player.use(player.getInventory().get(number))); // Use the selected item
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Something went wrong. Please make sure it is a valid number."); // Print error if index is invalid
                    }
                }
            }
            if (row == 5 && col == 5) { // Check if player has reached the end
                System.out.println("YOU WIN!!!!!!!!!"); // Print winning message
                break; // Exit the game loop
            }
        }
    }

    // Method to handle actions when a player moves to a new cell
    public static void doTheThingWhenYouMove(int row, int col, Player player) {
        if (map[row][col] instanceof Zombie) { // Check if the cell contains a zombie
            System.out.println("Zombie: "+ ((Zombie) map[row][col]).getBehavior());
            System.out.println(attackZombie(player, (Zombie) map[row][col])); // Attack the zombie
        }
        if (map[row][col] instanceof Supply) { // Check if the cell contains a supply
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Would you like to store this in your inventory(i) or use it(u)?");
            String input = sc1.nextLine();
            if (input.equals("i")) {
                System.out.println(player.addToInv((Supply) map[row][col])); // Add supply to inventory
            } else if (input.equals("u")) {
                System.out.println(player.use((Supply) map[row][col])); // Use the supply
            }
        }
    }

    // Method to handle attacking a zombie
    public static String attackZombie(Player player, Zombie zombie) {
        Scanner sc = new Scanner(System.in);
        if (player.getHealth() <= 0) { // Check if player is dead
            player.setHealth(0); // Set player's health to 0
            return "You died. Zombie has " + zombie.getHealth() + " health left.";
        } else if (zombie.getHealth() <= 0) { // Check if zombie is dead
            zombie.setHealth(0); // Set zombie's health to 0
            return "Zombie died. You have " + player.getHealth() + " health left.";
        }

        System.out.println("Do you want to attack (a) or dodge (d)?");
        String action = sc.nextLine().toLowerCase();

        if (action.equals("a")) { // If the player chooses to attack
            System.out.println("The zombie has " + zombie.getHealth() + " health left. You have " + player.getHealth() + " health left.");
            if (player.getSpeed() > zombie.getSpeed()) { // Check if player is faster than the zombie
                zombie.setHealth(zombie.getHealth() - player.getAttackpwr()); // Player attacks zombie
                if (zombie.getHealth() > 0) {
                    player.setHealth(player.getHealth() - zombie.getAttack()); // Zombie counterattacks
                }
            } else {
                player.setHealth(player.getHealth() - zombie.getAttack()); // Zombie attacks first
                if (player.getHealth() > 0) {
                    zombie.setHealth(zombie.getHealth() - player.getAttackpwr()); // Player counterattacks
                }
            }
        } else if (action.equals("d")) { // If the player chooses to dodge
            if (Math.random() < 0.5) {
                System.out.println("You dodged the zombie's attack!");
                return attackZombie(player, zombie); // Continue the fight
            } else {
                System.out.println("Dodge failed. Zombie attacks!");
                player.setHealth(player.getHealth() - zombie.getAttack()); // Player takes damage
            }
        } else {
            System.out.println("Invalid input. Please choose 'a' to attack or 'd' to dodge.");
            return attackZombie(player, zombie); // Repeat the choice
        }

        return attackZombie(player, zombie); // Continue the fight
    }
}
