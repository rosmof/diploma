package ro.rosmof.services;

public class DatabaseServiceProxy implements DatabaseServiceInterface {
    private static DatabaseServiceInterface ds;

    public DatabaseServiceProxy(DatabaseServiceInterface ds) {
        DatabaseServiceProxy.ds = ds;
    }

    public void process() {
        System.out.println("calling proxy class");
        ds.process();
    }
}
