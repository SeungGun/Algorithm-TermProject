package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class UserInfo{
	 String gender;
	 int age;
	 double height;
	 double weight;
	public UserInfo(String gender_,int age_, double height_, double weight_) {
		this.gender = gender_;
		this.age = age_;
		this.height = height_;
		this.weight = weight_;
	}
	
}
class FoodInfo{
	 String name;
	 double calories;
	 double sodium;
	 double cholesterol;
	public FoodInfo(String name_, double calories_, double sodium_, double cholesterol_) {
		this.name = name_;
		this.calories = calories_;
		this.sodium = sodium_;
		this.cholesterol = cholesterol_;
	}
}
public class First {

	public static void main(String[] args) {
		String fileName = "foodInfo.txt";
		Scanner inputStream = null;
		String t1 = "";
		String t2 ="";
		String t3="";
		String t4="";
		int loop_count = 1;
		int index = 0;
		Scanner sc = new Scanner(System.in);
		UserInfo user = new UserInfo(sc.next(),sc.nextInt(),sc.nextDouble(),sc.nextDouble());
		int num_food = sc.nextInt();
		FoodInfo[] foodList = new FoodInfo[num_food];
		System.out.println(foodList.length);
		try {
			inputStream = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error : opening file "+e.getMessage());
			System.exit(0);
		}
		while(inputStream.hasNext()) {
			
			if(loop_count % 5 == 1) {
				t1 = inputStream.next();
			}
			else if(loop_count % 5 == 2) {
				t2 = inputStream.next();
			}
			else if(loop_count %5 == 3) {
				t3 = inputStream.next();
			}
			else if(loop_count % 5 == 4) {
				t4 = inputStream.next();
			}
			else if(loop_count % 5 == 0) {
				foodList[index] = new FoodInfo(t1,Double.parseDouble(t2),Double.parseDouble(t3),Double.parseDouble(t4));
				System.out.println("Current :"+index);
				index++;
			}
			System.out.println(loop_count);
			loop_count++;
		}
		for(int i =0; i<index; i++) {
			System.out.println(foodList[i].name+","+foodList[i].calories+","+foodList[i].sodium+","+foodList[i].cholesterol);
		}
	}

}
