//package definition;
import java.util.ArrayList;

public class Attendance {
	ArrayList<Integer> att1= new ArrayList<Integer>();
	
	public void giveAttendance(int date)
	{
		att1.add(date);
	}
	
	public void display()
	{
		int i=0;
		for(i=1;i<=31;i++)
		{
			if(att1.contains(i))
			{
				System.out.println("Day: "+(i)+" Record: Present");
			}
			else
			{
				System.out.println("Day: "+(i)+" Record: Absent");
			}
		}
	}
}
