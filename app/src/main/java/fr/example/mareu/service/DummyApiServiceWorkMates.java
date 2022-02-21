package fr.example.mareu.service;

import java.util.List;
import fr.example.mareu.model.Workmate;

public class DummyApiServiceWorkMates implements ApiServiceWorkMate {

    private List<Workmate> workmates = DummyWorkMatesGenerator.generateWorkmate();

    @Override
    public List<Workmate> getWorkmateList() {
        return workmates;
    }
}
