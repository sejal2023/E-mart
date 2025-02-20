package com.example.Services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Models.Order;
import com.example.Models.User;
import com.example.Models.Product;
import com.example.Repositories.OrderRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.OutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void generateInvoice(int orderId, HttpServletResponse response) throws IOException {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            if (!optionalOrder.isPresent()) {
                throw new RuntimeException("Order with ID " + orderId + " not found.");
            }

            Order order = optionalOrder.get();
            User user = order.getUser();
            Product product = order.getCart().getProduct();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=invoice_" + orderId + ".pdf");

            Document document = new Document(PageSize.A4);
            OutputStream out = response.getOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26, BaseColor.BLACK);
            Paragraph title = new Paragraph("INVOICE", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Customer Information
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.DARK_GRAY);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            document.add(new Paragraph("Customer Details", headerFont));
            document.add(new Paragraph("Name: " + user.getUsername(), bodyFont));
            document.add(new Paragraph("Email: " + user.getEmail(), bodyFont));
            document.add(new Paragraph("Phone No. : " + user.getPhonenumber(), bodyFont));
            document.add(new Paragraph("\n"));

            // Product Details Table (Moved before Order Details)
            document.add(new Paragraph("Product Details", headerFont));
            document.add(new Paragraph("\n"));

            PdfPTable productTable = new PdfPTable(4);
            productTable.setWidthPercentage(100);
            productTable.setSpacingBefore(10f);
            productTable.setSpacingAfter(10f);
            productTable.setWidths(new float[]{3, 5, 2, 2});

            addHeaderCell(productTable, "Product Name", headerFont);
            addHeaderCell(productTable, "Description", headerFont);
            addHeaderCell(productTable, "Price", headerFont);
            addHeaderCell(productTable, "Quantity", headerFont);

            PdfPCell productNameCell = getStyledCell(product.getProductname(), bodyFont, BaseColor.LIGHT_GRAY);
            PdfPCell descriptionCell = getStyledCell(product.getDescription(), bodyFont, BaseColor.WHITE);
            PdfPCell priceCell = getStyledCell("₹" + product.getPrice(), bodyFont, BaseColor.LIGHT_GRAY);
            PdfPCell quantityCell = getStyledCell(String.valueOf(order.getCart().getQuantity()), bodyFont, BaseColor.WHITE);

            productTable.addCell(productNameCell);
            productTable.addCell(descriptionCell);
            productTable.addCell(priceCell);
            productTable.addCell(quantityCell);

            document.add(productTable);
            document.add(new Paragraph("\n"));

            // Order Date Formatting
            LocalDateTime orderDateTime = order.getOrderDate();
            String formattedDate = "N/A";
            if (orderDateTime != null) {
                Date orderDate = Date.from(orderDateTime.atZone(ZoneId.systemDefault()).toInstant());
                formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderDate);
            }

            // Order Details Table
            document.add(new Paragraph("Order Details", headerFont));
            document.add(new Paragraph("\n"));

            PdfPTable orderTable = new PdfPTable(2);
            orderTable.setWidthPercentage(100);
            orderTable.setSpacingBefore(10f);
            orderTable.setSpacingAfter(10f);
            orderTable.setWidths(new float[]{2, 3});

            addTableHeader(orderTable, "Order ID", String.valueOf(order.getId()), headerFont, bodyFont);
            addTableHeader(orderTable, "Order Date", formattedDate, headerFont, bodyFont);
            addTableHeader(orderTable, "Total Amount", "₹" + order.getAmount(), headerFont, bodyFont);
            
            document.add(orderTable);
            document.add(new Paragraph("\n"));

            // Generate QR Code
            BarcodeQRCode barcodeQRCode = new BarcodeQRCode("Order ID: " + order.getId() + 
                                                            "\nCustomer: " + user.getUsername() + 
                                                            "\nAmount: ₹" + order.getAmount(), 
                                                            150, 150, null);
            Image qrImage = barcodeQRCode.getImage();
            qrImage.setAlignment(Element.ALIGN_CENTER);
            qrImage.scaleAbsolute(100, 100); // Adjust size as needed

            document.add(new Paragraph("\n\n"));
            document.add(qrImage);
            document.add(new Paragraph("\n"));

            // Footer
            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLUE);
            Paragraph footer = new Paragraph("Thank you for shopping with us!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generating invoice PDF: " + e.getMessage(), e);
        }
    }

    private void addTableHeader(PdfPTable table, String key, String value, Font keyFont, Font valueFont) {
        table.addCell(getStyledCell(key, keyFont, BaseColor.LIGHT_GRAY));
        table.addCell(getStyledCell(value, valueFont, BaseColor.WHITE));
    }

    private void addHeaderCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8);
        table.addCell(cell);
    }

    private PdfPCell getStyledCell(String text, Font font, BaseColor bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(bgColor);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8);
        cell.setBorderWidth(1);
        return cell;
    }
}