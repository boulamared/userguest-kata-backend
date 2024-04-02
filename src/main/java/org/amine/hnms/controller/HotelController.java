package org.amine.hnms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.amine.hnms.domain.Hotel;
import org.amine.hnms.domain.Notification;
import org.amine.hnms.service.IHotelService;
import org.amine.hnms.service.INotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@Tag(name = "Hotels Management", description = "Endpoints for managing Hotels")
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {

    private final IHotelService hotelService;

    private final INotificationService notificationService;


    public HotelController(IHotelService hotelService, INotificationService notificationService) {
        this.hotelService = hotelService;
        this.notificationService = notificationService;
    }

    @PostMapping
    @Operation(summary = "Create a new hotel", description = "This endpoint allows you to create a new hotel.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel) {
            return new ResponseEntity<>(this.hotelService.create(hotel), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get Hotels", description = "This endpoint allows to get list of all hotels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get List of all the hotels"),
    })
    public ResponseEntity<List<Hotel>> getAll() {
        return new ResponseEntity<>(this.hotelService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    @Operation(summary = "Get Hotel", description = "This endpoint allows to get One hotel by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get details of the hotel by ID"),
    })
    public ResponseEntity<Hotel> getOneById(@PathVariable("hotelId") Integer hotelId) throws Exception {
        return new ResponseEntity<>(this.hotelService.getOneById(hotelId),HttpStatus.OK);
    }

    @PostMapping("/{hotelId}/notifications")
    @Operation(summary = "Create a new notification for a hotel",
            description = "This endpoint allows you to create a new notification for a specific hotel.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notification created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Hotel with the ID not found")
    })
    public ResponseEntity<Notification> createNotification(@PathVariable Integer hotelId, @RequestBody Notification notification) throws Exception {
        return new ResponseEntity<>(this.notificationService.create(hotelId,notification), HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}/notifications")
    @Operation(summary = "Get all notifications for a given hotel",
            description = "This endpoint allows you to retrieve all notifications for a specific hotel.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notifications retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Hotel with the ID not found")
    })
    public ResponseEntity<List<Notification>> getAllByHotelId(@PathVariable("hotelId") Integer hotelId) throws Exception {
        return new ResponseEntity<>(this.hotelService.getOneById(hotelId).getNotifications(),HttpStatus.OK);
    }
}
