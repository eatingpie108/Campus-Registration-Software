package classRegistration;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Student extends User implements StudentInterface, Serializable{
	private static final long serialVersionUID = 1L;
	// These are the data fields associated with the Student Class
	private ArrayList<Course> courses = null; 
	
	
	Student(){}
	Student(String u, String p, String f, String l, ArrayList<Course> cs){
		super(u,p,f,l);
		this.courses = cs;
		
	}
	public ArrayList<Course> getCourses(){
		return this.courses;
	}
	
	/*These are the methods associated with the Student Class that allow them to perform the assigned classes
	 * I essentially drew out how all of these methods would occur within the program here as opposed to defnining them within the main program,
	 * so that all of the "Student" related methods were in one place
	 */
	
	// This method just takes the ArrayList of all of the Courses, and then prints them out iteratively
	public void viewCourses(ArrayList<Course> allCourses) {
		System.out.printf("  |%45s||%12s||%20s||%25s||%20s||%20s||%20s| \n  ", "Course Name", "Course ID","Section Number","Course Instructor","Course Location", "Students Registered", "Max Students");
		for(int i = 0; i < allCourses.size(); i++) {
			System.out.printf("|%45s||%12s||%20d||%25s||%20s||%20d||%20d| \n  ", allCourses.get(i).getCoursename(), allCourses.get(i).getCourseID(),allCourses.get(i).getSectionNumber(),allCourses.get(i).getCourseInstructor(),allCourses.get(i).getCourseLocation(), allCourses.get(i).getCurrentNumStudents(), allCourses.get(i).getMaxStudents());
		}
		System.out.println("");
	}
	// This does the same as above, except it checks if the number of students registered isnt the same as the max students 
	public void viewCoursesNotFull(ArrayList<Course> allCourses) {
		System.out.printf("  |%45s||%12s||%20s||%25s||%20s||%20s||%20s| \n  ", "Course Name", "Course ID","Section Number","Course Instructor","Course Location", "Students Registered", "Max Students");
		for(int i = 0; i < allCourses.size(); i++) {
			if(allCourses.get(i).getCurrentNumStudents() != allCourses.get(i).getMaxStudents()) {
				System.out.printf("|%45s||%12s||%20d||%25s||%20s||%20d||%20d| \n  ", allCourses.get(i).getCoursename(), allCourses.get(i).getCourseID(),allCourses.get(i).getSectionNumber(),allCourses.get(i).getCourseInstructor(),allCourses.get(i).getCourseLocation(), allCourses.get(i).getCurrentNumStudents(), allCourses.get(i).getMaxStudents());
			}
		}
		System.out.println("");
	}
	// This method registers a student for the course
	public void registerCourse(ArrayList<Course> allCourses,ArrayList<User> allUsers) {
		//First it gathers the info of the course 
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the course ID of the course that you would like to register for?");
		String ci = null;
		String cn = null;
		boolean flag = true;
		int sn = 0;
		try {
			ci = in1.readLine();
			System.out.println("What is the course name of the course that you would like to register for?");
			cn = in1.readLine();
			System.out.println("What is the section number of the course that you would like to register for?");
			sn = Integer.parseInt(in1.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Then it searches for the course
		for(int i = 0; i < allCourses.size(); i++) {
			if(allCourses.get(i).getCourseID().equals(ci) && allCourses.get(i).getSectionNumber() == sn) {
				allCourses.get(i).addStudent(this.getFullName(),allUsers);//And finally adds the student to the course 
				flag =false;
				break;
			}
		}
		if(flag) {
			System.out.println("Course not found.");
		}
		System.out.println("Done. Back to the menu!");
		System.out.println("");
		
	}
	// This method withdraws a student from a coruse.
	public void withdrawCourse(ArrayList<Course> allCourses,ArrayList<User> allUsers) {
		// First the coruse info is inputted 
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is the course ID of the course that you would like to widthdraw from?");
		String ci = null;
		String cn = null;
		int sn = 0;
		boolean coursefound = false;
		try {
			ci = in1.readLine();
			System.out.println("What is the course name of the course that you would like to widthdraw from?");
			cn = in1.readLine();
			System.out.println("What is the section number of the course that you would like to widthdraw from?");
			sn = Integer.parseInt(in1.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Then the course is searched for and then deleted! 
		for(int i = 0; i < allCourses.size(); i++) {
			if(allCourses.get(i).getCourseID().equals(ci) && allCourses.get(i).getSectionNumber() == sn) {
				allCourses.get(i).removeStudent(this.getFullName(),allUsers);
				coursefound = true;
			}
		}
		if(!coursefound) {
			System.out.println("Course not found");
		}
		System.out.println("Done. Back to the menu!");
		System.out.println("");
	}
	// This method Displays all of the current course from the courses Array of the student object 
	public void viewCurrenCourses() {
		if(this.courses.size() != 0) {
			System.out.println("These are your courses:");
			for(int i = 0; i < this.getCourses().size(); i++) {
				System.out.printf("|%45s| \n", this.courses.get(i).getCoursename());
			}
		}else {
			System.out.println("You are not registered for any courses");
		}
		System.out.println("");
	}
	// This method just returns exit.
	public boolean Exit() {
		return false;
	}
	
	

}
