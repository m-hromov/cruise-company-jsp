package com.cruisecompany.util.files;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileHelperTest {
    @AfterAll
    static void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(File.separator + "TEST123" + File.separator + "documents"));
        Files.deleteIfExists(Path.of(File.separator + "TEST123"));
    }

    @Test
    void testWriteRecordWithId() throws IOException {
        String uploadPath = File.separator + "TEST123" + File.separator + "documents";
        Part part = mock(Part.class);
        when(part.getSubmittedFileName()).thenReturn("file13123.jpg");
        doNothing().when(part).write(ArgumentMatchers.anyString());
        String fullPath = FileHelper.writeRecord(part,uploadPath, FileType.PASSENGER_DOCUMENT,123);
        assertTrue(new File(uploadPath).exists());
        verify(part,times(1)).write(ArgumentMatchers.any());
        assertEquals(FileType.PASSENGER_DOCUMENT.str+"_123.jpg",
                fullPath);
    }

    @Test
    void testWriteRecord() throws IOException {
        String uploadPath = File.separator + "TEST123" + File.separator + "documents";
        Part part = mock(Part.class);
        when(part.getSubmittedFileName()).thenReturn("file13123.jpg");
        doNothing().when(part).write(ArgumentMatchers.anyString());
        String fullPath = FileHelper.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        verify(part,times(1)).write(ArgumentMatchers.any());
        assertEquals(FileType.PASSENGER_DOCUMENT.str+"_TEST_UNIQUE.jpg",
                fullPath);
    }
    @Test
    void testWriteRecordInvalidDirectory() throws IOException {
        String uploadPath = "?$#@";
        Part part = mock(Part.class);
        assertThrows(IOException.class,()->
                FileHelper.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE"));
    }
    @Test
    void testWriteRecordOverridesFile() throws IOException {
        String uploadPath = File.separator + "TEST123" + File.separator + "documents";
        Part part = mock(Part.class);
        when(part.getSubmittedFileName()).thenReturn("file13123.jpg");
        doNothing().when(part).write(ArgumentMatchers.anyString());
        FileHelper.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        FileHelper.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        String fullPath = FileHelper.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        verify(part,times(3)).write(ArgumentMatchers.any());
        assertEquals(FileType.PASSENGER_DOCUMENT.str+"_TEST_UNIQUE.jpg",
                        fullPath);
    }

    @Test
    void testExtractExtension() {
        String extension = FileHelper.extractExtension("file.jpg");
        assertEquals(".jpg", extension);
        extension = FileHelper.extractExtension("file.min.jpg");
        assertEquals(".jpg", extension);
    }
}