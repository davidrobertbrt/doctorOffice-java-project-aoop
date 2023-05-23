package ro.unibuc.doctorOffice.utils.delete;

import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.utils.*;
import ro.unibuc.doctorOffice.Main;

import java.util.Optional;

public class MedicDeletePanel extends Panel
{
    public void execute()
    {
        System.out.println("citeste prenumele");
        String firstName = scanner.nextLine();
        System.out.println("citeste numele");
        String lastName = scanner.nextLine();

        Optional<Medic> optional = Main.medicService.medicMap.values().stream()
                .filter(o->o.getFirstName().equals(firstName) && o.getLastName().equals(lastName))
                .findFirst();

        if(!optional.isPresent())
        {
            System.out.println("medic nu a fost gasit.");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
            return;
        }

        Medic medic = optional.get();

        Main.appointmentService.map.entrySet().stream()
                .filter(entry-> entry.getValue().getMedic().equals(medic))
                .forEach(
                        entry-> Main.appointmentService.delete(entry.getValue())
                );

        Main.reportService.map.entrySet().stream()
                .filter(entry->entry.getValue().getMedic().equals(medic))
                .forEach(
                        entry->Main.reportService.delete(entry.getValue())
                );


        int result = Main.medicService.delete(medic);
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
