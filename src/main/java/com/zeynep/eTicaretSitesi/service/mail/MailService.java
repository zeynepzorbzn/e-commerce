package com.zeynep.eTicaretSitesi.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmation(OrderCreatedEvent event) {

        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(event.getEmail());

            helper.setSubject(
                    "Siparişiniz Alındı - " + event.getOrderCode()
            );

            String htmlContent = buildOrderConfirmationEmail(event);

            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (MessagingException e) {

            throw new RuntimeException(
                    "Sipariş onay maili gönderilemedi.",
                    e
            );
        }
    }

    private String buildOrderConfirmationEmail(
            OrderCreatedEvent event
    ) {

        StringBuilder itemsHtml = new StringBuilder();

        for (OrderMailItem item : event.getItems()) {

            BigDecimal itemTotal =
                    item.getUnitPrice()
                            .multiply(
                                    BigDecimal.valueOf(item.getQuantity())
                            );

            String variantInfo = "";

            if (item.getSize() != null && !item.getSize().isBlank()) {
                variantInfo += "Beden: " + escapeHtml(item.getSize());
            }

            if (item.getColor() != null && !item.getColor().isBlank()) {

                if (!variantInfo.isBlank()) {
                    variantInfo += " &nbsp;|&nbsp; ";
                }

                variantInfo +=
                        "Renk: " + escapeHtml(item.getColor());
            }

            itemsHtml.append("""
                    <tr>
                        <td style="
                            padding: 16px 12px;
                            border-bottom: 1px solid #eeeeee;
                        ">
                            <strong>%s</strong>
                            <div style="
                                margin-top: 5px;
                                color: #777777;
                                font-size: 13px;
                            ">
                                %s
                            </div>
                        </td>

                        <td style="
                            padding: 16px 12px;
                            border-bottom: 1px solid #eeeeee;
                            text-align: center;
                        ">
                            %d
                        </td>

                        <td style="
                            padding: 16px 12px;
                            border-bottom: 1px solid #eeeeee;
                            text-align: right;
                        ">
                            %s TL
                        </td>

                        <td style="
                            padding: 16px 12px;
                            border-bottom: 1px solid #eeeeee;
                            text-align: right;
                            font-weight: bold;
                        ">
                            %s TL
                        </td>
                    </tr>
                    """.formatted(
                    escapeHtml(item.getProductName()),
                    variantInfo,
                    item.getQuantity(),
                    formatPrice(item.getUnitPrice()),
                    formatPrice(itemTotal)
            ));
        }

        String address = """
                %s<br>
                %s, %s<br>
                %s<br>
                %s
                """.formatted(
                escapeHtml(event.getAddressName()),
                escapeHtml(event.getDistrict()),
                escapeHtml(event.getCity()),
                escapeHtml(event.getStreet()),
                escapeHtml(event.getPostalCode())
        );

        return """
                <!DOCTYPE html>
                <html lang="tr">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport"
                          content="width=device-width, initial-scale=1.0">
                    <title>ZEY'Z Siparişiniz Alındı</title>
                </head>

                <body style="
                    margin: 0;
                    padding: 0;
                    background-color: #f4f4f5;
                    font-family: Arial, Helvetica, sans-serif;
                    color: #222222;
                ">

                    <div style="
                        width: 100%%;
                        padding: 40px 0;
                    ">

                        <div style="
                            max-width: 640px;
                            margin: 0 auto;
                            background-color: #ffffff;
                            border-radius: 16px;
                            overflow: hidden;
                        ">

                            <!-- HEADER -->
                            <div style="
                                padding: 28px 32px;
                                background-color: #111111;
                                color: #ffffff;
                            ">

                                <div style="
                                    font-size: 24px;
                                    font-weight: bold;
                                ">
                                    ZEY'Z
                                </div>

                                <div style="
                                    margin-top: 6px;
                                    font-size: 13px;
                                    color: #cccccc;
                                ">
                                    Sipariş Onayı
                                </div>

                            </div>

                            <!-- CONTENT -->
                            <div style="
                                padding: 36px 32px;
                            ">

                                <h1 style="
                                    margin: 0 0 16px 0;
                                    font-size: 26px;
                                ">
                                    Siparişiniz Alındı 🎉
                                </h1>

                                <p style="
                                    margin: 0 0 12px 0;
                                    font-size: 16px;
                                ">
                                    Merhaba <strong>%s</strong>,
                                </p>

                                <p style="
                                    margin: 0 0 28px 0;
                                    color: #666666;
                                    line-height: 1.6;
                                ">
                                    Siparişiniz başarıyla oluşturuldu.
                                    Aşağıda siparişinizin detaylarını
                                    bulabilirsiniz.
                                </p>

                                <!-- ORDER INFO -->
                                <div style="
                                    background-color: #f8f8f8;
                                    border-radius: 10px;
                                    padding: 20px;
                                    margin-bottom: 28px;
                                ">

                                    <div style="
                                        margin-bottom: 10px;
                                    ">
                                        <span style="color: #777777;">
                                            Sipariş No
                                        </span>
                                        <br>
                                        <strong>%s</strong>
                                    </div>

                                    <div>
                                        <span style="color: #777777;">
                                            Sipariş Tarihi
                                        </span>
                                        <br>
                                        <strong>%s</strong>
                                    </div>

                                </div>

                                <!-- PRODUCTS -->
                                <h2 style="
                                    font-size: 18px;
                                    margin: 0 0 14px 0;
                                ">
                                    Sipariş Detayları
                                </h2>

                                <table style="
                                    width: 100%%;
                                    border-collapse: collapse;
                                    font-size: 14px;
                                ">

                                    <thead>
                                        <tr>
                                            <th style="
                                                padding: 10px 12px;
                                                text-align: left;
                                                color: #777777;
                                                font-weight: normal;
                                            ">
                                                Ürün
                                            </th>

                                            <th style="
                                                padding: 10px 12px;
                                                text-align: center;
                                                color: #777777;
                                                font-weight: normal;
                                            ">
                                                Adet
                                            </th>

                                            <th style="
                                                padding: 10px 12px;
                                                text-align: right;
                                                color: #777777;
                                                font-weight: normal;
                                            ">
                                                Birim
                                            </th>

                                            <th style="
                                                padding: 10px 12px;
                                                text-align: right;
                                                color: #777777;
                                                font-weight: normal;
                                            ">
                                                Toplam
                                            </th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        %s
                                    </tbody>

                                </table>

                                <!-- TOTAL -->
                                <div style="
                                    margin-top: 24px;
                                    padding-top: 20px;
                                    border-top: 2px solid #222222;
                                    text-align: right;
                                ">

                                    <span style="
                                        color: #777777;
                                        font-size: 14px;
                                    ">
                                        Sipariş Toplamı
                                    </span>

                                    <div style="
                                        margin-top: 5px;
                                        font-size: 24px;
                                        font-weight: bold;
                                    ">
                                        %s TL
                                    </div>

                                </div>

                                <!-- ADDRESS -->
                                <div style="
                                    margin-top: 32px;
                                ">

                                    <h2 style="
                                        font-size: 18px;
                                        margin: 0 0 12px 0;
                                    ">
                                        Teslimat Adresi
                                    </h2>

                                    <div style="
                                        padding: 18px;
                                        background-color: #f8f8f8;
                                        border-radius: 10px;
                                        color: #555555;
                                        line-height: 1.7;
                                        font-size: 14px;
                                    ">
                                        %s
                                    </div>

                                </div>

                                <p style="
                                    margin-top: 32px;
                                    color: #777777;
                                    line-height: 1.6;
                                    font-size: 14px;
                                ">
                                    Siparişinizle ilgili gelişmeleri
                                    hesabınız üzerinden takip
                                    edebilirsiniz.
                                </p>

                            </div>

                            <!-- FOOTER -->
                            <div style="
                                padding: 24px 32px;
                                background-color: #f8f8f8;
                                text-align: center;
                                color: #999999;
                                font-size: 12px;
                            ">

                                <p style="margin: 0 0 6px 0;">
                                    Bizi tercih ettiğiniz için teşekkür ederiz.
                                </p>

                                <p style="margin: 0;">
                                    ZEY'Z
                                </p>

                            </div>

                        </div>

                    </div>

                </body>
                </html>
                """.formatted(
                escapeHtml(event.getCustomerName()),
                escapeHtml(event.getOrderCode()),
                escapeHtml(event.getOrderDate()),
                itemsHtml,
                formatPrice(new BigDecimal(event.getTotalPrice())),
                address
        );
    }

    private String formatPrice(BigDecimal price) {
        return price.setScale(2)
                .toString()
                .replace(".", ",");
    }

    private String escapeHtml(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}