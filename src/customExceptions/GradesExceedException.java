package customExceptions;

public class GradesExceedException extends Exception{
    public GradesExceedException(double p){
        super("You exceed the 50% of your grades, you can't cancel. Your % on grades is: "+p+"%");
    }
}
