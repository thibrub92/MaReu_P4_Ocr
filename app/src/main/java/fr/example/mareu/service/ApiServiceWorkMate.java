package fr.example.mareu.service;

import java.util.List;
import java.util.Map;

import fr.example.mareu.model.Workmate;

public interface ApiServiceWorkMate {

    List<Workmate> getWorkmateList();

    Map <String, Workmate> createWorkmateList();
}
