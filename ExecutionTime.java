import java.text.DecimalFormat;

public class ExecutionTime {
	static String executionTime(double startTime) {
				long endTime = System.nanoTime();
				double duration = (endTime - startTime)*Math.pow(10, -9);
				DecimalFormat df = new DecimalFormat("#.###");
				return "Total Program Execution time : " + df.format(duration) + " seconds";
	}
}
