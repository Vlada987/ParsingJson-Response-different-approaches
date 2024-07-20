package pojo;

public class Book<T> {

	public T id;
	public T name;
	public T type;
	public T available;

	public Book(T id, T name, T type, T available) {

		this.id = id;
		this.name = name;
		this.type = type;
		this.available = available;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	public T getName() {
		return name;
	}

	public void setName(T name) {
		this.name = name;
	}

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}

	public T isAvailable() {
		return available;
	}

	public void setAvailable(T available) {
		this.available = available;
	}

}
