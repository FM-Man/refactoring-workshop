package workshop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


class Player{
    private final String name;
    private int place = 0;

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
}

public class TriviaGame {
    ArrayList<Player> players = new ArrayList<>();
    int[] purses = new int[6];
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

        purses[players.size()] = 0;
        inPenaltyBox[players.size()] = false;

        announce(playerName + " was added");
        announce("They are player number " + players.size());
        return true;
    }

    public void roll(int roll) {
        announce(currentPlayer().name() + " is the current player");
        announce("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                announce(currentPlayer().name() + " is getting out of the penalty box");
                movePlayerPlace(roll);

                announce(currentPlayer().name()
                        + "'s new location is "
                        + currentPlayer().place());
                announce("The category is " + currentCategory());
                announce(nextQuestion());
            } else {
                announce(currentPlayer().name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            movePlayerPlace(roll);

            announce(currentPlayer().name()
                    + "'s new location is "
                    + currentPlayer().place());
            announce("The category is " + currentCategory());
            announce(nextQuestion());
        }

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
                announce("Answer was correct!!!!");
                purses[currentPlayer]++;
                announce(currentPlayer().name()
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            announce("Answer was correct!!!!");
            purses[currentPlayer]++;
            announce(currentPlayer().name()
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        announce("Question was incorrectly answered");
        announce(currentPlayer().name() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}