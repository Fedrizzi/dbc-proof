package service;

import br.com.dbc.proof.dto.Session;
import br.com.dbc.proof.service.EventManagementService;
import br.com.dbc.proof.service.FileProcessService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EventManagementServiceTest {

    @Mock
    private FileProcessService fileProcessService;

    @Test
    public void processSessions() {
        EventManagementService eventManagementService = new EventManagementService(fileProcessService);
        List<Session> sessionList = new ArrayList<Session>();
        loadSessions(sessionList);
        eventManagementService.processSessions(sessionList);

        Assert.assertTrue(sessionList.size() == 0);
    }

    private void loadSessions(List sessions){
        sessions.add(new Session("A",60));
        sessions.add(new Session("B",45));
        sessions.add(new Session("C",30));
        sessions.add(new Session("D",45));
        sessions.add(new Session("E",45));
        sessions.add(new Session("F",5));
        sessions.add(new Session("G",60));
        sessions.add(new Session("H",45));
        sessions.add(new Session("I",30));
        sessions.add(new Session("J",30));
        sessions.add(new Session("L",45));
        sessions.add(new Session("M",60));
        sessions.add(new Session("N",60));
        sessions.add(new Session("O",45));
        sessions.add(new Session("P",30));
        sessions.add(new Session("Q",30));
        sessions.add(new Session("R",60));
        sessions.add(new Session("S",30));
        sessions.add(new Session("T",30));
    }

}