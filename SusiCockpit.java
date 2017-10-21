
public class SusiCockpit implements Susi{
	private Student[] Students;
	private double [] totalCredits;
	private int numberOfStudents;
	
	SusiCockpit()
	{
		Students= new Student[1000];
		numberOfStudents=0;
		totalCredits=new double [1000];
	}
	
	public boolean registerStudent(Student student)
	{
		
		for (Student test : this.Students)
		{
			if(test==student)
				return false;
		}
		Students[this.numberOfStudents]=student;
		totalCredits[this.numberOfStudents]=0;
		this.numberOfStudents++;
		return true;
		
	}
	
	public int getStudentsCount()
	{
		return this.numberOfStudents;
	}
	
	public void addCourse(Student student, Course course )
	{
		
		for(int i=0;i<1000;i++)
		{
			if(Students[i]==student)
			{
				Students[i].addCourse(course);
			totalCredits[i]=totalCredits[i]+course.getCredits();
			}
			
		}
	}
	
	
	public boolean setGrade(Student student, Course course, double grade)
	{
		for(int i=0;i<1000;i++)
		{
			 if(this.Students[i]==student)
				 
			 {
				 for(int j=0;j<50;j++)
				 {
					 if(course.getName().equals(this.Students[i].getCourse(j).getName()))
					 {
						 
						 this.Students[i].getCourse(j).setGrade(grade);
						 return true;
					 }
				 }
			 }
			
		}
		return false;
	}
	
	public double getTotalCredits(Student student)
	{
		for(int i=0;i<1000;i++)
		{
			if(this.Students[i]==student)
				return this.totalCredits[i];
		}
		  
			return 0;
		
	}
	
	 
	
	
	public static void main(String [] args)
	{
	       SusiCockpit a =new SusiCockpit();
	       Student vanko = new Student ("vanko", 8123);
	       Course algebra = new Course("11","algebra",7);
	       Course dis = new Course("111","dis",7);
	       a.registerStudent(vanko);
	       a.addCourse(vanko, algebra);
	       a.addCourse(vanko, dis);
	       a.setGrade(vanko,algebra,5);
	       a.setGrade(vanko,dis,3);
	     
	       System.out.println(a.getTotalCredits(vanko));
	}
	
}
