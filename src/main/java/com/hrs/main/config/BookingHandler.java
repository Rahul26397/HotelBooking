package com.hrs.main.config;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.lambda.runtime.events.models.sns.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.models.sqs.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.models.sqs.SQSMessage;
import com.amazonaws.services.lambda.runtime.events.models.sqs.SQSSQSEvent;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class BookingHandler implements RequestHandler<Map<String, Object>, String> {

    private final DynamoDB dynamoDB;
    private final ObjectMapper objectMapper;

    public BookingHandler() {
        // Initialize DynamoDB client (ensure proper AWS credentials and region setup)
        this.dynamoDB = new DynamoDB(/* provide DynamoDB client */);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        try {
            // Parse booking details from the incoming event
            Booking bookingDetails = parseBookingDetails(input);

            // Save booking details to DynamoDB
            saveBookingToDynamoDB(bookingDetails);

            // Publish confirmation events (for demonstration)
            publishConfirmationEvent("Booking processed successfully!", context);

            return "Booking processed successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing booking!";
        }
    }

    private BookingDetails parseBookingDetails(Map<String, Object> input) {
        // Convert input to BookingDetails object using ObjectMapper
        // Parse according to your event source and data structure
        // For example: objectMapper.readValue(objectMapper.writeValueAsString(input), BookingDetails.class);
        return null; // Replace this with your actual parsing logic
    }

    private void saveBookingToDynamoDB(BookingDetails bookingDetails) {
        Table bookingsTable = dynamoDB.getTable("Bookings"); // Replace with your DynamoDB table name

        Item item = new Item()
                .withPrimaryKey("BookingId", bookingDetails.getBookingId())
                .withString("HotelName", bookingDetails.getHotelName())
                .withString("GuestName", bookingDetails.getGuestName())
                .withString("CheckInDate", bookingDetails.getCheckInDate())
                .withString("CheckOutDate", bookingDetails.getCheckOutDate());

        bookingsTable.putItem(item);
        // Log or handle the outcome as required
    }

    private void publishConfirmationEvent(String message, Context context) {
        // Your logic to publish confirmation events (e.g., using SNS)
        // For demonstration, log the message
        context.getLogger().log("Confirmation Event: " + message);
    }
}
