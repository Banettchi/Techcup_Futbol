package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.Payment;
import edu.eci.dosw.tech_cup.model.PaymentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toEntity(PaymentModel model);

    PaymentModel toModel(Payment entity);
}
