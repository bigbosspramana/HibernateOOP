package com.example;

import java.util.Scanner;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Configuration configuration = new Configuration().configure();
    static SessionFactory sessionFactory = configuration.buildSessionFactory();
    static Session session = sessionFactory.openSession();
    static Transaction tx = null;

    static String[] navList = new String[]{
        "Insert Data",
        "Update Data",
        "Delete Data",
        "Read/Select Data",
        "Exit"
    };

    public static void navBar() {
        String pilih = enumerator("Please Choose an Action:", navList);
        List<Students> result = session.createQuery("select s from Students s", Students.class).list();
        int i = 1;
        if(pilih != "Exit" && pilih != "Insert Data"){
            tx = session.beginTransaction();
            System.out.println("== Students List ==");
            for (Students students : result) {
                System.out.println("Student " + i + ": " + students.toString());
                i++;
            }
            session.getTransaction().commit();
        }
        switch(pilih){
            case "Exit":
                System.out.println("Bye!");
                System.exit(0);
            case "Insert Data":
                tx = session.beginTransaction();
                Students newStudent = new Students();
                newStudent.setName(toTitle(inputText("Enter Student Name: ", false)));
                newStudent.setAge(inputInt("Enter Student Age: ", 1));
                newStudent.setMajor(prodiSelector());
                session.save(newStudent);
                tx.commit();
                break;
            case "Update Data":
                Long updateId = Long.parseLong(String.valueOf(inputInt("Enter Update ID: ")));
                Students studentToUpdate = session.get(Students.class, updateId);
                if (studentToUpdate != null) {
                    tx = session.beginTransaction();
                    System.out.println("Data that will be Updated: " + studentToUpdate);
                    studentToUpdate.setName(toTitle(inputText("Enter New Student Name: ", false)));
                    studentToUpdate.setAge(inputInt("Enter New  Student Age: ", 1));
                    studentToUpdate.setMajor(prodiSelector());
                    session.update(studentToUpdate);
                    tx.commit();
                } else {
                    System.out.println("Data with that ID not found");
                }
                break;
            case "Delete Data":
                Long deleteId = Long.parseLong(String.valueOf(inputInt("Enter Delete ID: ")));
                Students studentToDelete = session.get(Students.class, deleteId);
                if (studentToDelete != null) {
                    tx = session.beginTransaction();
                    System.out.println("Data that is deleted: " + studentToDelete);
                    session.delete(studentToDelete);
                    tx.commit();
                } else {
                    System.out.println("Data with that ID not found");
                }
                break;
            case "Read/Select Data":
                break;
        }
        navBar();
    }

    public static String prodiSelector(){
        String[] prodi = new String[] {
            "Management",
            "Informatics",
            "Visual Communication Design"
        };

        String pilih = enumerator("Major:", prodi);
        return pilih;
    }

    public static String toTitle(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
    
        StringBuilder converted = new StringBuilder();
    
        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }
    
        return converted.toString();
    }

    public static String enumerator(String enumTitle, String[] choices){
        System.out.println(enumTitle);
        int count = 1;
        for(String choice:choices){
            System.out.println(count + ". " + choice);
            count++;
        }
        System.out.print("Masukkan Pilihan: ");
        int pilihan;
        try{
            pilihan = Integer.parseInt(input.nextLine());
            if(pilihan <= choices.length && pilihan > 0){
                return choices[pilihan-1];
            } else{
                System.out.println("\nWrong Input!");
                return enumerator(enumTitle, choices);
            }
        } catch(Exception e){
            System.out.println("\nWrong Input!");
            return enumerator(enumTitle, choices);
        }
        // Copyright By Gibek
    }

    public static String inputText(String prompt, boolean allowNum){
        System.out.print(prompt);
        try{
            String value = input.nextLine();
            if(!allowNum){
                if(value.matches("") || !value.matches("^[a-zA-Z\\s]*$") || value.matches("^[\\s]*$")){
                    System.out.println("\nWrong Input!");
                    return inputText(prompt, allowNum);
                }else {
                    return value;
                }
            } else{
                if(value.matches("") || !value.matches("^[a-zA-Z0-9\\s]*$") || value.matches("^[\\s]*$")){
                    System.out.println("\nWrong Input!");
                    return inputText(prompt, allowNum);
                }else {
                    return value;
                }
            }
        } catch(Exception e){
            System.out.println("\nWrong Input!");
            return inputText(prompt, allowNum);
        }
    }

    public static int inputInt(String prompt, int min){
        System.out.print(prompt);
        try{
            int value = Integer.parseInt(input.nextLine());
            if(value < min){
                System.out.println("\nYour Input is Less Than " + min + "!");
                return inputInt(prompt, min);
            } else{
                return value;
            }
        } catch(Exception e){
            System.out.println("\nWrong Input!");
            return inputInt(prompt, min);
        }
    }

    public static int inputIntMax(String prompt, int max){
        System.out.print(prompt);
        try{
            int value = Integer.parseInt(input.nextLine());
            if(value > max){
                System.out.println("\nYour Input Exeeds The Limit of " + max + "!");
                return inputIntMax(prompt, max);
            } else{
                return value;
            }
        } catch(Exception e){
            System.out.println("\nWrong Input!");
            return inputIntMax(prompt, max);
        }
    }

    public static int inputIntMin(String prompt){
        System.out.print(prompt);
        try{
            int value = Integer.parseInt(input.nextLine());
            if(value <= 0){
                System.out.println("\nWrong Input!");
                return inputIntMin(prompt);
            } else{
                return value;
            }
        } catch(Exception e){
            System.out.println("\nWrong Input!");
            return inputIntMin(prompt);
        }
    }

    public static int inputInt(String prompt, int min, int max){
        System.out.print(prompt);
        try{
            int value = Integer.parseInt(input.nextLine());
            if(value < min || value > max){
                System.out.println("\nYour Input Value Doesn't Match The Range!");
                return inputInt(prompt, min, max);
            } else{
                return value;
            }
        } catch(Exception e){
            System.out.println("\nWrong Input!");
            return inputInt(prompt, min, max);
        }
    }

    public static int inputInt(String prompt){
        System.out.print(prompt);
        try{
            int value = Integer.parseInt(input.nextLine());
            return value;
        } catch(Exception e){
            System.out.println("\nWrong Input!");
            return inputInt(prompt);
        }
    }
    public static void main(String[] args) {
        navBar();
    }
}