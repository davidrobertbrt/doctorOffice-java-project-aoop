package ro.unibuc.doctorOffice.utils.read;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.service.*;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.utils.ReadPanel;

import java.util.*;
public class PersonReadPanel extends Panel
{
    public void execute()
    {
        List<Pacient> pacientList = new ArrayList<>(Main.pacientService.pacientMap.values());
        List<Medic> medicList = new ArrayList<>(Main.medicService.medicMap.values());

        List<Person> personList = new ArrayList<>();
        personList.addAll(pacientList);
        personList.addAll(medicList);

        personList.sort(Comparator.comparing(Person::getFirstName)
                .thenComparing(Person::getLastName));

        for (Person person : personList) {
            String type = (person instanceof Pacient) ? "Pacient" : "Medic";
            System.out.println(type + ": " + person.getFirstName() + " " + person.getLastName());
        }

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        HandlerPanel.setPanel(new ReadPanel());
    }
}
