class Student{
	
	private String name ;

	private int age ;

	private String college ;

	private String depart ;

	public Student (){}

	public Student (String name , int age , String college , String depart){

		this.setName(name);

		this.setAge(age);

		this.setCollege(college);

		this.setDepart(depart);

	}

	public void setName(String name){

		this.name = name;

	}

	public void setAge(int age){

		this.age = age ;

	}

	public void setCollege(String college){

		this.college = college ;

	}

	public void setDepart(String depart){

		this.depart = depart ;

	}

	public String getName(){

		return this.name ;

	}

	public int getAge(){

		return this.age ;
 
	}

	public String getCollege(){

		return this.college ;

	}

	public String getDepart(){

		return this.depart;

	}
	public String getInfo(){

		return "ѧ����Ϣ����:" + "\n" +
	
			        "����:" + this.getName() + "\n" +

				"����:" + this.getAge() + "\n" +

				"ѧУ:" + this.getCollege() + "\n" +

				"ѧԺ:" + this.getDepart();
 
	}

}

 
public class  test1{

	public static void main (String args[]){

		Student stu1 = new Student("����" , 22 , "shu" , "computer science and techololege") ;

		System.out.println(stu1.getInfo());
	
        }

}

