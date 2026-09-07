package com.zeynep.eTicaretSitesi.service.mail;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderMailListener {

    private final MailService mailService;

    public OrderMailListener(MailService mailService) {
        this.mailService = mailService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreated(OrderCreatedEvent event) {

        try {

            mailService.sendOrderConfirmation(event);

        } catch (Exception e) {

            System.err.println(
                    "Sipariş onay maili gönderilemedi: "
                            + e.getMessage()
            );
        }
    }
}