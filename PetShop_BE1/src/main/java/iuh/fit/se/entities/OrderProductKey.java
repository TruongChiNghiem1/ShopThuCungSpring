package iuh.fit.se.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderProductKey implements Serializable {
    private Long orderId;
    private Long productId;
}

