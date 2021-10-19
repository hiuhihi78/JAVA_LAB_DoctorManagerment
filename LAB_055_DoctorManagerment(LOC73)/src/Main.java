
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "doctor.dat";
        // loop until user want to exit
        while (true) {
            // display menu
            Manager.displayMenu();
            // choice option
            int choice = GetValue.getNumberIntInRange(1, 5, "your choice");
            switch(choice){
                case 1:
                    // add doctor
                    Manager.addDoctor(fileName);
                    break;
                case 2:
                    // update doctor by id
                    Manager.updateDoctorByCode(fileName);
                    break;
                case 3:
                    // delete doctor id
                    Manager.deleteDoctorByCode(fileName);
                    break;
                case 4:
                    // search doctor by text
                    Manager.searchDoctorByText(fileName);
                    break;
                case 5:
                    // exit
                    System.exit(0);
            }
        }
    }

}
