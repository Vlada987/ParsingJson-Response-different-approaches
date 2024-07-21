package rest;

public enum E {

	BOOLEAN_VALUE("boolean_value"), TEXT("String value"), INTEGER_VALUE("Integer value"), DOUBLE_VALUE("Double value"),
	LIST_VALUE("List value");

	public String value;

	E(String value) {
		this.value = value;
	}

	public String getValue() {

		return value;
	}

}
