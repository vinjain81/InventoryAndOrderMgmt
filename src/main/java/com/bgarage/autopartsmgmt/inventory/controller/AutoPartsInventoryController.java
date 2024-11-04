package com.bgarage.autopartsmgmt.inventory.controller;

import com.bgarage.autopartsmgmt.inventory.dto.AutoPartsInventoryRequest;
import com.bgarage.autopartsmgmt.inventory.model.AutoPartsInventoryEntity;
import com.bgarage.autopartsmgmt.inventory.service.AutoPartsInventoryService;
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
public class AutoPartsInventoryController {

    private final AutoPartsInventoryService autoPartsInventoryService;


    public AutoPartsInventoryController(AutoPartsInventoryService autoPartsInventoryService) {
        this.autoPartsInventoryService = autoPartsInventoryService;
    }

    @PostMapping("/AutoPartsInventory")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartsInventoryEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<AutoPartsInventoryEntity> addAutoPartInventory(@Valid @RequestBody AutoPartsInventoryRequest autoPartsInventoryRequest)
    {
        AutoPartsInventoryEntity autoPartsInventoryEntity = autoPartsInventoryService.addAvailableQty(autoPartsInventoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(autoPartsInventoryEntity);
    }

    @PutMapping("/AutoPartsInventory/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartsInventoryEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<AutoPartsInventoryEntity> updateAutoPartInventory(@PathVariable Integer id,
                                                                     @RequestBody AutoPartsInventoryRequest autoPartsInventoryRequest)
    {
        AutoPartsInventoryEntity autoPartsInventoryEntity = autoPartsInventoryService.updateAvailableQty(id,
                autoPartsInventoryRequest.getAvailableQuantity());
        return ResponseEntity.status(HttpStatus.OK)
                .body(autoPartsInventoryEntity);
    }

    @GetMapping("/AutoPartsInventory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "CREATED", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartsInventoryEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<List<AutoPartsInventoryEntity>> getAllAutoPartInventory()
    {
        List<AutoPartsInventoryEntity> autoPartsInventory = autoPartsInventoryService.getAllInventory();
        return ResponseEntity.status(HttpStatus.OK)
                .body(autoPartsInventory);
    }

    @GetMapping("/AutoPartsInventory/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "CREATED", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartsInventoryEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<AutoPartsInventoryEntity> getInventoryById(@PathVariable Integer id)
    {
        AutoPartsInventoryEntity autoPartsInventoryEntity = autoPartsInventoryService.getInventoryByPartId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(autoPartsInventoryEntity);
    }
}
