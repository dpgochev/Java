public class Course implements Subject {
	private String ID;
	private String Name;
	private int Credits;
	private double Grade;

	public Course() {
		this.ID = "";
		this.Name = "";
		this.Grade = 0;
		this.Credits = 0;
	}

	public Course(String id, String name, int credits) {
		this.setId(id);
		this.setName(name);
		this.setCredits(credits);

	}

	public Course(Course course) {
		this.ID = course.getId();
		this.Name = course.getName();
		this.Credits = course.getCredits();
	}

	public void setCredits(int credits)

	{
		this.Credits = credits;
	}

	public int getCredits()

	{
		return this.Credits;
	}

	public void setGrade(double grade)

	{
		this.Grade = grade;
	}

	public double getGrade()

	{
		return this.Grade;
	}

	public void setId(String id) {
		this.ID = id;
	}

	public String getId() {
		return this.ID;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getName() {
		return this.Name;
	}
}