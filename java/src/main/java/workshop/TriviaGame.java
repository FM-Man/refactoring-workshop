package workshop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


class Player{
    private final String name;
    private int place = 0;
    private int purse = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String name(){
        return name;
    }

    public int place() {
        return place;
    }

    public void changePlace(int roll) {
        place += roll;
        place %= 12;
    }

    public int purse() {
        return purse;
    }

    public void incrementPurse() {
        purse++;
    }

    public boolean inPenaltyBox(){
        return inPenaltyBox;
    }

    public void switchPenaltyBox(){
        inPenaltyBox = !inPenaltyBox;
    }
}

public class TriviaGame {
    List<Player> players = new ArrayList<>();

    List<String> popQuestions = new LinkedList<>();
    List<String> scienceQuestions = new LinkedList<>();
    List<String> sportsQuestions = new LinkedList<>();
    List<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;

    public TriviaGame() {
        for (int i = 0; i < 50; i++) {
            createQuestion(i);
        }
    }

    private void createQuestion(int i) {
        popQuestions.add("Pop Question " + i);
        scienceQuestions.add(("Science Question " + i));
        sportsQuestions.add(("Sports Question " + i));
        rockQuestions.add("Rock Question " + i);
    }

    public void add(String playerName) {
        Player player = new Player(playerName);
        players.add(player);

        print(playerName + " was added");
        print("They are player number " + players.size());
    }

    public void roll(int roll) {
        print(currentPlayer().name() + " is the current player");
        print("They have rolled a " + roll);

        if (currentPlayer().inPenaltyBox()) {
            if (roll % 2 != 0) {
                print(currentPlayer().name() + " is getting out of the penalty box");
                currentPlayer().switchPenaltyBox();
                movePlayerPlace(roll);
                announcePlayerLocationQuestion();
            } else {
                print(currentPlayer().name() + " is not getting out of the penalty box");
            }
        } else {
            movePlayerPlace(roll);
            announcePlayerLocationQuestion();
        }
    }

    private void announcePlayerLocationQuestion() {
        print(currentPlayer().name() + "'s new location is " + currentPlayer().place());
        print("The category is " + currentCategory());
        print(nextQuestion());
    }

    private void movePlayerPlace(int roll) {
        currentPlayer().changePlace(roll);
    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    private String nextQuestion() {
        switch (currentPlayer().place() % 4) {
            case 0: return popQuestions.remove(0);
            case 1: return scienceQuestions.remove(0);
            case 2: return sportsQuestions.remove(0);
            default: return rockQuestions.remove(0);
        }
    }

    private String currentCategory() {
        switch (currentPlayer().place() % 4) {
            case 0: return "Pop";
            case 1: return "Science";
            case 2: return "Sports";
            default: return "Rock";
        }
    }

    public void correctAnswer() {
        if (currentPlayer().inPenaltyBox()) {
            incrementCurrentPlayer();
        } else {
            correctAnswerActions();
        }
    }

    private void correctAnswerActions() {
        print("Answer was correct!!!!");
        currentPlayer().incrementPurse();
        print(currentPlayer().name() + " now has " + currentPlayer().purse() + " Gold Coins.");
        incrementCurrentPlayer();
    }

    public void wrongAnswer() {
        print("Question was incorrectly answered");
        print(currentPlayer().name() + " was sent to the penalty box");
        currentPlayer().switchPenaltyBox();
        incrementCurrentPlayer();
    }

    private void incrementCurrentPlayer() {
        currentPlayer++;
        currentPlayer %= players.size();
    }

    protected void print(Object message) {
        System.out.println(message);
    }
}