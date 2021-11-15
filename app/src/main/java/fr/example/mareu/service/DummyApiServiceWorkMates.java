package fr.example.mareu.service;


import java.util.List;
import java.util.Map;

import fr.example.mareu.model.Workmate;

public class DummyApiServiceWorkMates implements ApiServiceWorkMate {

    private List <Workmate> workmates =  DummyWorkMatesGenerator.generateWorkmate();

    @Override
    public List<Workmate> getWorkmateList() {
        return null;
    }

    @Override
    public Map<String, Workmate> createWorkmateList() {
        return null;
    }
}
