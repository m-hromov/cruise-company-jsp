package com.cruisecompany.util.files;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.*;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileHelperImplTest {
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
        String fullPath = FileHelperImpl.writeRecord(part,uploadPath, FileType.PASSENGER_DOCUMENT,123);
        assertTrue(new File(uploadPath).exists());
        verify(part,times(1)).write(ArgumentMatchers.any());
        assertEquals("\\TEST123\\documents" +
                        File.separator + FileType.PASSENGER_DOCUMENT.str+"_123.jpg",
                fullPath);
    }

    @Test
    void testWriteRecord() throws IOException {
        String uploadPath = File.separator + "TEST123" + File.separator + "documents";
        Part part = mock(Part.class);
        when(part.getSubmittedFileName()).thenReturn("file13123.jpg");
        doNothing().when(part).write(ArgumentMatchers.anyString());
        String fullPath = FileHelperImpl.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        verify(part,times(1)).write(ArgumentMatchers.any());
        assertEquals("\\TEST123\\documents" +
                        File.separator + FileType.PASSENGER_DOCUMENT.str+"_TEST_UNIQUE.jpg",
                fullPath);
    }
    @Test
    void testWriteRecordInvalidDirectory() throws IOException {
        String uploadPath = "?$#@";
        Part part = mock(Part.class);
        String fullPath = FileHelperImpl.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        assertNull(fullPath);
    }
    @Test
    void testWriteRecordOverridesFile() throws IOException {
        String uploadPath = File.separator + "TEST123" + File.separator + "documents";
        Part part = mock(Part.class);
        when(part.getSubmittedFileName()).thenReturn("file13123.jpg");
        doNothing().when(part).write(ArgumentMatchers.anyString());
        FileHelperImpl.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        FileHelperImpl.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        String fullPath = FileHelperImpl.writeRecord(part,uploadPath,FileType.PASSENGER_DOCUMENT,"TEST_UNIQUE");
        verify(part,times(3)).write(ArgumentMatchers.any());
        assertEquals("\\TEST123\\documents" +
                        File.separator + FileType.PASSENGER_DOCUMENT.str+"_TEST_UNIQUE.jpg",
                        fullPath);
    }

    @Test
    void testExtractExtension() {
        String extension = FileHelperImpl.extractExtension("file.jpg");
        assertEquals(".jpg", extension);
        extension = FileHelperImpl.extractExtension("file.min.jpg");
        assertEquals(".jpg", extension);
    }
}