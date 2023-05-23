package ro.unibuc.doctorOffice.utils.delete;

import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.utils.*;
import ro.unibuc.doctorOffice.Main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AppointmentDeletePanel extends Panel
{
    public void execute()
    {
        System.out.println("Citeste data (YYYY-MM-DD):");
        String appointmentDate = scanner.nextLine();
        System.out.println("Citeste ora (HH:MM):");
        String appointmentTime = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(appointmentDate + " " + appointmentTime, formatter);

        Optional<Appointment> optionalAppointment = Main.appointmentService.map.values().stream()
                .filter(appointment -> appointment.getAppointmentDate().equals(dateTime))
                .findFirst();

        if (!optionalAppointment.isPresent()) {
            System.out.println("Programarea nu a fost gasita.");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
            return;
        }

        Appointment appointment = optionalAppointment.get();

        int result = Main.appointmentService.delete(appointment);

        if(result == -1)
        {
            System.out.println("stergerea a fost imposibila!");
            scanner.nextLine();
            HandlerPanel.setPanel(new DeletePanel());
        }

        System.out.println("Stergere programare cu succes!");
        scanner.nextLine();
        HandlerPanel.setPanel(new DeletePanel());
    }
}
