package uk.tw.energy.meter.reading.repository;

import org.springframework.stereotype.Repository;

import uk.tw.energy.meter.reading.electricity_reading_dto.ElectricityReadingDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MeterReadingRepository {

    private final Map<String, List<ElectricityReadingDto>> meterAssociatedReadings;

    public MeterReadingRepository(Map<String, List<ElectricityReadingDto>> meterAssociatedReadings) {
        this.meterAssociatedReadings = meterAssociatedReadings;
    }

    public Optional<List<ElectricityReadingDto>> getReadings(String smartMeterId) {
        return Optional.ofNullable(meterAssociatedReadings.get(smartMeterId));
    }

    public void storeReadings(String smartMeterId, List<ElectricityReadingDto> electricityReadings) {
        if (!meterAssociatedReadings.containsKey(smartMeterId)) {
            meterAssociatedReadings.put(smartMeterId, new ArrayList<>());
        }
        meterAssociatedReadings.get(smartMeterId).addAll(electricityReadings);
    }
}
