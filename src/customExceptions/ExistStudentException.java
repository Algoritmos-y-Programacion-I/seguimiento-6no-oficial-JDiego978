package customExceptions;

public class ExistStudentException extends Exception{
    public ExistStudentException(String i){
        super("The student with id "+i+" already exist");
    }
}