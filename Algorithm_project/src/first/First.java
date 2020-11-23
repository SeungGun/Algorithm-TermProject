package first;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class UserInfo {
	private String gender;
	private int age;
	private double height;
	private double weight;

	public double enoughSodium;
	public double enoughCalorie;

	public double maximumSodium = 3000;
	public double maximumCalorie;
	public double maxiumumCholesterol = 300;

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
	private String name;
	private double calories;
	private double sodium;
	private double cholesterol;
	private int category;
	private int index_num;

	public FoodInfo(String name_, double calories_, double sodium_, double cholesterol_, int category_, int index_) {
		this.name = name_;
		this.calories = calories_;
		this.sodium = sodium_;
		this.cholesterol = cholesterol_;
		this.category = category_;
		this.index_num = index_;
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
		switch (this.category) {
		case 0:
			return "Junk";
		case 1:
			return "Stew";
		case 2:
			return "Meat";
		case 3:
			return "Drink";
		case 4:
			return "Agricultural";
		case 5:
			return "Snack";
		case 6:
			return "Seafood";
		case 7:
			return "Other";
		default:
			return "MATCH ERROR";
		}
	}

	public int getIndexNum() {
		return this.index_num;
	}
}

class DailyDiet {
	public ArrayList<String> Breakfast = new ArrayList<String>();
	public ArrayList<String> Lunch = new ArrayList<String>();
	public ArrayList<String> Dinner = new ArrayList<String>();

	private double calorieTotal = 0;
	private double sodiumTotal = 0;
	private double cholesterolTotal = 0;

	public boolean isjunkFood = false;

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
	public static ArrayList<FoodInfo> ctg_seafood; // 6
	public static ArrayList<FoodInfo> ctg_other; // 7
	public static double[][] dp;
	public static FoodInfo[] foodList;
	public static FoodInfo[] first_List;
	public static double sum = 0.0;
	public static final int prefer_sodium = 3000;
	public static ArrayList<Integer> backtracked;
	public static boolean isThereJunk = false;
	public static boolean isFirst = false;
	public static boolean[] isSelected;
	public static DailyDiet[] weeks = new DailyDiet[7];

