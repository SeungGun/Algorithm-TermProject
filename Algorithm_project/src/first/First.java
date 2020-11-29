package first;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



class output {
   private JFrame frame1;

   public output(UserInfo u, HashMap<String, Double>[] f) {
      result(u, f);
      frame1.setVisible(true);
   }

   public void result(UserInfo user, HashMap<String, Double>[] foods) {
      int i = 1, j = 1;

      frame1 = new JFrame();
      frame1.setTitle("결과");
      frame1.setBounds(100, 100, 1700, 800);
      frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame1.getContentPane().setLayout(null);

      JPanel output = new JPanel();
      output.setBounds(0, 0, 1700, 800);
      frame1.getContentPane().add(output);
      output.setLayout(null);

      
      JPanel sund = new JPanel();
      sund.setBounds(200, 40, 200, 40);
      sund.setBorder(new TitledBorder(new LineBorder(Color.red,5)));
      output.add(sund);
      JPanel mond = new JPanel();
      mond.setBounds(400,40,200,40);
      mond.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(mond);
      JPanel tues = new JPanel();
      tues.setBounds(600,40,200,40);
      tues.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(tues);
      JPanel wedn = new JPanel();
      wedn.setBounds(800,40,200,40);
      wedn.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(wedn);
      JPanel thru = new JPanel();
      thru.setBounds(1000,40,200,40);
      thru.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(thru);
      JPanel frid = new JPanel();
      frid.setBounds(1200,40,200,40);
      frid.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(frid);
      JPanel satu = new JPanel();
      satu.setBounds(1400,40,200,40);
      satu.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(satu);
      JPanel brea = new JPanel();
      brea.setBounds(75,150,100,100);
      brea.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(brea);
      JPanel lunc = new JPanel();
      lunc.setBounds(74,350,100,100);
      lunc.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(lunc);
      JPanel dinn = new JPanel();
      dinn.setBounds(74,550,100,100);
      dinn.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,5)));
      output.add(dinn);
      
      
      JLabel sun = new JLabel("일요일");
      sun.setFont(new java.awt.Font("굴림",1, 18));
      sun.setHorizontalAlignment(SwingConstants.CENTER);
      sund.add(sun);

      JLabel mon = new JLabel("월요일");
      mon.setFont(new java.awt.Font("굴림",1, 18));
      mon.setHorizontalAlignment(SwingConstants.CENTER);
      mond.add(mon);

      JLabel tue = new JLabel("화요일");
      tue.setFont(new java.awt.Font("굴림",1, 18));
      tue.setHorizontalAlignment(SwingConstants.CENTER);
      tues.add(tue);

      JLabel wed = new JLabel("수요일");
      wed.setFont(new java.awt.Font("굴림",1, 18));
      wed.setHorizontalAlignment(SwingConstants.CENTER);
      wedn.add(wed);

      JLabel tur = new JLabel("목요일");
      tur.setFont(new java.awt.Font("굴림",1, 18));
      tur.setHorizontalAlignment(SwingConstants.CENTER);
      thru.add(tur);

      JLabel fri = new JLabel("금요일");
      fri.setFont(new java.awt.Font("굴림",1, 18));
      fri.setHorizontalAlignment(SwingConstants.CENTER);
      frid.add(fri);

      JLabel sat = new JLabel("토요일");
      sat.setFont(new java.awt.Font("굴림",1, 18));
      sat.setHorizontalAlignment(SwingConstants.CENTER);
      satu.add(sat);

      JLabel gen = new JLabel("성별 : " + (user.getGender().equals("M") ? "남자" : "여자"));
      gen.setBounds(30, 40, 200, 20);
      gen.setHorizontalAlignment(SwingConstants.LEFT);
      output.add(gen);

      JLabel age = new JLabel("나이 : " + user.getAge());
      age.setBounds(30, 60, 200, 20);
      age.setHorizontalAlignment(SwingConstants.LEFT);
      output.add(age);

      JLabel hei = new JLabel("키 : " + user.getHeight() * 100 + "cm");
      hei.setBounds(30, 80, 200, 20);
      hei.setHorizontalAlignment(SwingConstants.LEFT);
      output.add(hei);

      JLabel wei = new JLabel("몸무게 : " + user.getWeight() + "kg");
      wei.setBounds(30, 100, 200, 20);
      wei.setHorizontalAlignment(SwingConstants.LEFT);
      output.add(wei);

      JLabel mor = new JLabel("아침");
      mor.setFont(new java.awt.Font("굴림",1, 18));
      mor.setHorizontalAlignment(SwingConstants.CENTER);
      brea.add(mor);

      JLabel aft = new JLabel("점심");
      aft.setFont(new java.awt.Font("굴림",1, 18));
      aft.setHorizontalAlignment(SwingConstants.CENTER);
      lunc.add(aft);

      JLabel eve = new JLabel("저녁");
      eve.setFont(new java.awt.Font("굴림",1, 18));
      eve.setHorizontalAlignment(SwingConstants.CENTER);
      dinn.add(eve);

      for (HashMap<String, Double> h : foods) {
         int num = 0;
         for (String key : h.keySet()) {
            JLabel label = new JLabel(key + "(" + h.get(key) + "kcal)");
            label.setBounds(200 * i, 200 * j - 60 + 30 * num++, 200, 20);
            label.setFont(new java.awt.Font("LATO", 1, 15));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            output.add(label);
         }
         j++;
         if (j == 4) {
            j = 1;
            i++;
         }
      }

      // 알고리즘 시작버튼 (입력 버튼 포함)
      JButton restart = new JButton("다시하기");
      restart.setBounds(775, 700, 150, 30);
      restart.setHorizontalAlignment(SwingConstants.CENTER);
      restart.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frame1.dispose(); //출력 프레임 종료
            new input();
         }
      });
      output.add(restart);

   }
}

