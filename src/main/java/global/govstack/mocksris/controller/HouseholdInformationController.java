package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.HouseholdInformationDto;
import global.govstack.mocksris.model.HouseholdInformation;
import global.govstack.mocksris.service.HouseholdInformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HouseholdInformationController {

    private final HouseholdInformationService householdInformationService;

    public HouseholdInformationController(HouseholdInformationService householdInformationService) {
        this.householdInformationService = householdInformationService;
    }


    @GetMapping("/household-information")
    public List<HouseholdInformationDto> getAll() {
        return householdInformationService
                .findAll()
                .stream()
                .map(HouseholdInformationDto::new)
                .toList();
    }

    @GetMapping("/household-information/{id}")
    public HouseholdInformationDto getCandidate(@PathVariable("id") int id) {
        HouseholdInformation householdInformation = householdInformationService.findById(id);
        return new HouseholdInformationDto(householdInformation);
    }
}
