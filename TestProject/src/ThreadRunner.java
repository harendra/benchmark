import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ThreadRunner extends Thread{
	
	public static int threadcounter=0;
	private Iterator<File> it;
	private int maxthreads=16;
	private List<File> files;
	
	public ThreadRunner(List<File> files){
		
		this.files=files;
		
	}
	
	public synchronized void threadFuture(){
		System.out.println("Thread finished");
		threadcounter-=1;
		System.out.println("Thread counter "+threadcounter);
		createThreads();
	}
	
	public void createThreads(){
		
		if(threadcounter<maxthreads && it.hasNext()){
			System.out.println(new Date());
			ThreadWorker worker=new ThreadWorker(it.next(),this);
			worker.start();
			threadcounter+=1;
		}
	}
	
	public void run(){
		it=files.iterator();
		for(int i=0;i<maxthreads;i++){
			createThreads();
		}
		
	}

}
