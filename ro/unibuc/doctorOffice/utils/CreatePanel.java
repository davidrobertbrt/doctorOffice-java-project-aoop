package ro.unibuc.doctorOffice.utils;

import java.io.*;
import java.util.*;

import ro.unibuc.doctorOffice.model.Report;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;

public class CreatePanel extends Panel
{
    public void execute()
    {
        System.out.println("===ADAUGA====");
        System.out.println("medic -> adauga un medic");
        System.out.println("pacient -> adauga un pacient");
        System.out.println("prescriptie -> adauga prescriptie");
        System.out.println("raport -> adauga raport");
        System.out.println("programare -> adauga programare");
        System.out.println("back -> meniu principal");
        System.out.println("==================================");
        System.out.println("Astept optiunea ta...");
        System.out.println();

        String input = scanner.next();
        switch(input)
        {
            case "medic":
                HandlerPanel.setPanel(new MedicAddPanel());
                break;
            case "pacient":
                HandlerPanel.setPanel(new PacientAddPanel());
                break;
            case "prescriptie":
                HandlerPanel.setPanel(new PrescriptionAddPanel());
                break;
            case "raport":
                HandlerPanel.setPanel(new ReportAddPanel());
                break;
            case "programare":
                HandlerPanel.setPanel(new AppointmentAddPanel());
                break;
            case "back":
                HandlerPanel.setPanel(new MainPanel());
                break;
        }
    }
}
