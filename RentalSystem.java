import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.InputMismatchException;
public class RentalSystem implements Serializable{
	static RentalSystem res =  new RentalSystem();
	private Landlord currentLandlord = null;
	private Tenant tn = null;
	private Scanner sn = new Scanner(System.in);
	private ArrayList<Landlord> lds = new ArrayList();
	
	
	public boolean login(){
		System.out.println("Enter Your Name:");
		String uname = sn.next();
		System.out.println("Enter Your Password:");
		String pswd = sn.next();
		boolean re = false;
		for(int i=0; i<lds.size(); i++){
			if(lds.get(i).login(pswd, uname)){
				re = true;
				System.out.println("Successful");
				currentLandlord = lds.get(i);
				break;
			}
		}
		if(!re){
			System.out.println("Login Unsuccessful");

		}
		return re;
	}
	public void afterLogin(){
		while(true){
			System.out.println("What you wanna do: \n1.See your Info \n2.See your tenant's info \n3.Update your tenants \n4.See or Update tenant's rent Information\n5.exit");
			
			int choice = 0;
			try{	
				Scanner sn = new Scanner(System.in);
				choice = sn.nextInt();
			}catch(java.util.InputMismatchException ime){
				System.out.println("--------------------------------------Please Enter the Numeric Value-----------------------------------");
			}
			
			if(choice == 1){
				System.out.println("Your Name:" + currentLandlord.getName());
				System.out.println("Your address:"+ currentLandlord.getAddress());
			}
			else if(choice == 2){
				for(int i =0, a=1 ; i<currentLandlord.getTenants().size() ; i++, a++){
					Tenant t = currentLandlord.getTenants().get(i);
					System.out.println(a+"."+" "+t.getName()+"  "+ "Verification:" +t.getVerification() + "  Family Size : "+ t.getFamilyMembers().size());
				}
			}
			else if(choice == 3){
			while(true){
				System.out.println("What You wanna do \n1.Add Tenant \n2.Remove Tenant \n3.Go Back");
				choice  = 0;
				try{
					sn = new Scanner(System.in);
					choice  = sn.nextInt();
				}catch(InputMismatchException ime1){
					System.out.println("-------------------------------Please Enter the Numeric value-------------------------------------");
				}
				if(choice == 1){
					currentLandlord.addTenant(1 ,currentLandlord, sn);
				}
				else if(choice == 2){
					System.out.println("Enter the name of the Tenant which You want to remove");
					String uname = sn.next();
					currentLandlord.removeTenant(uname);
					System.out.println("Removed Successfully");
				}
				else if(choice == 3){
					break;
				}
			}
			}
			else if(choice == 4){
			while(true){
				choice = 0;
				System.out.println("What do you wanna do \n1.See all rent info \n2.Update rent info of a tenant \n3.See Records \n4.Go Back");
				try{
					choice = (new Scanner(System.in)).nextInt();
				}catch(InputMismatchException ime2){
					System.out.println("-------------------------------Please Enter the Numeric value-------------------------------------");
				
				}
				if(choice == 1){
					currentLandlord.getCurrentMonthRentInfo(new Date());
				}
				else if(choice== 2){
					Date dt = new Date();
					currentLandlord.updateRentInfo(dt, sn);
				}
				else if(choice == 3){
					System.out.println("write the name of the person of which you want to see records :");
					String uname = sn.next();
					currentLandlord.getRecords(uname);
				}
				else if(choice == 4){
					break;
				}
			}
			}
			else if(choice == 5){
				break;
			}
			}
		}
	
	
	
	
	public void registerAsLandlord(){

		Landlord ln = new Landlord();
		System.out.println("What is Your Name:");
		ln.setName(sn.next());
		System.out.println("Set your Password:");
		ln.setPassword(sn.next());
		System.out.println("How many tenants You have:");
		int num = 0;
		try{
			num = (new Scanner(System.in)).nextInt();
		}catch(InputMismatchException ime){
			while(true){
				System.out.println("Please enter the numeric value");
				try{
					num = (new Scanner(System.in)).nextInt();
					break;
				}catch(InputMismatchException ime1){
					
				}
			
			}
		}
		ln.addTenant(num, ln, sn);
		lds.add(ln);
		System.out.println("Registration Succesful");

		
	
	}
	
	
	public static void main(String[] args) {
		try{
			FileInputStream fs = new FileInputStream(new File("MyObject.ser"));
			ObjectInputStream ois = new ObjectInputStream(fs);
			res.lds = (ArrayList<Landlord>) ois.readObject();
			System.out.println("Reloading Done");
			ois.close();
			fs.close();
		
		}catch(Exception e){
			System.out.println("Creating File for Data Storing");
		}		
		while(true){
		
			System.out.println("What do you want to do \n1.Login\n2.Register\n3.Save and Exit");
				int choice = 0;
			try{	
				Scanner sn = new Scanner(System.in);
				choice = sn.nextInt();
			}catch(java.util.InputMismatchException ime){
				System.out.println("---------------------Please Enter the Numeric Value--------------------------");
				
			}
			if(choice == 1){
				if(res.login()){
					res.afterLogin();
				}else{
					System.out.println("Login Unsuccesful");
				}
			}
			else if(choice == 2){
				res.registerAsLandlord();
			}
			else if(choice == 3){
				break;
			}
		
			
		}
		try{
			FileOutputStream fos = new FileOutputStream("MyObject.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(res.lds);
			System.out.println("Save Successful");
			oos.close();
			fos.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
