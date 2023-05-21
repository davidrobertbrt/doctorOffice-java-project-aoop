package ro.unibuc.doctorOffice.utils.read;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.service.*;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.utils.ReadPanel;

import java.util.Map;

public class MedicReadPanel extends Panel
{
    public void execute()
    {
        System.out.println("=====LISTA MEDICI=======");
        for (Map.Entry<Integer, Medic> entry : Main.medicService.medicMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        System.out.println("Apasa enter pentru a te intoarce");
        scanner.nextLine();
        HandlerPanel.setPanel(new ReadPanel());
    }
}
