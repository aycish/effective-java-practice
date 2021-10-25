package chapterone.itemone.factory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import util.test.TestSetup;

public class FactoryTest extends TestSetup {

	@BeforeEach
	void beforeEach() {
		setUp();
	}

	@ParameterizedTest
	@DisplayName("정상_입력_테스트")
	@ValueSource(strings = {"A", "B"})
	void aAndBTest(String userInput) {
		assertSimpleTest(() -> {
			run(userInput);
			verify("Person " + userInput + " created.");
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
