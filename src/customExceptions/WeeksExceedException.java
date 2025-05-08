package customExceptions;

public class WeeksExceedException extends Exception {
    public WeeksExceedException(int w){
        super("You already pass the 2 weeks when you can matriculate, you are on week: " + w);
    }
}