	public static void main(String[] args) {
		// Process : initDP() -> foodSort() -> knapsack() -> backtrackingDP() ->
		// findThreshold() -> reSettingList() ==> 7 execution for a week
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

		weeks = new DailyDiet[7];
		for (int i = 0; i < weeks.length; i++)
			weeks[i] = new DailyDiet();

		ctg_junk = new ArrayList<FoodInfo>();
		ctg_stew = new ArrayList<FoodInfo>();
		ctg_meat = new ArrayList<FoodInfo>();
		ctg_drink = new ArrayList<FoodInfo>();
		ctg_agricultural = new ArrayList<FoodInfo>();
		ctg_snack = new ArrayList<FoodInfo>();
		ctg_seafood = new ArrayList<FoodInfo>();
		ctg_other = new ArrayList<FoodInfo>();

		isSelected = new boolean[num_food];

		Base = getBase(user);
		Promotion = getPromotion(user);
		foodList = new FoodInfo[num_food];
		first_List = foodList;
		// NAME | CALORIES | SODIUM | CHOLESTEROL | CATEGORY
		while (inputStream.hasNextLine()) {
			String temp = inputStream.nextLine();
			System.out.println(index + " " + temp);
			foodList[index] = new FoodInfo(temp.split(" ")[0], Double.parseDouble(temp.split(" ")[1]),
					Double.parseDouble(temp.split(" ")[2]), Double.parseDouble(temp.split(" ")[3]),
					Integer.parseInt(temp.split(" ")[4]), index);
			index++;
		}

		user.enoughSodium = getSodiumBase(user);
		user.enoughCalorie = getBase(user);
		user.maximumCalorie = getPromotion(user);

		System.out.println("\n");
		System.out.println("You should eat " + Base + " between " + Promotion + " calorie");
		System.out.println("\n");
		System.out.println("You should eat " + user.enoughSodium + " between " + prefer_sodium + " sodium");
		System.out.println("\n");
		System.out.println("You should eat cholesterol below " + user.maxiumumCholesterol);

		for (int i = 0; i < foodList.length; i++) {
			sum += foodList[i].getCalories();
		}
		System.out.println("\nTotal sum of Input Foods : " + sum);
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
		System.out.println("Seafoods Are: ");
		for (int i = 0; i < ctg_seafood.size(); i++) {
			System.out.println(ctg_seafood.get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Others Are: ");
		for (int i = 0; i < ctg_other.size(); i++) {
			System.out.println(ctg_other.get(i).getName());
		}
		for (int week = 0; week < 7; week++) {
			System.out.println("Week : " + week + "------------------------------------------");
			dp = new double[foodList.length + 1][prefer_sodium + 1];
			initDP();

			knapsack(0, prefer_sodium);

			backtrackingDP(Promotion);

			List<Integer> result = backtracked.stream().distinct().collect(Collectors.toList()); // remove duplicate
			ArrayList<Integer> tmp_list = new ArrayList<Integer>();
			for (int i = 0; i < result.size(); i++) {
				tmp_list.add(result.get(i));
			}
			backtracked = tmp_list;
			double s = 0;
			for (int i = 0; i < backtracked.size(); i++) {
				isSelected[backtracked.get(i)] = true;
				// System.out.println("Backtrack : "+foodList[backtracked.get(i)].getName());
				s += foodList[backtracked.get(i)].getCalories();
			}
			System.out.println("\nbacktracked sum : " + s);

			adjustCalories(backtracked, s);

			MatchRice(backtracked);
			s = 0;
			for (int i = 0; i < backtracked.size(); i++) {
				s += foodList[backtracked.get(i)].getCalories();
			}
			System.out.println("\nModified backtracked sum : " + s);

			System.out.println("\nSelected Food list :");

			for (int i = 0; i < backtracked.size(); i++) {
				isSelected[backtracked.get(i)] = true;
				System.out.println((i + 1) + " : " + backtracked.get(i) + " :" + foodList[backtracked.get(i)].getName()
						+ ", category : " + foodList[backtracked.get(i)].getCategoryInString());

			}
			createCombinationDaily(backtracked, week);

			backtracked.clear();
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

	public static void createCombinationDaily(ArrayList<Integer> set, int week) {
		if (set.size() == 3) {
			boolean[] keys = new boolean[] { false, false, false };

			for (int i = 0; i < set.size(); i++) {
				if (!keys[0] && foodList[set.get(i)].getCategory() == 7 || foodList[set.get(i)].getCategory() == 1
						|| foodList[set.get(i)].getCategory() == 4) {
					weeks[week].Breakfast.add(foodList[set.get(i)].getName());
					keys[0] = true;
				} else if (!keys[1] && foodList[set.get(i)].getCategory() == 7
						|| foodList[set.get(i)].getCategory() == 5) {
					weeks[week].Lunch.add(foodList[set.get(i)].getName());
					keys[1] = true;
				} else if (!keys[2] && foodList[set.get(i)].getCategory() == 2
						|| foodList[set.get(i)].getCategory() == 6) {
					weeks[week].Dinner.add(foodList[set.get(i)].getName());
					keys[2] = true;
				} else {
					if (keys[0] && keys[1])
						weeks[week].Dinner.add(foodList[set.get(i)].getName());
					else if (keys[1] && keys[2])
						weeks[week].Breakfast.add(foodList[set.get(i)].getName());
					else if (keys[0] && keys[2])
						weeks[week].Lunch.add(foodList[set.get(i)].getName());
					else
						weeks[week].Lunch.add(foodList[set.get(i)].getName());
				}

			}
			System.out.println(weeks[week].Breakfast.get(0));
			System.out.println(weeks[week].Lunch.get(0));
			System.out.println(weeks[week].Dinner.get(0));
		} else {
			int rice = -1;
			int stew = -1;
			int meat_drink = -1;
			int snack = -1;
			for (int i = 0; i < set.size(); i++) {
				if (i > 2) {
					if (foodList[set.get(i)].getCategory() == 1) {
						rice = check_Rice(set,week);
						stew = check_Stew(set,week);
						System.out.println(rice+" , "+stew);
						if(stew == 1) weeks[week].Lunch.add(foodList[set.get(i)].getName());
						else if(stew == 2) weeks[week].Dinner.add(foodList[set.get(i)].getName());
						else if(stew == 3) weeks[week].Breakfast.add(foodList[set.get(i)].getName());
						else 
						if(rice == 1) weeks[week].Breakfast.add(foodList[set.get(i)].getName());
						else if(rice == 2) weeks[week].Lunch.add(foodList[set.get(i)].getName());
						else if(rice == 3) weeks[week].Dinner.add(foodList[set.get(i)].getName());
					}
					else if(foodList[set.get(i)].getName().indexOf("Rice") > 0) {
						stew = check_Stew(set,week);
					}
				}
				if (i == 0) {
					weeks[week].Breakfast.add(foodList[set.get(i)].getName());

				} else if (i == 1) {
					weeks[week].Lunch.add(foodList[set.get(i)].getName());

				} else if (i == 2) {
					weeks[week].Dinner.add(foodList[set.get(i)].getName());
				}
				stew = -1;
			}
			for (int i = 0; i < weeks[week].Breakfast.size(); i++)
				System.out.println(weeks[week].Breakfast.get(i));
			System.out.println();
			for (int i = 0; i < weeks[week].Lunch.size(); i++)
				System.out.println(weeks[week].Lunch.get(i));
			System.out.println();

			for (int i = 0; i < weeks[week].Dinner.size(); i++)
				System.out.println(weeks[week].Dinner.get(i));
			System.out.println();

		}
	}

	public static void MatchRice(ArrayList<Integer> set) {
		for (int i = 0; i < set.size(); i++) {
			if (set.size() < 3 && foodList[set.get(i)].getName().indexOf("Rice") > 0) {
				for (int j = 0; j < ctg_stew.size(); j++) {
					if (!isSelected[ctg_stew.get(j).getIndexNum()]) {
						set.add(ctg_stew.get(j).getIndexNum());
						return;
					}
				}
			}
		}
	}

	public static void adjustCalories(ArrayList<Integer> set, double sum) {
		double max = -1;
		int idx = -1;
		double k = 0;
		int number = -1;
//		System.out.println("Size : "+set.size());
		if (sum <= Promotion && sum >= Base && set.size() >= 3)
			return;

		if (sum <= Promotion || set.size() < 3) {
			if (sum <= Base || set.size() < 3) {
				for (int i = 0; i < foodList.length; i++) {
					if (!isSelected[i]) {
						k = foodList[i].getCalories();
						if (max < k && sum + k <= Promotion && foodList[i].getCategory() != 0) {
							max = k;
							idx = i;
						}
					}
				}
				set.add(idx);
				isSelected[idx] = true;
				if (sum + max <= Base)
					adjustCalories(set, sum + k); // despite adding a max value, if it is less than Base calories,
													// recursively executes.
				else
					return;

			} else
				return;
		}

		for (int i = 0; i < set.size(); i++) {
			int to = set.get(i);
			k = foodList[to].getCalories();
			if (max < sum - k && sum - k <= Promotion) {
				max = sum - k;
				idx = i;
				number = to;
			}
		}
		if (max == -1) {
			for (int i = 0; i < set.size(); i++) {
				int to = set.get(i);
				k = foodList[to].getCalories();
				if (max < sum - k) {
					max = sum - k;
					idx = i;
					number = to;
				}
			}
		}
		if (idx != -1 && number != -1) {
			set.remove(idx);
			isSelected[number] = false;
//			System.out.println("Total Max : " + max + ", And extracted : " + foodList[number].getCalories() + "("
//					+ foodList[number].getName() + "),  its index : " + number);
		}
		if (max > Promotion) {
			adjustCalories(set, max);
		}
	}

	public static double knapsack(int num, double capacity) {
		if (num == foodList.length) {
			return 0;
		}
		double temp = dp[num][(int) capacity];
		if (temp != -1)
			return temp;
		if (foodList[num].getSodium() <= capacity) {
			double tmp = knapsack(num + 1, (int) (capacity - foodList[num].getSodium()));

			if (foodList[num].getCategory() == 0 && !isThereJunk) {
				isThereJunk = true;
				isFirst = true;
				temp = tmp + foodList[num].getCalories();
				dp[num][(int) capacity] = temp;
				return temp;
			}
			if (isThereJunk && !isFirst) {
				if (foodList[num].getCategory() == 0)
					temp = tmp;
				else {
					temp = foodList[num].getCalories() + tmp;
				}

			} else if (!isFirst) {
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
		dp[num][(int) capacity] = temp;
		return temp;
	}

	public static void backtrackingDP(int promotion) {
		double t = dp[foodList.length][prefer_sodium];
		int junkCount = 0;
		for (int i = foodList.length; i >= 0; i--) {
			for (int j = prefer_sodium; j >= 0; j--) {
				if (dp[i][j] == -1)
					continue;
				if (t < dp[i][j] && !isSelected[i]) {
					if (foodList[i].getCategoryInString().equals("Junk"))
						junkCount++;
					if (junkCount < 2) {
						t = dp[i][j];
						backtracked.add(i);
					}
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
		switch (f.getCategory()) {
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
		case 6:
			ctg_seafood.add(f);
		default:
			ctg_other.add(f);
		}
	}

	public static void initDP() {
		for (int i = 0; i < foodList.length; i++) {
			for (int j = 0; j < prefer_sodium + 1; j++) {
				dp[i][j] = -1;
			}
		}
	}

	public static int check_Rice(ArrayList<Integer> set, int week) {
		for (int i = 0; i < weeks[week].Breakfast.size(); i++) {
			if (weeks[week].Breakfast.get(i).indexOf("Rice") >= 0) {
				return 1;
			}
		}
		for (int i = 0; i < weeks[week].Lunch.size(); i++) {
			if (weeks[week].Lunch.get(i).indexOf("Rice") >= 0) {
				return 2;
			}
		}
		for(int i =0; i<weeks[week].Dinner.size(); i++) {
			if(weeks[week].Dinner.get(i).indexOf("Rice") >= 0) {
				return 3;
			}
		}
		return -1;
	}

	public static int check_Stew(ArrayList<Integer> set, int week) {
		int sum = 0;
		for (int i = 0; i < weeks[week].Breakfast.size(); i++) {
			for(int j =0; j<set.size(); j++) {
				if (weeks[week].Breakfast.get(i).equals(foodList[set.get(j)].getName())) {
					System.out.println("1 chk");
					sum+= 1;
				}
			}
			
		}
		for (int i = 0; i < weeks[week].Lunch.size(); i++) {
			for(int j =0; j<set.size(); j++) {
				if (weeks[week].Lunch.get(i).equals(foodList[set.get(j)].getName())) {
					System.out.println("2 chk");

					sum += 2;
				}
			}
		}
		for(int i =0; i<weeks[week].Dinner.size(); i++) {
			for(int j =0; j<set.size(); j++) {
				if (weeks[week].Dinner.get(i).equals(foodList[set.get(j)].getName())) {
					System.out.println("3 chk");

					sum+= 3;
				}
			}
		}
		return sum -1;
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
