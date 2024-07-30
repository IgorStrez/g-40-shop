package de.ait_tr.g_40_shop.exception_handling;

import java.util.Objects;

public class Response {

    private String message;
    private String additionalMessage;

    public Response(String message) {
        this.message = message;
    }

    public Response(String message, String additionalMessage) {
        this.message = message;
        this.additionalMessage = additionalMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(message, response.message) && Objects.equals(additionalMessage, response.additionalMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, additionalMessage);
    }

    @Override
    public String toString() {
        return String.format("Response: message - %s%s",
                message,
                additionalMessage == null ? "" : ", additionalMessage: " + additionalMessage);
    }
}