package uk.tw.energy.meter.reading.store;

import java.util.List;

import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;

public class MeterReadingsDto {

    private List<ElectricityReadingDto> electricityReadings;
    private String smartMeterId;

    public MeterReadingsDto() {
    }

    public MeterReadingsDto(String smartMeterId, List<ElectricityReadingDto> electricityReadings) {
        this.smartMeterId = smartMeterId;
        this.electricityReadings = electricityReadings;
    }

    public boolean isValid() {
        String smartMeterId = this.getSmartMeterId();
        List<ElectricityReadingDto> electricityReadings = this.getElectricityReadings();
        return smartMeterId != null && !smartMeterId.isEmpty()
                && electricityReadings != null && !electricityReadings.isEmpty();
    }

    public List<ElectricityReadingDto> getElectricityReadings() {
        return electricityReadings;
    }

    public String getSmartMeterId() {
        return smartMeterId;
    }
}
