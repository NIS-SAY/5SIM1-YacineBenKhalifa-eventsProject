package tn.esprit.eventsproject;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SpringBootWithUnitTestMockito {

    @Mock
    EventRepository eventRepo;
    @Mock
    ParticipantRepository partRepo;
    @Mock
    LogisticsRepository logiRepo;
    //ou EventRepository eventRepo = Mockito.mock(EventRepository.class);

    @InjectMocks
    EventServicesImpl eventService;


    Event m  = Event.builder().description("event1").dateDebut(LocalDate.parse("2nd October 2023")).dateFin(LocalDate.parse("2nd October 2023")).cout(1.5f).build();
    Participant p  = Participant.builder().nom("part1").prenom("part1").tache(Tache.SERVEUR).build();
    Logistics l  = Logistics.builder().description("Logistics2").reserve(true).prixUnit(3.5f).quantite(1).build();
    List<Logistics> list= new ArrayList<Logistics>() {
        {
            add(Logistics.builder().description("Logistics2").reserve(true).prixUnit(1.5f).quantite(10).build());
            add(Logistics.builder().description("Logistics3").reserve(false).prixUnit(5.5f).quantite(100).build());
        }
    };

    @Test
    public void addParticipantTest() {
        Mockito.when(partRepo.save(Mockito.any(Participant.class))).then(invocation -> {
            Participant model = invocation.getArgument(0, Participant.class);
            model.setIdPart(1);
            return model;
        });
        log.info("Avant ==> " + p.toString());
        Participant participant = eventService.addParticipant(p);
        assertSame(participant, p);
        log.info("Après ==> " + p.toString());
    }

    @Test
    public void addAffectEvenTest() {
        Mockito.when(eventRepo.save(Mockito.any(Event.class))).then(invocation -> {
            Event model = invocation.getArgument(0, Event.class);
            model.setIdEvent(1);
            return model;
        });
        log.info("Avant ==> " + m.toString());
        Event event = eventService.addAffectEvenParticipant(m);
        assertSame(event, m);
        log.info("Après ==> " + m.toString());
    }

    @Test
    public void addAffectEvenParticipantTest() {
        Mockito.when(eventRepo.save(Mockito.any(Event.class))).then(invocation -> {
            Event model = invocation.getArgument(0, Event.class);
            model.setIdEvent(2);
            return model;
        });
        log.info("Avant ==> " + m.toString());
        Event event = eventService.addAffectEvenParticipant(m,p.getIdPart());
        assertSame(event, m);
        log.info("Après ==> " + m.toString());
    }

    @Test
    public void addAffectLogTest() {
        Mockito.when(logiRepo.save(Mockito.any(Logistics.class))).then(invocation -> {
            Logistics model = invocation.getArgument(0, Logistics.class);
            model.setIdLog(1);
            return model;
        });
        log.info("Avant ==> " + l.toString());
        Logistics logistics = eventService.addAffectLog(l,m.getDescription());
        assertSame(logistics, l);
        log.info("Après ==> " + l.toString());
    }

    @Test
    public void getLogisticsDatesTest() {
        Mockito.when(logiRepo.findAll()).thenReturn(list);
        List<Logistics> logistics = eventService.getLogisticsDates(LocalDate.parse("2nd October 2023"),LocalDate.parse("2nd October 2023"));
        assertNotNull(logistics);
    }
}
