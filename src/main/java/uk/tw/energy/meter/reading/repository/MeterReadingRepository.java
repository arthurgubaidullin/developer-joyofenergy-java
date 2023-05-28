package uk.tw.energy.meter.reading.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import uk.tw.energy.meter.reading.dpo.ElectricityReadingDpo;

@Repository
public class MeterReadingRepository {

    private final Map<String, List<ElectricityReadingDpo>> meterAssociatedReadings;

    public MeterReadingRepository(Map<String, List<ElectricityReadingDpo>> meterAssociatedReadings) {
        this.meterAssociatedReadings = meterAssociatedReadings;
    }

    public Optional<List<ElectricityReadingDpo>> getReadings(String smartMeterId) {
        return Optional.ofNullable(meterAssociatedReadings.get(smartMeterId));
    }

    public void storeReadings(String smartMeterId, List<ElectricityReadingDpo> electricityReadings) {
        if (!meterAssociatedReadings.containsKey(smartMeterId)) {
            meterAssociatedReadings.put(smartMeterId, new ArrayList<>());
        }
        meterAssociatedReadings.get(smartMeterId).addAll(electricityReadings);
    }
}
