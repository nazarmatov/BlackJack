package BlackJack.GameComponents.Koloda;

public class Cart {
    private String rank;
    private String kind;
    private int value;

    public Cart(String kind,String rank,int value){
        this.kind = kind;
        this.rank = rank;
        this.value = value;
    }

    public String getRank() {
        return rank;
    }
    public String getKind(){
        return kind;
    }
    public int getValue(){
        return value;
    }

    public String toString(){
        return rank+" of "+kind;
    }
    public void setAceValue(int n){
        if(rank.equals("ace")&&(n==1||n==11)){
            this.value = n;
        }else{
            throw new IllegalArgumentException("1 or 11(for ace)");
        }
    }
}
