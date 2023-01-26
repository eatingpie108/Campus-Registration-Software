package classRegistration;
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Comparable<Course>, Serializable{
	//These are the data feilds for the Course class 
	private String courseName = null;
	private String courseID = null;
	private int maxStudents;
	private int currentNumStudents;
	private ArrayList<String> nameStudentsRegistered = new ArrayList<String>(); // This is stored as an ArrayList of Strings because you just need the name
	private int sectionNumber;
	private String courseInstructor = null;
	private String courseLocation = null;
	
	// These are the no args constructors and the args constructors for the Course 
	Course() {}
	
	Course(String cn, String ci, int ms, int cns, int sn, String cin, String cl){
		this.courseName = cn;
		this.courseID = ci;
		this.maxStudents = ms;
		this.currentNumStudents = cns;
		this.sectionNumber = sn;
		this.courseInstructor = cin;
		this.courseLocation = cl;
	}
	
	// These are the getters and setters for each of the data fields in an instance of Course
	
	public void setCoursename(String cn) {
		this.courseName = cn;
	}
	public String getCoursename() {
		return this.courseName;
	}
	public void setCourseID(String ci) {
		this.courseID = ci;
	}
	public String getCourseID() {
		return this.courseID;
	}
	public void setMaxStudents(int ms) {
		this.maxStudents = ms;
	}
	public int getMaxStudents() {
		return this.maxStudents;
	}
	public void setCurrentNumStudents(int cns) {
		this.currentNumStudents = cns;
	}
	public int getCurrentNumStudents() {
		return this.currentNumStudents;
	}
	public void setNameStudentsRegistered(ArrayList<String> nsr) {
		this.nameStudentsRegistered = nsr;
	}
	public ArrayList<String> getNameStudentsRegistered(){
		return this.nameStudentsRegistered;
	}
	public void setSectionNumber(int sn) {
		this.sectionNumber = sn;
	}
	public int getSectionNumber() {
		return this.sectionNumber;
	}
	public void setCourseInstructor(String ci) {
		this.courseInstructor = ci;
	}
	public String getCourseInstructor() {
		return this.courseInstructor;
	}
	public void setCourseLocation(String cl) {
		this.courseLocation = cl;
	}
	public String getCourseLocation() {
		return this.courseLocation;
	}
	// These are the methods that courses actually do! 
	
	public void addStudent(String name, ArrayList<User> allUsers) {
		if(this.currentNumStudents != this.maxStudents) {
			ArrayList<String> directory = this.getNameStudentsRegistered();
			directory.add(name);
			this.currentNumStudents += 1;
			for(int i = 1; i < allUsers.size();i++) {
				if(allUsers.get(i).getFullName().equals(name) && allUsers.get(i) instanceof Student) {
					ArrayList<Course> theirCourses = ((Student) allUsers.get(i)).getCourses();
					theirCourses.add(this);
				}
			}
		}
		else 
		{
			System.out.println("Too many students already registered!");
		}
	}
	
	public void removeStudent(String name, ArrayList<User> allUsers) {
		
		if(this.nameStudentsRegistered.contains(name)) {
			this.nameStudentsRegistered.remove(name);
			this.currentNumStudents -= 1;
			for(int i = 1; i < allUsers.size();i++) {
				if(allUsers.get(i).getFullName().equals(name) && allUsers.get(i) instanceof Student) {
					ArrayList<Course> theirCourses = ((Student) allUsers.get(i)).getCourses();
					theirCourses.remove(this);
				}
			}
		} else {
			System.out.println("Invalid Operation");
		}
	}
	
	// Overriding the compareTo() method so that I can use the default sort function for ArrayLists!
	public int compareTo(Course c) {
		if(this.currentNumStudents == c.getCurrentNumStudents()) {
			return 0;
		}
		else if (this.currentNumStudents < c.getCurrentNumStudents()) {
			return -1;
		}
		else {
			return 1;
		}
	}


}
