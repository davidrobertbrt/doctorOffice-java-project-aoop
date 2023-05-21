package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrescriptionAddPanel extends Panel
{
    public void execute()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Map<String,Integer> pills = new HashMap<>();

        System.out.println("Alegeti pacientul pentru care realizati planificarea");
        for (Map.Entry<Integer, Pacient> entry : Main.pacientService.pacientMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        int selectedPacientId = scanner.nextInt();
        Pacient selectedPacient = Main.pacientService.pacientMap.get(selectedPacientId);

        System.out.println("Data utilizarii (dd-MM-yyyy):");
        String dateString = scanner.nextLine();

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

                pills.put(pillName, pillQuantity);
            }

            Prescription p = new Prescription(selectedPacient,pills,datePlanning);
            Main.prescriptionService.insert(p);


        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
        }
        finally{
            System.out.println("Apasa pentru a te intoarce...");
            String input = scanner.nextLine();
            HandlerPanel.setPanel(new CreatePanel());
        }
    }
}
