package ro.rosmof.services;

public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
}
