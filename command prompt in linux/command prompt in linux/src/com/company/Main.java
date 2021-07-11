package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code her
        boolean flag=true;
        Parser par=new Parser();
        Scanner scan=new Scanner(System.in);
        String command;
        while (flag==true) {


            command = scan.nextLine();
            String str=String.valueOf(command);


            if (str.equals("exist")) {
                flag = false;
                System.exit(0);
            }
            else if(str.equals(""))
            {
                System.out.println(" ");
            }
            else {

                if (!par.parse(command)) {
                    System.out.println("the error command ");

                }

            }

            //System.out.println(par.cmd);
            //System.out.println(Arrays.toString(par.getArguments()));
        }
    }
}
