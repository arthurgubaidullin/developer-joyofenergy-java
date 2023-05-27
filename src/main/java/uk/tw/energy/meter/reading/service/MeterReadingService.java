package uk.tw.energy.meter.reading.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.tw.energy.meter.reading.domain.ElectricityReading;
import uk.tw.energy.meter.reading.get.GetMeterReading;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;
import uk.tw.energy.meter.reading.store.StoreMeterReading;

@Service
public class MeterReadingService {

    private final StoreMeterReading storeMeterReading;
    private final GetMeterReading getMeterReading;

    public MeterReadingService(MeterReadingRepository repository) {
        this.storeMeterReading = new StoreMeterReading(repository);
        this.getMeterReading = new GetMeterReading(repository);
    }

    public Optional<List<ElectricityReading>> getReadings(String smartMeterId) {
        return this.getMeterReading.execute(smartMeterId);
    }

    public void storeReadings(String smartMeterId, List<ElectricityReading> electricityReadings) {
        storeMeterReading.execute(smartMeterId, electricityReadings);
    }
}
