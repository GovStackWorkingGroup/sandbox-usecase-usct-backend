package global.govstack.usct.controller.dto;

import java.util.Set;

public record CreateCandidateDto(CreatePersonDto person, Set<Integer> packageIds) {}
