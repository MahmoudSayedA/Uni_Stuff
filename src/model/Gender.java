package model;

public enum Gender {

	MALE, FEMALE, UNDEFINED;

	public String toString() {
		if (this.equals(FEMALE))
			return "FEMALE";
		else if (this.equals(MALE))
			return "MALE";
		else
			return "UNDEFINED";
	}

	public static Gender getType(String type) {
		type = type.toLowerCase();
		if (type.equals("male"))
			return MALE;
		else if (type.equals("female"))
			return FEMALE;
		else
			return UNDEFINED;
	}
}
