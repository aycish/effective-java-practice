package chapterone.itemone;

import java.util.ArrayList;
import java.util.List;

public class Shape {
	private String color;
	private static Shape shape;

	public Shape() {
	}

	/* 기본적으로 사용하는 public 생성자 */
	public Shape(String color) {
		this.color = color;
	}

	/* 장점 1. 직관적인 이름으로 사용 가능하다. */
	public static Shape withColor(String color) {
		return new Shape(color);
	}

	/* 장점 2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다. */
	public static Shape createShape(String color) {
		if (shape == null) {
			shape = new Shape(color);
		}

		return shape;
	}

	/* 장점 3. 반환 타입의 하위 타입 객체를 반환할 수 있다. */
	public static Shape getCircle() {
		return new Circle();
	}

	public static Shape getSquare() {
		return new Square();
	}

	/* 장점 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다. */
	public static Shape getShapeWithArgs(String type) {
		if (type.compareTo("Square") == 0) {
			return getSquare();
		}

		return getCircle();
	}

	/* 장점 5. 정적 팩터리 메서드를 작성하는 시점에는 객체의 클래스가 존재하지 않아도 된다. (= 미구현 되어있어도 된다.) */
	public static void doSomething() {
		List<ExampleShape> exampleShapeList = new ArrayList<>();

		/* 아직 ExampleTriangle class는 미구현한 클래스이다 */
		ExampleTriangle exampleTriangle = new ExampleTriangle();
		exampleShapeList.add(exampleTriangle);
	}

	/* 단점 1. 정적 팩토리 매서드만 제공할 경우, 상속이 불가능하다
	 *
	 *  Shape 클래스에서는 상속을 위해 public 기본 생성자를 생성해 두었지만, 만약 제거한다면 하위 클래스(Square, Circle)의 컴파일 에러가 발생한다.
	 * */

	/* 단점 2. 기본 생성자처럼 명확하게 API가 드러나있는게 아니기 때문에, 다른 프로그래머가 이를 인식하기 어렵다.
	 *
	 *  따라서 상기 예시를 위한 메서드의 경우도 문서를 작성하여 다른 프로그래머가 쉽게 인스턴스화할 수 있도록 도움을 주어야한다.
	 * */
}
