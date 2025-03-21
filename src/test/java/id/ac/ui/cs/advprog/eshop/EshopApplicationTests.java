package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class EshopApplicationTests {

	@Test
	void contextLoads() {
		// is empty
		//
		// fails if web app fails to run
	}

	@Test
	void applicationStartsAndPrintsMessage(CapturedOutput capturedOutput) {
		EshopApplication.main(new String[] {});

		String output = capturedOutput.getAll();

		assertTrue(output.contains("Application started"));
	}

}
