package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.*;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.Package;
import global.govstack.mocksris.model.Person;
import global.govstack.mocksris.service.BeneficiaryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class BeneficiaryController {

    private final BeneficiaryService service;

    private final ModelMapper modelMapper;


    public BeneficiaryController(BeneficiaryService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/beneficiaries")
    public List<BeneficiaryDto> getAll() {
        return service
                .findAll()
                .stream()
                .map(BeneficiaryDto::new)
                .toList();
    }

    @GetMapping("/beneficiaries/{id}")
    public BeneficiaryDto getBeneficiary(@PathVariable("id") int id) {
        Beneficiary beneficiary = service.findById(id);
        return new BeneficiaryDto(beneficiary);
    }

    @PostMapping(value = "/beneficiaries")
    @ResponseStatus(HttpStatus.CREATED)
    public BeneficiaryDto create(@RequestBody final CreateBeneficiaryDto createBeneficiaryDto) {

        Person person = convertToEntity(createBeneficiaryDto.getPerson());
        Package enroledPackage = convertToEntity(createBeneficiaryDto.getEnrolledPackage());
        Beneficiary beneficiary = service.create(person, enroledPackage);
        return convertToDto(beneficiary);
    }

    private Person convertToEntity(PersonDto personDto)  {
        return modelMapper.map(personDto, Person.class);
    }

    private Package convertToEntity(PackageDto packageDto)  {
        return modelMapper.map(packageDto, Package.class);
    }

    private BeneficiaryDto convertToDto(Beneficiary beneficiary) {
        return modelMapper.map(beneficiary, BeneficiaryDto.class);
    }
}
