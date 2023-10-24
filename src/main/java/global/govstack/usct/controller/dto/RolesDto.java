package global.govstack.usct.controller.dto;

import java.util.List;

public record RolesDto(String email, String name, List<String> roles) {}
