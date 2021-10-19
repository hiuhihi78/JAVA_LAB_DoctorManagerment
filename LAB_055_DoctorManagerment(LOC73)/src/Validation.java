
import java.io.File;
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
class Validation {

    public static boolean checkDoctorExistedByCode(ArrayList<Doctor> list, String code) {
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getCode().equalsIgnoreCase(code)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkFileExisted(String fileName) {
        try {
            File file = new File(fileName);
            if(file.exists() == true){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    
}
