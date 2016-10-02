import java.io.FileReader;
import java.util.Scanner;

public class Runner {
	public static void main(String[] args) {
		String filename, request, password1, password2;
		Scanner console, fileContents;
		while (true) {
			System.out.println("Enter the file name (including the file path)\n"
							 + "of the file you would like to modify?");
			console = new Scanner(System.in);
			filename = console.nextLine();
			try {
				fileContents = new Scanner(new FileReader(filename));
				break;
			}
			catch (Exception FileNotFoundException) {
				System.out.println("File not found. Please try again.");
			}
		}
		while (true) {
			System.out.println("Would you like to encrypt or decrypt?");
			console = new Scanner(System.in);
			request = console.nextLine();
			if (!request.equals("encrypt") && !request.equals("decrypt")) {
				System.out.println("Invalid request. Please try again.");
			}
			else {
				break;
			}
		}
		while (true) {
			if (request.equals("encrypt")) {
				System.out.println("Please enter the desired password?");
			}
			else {
				System.out.println("Please enter the password?");
			}
			console = new Scanner(System.in);
			password1 = console.nextLine();
			if (request.equals("encrypt")) {
				System.out.println("Please re-enter the desired password?");
			}
			else {
				System.out.println("Please re-enter the password?");
			}
			console = new Scanner(System.in);
			password2 = console.nextLine();
			if (!password1.equals(password2)) {
				System.out.println("Passwords do not match. Please try again.");
			}
			else {
				break;
			}
		}
		try {
			if (request.equals("encrypt")) {
				System.out.println(EncryptUtils.encrypt(fileContents.nextLine(), password1)); // just to test
			}
			else {
				System.out.println(EncryptUtils.decrypt(fileContents.nextLine(), password1)); // just to test
			}
			if (request.equals("encrypt")) {
				System.out.print("Encryption successful.");
			}
			else {
				System.out.print("Decryption successful.");
			}
		}
		catch (Exception e) {
			if (request.equals("encrypt")) {
				System.out.print("Encryption unsuccessful. Exception thrown:");
			}
			else {
				System.out.print("Decryption unsuccessful. Exception thrown:");
			}
			System.out.println(e);
		}
	}
}