package workshop.trivia;

import java.util.*;

public class Questions {
    private final Map<String, List<String>> questions = new LinkedHashMap<>();

    public Questions() {
        questions.put("Pop", new ArrayList<>());
        questions.put("Science", new ArrayList<>());
        questions.put("Sports", new ArrayList<>());
        questions.put("Rock", new ArrayList<>());

        for (int i = 0; i < 50; i++) {
            questions.get("Pop").add("Pop Question " + i);
            questions.get("Science").add(("Science Question " + i));
            questions.get("Sports").add(("Sports Question " + i));
            questions.get("Rock").add("Rock Question " + i);
        }
    }

    public String nextQuestion(int playerPosition) {
        switch (playerPosition % 4) {
            case 0: return questions.get("Pop").remove(0);
            case 1: return questions.get("Science").remove(0);
            case 2: return questions.get("Sports").remove(0);
            default: return questions.get("Rock").remove(0);
        }
    }

    public String currentCategory(int playerPosition){
        switch (playerPosition%4){
            case 0: return "Pop";
            case 1: return "Science";
            case 2: return "Sports";
            default: return "Rock";
        }
    }
}
