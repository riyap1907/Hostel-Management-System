//package definition;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
public class Admin {
	LinkedHashSet<Student> hs ;
	Room r= new Room() ;
	SubmitAttendance sa=new SubmitAttendance();
	public Admin(LinkedHashSet<Student> hs,Room r, SubmitAttendance sa)
	{
		this.hs=hs;
		this.r=r ;
		this.sa=sa;
	}
	public boolean login() throws IOException
	{
		String name="a";
		String pass="a";
		String u_name;
		String u_pass;
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		System.out.println("Enter username");
		u_name=br.readLine();
		System.out.println("Enter password");
		u_pass=br.readLine();
		if(u_name.compareTo(name)==0 && u_pass.compareTo(pass)==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int menu() throws IOException
	{
		int choice=0;
		do
		{
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			System.out.println("Enter your choice");
			System.out.println("1.Display all records");
			System.out.println("2.Display vacancy");
			System.out.println("3.Display attendance");
			System.out.println("4.Search record");
			System.out.println("5.Fee paid status");
			System.out.println("0. Exit");
			try 
			{
				choice=Integer.parseInt(br.readLine());
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid number");
				choice=-1;
			}
		}while(choice < 0 || choice > 5 );
		return choice;
	}
	public void call_menu() throws IOException
	{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		int choice =0 ;
		int flag=0;
		int id=0;
		do
		{
			choice = menu();
			switch(choice)
			{
				case 1:
					if (hs.isEmpty())
					{
						System.out.println("No records found");
						break ;
					}
					for(Student b:hs)
				    {  
				    	System.out.println(b.id+" "+b.name+" "+b.roomno+" "+b.fees+" "+b.mobile_no);  
				    } 
				    break ;
				case 2:
					r.displayAllRoomDetails();
					break ;
				case 3 :
					if (hs.isEmpty())
					{
						System.out.println("No records found");
						break ;
					}
					System.out.println("Enter id: ");
					flag=0;
					do
					{
						try
						{
							id=Integer.parseInt(br.readLine());
							flag=1;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Invalid id");
							System.out.println("Please enter correct id again");
						}
					}while(flag!=1);
					
					sa.bdisplayAttendance(id);
					break ;
				case 4:
					if (hs.isEmpty())
					{
						System.out.println("No records found");
						break ;
					}
					System.out.println("Enter id: ");
					flag=0;
					do
					{
						try
						{
							id=Integer.parseInt(br.readLine());
							flag=1;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Invalid id");
							System.out.println("Please enter correct id again");
						}
					}while(flag!=1);
					displayById(id);
					break;
				case 5:
					if (hs.isEmpty())
					{
						System.out.println("No records found");
						break ;
					}
					int fchoice=0;
					do
					{
						System.out.println("Enter choice:\n1: Students who have paid the fees");
						System.out.println("2: Students who have not paid the fees");
						try 
						{
							fchoice=Integer.parseInt(br.readLine());
						}
						catch(NumberFormatException e)
						{
							System.out.println("Please enter a valid number");
							fchoice=-1;
						}
					}while(fchoice < 1 || fchoice > 2 );
					displayFeeStatus(fchoice);
					break;
				case 0:
					break ;
			}
		}while(choice!=0);
	}
	
	public void displayById(int id_check)
	{
		int flag=0;
		for(Student b:hs)
	    {  
			if(id_check==b.id)
			{
				System.out.println(b.id+" "+b.name+" "+b.roomno);
				System.out.println("Total fees 1,00,000\nFees paid : "+b.fees+" Fees remaining :"+(100000-b.fees));
				flag=1;
				break ;
			}
	    }
		if(flag!=1)
		{
			System.out.println("Record not found");
		}
	}
	
	public void displayFeeStatus(int fchoice)
	{
		if(fchoice==1)
		{
			System.out.println("Name\tStatus");
			for(Student b:hs)
			{
				if(b.fees==100000f)
				{
					System.out.println(b.name+"\tPaid");
				}
			}
		}
		else if(fchoice==2)
		{
			System.out.println("Name\tTotal Fees\tFees Paid\tFees Remaining");
			for(Student b:hs)
			{
				if(b.fees!=100000f)
				{
					System.out.println(b.name+"\t1,00,000\t"+b.fees+"\t\t"+(100000-b.fees));
				}
			}
		}
	}
}