package ro.unibuc.doctorOffice.utils.update;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.utils.CreatePanel;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.utils.UpdatePanel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class PacientUpdatePanel extends Panel
{
    public void execute()
    {
        System.out.println("Citeste prenumele");
        String firstName = scanner.nextLine();

        System.out.println("Citeste numele");
        String lastName = scanner.nextLine();

        String finalFirstName = firstName;
        String finalLastName = lastName;
        Optional<Pacient> optionalPacient = Main.pacientService.pacientMap.values().stream()
                .filter(pacient->pacient.getFirstName().equals(finalFirstName) && pacient.getLastName().equals(finalLastName))
                .findFirst();

        Pacient pacient = optionalPacient.get();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Adaugare un pacient nou.");
        System.out.println("Prenume:");
        firstName = scanner.nextLine();

        System.out.println("Nume de familie:");
        lastName = scanner.nextLine();

        System.out.println("Numar de telefon:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Adresa:");
        String address = scanner.nextLine();

        System.out.println("Data nasterii (dd-MM-yyyy):");
        String dobString = scanner.nextLine();

        try {
            Date dateOfBirth = dateFormat.parse(dobString);
            System.out.println("Date of birth: " + dateOfBirth);

            int result = Main.pacientService.update(pacient);

            if(result == -1)
            {
                System.out.println("Eroare la update");
                String input = scanner.nextLine();
                HandlerPanel.setPanel(new UpdatePanel());
            }

            System.out.println("Update cu succes!");
            String input = scanner.nextLine();
            HandlerPanel.setPanel(new UpdatePanel());
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
