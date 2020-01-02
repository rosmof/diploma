package ro.rosmof.services;

public interface ErrorService {
    void saveErrorWithNewTransaction(Exception e);
}
