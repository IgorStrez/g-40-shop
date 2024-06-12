package de.ait_tr.g_40_shop.exception_handling;

import de.ait_tr.g_40_shop.exception_handling.exceptions.FirstTestException;

import java.util.Objects;

public class Response {

    private String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "Response: " + message;
    }
}