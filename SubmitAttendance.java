//package definition;

public class SubmitAttendance {
	Attendance[] a = new Attendance[10];
	
	public SubmitAttendance()
	{
		int i=0;
		for(i=0;i<10;i++)
		{
			a[i]=new Attendance();
		}
	}
	public void bgetAttendance(int id, int date)
	{
		a[id].giveAttendance(date);
	}
	
	public void bdisplayAttendance(int id)
	{
		a[id].display();
	}
}
