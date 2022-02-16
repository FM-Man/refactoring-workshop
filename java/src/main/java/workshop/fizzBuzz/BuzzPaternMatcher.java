package workshop.fizzBuzz;

public class BuzzPaternMatcher implements PatternMatcher{
    @Override
    public boolean matches(int number) {
        return number % 5 == 0;
    }

    @Override
    public String generateResponse() {
        return "Buzz";
    }
}
