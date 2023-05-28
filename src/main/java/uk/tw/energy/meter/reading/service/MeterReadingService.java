package uk.tw.energy.meter.reading.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.tw.energy.meter.reading.api.GetMeterReadingService;
import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;
import uk.tw.energy.meter.reading.get.GetMeterReading;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;
import uk.tw.energy.meter.reading.store.StoreMeterReading;
import uk.tw.energy.meter.reading.store.StoreMeterReadingService;

@Service
public class MeterReadingService implements GetMeterReadingService, StoreMeterReadingService {

    private final StoreMeterReadingService storeMeterReading;
    private final GetMeterReadingService getMeterReading;

    public MeterReadingService(MeterReadingRepository repository) {
        this.storeMeterReading = new StoreMeterReading(repository);
        this.getMeterReading = new GetMeterReading(repository);
    }

    public Optional<List<ElectricityReadingDto>> getReadings(String smartMeterId) {
        return this.getMeterReading.getReadings(smartMeterId);
    }

    public void storeReadings(String smartMeterId, List<ElectricityReadingDto> electricityReadings) {
        storeMeterReading.storeReadings(smartMeterId, electricityReadings);
    }
}
