package ro.unibuc.doctorOffice.service;

import ro.unibuc.doctorOffice.model.Medic;
import ro.unibuc.doctorOffice.model.Specialization;
import ro.unibuc.doctorOffice.repository.MedicRepository;
import java.util.*;

public class MedicService
{
    public MedicRepository medicRepo;
   // public List<Medic> medicList;
    public Map<Integer,Medic> medicMap;

    public MedicService()
    {
        this.medicRepo = new MedicRepository();
    }

    public int loadMedics()
    {
        List<Medic> listMedic = medicRepo.readAll();

        if(listMedic.size() == 0)
            return 0;

        this.medicMap = new HashMap<>();

        for(int i = 0; i < listMedic.size(); i++)
        {
            medicMap.put(i,listMedic.get(i));
        }

        return 1;
    }

    public int findPosition(Medic m)
    {
        int pos = -1;

        for (Map.Entry<Integer, Medic> entry : medicMap.entrySet()) {
            if (entry.getValue().equals(m)) {
                pos = entry.getKey();
                break;
            }
        }

        return pos;
    }

    public int insertMedic(String firstName, String lastName, Specialization specialization, String phoneNumber)
    {
        Medic newMedic = new Medic(firstName,lastName,specialization,phoneNumber);
        int response = medicRepo.insert(newMedic);
        medicMap.put(medicMap.size() - 1,newMedic);

        return response;
    }


    public int delete(Medic m)
    {
        int pos = findPosition(m);

        if(pos == -1)
            return -1;

        int response = medicRepo.delete(m);

        medicMap.remove(pos);

        return 1;
    }

    public int update(Medic m,String firstName, String lastName, Specialization specialization, String phoneNumber)
    {
        int pos = findPosition(m);

        if(pos == -1)
            return -1;

        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setSpecialization(specialization);
        m.setPhoneNumber(phoneNumber);

        int response = medicRepo.update(m);

        medicMap.put(pos,m);

        return 1;
    }
}
