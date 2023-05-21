package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.utils.read.*;

public class ReadPanel extends Panel
{
    public void execute()
    {
        System.out.println("===CITESTE====");
        System.out.println("medic -> citeste un medic");
        System.out.println("pacient -> citeste un pacient");
        System.out.println("prescriptie -> citeste prescriptiile pe baza pacientului");
        System.out.println("raport -> citeste rapoartele pe baza pacientului");
        System.out.println("programare -> citeste o programare pe ziua de azi");
        System.out.println("back -> meniu principal");
        System.out.println("==================================");
        System.out.println("Astept optiunea ta...");
        System.out.println();

        String input = scanner.next();
        switch(input)
        {
            case "medic":
                HandlerPanel.setPanel(new MedicReadPanel());
                break;
            case "pacient":
                HandlerPanel.setPanel(new PacientReadPanel());
                break;
            case "prescriptie":
                HandlerPanel.setPanel(new PrescriptionReadPanel());
                break;
            case "raport":
                HandlerPanel.setPanel(new ReportReadPanel());
                break;
            case "programare":
                HandlerPanel.setPanel(new AppointmentReadPanel());
                break;
            case "back":
                HandlerPanel.setPanel(new MainPanel());
                break;
        }
    }
}