class input {

   private JFrame frame;
    private javax.swing.JButton jButton1;
       private javax.swing.JComboBox<String> jComboBox1;
       private javax.swing.JLabel jLabel1;
       private javax.swing.JLabel jLabel2;
       private javax.swing.JLabel jLabel3;
       private javax.swing.JLabel jLabel4;
       private javax.swing.JLabel jLabel5;
       private javax.swing.JTextField jTextField1;
       private javax.swing.JTextField jTextField2;
       private javax.swing.JTextField jTextField3;

   public input() {
      initialize();
      frame.setVisible(true);

        
         
   }

   public void initialize() {
      frame = new JFrame();
      jComboBox1= new JComboBox();
      jLabel1 = new JLabel();
      jLabel2 = new JLabel();
      jLabel3 = new JLabel();
      jLabel4 = new JLabel();
      jLabel5 = new JLabel();
      jTextField1 = new JTextField();
      jTextField2 = new JTextField();
      jTextField3 = new JTextField();
      jButton1 = new JButton();
      
      frame.setTitle("Daily Diet");
      frame.setBounds(100, 100, 700, 550);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      

        jLabel1.setFont(new java.awt.Font("Lato Semibold", 1, 36)); // NOI18N
        jLabel1.setText("Automatic diet table ");

        jButton1.setFont(new java.awt.Font("굴림", 1, 24)); // NOI18N
        jButton1.setText("시작");

        jLabel2.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N
        jLabel2.setText("성별 :");

        jLabel3.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N
        jLabel3.setText("나이 :");

        jLabel4.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N
        jLabel4.setText("키(M)     :");

        jLabel5.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N
        jLabel5.setText("체중(Kg) :");

        jTextField1.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N

        jTextField2.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N

        jTextField3.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "W", "M" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101))
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 290, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(172, 172, 172))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

      // 알고리즘 시작버튼 (입력 버튼 포함)
      
      jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String gen = (String) jComboBox1.getSelectedItem();
            int age = Integer.parseInt(jTextField1.getText());
            double hei = Double.parseDouble(jTextField2.getText());
            double wei = Double.parseDouble(jTextField3.getText());
            UserInfo u = new UserInfo(gen, age, hei, wei);
            System.out.println("Enter");
            frame.dispose(); // 입력 프레임 종료
            new DietPlanner(u);
         }
      });
   }
}

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

class DietPlanner {
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

   public DietPlanner(UserInfo u) {
      insert(u);
   }

   public void insert(UserInfo user) {
      // Process : initDP() -> knapsack() -> backtrackingDP() -> adjustCalories() ->
      // createCombinationDaily() ==> 7 execution for a week

      String fileName = "foodInfo.txt";
      Scanner inputStream = null;
      int index = 0;
      Scanner sc = new Scanner(System.in);
      HashMap<String, Double>[] foods = new HashMap[21]; // 음식의 이름과 칼로리 리스트
      HashMap<String, Double> temp_food; // 여기다 먼저 입력하고 리스트에 저장
      System.out.println("Enter Gender, Age, Height(m), Weight(kg) in order :");

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
      for (int i = 0, k = 0; i < 7; i++) {
         System.out.println("Day #" + (i + 1) + " Diet Menu------------------");
         System.out.println("\n* Breakfast : ");
         temp_food = new HashMap<String, Double>();
         for (int j = 0; j < weeks[i].Breakfast.size(); j++) {
            System.out.println(foodList[weeks[i].Breakfast.get(j)].getName() + " => "
                  + foodList[weeks[i].Breakfast.get(j)].getCalories() + "kcal");
            temp_food.put(foodList[weeks[i].Breakfast.get(j)].getName(),
                  foodList[weeks[i].Breakfast.get(j)].getCalories());
         }
         foods[k++] = temp_food;
         System.out.println("\n* Lunch : ");
         temp_food = new HashMap<String, Double>();
         for (int j = 0; j < weeks[i].Lunch.size(); j++) {
            System.out.println(foodList[weeks[i].Lunch.get(j)].getName() + " => "
                  + foodList[weeks[i].Lunch.get(j)].getCalories() + "kcal");
            temp_food.put(foodList[weeks[i].Lunch.get(j)].getName(), foodList[weeks[i].Lunch.get(j)].getCalories());
         }
         foods[k++] = temp_food;
         System.out.println("\n*Dinner : ");
         temp_food = new HashMap<String, Double>();
         for (int j = 0; j < weeks[i].Dinner.size(); j++) {
            System.out.println(foodList[weeks[i].Dinner.get(j)].getName() + " => "
                  + foodList[weeks[i].Dinner.get(j)].getCalories() + "kcal");
            temp_food.put(foodList[weeks[i].Dinner.get(j)].getName(),
                  foodList[weeks[i].Dinner.get(j)].getCalories());
         }
         foods[k++] = temp_food;
         System.out.println();
      }
      sc.close();
      new output(user, foods); // 결과창 실행
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
         boolean bre = false;
         boolean lun = false;
         boolean din = false;
         boolean isSnack_b = false;
         boolean isSnack_l = false;
         boolean isSnack_d = false;
         for (int i = 0; i < set.size(); i++) {
            if (i > 2) {
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

}

public class First {
   public static void main(String[] args) {
      
   
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(First.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(First.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(First.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(First.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      new input();
        
      
        
        
      
   }
}