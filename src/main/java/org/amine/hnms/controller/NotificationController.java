package org.amine.hnms.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.amine.hnms.DTO.NotificationPerformance;
import org.amine.hnms.domain.*;
import org.amine.hnms.service.INotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/notifications")
@Tag(name = "Notification Management", description = "Endpoints for managing notifications/Performance")
public class NotificationController {

    private final INotificationService notificationService;

    public NotificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{notificationId}")
    @Operation(summary = "Get notification by ID",
            description = "This endpoint allows you to retrieve a notification by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Notification with ID not found")
    })
    public ResponseEntity<Notification> getOneById(@PathVariable("notificationId") Integer notificationId) throws Exception {
            return new ResponseEntity<>(this.notificationService.getOneById(notificationId),HttpStatus.OK);
    }

    @PutMapping("/{notificationId}")
    @Operation(summary = "Update notification",
            description = "This endpoint allows you to update a notification.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<Notification> update(@PathVariable("notificationId") Integer notificationId, @RequestBody Notification notification) {
        Notification updatedNotification = notificationService.update(notificationId, notification);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{notificationId}")
    @Operation(summary = "Delete notification",
            description = "This endpoint allows you to delete a notification.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notification deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<Void> delete(@PathVariable("notificationId") Integer notificationId) {
        notificationService.delete(notificationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{notificationId}/performance")
    @Operation(summary = "Get performance metrics for a notification",
            description = "This endpoint allows you to retrieve performance metrics for a specific notification within a specified date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Performance metrics retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<NotificationPerformance> getNotificationPerformanceById(@PathVariable("notificationId") Integer notificationId, @RequestParam(value = "startDate", required = true) LocalDateTime startDateTime, @RequestParam(value = "endDate",required = true) LocalDateTime endDateTime) throws Exception {
        return new ResponseEntity<>(this.notificationService.getNotificationPerformance(notificationId, startDateTime,endDateTime),HttpStatus.OK);
    }

    @PostMapping("/{notificationId}/click")
    @Operation(summary = "Add click for a notification",
            description = "This endpoint allows you to add a click for a specific notification.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Click added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<Click> addClick(@PathVariable("notificationId")Integer notificationId, @RequestBody Click click) throws Exception {
        return new ResponseEntity<>(this.notificationService.addClick(notificationId,click), HttpStatus.CREATED);
    }

    @PostMapping("/{notificationId}/impression")
    @Operation(summary = "Add impression for a notification",
            description = "This endpoint allows you to add an impression for a specific notification.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Impression added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<Impression> addImpression(@PathVariable("notificationId")Integer notificationId, @RequestBody Impression impression) {
        return new ResponseEntity<>(this.notificationService.addImpression(notificationId,impression), HttpStatus.CREATED);
    }

    @PostMapping("/{notificationId}/conversion")
    @Operation(summary = "Add conversion for a notification",
            description = "This endpoint allows you to add a conversion for a specific notification.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conversion added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    public ResponseEntity<Conversion> addConversion(@PathVariable("notificationId")Integer notificationId,@RequestBody Conversion conversion) {
        return new ResponseEntity<>(this.notificationService.addConversion(notificationId,conversion), HttpStatus.CREATED);
    }

}
