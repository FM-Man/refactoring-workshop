package workshop.trivia;

public class Player {
    private final String name;
    private int position = 0;
    private int coins = 0;
    private boolean inPenalty = false;

    public Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public int position() {
        return position;
    }

    public void move(int roll) {
        position += roll;
        position %= 12;
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
