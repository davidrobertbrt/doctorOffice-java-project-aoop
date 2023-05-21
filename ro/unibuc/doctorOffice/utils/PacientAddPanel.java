package ro.unibuc.doctorOffice.utils;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class PacientAddPanel extends Panel
{
    public void execute()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Adaugare un pacient nou.");
        System.out.println("Prenume:");
        String firstName = scanner.nextLine();

        System.out.println("Nume de familie:");
        String lastName = scanner.nextLine();

        System.out.println("Numar de telefon:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Adresa:");
        String address = scanner.nextLine();

        System.out.println("Data nasterii (dd-MM-yyyy):");
        String dobString = scanner.nextLine();

        try {
            Date dateOfBirth = dateFormat.parse(dobString);
            System.out.println("Date of birth: " + dateOfBirth);

            Main.pacientService.insert(firstName,lastName,phoneNumber,address,dateOfBirth);
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
