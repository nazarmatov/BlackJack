package BlackJack.Players;
import java.util.ArrayList;

import BlackJack.Game;
import BlackJack.GameComponents.Koloda.Cart;
import BlackJack.GameComponents.Koloda.Koloda;
import java.util.Scanner;

public class Player {
    Scanner scan = new Scanner(System.in);
    private final Koloda koloda;
    private Game game;
    ArrayList<Cart> hand = new ArrayList<>();
    private int score = 0;
    private int bank = 1000;
    int bid;

    public Player(Koloda koloda){
        this.koloda = koloda;
        
    }

    public void addCart(){
            koloda.getCart(koloda.getKoloda(), hand, 0);
    }

    public int getScore(){
        score = 0;
        int aceCount = 0;

        for(int i=0;i<hand.size();i++){
            score += hand.get(i).getValue();
            if(hand.get(i).getRank().equals("ace")){
                aceCount++;
            }
        }

        while(score>21&&aceCount>0){
            for(int i=0;i<hand.size();i++){
                if(hand.get(i).getRank().equals("ace")&&hand.get(i).getValue()==11){
                    hand.get(i).setAceValue(1);
                    score-=10;
                    aceCount--;
                    break;
                }
            }
        }
        return score;
    }
    public void setBid(){
        if(bank>0){
            bid = 0;
            while (bid == 0&&bid<bank) {
                System.out.println("                            "+"cash: {"+bank+"}");
                System.out.println("please place a bet: ");
                bid = scan.nextInt();
                if (bid<bank||bid==bank) {
                    bank -=bid;
                }else{
                    clearScreen();
                    System.err.println("You cannot place a bet more then "+bank);
                }
            }
        }else{
                game.isGameOver = true;
        }
    }
    public void showHand(){
        System.out.println("            "+hand);
    }
    public void winBet() {
        bank += 1.5 * bid;
    }
    public ArrayList<Cart> getHand(){
        return hand;
    }
    public int getBank(){
        return bank;
    }
    public static void clearScreen() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();   
    } 
}
