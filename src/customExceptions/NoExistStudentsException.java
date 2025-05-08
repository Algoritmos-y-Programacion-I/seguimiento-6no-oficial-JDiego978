package customExceptions;

public class NoExistStudentsException extends Exception{
    public NoExistStudentsException(){
        super("No exist students");
    }
}
