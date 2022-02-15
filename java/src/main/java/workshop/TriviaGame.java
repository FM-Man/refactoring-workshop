package workshop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


class Player{
    private final String name;
    private int place = 0;
    private int purse = 0;

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
}

public class TriviaGame {
    ArrayList<Player> players = new ArrayList<>();
    boolean[] inPenaltyBox = new boolean[6];

    List<String> popQuestions = new LinkedList<>();
    List<String> scienceQuestions = new LinkedList<>();
    List<String> sportsQuestions = new LinkedList<>();
    List<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add(("Science Question " + i));
            sportsQuestions.add(("Sports Question " + i));
            rockQuestions.add(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean add(String playerName) {
        Player player = new Player(playerName);
        players.add(player);

        inPenaltyBox[players.size()] = false;

        print(playerName + " was added");
        print("They are player number " + players.size());
        return true;
    }

    public void roll(int roll) {
        print(currentPlayer().name() + " is the current player");
        print("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                print(currentPlayer().name() + " is getting out of the penalty box");

                movePlayerPlace(roll);
                announcePlayerLocationQuestion();

            } else {
                print(currentPlayer().name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            movePlayerPlace(roll);
            announcePlayerLocationQuestion();
        }

    }

    private void announcePlayerLocationQuestion() {
        print(currentPlayer().name()
                + "'s new location is "
                + currentPlayer().place());
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
        switch (currentCategory()) {
            case "Pop":
                return popQuestions.remove(0);
            case "Science":
                return scienceQuestions.remove(0);
            case "Sports":
                return sportsQuestions.remove(0);
            case "Rock":
                return rockQuestions.remove(0);
            default:
                throw new IllegalStateException("Unexpected value: " + currentCategory());
        }
    }


    private String currentCategory() {
        if (currentPlayer().place() == 0) return "Pop";
        if (currentPlayer().place() == 4) return "Pop";
        if (currentPlayer().place() == 8) return "Pop";
        if (currentPlayer().place() == 1) return "Science";
        if (currentPlayer().place() == 5) return "Science";
        if (currentPlayer().place() == 9) return "Science";
        if (currentPlayer().place() == 2) return "Sports";
        if (currentPlayer().place() == 6) return "Sports";
        if (currentPlayer().place() == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                return correctAnswerActions();
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }
        } else {
            return correctAnswerActions();
        }
    }

    private boolean correctAnswerActions() {
        print("Answer was correct!!!!");
        currentPlayer().incrementPurse();
        print(currentPlayer().name()
                + " now has "
                + currentPlayer().purse()
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return winner;
    }

    public boolean wrongAnswer() {
        print("Question was incorrectly answered");
        print(currentPlayer().name() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(currentPlayer().purse() == 6);
    }

    protected void print(Object message) {
        System.out.println(message);
    }
}