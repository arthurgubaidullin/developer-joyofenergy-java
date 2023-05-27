package uk.tw.energy.meter.reading.store;

import java.util.List;

import uk.tw.energy.meter.reading.domain.ElectricityReading;

public class MeterReadingsDto {

    private List<ElectricityReading> electricityReadings;
    private String smartMeterId;

    public MeterReadingsDto() {
    }

    public MeterReadingsDto(String smartMeterId, List<ElectricityReading> electricityReadings) {
        this.smartMeterId = smartMeterId;
        this.electricityReadings = electricityReadings;
    }

    public List<ElectricityReading> getElectricityReadings() {
        return electricityReadings;
    }

    public String getSmartMeterId() {
        return smartMeterId;
    }
}
