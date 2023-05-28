package uk.tw.energy.meter.reading.get;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.tw.energy.meter.reading.api.GetMeterReadingService;
import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;

@Service
public class GetMeterReading implements GetMeterReadingService {

    private final MeterReadingRepository repository;

    public GetMeterReading(MeterReadingRepository repository) {
        this.repository = repository;
    }

    public Optional<List<ElectricityReadingDto>> getReadings(String smartMeterId) {
        return this.repository.getReadings(smartMeterId);
    }

}
