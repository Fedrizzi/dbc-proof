package br.com.dbc.proof;

import br.com.dbc.proof.service.EventManagementService;
import br.com.dbc.proof.service.FileProcessService;

public class Application {

    public static void main(String[] args) {
        FileProcessService fileService = new FileProcessService(args[0], args[1]);
        EventManagementService eventManagementService = new EventManagementService(fileService);
        eventManagementService.processSessions(fileService.splitFileToSessions());
    }


}
