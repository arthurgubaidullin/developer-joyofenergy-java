package uk.tw.energy.meter.reading.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import uk.tw.energy.meter.reading.builder.MeterReadingsBuilder;
import uk.tw.energy.meter.reading.domain.ElectricityReading;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;
import uk.tw.energy.meter.reading.service.MeterReadingService;
import uk.tw.energy.meter.reading.store.MeterReadingsDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MeterReadingControllerTest {

    private static final String SMART_METER_ID = "10101010";
    private MeterReadingController meterReadingController;
    private MeterReadingService meterReadingService;
    private MeterReadingRepository meterReadingRepository;

    @BeforeEach
    public void setUp() {
        this.meterReadingRepository = new MeterReadingRepository(new HashMap<>());
        this.meterReadingService = new MeterReadingService(this.meterReadingRepository);
        this.meterReadingController = new MeterReadingController(meterReadingService);
    }

    @Test
    public void givenNoMeterIdIsSuppliedWhenStoringShouldReturnErrorResponse() {
        MeterReadingsDto meterReadings = new MeterReadingsDto(null, Collections.emptyList());
        assertThat(meterReadingController.storeReadings(meterReadings).getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void givenEmptyMeterReadingShouldReturnErrorResponse() {
        MeterReadingsDto meterReadings = new MeterReadingsDto(SMART_METER_ID, Collections.emptyList());
        assertThat(meterReadingController.storeReadings(meterReadings).getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void givenNullReadingsAreSuppliedWhenStoringShouldReturnErrorResponse() {
        MeterReadingsDto meterReadings = new MeterReadingsDto(SMART_METER_ID, null);
        assertThat(meterReadingController.storeReadings(meterReadings).getStatusCode())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void givenMultipleBatchesOfMeterReadingsShouldStore() {
        MeterReadingsDto meterReadings = new MeterReadingsBuilder().setSmartMeterId(SMART_METER_ID)
                .generateElectricityReadings()
                .build();

        MeterReadingsDto otherMeterReadings = new MeterReadingsBuilder().setSmartMeterId(SMART_METER_ID)
                .generateElectricityReadings()
                .build();

        meterReadingController.storeReadings(meterReadings);
        meterReadingController.storeReadings(otherMeterReadings);

        List<ElectricityReading> expectedElectricityReadings = new ArrayList<>();
        expectedElectricityReadings.addAll(meterReadings.getElectricityReadings());
        expectedElectricityReadings.addAll(otherMeterReadings.getElectricityReadings());

        assertThat(meterReadingService.getReadings(SMART_METER_ID).get()).isEqualTo(expectedElectricityReadings);
    }

    @Test
    public void givenMeterReadingsAssociatedWithTheUserShouldStoreAssociatedWithUser() {
        MeterReadingsDto meterReadings = new MeterReadingsBuilder().setSmartMeterId(SMART_METER_ID)
                .generateElectricityReadings()
                .build();

        MeterReadingsDto otherMeterReadings = new MeterReadingsBuilder().setSmartMeterId("00001")
                .generateElectricityReadings()
                .build();

        meterReadingController.storeReadings(meterReadings);
        meterReadingController.storeReadings(otherMeterReadings);

        assertThat(meterReadingService.getReadings(SMART_METER_ID).get())
                .isEqualTo(meterReadings.getElectricityReadings());
    }

    @Test
    public void givenMeterIdThatIsNotRecognisedShouldReturnNotFound() {
        assertThat(meterReadingController.readReadings(SMART_METER_ID).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
