package ro.unibuc.doctorOffice.utils.delete;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.utils.DeletePanel;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.utils.ReadPanel;

import java.util.Optional;
import java.util.logging.Handler;

public class ReportDeletePanel extends Panel
{
    public void execute()
    {
        System.out.println("Citeste prenumele");
        String firstName = scanner.nextLine();

        System.out.println("Citeste numele");
        String lastName = scanner.nextLine();

        Optional<Pacient> optionalPacient = Main.pacientService.pacientMap.values().stream()
                .filter(pacient->pacient.getFirstName().equals(firstName) && pacient.getLastName().equals(lastName))
                .findFirst();

        if(!optionalPacient.isPresent())
        {
            System.out.println("pacientul nu a fost gasit.");
            scanner.nextLine();
            HandlerPanel.setPanel(new ReadPanel());
        }


        Pacient pacient = optionalPacient.get();

        Main.reportService.map.entrySet().stream()
                .filter(entry -> entry.getValue().getPacient().equals(pacient))
                .forEach(entry -> {
                    System.out.println("Report ID: " + entry.getKey());
                    System.out.println("Report Details: " + entry.getValue());
                });

        System.out.println("Alege raportul pe care doresti sa il stergi...");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(Main.reportService.map.get(choice) != null)
        {
            Main.reportService.delete(Main.reportService.map.get(choice));
            System.out.println("Raport sters cu succes!");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
        }
        else
        {
            System.out.println("Raportul nu a putut fi sters!");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
        }
    }
}
