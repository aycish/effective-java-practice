package chapterone.itemone.template;

public class Client {
	public static void main(String[] args) {
		ChildA childA = new ChildA();
		childA.doSomething();

		System.out.println("------------------");

		ChildB childB = new ChildB();
		childB.doSomething();
	}
}
