/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Student
 */

@ManagedBean
@SessionScoped
public class Backend {
    
    
   int roll;
   int reg;
   String name;
   String dob;
    int bangla;
    int english;
    int math;
    int gs;
    int ss;
    int religion;
    double gpa;
    String grade;
    
    ArrayList<Backend>list;

    public ArrayList<Backend> getList() {
        return list;
    }

    public void setList(ArrayList<Backend> list) {
        this.list = list;
    }
    
    
    
       public Backend(int roll, int reg, String name, String dob, int bangla, int english, int math, int gs, int ss, int religion, double gpa, String grade) {
        this.roll = roll;
        this.reg = reg;
        this.name = name;
        this.dob = dob;
        this.bangla = bangla;
        this.english = english;
        this.math = math;
        this.gs = gs;
        this.ss = ss;
        this.religion = religion;
        this.gpa = gpa;
        this.grade = grade;
    }

    public Backend() {
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getBangla() {
        return bangla;
    }

    public void setBangla(int bangla) {
        this.bangla = bangla;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getGs() {
        return gs;
    }

    public void setGs(int gs) {
        this.gs = gs;
    }

    public int getSs() {
        return ss;
    }

    public void setSs(int ss) {
        this.ss = ss;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
   
    public double totalAvg(){
        int avg = (bangla+english+math+ss+ss+religion)/6;
        return avg;
    }
    public double totalGpa(){
        
        if(totalAvg()>=80 && totalAvg() <=100){
            gpa = 5.00;
        }else if(totalAvg()>=70 && totalAvg()<=79){
            gpa =4.00;
        }else if(totalAvg()>=60 && totalAvg()<=69){
            gpa =3.50;
        }else if(totalAvg()>=50 && totalAvg()<59){
            gpa =3.00;
        }else{
            gpa =0.00;
        }
        return gpa;
    }
    public String totalGrade(){
        
        if(gpa == 5.0){
            grade = "A+";
        }else if(gpa == 4.0){
            grade = "A";
        }else if(gpa == 3.5){
            grade = "A-";
        }else if(gpa == 3.0){
            grade = "B";
        }else{
            grade = "F";
        }
        return grade;
    }
    
    
    public void saveDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","sysadmin");
            PreparedStatement pst = con.prepareStatement("insert into ssc_result values (?,?,?,?,?,?,?,?,?,?,?,?)");
           pst.setInt(1, roll);
           pst.setInt(2, reg);
           pst.setString(3, name);
           pst.setString(4, dob);
           pst.setInt(5, bangla);
           pst.setInt(6, english);
           pst.setInt(7, math);
           pst.setInt(8, gs);
           pst.setInt(9, ss);
           pst.setInt(10,religion );
           pst.setDouble(11,gpa );
           pst.setString(12, grade);
           pst.executeUpdate();
        }catch(ClassNotFoundException | SQLException a){
           System.out.println(a);
        }
        
    } 
     
    public String show(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","sysadmin");
            PreparedStatement pst = con.prepareStatement("select * from ssc_result where roll = ? and reg = ?");
            pst.setInt(1, roll);
            pst.setInt(2, reg);
            Backend b;
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
               b = new Backend();
                b.setRoll(rs.getInt(1));
                b.setReg(rs.getInt(2));
                b.setName(rs.getString(3));
                b.setDob(rs.getString(4));
                b.setBangla(rs.getInt(5));
                b.setEnglish(rs.getInt(6));
                b.setMath(rs.getInt(7));
                b.setGs(rs.getInt(8));
                b.setSs(rs.getInt(9));
                b.setReligion(rs.getInt(10));
                b.setGpa(rs.getDouble(11));
                b.setGrade(rs.getString(12));
                
                list.add(b);
                
            }
          }catch(ClassNotFoundException | SQLException a){
            System.out.println(a);
        }
        return "ShowResult.xhtml";
    }
      
     public String showAllResult(){
        
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db","root","sysadmin");
            PreparedStatement pst = con.prepareStatement("select * from ssc_result");
            
            Backend b;
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                b = new Backend();
                
                b.setRoll(rs.getInt(1));
                b.setReg(rs.getInt(2));
                b.setName(rs.getString(3));
               
                b.setDob(rs.getString(4));
                b.setBangla(rs.getInt(5));
                b.setEnglish(rs.getInt(6));
                b.setMath(rs.getInt(7));
                b.setGs(rs.getInt(8));
                b.setSs(rs.getInt(9));
                b.setReligion(rs.getInt(10));
                b.setGpa(rs.getDouble(11));
                b.setGrade(rs.getString(12));
                
                list.add(b);
            }
         
        }catch(ClassNotFoundException | SQLException a){
            System.out.println(a);
        }
        return "ShowAllResult.xhtml";   
    }
     
     public String showResult(){
        totalGpa();
        totalGrade();
        saveDb();
        return "index.xhtml";
    }
    
}
