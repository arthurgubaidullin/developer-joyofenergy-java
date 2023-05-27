package uk.tw.energy.meter.reading.electricity_reading_dto;

import java.math.BigDecimal;
import java.time.Instant;

public class ElectricityReadingDto {

    private Instant time;
    private BigDecimal reading; // kW

    public ElectricityReadingDto() {
    }

    public ElectricityReadingDto(Instant time, BigDecimal reading) {
        this.time = time;
        this.reading = reading;
    }

    public BigDecimal getReading() {
        return reading;
    }

    public Instant getTime() {
        return time;
    }
}
