/*
 * Zombie
 *
 * This class is derived from Character.  It is a NPC (Non-Player Character).
 *
 * With a 50% chance the zombie will move toward the first (and only) player 
   passed in the opponents array and always attack the player if adjacent. If the
   zombie is not adjacent and not moving toward the player, then for the other 50% of 
   the moves, the zombie will not move at all.
 */
package rpgoop;

import java.util.Random;

public class Zombie extends Character {
    private static int DAMAGE = 2; // Does 0 to 2 damage
    public Zombie()
    {
        super("",2,2,20);
    }
    public Zombie(int x, int y)
    {
        super("zombie",x,y,20);
    }
    
    @Override
    public char getDisplayChar() {
        return('Z');
    }
    
 
    // Attack any adjacent opponent
    private void attackOpponent(Character[] opponents)
    {
        Random rnd = new Random();
        for (Character opponent : opponents)
        {
            if (((opponent.x-1 == x) && (opponent.y == y)) ||
                ((opponent.x+1 == x) && (opponent.y == y)) ||
                ((opponent.x == x) && (opponent.y-1 == y)) ||
                ((opponent.x == x) && (opponent.y+1 == y)))                  
            {
                int damage = rnd.nextInt(DAMAGE);
                System.out.println(name + " attacks " + opponent.name + " for " + damage + " damage!");
                opponent.hp -= damage;
                // We end the game immediately if a player dies
                if (opponent.hp <= 0)
                {
                    System.out.println(opponent.name + " is dead! Game over, you lose!");
                    System.exit(0);
                }
            }
        }
    }
    private void tryMove(Room room, int newX, int newY, Character[] opponents)
    {
        // Check if we are moving to a new room, if so don't go there
        Room nextRoom = room.isNewRoom(newX, newY);
        if (nextRoom != null)
           return;
        
        
        // Move if we don't hit a wall
        if (!room.isWall(newX, newY))
        {
            x = newX;
            y = newY;
        }
    }

    @Override
    public Room makeMove(Room room, Character[] opponents) {
        Random rnd = new Random();
        int i = rnd.nextInt(2);
        switch (i) {
            case 0:
                for (Character opponent : opponents) {
                    if ((opponent.x < x) && (opponent.y < y)){
                            tryMove(room,this.x-1,this.y-1, opponents);
                            attackOpponent(opponents);
                    }
                    else if((opponent.x > x) && (opponent.y > y)){
                            tryMove(room,this.x+1,this.y+1, opponents);
                            attackOpponent(opponents);
                    }
                    else if ((opponent.x < x) && (opponent.y > y)){
                            tryMove(room,this.x-1,this.y+1, opponents);
                            attackOpponent(opponents);
                    }
                    else if ((opponent.x > x) && (opponent.y < y)){
                            tryMove(room,this.x+1,this.y-1, opponents);
                            attackOpponent(opponents);
                    }      
                    else if ((opponent.x == x) && (opponent.y > y)){
                            tryMove(room,this.x,this.y+1, opponents);
                            attackOpponent(opponents);
                    }
                    else if ((opponent.x == x) && (opponent.y < y)){
                            tryMove(room,this.x,this.y-1, opponents);
                            attackOpponent(opponents);
                    }
                    else if ((opponent.x < x) && (opponent.y == y)){
                            tryMove(room,this.x-1,this.y, opponents);
                            attackOpponent(opponents);
                    }
                    else if ((opponent.x > x) && (opponent.y == y)){
                            tryMove(room,this.x+1,this.y, opponents);
                            attackOpponent(opponents);
                    }
                    else
                break;
            }
            case 1:
                attackOpponent(opponents);
                break;
        }
        return null;
    }    
}
