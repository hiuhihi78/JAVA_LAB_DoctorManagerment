
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class GetValue {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getNumberIntInRange(int from, int to, String msg) {
        int result = 0;
        // loop until user input correct
        while (true) {
            result = getInputPositiveInt(msg);
            if (result < from || result > to) {
                System.err.println("Invalid of " + msg + " !" + msg + " must be in range [" + from + " - " + to + "]!");
            } else {
                return result;
            }
        }
    }

    public static String getInputString(String msg) {
        String result = null;
        // loop until user input correct
        while (true) {
            System.out.print("Enter " + msg + ": ");
            result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Invalid of " + msg + "! " + msg + " must be not empty!");
            } else {
                return result;
            }
        }
    }

    public static int getInputPositiveInt(String msg) {
        int result = 0;
        // loop until user input correct
        while (true) {
            try {
                String temp = getInputString(msg);
                result = Integer.parseInt(temp);
                if (result <= 0) {
                    System.err.println("Invalid of " + msg + "! " + msg + " must be greater than 0!");
                } else {
                    return result;
                }
            } catch (Exception e) {
                System.err.println("Invalid of " + msg + "! " + msg + " a positive interger!");
            }
        }
    }

    public static String getCode(ArrayList<Doctor> list) {
        String result = null;
        // loop until user input correct
        while (true) {
            result = getInputString("Code");
            boolean doctorExisted = Validation.checkDoctorExistedByCode(list, result);
            if (doctorExisted == false) {
                return result;
            } else {
                System.err.println("Invalid of Code! Code was exited in system!");
            }
        }
    }

    public static Doctor getDoctorByCode(ArrayList<Doctor> list, String code) {
        for (Doctor doctor : list) {
            if (doctor.getCode().equalsIgnoreCase(code)) {
                return doctor;
            }
        }
        return null;
    }

    public static ArrayList<Doctor> getListInforDoctorInFile(String fileName) {
        ArrayList<Doctor> result = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String stringLine;
            String[] array;
            String code, name, specialization;
            int availability;
            while (true) {
                stringLine = br.readLine();
                if (stringLine == null) {
                    break;
                }
                array = stringLine.split("[|]");
                code = array[0].trim();
                name = array[1].trim();
                specialization = array[2].trim();
                availability = Integer.parseInt(array[3].trim());
                result.add(new Doctor(code, name, specialization, availability));
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.err.println("File is not found!");
        }
        return result;
    }

    public static String getCodeUpdate(ArrayList<Doctor> list, String code) {

        while (true) {
            String input = getInputString("code");
            if (input.equalsIgnoreCase(code)) {
                return input;
            }
            // check doctor existed 
            boolean doctorExisted = Validation.checkDoctorExistedByCode(list, code);
            if (doctorExisted == true) {
                System.err.println("Invalid of code, code was existed!");
            } else {
                return input;
            }
        }
    }

}
