package uk.tw.energy.price.plan.calculate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;

import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;
import uk.tw.energy.price.plan.domain.PricePlan;

public class CalculateCost {

    public static BigDecimal calculateCost(List<ElectricityReadingDto> electricityReadings, PricePlan pricePlan) {
        BigDecimal average = calculateAverageReading(electricityReadings);
        BigDecimal timeElapsed = calculateTimeElapsed(electricityReadings);

        BigDecimal averagedCost = average.divide(timeElapsed, RoundingMode.HALF_UP);
        return averagedCost.multiply(pricePlan.getUnitRate());
    }

    private static BigDecimal calculateAverageReading(List<ElectricityReadingDto> electricityReadings) {
        BigDecimal summedReadings = electricityReadings.stream()
                .map(ElectricityReadingDto::getReading)
                .reduce(BigDecimal.ZERO, (reading, accumulator) -> reading.add(accumulator));

        return summedReadings.divide(BigDecimal.valueOf(electricityReadings.size()), RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateTimeElapsed(List<ElectricityReadingDto> electricityReadings) {
        ElectricityReadingDto first = electricityReadings.stream()
                .min(Comparator.comparing(ElectricityReadingDto::getTime))
                .get();
        ElectricityReadingDto last = electricityReadings.stream()
                .max(Comparator.comparing(ElectricityReadingDto::getTime))
                .get();

        return BigDecimal.valueOf(Duration.between(first.getTime(), last.getTime()).getSeconds() / 3600.0);
    }
}
