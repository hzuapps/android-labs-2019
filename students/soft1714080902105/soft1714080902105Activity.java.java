package my;

public class student {
	public String id;
	public String name;
	public int age;
	public boolean sex;

	student(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public void show() {
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "ѧ��" + id + "����" + name + "����" + age;
		if (sex) {
			s = s + "��";
		} else {
			s = s + "Ů";
		}
		return s;
	}
}
