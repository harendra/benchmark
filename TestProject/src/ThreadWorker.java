import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class ThreadWorker extends Thread{
	
	private File file;
	private ThreadRunner runner;
	
	public void readLines(File filename) {
		System.out.println(filename.getAbsolutePath());
		try {
			GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(new InputStreamReader(gzip));
			String line = "";
			while ((line = br.readLine()) != null) {
				processLine(line);
			}
		} catch (Exception e) {
			System.out.println(filename.getAbsolutePath());
			e.printStackTrace();
		}
	}

	private void processLine(String line) {
		String[] lines=line.split(" ");
		for(String sline:lines){
			sline.equals("sprit");
		}
	}
	
	
	public ThreadWorker(File file, ThreadRunner runner){
		this.file=file;
		this.runner=runner;
	}
	
	public void run(){
		BenchmarkString bm=new BenchmarkString();
		readLines(this.file);
		runner.threadFuture();
	}

}
