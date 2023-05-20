package ro.unibuc.doctorOffice.model;
import java.util.*;

public final class Pacient extends Person
{

    private UUID id;
    private String phoneNumber;
    private String address;
    private Date dateOfBirth;


    public Pacient(String firstName, String lastName,String phoneNumber,String address,Date dateOfBirth) {
        super(firstName, lastName);
        this.id = UUID.randomUUID();
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public Pacient(String firstName,String lastName,String phoneNumber,String address, Date dateOfBirth, UUID id){
        this(firstName,lastName,phoneNumber,address,dateOfBirth);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pacient{");
        sb.append("id=").append(id);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
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

        Pacient pacient = (Pacient) o;

        return id.equals(pacient.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
