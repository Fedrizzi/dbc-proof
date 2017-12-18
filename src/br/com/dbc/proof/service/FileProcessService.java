package br.com.dbc.proof.service;

import br.com.dbc.proof.dto.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileProcessService {

    private File file;
    private String fileOutput;

    public FileProcessService(String fileInput, String fileOutput){
        file = new File(fileInput);
        this.fileOutput = fileOutput;
    }

    public List<Session> splitFileToSessions(){
        List<Session> sessions = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                sessions.add(splitLineToSession(line));
            }
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Input file not found");
        }
        return sessions;
    }

    private Session splitLineToSession(String line){
        String[] splitedLine = line.split(" ");
        String nameSession = "";
        Integer sessionDuration = 0;
        for (int i=0; i<splitedLine.length; i++){
            if (i == splitedLine.length - 1){
                if (splitedLine[i].contains("min")){
                    sessionDuration = Integer.valueOf(splitedLine[i].replace("min",""));
                } else if (splitedLine[i].equalsIgnoreCase("lightning")){
                    sessionDuration=5;
                }
            } else {
                nameSession = nameSession.concat(splitedLine[i]) + " ";
            }
        }
        Session session = new Session(nameSession, sessionDuration);

        return session;
    }

    public void processFileOutputEvent(List<Session> morningTrackOne, List<Session> afternoonTrackOne, List<Session> morningTrackTwo, List<Session> afternoonTrackTwo) {
        List<String> lines = new ArrayList<String>();
        lines.add("Track 1");
        lines.addAll(morningTrackOne.stream().map(p -> p.toStringLineOutput()).collect(Collectors.toList()));
        lines.addAll(afternoonTrackOne.stream().map(p -> p.toStringLineOutput()).collect(Collectors.toList()));
        lines.add("Track 2:");
        lines.addAll(morningTrackTwo.stream().map(p -> p.toStringLineOutput()).collect(Collectors.toList()));
        lines.addAll(afternoonTrackTwo.stream().map(p -> p.toStringLineOutput()).collect(Collectors.toList()));

        Path file = Paths.get(fileOutput);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.out.println("Error to write file. ");
        }
    }
}
