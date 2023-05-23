package ro.unibuc.doctorOffice.utils.delete;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.utils.DeletePanel;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.utils.ReadPanel;

import java.util.Map;
import java.util.Optional;

public class PrescriptionDeletePanel extends Panel {
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
            return;
        }

        Pacient pacient = optionalPacient.get();

        Main.prescriptionService.map.entrySet().stream()
                .filter(entry -> entry.getValue().getPacient().equals(pacient))
                .forEach(entry -> {
                    System.out.println("Prescription ID: " + entry.getKey());
                    System.out.println("Prescription Details: " + entry.getValue());

                    Map<String, Integer> pills = entry.getValue().getPills();
                    for (Map.Entry<String, Integer> pillEntry : pills.entrySet()) {
                        String pillName = pillEntry.getKey();
                        Integer pillValue = pillEntry.getValue();

                        System.out.println(pillName);
                        System.out.println(pillValue);
                    }
                });

        System.out.println("Alege prescriptia pe care doresti sa il stergi...");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(Main.prescriptionService.map.get(choice) != null)
        {
            Main.prescriptionService.delete(Main.prescriptionService.map.get(choice));
            System.out.println("Prescriptie stearsa cu succes!");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
        }
        else
        {
            System.out.println("Prescriptia nu a putut fi stearsa!");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
        }
    }
}
