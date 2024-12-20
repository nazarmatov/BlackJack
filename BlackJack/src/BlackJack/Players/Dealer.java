package BlackJack.Players;

import java.util.ArrayList;

import BlackJack.GameComponents.Koloda.Cart;
import BlackJack.GameComponents.Koloda.Koloda;

public class Dealer {
    ArrayList<Cart> hand = new ArrayList<>();
    private int score = 0;
    private final Koloda koloda;

    public Dealer(Koloda koloda){
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
    public ArrayList<Cart> getHand(){
        return hand;
    }
    public void showHand(){
        System.out.println("            "+hand);
    }
}
