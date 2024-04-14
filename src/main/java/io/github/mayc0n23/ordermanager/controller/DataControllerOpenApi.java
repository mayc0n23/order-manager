package io.github.mayc0n23.ordermanager.controller;

import io.github.mayc0n23.ordermanager.exception.ProblemDetails;
import io.github.mayc0n23.ordermanager.model.request.DataUploadRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "Data Upload", description = "Operações para fazer o upload de dados para o sistema")
@OpenAPIDefinition(info = @Info(title = "Open API Doc", version = "v1"))
public interface DataControllerOpenApi {

    @Operation(summary = "Upload de dados para o sistema atraves de arquivos txt")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content= @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetails.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity",
                    content= @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetails.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content= @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetails.class)
                    )
            )
    })
    void dataUpload(DataUploadRequest request);

}