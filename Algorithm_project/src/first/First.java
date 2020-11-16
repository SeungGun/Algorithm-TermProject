package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class UserInfo {
	private String gender;
	private int age;
	private double height;
	private double weight;

	public UserInfo(String gender_, int age_, double height_, double weight_) {
		this.gender = gender_;
		this.age = age_;
		this.height = height_;
		this.weight = weight_;
	}

	public String getGender() {
		return this.gender;
	}

	public int getAge() {
		return this.age;
	}

	public double getHeight() {
		return this.height;
	}

	public double getWeight() {
		return this.weight;
	}
}

class FoodInfo {
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

	public String getName() {
		return this.name;
	}

	public double getCalories() {
		return this.calories;
	}

	public double getSodium() {
		return this.sodium;
	}

	public double getCholesterol() {
		return this.cholesterol;
	}
}

public class First {
	public static double Base = 0.0;
	public static int Promotion = 0;

	public static void main(String[] args) {
		String fileName = "foodInfo.txt";
		Scanner inputStream = null;
		int index = 0;
		Scanner sc = new Scanner(System.in);
		UserInfo user = new UserInfo(sc.next(), sc.nextInt(), sc.nextDouble(), sc.nextDouble());
		int num_food = sc.nextInt();
		Base = getBase(user);
		Promotion = getPromotion(user);
		
		FoodInfo[] foodList = new FoodInfo[num_food];
		System.out.println(foodList.length);
		try {
			inputStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error : opening file " + e.getMessage());
			System.exit(0);
		}
		while (inputStream.hasNext()) {
			String temp = inputStream.nextLine();
			foodList[index] = new FoodInfo(temp.split(" ")[0], Double.parseDouble(temp.split(" ")[1]),
					Double.parseDouble(temp.split(" ")[2]), Double.parseDouble(temp.split(" ")[3]));
			index++;
		}
		for (int i = 0; i < index; i++) {
			System.out.println(foodList[i].name + "," + foodList[i].calories + "," + foodList[i].sodium + ","
					+ foodList[i].cholesterol);
		}
	}

	public static double getBase(UserInfo u) {
		double result = 0.0;
		if (u.getGender().equals("M") || u.getGender().equals("m")) {
			result = 66.47 + (13.75 * u.getWeight()) + (5 * u.getHeight()) - (6.76 * u.getAge());
		} else if (u.getGender().equals("W") || u.getGender().equals("w")) {
			result = 655.1 + 9.56 * u.getWeight() + 1.85 * u.getHeight() - 4.68 * u.getAge();
		}
		return result;
	}

	public static int getPromotion(UserInfo u) {
		int result = 0;
		int a = u.getAge();
		
		if(a>=7 && a<=9) {
			result = 1800;
			return result;
		}
		else if(a>=4 && a<=6) {
			result = 1600;
			return result;
		}
		else if(a >=1 && a<=3) {
			result = 1200;
			return result;
		}
		if (u.getGender().equals("M") || u.getGender().equals("m")) {
			if (a >= 75) {
				result = 1800;
			} else if (a >= 65) {
				result = 2000;
			} else if (a >= 50) {
				result = 2300;
			} else if (a >= 30) {
				result = 2500;
			} else if (a >= 20) {
				result = 2500;
			} else if (a >= 16) {
				result = 2700;
			} else if (a >= 13) {
				result = 2500;
			} else if (a >= 10) {
				result = 2200;
			}
		} 
		else if (u.getGender().equals("W") || u.getGender().equals("w")) {
			if (a >= 75) {
				result = 1600;
			} else if (a >= 65) {
				result = 1700;
			} else if (a >= 50) {
				result = 1900;
			} else if (a >= 30) {
				result = 2000;
			} else if (a >= 20) {
				result = 2000;
			} else if (a >= 16) {
				result = 2100;
			} else if (a >= 13) {
				result = 2100;
			} else if (a >= 10) {
				result = 2000;
			} 
		}
		return result;
	}
	

}
