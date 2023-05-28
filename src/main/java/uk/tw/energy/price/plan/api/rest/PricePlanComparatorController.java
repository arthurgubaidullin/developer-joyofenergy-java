package uk.tw.energy.price.plan.api.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.tw.energy.price.plan.compare.CompareAll;
import uk.tw.energy.price.plan.recomend.RecommendPricePlans;

@RestController
@RequestMapping("/price-plans")
public class PricePlanComparatorController {

    public final static String PRICE_PLAN_ID_KEY = "pricePlanId";
    public final static String PRICE_PLAN_COMPARISONS_KEY = "pricePlanComparisons";

    private final CompareAll compareAllService;
    private final RecommendPricePlans recommendPricePlans;

    public PricePlanComparatorController(
            CompareAll compareAllService,
            RecommendPricePlans recommendPricePlans) {
        this.compareAllService = compareAllService;
        this.recommendPricePlans = recommendPricePlans;
    }

    @GetMapping("/compare-all/{smartMeterId}")
    public ResponseEntity<Map<String, Object>> calculatedCostForEachPricePlan(@PathVariable String smartMeterId) {

        Optional<Map<String, Object>> pricePlanComparisons = compareAllService
                .calculatedCostForEachPricePlan(smartMeterId);

        return pricePlanComparisons.isPresent()
                ? ResponseEntity.ok(pricePlanComparisons.get())
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/recommend/{smartMeterId}")
    public ResponseEntity<List<Map.Entry<String, BigDecimal>>> recommendCheapestPricePlans(
            @PathVariable String smartMeterId,
            @RequestParam(value = "limit", required = false) Integer limit) {

        Optional<List<Entry<String, BigDecimal>>> result = recommendPricePlans.recommendCheapestPricePlans(
                smartMeterId,
                limit);

        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
    }
}
