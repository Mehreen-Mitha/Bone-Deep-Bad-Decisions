/*------------------------------
    TEXT BASED ADVENTURE GAME
-------------------------------*/
package bonedeepbaddecisions;
import java.util.*;
public class BoneDeepBadDecisions {

    // Global player stats and variables
    static int health;
    static int attack;

    // Inventory flags
    static boolean has_diamond_sword;
    static boolean has_key;
    static boolean has_lockpick;
    static boolean has_potion;

    // Skeleton friend
    static boolean skeleton_helpful;

    // Global Scanner for input and Random for RNG mechanics
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        // Start the game by opening the main menu
        main_menu();
    }

    // Initializing player stats and variables
    // Also used for replay with base attributes
    public static void original_stats() {
        health = 20;
        attack = 3;

        has_diamond_sword = false;
        has_key = false;
        has_lockpick = false;
        has_potion = false;

        skeleton_helpful = false;
    }

    //------------------
    // MAIN MENU FUNCTION
    public static void main_menu() {
        while (true) {
            System.out.println("==============================");
            System.out.println("    BONE DEEP BAD DECISIONS");
            System.out.println("==============================");
            System.out.println("== every choice leaves a mark ==");
            System.out.println("\n1. Start New Game\n2. Quit");
            System.out.println("(enter number to select the choice)");
            System.out.print("> ");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                original_stats();
                intro();
            } else if (choice.equals("2")) {
                System.out.println("\nFarewell Traveler.");
                System.exit(0);
            } else {
                System.out.println("\nThis choice leads nowhere.");
            }
        }
    }

    //--------------
    // INTRO FUNCTION
    public static void intro() {
        health = 20;
        System.out.println("A fire crackles softly...");
        System.out.println("You seem to have gotten stuck in a dark dungeon while out on your travels.");
        System.out.println("Your torch is nearly burnt out...\nYou start a fire to stay warm and regain energy.");
        System.out.println("\n** ALL STATS REPLENISHED **");
        System.out.println("Health: " + health);
        System.out.println("Attack: " + attack);
        System.out.println("\n** QUEST UPDATED: ESCAPE THE DUNGEON **");
        skeleton_encounter();
        next_choice();
    }

    //---------------------------
    // SKELETON ENCOUNTER FUNCTION
    public static void skeleton_encounter() {
        System.out.println("\nAs the fire continues to brighten your surroundings, you suddenly hear shuffling.");
        System.out.println("Glancing around you see a skeleton slouched in the corner reading a book.");
        System.out.println("As you stare at it, it looks up and glares at you..");
        System.out.println("\nWhat do you do?");
        System.out.println("1. Glare Back");
        System.out.println("2. Wave");
        System.out.println("3. Ignore it");
        System.out.println("=== use the number of the choice to answer ===");
        System.out.print("> ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.println("\nYou glare back. It glares at you.\n...Nothing happens.");
            skeleton_helpful = false;
        } else if (choice.equals("2")) {
            System.out.println("\nYou wave!\nShockingly it waves back, accidentally fumbling its book.\nIt mutters angrily as it shuffles away.");
            skeleton_helpful = true;
        } else if (choice.equals("3")) {
            System.out.println("\nYou ignore it and go about your business.\nThe skeleton sighs dramatically, continuing to read.");
            skeleton_helpful = false;
        } else {
            System.out.println("\nWrong choice!\nThe skeleton throws a bone at you.");
            System.out.println("** damage taken **");
            health -= 1;
            skeleton_helpful = false;
        }
        System.out.println("\n** YOUR ACTIONS WILL HAVE CONSEQUENCES **");
    }

    //-----------------------------------
    // DECIDING BEGINNING PATHWAY FUNCTION
    public static void next_choice() {
        System.out.println("\nAnyways...");
        System.out.println("Grabbing your fire lit torch you look around..\nUp ahead you see a sign pointing EAST to an armory and a blood stained hallway towards the WEST.");
        System.out.println("Where do you plan to go next?");
        System.out.println("1. EAST\n2. WEST");
        System.out.print("> ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.println("You start heading towards the armory..");
            armory();
        } else if (choice.equals("2")) {
            System.out.println("You start heading towards the hallway..watch your step theres blood on the floor");
            hallway();
        } else {
            System.out.println("Invalid choice! You wander aimlessly..forever, pick the right direction!");
            next_choice();
        }
    }

    //------------------
    // ARMORY PATH CHOSEN
    public static void armory() {
        System.out.println("\nYou enter the RUSTY ARMORY. Dusty weapons line the walls.");
        System.out.println("You find a sturdy Iron Sword.");
        System.out.println("\n** IRON SWORD ADDED TO INVENTORY **");
        attack += 3;
        System.out.println("Your attack has increased\nAttack: " + attack);
        System.out.println("Suddenly you hear rustling...");
        fight_goblin();
        System.out.println("Oh hey you survived. Thats nice.");
        next_path_choice();
    }

    //-------------------
    // HALLWAY PATH CHOSEN
    public static void hallway() {
        System.out.println("\nGrimacing as you walk through the HALL, you jump as you find a rotten corpse.");
        System.out.println("Seeing as he held a sword and was currently dead so had no use for it..you grab it.");
        System.out.println("\n** STEEL SWORD ADDED TO INVENTORY **");
        attack += 5;
        System.out.println("Your attack has increased\nAttack: " + attack);
        System.out.println("\nUp ahead you hear a faint growl...");
        fight_goblin();
        System.out.println("\nOh hey you survived. Thats nice.");
        System.out.println("You notice an exit to the hallway up ahead...\nDo you stay and look around the hallway or head towards the exit?");
        System.out.println("1. Look Around\n2. Exit");
        System.out.print("> ");
        String path = input.nextLine();

        if (path.equals("1")) {
            System.out.println("\nYou notice a small crack in the wall. Do you wish to investigate?\n1. Yes\n2. No");
            System.out.print("> ");
            String choice = input.nextLine();
            if (choice.equals("1")) {
                secret_room();
            } else {
                System.out.println("You decide to head towards the exit.");
                next_path_choice();
            }
        } else {
            next_path_choice();
        }
    }

    //---------------------
    // GOBLIN FIGHT FUNCTION
    public static void fight_goblin() {
        int goblin_health = 20;
        int goblin_attack = 3;
        boolean fought = false;
        System.out.println("\nA GOBLIN jumps out. Prepare yourself for a fight.");

        while (goblin_health > 0 && health > 0) {
            System.out.println("\nYour Health: " + health);
            System.out.println("Goblins Health: " + goblin_health);
            System.out.println("Do you \n1. Attack\n2. Run");
            System.out.print("> ");
            String action = input.nextLine();

            if (action.equals("1")) {
                fought = true;
                goblin_health -= attack;
                System.out.println("\nYou slash at the goblin!\nGoblins Health: " + goblin_health);
                if (goblin_health <= 0) {
                    System.out.println("You defeated it...");
                    break;
                }
                health -= goblin_attack;
                System.out.println("\nThe goblin attacks you! Damage taken!\nHealth: " + health);
            } else if (action.equals("2")) {
                System.out.println("You run past the goblin..barely escaping harm.");
                break;
            } else {
                fought = true;
                System.out.println("Wrong choices will get you killed.\nThe Goblin scratches you lightly. ");
                health -= 1;
                System.out.println("Health: " + health);
            }
        }

        if (health <= 0) {
            System.out.println("Ouch I think you just died.");
            System.out.println("** GAME OVER **");
            System.exit(0);
        }

        if (fought) {
            System.out.println("\nTired..you patch yourself up a little.");
            health += 2;
            System.out.println("** 2 HP Restored **");
            System.out.println("Health:" + health);
        }
    }

    //--------------------------
    // SECRET ROOM FOUND FUNCTION
    public static void secret_room() {
        System.out.println("\nYou squeeze through the crack, discovering a HIDDEN ROOM.");
        System.out.println("Torches flicker along the walls, casting long, dancing shadows.");
        System.out.println("At the center of the room, a diamond sword is perched on a pedestal.");
        System.out.println("\nSuddenly, a cold chill washes over you...");
        System.out.println("\nA pale vampire steps forward, his eyes gleaming in the torchlight.");
        System.out.println("\nDo you.. \n1. Approach the sword\n2. Step back\n3. Prepare to attack");
        System.out.print("> ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.println("\nYou cautiously approach the sword. The vampire watches silently, a shadow flickering across his face.");
            System.out.println("As your hand hovers over the hilt, a whisper fills your mind...");
            System.out.println("\"Many have tried..all have failed..\"");
            System.out.println("Do you risk grabbing the sword?\n1.Yes\n2.No");
            System.out.print("> ");
            String grab = input.nextLine();
            if (grab.equals("1")) {
                has_diamond_sword = true;
                attack += 8;
                System.out.println("\nYour fingers close around the hilt of the diamond sword. A cold shiver runs through you.");
                System.out.println("The vampire steps back, his eyes narrowing.\"So be it. But know, this sword carries a shadow of my curse.\"");
            } else {
                System.out.println("You decide against taking the sword. The vampires shadowy form watches you silently before fading back into the darkness.");
            }
        } else if (choice.equals("2")) {
            System.out.println("\nYou take a cautious step back. The vampire tilts his head, seeming almost amused.");
            System.out.println("\"Caution...perhaps wise.\"");
        } else if (choice.equals("3")) {
            System.out.println("\nYou prepare to fight!");
            System.out.println("\"I have better things to do than to fight you child. Leave.\"");
            System.out.println("\nYou quickly step back.");
        } else {
            System.out.println("\nHesitation hangs in the air. The vampire studies you before retreating into the shadows.");
        }
        System.out.println("\n** Slipping out through the crack **");
        next_path_choice();
    }

    //----------------------------
    // NEXT PATHWAY CHOICE FUNCTION
    public static void next_path_choice() {
        System.out.println("\nAs you keep walking you come across a fork in your path.");
        System.out.println("Which way do you wish to go?");
        System.out.println("1. Left\n2. Right");
        System.out.print("> ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            bone_pit();
        } else if (choice.equals("2")) {
            spider_nest();
        } else {
            System.out.println("Wrong way..you bump into a wall. Think before you walk...");
            next_path_choice();
        }
    }

    //------------------
    // BONE PIT FUNCTION
    public static void bone_pit() {
        System.out.println("\nShuffling through the dark you come across a BONE PIT.");
        System.out.println("\nBones crunch under your feet.");
        if (skeleton_helpful) {
            System.out.println("Suddenly the skeleton from before appears and points out a safer path through the pit.");
            System.out.println("** YOU AVOIDED TRAPS **");
        } else {
            System.out.println("Carelessly stepping over them, you trigger a trap.");
            System.out.println("\n** DAMAGE TAKEN **");
            health -= 2;
            System.out.println("Health: " + health);
            System.out.println("\nSpotting the skeleton from before, you watch its bones rattle as it laughs at you.");
        }
        System.out.println("\nAs you keep walking you notice a shiny lockpick on the floor. You pick it up.");
        has_lockpick = true;
        System.out.println("\n** LOCK PICK ADDED TO INVENTORY **");
        alchemy_room();
    }

    //---------------------
    // SPIDER NEST FUNCTION
    public static void spider_nest() {
        System.out.println("\nShuffling through the dark you realise youve walked into a SPIDERS NEST.");
        System.out.println("Raising your torch, you spot a giant spider.");
        System.out.println("Surprisingly, the spider speaks..\n\"Greetings Traveller. Do you wish to answer my riddle?\"");
        System.out.println("1.Yes\n2.No");
        System.out.print("> ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.println("\"Wonderful! Bill The Skeleton doesnt really talk, he more so just judges so its nice to have someone who can reply.");
            System.out.println("Answer the riddle Traveller. I speak without a mouth and hear without ears. What am I?\"");
            System.out.print("> ");
            String answer = input.nextLine().toLowerCase();

            if (answer.contains("echo")) {
                System.out.println("\"Correct! As a reward some advice, the ALCHEMY ROOM holds a key. Goodluck now!\"");
                has_key = true;
            } else {
                System.out.println("\"Wrong. Thats too bad. Off you go traveller, Im due for book club, Bill doesnt like to wait.\"");
            }
        } else {
            System.out.println("\"Thats too bad. Off you go traveller, Im due for book club, Bill The Skeleton doesnt like to wait.\"");
        }
        alchemy_room();
    }

    //---------------------
    // ALCHEMY ROOM FUNCTION
    public static void alchemy_room() {
        System.out.println("\n** TIME SKIP **");
        System.out.println("You reach the ALCHEMY ROOM. Even though no one seems to be present, bottles bubble and smoke.");
        
        // FIXED LOGIC: If you did not get the key from the riddle, you can still search and find it here!
        if (!has_key) {
            System.out.println("\nYou search the room, finding a key on a shelf. This will aid your escape.");
            System.out.println("** KEY ADDED TO INVENTORY **");
            has_key = true;
        } else {
            System.out.println("\nYou search the room but find nothing extra useful.");
            System.out.println("** PAST CHOICES HAVE AFFECTED YOUR SEARCH **");
        }
        
        System.out.println("\nAs you walk towards a door you spot a sign pointing towards the exit of the dungeon.");
        System.out.println("As you go to leave you spot a glowing bottle filled with a neon coloured liquid.\n\nPicking it up, do you..");
        System.out.println("1. Drink it\n2. Leave it");
        System.out.print("> ");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            System.out.println("\nYour hair turns bright neon..it seems to be lighting up your path...");
            System.out.println("You put out your torch and continue towards the exit with giddy steps.");
            has_potion = true;
        } else {
            System.out.println("\nLeaving it, you continue towards the exit.");
        }
        final_boss();
    }

    //-------------------
    // FINAL BOSS FUNCTION
    public static void final_boss() {
        int beast_health = 40;
        int beast_attack = 6;

        System.out.println("\nExcited on finally knowing a way out you dont notice the sudden drop in temperature.");
        System.out.println("A giant beast emerges from the shadows, the wind picks up and wisps of shadows encompass your surroundings.");
        System.out.println("The beast lets out an ear shattering roar, the walls of the dungeon rattling.");

        System.out.println("\n** FINAL BATTLE HAS BEGUN **");
        System.out.println("\nBefore the beast strikes, you steady yourself.");
        System.out.println("== 5 HP regained ==");
        health += 5;

        if (has_potion) {
            System.out.println("Suddenly the potion inside you glows brightly!");
            System.out.println("== potion restores 10 HP ==");
            health += 10;
        }

        while (beast_health > 0 && health > 0) {
            System.out.println("\n** Your Stats **");
            System.out.println("Health: " + health);
            System.out.println("Attack: " + attack);

            System.out.println("** Shadow Beasts Stats **");
            System.out.println("Health: " + beast_health);
            System.out.println("Attack: " + beast_attack);

            System.out.println("\n1.Attack\n2.Defend");
            System.out.print("> ");
            String action = input.nextLine();

            if (action.equals("1")) {
                System.out.println("\nYou attack!");
                beast_health -= attack;
                if (random.nextDouble() < 0.3) {
                    System.out.println("You dodge the Beasts attack!\nNo damage taken!");
                } else {
                    System.out.println("\nThe beast strikes!");
                    health -= beast_attack;
                }
                if (beast_health <= 0) {
                    System.out.println("** BEAST DEFEATED **");
                    break;
                }
            } else if (action.equals("2")) {
                System.out.println("\nYou defend!");
                int reduced = beast_attack / 3;
                health -= reduced;
                int counter = attack / 2;
                beast_health -= counter;
                System.out.println("You took reduced damage: " + reduced);
                System.out.println("But countered with an attack of " + counter);
                if (beast_health <= 0) {
                    System.out.println("** BEAST DEFEATED **");
                    break;
                }
            } else {
                System.out.println("\nHesitation Traveller! It will get you killed..");
                System.out.println("The beast attacks you with full force!");
                health -= beast_attack;
            }
        }

        if (health <= 0) {
            System.out.println("\nYour strength falters..the beast towers over you.");
            System.out.println("As your final sparks of hope fade, so do you..");
            System.out.println("\nThe dungeon grows silent once more\nA nameless traveler lost to its shadows.");
            System.out.println("\nYou didnot survive this quest traveller i truly hoped you would have.");
            System.out.println("\n** QUEST FAILED **");
            System.out.println("==== ending : extinguished by darkness ====");
            System.out.println("** GAME OVER **");
            System.exit(0);
        }
        exit_door();
    }

    //-------------------
    // EXIT ROUTE FUNCTION
    public static void exit_door() {
        System.out.println("After defeating the Shadow Beast, you reach a massive door.");

        if (has_key || has_lockpick) {
            System.out.println("\nYou notice the door has a complex lock.");
            if (has_key) {
                System.out.println("Do you wish to try the key you found?\n1.Yes\n2.No");
                System.out.print("> ");
                String choice = input.nextLine();
                if (choice.equals("1")) {
                    System.out.println("Click! The door swings open..");
                    System.out.println("\n** QUEST COMPLETED : YOU ESCAPED **");
                    System.out.println("==== good ending achieved ====");
                    System.exit(0);
                } else {
                    System.out.println("You step back from the door, thinking about other options.");
                }
            }

            if (has_lockpick) {
                System.out.println("Do you wish to try using the lockpick you found?\n1.Yes\n2.No");
                System.out.print("> ");
                String choice = input.nextLine();
                if (choice.equals("1")) {
                    System.out.println("\nYou carefully work...");
                    System.out.println("Click! The door swings open..");
                    System.out.println("\n** QUEST COMPLETED : YOU ESCAPED **");
                    System.out.println("==== good ending achieved ====");
                    System.exit(0);
                } else {
                    System.out.println("You step back from the door, thinking about other options.");
                }
            }
        }

        if (has_diamond_sword && health > 10) {
            System.out.println("\nYou feel your Diamond Sword pulse with energy...");
            System.out.println("Do you wish to attempt smashing the door?\n1.Yes\n2.No");
            System.out.print("> ");
            String choice = input.nextLine();
            if (choice.equals("1")) {
                boolean success = random.nextBoolean();
                if (success) {
                    System.out.println("With all the strength you have left, you smash the door wide open!");
                    System.out.println("\n** QUEST COMPLETED : YOU ESCAPED **");
                    System.out.println("==== alternate ending achieved : escape through strength ====");
                    System.exit(0);
                } else {
                    System.out.println("\nThe door holds firm. The impact leaves you exhausted...");
                    System.out.println("Darkness surrounds you as the dungeon swallows your hopes to escape...");
                    System.out.println("Goodbye Traveler...I truly did hope your ending was better.");
                    System.out.println("\n** QUEST FAILED **");
                    System.out.println("==== bad ending achieved ====");
                    System.exit(0);
                }
            }
        }

        System.out.println("\nWithout any means to open the door, you remain trapped...");
        System.out.println("Darkness surrounds you as the dungeon swallows your hopes to escape...");
        System.out.println("Goodbye Traveler...I truly did hope your ending was better.");
        System.out.println("\n** QUEST FAILED **");
        System.out.println("==== bad ending achieved ====");
        System.exit(0);
    }
}