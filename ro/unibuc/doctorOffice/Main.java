package ro.unibuc.doctorOffice;
import ro.unibuc.doctorOffice.audit.DatabaseAudit;
import ro.unibuc.doctorOffice.model.*;
import ro.unibuc.doctorOffice.service.*;
import ro.unibuc.doctorOffice.repository.*;
import ro.unibuc.doctorOffice.utils.HandlerPanel;

import java.util.*;
import java.io.*;
import java.util.logging.Handler;

public class Main {

    public static PrescriptionService prescriptionService;
    public static ReportService reportService;
    public static PacientService pacientService;
    public static MedicService medicService;
    public static AppointmentService appointmentService;

    public Main()
    {

    }
    public static void main(String[] args)
    {
        prescriptionService = new PrescriptionService();
        reportService = new ReportService();
        pacientService = new PacientService();
        medicService = new MedicService();
        appointmentService = new AppointmentService();

        loadAll();


        HandlerPanel handlerPanel = new HandlerPanel();

        while(HandlerPanel.signalExit == false)
        {
            handlerPanel.execute();
        }
    }

    public static void loadAll()
    {
        pacientService.loadPacients();
        medicService.loadMedics();
        prescriptionService.load();
        reportService.load();
        appointmentService.load();
    }
}
