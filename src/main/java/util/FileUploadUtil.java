package util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import jakarta.servlet.http.Part;

public class FileUploadUtil {
    public static String saveFile(Part filePart) throws IOException {
        String uploadDirectory = "D:\\Java Workspace\\College Management System\\src\\main\\webapp\\upload\\"; 
    	
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
            Files.copy(inputStream, file.toPath());
        }

        return "upload/" + fileName;
    }
    private static String getFileName(Part part) {
        String contentDisposition = part.getHeader("Content-Disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            item = item.trim();
            if (item.startsWith("filename")) {
                String originalFileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
                int lastDotIndex = originalFileName.lastIndexOf(".");
                String baseName = (lastDotIndex == -1) ? originalFileName : originalFileName.substring(0, lastDotIndex);
                String extension = (lastDotIndex == -1) ? "" : originalFileName.substring(lastDotIndex);

                String uniqueFileName = baseName + RandomStringGenerator.generateRandomString(5) + extension;
                return uniqueFileName;
            }
        }
        return null;
    }
}
