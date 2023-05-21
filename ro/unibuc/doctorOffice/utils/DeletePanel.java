package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;

public class DeletePanel extends Panel
{
    public void execute()
    {
        System.out.println("===Sterge====");
        System.out.println("medic -> sterge un medic");
        System.out.println("pacient -> sterge un pacient");
        System.out.println("programare -> sterge o programare pe baza datei si orei");
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
            case "back":
                HandlerPanel.setPanel(new MainPanel());
                break;
        }
    }
}
