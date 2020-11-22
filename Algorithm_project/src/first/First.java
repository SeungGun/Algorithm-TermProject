package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class UserInfo {
	private String gender;
	private int age;
	private double height;
	private double weight;

	double enoughSodium;
	double enoughCalorie;

	double maximumSodium = 3000;
	double maximumCalorie;
	double maxiumumCholesterol = 300;

	public UserInfo(String gender_, int age_, double height_, double weight_) {
		this.gender = gender_;
		this.age = age_;
		this.height = height_;
		this.weight = weight_;
	}

	public void setEnoughSodium(double a) {
		this.enoughSodium = a;
	}

	public void setEnoughCalorie(double a) {
		this.enoughCalorie = a;
	}

	public void setMaximumCalorie(double a) {
		this.maximumCalorie = a;
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
	int category;

	public FoodInfo(String name_, double calories_, double sodium_, double cholesterol_, int category_) {
		this.name = name_;
		this.calories = calories_;
		this.sodium = sodium_;
		this.cholesterol = cholesterol_;
		this.category = category_;
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

	public int getCategory() {
		return this.category;
	}
	public String getCategoryInString() {
		switch(this.category) {
		case 0: return "Junk";
		case 1: return "Stew"; 
		case 2: return "Meat";
		case 3: return "Drink";
		case 4: return "Agricultural";
		case 5: return "Snack";
		case 6: return "Other";
		default : return "MATCH ERROR";
		}
	}
}

class DailyDiet {
	String Breakfast[] = new String[3];
	String Lunch[] = new String[3];
	String Dinner[] = new String[3];

	double calorieTotal = 0;
	double sodiumTotal = 0;
	double cholesterolTotal = 0;

	boolean isjunkFood = false;

	public void updateCalorie(double newCal) {
		this.calorieTotal = this.calorieTotal + newCal;
	}

	public void updateSodium(double newSod) {
		this.sodiumTotal = this.sodiumTotal + newSod;
	}

	public void updateCholesterol(double newCho) {
		this.cholesterolTotal = this.cholesterolTotal + newCho;
	}

	public double getCalorieDay() {
		return this.calorieTotal;
	}

	public double getSodiumDay() {
		return this.sodiumTotal;
	}

	public double getCholesterolDay() {
		return this.cholesterolTotal;
	}

	public boolean getIsJunkFood() {
		return this.isjunkFood;
	}
}

public class First {
	public static double Base = 0.0;
	public static int Promotion = 0;
	public static int num_food;
	public static ArrayList<FoodInfo> ctg_junk; // 0
	public static ArrayList<FoodInfo> ctg_stew; // 1
	public static ArrayList<FoodInfo> ctg_meat; // 2
	public static ArrayList<FoodInfo> ctg_drink; // 3
	public static ArrayList<FoodInfo> ctg_agricultural; // 4
	public static ArrayList<FoodInfo> ctg_snack; // 5
	public static ArrayList<FoodInfo> ctg_other; // 6
	public static double[][] dp;
	public static FoodInfo[] foodList;
	public static double sum = 0.0;
	public static final int prefer_sodium = 3000;
	public static ArrayList<Integer> backtracked;
	public static boolean isThereJunk = false;
	public static boolean isFirst = false;
	
	public static void main(String[] args) {
		//Process : initDP() -> foodSort() ->  knapsack() ->  backtrackingDP() -> findThreshold()
		String fileName = "foodInfo.txt";
		Scanner inputStream = null;
		int index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Gender, Age, Height(m), Weight(kg) in order :");
		UserInfo user = new UserInfo(sc.next(), sc.nextInt(), sc.nextDouble(), sc.nextDouble());
		try {
			inputStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error : opening file " + e.getMessage());
			System.exit(0);
		}
		num_food = Integer.parseInt(inputStream.nextLine());
		System.out.println("food count :" + num_food);

		backtracked = new ArrayList<Integer>();

		DailyDiet monday = new DailyDiet();
		DailyDiet tuesday = new DailyDiet();
		DailyDiet wednesday = new DailyDiet();
		DailyDiet thursday = new DailyDiet();
		DailyDiet friday = new DailyDiet();
		DailyDiet saturday = new DailyDiet();
		DailyDiet sunday = new DailyDiet();

		ctg_junk = new ArrayList<FoodInfo>();
		ctg_stew = new ArrayList<FoodInfo>();
		ctg_meat = new ArrayList<FoodInfo>();
		ctg_drink = new ArrayList<FoodInfo>();
		ctg_agricultural = new ArrayList<FoodInfo>();
		ctg_snack = new ArrayList<FoodInfo>();
		ctg_other = new ArrayList<FoodInfo>();

		Base = getBase(user);
		Promotion = getPromotion(user);
		foodList = new FoodInfo[num_food];
		dp = new double[num_food + 1][prefer_sodium+1];

		initDP();
		// NAME | CALORIES | SODIUM | CHOLESTEROL | CATEGORY
		while (inputStream.hasNextLine()) {
			String temp = inputStream.nextLine();
			System.out.println(temp);
			foodList[index] = new FoodInfo(temp.split(" ")[0], Double.parseDouble(temp.split(" ")[1]),
					Double.parseDouble(temp.split(" ")[2]), Double.parseDouble(temp.split(" ")[3]),
					Integer.parseInt(temp.split(" ")[4]));
			index++;
		}
		user.enoughSodium = getSodiumBase(user);
		user.enoughCalorie = getBase(user);
		user.maximumCalorie = getPromotion(user);
		
		System.out.println("\n");
		System.out.println("You should eat " + user.enoughCalorie + " between " + user.maximumCalorie + " calorie");
		System.out.println("\n");
		System.out.println("You should eat " + user.enoughSodium + " between " + user.maximumSodium + " sodium");
		System.out.println("\n");
		System.out.println("You should eat cholesterol below " + user.maxiumumCholesterol);

		for (int i = 0; i < foodList.length; i++) {
			sum += foodList[i].getCalories();
		}
		System.out.println("\nTotal sum of Input Foods : "+sum);
		for (int i = 0; i < index; i++) {
			foodSort(foodList[i]);
		}
		System.out.println("\n");
		System.out.println("Junk Foods Are: ");
		for (int i = 0; i < ctg_junk.size(); i++) {
			System.out.println(ctg_junk.get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Stews Are: ");
		for (int i = 0; i < ctg_stew.size(); i++) {
			System.out.println(ctg_stew.get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Meats Are: ");
		for (int i = 0; i < ctg_meat.size(); i++) {
			System.out.println(ctg_meat.get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Drinks Are: ");
		for (int i = 0; i < ctg_drink.size(); i++) {
			System.out.println(ctg_drink.get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Agricultural Are: ");
		for (int i = 0; i < ctg_agricultural.size(); i++) {
			System.out.println(ctg_agricultural.get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Snacks Are: ");
		for (int i = 0; i < ctg_snack.size(); i++) {
			System.out.println(ctg_snack.get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Others Are: ");
		for (int i = 0; i < ctg_other.size(); i++) {
			System.out.println(ctg_other.get(i).getName());
		}
		
		knapsack(0, prefer_sodium);

		backtrackingDP(Promotion);
		
		double s = 0;
		for (int i = 0; i < backtracked.size(); i++) {
			s += foodList[backtracked.get(i)].getCalories();
		}
		System.out.println("\nbacktracked sum : " + s);
		
		findThreshold(backtracked, s);
		System.out.println("\nSelected Food list :");
		for(int i = 0; i< backtracked.size(); i++) {
			System.out.println((i+1)+" : "+foodList[backtracked.get(i)].getName()+", category : "+foodList[backtracked.get(i)].getCategoryInString());
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

	public static void findThreshold(ArrayList<Integer> set, double sum) {
		if(sum <= Promotion) return;
		double max = -1;
		int idx = -1;
		double k = 0;
		int number = -1;
		for(int i =0; i<set.size(); i++) {
			int to = set.get(i);
			k = foodList[to].getCalories();
			if (max < sum - k && sum - k <= Promotion) {
				max = sum - k;
				idx = i;
				number = to;
			}
		}
		if(idx != -1 && number != -1) {
			set.remove(idx);
			System.out.println("Total Max : " + max + ", And extracted : " + foodList[number].getCalories() 
					+"("+foodList[number].getName()+ "),  its index : " + number);
		}
		if(max > Promotion) {
			findThreshold(set, max);
		}
	}

	public static double knapsack(int num, double capacity) {
		if (num == num_food) {
			return 0;
		}
		double temp = dp[num][(int)capacity];
		if (temp != -1) 
			return temp;		

		if (foodList[num].getSodium() <= capacity) {
			double tmp = knapsack(num + 1, (int) (capacity - foodList[num].getSodium()));
			
			if (foodList[num].getCategory() == 0 && !isThereJunk) {
				isThereJunk = true;
				isFirst = true;
				temp = tmp + foodList[num].getCalories();
				dp[num][(int)capacity] = temp;
				return temp;
			}
			if (isThereJunk && !isFirst) {
				if (foodList[num].getCategory() == 0)
					temp = tmp;
				else {
					temp = foodList[num].getCalories() + tmp;
				}
				
			} 
			else if(!isFirst){
				temp = foodList[num].getCalories() + tmp;
			}
			if (temp > Promotion) {
				temp = tmp;		
			}
			isFirst = false;
		}

		double tmp2 = knapsack(num + 1, capacity);
		if (tmp2 > temp && tmp2 != 0 && temp != 0) {
			temp = tmp2;
		}
		dp[num][(int)capacity] = temp;
		return temp;
	}

	public static void backtrackingDP(int promotion) {
		double t = dp[num_food][prefer_sodium];
		for (int i = num_food; i >= 0; i--) {
			for (int j = prefer_sodium; j >= 0; j--) {
				if (dp[i][j] == -1)
					continue;
				if (t < dp[i][j]) {
					t = dp[i][j];
					backtracked.add(i);
				}
			}
		}
	}
	public static int getPromotion(UserInfo u) {
		int result = 0;
		int a = u.getAge();

		if (a >= 7 && a <= 9) {
			result = 1800;
			return result;
		} else if (a >= 4 && a <= 6) {
			result = 1600;
			return result;
		} else if (a >= 1 && a <= 3) {
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
		} else if (u.getGender().equals("W") || u.getGender().equals("w")) {
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

	public static int getSodiumBase(UserInfo u) {
		int result = 0;
		int a = u.getAge();

		if (a <= 2) {
			result = 900;
		} else if (a <= 5) {
			result = 1000;
		} else if (a <= 8) {
			result = 1200;
		} else if (a <= 11) {
			result = 1400;
		} else if (a <= 64) {
			result = 1500;
		} else if (a <= 74) {
			result = 1300;
		} else {
			result = 1100;
		}

		return result;
	}

	public static void foodSort(FoodInfo f) {
		switch (f.category) {
		case 0:
			ctg_junk.add(f);
			break;
		case 1:
			ctg_stew.add(f);
			break;
		case 2:
			ctg_meat.add(f);
			break;
		case 3:
			ctg_drink.add(f);
			break;
		case 4:
			ctg_agricultural.add(f);
			break;
		case 5:
			ctg_snack.add(f);
			break;
		default:
			ctg_other.add(f);
		}
	}
	public static void initDP() {
		for (int i = 0; i < num_food + 1; i++) {
			for (int j = 0; j < prefer_sodium+1; j++) {
				dp[i][j] = -1;
			}
		}
	}
	// public static boolean isJunkFood(FoodInfo f) {
	// if(f.getName().indexOf("pizza") >= 0 || f.getName().indexOf("Pizza") >= 0) {
	// return true;
	// }
	// else if(f.getName().indexOf("hamburger") >=0 ||
	// f.getName().indexOf("Hamburger") >= 0) {
	// return true;
	// }
	// else if(f.getName().indexOf("chicken")>=0 || f.getName().indexOf("Chicken")
	// >= 0) {
	// return true;
	// }
	// else if(f.getCalories() < f.getSodium() && f.getCholesterol() > 10.0) {
	// return true;
	// }
	// return false;
	// }
}
