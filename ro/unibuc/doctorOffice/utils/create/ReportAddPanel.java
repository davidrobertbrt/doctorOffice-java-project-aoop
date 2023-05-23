package ro.unibuc.doctorOffice.utils.create;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.service.*;
import ro.unibuc.doctorOffice.utils.CreatePanel;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportAddPanel extends Panel
{

    @Override
    public void execute()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Selecteaza pacient");
        for (Map.Entry<Integer, Pacient> entry : Main.pacientService.pacientMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        int selectedPacientId = scanner.nextInt();
        Pacient selectedPacient = Main.pacientService.pacientMap.get(selectedPacientId);

        System.out.println("Selecteaza medic");
        for (Map.Entry<Integer, Medic> entry : Main.medicService.medicMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        int selectedMedicId = scanner.nextInt();
        Medic selectedMedic = Main.medicService.medicMap.get(selectedMedicId);

        System.out.println("Adauga descrierea raportului");
        scanner.nextLine(); // Consume the newline character
        String description = scanner.nextLine();

        System.out.println("Data raportului... (dd-MM-yyyy):");
        String dateString = scanner.nextLine();

        try {
            Date dateOfWrite = dateFormat.parse(dateString);
            System.out.println("Data raport adaugata: " + dateOfWrite);

            Report report = new Report(selectedPacient, selectedMedic, description, dateOfWrite);
            Main.reportService.insert(report);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
        } finally {
            System.out.println("Apasa tasta enter pentru a te intoarce");
            String input = scanner.nextLine();
            HandlerPanel.setPanel(new CreatePanel());
        }
    }

}
