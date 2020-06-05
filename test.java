import java.util.Date;
import java.text.SimpleDateFormat;
public class test{

	public static void main(String[] args) {
		Date d = new Date();
		String s = "tanmay h don";
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
		System.out.println(f.format(d).toString() + "  " +s.substring(0, 6));
	}

}
