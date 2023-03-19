import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение арабскими или римскими цифрами");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String result = calc(input);
        System.out.println(result);
    }

    public static String calc(String input) throws RuntimeException{
        String[] arr = input.split(" ");
        if(arr.length != 3){
            throw new RuntimeException("строка не является математической операцией");
        }
        String aStr = arr[0];
        String bStr = arr[2];
        String c = arr[1];

        int a = 0;
        int b = 0;
        boolean isRoman = false;

        try{
            a = Integer.parseInt(aStr);
            b = Integer.parseInt(bStr);
        } catch (NumberFormatException e){
            a = romanToArabic(aStr);
            b = romanToArabic(bStr);
            isRoman = true;
        }

        int result = 0;

        if(a < 1 || a > 10 || b < 1 || b > 10){
            throw new RuntimeException("калькулятор умеет работать только с числами от 1 до 10");
        }

        if(c.equals("+")){
            result = a + b;
        } else if(c.equals("-")){
            result = a - b;
        } else if (c.equals("*")){
            result = a * b;
        } else if(c.equals("/")){
            result = a / b;
        } else{
            throw new RuntimeException("Неверная арифметическая операция");
        }

        if(isRoman){
            if(result <=0){
                throw new RuntimeException("Римское число не может быть меньше I");
            }

            StringBuilder romanResult = new StringBuilder();
            while(result >= 1000){
                romanResult.append('M');
                result -= 1000;
            }
            if(result >= 900){
                romanResult.append("CM");
                result -= 900;
            }
            if(result >= 500){
                romanResult.append("D");
                result -= 500;
            }
            if(result >= 400){
                romanResult.append("CD");
                result -= 400;
            }
            while(result >= 100){
                romanResult.append("C");
                result -= 100;
            }
            if(result >= 90){
                romanResult.append("XC");
                result -= 90;
            }
            if(result >= 50){
                romanResult.append("L");
                result -= 50;
            }
            if(result >= 40){
                romanResult.append("XL");
                result -= 40;
            }
            while(result >= 10){
                romanResult.append("X");
                result -= 10;
            }
            if(result >= 9){
                romanResult.append("IX");
                result -= 9;
            }
            if(result >= 5){
                romanResult.append("V");
                result -= 5;
            }
            if(result >= 4){
                romanResult.append("IV");
                result -= 4;
            }
            while(result >= 1){
                romanResult.append("I");
                result -= 1;
            }
            return romanResult.toString();
        } else {
            return String.valueOf(result);
        }
    }

    public static int romanToArabic(String romanNumeral){
        int result = 0;
        String romanNumeralUpperCase = romanNumeral.toUpperCase();
        for(int i = 0; i < romanNumeralUpperCase.length(); i++){
            char letter = romanNumeralUpperCase.charAt(i);
            if(letter == 'I'){
                if(i + 1 < romanNumeralUpperCase.length() && romanNumeralUpperCase.charAt(i+1) == 'V'){
                    result +=4;
                    i++;
                } else if(i+1 < romanNumeralUpperCase.length() && romanNumeralUpperCase.charAt(i+1) == 'X'){
                    result +=9;
                    i++;
                } else{
                    result +=1;
                }
            } else if(letter == 'V'){
                result += 5;
            } else if(letter == 'X'){
                if(i+1 < romanNumeralUpperCase.length() && romanNumeralUpperCase.charAt(i+1) == 'L'){
                    result += 40;
                    i++;
                } else if(i+1 < romanNumeralUpperCase.length() && romanNumeralUpperCase.charAt(i+1) == 'C'){
                    result +=90;
                    i++;
                } else{
                    result += 10;
                }
            } else if(letter == 'L'){
                result += 50;
            } else if(letter == 'C'){
                result += 100;
            } else if(letter == 'D'){
                result += 500;
            } else if(letter == 'M'){
                result += 1000;
            } else{
                throw new RuntimeException("используются одновременно разные системы счисления");
            }
        }
        return result;

    }

}