package com.newspaper.dao;

import java.time.LocalDate;

public class DailyDeliveryLogDAO {
    private Integer id;
    private LocalDate deliveryDate;

    public DailyDeliveryLogDAO() {}

    public DailyDeliveryLogDAO(Integer id, LocalDate deliveryDate) {
        this.id = id;
        this.deliveryDate = deliveryDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
