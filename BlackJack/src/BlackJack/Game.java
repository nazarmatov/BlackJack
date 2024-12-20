package BlackJack;

import BlackJack.GameComponents.Koloda.Koloda;
import BlackJack.Players.Dealer;
import BlackJack.Players.Player;

import java.util.Scanner;

public class Game {
    private final Koloda koloda;
    private Dealer dealer;
    private Player player;
    public boolean isGameOver = false;
    private boolean isPlayerTurn = true;
    Scanner scan = new Scanner(System.in);

    public Game() {
        koloda = new Koloda();
        dealer = new Dealer(koloda);
        player = new Player(koloda);        

        startGame();
        finishGame();
    }

    private void startGame() {
        clearScreen();
        System.out.println("--- Welcome to BlackJack! ---");
        while (!isGameOver) {
            startRound();
            playerTurn();

        }
    }

    private void startRound() {
        if (player.getBank()!=0) {
            player.setBid();
        }else{
            isGameOver = true;
        }
        clearScreen();
        System.out.println("--- Starting a new round ---\n");
        player.addCart();
        player.addCart();
        dealer.addCart();
        dealer.addCart();

        System.out.println("Your starting hand: ");
        player.showHand();
        System.out.println("Dealer's cards: ");
        dealer.showHand();
    }

    private void playerTurn(){
        while (isPlayerTurn) {
            System.out.println("\nYour current score:   ["+player.getScore()+"]"+"\nWould you like to (1) Hit or (2) Stand?");
            int chice = scan.nextInt();
            scan.nextLine();

            if (chice==1) {
                clearScreen();
                player.addCart();
                System.out.println("Your hand:");
                player.showHand();
                if (player.getScore()>21) {
                    gameResult();
                    isPlayerTurn = false;
                }
            }else if (chice==2) {
                dealerTurn();
                isPlayerTurn = false;
            }else{
                clearScreen();
                System.err.println("Invalid input. Please choose (1) Hit or (2) Stand\n");
            }
        }
    }

    private void dealerTurn(){
        clearScreen();
        System.out.println("Dealer's turn");
        dealer.addCart();

        while (dealer.getScore()<17) {
            dealer.addCart();
        }
        if (dealer.getScore()>21) {
            gameResult();
        }else{
            dealer.showHand();
            System.out.println("dealer stands with score: "+dealer.getScore());
            gameResult();
        }
    }

    private void gameResult(){
        clearScreen();
        if (player.getScore()>21) {
            System.out.println("---- Dealer wins ----\n");
            showHands();
        }else{
            if (dealer.getScore()>21) {
                System.out.println("---- You win ----\n");
                showHands();
                player.winBet();
            }else{
                if (player.getScore()>dealer.getScore()) {
                    System.out.println("---- You win ----\n");
                    showHands();
                }else if (player.getScore()==dealer.getScore()) {
                    System.out.println("---- Friendship wins ----");
                    showHands();
                }
            }
        }
        isPlayerTurn = true;
        if (player.getBank()==0) {
            isGameOver=true;
        }else{
            askForNewRound();
        }
        
    }

    private void askForNewRound() {
        System.out.println("\nWould you like to play another round? (yes/no)");
        String input = scan.nextLine();

        if (input.equalsIgnoreCase("no")) {
            isGameOver = true;
        } else {
            resetHands();
        }
    }

    private void showHands(){
        System.out.println("your hand: ");
        player.showHand();
        System.out.println("\ndealer hand: ");
        dealer.showHand();
    }

    private void resetHands() {
        dealer.getHand().clear();
        player.getHand().clear();
    }
    public static void clearScreen() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();   
    } 
    public void finishGame(){
        System.out.println("\n--- Thank you for playing BlackJack! ---");
        System.out.println("Goodbye!");
    }
}
