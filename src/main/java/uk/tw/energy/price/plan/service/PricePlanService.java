package uk.tw.energy.price.plan.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import uk.tw.energy.meter.reading.api.GetMeterReadingService;
import uk.tw.energy.meter.reading.dto.ElectricityReadingDto;
import uk.tw.energy.price.plan.calculate.CalculateCost;
import uk.tw.energy.price.plan.domain.PricePlan;

@Service
public class PricePlanService {

    private final List<PricePlan> pricePlans;
    private final GetMeterReadingService meterReadingService;

    public PricePlanService(List<PricePlan> pricePlans, GetMeterReadingService meterReadingService) {
        this.pricePlans = pricePlans;
        this.meterReadingService = meterReadingService;
    }

    public Optional<Map<String, BigDecimal>> getConsumptionCostOfElectricityReadingsForEachPricePlan(
            String smartMeterId) {
        Optional<List<ElectricityReadingDto>> electricityReadings = meterReadingService.getReadings(smartMeterId);

        if (!electricityReadings.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(pricePlans.stream().collect(
                Collectors.toMap(PricePlan::getPlanName,
                        t -> CalculateCost.calculateCost(electricityReadings.get(), t))));
    }

}
