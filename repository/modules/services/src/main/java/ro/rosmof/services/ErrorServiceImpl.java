package ro.rosmof.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rosmof.model.entities.Error;
import ro.rosmof.model.repositories.ErrorRepository;

import javax.transaction.Transactional;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

@Service
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class ErrorServiceImpl implements ErrorService {

    @Autowired
    private ErrorRepository repository;


    @Override
    public void saveErrorWithNewTransaction(Exception ex) {
        Error error = new Error();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        error.setMessage(sw.toString());
        error.setDate(Calendar.getInstance());

        try {
            repository.save(error);
        } catch (Exception e) {
            System.out.println("XXXXXXX");
        }
    }
}
