package io.github.mayc0n23.ordermanager.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DataControllerTest {

    private static final String PATH = "/data/upload";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDataUpload() throws Exception {
        final String content = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final InputStream fileContent = new ByteArrayInputStream(content.getBytes());
        final var file = new MockMultipartFile("file", "test.txt", "text/plain", fileContent);

        mockMvc.perform(multipart(PATH)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isNoContent());
    }

}