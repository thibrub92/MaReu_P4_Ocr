package fr.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.example.mareu.model.Workmate;

public abstract class DummyWorkMatesGenerator {

    public static List<Workmate> WORKMATE_LAMZONE = Arrays.asList(
            new Workmate("Karim", "Benzema", "IdCol-1", "Karim.Benzema@Lamzone.com"),
            new Workmate("Eden", "Hazard", "IdCol-2", "Eden.Hazard@Lamzone.com"),
            new Workmate("Junior", "Vinicius", "IdCol-3", "Junio.Vinicius@Lamzone.com"),
            new Workmate("Luka", "Modric", "IdCol-4", "Luka.Modric@Lamzone.com"),
            new Workmate("Tony", "Kroos", "IdCol-5", "Tony.Kroos@Lamzone.com"),
            new Workmate("Federico", "Valverde", "IdCol-6", "Federico.Valverde@Lamzone.com"),
            new Workmate("Dani", "Carvaral", "IdCol-7", "Dani.Carvaral@Lamzone.com"),
            new Workmate("Eder", "Militao", "IdCol-8", "Eder.Militao@Lamzone.com"),
            new Workmate("Ferland", "Mendy", "IdCol-9", "Ferland.Mendy@Lamzone.com"),
            new Workmate("David", "Alaba", "IdCol-10", "David.Alaba@Lamzone.com"),
            new Workmate("Marcelo", "Viera Da Silva", "IdCol-11", "Marcelo.vds@Lamzone.com"),
            new Workmate("Eduardo", "Camavinga", "IdCol-12", "Eduardo.Cmavinga@Lamzone.com"),
            new Workmate("Marco", "Asensio", "IdCol-13", "Marco.Ascensio.com"),
            new Workmate("Thibaut", "Courtois", "IdCol-14", "Thibaut.Courtois@Lamzone.com")
    );
    static List<Workmate> generateWorkmate() {
        return new ArrayList<>(WORKMATE_LAMZONE);
    }


}
