package uk.tw.energy.meter.reading.store;

import java.util.List;

import uk.tw.energy.meter.reading.domain.ElectricityReading;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;

public class StoreMeterReading {

    private final MeterReadingRepository meterReadingRepository;

    public StoreMeterReading(MeterReadingRepository meterReadingRepository) {
        this.meterReadingRepository = meterReadingRepository;
    }

    public void execute(String smartMeterId, List<ElectricityReading> electricityReadings) {
        this.meterReadingRepository.storeReadings(smartMeterId, electricityReadings);
    }
}
