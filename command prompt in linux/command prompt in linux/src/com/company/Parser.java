package com.company;

import java.io.IOException;

public class Parser {
    String[] args=new String[20]; // Will be filled by arguments extracted by parse method
    String cmd; // Will be filled by the command extracted by parse method
    Teriminal term=new Teriminal();
    public  int spacecount(String s)
    {
        int c,i;
        for( i=0,c=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(ch==' ')
                c++;
        }
        return c;
    }
    public  int slashcount(String s)
    {
        int c,i;
        for( i=0,c=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(ch=='/'|| ch=='\''||ch==':')
                c++;
        }
        return c;
    }
    public boolean opout1(String s)
    {
        s=s.trim();
        return s.substring(4,5).contains(">")&& !s.substring(5,6).contains(">");
    }
    public boolean opout2(String s)
    {
        return s.substring(4,6).contains(">>");
    }
    public boolean pipop(String s){
        return s.contains("|");
    }

    public boolean parse(String input) throws IOException {
        boolean flag=true;

        if(spacecount(input)==1&&input.substring(0,2).equals("cd")) {
            cmd="cd";
            args[0]=input.substring(3).trim();
            term.cd(input);
            flag=true;
            //pwd command

        }
        else if(spacecount(input)==0&&input.substring(0,2).equals("ls") ) {
            cmd="ls";
            Teriminal.LS();
            flag=true;
        }

        else if(input.substring(0,3).equals("pwd")&& spacecount(input)==0) {
            cmd="pwd";
            Teriminal.Pwd();
            flag=true;
        }
        else if(input.substring(0,4).equals("date")&& spacecount(input)==0) {
            cmd="date";
            term.date();
            flag=true;
        }
        else if(input.substring(0,4).equals("more")&& spacecount(input)==1) {
            cmd="more";
            args[0]=input.substring(5,input.length());
            term.more(input.substring(5,input.length()));
            flag=true;
        }

        else if(input.substring(0,4).equals("help")&&spacecount(input)==0)
        {
            cmd="help";
            term.help();
        }
        else if(input.substring(0,4).equals("args")&&spacecount(input)==0){
            cmd="args";
            term.args();
        }
        else  if(input.substring(0,5).equals("mkdir")&&input.length()>6&&spacecount(input)==1) {
            cmd="mkdir";
            args[0]=input.substring(3).trim();
            if(slashcount(input)>=1&&spacecount(input)==1)
                term.mkdir_path(input);
            else if(slashcount(input)==0&&spacecount(input)==1)
                term.mkdir_foldername(input);
            flag=true;
        }
        else if(input.substring(0,5).equals("clear")&& spacecount(input)==0) {
            cmd="clear";
            Teriminal.clear();
            flag=true;

        }
        else if(input.substring(0,5).equals("rmdir")&& spacecount(input)==1){
            cmd="rmdir";
            args[0]=input.substring(6,input.length()).trim();
            Teriminal.Rmdir(input.substring(6,input.length()).trim());
            flag=true;
        }
        else if(input.substring(0,2).equals("rm")&& spacecount(input)==1) {
            cmd="rm";
            args[0]=input.substring(3).trim();
            term.rm(input.substring(3).trim());
            flag=true;
        }
        else if(input.substring(0,2).equals("cp")&&spacecount(input)==2)
        {
            cmd="cp";
            String arg1= "";
            String arg2= "";

            String []words=input.split("\\s+");
            arg1=words[1];
            arg2=words[2];
            if(slashcount(arg2)>=1)
            {
                Teriminal.cpfd(arg1,arg2);
            }
            else
            {
                Teriminal.cpff(arg1,arg2);
            }



        }
        else if(input.substring(0,2).equals("mv")&&spacecount(input)==2)
        {
            cmd="mv";
            String arg1= "";
            String arg2= "";
            String []words=input.split("\\s+");
            arg1=words[1];
            arg2=words[2];
            if(slashcount(arg1)>=1)
            {
                term.mv_cp(arg1,arg2);
                flag=true;
            }
            else
            {
                term.mv_rename(arg1,arg2);
                flag=true;
            }

        }
        else if(input.substring(0,3).equals("cat")&&spacecount(input)==1)
        {
            cmd="cat";
            args[0]=input.substring(4,input.length()).trim();
            term.cat(input.substring(4,input.length()).trim());
            flag=true;
        }


        else if(spacecount(input)>2&&pipop(input))
        {
            String []words=input.split("\\s+");
            if(words[0].equals("cd")&&words[3].equals("pwd"))
            {
                term.cd(words[0]+words[1]);
                Teriminal.Pwd();
            }
            else if(words[0].equals("cd")&&words[3].equals("ls"))
            {
                System.out.println(words[0]+" "+words[1]+" "+words[2]+" "+words[3]);
                term.cd(words[0]+words[1]);
                Teriminal.LS();
            }
            else if(words[0].equals("ls")&&words[2].equals("more"))
            {
                Teriminal.LS();
                term.more(words[3]);
            }
            else if((words[0].equals("cat")&&words[3].equals("more")))
            {
                term.cat(words[1]);
                term.more(words[1]);
            }
            flag=true;


        }
        else if(spacecount(input)==2&&opout1(input))
        {
            String arg1,arg2 = null;
            for (int i=4;i<input.length();i++){
                if(input.charAt(i)==' '){
                    arg1=input.substring(4,i-1);
                    arg2=input.substring(i+1,input.length()-1);
                }
            }
            term.operatoroutput1(arg2);
            flag=true;
        }
        else if(spacecount(input)==2&&opout2(input))
        {
            String arg1,arg2 = null;
            for (int i=4;i<input.length();i++){
                if(input.charAt(i)==' '){
                    arg1=input.substring(4,i-1);
                    arg2=input.substring(i+1,input.length()-1);
                }
            }
            term.operatoroutput2(arg2);
            flag=true;
        }


        else
        {
            flag=false;
        }

        return flag;
    }
    public String getCmd () {
        return cmd;
    }
    public String[] getArguments () {
        return args;
    }



}
