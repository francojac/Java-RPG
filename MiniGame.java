/*
 * Mini Game Item
 *
 * This item blocks entry. It must be placed at a specific x/y coordinate.
 * Entry is blocked unless a trivia question is properly answered.  The question and
 * answer are set in the constructor.
 */
package rpgoop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kenrick
 */
public class MiniGame extends Item {
    private String question;
    private String answer;
    
    public MiniGame()
    {
        super();
    }
    
    public MiniGame(String name, int x, int y, String question, String answer)
    {
        super(name, x, y);
        this.answer = answer;
        this.question = question;
    }
    
    @Override
    public char getDisplayChar()
    {
        return 'M';
    }

    @Override
    public boolean conditionsSatisfied(ArrayList<Item> items) {
        System.out.println("You found the mini game! In order to advance to the next room, you must answer a trivia question.");
        System.out.println(question);
        Scanner kbd = new Scanner(System.in);
        String response = kbd.nextLine();
        if (response.equalsIgnoreCase(answer))
        {
            System.out.println("Correct!");
            return true;
        }
        System.out.println("Try again.");
        return false;
    }    
    
    @Override
    public boolean specialItem()
    {
        return true;
    }   
}
