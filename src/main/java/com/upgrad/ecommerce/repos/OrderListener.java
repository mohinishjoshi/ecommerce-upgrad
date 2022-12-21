package com.upgrad.ecommerce.repos;

import com.upgrad.ecommerce.domain.Order;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class OrderListener extends AbstractMongoEventListener<Order> {

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Order> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(UUID.randomUUID());
        }
    }

}
