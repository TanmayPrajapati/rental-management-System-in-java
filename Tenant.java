import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.Serializable;
public class Tenant implements Serializable{
	
	private String name;
	private String address;
	private boolean verification;
	private long adharNum;
	private ArrayList<String> familyMembers = new ArrayList();
	private Landlord ld = new Landlord();
	public ArrayList<String> rentAndDate = new ArrayList();
	private static final long serialVersionUID = 2l;
	
	public Tenant(Landlord ld){
	
		this.ld = ld;
	
	}
	
	public String getName(){
	
		return name;
	
	}
	public String getAddress(){
	
		return address;
	
	}
	
	public boolean getVerification(){
		
		return verification;
	
	}
	public ArrayList getFamilyMembers(){
	
		return familyMembers;
	
	}
	
	
	public void setName(String name){
	
		this.name = name;
	
	} 
	public void setAddress(String address){
	
		this.address = address;
	
	}
	public void setAdharNumber(long adharNum){
		this.adharNum = adharNum;
	}
	
	public void setFamilyMembers(String name){
		familyMembers.add(name);
	}
	public void isVerifiedByGovernment(){
		//some code which get access to government Server
		verification = true;
	}
	
	public void rent(Date dt){
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
		String record = f.format(dt).toString() + ": "+ "Rent Paid :"+"Name: "+getName();
		rentAndDate.add(record);
		System.out.println("record Added");
	
	}
	public void getRecords(){
		for(int i=0; i<rentAndDate.size(); i++){
			System.out.println(rentAndDate.get(i));
		}
	}
	

}
