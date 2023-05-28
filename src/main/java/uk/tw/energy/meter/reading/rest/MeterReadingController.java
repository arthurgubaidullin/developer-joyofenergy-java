package uk.tw.energy.meter.reading.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;
import uk.tw.energy.meter.reading.get.GetMeterReadingService;
import uk.tw.energy.meter.reading.store.MeterReadingsDto;
import uk.tw.energy.meter.reading.store.StoreMeterReadingService;

@RestController
@RequestMapping("/readings")
public class MeterReadingController {

    private final StoreMeterReadingService storeMeterReading;
    private final GetMeterReadingService getMeterReading;

    public MeterReadingController(StoreMeterReadingService storeMeterReading, GetMeterReadingService getMeterReading) {
        this.storeMeterReading = storeMeterReading;
        this.getMeterReading = getMeterReading;
    }

    @PostMapping("/store")
    public ResponseEntity storeReadings(@RequestBody MeterReadingsDto meterReadings) {
        if (!(meterReadings.isValid())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        storeMeterReading.execute(meterReadings.getSmartMeterId(), meterReadings.getElectricityReadings());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read/{smartMeterId}")
    public ResponseEntity readReadings(@PathVariable String smartMeterId) {
        Optional<List<ElectricityReadingDto>> readings = getMeterReading.getReadings(smartMeterId);
        return readings.isPresent()
                ? ResponseEntity.ok(readings.get())
                : ResponseEntity.notFound().build();
    }
}
