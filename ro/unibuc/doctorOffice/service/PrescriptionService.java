package ro.unibuc.doctorOffice.service;

import java.util.*;

import ro.unibuc.doctorOffice.model.Appointment;
import ro.unibuc.doctorOffice.model.Report;
import ro.unibuc.doctorOffice.repository.PrescriptionRepository;
import ro.unibuc.doctorOffice.repository.PacientRepository;

import ro.unibuc.doctorOffice.model.Prescription;
import ro.unibuc.doctorOffice.model.Pacient;

public class PrescriptionService
{
    public PrescriptionRepository repo;
    public Map<Integer, Prescription> map;

    public PrescriptionService()
    {
        repo = new PrescriptionRepository();
    }

    public int load()
    {
        List<Prescription> list = repo.readAll();

        if(list.size() == 0)
            return 0;

        this.map = new HashMap<>();

        for(int i = 0; i < list.size(); i++)
        {
            map.put(i,list.get(i));
        }

        return 1;
    }

    public int findPosition(Prescription val)
    {
        int pos = -1;

        for (Map.Entry<Integer, Prescription> entry : map.entrySet()) {
            if (entry.getValue().equals(val)) {
                pos = entry.getKey();
                break;
            }
        }

        return pos;
    }

    public int update(Prescription val)
    {
        int pos = findPosition(val);

        if(pos == -1)
            return -1;

        int response = repo.update(val);

        map.put(pos,val);

        return 1;
    }

    public int insert(Prescription val)
    {
        int response = repo.insert(val);
        map.put(map.size()-1,val);

        return response;
    }

}
