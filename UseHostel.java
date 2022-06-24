//package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

import definition.Admin;
import definition.Room;
import definition.Student; 
public class UseHostel 
{  
	public static void main(String args[]) throws IOException 
	{  
		int cho=0;
		Room r= new Room();
	    LinkedHashSet<Student> hs=new LinkedHashSet<Student>();  
	    Student b1=new Student(hs,r); 
	    Admin ad=new Admin(hs,r,b1.sa);
	    do
	    {
	    	cho=main_menu() ;
			switch(cho)
			{
				case 0:
					System.out.println("Exiting! Have a great day ahead!");
					break;
				case 1:
					boolean status1 = ad.login();
					if(status1==true)
					{
						ad.call_menu();
					}
					else
					{
						System.out.println("Invalid login");
					}
					break;
				case 2:
					b1.call_menu();
					break;
			}
		}while(cho!=0) ;
	}
	public static int main_menu() throws IOException
	{
		int cho=0 ;
		do
		{
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			System.out.println("Enter choice ");
			System.out.println("1. Admin");
			System.out.println("2. Student");
			System.out.println("0. Exit");
			try 
			{
				cho=Integer.parseInt(br.readLine()) ;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid number");
				cho=-1;
			}
		}while(cho < 0 || cho > 2 );
		return cho ;
		
	}
}