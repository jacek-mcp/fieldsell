package com.jacek.fieldsell.dtos;

import com.jacek.fieldsell.models.AuthenticatedUser;

public record LoginResponseDTO (AuthenticatedUser user, String jwt) {

}
