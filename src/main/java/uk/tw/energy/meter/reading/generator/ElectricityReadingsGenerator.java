package uk.tw.energy.meter.reading.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;

public class ElectricityReadingsGenerator {

    public List<ElectricityReadingDto> generate(int number) {
        List<ElectricityReadingDto> readings = new ArrayList<>();
        Instant now = Instant.now();

        Random readingRandomiser = new Random();
        for (int i = 0; i < number; i++) {
            double positiveRandomValue = Math.abs(readingRandomiser.nextGaussian());
            BigDecimal randomReading = BigDecimal.valueOf(positiveRandomValue).setScale(4, RoundingMode.CEILING);
            ElectricityReadingDto electricityReading = new ElectricityReadingDto(now.minusSeconds(i * 10),
                    randomReading);
            readings.add(electricityReading);
        }

        readings.sort(Comparator.comparing(ElectricityReadingDto::getTime));
        return readings;
    }
}
