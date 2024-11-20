package basicBoot;

public class Test {
	public static void main(String[] args) {
		String str = "";
		
		if(str != null || !str.equals("")) {
			System.out.println("비엇음 ");
		} else {
			System.out.println("안비엇음 ");
		}
	}
}
