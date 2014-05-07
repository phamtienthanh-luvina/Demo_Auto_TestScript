package demo.junit_test;

public class Person {
	private String name;
	private Integer age;
	
	public Person(String name, Integer age){
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String persionInfo(){
		return "My name is " + name + ", i " + age + " years old !!!";
	}
}
