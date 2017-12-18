package br.com.dbc.proof.service;

import br.com.dbc.proof.dto.Session;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class EventManagementService {

    private FileProcessService fileProcessService;

    public EventManagementService(FileProcessService fileProcessService){
        this.fileProcessService = fileProcessService;
    }

    public void processSessions(List<Session> sessions){
        sessions.sort(Comparator.comparingInt(Session::getSessionDuration).reversed());
        List<Session> morningTrackOne = madeMorningEvents(sessions);
        List<Session> morningTrackTwo = madeMorningEvents(sessions);
        List<Session> afternoonTrackOne = madeAfternoonTrackOneEvents(sessions);
        List<Session> afternoonTrackTwo = madeAfternoonTrackOneEvents(sessions);

        madeTimeMorningSessions(morningTrackOne);
        madeTimeMorningSessions(morningTrackTwo);
        madeTimeAfternoonSessions(afternoonTrackOne);
        madeTimeAfternoonSessions(afternoonTrackTwo);

        fileProcessService.processFileOutputEvent(morningTrackOne, afternoonTrackOne, morningTrackTwo, afternoonTrackTwo);
    }

    private void madeTimeMorningSessions(List<Session> sessions) {
        LocalTime initialMorningTime = LocalTime.of(9,0);
        Iterator it = sessions.listIterator();
        while (it.hasNext()){
            Session session = (Session) it.next();
            session.setSessionStartTime(initialMorningTime);
            initialMorningTime=initialMorningTime.plusMinutes(session.getSessionDuration());
        }
        sessions.add(new Session("Launch", 60, LocalTime.of(12,0)));
    }

    private void madeTimeAfternoonSessions(List<Session> sessions){
        LocalTime initialAfternoonTime = LocalTime.of(13,0);
        Iterator it = sessions.listIterator();
        while (it.hasNext()){
            Session session = (Session) it.next();
            session.setSessionStartTime(initialAfternoonTime);
            initialAfternoonTime=initialAfternoonTime.plusMinutes(session.getSessionDuration());
        }
        sessions.add(new Session("Network Event", 60, initialAfternoonTime));
    }

    private List<Session> madeMorningEvents(List<Session> sessions) {
        Integer totalTimeSessionsMorning = 180;
        Integer sumTimeSession = 0;
        List<Session> sessionsMorning = new ArrayList<Session>();
        Iterator it = sessions.listIterator();
        while (it.hasNext()){
            Session session = (Session) it.next();
            if ((sumTimeSession + session.getSessionDuration()) <= totalTimeSessionsMorning){
                sessionsMorning.add(session);
                sumTimeSession += session.getSessionDuration();
                it.remove();
            }
        }
        return sessionsMorning;
    }

    private List<Session> madeAfternoonTrackOneEvents(List<Session> sessions) {
        Integer maxTimeSessionsAfternoon = 240;
        Integer minTimeSessionsAfternoon = 180;
        Integer sumTimeSession = 0;
        List<Session> sessionsMorning = new ArrayList<Session>();
        Iterator it = sessions.listIterator();
        while (it.hasNext()){
            Session session = (Session) it.next();
            Integer parcialSumTimeSession = sumTimeSession + session.getSessionDuration();
            if (parcialSumTimeSession <= minTimeSessionsAfternoon){
                sessionsMorning.add(session);
                sumTimeSession += session.getSessionDuration();
                it.remove();
            } else if (parcialSumTimeSession > minTimeSessionsAfternoon && parcialSumTimeSession <= maxTimeSessionsAfternoon) {
                sessionsMorning.add(session);
                sumTimeSession += session.getSessionDuration();
                it.remove();
            }
        }
        return sessionsMorning;
    }


}
