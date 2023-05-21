package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.utils.*;
import ro.unibuc.doctorOffice.Main;

import java.util.Optional;

public class PacientDeletePanel extends Panel
{
    public void execute()
    {
        System.out.println("citeste prenumele");
        String firstName = scanner.nextLine();
        System.out.println("citeste numele");
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

        Main.appointmentService.map.entrySet().stream()
                .filter(entry-> entry.getValue().getPacient().equals(pacient))
                .forEach(
                        entry-> Main.appointmentService.delete(entry.getValue())
                );

        Main.reportService.map.entrySet().stream()
                .filter(entry->entry.getValue().getPacient().equals(pacient))
                .forEach(
                        entry->Main.reportService.delete(entry.getValue())
                );

        Main.prescriptionService.map.entrySet().stream()
                .filter(entry->entry.getValue().getPacient().equals(pacient))
                .forEach(
                        entry->Main.prescriptionService.delete(entry.getValue())
                );

        int result = Main.pacientService.delete(pacient);
        if(result == -1) {
            System.out.println("stergerea a fost imposibila!");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
        }

        System.out.println("stergere cu succes!");
        scanner.nextLine();
        HandlerPanel.setPanel(new DeletePanel());
    }
}
