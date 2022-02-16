package workshop.trivia;

import java.util.ArrayList;
import java.util.List;


public class TriviaGame {
    private final List<Player> players = new ArrayList<>();
    private final Questions questions;

    int currentPlayer = 0;

    public TriviaGame() {
        questions = new Questions();
    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        players.add(player);

        print(playerName + " was added");
        print("They are player number " + players.size());
    }

    public void roll(int roll) {
        print(currentPlayer().name() + " is the current player");
        print("They have rolled a " + roll);

        if (currentPlayer().inPenalty()) {
            if (roll % 2 != 0) {
                print(currentPlayer().name() + " is getting out of the penalty box");
                currentPlayer().invertPenalty();
                currentPlayer().move(roll);
                print_playerName_place_question();
            } else {
                print(currentPlayer().name() + " is not getting out of the penalty box");
            }
        } else {
            currentPlayer().move(roll);
            print_playerName_place_question();
        }
    }

    private void print_playerName_place_question() {
        print(currentPlayer().name() + "'s new location is " + currentPlayer().place());
        String currentCategory;
        switch (currentPlayer().place() % 4) {
            case  0: currentCategory = "Pop"; break;
            case  1: currentCategory = "Science"; break;
            case  2: currentCategory = "Sports"; break;
            default: currentCategory = "Rock";
        }
        print("The category is " + currentCategory);
        print(questions.nextQuestion(currentPlayer().place()));
    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    public void correctAnswer() {
        if (!currentPlayer().inPenalty()) {
            print("Answer was correct!!!!");
            currentPlayer().addCoin();
            print(currentPlayer().name() + " now has " + currentPlayer().coin() + " Gold Coins.");
        }
        nextPlayer();
    }

    public void wrongAnswer() {
        print("Question was incorrectly answered");
        print(currentPlayer().name() + " was sent to the penalty box");
        currentPlayer().invertPenalty();
        nextPlayer();
    }

    private void nextPlayer() {
        currentPlayer++;
        currentPlayer %= players.size();
    }

    protected void print(Object message) {
        System.out.println(message);
    }
}