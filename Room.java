//package definition;
public class Room
{
	int arr[] =new int[10] ;
	public Room()
	{

	}
	public boolean allot(int roomno)
	{
		if(arr[roomno-1]<3)
		{
			arr[roomno-1]++;
			return true;
		}
		else if(arr[roomno-1]>=3)
		{
			System.out.println("Sorry, this room is full. Please enter another room no");
		}
		return false;
	}
	public void displayAllRoomDetails()
	{
		System.out.println("Room No   No of occupants   Vacancy");
		for(int i=0 ;i<10 ;i++)
		{
			System.out.println((i+1)+"\t        "+arr[i]+"\t      "+(3-arr[i]));
		}
	}
	
}