package chapterone.itemone.factory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import util.test.TestSetup;

public class FactoryTest extends TestSetup {

	@BeforeEach
	void beforeEach() {
		setUp();
	}

	@ParameterizedTest
	@DisplayName("정상_입력_테스트")
	@CsvSource(value = {"1:A", "1:B", "1:C"}, delimiter = ':')
	void normalTest(String userTrial, String userInput) {
		assertSimpleTest(() -> {
			run(userTrial, userInput);
			verify("Person " + userInput + " created.");
		});
	}

	@Test
	@DisplayName("정상_입력_테스트")
	void normalTest2() {
		assertSimpleTest(() -> {
			run("4", "A", "B", "C", "A");
			verify("A", "A", "B", "B", "C", "C", "A", "A");
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
