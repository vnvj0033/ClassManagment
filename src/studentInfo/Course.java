package studentInfo;


import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable, Cloneable {

    private String department;
    private String number;
    private Date effectiveDate;

    public Course(String department, String number) {
        this.department = department;
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date date) {
        this.effectiveDate = date;
    }

    @Override
    protected Course clone()  {
        Course copy = null;
        try{
            copy = (Course) super.clone();
        }catch (CloneNotSupportedException impossible){
            throw new RuntimeException("unable to clone");
        }
        return copy;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (this.getClass() != object.getClass())
            return false;
        Course that = (Course) object;
        return this.department.equals(that.department) && this.number.equals(that.number);
    }

    @Override
    public int hashCode() {
        final int hashMultiplier = 41;
        int result = 7;
        result = result * hashMultiplier + department.hashCode();
        result = result * hashMultiplier + number.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return department + " " + number;
    }
}