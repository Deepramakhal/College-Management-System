package util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.http.Part;

public class FileUploadUtil {

    // Method to handle file saving and return the saved file path
    public static String saveFile(Part filePart) throws IOException {
        String uploadDirectory = "C:/uploads/"; 
    	
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String fileName = getFileName(filePart);

        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("File name is empty.");
        }
        String filePath = uploadDirectory + File.separator + fileName;
        File file = new File(filePath);
        
        try (InputStream inputStream = filePart.getInputStream()) {
            java.nio.file.Files.copy(inputStream, file.toPath());
        }

        return filePath;
    }
    private static String getFileName(Part part) {
        String contentDisposition = part.getHeader("Content-Disposition");
        
        String[] items = contentDisposition.split(";");
        
        for (String item : items) {
            item = item.trim();
            if (item.startsWith("filename")) {
                // Extract the filename, remove the quotes around it
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return null;
    }
}
