package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.service.*;

public class MedicAddPanel extends Panel
{
    public void execute()
    {
        System.out.println("Adaugare un medic nou.");
        System.out.println("Prenume:");
        String firstName = scanner.nextLine();

        System.out.println("Nume de familie:");
        String lastName = scanner.nextLine();

        System.out.println("Numar de telefon:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Specializare:");
        String specializationInput = scanner.nextLine();
        Specialization specialization = Specialization.valueOf(specializationInput.toUpperCase());

        Main.medicService.insertMedic(firstName,lastName,specialization,phoneNumber);

        System.out.println("Medic adaugat cu succes! Astept input pentru a continua");
        String input = scanner.next();

        HandlerPanel.setPanel(new CreatePanel());

    }
}
