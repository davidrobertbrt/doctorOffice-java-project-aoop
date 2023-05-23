package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.utils.update.PacientUpdatePanel;
import ro.unibuc.doctorOffice.utils.update.MedicUpdatePanel;

public class UpdatePanel extends Panel
{
    public void execute()
    {
        System.out.println("===Actualizeaza====");
        System.out.println("medic -> actualizeaza un medic");
        System.out.println("pacient -> actualizeaza un pacient");
        System.out.println("back -> meniu principal");
        System.out.println("==================================");
        System.out.println("Astept optiunea ta...");
        System.out.println();

        String input = scanner.next();
        switch(input)
        {
            case "medic":
                HandlerPanel.setPanel(new MedicUpdatePanel());
                break;
            case "pacient":
                HandlerPanel.setPanel(new PacientUpdatePanel());
                break;
            case "back":
                HandlerPanel.setPanel(new MainPanel());
                break;
        }
    }
}
