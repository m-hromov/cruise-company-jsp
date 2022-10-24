package com.cruisecompany.util.files;


import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileHelper {
    /**
     * Creates a directories with specified uploadPath if they do not exist and then creates a file with unique name
     * there.
     * @param part request Part with file
     * @param uploadPath upload path
     * @param fileType the type of the file
     * @param id ID that is used for file's name
     * @return name of the file
     * @throws IOException if something went wrong
     */
    static public String writeRecord(Part part, String uploadPath, FileType fileType, long id) throws IOException {
        return writeRecord(part, uploadPath, fileType, String.valueOf(id));
    }

    /**
     * Creates a directories with specified uploadPath if they do not exist and then creates a file with unique name
     * there.
     * @param part request Part with file
     * @param uploadPath upload path
     * @param fileType the type of the file
     * @param uniqueStr unique string that is used for file's name
     * @return name of the file
     * @throws IOException if something went wrong
     */
    static public String writeRecord(Part part, String uploadPath, FileType fileType, String uniqueStr) throws IOException {
        File file = new File(uploadPath);
        if (!file.exists()) {
            if (!file.mkdirs()) throw new IOException("Wrong directory");
        }
        String fileExtension = extractExtension(part.getSubmittedFileName());
        String fileName = fileType.str + "_" + uniqueStr + fileExtension;
        part.write(uploadPath + File.separator + fileName);
        return fileName;
    }

    /**
     * Extract the extensions of the file.
     * @param filename name of the file
     * @return file's extensions
     */
    static public String extractExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

}
