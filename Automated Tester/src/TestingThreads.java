import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestingThreads implements Runnable{

	List<String[]> inputArr;
	int startIndex;
	int endIndex;
	int badCount;
	int goodCount;
	List<String> exceptionArray;
	
	public TestingThreads(List<String[]> inputArr, int startIndex, int endIndex) {
		this.inputArr = inputArr;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		exceptionArray = new ArrayList<>();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Thread Start Time Stamp: " + timestamp);                      // 2022-07-16 15:23:50.891
		
		for (int i = startIndex; i <= endIndex; i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			try {
				String ticketID = inputArr.get(i)[0];
				Date devDate = sdf.parse(inputArr.get(i)[1]);
				Date qaDate = sdf.parse(inputArr.get(i)[2]);
				Date uatDate = sdf.parse(inputArr.get(i)[3]);
				
				if ((devDate.before(qaDate) || devDate.equals(qaDate)) && (qaDate.before(uatDate) || qaDate.equals(uatDate))) {
					goodCount++;
					continue;
				} else {
					badCount++;
					exceptionArray.add(ticketID);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Timestamp timeEndStamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Thread End Time Stamp: " + timeEndStamp);                      // 2022-07-16 15:23:52.577
	}
	
	public int getGoodCount() {
		return goodCount;
	}
	
	public int getBadCount() {
		return badCount;
	}
	
	public List<String> getExceptionArray(){
		return exceptionArray;
	}
}