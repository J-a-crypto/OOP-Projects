import java.text.ParseException;

public class Tester {

	public static void main(String[] args) throws ParseException {
		
		
	
		PatientList List = new PatientList();
		List.readFromFile("patient.txt");
		List.displayList();
		List.run();
		
		//n this case, the year should be passed as 2003 - 1900 to account for the fact that the Date class counts years since 1900. The month argument is 0-based, so 1 represents February. Finally, the day argument is simply 6.
		
		
		
		
		
		
		
		

	

}
}
