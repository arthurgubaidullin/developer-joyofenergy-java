package uk.tw.energy.meter.reading.get;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;

@Service
public interface GetMeterReadingService {

    public Optional<List<ElectricityReadingDto>> execute(String smartMeterId);

}
