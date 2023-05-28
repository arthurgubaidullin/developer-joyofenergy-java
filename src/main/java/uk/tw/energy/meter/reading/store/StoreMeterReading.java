package uk.tw.energy.meter.reading.store;

import java.util.List;

import org.springframework.stereotype.Service;

import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;

@Service
public class StoreMeterReading implements StoreMeterReadingService {

    private final MeterReadingRepository meterReadingRepository;

    public StoreMeterReading(MeterReadingRepository meterReadingRepository) {
        this.meterReadingRepository = meterReadingRepository;
    }

    public void execute(String smartMeterId, List<ElectricityReadingDto> electricityReadings) {
        this.meterReadingRepository.storeReadings(smartMeterId, electricityReadings);
    }
}
