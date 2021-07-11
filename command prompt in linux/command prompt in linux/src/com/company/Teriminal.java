package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

public class Teriminal {
        public  static Path path= Paths.get("E:\\level_3_1\\Operating_System\\assignments\\assignment_1");
        //change directory command
        public void  cd(String str) {
            path=Paths.get(str.substring(2,str.length()).trim());

            System.out.println(path);


        }

        //make directory command for path comman
        public  void mkdir_path(String str) throws IOException {

            File file=new File(String.valueOf(str.substring(6,str.length()).trim()));
            path= Path.of(str.substring(6,str.length()).trim());


            if (Files.notExists(path)) {
                Files.createDirectory(path);
                file.mkdir();
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }

        }

        //make directory for folder name command
        public  void mkdir_foldername(String str) throws IOException {

            String var= str.substring(6,str.length()).trim();
            File file=new File(String.valueOf(path+"/"+var));
            path= Path.of(path + "/" + var);
            System.out.println(path);

            if (file.mkdir()) {
                System.out.println( "File created: " + file.getName());
            } else {
                System.out.println( "File already exists .");
            }

        }

        //clear command
        public static void clear() throws IOException{
            for(int i=0 ;i<150;i++)
            {
                System.out.println() ;

            }
        }

        //remove directory
        public static  void Rmdir ( String File_Name) {
            File file = new File(String.valueOf(path));
            path = Path.of(file.getAbsolutePath() );
            System.out.println(path);
            if(file.delete())
            {
                System.out.println("File deleted successfully");
            }
            else
            {
                System.out.println("Failed to delete the file");
            }
        }

        //print working directory command
        public static void Pwd() {

            System.out.println(path);
        }

        // ls command
        public  static void LS () {
            File dir = new File(String.valueOf(path));
            String childs[] = dir.list();
            for(String child: childs)
            {
                System.out.println(child);
            }
        }

        //remove file command
        public void rm(String path) {
            File file = new File(path);
            if(file.delete())
            {
                System.out.println("File deleted ");
            }
            else
            {
                System.out.println("File deletion failure");
            }
        }

        //print date command
        public void date()
        {
            System.out.println( new Date(System.currentTimeMillis()) );
        }

        // cat command (Print the content of file)
        public void cat(String File_Name)
        {
            File file = new File(path+"/"+File_Name);
            try
            {
                FileReader fr= new FileReader(file);
                BufferedReader B_R = new BufferedReader(fr);
                String statment;
                while ((statment = B_R.readLine()) != null)
                {
                    System.out.println(statment);
                }
                fr.close();
            }
            catch(Exception e)
            {
                System.out.println("there is an error during reading the file" + e.getMessage());
            }
        }

        //more command (Print the content of file with scroll)
        public void more(String File_Name)
        {
            Scanner scan =new Scanner(System.in);
            File file = new File(path+"/"+File_Name);
            try
            {
                FileReader fr= new FileReader(file);
                BufferedReader B_R = new BufferedReader(fr);
                String statment;
                int cont=0;

                while ((statment = B_R.readLine()) != null)
                {

                    if(cont <=10)
                    {
                        System.out.println(statment);

                    }

                    else
                    {
                        String enterkey ;
                        enterkey=scan.nextLine();
                        if(enterkey.equals(""))
                            System.out.println(statment);
                    }
                    cont++;
                }
                fr.close();
            }
            catch(Exception e)
            {
                System.out.println("there is an error during reading the file" + e.getMessage());
            }
        }

        // cp command (Copy from file 1 to file 2 )
        public static void cpff(String file1,String file2)
        {
            try{
                File myfile_2=new File(path+"/"+file1);
                if(myfile_2.createNewFile())
                {
                    System.out.print("is created  !! "  ) ;
                }
                else
                {
                    System.out.print("is exsists  !!  "+myfile_2.getName() + myfile_2.getAbsolutePath()) ;
                }

            }
            catch(Exception e)
            {
                System.out.print(e.getMessage());


            }
            try{
                File myfile_1=new File(path+"/"+file1);
                FileWriter mywriter=new FileWriter(path+"/"+file2);
                Scanner myreader = new Scanner (myfile_1);
                while(myreader.hasNextLine())
                {
                    String data = myreader.nextLine();
                    //System.out.println(data);
                    mywriter.write(data);

                }
                myreader.close();
                mywriter.close();
                System.out.print("done");
            }
            catch(Exception e)
            {
                System.out.print("error occurred");
            }
        }

        // cp command (Copy file to another directory)
        public static void cpfd(String fileName,String directoryName)
        {
            String location=directoryName+"/"+fileName;
            try{
                File myfile_2=new File(location);
                if(myfile_2.createNewFile())
                {
                    System.out.print("is created  !! "  ) ;
                }
                else
                {
                    System.out.print("is exsists  !!  "+myfile_2.getName() + myfile_2.getAbsolutePath()) ;
                }

            }
            catch(Exception e)
            {
                System.out.print(e.getMessage());

            }
            try{
                File myfile_1=new File(path+"/"+fileName);
                FileWriter mywriter=new FileWriter(location);
                Scanner myreader = new Scanner (myfile_1);
                while(myreader.hasNextLine())
                {
                    String data = myreader.nextLine();
                    //System.out.println(data);
                    mywriter.write(data);

                }
                myreader.close();
                mywriter.close();
                System.out.print("done");
            }
            catch(Exception e)
            {
                System.out.print("error occurred");
            }
        }

