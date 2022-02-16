package workshop.trivia;

public class Player {
    private final String name;
    private int place = 0;
    private int coins = 0;
    private boolean inPenalty = false;

    public Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public int place() {
        return place;
    }

    public void move(int roll) {
        place += roll;
        place %= 12;
    }

    public int coin() {
        return coins;
    }

    public void addCoin() {
        coins++;
    }

    public boolean inPenalty() {
        return inPenalty;
    }

    public void invertPenalty() {
        inPenalty = !inPenalty;
    }
}
