import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class MainRunner {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		ReadAFile oneFile = new ReadAFile("C:\\Users\\melvi\\Desktop\\Sample CSV Files\\sampleData1_snowTickets_approvalDates - result1.csv");
		oneFile.printGreetingAndTotalCount();
		List<String[]> res = oneFile.getResArray();
		
		TestingThreads partOne = new TestingThreads(res, 1, res.size() / 2);
		TestingThreads partTwo = new TestingThreads(res, res.size() / 2, res.size() - 1);
		
		Thread t1 = new Thread(partOne);
		Thread t2 = new Thread(partTwo);
		
		t1.start();
		t2.start();
		
		//wait for both threads to finish
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> partOneArray = partOne.getExceptionArray();
		List<String> partTwoArray = partTwo.getExceptionArray();
		
		int totalGood = partOne.getGoodCount() + partTwo.getGoodCount();;
		int totalBad = partOne.getBadCount() + partTwo.getBadCount();
		int total = totalGood + totalBad;
		double goodPercent = totalGood * 100 / total;
		double badPercent = totalBad * 100 / total;
		
		System.out.println("\n*************************************************************************");
		System.out.println("Results:");
		System.out.println("Total Tickets Processed: " + total);
		System.out.println("# of Good Tickets: " + totalGood);
		System.out.println("Good Ticket %: " + goodPercent +"%");
		System.out.println("# of Bad Tickets: " + totalBad);
		System.out.println("Bad Ticket %: " + badPercent +"%");
		System.out.println("Testing has been completed.");
		
		//all done here
		
	}
}