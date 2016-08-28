import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BenchmarkString {

	private List<File> getInputFiles(String inputfolder) {
		File inpfolder = new File(inputfolder);
		File[] files = inpfolder.listFiles();
		return Arrays.asList(files);
	}

	public static void main(String[] argv) {
		System.out.println(new Date());
		String filename = "";
		BenchmarkString str = new BenchmarkString();
		List<File> files=str.getInputFiles(filename);
		ThreadRunner runner=new ThreadRunner(files);
		runner.start();
		try {
			runner.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Task finished");
		System.out.println(new Date());
	}

}
