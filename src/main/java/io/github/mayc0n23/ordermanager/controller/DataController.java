package io.github.mayc0n23.ordermanager.controller;

import io.github.mayc0n23.ordermanager.exception.ReadFileErrorException;
import io.github.mayc0n23.ordermanager.facade.DataFacade;
import io.github.mayc0n23.ordermanager.model.request.DataUploadRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.READ_FILE_ERROR_MESSAGE;

@RestController
@RequestMapping("/data")
public class DataController implements DataControllerOpenApi {

    private final DataFacade facade;

    public DataController(DataFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void dataUpload(@Valid DataUploadRequest request) {
        try {
            facade.upload(request.file().getInputStream());
        } catch (IOException e) {
            throw new ReadFileErrorException(READ_FILE_ERROR_MESSAGE);
        }
    }

}