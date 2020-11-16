package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

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
	public static ArrayList<FoodInfo> ctg_junk;
	public static ArrayList<FoodInfo> ctg_stew;
	public static ArrayList<FoodInfo> ctg_meat;
	public static ArrayList<FoodInfo> ctg_drink;
	public static ArrayList<FoodInfo> ctg_agricultural;
	
	public static void main(String[] args) {
		String fileName = "foodInfo.txt";
		Scanner inputStream = null;
		int index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Gender, Age, Height, Weight in order :");
		UserInfo user = new UserInfo(sc.next(), sc.nextInt(), sc.nextDouble(), sc.nextDouble());
		System.out.println("Enter number of foods : ");
		int num_food = sc.nextInt();
		
		ctg_junk = new ArrayList();
		ctg_stew = new ArrayList();
		ctg_meat = new ArrayList();
		ctg_drink = new ArrayList();
		ctg_agricultural = new ArrayList();
		
		Base = getBase(user);
		Promotion = getPromotion(user);
		
		FoodInfo[] foodList = new FoodInfo[num_food];
		
		try {
			inputStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error : opening file " + e.getMessage());
			System.exit(0);
		}
		// NAME | CALORIES | SODIUM | CHOLESTEROL
		while (inputStream.hasNextLine()) {
			String temp = inputStream.nextLine();
			foodList[index] = new FoodInfo(temp.split(" ")[0], Double.parseDouble(temp.split(" ")[1]),
					Double.parseDouble(temp.split(" ")[2]), Double.parseDouble(temp.split(" ")[3]));
			index++;
		}
		for (int i = 0; i < index; i++) {
			if(isJunkFood(foodList[i])) {
				ctg_junk.add(foodList[i]);
			}
		}
		for(int i=0; i<ctg_junk.size(); i++) {
			System.out.println(ctg_junk.get(i).getName());
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
	public static boolean isJunkFood(FoodInfo f) {
		if(f.getName().indexOf("pizza") >= 0 || f.getName().indexOf("Pizza") >= 0) {
			return true;
		}
		else if(f.getName().indexOf("hamburger") >=0 || f.getName().indexOf("Hamburger") >= 0) {
			return true;
		}
		else if(f.getName().indexOf("chicken")>=0 || f.getName().indexOf("Chicken") >= 0) {
			return true;
		}
		else if(f.getCalories() < f.getSodium() && f.getCholesterol() > 10.0) {
			return true;
		}
		return false;
	}
}
