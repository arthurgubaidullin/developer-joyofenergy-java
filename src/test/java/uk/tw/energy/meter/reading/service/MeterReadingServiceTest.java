package uk.tw.energy.meter.reading.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.tw.energy.meter.reading.repository.MeterReadingRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MeterReadingServiceTest {

    private MeterReadingService meterReadingService;
    private MeterReadingRepository meterReadingRepository;

    @BeforeEach
    public void setUp() {
        this.meterReadingRepository = new MeterReadingRepository(new HashMap<>());
        this.meterReadingService = new MeterReadingService(this.meterReadingRepository);
    }

    @Test
    public void givenMeterIdThatDoesNotExistShouldReturnNull() {
        assertThat(meterReadingService.getReadings("unknown-id")).isEqualTo(Optional.empty());
    }

    @Test
    public void givenMeterReadingThatExistsShouldReturnMeterReadings() {
        meterReadingService.storeReadings("random-id", new ArrayList<>());
        assertThat(meterReadingService.getReadings("random-id")).isEqualTo(Optional.of(new ArrayList<>()));
    }
}
