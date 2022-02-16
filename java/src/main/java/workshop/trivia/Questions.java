package workshop.trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Questions {
    private final Map<String, List<String>> questionSet = new HashMap<>();

    public Questions() {
        questionSet.put("Pop", new ArrayList<>());
        questionSet.put("Science", new ArrayList<>());
        questionSet.put("Sports", new ArrayList<>());
        questionSet.put("Rock", new ArrayList<>());

        for (int i = 0; i < 50; i++) {
            questionSet.get("Pop").add("Pop Question " + i);
            questionSet.get("Science").add(("Science Question " + i));
            questionSet.get("Sports").add(("Sports Question " + i));
            questionSet.get("Rock").add("Rock Question " + i);
        }
    }

    public String nextQuestion(int playerPlace) {
        switch (playerPlace % 4) {
            case 0:
                return questionSet.get("Pop").remove(0);
            case 1:
                return questionSet.get("Science").remove(0);
            case 2:
                return questionSet.get("Sports").remove(0);
            default:
                return questionSet.get("Rock").remove(0);
        }
    }
}
