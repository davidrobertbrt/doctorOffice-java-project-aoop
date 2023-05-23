package ro.unibuc.doctorOffice.utils;

import java.io.*;
import java.util.*;
import ro.unibuc.doctorOffice.utils.HandlerPanel;


public class MainPanel extends Panel
{
    public void execute()
    {
        System.out.println("=== APLICATIE MEDICALA ===");
        System.out.println("iesire -> iesi din aplicatie");
        System.out.println("citeste -> vezi informatiile clinicii");
        System.out.println("adauga -> adauga informatii");
        System.out.println("sterge -> sterge informatii");
        System.out.println("schimba -> schimba informatii (actualizeaza)");
        System.out.println("==================================");
        System.out.println("Astept optiunea ta...");
        System.out.println();
        String input = scanner.next();
        switch(input)
        {
            case "iesire":
                System.out.println("La revedere!");
                HandlerPanel.signalExit = true;
                break;
            case "citeste":
                HandlerPanel.setPanel(new ReadPanel());
                break;
            case "adauga":
                HandlerPanel.setPanel(new CreatePanel());
                break;
            case "sterge":
                HandlerPanel.setPanel(new DeletePanel());
                break;
            case "schimba":
                HandlerPanel.setPanel(new UpdatePanel());
                break;
        }
    }

}
