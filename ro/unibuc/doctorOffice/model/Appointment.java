package ro.unibuc.doctorOffice.model;
import java.util.*;

public final class Appointment
{
    private UUID id;
    private Date appointmentDate;
    private Pacient pacient;
    private Medic medic;

    private Appointment(Date appointmentDate,Pacient pacient, Medic medic)
    {
        this.id = UUID.randomUUID();
        this.appointmentDate = appointmentDate;
        this.pacient = pacient;
        this.medic = medic;
    }

    private Appointment(Date appointmentDate,Pacient pacient, Medic medic,UUID id)
    {
        this(appointmentDate,pacient,medic);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Appointment{");
        sb.append("id=").append(id);
        sb.append(", appointmentDate=").append(appointmentDate);
        sb.append(", pacient=").append(pacient);
        sb.append(", medic=").append(medic);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
