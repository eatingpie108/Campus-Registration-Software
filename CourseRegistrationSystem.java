package classRegistration;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CourseRegistrationSystem {

	public static void main(String[] args) {
		
		// First I will try to set up the data within the program 
		ArrayList<Course> allCourses = new ArrayList<Course>();
		ArrayList<User> allUsers = new ArrayList<User>();
		
		Data data = new Data(allUsers, allCourses);
		// Below is the code that first tries to read from the Employee serialized code, then if not reads from the csv file provided
		try {
			// First try to see if there is a Data file 
			FileInputStream fis = new FileInputStream("Data.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				// Gets the data file and the user as well as Course "database" 
				data = (Data) ois.readObject();
				allCourses = data.getCourses();
				allUsers = data.getUser();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//make sure to close all streams
			fis.close();
			ois.close();
		}
		catch(FileNotFoundException fnf) {
			// If the file is not found, here we read from the csv file and initialize the one and only Admin.
			String fileName = "MyUniversityCoursesFile.csv";
			String line = null;
			allUsers.add(new Admin());
			try{
				FileReader fileReader = new FileReader(fileName);
		
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				bufferedReader.readLine(); // Skips the headers so i only get information! 
				while((line = bufferedReader.readLine()) != null) { //While there is information to be read, the Scanner stores it into the Arrays
					String cn = null; String ci = null; int ms = 0; int cns = 0; String placeholder; int sn = 0; String cin = null; String cl = null;
						Scanner input = new Scanner(line).useDelimiter(",");
						while(input.hasNext()) {
							cn = input.next();
							ci = input.next();
							ms = Integer.parseInt(input.next());
							cns = Integer.parseInt(input.next());
							placeholder = input.next();
							cin = input.next();
							sn = Integer.parseInt(input.next());
							cl = input.next();
					}
						input.close();
					allCourses.add(new Course(cn,ci,ms,cns,sn,cin,cl));
			}
				bufferedReader.close();
			}
			
			catch(FileNotFoundException ex){
				System.out.println( "Unable to open file '" + fileName + "'");
				ex.printStackTrace();
			}
	
			catch (IOException ex) {
				System.out.println( "Error reading file '" + fileName + "'");
				ex.printStackTrace();
			}
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		/*
		 * This is the end of the initial code that determines if it's the first log in or not, and gets the data 
		 * Now we will begin with the log in process and what each of the users can do
		 */
		
		boolean adminLoggedIn = false; 
		boolean studentLoggedIn = false;
		// The two variables above are going to act as flags for the continuation of operation for whoever is logged in.
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); //This is the main input reader object 
		
		// I set everything in a try block because if it doesn't work there is no reason to continue the program
		try {
			User unspecCurrentUser = null;
			System.out.println("What is the username?");
			String username = input.readLine();
			System.out.println("What is the password");
			String password = input.readLine();
			// The loop below checks if the username and password matches with any of the users in the system
			for(int i = 0; i < allUsers.size(); i++) {
				if(allUsers.get(i).getUsername().equals(username) && allUsers.get(i).getPassword().equals(password)) {
					if(allUsers.get(i) instanceof Admin) {
						adminLoggedIn = true;
						unspecCurrentUser = allUsers.get(i); // specifies the current user
						// If the user it matches with is an Admin, then the system starts as an admin
					}
					else { // Otherwise, it logs in as a student
						unspecCurrentUser = allUsers.get(i); // specifies which user is the current user
						studentLoggedIn = true;
					}
				}
			}
			if(!adminLoggedIn && !studentLoggedIn) {
				//prints out a message if a user is not found 
				System.out.println("Username and password combination not recognized.");
			}
			while(adminLoggedIn) { // Setting up the Admin user experience
				Admin currentUser = (Admin) unspecCurrentUser;// Casts the user as an Admin so they can use the Admin methods 
				welcomeMessage(currentUser);
				System.out.println("What would you like to do? (1) Add Course | (2) Delete Course | (3) Edit Course | (4) View Single Course Info | (5) Register a Student | (6) View all Courses " +
			"| (7) View Full Courses | (8) Display Students Registered for a Course | (9) Display a Student's Courses | (10) Write Full Courses to a Document | (11) Sort Courses by Number of students registered | (12) Display Account Info | (13) Exit");
				int choice = Integer.parseInt(input.readLine());
				// Begins the tree of options for the Admin
				if(choice == 1) {
					currentUser.createCourse(allCourses);
				}
				else if(choice == 2) {
					currentUser.deleteCourse(allCourses);
				}
				else if(choice == 3) {
					currentUser.editCourse(allCourses,allUsers);
				}
				else if(choice == 4) {
					currentUser.courseInfo(allCourses);
				}
				else if(choice == 5) {
					currentUser.registerStudent(allUsers);
				}
				else if(choice == 6) {
					currentUser.viewCourses(allCourses);
				}
				else if(choice == 7) {
					currentUser.viewFullCourses(allCourses);
				}
				else if(choice == 8) {
					currentUser.displayRegisteredStudents(allCourses);
				}
				else if(choice == 9) {
					System.out.println("What is the student's First Name?");
					String first = input.readLine();
					System.out.println("What is the student's Last Name");
					String last = input.readLine();
					currentUser.displayStudentCourses(first, last, allUsers);
				}
				else if(choice == 10) {
					currentUser.writeFullCourses(allCourses);
				}
				else if(choice == 11) {
					currentUser.sortCourses(allCourses);
				}
				else if(choice == 12) {
					System.out.println(currentUser);
				}
				else if(choice == 13) {
					adminLoggedIn = currentUser.exit();
				}
				else {
					System.out.println("Invalid input");
				}
				System.out.println("");
				
			}
			while(studentLoggedIn) { // Setting up the Student log in experience
				Student currentUser = (Student) unspecCurrentUser; // Casts the user as a Student so they can use the Student methods
				welcomeMessage(currentUser);
				System.out.println("What would you like to do? (1) View all courses | (2) View Courses that are not full | (3) Register for a Course | (4) Withdraw from a Course | (5) View My Courses | (6) Display Account Info | (7) Exit");
				int choice = Integer.parseInt(input.readLine());
				if(choice == 1) {
					currentUser.viewCourses(allCourses);
				}
				else if(choice == 2) {
					currentUser.viewCoursesNotFull(allCourses);
				}
				else if(choice == 3) {
					currentUser.registerCourse(allCourses,allUsers);
				}
				else if(choice == 4) {
					currentUser.withdrawCourse(allCourses,allUsers);
				}
				else if(choice == 5) {
					currentUser.viewCurrenCourses();
				}
				else if(choice == 6) {
					System.out.println(currentUser);
				}
				else if(choice == 7) {
					studentLoggedIn = currentUser.Exit();
				}
				System.out.println("");
				
			}
			
			/*
			 * This sets up the "End of program" code
			 * Here I print out a goodbye message, then serialize the data object!
			 */
			 // closes the input stream
			System.out.println("Exiting program and saving changes...");
			try {
				FileOutputStream fos = new FileOutputStream("Data.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(data);
				oos.close();
				fos.close();
			} catch(IOException ioe){
				ioe.printStackTrace();
			}
			input.close();
			
		} catch(IOException e) {

			e.printStackTrace();
		}
		
	}
	// These are two Overloaded messages that print out a specific message depending on who is logged in 
	public static void welcomeMessage(Admin a) {
		System.out.println("Welcome to the Admin Hub!");
	}
	public static void welcomeMessage(Student s) {
		System.out.println("Welcome to the Student Hub!");
	}
}


