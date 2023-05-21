package ro.unibuc.doctorOffice.service;

import java.util.*;

import ro.unibuc.doctorOffice.model.Medic;
import ro.unibuc.doctorOffice.repository.PacientRepository;
import ro.unibuc.doctorOffice.model.Pacient;

public class PacientService
{
    public PacientRepository pacientRepo;

    public Map<Integer,Pacient> pacientMap;

    public PacientService()
    {
        this.pacientRepo = new PacientRepository();
        this.pacientMap = new HashMap<>();
    }

    public int loadPacients()
    {
        List<Pacient> list = pacientRepo.readAll();

        if(list.size() == 0)
            return 0;

        this.pacientMap = new HashMap<>();

        for(int i = 0; i < list.size(); i++)
        {
            pacientMap.put(i,list.get(i));
        }

        return 1;
    }

    public int findPosition(Pacient p)
    {
        int pos = -1;
        for (Map.Entry<Integer, Pacient> entry : pacientMap.entrySet()) {
            if (entry.getValue().equals(p)) {
                pos = entry.getKey();
                break;
            }
        }

        return pos;
    }

    public int insert(String firstName, String lastName,String phoneNumber,String address,Date dateOfBirth)
    {
        Pacient newPacient = new Pacient(firstName,lastName,phoneNumber,address,dateOfBirth);
        int response = pacientRepo.insert(newPacient);
        pacientMap.put(pacientMap.size() - 1,newPacient);

        return response;
    }

    public int delete(Pacient p)
    {
        int pos = findPosition(p);

        if(pos == -1)
            return -1;

        int response = pacientRepo.delete(p);

        pacientMap.remove(pos);

        return 1;
    }

    public int update(Pacient p)
    {
        int pos = findPosition(p);

        if(pos == -1)
            return -1;

        int response = pacientRepo.update(p);
        pacientMap.put(pos,p);

        return 1;
    }
}
