package ro.unibuc.doctorOffice.service;

import java.util.*;

import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.repository.MedicRepository;
import ro.unibuc.doctorOffice.repository.PacientRepository;
import ro.unibuc.doctorOffice.repository.ReportRepository;

public class ReportService
{
    public ReportRepository repo;
    public Map<Integer, Report> map;

    public ReportService()
    {
        repo = new ReportRepository();
    }

    public int load()
    {
        List<Report> list = repo.readAll();

        if(list.size() == 0)
            return 0;

        this.map = new HashMap<>();

        for(int i = 0; i < list.size(); i++)
        {
            map.put(i,list.get(i));
        }

        return 1;
    }

    public int findPosition(Report val)
    {
        int pos = -1;

        for (Map.Entry<Integer, Report> entry : map.entrySet()) {
            if (entry.getValue().equals(val)) {
                pos = entry.getKey();
                break;
            }
        }

        return pos;
    }

    public int delete(Report val)
    {
        int pos = findPosition(val);

        if(pos == -1)
            return -1;

        int response = repo.delete(val);

        map.remove(pos);

        return 1;
    }

    public int update(Report val)
    {
        int pos = findPosition(val);

        if(pos == -1)
            return -1;

        int response = repo.update(val);

        map.put(pos,val);

        return 1;
    }

    public int insert(Report val)
    {
        int response = repo.insert(val);
        map.put(map.size()-1,val);

        return response;
    }


}
