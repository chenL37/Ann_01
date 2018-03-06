package com.study.test;
/**
 * 
 * @author  ChanRain
 *
 */
@Table("study_tab")
public class Filter {
	@Column("Id")
	private int id;
	
	private String name;
	
	private int age;
	
	private String city;
	
	private  String email;
	
	private String mobile;

	@Override
	public String toString() {
		return "Filter [id=" + id + ", name=" + name + ", age=" + age + ", city=" + city + ", email=" + email
				+ ", mobile=" + mobile + "]";
	}

	public Filter() {
		super();
	}

	public Filter(String name, int age, String city, String email, String mobile) {
		super();
		this.name = name;
		this.age = age;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
	}

	public Filter(int id, String name, int age, String city, String email, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
