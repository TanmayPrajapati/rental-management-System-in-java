import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Landlord implements Serializable{
	
	private String name;
	private String password;
	private String address;
	private ArrayList<Tenant> tenants = new ArrayList();;
	private static final long serialVersionUID = 1l;
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getName(){
		return name;
	}
	public boolean login(String ps, String us){
		boolean re = false;
		if(ps.equals(password) && us.equals(name)){
			re= true;
		}
		return re;
	}
	
	public ArrayList<Tenant> getTenants(){
		return tenants;
	}
	
	public void addTenant(int num, Landlord ld, Scanner sn){
		for(int i=1; i<=num ;  i++){
			Tenant t = new Tenant(ld);
			System.out.println(i+" Enter the name of tenant:");
			t.setName(sn.next());
			t.setAddress(ld.getAddress());
			System.out.println("Enter the Adhar Number of the Tenant:");
			t.setAdharNumber(sn.nextLong());
			System.out.println("How many family members the Tenant has:");
			int n = sn.nextInt();
			for(int a = 1; a<=n; a++){
				System.out.println("enter the name of "+a+"th member");
				t.setFamilyMembers(sn.next());
			}
			tenants.add(t);
		}
		
	}
	
	public void getCurrentMonthRentInfo(Date dt){
		for(int i=0; i<tenants.size(); i++){
			Tenant t = tenants.get(i);
			SimpleDateFormat f = new SimpleDateFormat("MM");
			if(t.rentAndDate.size() != 0){
				int d = Integer.parseInt(t.rentAndDate.get(t.rentAndDate.size() - 1).substring(3, 5));
			int m = Integer.parseInt(f.format(dt));
			if(d== m){
				System.out.println(i+".Name: "+t.getName()+ "  rent of this Month: PAID");
			}
			}else{
				System.out.println(i+".Name: "+t.getName()+ "  rent of this Month: UNPAID");
			}
			
			
		}
	}
	
	public void getRecords(String uname){
		for(int i=0; i<tenants.size() ; i++){
			if(tenants.get(i).getName().equalsIgnoreCase(uname)){
				tenants.get(i).getRecords();
				break;
			}
		}
	}
	
	
	public void removeTenant(String name){
		for(int i=0; i<tenants.size() ; i++){
			if(tenants.get(i).getName().equalsIgnoreCase(name)){
				tenants.remove(i);
			}
		}
	}
	public boolean updateRentInfo(Date dt, Scanner sn){
		System.out.println("Enter the Name of tenant Which has paid the Rent");
		String n = sn.next();
		for(int i=0; i<tenants.size() ; i++){
			if(tenants.get(i).getName().equalsIgnoreCase(n)){
				tenants.get(i).rent(dt);
				return true;
			}
		}
		System.out.println("No name "+n+" exists");
		return false;
	}
	
}
