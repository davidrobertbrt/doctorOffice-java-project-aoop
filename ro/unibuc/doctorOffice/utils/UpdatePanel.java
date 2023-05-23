package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.utils.update.PacientUpdatePanel;
import ro.unibuc.doctorOffice.utils.update.MedicUpdatePanel;
import ro.unibuc.doctorOffice.utils.update.PrescriptionUpdatePanel;
import ro.unibuc.doctorOffice.utils.update.ReportUpdatePanel;

public class UpdatePanel extends Panel
{
    public void execute()
    {
        System.out.println("===Actualizeaza====");
        System.out.println("medic -> actualizeaza un medic");
        System.out.println("pacient -> actualizeaza un pacient");
        System.out.println("prescriptie -> actualizeaza un prescriptie");
        System.out.println("raport -> actualizeaza un raport");
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
            case "raport":
                HandlerPanel.setPanel(new ReportUpdatePanel());
                break;
            case "prescriptie":
                HandlerPanel.setPanel(new PrescriptionUpdatePanel());
                break;
            case "back":
                HandlerPanel.setPanel(new MainPanel());
                break;
        }
    }
}
