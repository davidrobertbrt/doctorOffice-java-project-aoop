package ro.unibuc.doctorOffice.model;

import java.util.*;

public final class Prescription
{
    private UUID id;
    private Pacient pacient;
    private Map<String,Integer> pills;
    private Date dateOfUse;

    public Prescription(Pacient pacient, Map<String,Integer> pills,Date dateOfUse)
    {
        this.id = UUID.randomUUID();
        this.pacient = pacient;
        this.pills = pills;
        this.dateOfUse = dateOfUse;
    }

    public Prescription(Pacient pacient, Map<String,Integer> pills,Date dateOfUse,UUID id)
    {
        this(pacient,pills,dateOfUse);
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

    public Map<String, Integer> getPills() {
        return pills;
    }

    public void setPills(Map<String, Integer> pills) {
        this.pills = pills;
    }

    public Date getDateOfUse() {
        return dateOfUse;
    }

    public void setDateOfUse(Date dateOfUse) {
        this.dateOfUse = dateOfUse;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Prescription{");
        sb.append("id=").append(id);
        sb.append(", pacient=").append(pacient);
        sb.append(", pills=").append(pills);
        sb.append(", dateOfUse=").append(dateOfUse);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prescription that = (Prescription) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
