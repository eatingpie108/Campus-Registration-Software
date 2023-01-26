package classRegistration;
import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Course> allCourses = new ArrayList<Course>();

	Data(){}
	
	Data(ArrayList<User> u,ArrayList<Course> c){
		this.users = u;
		this.allCourses = c;
		
	}
	
	public void setUser(ArrayList<User> u) {
		this.users = u;
	}
	public ArrayList<User> getUser(){
		return this.users;
	}
	public void setCourses(ArrayList<Course> c) {
		this.allCourses = c;
	}
	public ArrayList<Course> getCourses(){
		return this.allCourses;
	}
}