        // Cat >> command (to create new file and write in this file)
        public void operatoroutput2(String arg2) throws IOException {


            //File myfile_1=new File("file_1.txt");
            FileWriter myfileGehad = new FileWriter(path+arg2+"t");
            Scanner input = new Scanner(System.in);
            String str = null;
            str = input.nextLine();
            while (!str.equals("exist")) {
                myfileGehad.write(str);
                str = input.nextLine();
            }
            myfileGehad.close();
        }

        // Cat > command (to append in file)
        public void operatoroutput1(String arg2) throws IOException {
            //File myfile_1=new File("file_1.txt");
            FileWriter myfileGehad = new FileWriter(path+arg2+"t",true);
            Scanner input = new Scanner(System.in);
            String str = null;
            str = input.nextLine();
            while (!str.equals("exist")) {
                myfileGehad.write(str);
                myfileGehad.append(str);
                str = input.nextLine();
            }
            myfileGehad.close();
        }

        // mv command (to cut file to another directory)
        public void  mv_cp(String directoryName, String fileName) {
            Path pe= Path.of(path + "/" + fileName);
            String location=directoryName+"/"+fileName;
            System.out.println(path);

            path= Path.of(directoryName + "/" + fileName);
            try{
                File myfile_2=new File(location);
                if(myfile_2.createNewFile())
                {
                    System.out.print("is created  !! "  ) ;
                }
                else
                {
                    System.out.print("is exsists  !!  "+myfile_2.getName() + myfile_2.getAbsolutePath()) ;
                }
            }
            catch(Exception e)
            {
                System.out.print(e.getMessage());

            }
            try{
                File myfile_1=new File(String.valueOf(pe));
                FileWriter mywriter=new FileWriter(location);
                Scanner myreader = new Scanner (myfile_1);
                while(myreader.hasNextLine())
                {
                    String data = myreader.nextLine();
                    //System.out.println(data);
                    mywriter.write(data);

                }
                myreader.close();
                mywriter.close();
                System.out.print("done");
            }
            catch(Exception e)
            {
                System.out.print("error occurred");
            }

            File file = new File(String.valueOf(pe));

            if(file.delete())
            {
                System.out.println("File deleted successfully");
            }
            else
            {
                System.out.println("Failed to delete the file");
            }

            path= Path.of(directoryName);
        }

        // mv command (to rename fileneme_1 by using fileneme_2)
        public void  mv_rename(String file1, String file2)
        {
            File newfile = new File (path+"/"+file1);
            File refile = new File (path+"/"+file2);
            if(newfile.renameTo(refile))
            {
                System.out.print(newfile.getName()+"renamed");
            }
            else
            {
                System.out.print("failed");
            }
        }
        // help command (print all commands )
        public void help() {
            System.out.print("1)  ls---------------------------> (to print path of directory)\n"
                    +        "2)  pwd--------------------------> (to print path of directory)\n"
                    +        "3)  cat > filename --------------> (to append in file)\n"
                    +        "4)  rmdir filename---------------> (delete file from directory)\n"
                    +        "5)  cd path----------------------> (to change directory\n)"
                    +        "6)  mkdir path ------------------> (to make file in specific path) \n"
                    +        "7)  mkdir folder-----------------> (to make file in the current path)\n"
                    +        "9)  clear -----------------------> (to clean screen)\n"
                    +        "10) command1 | command2----------> (to use 2 command in the same time)\n"
                    +        "11) cp file_1 file_2 ------------> (to create new file(1) and copy file_2 to file_1)\n"
                    +        "12) cp directory file -----------> (to copy file to another directory)\n"
                    +        "13) mv directory file -----------> (to cut file to another directory)\n"
                    +        "14) mv fileneme_1 fileneme_2 ----> (to rename fileneme_1 by using fileneme_2)\n"
                    +        "15) cat >> filename -------------> (to create new file and write in this file)\n"
                    +        "16) date ------------------------> (to return date)\n"
                    +        "17) rm path  --------------------> (to delete file)\n"
                    +        "18) more filename  --------------> (to print the content in file)\n"
                    +        "19) cat path---------------------> (to print the content in the file\n"
                    +        "20) help  -----------------------> (to print all commands)\n"
            );
        }

        // args command (print all commands' argument)
        public void args() {
            System.out.print("1)  ls    (no args)\n"
                    + "2)  pwd   (no args) \n"
                    + "3)  cat < (filename) \n"
                    + "4)  rmdir (filename)\n)"
                    + "5)  cd    (path)\n)"
                    + "6)  mkdir (path)\n"
                    + "7)  clear (no args)\n"
                    + "8)  |     (command1 | command2)\n"
                    + "9)  cp    (file_1 file_2) \n"
                    + "10) cp    (directory file) \n"
                    + "11) mv    (directory file)\n"
                    + "12) mv    (fileneme_1 fileneme_2) \n"
                    + "13) cat <<(filename) \n"
                    + "14) date  (no args)\n"
                    + "15) rm    (path) \n"
                    + "16) more  (filename) \n"
                    + "17) help  (no args)\n"
                    + "18) mkdir  (foldername)\n"
            );
        }
    }



