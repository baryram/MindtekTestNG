package assignments;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.Arrays;

public class Deneme {

    int reversenum;

    public static void main(String[] args) {
        int num2 = 29;
        boolean flag = false;
        for (int p = 2; p <= num2 / 2; ++p) {
            // condition for nonprime number
            if (num2 % p == 0) {
                flag = true;
                break;
            }
        }

        if (!flag)
            System.out.println(num2 + " is a prime number.");
        else
            System.out.println(num2 + " is not a prime number.");


        System.out.println("*******************");
        System.out.println("Reverse string without method");

        String str = "mindtek school in Chicago"; //
        String reversedStr = "";
        String back = "";
        String front = "";
        //String [] reversed;

        for (int i = str.length() - 1; i >= 0; i--) {

            reversedStr += str.charAt(i);

            front = reversedStr.substring(0, reversedStr.indexOf(" ") + 1);
            back = reversedStr.substring(reversedStr.indexOf(" ") + 1);


        }
        System.out.println(back + " " + front);
        String arrstr[] = reversedStr.split(" ");
        System.out.println("yeni  split methodan sonra");

        for (int i = arrstr.length - 1; i >= 0; i--) {
            System.out.println(arrstr[i]);


        }

        int intArr[] = {1, 2, 3, 4, 5, 6, 7};

        for (int i = intArr.length - 1; i >= 0; i--) {

            System.out.println(intArr[i]);
        }

        int num3 = 123;

        String num3str = Integer.toString(num3);
        StringBuilder num3SB = new StringBuilder(num3str);
        num3SB.reverse();
        String singleString = num3SB.toString();
        int num3Rev = Integer.parseInt(singleString);


        System.out.println("***** son cevirilen int");
        System.out.println(num3Rev);
        int reversenum =0;

        for( ;num3 != 0; ) {
            reversenum = reversenum * 10;
            reversenum = reversenum + num3%10;
            num3 = num3/10;
        }
        System.out.println("*** loop ile "+reversenum);



/*
\String str = "geekss@for@geekss";
        String[] arrOfStr = str.split("s", -2);

        for (String a : arrOfStr)
            System.out.println(a);
 */


        int[] arr = {11, 3, 55, 32, 9, 6};
        int maxNum = arr[0];

        for (int i = 0; i < arr.length; i++) {

            if (maxNum > arr[i]) {
                maxNum = arr[i];

            }
        }
        System.out.println(maxNum);


        int a = 5;
        int b = 10;

        a = a + b; //15=5+10
        b = a - b;  //5=15-10
        a = a - b; //10=15-5


        System.out.println(a);


        fizzBuzzMethod(10);

    }

    public static void fizzBuzzMethod(int num) {

        for (int i = 1; i < num; i++) {

            if ((i % 5 == 0) && (i % 7 == 0)) {
                System.out.println("FizzBuzz");

            } else if (i % 5 == 0) {
                System.out.println("Fizz");
            } else if (i % 7 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }


        }


    }
}

