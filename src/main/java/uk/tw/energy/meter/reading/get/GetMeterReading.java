package uk.tw.energy.meter.reading.get;

import java.util.List;
import java.util.Optional;

import uk.tw.energy.meter.reading.domain.ElectricityReading;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;

public class GetMeterReading {

    private final MeterReadingRepository repository;

    public GetMeterReading(MeterReadingRepository repository) {
        this.repository = repository;
    }

    public Optional<List<ElectricityReading>> execute(String smartMeterId) {
        return this.repository.getReadings(smartMeterId);
    }

}
