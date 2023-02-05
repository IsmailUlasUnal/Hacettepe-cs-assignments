public class NoMatchException extends RuntimeException{
    //right now this exception is useless but if we want to print error we can do it by using this exception
    public NoMatchException(){
        //
    }

    public NoMatchException(String message){
        super(message);
    }
}
