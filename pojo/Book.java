package pojo;

public class Book {

	int id;
	String name;
	String type;
	boolean available;

	public Book(int id, String name, String type, boolean available) {

		this.id = id;
		this.name = name;
		this.type = type;
		this.available = available;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
