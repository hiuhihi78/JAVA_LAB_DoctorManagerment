
import java.util.ArrayList;
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
class Manager {

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("========= Doctor Management ==========");
        System.out.println("    1.Add Doctor");
        System.out.println("    2.Update Doctor");
        System.out.println("    3.Delete Doctor");
        System.out.println("    4.Search Doctor");
        System.out.println("    5.Exit");
    }

    public static void addDoctor(String fileName) {
        ArrayList<Doctor> list = new ArrayList<>();
        // check file was existed
        if (Validation.checkFileExisted(fileName) == false) {
            System.out.println("File is not existed");
        }
        // get data form file
        list.addAll(FileProcess.readListDoctor(fileName));
        System.out.println("--------- Add Doctor ----------");
        String code = GetValue.getCode(list);
        String name = GetValue.getInputString("Name");
        String specialization = GetValue.getInputString("Specialization");
        int availability = GetValue.getInputPositiveInt("Availability");
        list.add(new Doctor(code, name, specialization, availability));
        // write data to file
        FileProcess.writeListDoctor(list, fileName);
        System.out.println("Successfully!\n");
    }

    public static void updateDoctorByCode(String fileName) {
        ArrayList<Doctor> list = new ArrayList<>();
        // check file not existed
        if (Validation.checkFileExisted(fileName) == false) {
            System.out.println("File is not existed");
            return;
        }
        // get data form file
        list.addAll(FileProcess.readListDoctor(fileName));
        System.out.println("--------- Update Doctor -------");
        // check system is empty
        if (list.isEmpty()) {
            System.out.println("System is empty!\n");
            return;
        }
        // input code
        String code = GetValue.getInputString("Code");
        // check docter existed
        boolean doctorExisted = Validation.checkDoctorExistedByCode(list, code);
        // update
        if (doctorExisted == false) {
            System.out.println("Docter with this Code not existed in system!\n");
            return;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCode().equalsIgnoreCase(code)) {
                    String codeUpdate = GetValue.getCodeUpdate(list, code);
                    String name = GetValue.getInputString("Name");
                    String specialization = GetValue.getInputString("Specialization");
                    int availability = GetValue.getInputPositiveInt("Availability");
                    list.set(i, new Doctor(codeUpdate, name, specialization, availability));
                    System.out.println("Successfully!\n");
                    return;
                }
            }
        }
    }

    public static void deleteDoctorByCode(String fileName) {
        ArrayList<Doctor> list = new ArrayList<>();
        // check file not existed
        if (Validation.checkFileExisted(fileName) == false) {
            System.out.println("File is not existed");
            return;
        }
        // get data form file
        list.addAll(FileProcess.readListDoctor(fileName));
        System.out.println("--------- Delete Doctor -------");
        // check system is empty
        if (list.isEmpty()) {
            System.out.println("System is empty!\n");
            return;
        }
        // input code
        String code = GetValue.getInputString("Code");
        // check doctor existed
        boolean doctorExisted = Validation.checkDoctorExistedByCode(list, code);
        // delete doctor
        if (doctorExisted == false) {
            System.out.println("Docter with this Code not existed in system!\n");
        } else {
            Doctor doctorDelete = GetValue.getDoctorByCode(list, code);
            list.remove(doctorDelete);
            System.out.println("Successfully!\n");
        }
    }

    public static void searchDoctorByText(String fileName) {
        ArrayList<Doctor> list = new ArrayList<>();
        ArrayList<Doctor> listSearch = new ArrayList<>();
        // check file not existed
        if (Validation.checkFileExisted(fileName) == false) {
            System.out.println("File is not existed");
            return;
        }
        // get data form file
        list.addAll(FileProcess.readListDoctor(fileName));
        System.out.println("---------- Search Doctor --------");
        // check system is empty
        if (list.isEmpty()) {
            System.out.println("System is empty!\n");
            return;
        }
        // input info doctor
        System.out.print("Enter text: ");
        String text = scanner.nextLine().trim();
        // search
        for (Doctor doctor : list) {
            String code = doctor.getCode();
            String name = doctor.getName();
            String specialization = doctor.getSpecialization();
            String availability = String.format("%s", doctor.getAvailability());
            if (code.contains(text) || name.contains(text)
                    || specialization.contains(text) || availability.contains(text)) {
                listSearch.add(doctor);
            }
        }
        if (listSearch.isEmpty()) {
            System.out.println("Doctor is not found!\n");
            return;
        } else {
            System.out.println("--------- Result ------------");
            String format = String.format("%-7s%-12s%-18s%s", "Code", "Name",
                    "Specialization", "Availability");
            System.out.println(format);
            for (Doctor doctor : listSearch) {
                System.out.println(doctor);
            }
            System.out.println("");
        }
    }

}
