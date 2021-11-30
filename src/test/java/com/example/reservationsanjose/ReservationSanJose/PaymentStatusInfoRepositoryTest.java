package com.example.reservationsanjose.ReservationSanJose;

import com.example.reservationsanjose.ReservationSanJose.Model.PaymentStatusInfo;
import com.example.reservationsanjose.ReservationSanJose.Repository.PaymentStatusInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PaymentStatusInfoRepositoryTest {

    @Autowired
    PaymentStatusInfoRepository paymentStatusInfoRepository;

    @Test
    public void testCreatePaymentStatus(){
        PaymentStatusInfo notPaid = new PaymentStatusInfo("Not Paid");
        PaymentStatusInfo paid = new PaymentStatusInfo("Paid");

        paymentStatusInfoRepository.saveAll(List.of(notPaid,paid));

        List<PaymentStatusInfo>paymentStatusInfoList = paymentStatusInfoRepository.findAll();
        assertThat(paymentStatusInfoList.size()).isEqualTo(2);
    }

}
