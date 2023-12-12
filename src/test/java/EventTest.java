import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class EventTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    public EventTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEvent() {
        // Arrange
        Event ev = new Event(1, "evenment 1",1);
        when(eventRepository.save(ev)).thenReturn(ev);

        // Act
        Event result = eventServices.addEvent(ev);

        // Assert
        assertNotNull(result);
        assertEquals("evenment 1", result.getDescription());
        assertEquals(1, result.getIdEvent());
        assertEquals(ev, result);
    }
    @Test
    void testRetrieveEvent() {
        Event ev = new Event(2, "evenment 2",1);

        when(eventRepository.findById(ev.getIdEvent())).thenReturn(Optional.of(ev));

        Event result = eventServices.retrieveEvent(2);

        assertEquals(ev, result);
        assertEquals("evenment 2", result.getDescription());
    }
    @Test
    void testRetrieveAllEvents() {
        // Arrange
        List<Event> EventList = new ArrayList<>();
        EventList.add(new Event(0, "evenment 2",1));
        EventList.add(new Event(1, "evenment 3",1));


        when(eventRepository.findAll()).thenReturn(EventList);

        // Act
        List<Event> result = eventServices.retrieveAllEvents();

        // Assert
        assertEquals(2, result.size());
        assertEquals("evenment 2", result.get(0).getDescription());
        assertEquals("evenment 3", result.get(1).getDescription());
    }
    @Test
    void testDeleteEvent() {
        // Arrange
        Event ev = new Event(2, "evenment 2",1);

        when(eventRepository.findById(2)).thenReturn(Optional.of(ev));

        // Act
        eventServices.removeEvent(2);

        // Assert
        verify(eventRepository, times(1)).deleteById(2);
    }
}
