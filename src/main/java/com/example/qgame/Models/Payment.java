package com.example.qgame.Models;

import com.example.qgame.helpers.converters.IJsonConverter;
import com.example.qgame.helpers.listeners.PaymentListener;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@DynamicUpdate
@EntityListeners(PaymentListener.class)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    private String paymentUrl;

    private boolean isSuccess;

    private String referenceCode;

    private String code;

    @Column(columnDefinition = "TEXT")
    private String thirdPartyResponse;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private PaymentMethod paymentMethod;

    @Convert(converter = IJsonConverter.class)
    @Column(columnDefinition = "json")
    private PaymentInfo information;

    private String paymentService;

    private float amount;

    private String test;
}
