package com.virtusatest.codetest;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Numerals {

static Map<String, String> TotalSumOfMoney = new HashMap<>();


public static int romanNumeralsToIntConversion(String s) {
    int nums[] = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
        switch (s.charAt(i)) {
            case 'M':
                nums[i] = 1000;
                break;
            case 'D':
                nums[i] = 500;
                break;
            case 'C':
                nums[i] = 100;
                break;
            case 'L':
                nums[i] = 50;
                break;
            case 'X':
                nums[i] = 10;
                break;
            case 'V':
                nums[i] = 5;
                break;
            case 'I':
                nums[i] = 1;
                break;

        }

    }
    int sum = 0;
    for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i] < nums[i + 1])
            sum -= nums[i];

        else {
            sum += nums[i];
        }


    } return sum + nums[nums.length - 1];
}




public static void whichMetal(String s, String metal) {


    double sum = 0;

    String[] output = s.split(Pattern.quote(metal));


    String individualWords = output[0];
    String[] outputWords = individualWords.split(" ");

    String romanNumeral = "";


    for (String ow : outputWords) {
        romanNumeral += TotalSumOfMoney.get(ow);
    }


    double convertedInt = romanNumeralsToIntConversion(romanNumeral);

    String creditWords = output[1];

    String[] creditOutputWords = creditWords.split(" ");

    int money = creditOutputWords.length - 2;

    double creditMoney = Double.parseDouble(creditOutputWords[money]);

    double valueToInsert = 0;
    if (convertedInt !=0 ) {
        valueToInsert = creditMoney / convertedInt;
    }

    else {
        System.out.println("creditMoney " + creditMoney);
        System.out.println("convertedInt " + convertedInt);
    }

    TotalSumOfMoney.put(metal, Double.toString(valueToInsert));
}

static void translate(String s, String separator){

    double sum = 0;

     DecimalFormat df2  = new DecimalFormat("");


    String[] output = s.split(Pattern.quote(separator));


    String individualWords = output[1];

    String[] outputWords = individualWords.split(" ");

    String romanNumeral ="";

    double value =0;

    for (String ow: outputWords){

        if (!ow.equals("")) {


            if (!ow.equals("Silver") && (!ow.equals("Gold") && (!ow.equals("Iron")))) {


                romanNumeral += TotalSumOfMoney.get(ow);

            }
            else {


                value= Double.parseDouble(TotalSumOfMoney.get(ow));

                sum = value * romanNumeralsToIntConversion(romanNumeral);


                System.out.println(output[1].trim().replaceAll(" \\?", "") +" is " + df2.format(sum).toString().replaceAll(",", "") + " Credits");
            }
        }

    }


     if (!output[1].contains("Silver") && !output[1].contains("Gold") && !output[1].contains("Iron"))
    System.out.println(output[1].trim().replaceAll(" \\?", "") + " is " + romanNumeralsToIntConversion(romanNumeral));

}

public static boolean validate(String s){
    if (s.contains("DD") || s.contains("LL") || s.contains("VV") || s.contains("MMMM") || s.contains("CCCC") || s.contains("XXXX"))
        return false;
    else
        return true;
}


public static void parse(String s){



    boolean validString = validate(s);

    if (validString == true) {

        if (s.contains("how many Credits")) {
            translate(s, "how many Credits is");
        } else if (s.contains("Silver")) {

            whichMetal(s, "Silver");

        } else if (s.contains("Gold")) {
            whichMetal(s, "Gold");
        } else if (s.contains("Iron")) {
            whichMetal(s, "Iron");
        } else if (s.contains("how much is")) {
            translate(s, "how much is ");
        } else if (!s.equals("") && s.contains("is")) {
            String delimiter = " is ";

            String[] output = s.split(Pattern.quote(delimiter));

            TotalSumOfMoney.put(output[0], output[1]);
        } else System.out.println("I have no idea what you are talking about");

    }

    else {
        System.out.println("The string is invalid");
    }
}
}