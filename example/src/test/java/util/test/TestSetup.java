package util.test;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public abstract class TestSetup {
	private static final Duration TEST_TIMEOUT = Duration.ofSeconds(1L);

	private PrintStream standardOut;
	private OutputStream captor;

	protected void setUp() {
		standardOut = System.out;
		captor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(captor));
	}

	protected void assertSimpleTest(final Executable executable) {
		Assertions.assertTimeoutPreemptively(TEST_TIMEOUT, executable);
	}

	protected void run(final String... args) {
		subject(args);
	}

	protected void verify(final String... args) {
		assertThat(output()).contains(args);
	}

	private void subject(final String... args) {
		command(args);
		runMain();
	}

	protected abstract void runMain();

	private void command(final String... args) {
		final byte[] buf = Strings.join(args).with("\n").getBytes();
		System.setIn(new ByteArrayInputStream(buf));
	}

	protected String output() {
		return captor.toString().trim();
	}

	protected void outputStandard() {
		System.setOut((standardOut));
		System.out.println(output());
	}
}
