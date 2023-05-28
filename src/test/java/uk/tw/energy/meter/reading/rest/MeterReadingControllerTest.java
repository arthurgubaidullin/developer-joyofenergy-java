package uk.tw.energy.meter.reading.rest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import uk.tw.energy.meter.reading.api.GetMeterReadingService;
import uk.tw.energy.meter.reading.api.StoreMeterReadingService;
import uk.tw.energy.meter.reading.builder.MeterReadingsBuilder;
import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;
import uk.tw.energy.meter.reading.dto.MeterReadingsDto;
import uk.tw.energy.meter.reading.get.GetMeterReading;
import uk.tw.energy.meter.reading.repository.MeterReadingRepository;
import uk.tw.energy.meter.reading.store.StoreMeterReading;

public class MeterReadingControllerTest {

    private static final String SMART_METER_ID = "10101010";
    private MeterReadingController meterReadingController;
    private MeterReadingRepository meterReadingRepository;
    private GetMeterReadingService getMeterReading;
    private StoreMeterReadingService storeMeterReading;

    @BeforeEach
    public void setUp() {
        this.meterReadingRepository = new MeterReadingRepository(new HashMap<>());
        this.getMeterReading = new GetMeterReading(this.meterReadingRepository);
        this.storeMeterReading = new StoreMeterReading(this.meterReadingRepository);
        this.meterReadingController = new MeterReadingController(this.storeMeterReading, this.getMeterReading);
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

        List<ElectricityReadingDto> expectedElectricityReadings = new ArrayList<>();
        expectedElectricityReadings.addAll(meterReadings.getElectricityReadings());
        expectedElectricityReadings.addAll(otherMeterReadings.getElectricityReadings());

        assertThat(getMeterReading.getReadings(SMART_METER_ID).get()).isEqualTo(expectedElectricityReadings);
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

        assertThat(getMeterReading.getReadings(SMART_METER_ID).get())
                .isEqualTo(meterReadings.getElectricityReadings());
    }

    @Test
    public void givenMeterIdThatIsNotRecognisedShouldReturnNotFound() {
        assertThat(meterReadingController.readReadings(SMART_METER_ID).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
