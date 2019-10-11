package by.itacademy.restaurant.dao.pool;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(){
        super();
    }

    public ConnectionPoolException(Exception e){
        super(e);
    }

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(String message, Exception e){
        super(message, e);
    }


}
