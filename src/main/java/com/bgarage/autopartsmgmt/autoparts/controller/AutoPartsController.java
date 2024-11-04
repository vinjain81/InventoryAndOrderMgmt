package com.bgarage.autopartsmgmt.autoparts.controller;

import com.bgarage.autopartsmgmt.autoparts.dto.AutoPartRequest;
import com.bgarage.autopartsmgmt.autoparts.model.AutoPartEntity;
import com.bgarage.autopartsmgmt.autoparts.service.AutoPartsService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutoPartsController
{
    private final AutoPartsService autoPartsService;

    public AutoPartsController(AutoPartsService autoPartsService) {
        this.autoPartsService = autoPartsService;
    }

    @PostMapping("/AutoParts")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<AutoPartEntity> addAutoPart(@Valid @RequestBody AutoPartRequest autoPartRequest)
    {
        AutoPartEntity autoPartEntity = autoPartsService.addPart(autoPartRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(autoPartEntity);
    }

    @PutMapping("/AutoParts/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<AutoPartEntity> updateAutoPart(@PathVariable Integer id, @RequestBody AutoPartRequest autoPartRequest)
    {
        AutoPartEntity autoPartEntity = autoPartsService.updatePart(id, autoPartRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(autoPartEntity);
    }

    @GetMapping("/AutoParts")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<List<AutoPartEntity>> getAllAutoParts()
    {
        List<AutoPartEntity> autoPartEntities = autoPartsService.getAllAutoParts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(autoPartEntities);
    }

    @GetMapping("/AutoParts/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<AutoPartEntity> getAutoPartsById(@PathVariable Integer id)
    {
        AutoPartEntity autoPartEntity = autoPartsService.getAutoPartsById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(autoPartEntity);
    }
}
