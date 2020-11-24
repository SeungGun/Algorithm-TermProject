package first;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	public ArrayList<Integer> Breakfast = new ArrayList<Integer>();
	public ArrayList<Integer> Lunch = new ArrayList<Integer>();
	public ArrayList<Integer> Dinner = new ArrayList<Integer>();

	private double calorieTotal = 0;
	private double sodiumTotal = 0;
	private double cholesterolTotal = 0;

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
}

public class First {
	public static double Base = 0.0;
	public static int Recommend = 0;
	public static int num_food;
	public static final int prefer_sodium = 3000;
	public static double sum = 0.0;

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
	public static DailyDiet[] weeks = new DailyDiet[7];
	public static ArrayList<Integer> backtracked;

	public static boolean isThereJunk = false;
	public static boolean[] isSelected;

	public static void main(String[] args) {
		// Process : initDP() -> knapsack() -> backtrackingDP() -> adjustCalories() -> createCombinationDaily() ==> 7 execution for a week
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

		Base = getBase(user);
		Recommend = getRecommend(user);

		foodList = new FoodInfo[num_food];
		isSelected = new boolean[num_food];
		backtracked = new ArrayList<Integer>();

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
		user.maximumCalorie = getRecommend(user);

		System.out.println("\n");
		System.out.println("You should eat " + Base + " between " + Recommend + " calorie");
		System.out.println("\n");
		System.out.println("You should eat " + user.enoughSodium + " between " + prefer_sodium + " sodium");
		System.out.println("\n");
		System.out.println("You should eat cholesterol below " + user.maxiumumCholesterol);

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
		System.out.println("\n");

		for (int week = 0; week < 7; week++) {
			dp = new double[foodList.length + 1][prefer_sodium + 1];
			initDP();

			knapsack(0, prefer_sodium);

			backtrackingDP(Recommend);

			List<Integer> result = backtracked.stream().distinct().collect(Collectors.toList()); // remove duplicate
			ArrayList<Integer> tmp_list = new ArrayList<Integer>();
			for (int i = 0; i < result.size(); i++) {
				tmp_list.add(result.get(i));
			}
			backtracked = tmp_list;
			double s = 0;
			for (int i = 0; i < backtracked.size(); i++) {
				isSelected[backtracked.get(i)] = true;
				s += foodList[backtracked.get(i)].getCalories();
			}

			adjustCalories(backtracked, s);

			MatchRice(backtracked);

			for (int i = 0; i < backtracked.size(); i++) {
				isSelected[backtracked.get(i)] = true;
			}
			createCombinationDaily(backtracked, week);

			backtracked.clear();
		}

		for (int i = 0; i < 7; i++) {
			System.out.println("Day #" + (i + 1) + " Diet Menu------------------");
			System.out.println("\n* Breakfast : ");
			for (int j = 0; j < weeks[i].Breakfast.size(); j++)
				System.out.println(foodList[weeks[i].Breakfast.get(j)].getName() + " => "
						+ foodList[weeks[i].Breakfast.get(j)].getCalories() + "kcal");
			System.out.println("\n* Lunch : ");
			for (int j = 0; j < weeks[i].Lunch.size(); j++)
				System.out.println(foodList[weeks[i].Lunch.get(j)].getName() + " => "
						+ foodList[weeks[i].Lunch.get(j)].getCalories() + "kcal");
			System.out.println("\n*Dinner : ");
			for (int j = 0; j < weeks[i].Dinner.size(); j++)
				System.out.println(foodList[weeks[i].Dinner.get(j)].getName() + " => "
						+ foodList[weeks[i].Dinner.get(j)].getCalories() + "kcal");
			System.out.println();
		}
		sc.close();
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
					weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());
					keys[0] = true;
				} else if (!keys[1] && foodList[set.get(i)].getCategory() == 7
						|| foodList[set.get(i)].getCategory() == 5) {
					weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
					keys[1] = true;
				} else if (!keys[2] && foodList[set.get(i)].getCategory() == 2
						|| foodList[set.get(i)].getCategory() == 6) {
					weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
					keys[2] = true;
				} else {
					if (keys[0] && keys[1])
						weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
					else if (keys[1] && keys[2])
						weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());
					else if (keys[0] && keys[2])
						weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
					else
						weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
				}

			}

		} else {
			int rice = -1;
			boolean bre = false;
			boolean lun = false;
			boolean din = false;
			boolean isSnack_b = false;
			boolean isSnack_l = false;
			boolean isSnack_d = false;
			for (int i = 0; i < set.size(); i++) {
				if (i > 2) {
					int flag = 0;
					if (foodList[set.get(i)].getCategory() == 1) {
						for (int j = 0; j < i; j++) {
							if (foodList[set.get(j)].getCategory() == 1) {
								if (j % 3 == 0)
									bre = true;
								else if (j % 3 == 1)
									lun = true;
								else if (j % 3 == 2)
									din = true;
							} else if (foodList[set.get(j)].getCategory() == 5) {
								if (j % 3 == 0)
									isSnack_b = true;
								else if (j % 3 == 1)
									isSnack_l = true;
								else if (j % 3 == 2)
									isSnack_d = true;
								flag = 1;
							}
						}
						if (!bre) {
							weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());
						} else if (!lun && !isSnack_l) {
							weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
						} else if (!din) {
							weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
						} else {
							if (i % 3 == 0) {
								weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());
							} else if (i % 3 == 1) {
								weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
							} else if (i % 3 == 2) {
								weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
							}
						}

						if (flag == 1) {
							flag = 0;
							continue;
						}
						rice = check_Rice(set, week);

						if (rice == 1)
							weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());
						else if (rice == 2)
							weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
						else if (rice == 3)
							weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
					} else if (foodList[set.get(i)].getCategory() == 5) {
						if (isSnack_b)
							weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());
						else if (isSnack_l)
							weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
						else if (isSnack_d)
							weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
						else {
							if (i % 3 == 0) {
								weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());

							} else if (i % 3 == 1) {
								weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());

							} else if (i % 3 == 2) {

								weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
							}
						}
					} else {
						if (i % 3 == 0) {
							weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());

						} else if (i % 3 == 1) {
							weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());

						} else if (i % 3 == 2) {

							weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
						}
					}
				}

				if (i == 0) {
					weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());

				} else if (i == 1) {
					if (foodList[set.get(i)].getCategory() == 5) {
						for (int j = 0; j < i; j++) {
							if (foodList[set.get(j)].getCategory() == 1) {
								if (j % 3 == 0)
									bre = true;
								else if (j % 3 == 1)
									lun = true;
								else if (j % 3 == 2)
									din = true;
								continue;
							} else if (foodList[set.get(j)].getCategory() == 5) {
								if (j % 3 == 0)
									isSnack_b = true;
								else if (j % 3 == 1)
									isSnack_l = true;
								else if (j % 3 == 2)
									isSnack_d = true;
							}

						}
						if (isSnack_b)
							weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());
						else if (isSnack_l)
							weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
						else if (isSnack_d)
							weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
						else
							weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
					} else
						weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());

				} else if (i == 2) {
					if (foodList[set.get(i)].getCategory() == 5) {
						for (int j = 0; j < i; j++) {
							if (foodList[set.get(j)].getCategory() == 1) {
								if (j % 3 == 0)
									bre = true;
								else if (j % 3 == 1)
									lun = true;
								else if (j % 3 == 2)
									din = true;
								continue;
							} else if (foodList[set.get(j)].getCategory() == 5) {
								if (j % 3 == 0)
									isSnack_b = true;
								else if (j % 3 == 1)
									isSnack_l = true;
								else if (j % 3 == 2)
									isSnack_d = true;
							}

						}
						if (isSnack_b)
							weeks[week].Breakfast.add(foodList[set.get(i)].getIndexNum());
						else if (isSnack_l)
							weeks[week].Lunch.add(foodList[set.get(i)].getIndexNum());
						else if (isSnack_d)
							weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
						else
							weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
					} else
						weeks[week].Dinner.add(foodList[set.get(i)].getIndexNum());
				}
			}
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
		if (sum <= Recommend && sum >= Base && set.size() >= 3)
			return;

		if (sum <= Recommend || set.size() < 3) {
			if (sum <= Base || set.size() < 3) {
				for (int i = 0; i < foodList.length; i++) {
					if (!isSelected[i]) {
						k = foodList[i].getCalories();
						if (max < k && sum + k <= Recommend && foodList[i].getCategory() != 0) {
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
			if (max < sum - k && sum - k <= Recommend) {
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
		}
		if (max > Recommend) {
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
				temp = tmp + foodList[num].getCalories();
				dp[num][(int) capacity] = temp;
				return temp;
			}
			if (isThereJunk) {
				if (foodList[num].getCategory() == 0)
					temp = tmp;
				else {
					temp = foodList[num].getCalories() + tmp;
				}
			}
			if (temp > Recommend) {
				temp = tmp;
			}
		}

		double tmp2 = knapsack(num + 1, capacity);
		if (tmp2 > temp && tmp2 != 0 && temp != 0) {
			temp = tmp2;
		}
		dp[num][(int) capacity] = temp;
		return temp;
	}

	public static void backtrackingDP(int Recommend) {
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

	public static int getRecommend(UserInfo u) {
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
			if (foodList[weeks[week].Breakfast.get(i)].getName().indexOf("Rice") >= 0) {
				return 1;
			}
		}
		for (int i = 0; i < weeks[week].Lunch.size(); i++) {
			if (foodList[weeks[week].Lunch.get(i)].getName().indexOf("Rice") >= 0) {
				return 2;
			}
		}
		for (int i = 0; i < weeks[week].Dinner.size(); i++) {
			if (foodList[weeks[week].Dinner.get(i)].getName().indexOf("Rice") >= 0) {
				return 3;
			}
		}
		return -1;
	}

	public static void showDaily(int week) {
		for (int i = 0; i < weeks[week].Breakfast.size(); i++) {
			System.out.println(weeks[week].Breakfast.get(i));
		}
		System.out.println("------------------------------------");
		for (int i = 0; i < weeks[week].Lunch.size(); i++) {
			System.out.println(weeks[week].Lunch.get(i));
		}
		System.out.println("------------------------------------");

		for (int i = 0; i < weeks[week].Dinner.size(); i++) {
			System.out.println(weeks[week].Dinner.get(i));
		}
		System.out.println("------------------------------------");
	}
}
