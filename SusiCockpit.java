public class SusiCockpit implements Susi{
	private Student[] Students;
	private double [] totalCredits;
	private double [] totalGrade;
	private int numberOfStudents;
	
	SusiCockpit()
	{
		Students= new Student[1000];
		numberOfStudents=0;
		totalCredits=new double [1000];
		totalGrade= new double [1000];
	}
	
	public boolean registerStudent(Student student)
	{
		
		if(this.numberOfStudents>=1000)
		{
			System.out.print("System capacity reached");
			return false;
		}
		
		 for (Student test : this.Students)
			  	
			         {
			 	  	
			             if(test==student)
			 	  	
			                 {System.out.print("Student already registered");
			 	  	
			                 return false;}
			 	  	
			         }
		
		Students[this.numberOfStudents]=student;
		totalCredits[this.numberOfStudents]=0;
		totalGrade[this.numberOfStudents]=0;
		this.numberOfStudents++;
		return true;
		
		
		
	}
	
	public int getStudentsCount()
	{
		return this.numberOfStudents;
	}
	
	public void addCourse(Student student, Course course )
	{
		
		for(int i=0;i<this.numberOfStudents;i++)
		{
			if(Students[i]==student)
			{
				Students[i].addCourse(course);
			 
			 
			}
			
		}
	}
	
	public boolean setGrade(Student student, Course course, double grade)
	{
		for(int i=0;i<this.numberOfStudents;i++)
		{
			 if(student==Students[i] && this.Students[i]!=null)
				 
			 {
				 for(int j=0;j<this.Students[i].getNumberOfCourses();j++)
				 {
					 
					  
					 
					 if(course== this.Students[i].getCourse(j))
					 {
						 
				 
						 this.Students[i].getCourse(j).setGrade(grade);
						 this.totalGrade[i]=this.totalGrade[i]+grade;
						 if(grade>2)
						 {
							 this.totalCredits[i]=this.totalCredits[i]+course.getCredits();
						 }
						 
						 
						 return true;
					 }
				 }
				 this.Students[i].addCourse(course);
				 for(int j=0;j<this.Students[i].getNumberOfCourses();j++)
				 {
					 
					  
					 
					 if(course.getId().equals(this.Students[i].getCourse(j).getId())&&course.getName().equals(this.Students[i].getCourse(j).getName()) )
					 {
						 
				 
						 this.Students[i].getCourse(j).setGrade(grade);
						 this.totalGrade[i]=this.totalGrade[i]+grade;
						 if(grade>2)
						 {
							 this.totalCredits[i]=this.totalCredits[i]+course.getCredits();
						 }
						 
						 
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
				{
				return this.totalCredits[i];
				}
		}
		  
			return 0;
		
	}
	
	 
	public double getGPA(Student student)
	{
		for(int i=0;i<this.numberOfStudents;i++)
		{
			if(this.Students[i]==student && this.Students[i]!=null)
				
				{
				if(this.Students[i].getNumberOfCourses()==0)
					return 0;
				else
					
				return this.totalGrade[i]/this.Students[i].getNumberOfCourses();
				}
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
	       
	       System.out.print(a.getTotalCredits(vanko));
	       System.out.print(a.getGPA(vanko));
	       
	       
	       
	     
	       
	}
	
}