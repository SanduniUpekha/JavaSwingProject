package commandlineprogramming;
import java.util.Scanner;

public class CliProgram {

	public static void main(String[] args) {
		
		System.out.println("Student registration Form");
		System.out.println();
		
		Scanner sc =  new Scanner(System.in);
		 
		System.out.println("Enter your first name: ");
		String firstName = sc.nextLine();
		System.out.println();
		
		System.out.println("Enter your lasrt name: ");
		String lastName = sc.nextLine();
		System.out.println();
		
		System.out.println("Select your gender: Male --> Enter M, Female --> Enter F ");
		char gender = sc.next().charAt(0);
		System.out.println();
		
		System.out.println("Enter your age: ");
		int age = sc.nextInt();
		System.out.println();
		
		System.out.println("Enter your mobile number: ");
		long mobile = sc.nextLong();
		System.out.println();
		
		System.out.println("Enter your weight: ");
		double weight = sc.nextDouble();
		System.out.println();
		System.out.println();
		
		System.out.println("Welcome "+firstName+" "+lastName);
		System.out.println("Your registration details: \nGender:"+gender+"\nAge: "+age+"\nMobile Numbe: "+mobile+"\nWeight:"+weight);
	}
}
