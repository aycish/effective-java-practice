package chapterone.itemone.flyweight;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import util.test.TestSetup;

public class FlyTest extends TestSetup {

	@BeforeEach
	void beforeEach() {
		setUp();
	}

	@ParameterizedTest
	@DisplayName("정상_입력_테스트")
	@ValueSource(strings = {"4,A,B,C,D", "3,A,B,C"})
	void normalTest(String userInput) {
		/* TODO : 다른 검증 방법 찾아보기 ,,, */
		assertSimpleTest(() -> {
			run(userInput.split(","));
			verify(userInput.split(","));
		});
	}

	@AfterEach
	void tearDown() {
		outputStandard();
	}

	@Override
	protected void runMain() {
		Client.main(new String[] {});
	}
}

