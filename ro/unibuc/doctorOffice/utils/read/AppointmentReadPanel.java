package ro.unibuc.doctorOffice.utils.read;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.service.*;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.utils.ReadPanel;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.time.ZoneId;


public class AppointmentReadPanel extends Panel
{
    public void execute()
    {
        LocalDate currentDate = LocalDate.now();

        Date crDate = java.sql.Date.valueOf(currentDate);

        Main.appointmentService.map.entrySet().stream()
                .filter(entry -> {
                    Date appointmentDate = entry.getValue().getAppointmentDate();
                    LocalDate localAppointmentDate = appointmentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    return localAppointmentDate.equals(currentDate);
                })
                .forEach(entry -> {
                    System.out.println("Appointment ID: " + entry.getKey());
                    System.out.println("Appointment Details: " + entry.getValue());
                });

        System.out.println("Apasa enter pentru a te intoarce");
        scanner.nextLine();
        HandlerPanel.setPanel(new ReadPanel());
    }
}
