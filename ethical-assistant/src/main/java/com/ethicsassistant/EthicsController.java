package com.ethicsassistant;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EthicsController {

    private final List<EthicsCase> dataset = DatasetLoader.loadDataset();

    @PostMapping("/checkEthics")
    public EthicsCase checkEthics(@RequestBody EthicsCase input) {
        return dataset.stream()
                .filter(caseItem -> caseItem.getScenario().equalsIgnoreCase(input.getScenario())
                        && caseItem.getFramework().equalsIgnoreCase(input.getFramework()))
                .findFirst()
                .orElseGet(() -> {
                    EthicsCase notFound = new EthicsCase();
                    notFound.setVerdict("Unknown");
                    notFound.setReasoning("Scenario not found in dataset. Please refine your input.");
                    return notFound;
                });
    }
}
