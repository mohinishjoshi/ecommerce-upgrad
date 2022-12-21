package com.upgrad.ecommerce.repos;

import com.upgrad.ecommerce.domain.Address;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class AddressListener extends AbstractMongoEventListener<Address> {

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Address> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(UUID.randomUUID());
        }
    }

}
