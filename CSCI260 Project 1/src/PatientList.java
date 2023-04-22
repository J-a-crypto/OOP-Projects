import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class PatientList  {
    private Patient head;
    private Patient tail;

    public PatientList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addPatient(String name, String id, String address, int height, double weight, Date dateOfBirth,
            Date initialVisitDate, Date lastVisitDate) {
    	
    	// I want to check if ID already exists, this means each ID will be unique for each patient
    	Patient curr = head;
    	while(curr!= null) {
    		if(curr.getId().equals(id)) {
    			System.out.println("Patient ID already exists. Please use a different ID.");
    			return;
    		}
    		curr = curr.getNext();
    	}
    	
    	
        Patient newPatient = new Patient(name, id, address, height, weight, dateOfBirth, initialVisitDate, lastVisitDate);
        if (isEmpty()) {
            head = newPatient;
            tail = newPatient;
        } else {
            tail.setNext(newPatient);
            tail = newPatient;
        }
    }
    
    public void displayList() {
        Patient current = head;
        while (current != null) {
            System.out.println("Name: "+ current.getName() + " ID: " + current.getId() + " Address: " + current.getAddress() + " Height:" +
                    current.getHeight() + " Weight: " + current.getWeight() + " DOB: " + current.getDateOfBirth() + " DOIV: " +
                    current.getInitialVisitDate() + " DOLV: " + current.getLastVisitDate());
            current = current.getNext();
        }
    }
    public void readFromFile(String fileName) {
    	try {
    		File file = new File(fileName); 
    			Scanner scanner = new Scanner(file);
    			DateFormat df = new SimpleDateFormat ("yyyy/MM/dd");
    			while(scanner.hasNextLine()) {
    				String line = scanner.nextLine();
    				String[] parts = line.split(",");
    				String name = parts[0];
    				String iD = parts[1];
    				String address = parts[2];
    				int height = Integer.parseInt(parts[3].trim());
    				double weight = Double.parseDouble(parts[4]);
    				Date dateOfBirth = df.parse(parts[5]);
    				Date initialVisitDate = df.parse(parts[6]);
    				Date lastVisitDate = df.parse(parts[7]);
    				
    				addPatient(name,iD,address,height,weight,dateOfBirth,initialVisitDate,lastVisitDate);
    				
    			} scanner.close();
    			}catch(FileNotFoundException e ) {
    				System.out.println("File not found.");
    			}catch(ParseException e) {
    				System.out.println("Error parsing date." +e.getMessage());
    			}
    	}
    	
    public void printOverduePatients() {
        Patient curr = head;
        while (curr != null) {
            if (curr.isOverdue()) {
                System.out.println(curr.getName() + " is overdue for a visit.");
            }
            curr = curr.getNext();
        }
    }
    
    public int getYoungest() {
        if (isEmpty()) {
            return -1;
        }
        int youngest = Integer.MAX_VALUE;
        Patient curr = head;
        while (curr != null) {
            int age = curr.get_age();
            if (age < youngest) {
                youngest = age;
            }
            curr = curr.getNext();
        }
        return youngest;
    }


    public double avg_Age() {
    	double total = 0;
    	int numPatients =0;
    	Patient curr = head;
    	while(curr !=null) {
    		total += curr.get_age();
    		numPatients++;
    		curr = curr.getNext();
    	}
    	return total/numPatients;
    }
    
    public void addPatientToFile(String fileName, String name, String iD, String address, int height, double weight, Date dateOfBirth, Date initialVisitDate, Date lastVisitDate) {
        // create a new patient object with the given information
        //Patient newPatient = new Patient(name, iD, address, height, weight, dateOfBirth, initialVisitDate, lastVisitDate);
        // add the patient to the linked list
        addPatient(name, iD, address, height, weight, dateOfBirth, initialVisitDate, lastVisitDate);
        // write the patient's information to the output file
        try {
            FileWriter writer = new FileWriter(fileName, true); // true appends to file
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            writer.write(String.format("%s,%s,%s,%d,%.2f,%s,%s,%s\n",
                name, iD, address, height, weight, df.format(dateOfBirth), df.format(initialVisitDate), df.format(lastVisitDate)));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public Patient InfoByID(String n) {
    	Patient curr = head;
    	boolean found = false;
    	while(curr!=null) {
    		if(curr.getId().equals(n)) {
    			System.out.print(curr.toString());
    			found = true;
    			break;
    		}else
    		curr=curr.getNext();
    	}
    	
    	if(!found) {
    		System.out.println("Patient with ID# " +n + " not found.");
    	}
    	return null;
    }
    
    public void DeleteByID(String n) {
    	Patient curr = head;
    	Patient prev = null;
    	boolean found = false;
    	while(curr!=null) {
    		if(curr.getId().equals(n)) {
    			if(prev == null) {
    				head = curr.getNext();
    			} else {
    				prev.setNext(curr.getNext());
    			}
    			found = true;
    			break;
    		}
    		prev = curr;
    		curr=curr.getNext();
    	}
    	if(!found) {
    		System.out.println("Patient with ID# " + n + " not found.");
    	} else {
    		System.out.println("Patient with ID# " + n + " deleted from the list.");
    	}
    }
    public void run() throws ParseException {
    	Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		while (!quit) {
			System.out.println("Welcome To The PatientList Program!");
			System.out.println("Please select an option: ");
			System.out.println("1. Display List");
			System.out.println("2. Add a new patient");
			System.out.println("3. Show information for a patient");
			System.out.println("4. Delete a patient");
			System.out.println("5. Show average patient age");
			System.out.println("6. Show information for the youngest patient");
			System.out.println("7. Show notifcaiton list");
			System.out.println("8. Quit");
			int choice = scanner.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Patient List:");
				displayList();
				break;
			case 2:
				System.out.println("Please Enter Information Accordingly!!");
				scanner.nextLine();
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				System.out.print("Enter ID: ");
                String iD = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter height (inches): ");
                int height = scanner.nextInt();
                System.out.print("Enter weight (pounds): ");
                double weight = scanner.nextDouble();
                scanner.nextLine(); // consume newline character
                System.out.print("Enter date of birth (yyyy/MM/dd): ");
                String dobStr = scanner.nextLine();
                System.out.print("Enter date of initial visit (yyyy/MM/dd): ");
                String initStr = scanner.nextLine();
                System.out.print("Enter date of last visit (yyyy/MM/dd): ");
                String lastStr = scanner.nextLine();
                //parse the dates
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date dob = df.parse(dobStr);
                Date init = df.parse(initStr);
                Date last = df.parse(lastStr);
                
                addPatientToFile("patients.txt", name, iD, address, height, weight, dob, init, last);
                System.out.println("Patient added");
				break;
			case 3:
				System.out.println("Please Input the ID Number of the Patient you are looking for:");
				scanner.nextLine();
				String id= scanner.nextLine();
				InfoByID(id);
				
				break;
			case 4:
				System.out.println("Please Input Patient ID to Remove: ");
				scanner.nextLine();
				String id1 = scanner.nextLine();
				DeleteByID(id1);
				break;
			case 5:
				System.out.println("The Average Age Among our Patients is"+avg_Age());
				break;
			case 6:
				getYoungest();
				System.out.println("The Youngest BirthYear: "+getYoungest());
				break;
			case 7:
				printOverduePatients();
				break;
			case 8:
				quit=true;
				break;
			default:
				System.out.println("Invalid choice.");
			}
		}
		scanner.close();
	}
    
    }



