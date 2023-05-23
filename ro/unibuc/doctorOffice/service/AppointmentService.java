package ro.unibuc.doctorOffice.service;

import java.util.*;

import ro.unibuc.doctorOffice.audit.DatabaseAudit;
import ro.unibuc.doctorOffice.model.Specialization;
import ro.unibuc.doctorOffice.repository.AppointmentRepository;
import ro.unibuc.doctorOffice.repository.MedicRepository;
import ro.unibuc.doctorOffice.repository.PacientRepository;

import ro.unibuc.doctorOffice.model.Pacient;
import ro.unibuc.doctorOffice.model.Medic;
import ro.unibuc.doctorOffice.model.Appointment;

public class AppointmentService
{
    public AppointmentRepository repo;
    public Map<Integer,Appointment> map;

    public AppointmentService()
    {
        repo = new AppointmentRepository();
        this.map = new HashMap<>();
    }

    public int load()
    {
        List<Appointment> list = repo.readAll();


        if(list.size() == 0)
            return 0;

        this.map = new HashMap<>();


        for(int i = 0; i < list.size(); i++)
        {
            map.put(i,list.get(i));
        }

        DatabaseAudit.send("load_appointment",new Date());

        return 1;
    }

    public int findPosition(Appointment val)
    {
        int pos = -1;

        for (Map.Entry<Integer, Appointment> entry : map.entrySet()) {
            if (entry.getValue().equals(val)) {
                pos = entry.getKey();
                break;
            }
        }

        return pos;
    }

    public int delete(Appointment val)
    {
        int pos = findPosition(val);

        if(pos == -1)
            return -1;

        int response = repo.delete(val);

        map.remove(pos);

        DatabaseAudit.send("delete_appointment",new Date());
        return 1;
    }

    public int update(Appointment val)
    {
        int pos = findPosition(val);

        if(pos == -1)
            return -1;

        int response = repo.update(val);

        map.put(pos,val);

        DatabaseAudit.send("update_appointment",new Date());
        return 1;
    }

    public int insert(Appointment val)
    {
        int response = repo.insert(val);
        map.put(map.size()-1,val);

        DatabaseAudit.send("insert_appointment",new Date());
        return response;
    }
}
