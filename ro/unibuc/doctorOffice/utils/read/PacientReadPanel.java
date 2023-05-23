package ro.unibuc.doctorOffice.utils.read;

import ro.unibuc.doctorOffice.Main;
import ro.unibuc.doctorOffice.model.Medic;
import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.utils.HandlerPanel;
import ro.unibuc.doctorOffice.utils.Panel;
import ro.unibuc.doctorOffice.utils.ReadPanel;

import java.util.Map;

public class PacientReadPanel extends Panel
{
    public void execute()
    {
        System.out.println("=====LISTA PACIENTI=======");
        for (Map.Entry<Integer, Pacient> entry : Main.pacientService.pacientMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        System.out.println("Apasa enter pentru a te intoarce");
        scanner.nextLine();
        HandlerPanel.setPanel(new ReadPanel());
    }
}
