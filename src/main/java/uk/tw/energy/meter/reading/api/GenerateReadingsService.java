package uk.tw.energy.meter.reading.api;

import java.util.List;

import org.springframework.stereotype.Service;

import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;

@Service
public interface GenerateReadingsService {

    public List<ElectricityReadingDto> generate(int number);

}