package uk.tw.energy.meter.reading.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.tw.energy.meter.reading.domain.ElectricityReading;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;
import uk.tw.energy.meter.reading.store.StoreMeterReading;

@Service
public class MeterReadingService {

    private final MeterReadingRepository repository;
    private final StoreMeterReading storeMeterReading;

    public MeterReadingService(MeterReadingRepository repository) {
        this.repository = repository;
        this.storeMeterReading = new StoreMeterReading(repository);
    }

    public Optional<List<ElectricityReading>> getReadings(String smartMeterId) {
        return this.repository.getReadings(smartMeterId);
    }

    public void storeReadings(String smartMeterId, List<ElectricityReading> electricityReadings) {
        storeMeterReading.execute(smartMeterId, electricityReadings);
    }
}
