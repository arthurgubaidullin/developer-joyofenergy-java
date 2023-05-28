package uk.tw.energy.price.plan.recomend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uk.tw.energy.price.plan.get.GetConsumptionCost;

@Service
public class RecommendPricePlans {

    private final GetConsumptionCost pricePlanService;

    public RecommendPricePlans(GetConsumptionCost pricePlanService) {
        this.pricePlanService = pricePlanService;
    }

    public Optional<List<Map.Entry<String, BigDecimal>>> recommendCheapestPricePlans(
            String smartMeterId,
            Integer limit) {
        Optional<Map<String, BigDecimal>> consumptionsForPricePlans = pricePlanService
                .getConsumptionCostOfElectricityReadingsForEachPricePlan(smartMeterId);

        if (!consumptionsForPricePlans.isPresent()) {
            return Optional.empty();
        }

        List<Map.Entry<String, BigDecimal>> recommendations = new ArrayList<>(
                consumptionsForPricePlans.get().entrySet());
        recommendations.sort(Comparator.comparing(Map.Entry::getValue));

        if (limit != null && limit < recommendations.size()) {
            recommendations = recommendations.subList(0, limit);
        }

        return Optional.of(recommendations);
    }
}
