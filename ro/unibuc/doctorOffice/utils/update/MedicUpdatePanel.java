package ro.unibuc.doctorOffice.utils.update;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.model.Medic;
import ro.unibuc.doctorOffice.model.Specialization;
import ro.unibuc.doctorOffice.utils.*;

import java.util.Optional;

public class MedicUpdatePanel extends Panel
{
    public void execute()
    {
        System.out.println("citeste prenumele");
        String firstName = scanner.nextLine();
        System.out.println("citeste numele");
        String lastName = scanner.nextLine();

        String finalFirstName = firstName;
        String finalLastName = lastName;
        Optional<Medic> optional = Main.medicService.medicMap.values().stream()
                .filter(o->o.getFirstName().equals(finalFirstName) && o.getLastName().equals(finalLastName))
                .findFirst();

        if(!optional.isPresent())
        {
            System.out.println("medic nu a fost gasit.");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
        }

        Medic medic = optional.get();

        System.out.println("Prenume:");
        firstName = scanner.nextLine();

        System.out.println("Nume de familie:");
        lastName = scanner.nextLine();

        System.out.println("Numar de telefon:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Specializare:");
        String specializationInput = scanner.nextLine();
        Specialization specialization = Specialization.valueOf(specializationInput.toUpperCase());

        medic.setFirstName(firstName);
        medic.setLastName(lastName);
        medic.setPhoneNumber(phoneNumber);
        medic.setSpecialization(specialization);

        int result = Main.medicService.update(medic,firstName,lastName,specialization,phoneNumber);

        if(result == -1)
        {
            System.out.println("update esuat");
            scanner.nextLine();
            HandlerPanel.setPanel(new UpdatePanel());
        }

        System.out.println("update reusit!");
        scanner.nextLine();
        HandlerPanel.setPanel(new UpdatePanel());


    }
}
