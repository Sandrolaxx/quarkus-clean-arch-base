package com.aktie.utils;

import com.aktie.presentation.dto.UserDTO;

/**
 *
 * @author SRamos
 */
public class ValidatorUtil {
    
    public static void verifyUserDto(UserDTO userDTO) throws Exception {
        
        if (StringUtil.isNullOrEmpty(userDTO.getDocument())) {
            throw new Exception("Documento do usuário inválido");
        }

        if (StringUtil.isNullOrEmpty(userDTO.getName())) {
            throw new Exception("Documento do usuário inválido");
        }

    }

}
