package ro.unibuc.doctorOffice.utils.update;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.model.Report;
import ro.unibuc.doctorOffice.utils.CreatePanel;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.utils.ReadPanel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class ReportUpdatePanel extends Panel
{
    public void execute()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
            return;
        }


        Pacient pacient = optionalPacient.get();

        Main.reportService.map.entrySet().stream()
                .filter(entry -> entry.getValue().getPacient().equals(pacient))
                .forEach(entry -> {
                    System.out.println("Report ID: " + entry.getKey());
                    System.out.println("Report Details: " + entry.getValue());
                });

        Report r;

        do{
            System.out.println("Alege raportul");
            int choice = scanner.nextInt();
            scanner.nextLine();

            r = Main.reportService.map.get(choice);
            System.out.println("raportul nu exista! Alege din nou!");
        }while(r == null);

        System.out.println("Adauga descrierea raportului");
        scanner.nextLine(); // Consume the newline character
        String description = scanner.nextLine();

        System.out.println("Data raportului... (dd-MM-yyyy):");
        String dateString = scanner.nextLine();

        try {
            Date dateOfWrite = dateFormat.parse(dateString);
            System.out.println("Data raport adaugata: " + dateOfWrite);

            r.setDescription(description);
            r.setDateOfWrite(dateOfWrite);

            Main.reportService.update(r);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
        } finally {
            System.out.println("Apasa tasta enter pentru a te intoarce");
            String input = scanner.nextLine();
            HandlerPanel.setPanel(new CreatePanel());
        }
    }
}
