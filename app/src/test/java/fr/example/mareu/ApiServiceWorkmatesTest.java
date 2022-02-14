package fr.example.mareu;

import static org.junit.Assert.assertThat;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import fr.example.mareu.DI.DI;
import fr.example.mareu.model.Workmate;
import fr.example.mareu.service.ApiServiceWorkMate;
import fr.example.mareu.service.DummyWorkMatesGenerator;

public class ApiServiceWorkmatesTest {

    private ApiServiceWorkMate apiServiceWorkMate;

    @Before
    public void setUp() {
        apiServiceWorkMate = DI.getApiServiceWorkMate();
    }

    @Test
    public void getListWormatesWithSuccess() {
        List<Workmate> workmateList = apiServiceWorkMate.getWorkmateList();
        List<Workmate> expectedListEmployees = DummyWorkMatesGenerator.WORKMATE_LAMZONE;
        assertThat(workmateList, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListEmployees.toArray()));
    }
}

