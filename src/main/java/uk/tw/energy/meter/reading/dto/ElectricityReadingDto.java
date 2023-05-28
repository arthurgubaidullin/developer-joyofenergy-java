package uk.tw.energy.meter.reading.dto;

import java.math.BigDecimal;
import java.time.Instant;

import uk.tw.energy.price.plan.calculate.ElectricityReading;

public class ElectricityReadingDto implements ElectricityReading {

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

    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ElectricityReadingDto other = (ElectricityReadingDto) obj;

        return other.getReading().equals(this.reading)
                && other.getTime().equals(this.time);
    }
}
