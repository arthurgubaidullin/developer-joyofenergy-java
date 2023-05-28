package uk.tw.energy.price.plan.calculate;

import java.math.BigDecimal;
import java.time.Instant;

public interface ElectricityReading {

    public BigDecimal getReading();

    public Instant getTime();

}
