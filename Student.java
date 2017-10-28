public class Student implements User {
	private String Name;
	private int FacultyNumber;
	private Course[] Courses;
	private int numberOfCourses;
	
	public Student()
	{
		this.Name="";
		this.FacultyNumber=0;
		this.Courses=new Course [50];
		this.numberOfCourses=0;
	}
	
	public Student(String name,int facultynumber)
	{
		this.Name="";
		this.FacultyNumber=0;
		this.Courses=new Course[50];
		this.numberOfCourses=0;
	}
	public Student(Student student)
	{
		this.Name=student.getName();
		this.FacultyNumber=student.getFacultyNumber();
		this.numberOfCourses=student.getNumberOfCourses();
	 
		
	}
	
	public void addCourse(Course course)
	{
		Courses[numberOfCourses]= course;
		 
		this.numberOfCourses ++;
	}
	
	public Course getCourse(int number)
	{
		return Courses[number];
	}
	
	
	public String getName()
	{
		return this.Name;
	}
	
	public int getFacultyNumber()
	{
		return FacultyNumber; 
		
	}
	
	
	public void setName(String name)
	{
		this.Name=name;
	}
	
	public void setFacultyNumber(int facultyNumber)
	{
		this.FacultyNumber=facultyNumber;
	}
	
	public int getNumberOfCourses()
	{
		return this.numberOfCourses;
	}
	
}