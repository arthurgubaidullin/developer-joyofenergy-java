package uk.tw.energy.meter.reading.store;

import java.util.List;

import uk.tw.energy.meter.reading.domain.ElectricityReadingDto;

public class MeterReadingsDto {

    private List<ElectricityReadingDto> electricityReadings;
    private String smartMeterId;

    public MeterReadingsDto() {
    }

    public MeterReadingsDto(String smartMeterId, List<ElectricityReadingDto> electricityReadings) {
        this.smartMeterId = smartMeterId;
        this.electricityReadings = electricityReadings;
    }

    public List<ElectricityReadingDto> getElectricityReadings() {
        return electricityReadings;
    }

    public String getSmartMeterId() {
        return smartMeterId;
    }
}
