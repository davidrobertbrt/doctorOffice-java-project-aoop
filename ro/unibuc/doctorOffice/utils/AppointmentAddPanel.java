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

public class AppointmentAddPanel extends Panel
{
    public void execute()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Alegeti pacientul pentru care realizati planificarea");
        for (Map.Entry<Integer, Pacient> entry : Main.pacientService.pacientMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        int selectedPacientId = scanner.nextInt();

        Pacient selectedPacient = Main.pacientService.pacientMap.get(selectedPacientId);

        System.out.println("Alegeti medicul pentru care realizati planificarea");
        for (Map.Entry<Integer, Medic> entry : Main.medicService.medicMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        int selectedMedicId = scanner.nextInt();
        scanner.nextLine();
        Medic selectedMedic = Main.medicService.medicMap.get(selectedMedicId);

        System.out.println("Data programarii (dd-MM-yyyy):");
        String dateString = scanner.nextLine();

        try {
            Date datePlanning = dateFormat.parse(dateString);
            System.out.println("Data programarii: " + datePlanning);

            Appointment newAppointment = new Appointment(new Date(), selectedPacient, selectedMedic);

            Main.appointmentService.insert(newAppointment);
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
