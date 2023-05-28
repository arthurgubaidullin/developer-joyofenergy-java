package uk.tw.energy.meter.reading.dpo;

import java.math.BigDecimal;
import java.time.Instant;

public class ElectricityReadingDpo {

    private Instant time;
    private BigDecimal reading; // kW

    public ElectricityReadingDpo() {
    }

    public ElectricityReadingDpo(Instant time, BigDecimal reading) {
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
