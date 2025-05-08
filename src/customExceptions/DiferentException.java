package customExceptions;

public class DiferentException extends Exception {
    public DiferentException(double min, double max){
        super("Cant apply because Max grade can't be less than min grade");
    }
}
