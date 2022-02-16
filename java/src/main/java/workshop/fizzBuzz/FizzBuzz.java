package workshop.fizzBuzz;

import java.util.ArrayList;
import java.util.List;

/**
 * Requirements:
 * For factor of three print Fizz instead of the number
 * For factor of five print Buzz instead of the number
 * For numbers which are factors of both three and five print fizzBuzz instead of the number
 */
public class FizzBuzz {
    private final List<PatternMatcher> patternMatchers;

    public FizzBuzz(){
        PatternMatcher fizz = new FizzPatternMatcher();
        PatternMatcher buzz = new BuzzPaternMatcher();
        patternMatchers = new ArrayList<>();
        patternMatchers.add(fizz);
        patternMatchers.add(buzz);
    }

    public String say(int number) {
        boolean patternMatched = false;
        String returnString ="";
        for (PatternMatcher pattern: patternMatchers) {
            if (pattern.matches(number)){
                returnString += pattern.generateResponse();
                patternMatched = true;
            }
        }
        if(patternMatched) return String.valueOf(number);
        else return returnString;
    }
}
