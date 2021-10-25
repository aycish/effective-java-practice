package chapterone.itemone.factory;

public class ClassA {
	public Person createPerson(String type) {
		PersonFactory factory = new PersonFactory();
		Person returnPerson = factory.createPerson(type);
		return returnPerson;
	}
}
