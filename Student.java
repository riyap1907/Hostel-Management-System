//package definition;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class Student {  
	
	int id;  
	static int roll_no;
	String name;
	int roomno;  
	float fees;
	String password;
	long mobile_no;
	public SubmitAttendance sa = new SubmitAttendance();
	int arr[] = new int[10];
	LinkedHashSet<Student> hs;
	Room r= new Room() ;
	public Student()
	{
		
	}
	public Student(LinkedHashSet<Student> hs,Room r) {  
		    this.hs=hs ;
		    this.r=r ;
		
	}  
	
	public static int getRollNo() {
		return roll_no ;
	}
	public static void setRollNo() {
		roll_no++;
	}
	public int menu() throws IOException
	{
		int choice=0;
		do
		{
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			System.out.println("Enter your choice");
			System.out.println("1.Create new record");
			System.out.println("2.Access your record");
			System.out.println("0. Exit");
			try 
			{
				choice=Integer.parseInt(br.readLine()) ;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid number");
				choice=-1;
			}
		}while(choice < 0 || choice > 2 );
		return choice ;
	}
	public void call_menu() throws IOException
	{
		int choice=0;
		do
		{
			choice=menu();
			switch(choice)
			{
				case 1:
					if(Student.roll_no==30)
					{
						System.out.println("Hostel Full");
						return;
					}
					addrecord();
					break;
				case 2:
					verifyLogin();
					break ;
			}
		}while(choice!=0) ;
	}
	public void verifyLogin() throws IOException
	{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		int id1=0;
		int flag=0;
		String pass;
		System.out.println("Enter id:");
		try
		{
			id1=Integer.parseInt(br.readLine()) ;
		}
		catch(Exception e)
		{
			System.out.println("Invalid id ");
			verifyLogin();
			return;
		}
		
		
		System.out.println("Enter password:");
		pass=br.readLine();
		if(!hs.isEmpty())
		{
			for(Student b:hs)
		    {  
				if(id1==b.id)
				{
					flag=1;
					if(pass.compareTo(b.password)==0)
					{
						System.out.println("Welcome "+b.name);
						sub_call_menu(b.id);
					}
					else
					{
						System.out.println("Invalid password");
					}
				}
			}
			if(flag!=1)
			{
				System.out.println("No such record exists");
			}
		}
		else
		{
			System.out.println("No records present");
		}
	}
	public int sub_menu() throws IOException
	{
		int choice=0;
		do
		{
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			System.out.println("Enter your choice");
			System.out.println("1.Display profile");
			System.out.println("2.Give attendance");
			System.out.println("3.Display attendance of the month");
			System.out.println("4.Pay Fees");
			System.out.println("0.Exit");
			try 
			{
				choice=Integer.parseInt(br.readLine()) ;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid number");
				choice=-1;
			}
		}while(choice < 0 || choice > 4 );
		return choice ;
	}
	public void sub_call_menu(int id) throws IOException
	{
		int choice=0;
		do
		{
			choice=sub_menu();
			switch(choice)
			{
				case 1:
					display_profile(id);
					break ;
				case 2: 
					attendance(id);
					break ;	
				case 3:
					System.out.println("Attendance of the month is:");
					sa.bdisplayAttendance(id);
					break;
				case 4:
					payFees(id);
					break;
			}
		}while(choice!=0) ;
	}
	public void addrecord() throws IOException
	{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		int flag=0;
		Student b=new Student();
		System.out.println("Enter your name:");
		b.name=br.readLine();
		System.out.println("Enter your room number:");
		do
		{
			try
			{
				b.roomno=Integer.parseInt(br.readLine()) ;
				while(b.roomno>10 || b.roomno<1 ||r.allot(b.roomno)!=true)
				{
					System.out.println("Please enter a valid roomno between 1 and 10");
					b.roomno=Integer.parseInt(br.readLine());
				}
				flag=1;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid room number");
				System.out.println("Please enter your correct roomno again");
			}
		}while(flag!=1);
		System.out.println("Enter fees that you have paid:");
		flag=0;
		do
		{
			try
			{
				b.fees=Float.parseFloat(br.readLine());
				while(b.fees>100000 || b.fees<0)
				{
					System.out.println("Enter fees less than 10000 and non negative");
					b.fees=Float.parseFloat(br.readLine());
				}
				flag=1;
			}
			catch(Exception e)
			{
				System.out.println("Invalid fees");
				System.out.println("Please enter your correct fees again");
			}
		}while(flag!=1);
		flag=0;
		String mobno;
		System.out.println("Enter mobile number");
		do
		{
			try
			{
				mobno=br.readLine();
				b.mobile_no=Long.parseLong(mobno);
		
				while(true)
				{
					if(b.mobile_no>=1000000000 && mobno.length()==10)
					{
						break;
					}
					else
					{
						System.out.println("Please enter a valid 10 digit mobile no greater than 0");
						mobno=br.readLine();
						b.mobile_no=Long.parseLong(mobno);
					}
				}
				flag=1;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid mobile number");
				System.out.println("Please enter your correct mobile number again");
			}
		}while(flag!=1);
		System.out.println("Enter password");
		b.password=br.readLine();
		for(Student i:hs)
		{
			if(i.mobile_no==b.mobile_no)
			{
				System.out.println("Record already present");
				return;
			}
		}
		Student.setRollNo();
		b.id=Student.getRollNo();
		hs.add(b);
		System.out.println("Record added successfully! Your id is: "+b.id);
	}
	
	public void display_profile(int id_check)
	{
		for(Student b:hs)
	    {  
			if(id_check==b.id)
			{
				System.out.println("Id: "+b.id+"\nName: "+b.name+"\nRoom number: "+b.roomno);
				System.out.println("Total fees: 1,00,000");
				System.out.println("Fees paid: "+b.fees+"\nFees remaining: "+(100000-b.fees));
				System.out.println("Mobile No: "+b.mobile_no+"\n");
				break ;
			}
	    }
	}

	public void attendance(int id) throws IOException
	{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		int date=0;
		System.out.println("Enter today's date");
		try
		{
			date=Integer.parseInt(br.readLine()) ;
			while(date>31 || date<1)
			{
				System.out.println("Please enter a valid date");
				date=Integer.parseInt(br.readLine()) ;
			}
		}
		catch(Exception e)
		{
			System.out.println("Invalid date");
			System.out.println("Please enter the correct date again");
			attendance(id);
			return;
		}
		sa.bgetAttendance(id, date);
	}
	
	public void payFees(int id) throws IOException
	{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		float amount=0.0f;
		System.out.println("Enter amount to be paid");
		try
		{
			amount=Float.parseFloat(br.readLine());
		}
		catch (Exception e)
		{
			System.out.println("Please enter correct amount");
			payFees(id);
			return;
		}
		for(Student b:hs)
		{  
			if(id==b.id &&(amount+ b.fees)<=100000.0 && (amount>=0.0))
			{
				b.fees=b.fees+amount;
				System.out.println(b.fees);
				System.out.println("Amount paid successfully!");
				break;
			}
			else if(id==b.id && b.fees==100000.0)
			{
				System.out.println("Your total fees already completed");
				break;
			}
			else if((100000.0-b.fees)<amount&&id==b.id&&(amount>=0.0))
			{
				System.out.println("Please check your fees status");
				break;
			}
			else if(amount<0.0&&b.id==id)
			{
				System.out.println("Enter amount correctly");
				break;
			}
	   }
	}
}  
