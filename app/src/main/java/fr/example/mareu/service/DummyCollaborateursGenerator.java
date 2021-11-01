package fr.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.example.mareu.model.Collaborateurs;
import fr.example.mareu.model.Meeting;

public abstract class DummyCollaborateursGenerator {

    public static List<Collaborateurs> COLLABORATEUR_LAMZONE = Arrays.asList(
            new Collaborateurs("Karim", "Benzema", "IdCol-1", "Karim.Benzema@Lamzone.com"),
            new Collaborateurs("Eden", "Hazard", "IdCol-2", "Eden.Hazard@Lamzone.com"),
            new Collaborateurs("Junior", "Vinicius", "IdCol-3", "Junio.Vinicius@Lamzone.com"),
            new Collaborateurs("Luka", "Modric", "IdCol-4", "Luka.Modric@Lamzone.com"),
            new Collaborateurs("Tony", "Kroos", "IdCol-5", "Tony.Kroos@Lamzone.com"),
            new Collaborateurs("Federico", "Valverde", "IdCol-6", "Federico.Valverde@Lamzone.com"),
            new Collaborateurs("Dani", "Carvaral", "IdCol-7", "Dani.Carvaral@Lamzone.com"),
            new Collaborateurs("Eder", "Militao", "IdCol-8", "Eder.Militao@Lamzone.com"),
            new Collaborateurs("Ferland", "Mendy", "IdCol-9", "Ferland.Mendy@Lamzone.com"),
            new Collaborateurs("David", "Alaba", "IdCol-10", "David.Alaba@Lamzone.com"),
            new Collaborateurs("Marcelo", "Viera Da Silva", "IdCol-11", "Marcelo.vds@Lamzone.com"),
            new Collaborateurs("Eduardo", "Camavinga", "IdCol-12", "Eduardo.Cmavinga@Lamzone.com"),
            new Collaborateurs("Marco", "Asensio", "IdCol-13", "Marco.Ascensio.com"),
            new Collaborateurs("Thibaut", "Courtois", "IdCol-14", "Thibaut.Courtois@Lamzone.com")
    );
    static List<Collaborateurs> generateCollaborateurs() {
        return new ArrayList<>(COLLABORATEUR_LAMZONE);
    }

    public static List<Meeting> generateReunion() {
        return new ArrayList<>(generateReunion());
    }
}
