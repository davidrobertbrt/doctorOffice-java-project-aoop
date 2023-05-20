package ro.unibuc.doctorOffice.model;
import java.util.*;
public final class Medic extends Person
{
    private UUID id;

    private Specialization specialization;

    private String phoneNumber;

    public Medic(String firstName,String lastName,Specialization specialization,String phoneNumber)
    {
        super(firstName,lastName);
        this.id = UUID.randomUUID();
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
    }

    public Medic(String firstName,String lastName,Specialization specialization,String phoneNumber,UUID id)
    {
        this(firstName,lastName,specialization,phoneNumber);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Medic{");
        sb.append("id=").append(id);
        sb.append(", specialization=").append(specialization);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Medic medic = (Medic) o;

        return id.equals(medic.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
