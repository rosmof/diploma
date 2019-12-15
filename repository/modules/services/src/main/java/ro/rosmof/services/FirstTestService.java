package ro.rosmof.services;

import org.springframework.stereotype.Service;
import ro.rosmof.model.TestModel;

@Service
public class FirstTestService implements FirstServiceInterface {
    @Override
    public TestModel getTestModel() {
        TestModel model = new TestModel();
        model.setId(123);
        model.setName("Vijelie");
        return model;
    }
}
