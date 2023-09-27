package global.govstack.mocksris.controller.dto;

import java.util.Set;

public record CreateCandidateDto(CreatePersonDto person, Set<PackageDto> packages) {}
