import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Patient {
    private String name;
    private String id;
    private String address;
    private int height;
    private double weight;
    private Date dateOfBirth;
    private Date initialVisitDate;
    private Date lastVisitDate;
    private Patient next;
    
//Create the Constructor with the next node
    public Patient(String name, String id, String address, int height, double weight, Date dateOfBirth,
            Date initialVisitDate, Date lastVisitDate) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.initialVisitDate = initialVisitDate;
        this.lastVisitDate = lastVisitDate;
        this.next = null;
    }
//Create set of getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getInitialVisitDate() {
        return initialVisitDate;
    }

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public Patient getNext() {
        return next;
    }

    public void setNext(Patient next) {
        this.next = next;
    }
    
    //Create 
	public int get_age() {
		//You can use the Calendar class in order to 
    	Calendar birthDate = Calendar.getInstance();
    	birthDate.setTime(this.dateOfBirth);
    	Calendar today = Calendar.getInstance();
    	int currentYear = today.get(Calendar.YEAR);
        int birthYear = birthDate.get(Calendar.YEAR);
    	int age = currentYear - birthYear;
    	
    	if(today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
    		age--;
    	}
    	return age;
    }
	public boolean isOverdue() {
	    // Get today's date
	    Date currentDate = new Date();

	    // Calculate the time difference between today's date and the patient's last visit date
	    long timeDifference = currentDate.getTime() - lastVisitDate.getTime();
	    long yearsDifference = TimeUnit.MILLISECONDS.toDays(timeDifference) / 365;

	    // Check if the patient's last visit was more than 3 years ago
	    if (yearsDifference >= 3) {
	        return true;
	    } else {
	        return false;
	    }
	}

	
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String str = "Name: " + name + "\n" +
                "ID: " + id + "\n" +
                "Address: " + address + "\n" +
                "Height: " + height + " cm\n" +
                "Weight: " + weight + " kg\n" +
                "Date of birth: " + df.format(dateOfBirth) + "\n" +
                "Date of initial visit: " + df.format(initialVisitDate) + "\n" +
                "Date of last visit: " + df.format(lastVisitDate);
   return str;
}


}
