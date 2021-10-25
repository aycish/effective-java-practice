package chapterone.itemone.factory;

import util.Console;

public class Client {
	public static void main(String args[]) {
		ClassA classA = new ClassA();

		String userInput = Console.readLine();
		classA.createPerson(userInput);
	}
}
