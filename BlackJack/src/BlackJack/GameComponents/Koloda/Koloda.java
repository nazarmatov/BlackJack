package BlackJack.GameComponents.Koloda;

import java.util.ArrayList;
import java.util.Collections;

public class Koloda {
    private ArrayList<Cart> carts = new ArrayList<>();
    public Koloda(){
        String[] rank = {"ace", "king", "queen", "jack", "ten", "nine", "eight", "seven", "six", "five", "four","three", "two"};
        String[] kind = {"heart","club","spade","diamond"};
        int[] value = {11,10,10,10,10,9,8,7,6,5,4,3,2};
        for(int l=0;l<kind.length;l++){
            for(int i=0;i<rank.length;i++){
                carts.add(new Cart(kind[l], rank[i], value[i]));
            }
        }
        Collections.shuffle(carts);
    }

    public void getCart(ArrayList<Cart> source, ArrayList<Cart> target,int index){
        Cart cart = source.get(index);
        target.add(cart);
        source.remove(index);
    }
    public ArrayList<Cart> getKoloda(){
        return carts;
    }
    
}
