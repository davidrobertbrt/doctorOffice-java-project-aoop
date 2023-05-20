package ro.unibuc.doctorOffice.model;

import java.util.UUID;
import java.util.Date;

public class Report
{
    private UUID id;
    private Pacient pacient;
    private Medic medic;

    private String description;

    private Date dateOfWrite;

    public Report(Pacient pacient, Medic medic, String description,Date dateOfWrite)
    {
        this.id = UUID.randomUUID();
        this.pacient = pacient;
        this.medic = medic;
        this.description = description;
        this.dateOfWrite = dateOfWrite;
    }

    public Report(Pacient pacient, Medic medic, String description,Date dateOfWrite, UUID id)
    {
        this(pacient,medic,description,dateOfWrite);
        this.id = id;
    }

    public UUID getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfWrite() {
        return dateOfWrite;
    }

    public void setDateOfWrite(Date dateOfWrite) {
        this.dateOfWrite = dateOfWrite;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Report{");
        sb.append("id=").append(id);
        sb.append(", pacient=").append(pacient);
        sb.append(", medic=").append(medic);
        sb.append(", description='").append(description).append('\'');
        sb.append(", dateOfWrite=").append(dateOfWrite);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return id.equals(report.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
