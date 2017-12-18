package br.com.dbc.proof;

import br.com.dbc.proof.service.EventManagementService;
import br.com.dbc.proof.service.FileProcessService;

public class Application {

    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("Include parameters with Input File and Output File. I.e: java -jar dbc-proof.jar c:/file/input.txt c:/file/output.txt");
            return;
        }
        FileProcessService fileService = new FileProcessService(args[0], args[1]);
        EventManagementService eventManagementService = new EventManagementService(fileService);
        eventManagementService.processSessions(fileService.splitFileToSessions());
    }


}
