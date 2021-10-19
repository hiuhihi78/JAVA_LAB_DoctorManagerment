
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class FileProcess {

    public static ArrayList<Doctor> readListDoctor(String fileName) {
        FileInputStream fileInput = null;
        boolean fileExisted = Validation.checkFileExisted(fileName);
        if (fileExisted == false) {
            // create new file
            writeListDoctor(new ArrayList<Doctor>(), fileName);
        }
        try {
            fileInput = new FileInputStream(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            List<Doctor> listDoctor = (List<Doctor>) inputStream.readObject();
            return (ArrayList<Doctor>) listDoctor;
        } catch (Exception ex) {
        } finally {
            try {
                fileInput.close();
            } catch (Exception e) {
            }
        }
        return new ArrayList<>();
    }

    public static void writeListDoctor(ArrayList<Doctor> list, String fileName) {
        FileOutputStream fileOutput = null;
        try {
            fileOutput = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutput.close();
            } catch (IOException ex) {
            }
        }

    }

}
