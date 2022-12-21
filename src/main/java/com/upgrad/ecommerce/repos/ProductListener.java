package com.upgrad.ecommerce.repos;

import com.upgrad.ecommerce.domain.Product;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class ProductListener extends AbstractMongoEventListener<Product> {

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Product> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(UUID.randomUUID());
        }
    }

}
