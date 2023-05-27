package uk.tw.energy.meter.reading.builder;

import uk.tw.energy.meter.reading.domain.ElectricityReadingDto;
import uk.tw.energy.meter.reading.generator.ElectricityReadingsGenerator;
import uk.tw.energy.meter.reading.store.MeterReadingsDto;

import java.util.ArrayList;
import java.util.List;

public class MeterReadingsBuilder {

    private static final String DEFAULT_METER_ID = "id";

    private String smartMeterId = DEFAULT_METER_ID;
    private List<ElectricityReadingDto> electricityReadings = new ArrayList<>();

    public MeterReadingsBuilder setSmartMeterId(String smartMeterId) {
        this.smartMeterId = smartMeterId;
        return this;
    }

    public MeterReadingsBuilder generateElectricityReadings() {
        return generateElectricityReadings(5);
    }

    public MeterReadingsBuilder generateElectricityReadings(int number) {
        ElectricityReadingsGenerator readingsBuilder = new ElectricityReadingsGenerator();
        this.electricityReadings = readingsBuilder.generate(number);
        return this;
    }

    public MeterReadingsDto build() {
        return new MeterReadingsDto(smartMeterId, electricityReadings);
    }
}
