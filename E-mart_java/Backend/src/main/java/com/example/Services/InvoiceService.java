package com.example.Services;



import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface InvoiceService {
    void generateInvoice(int orderId, HttpServletResponse response) throws IOException;
}
