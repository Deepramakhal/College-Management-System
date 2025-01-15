package util;
import java.io.File;
public class DeleteFileUtil {
   public static boolean deleteFile(String relativeFilePath) {
        String uploadDirectory = "D:\\Java Workspace\\College Management System (1)\\src\\main\\webapp\\";
        String fullFilePath = uploadDirectory + relativeFilePath;
        File file = new File(fullFilePath);
        if (file.exists()) {
            return file.delete(); 
        } else {
            System.out.println("File not found: " + fullFilePath);
            return false; 
        }
    }
}
