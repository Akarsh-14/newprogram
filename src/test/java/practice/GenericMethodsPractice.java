package practice;

public class GenericMethodsPractice {

	public static void main(String[] args) {  //caller function
	
			int sum = add(10,20);
			System.out.println(sum);
			
		}
		public static int add(int a,int b)  //called function  -generic method
		{
			int c=a+b;
			return c;
	}

}
