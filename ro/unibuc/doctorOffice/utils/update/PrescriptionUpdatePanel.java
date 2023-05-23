package ro.unibuc.doctorOffice.utils.update;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.model.Prescription;
import ro.unibuc.doctorOffice.utils.CreatePanel;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.utils.ReadPanel;
import ro.unibuc.doctorOffice.utils.UpdatePanel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PrescriptionUpdatePanel extends Panel
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
            HandlerPanel.setPanel(new UpdatePanel());
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


        Prescription p;

        do{
            System.out.println("Alege prescriptia");
            int choice = scanner.nextInt();
            scanner.nextLine();

            p = Main.prescriptionService.map.get(choice);
            if(p == null)
                System.out.println("Prescriptia nu exista! Alege din nou!");
        }while(p == null);

        System.out.println("Data utilizarii (dd-MM-yyyy):");
        String dateString = scanner.nextLine();
        Map<String,Integer> pillsUpdate = new HashMap<>();
        try {
            Date datePlanning = dateFormat.parse(dateString);
            System.out.println("Data utilizarii: " + datePlanning);

            System.out.print("Enter the number of pills: ");
            int numPills = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            for (int i = 0; i < numPills; i++) {
                System.out.print("Enter the pill name: ");
                String pillName = scanner.nextLine();

                System.out.print("Enter the pill quantity: ");
                int pillQuantity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                pillsUpdate.put(pillName, pillQuantity);
            }


            p.setDateOfUse(datePlanning);
            p.setPills(pillsUpdate);

            Main.prescriptionService.update(p);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
        }
        finally{
            System.out.println("Apasa pentru a te intoarce...");
            String input = scanner.nextLine();
            HandlerPanel.setPanel(new UpdatePanel());
        }
    }

}
