package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.utils.delete.*;

public class DeletePanel extends Panel
{
    public void execute()
    {
        System.out.println("===Sterge====");
        System.out.println("medic -> sterge un medic");
        System.out.println("pacient -> sterge un pacient");
        System.out.println("programare -> sterge o programare pe baza datei si orei");
        System.out.println("raport-> sterge un raport pe baza pacientului");
        System.out.println("prescriptie->sterge o prescriptie pe baza pacientului");
        System.out.println("back -> meniu principal");
        System.out.println("==================================");
        System.out.println("Astept optiunea ta...");
        System.out.println();

        String input = scanner.next();
        switch(input)
        {
            case "medic":
                HandlerPanel.setPanel(new MedicDeletePanel());
                break;
            case "pacient":
                HandlerPanel.setPanel(new PacientDeletePanel());
                break;
            case "programare":
                HandlerPanel.setPanel(new AppointmentDeletePanel());
                break;
            case "prescriptie":
                HandlerPanel.setPanel(new PrescriptionDeletePanel());
                break;
            case "report":
                HandlerPanel.setPanel(new ReportDeletePanel());
                break;
            case "back":
                HandlerPanel.setPanel(new MainPanel());
                break;
        }
    }
}
