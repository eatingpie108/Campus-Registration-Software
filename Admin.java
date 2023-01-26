package classRegistration;
import java.io.BufferedReader;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileWriter;

public class Admin extends User implements AdminInterface, Serializable{
	private static final long serialVersionUID = 1L;
	Admin(){
		super("Admin","Admin001",null,null);
	}
	// These are the methods associated with the Admin class 
	/*
	 * This first method creates a Course within the system.
	 * It takes the ArrayList of all courses as in input so that it can add it after the user has inputed all the methods 
	 */
	public void createCourse(ArrayList<Course> allCourses) {
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("What is the course name?");
			String cn = in1.readLine();
			System.out.println("What is the course ID?");
			String cid = in1.readLine();
			System.out.println("What is the max number of students?");
			int ms = Integer.parseInt(in1.readLine());
			System.out.println("What is the section number?");
			int sn = Integer.parseInt(in1.readLine());
			System.out.println("What is the name of the course instructor?");
			String ci = in1.readLine();
			System.out.println("What is the location of the course?");
			String cl = in1.readLine();
			allCourses.add(new Course(cn,cid,ms,0,sn, ci,cl));
			} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println("Done. Back to the menu!");
		System.out.println("");
	}
	
	/*
	 * The next method is the delete course method
	 * This method has the full course ArrayList as a parameter, and runs though it until it finds the matching course ID and sectio, then deletes it
	 */
	public void deleteCourse(ArrayList<Course> allCourses) {
		String cid = null;
		int sn = 0;
		boolean flag = false;
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the course ID of the coruse you would like to delete?");
		try {
			cid = in1.readLine();
		System.out.println("What is the section numeber of the class you would like to delete? ");
			sn = Integer.parseInt(in1.readLine());
		for(int i = 0; i <allCourses.size(); i ++) {
			if(allCourses.get(i).getCourseID().equals(cid) && sn == allCourses.get(i).getSectionNumber()) {
				allCourses.remove(i);
				flag = true;
				break;
			}
		}
		if(!flag) {
			System.out.println("Course not found");
		}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done. Back to the menu!");
		System.out.println("");
	}
	
	// This is the method that edits the courses 
	public void editCourse(ArrayList<Course> allCourses, ArrayList<User> allUsers) {
		String cid = null;
		int sn = 0;
		int choice = 0;
		Course selectedClass = null;
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		//This section asks the user for the CID and section number, then searches for that course in the courses ArrayList
		System.out.println("What is the course ID of the coruse you would like to edit?");
		try {
			cid = in1.readLine();
		System.out.println("What is the section numeber of the class you would like to edit? ");
			sn = Integer.parseInt(in1.readLine());
		for(int i = 0; i <allCourses.size(); i ++) {
			if(cid.equals(allCourses.get(i).getCourseID()) && sn == allCourses.get(i).getSectionNumber()) {
				selectedClass = allCourses.get(i);
				break;
			}
		}
		if(selectedClass == null) {
			System.out.println("Class not found");
		}
		else {
		//After the course is found, the user is asked what they want to do
		System.out.println("What would you like to edit? Max Students (1) | Registered Students (2) | Section Number (3) | Course Instructor (4) | Course Location (5)");
			choice = Integer.parseInt(in1.readLine());
			
			if(choice == 1) {
				System.out.println("What would you like to change the max number of students to?");
				selectedClass.setMaxStudents(Integer.parseInt(in1.readLine()));
			}
			else if (choice == 2) {
				System.out.println("Would you like to remove or add a student? Add (1) | Remove (2) ");
				int choice2 = Integer.parseInt(in1.readLine());
				if( choice2 == 1) {
					System.out.println("Who would you like to add? (Firstname Lastname)");
					selectedClass.addStudent(in1.readLine(), allUsers);
				}
				else if(choice2 == 2) {
					System.out.println("Who would you like to remove? (Firstname Lastname)");
					selectedClass.removeStudent(in1.readLine(), allUsers);
				}
				
			}
			else if (choice == 3) {
				System.out.println("What would you like to change the section number to?");
				selectedClass.setSectionNumber(Integer.parseInt(in1.readLine()));
			}
			else if (choice == 4) {
				System.out.println("Who is the new Instructor");
				selectedClass.setCourseInstructor(in1.readLine());
			}
			else if (choice == 5) {
				
				System.out.println("Where is the course now located");
				selectedClass.setCourseLocation(in1.readLine());
			}
			else {
				System.out.println("Invalid choice");
			}
		}
		}catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done. Back to the menu!");
		System.out.println("");
		
	}
	// This method displays the info on all of the courses
	public void courseInfo(ArrayList<Course> allCourses) {
		String cid = null;
		int sn = 0;
		Course selectedClass = null;
		// This is the same search method that I used above 
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the course ID of the coruse you would like to view?");
		try {
			cid = in1.readLine();
			System.out.println("What is the section numeber of the class you would like to view? ");
			sn = Integer.parseInt(in1.readLine());
			for(int i = 0; i <allCourses.size(); i ++) {
				if(cid.equals(allCourses.get(i).getCourseID()) && sn == allCourses.get(i).getSectionNumber()) {
					selectedClass = allCourses.get(i);
					break;
				}
			}
			if(selectedClass == null) {
				System.out.println("Class not found");
			}
			else {
				System.out.printf("  |%45s||%12s||%20s||%25s||%20s||%20s||%20s| \n  ", "Course Name", "Course ID","Section Number","Course Instructor","Course Location", "Students Registered", "Max Students");
				System.out.printf("|%45s||%12s||%20d||%25s||%20s||%20d||%20d| \n  ", selectedClass.getCoursename(), selectedClass.getCourseID(),selectedClass.getSectionNumber(),selectedClass.getCourseInstructor(),selectedClass.getCourseLocation(), selectedClass.getCurrentNumStudents(), selectedClass.getMaxStudents());
			}
			
		}catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		
	}
	//This makes a new student by asking the input of all of the data feilds and adding the Student to the ArrayList of Users 
	public void registerStudent(ArrayList<User> list) {
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		String fn = null;
		String ln = null;
		String user = null;
		String pass = null;
		try {
			System.out.println("What is the student's first name?");
			fn = in1.readLine();
			System.out.println("What is the student's last name?");
			ln = in1.readLine();
			System.out.println("What is the account's username?");
			user = in1.readLine();
			System.out.println("What is the account's password?");
			pass = in1.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done. Back to the menu!");
		System.out.println("");
		
		list.add(new Student(user,pass,fn,ln,new ArrayList<Course>()));
		
	}
	// This method works by printing out the important data fields of every class in the Course ArrayList
	public void viewCourses(ArrayList<Course> allCourses) {
		System.out.printf("  |%45s||%12s||%20s||%25s||%20s||%20s||%20s| \n  ", "Course Name", "Course ID","Section Number","Course Instructor","Course Location", "Students Registered", "Max Students");
		for(int i = 0; i < allCourses.size(); i++) {
			System.out.printf("|%45s||%12s||%20d||%25s||%20s||%20d||%20d| \n  ", allCourses.get(i).getCoursename(), allCourses.get(i).getCourseID(),allCourses.get(i).getSectionNumber(),allCourses.get(i).getCourseInstructor(),allCourses.get(i).getCourseLocation(), allCourses.get(i).getCurrentNumStudents(), allCourses.get(i).getMaxStudents());
		}
		System.out.println("");
	}
	//This method does the same as the above method, except it ads a check for if the current number of students == the max number of students before printing.
	public void viewFullCourses(ArrayList<Course> allCourses) { 
		System.out.printf("  |%45s||%12s||%20s||%25s||%20s||%20s||%20s| \n  ", "Course Name", "Course ID","Section Number","Course Instructor","Course Location", "Students Registered", "Max Students");
		for(int i = 0; i < allCourses.size(); i++) {
			if(allCourses.get(i).getCurrentNumStudents() == allCourses.get(i).getMaxStudents()) {
				System.out.printf("|%45s||%12s||%20d||%25s||%20s||%20d||%20d| \n  ", allCourses.get(i).getCoursename(), allCourses.get(i).getCourseID(),allCourses.get(i).getSectionNumber(),allCourses.get(i).getCourseInstructor(),allCourses.get(i).getCourseLocation(), allCourses.get(i).getCurrentNumStudents(), allCourses.get(i).getMaxStudents());
			}
		}
		System.out.println("");
		
	}
	// This method searches the Userlist for a student given an input, and then displays their courses 
	public void displayStudentCourses(String first, String last, ArrayList<User> allUsers) {
		Student currentStudent = null;
		for(int i = 0; i< allUsers.size(); i++) {
			if(first.equals(allUsers.get(i).getFirstname()) && last.equals(allUsers.get(i).getLastname())) {
				currentStudent = (Student) allUsers.get(i);
				for(int j = 0; j < currentStudent.getCourses().size(); j++) {
					System.out.printf("|%45s|", currentStudent.getCourses().get(j).getCoursename());
				}
			}
			if(currentStudent == null) {
				System.out.println("Student not found");
			}
		}
		System.out.println("");
	}
	
	//This method uses the Filewriter class to write all the courses that are full into a file 
	public void writeFullCourses(ArrayList<Course> allCourses) {
		String fileName = "FullCourses.txt";
		try {
			ArrayList<String> fullCourses = new ArrayList<String>();
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("Full Courses:  \n");
			for(int i = 0; i < allCourses.size(); i++) {
				if(allCourses.get(i).getCurrentNumStudents() == allCourses.get(i).getMaxStudents()) {
						bufferedWriter.append(allCourses.get(i).getCoursename());
						bufferedWriter.newLine();
				}
			}
			System.out.println("Done. Back to the menu!");
			bufferedWriter.close();
		}catch(IOException exk) {
			exk.printStackTrace();
		}
	}
	// This method sorts the ArrayList of Courses based off number of students using the default methods 
	public void sortCourses(ArrayList<Course> courses) {
		// Since I overrided the compareTo method in the Course class to compare courses based off of current Numeber of Students registered, the sort function of ArrayLists sorts based of that value for each class
		Collections.sort(courses);
		System.out.println("Courses Sorted.");
		System.out.println("Done. Back to the menu! \n");
	}
	// This method searches for a course in the ArrayList of Courses, then displays each name in theArrayList of registered students 
	public void displayRegisteredStudents(ArrayList<Course> allCourses) {
		String cid = null;
		int sn = 0;
		Course selectedClass = null;
		// This is the same search method that I used above 
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the course ID of the coruse you would like to view?");
		try {
			cid = in1.readLine();
			System.out.println("What is the section numeber of the class you would like to view? ");
			sn = Integer.parseInt(in1.readLine());
			for(int i = 0; i <allCourses.size(); i ++) {
				if(cid.equals(allCourses.get(i).getCourseID()) && sn == allCourses.get(i).getSectionNumber()) {
					selectedClass = allCourses.get(i);
					break;
				}
			}
			if(selectedClass == null) {
				System.out.println("Class not Found...");
			}
			else {
				System.out.println("These are the students registered: ");
				for(int i = 0; i < selectedClass.getNameStudentsRegistered().size(); i++) {
					System.out.println(allCourses.get(i).getNameStudentsRegistered().get(i));
				}
			}
		}catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		
	}
	// This method returns Exit.
	public boolean exit() {
		return false;
	}

}
