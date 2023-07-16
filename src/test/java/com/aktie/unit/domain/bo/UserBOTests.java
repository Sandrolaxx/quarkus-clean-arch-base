package com.aktie.unit.domain.bo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.aktie.domain.entities.UserBO;
import com.aktie.domain.entities.vo.UuidVO;
import com.aktie.domain.utils.exception.AktieException;

public class UserBOTests {

    @Test
    @DisplayName("Deve ser possível criar um UserBO informando campos válidos.")
    void mustBePossibleCreateUserBO() {
        var userBO = createUserBOMock(null);

        assertNotNull(userBO);
        assertEquals("Sandrolax Test", userBO.getName());
        assertEquals("10232434323", userBO.getDocument());
    }

    @Test
    @DisplayName("Deve lançar uma exception na tentativa de criar um UserBO sem document.")
    void exceptionWhenCreateUserBOWithIncorrectParamDocument() {
        assertThrows(AktieException.class, () -> {
            new UserBO(
                    new UuidVO(null),
                    "Sandrolax Test",
                    null,
                    null,
                    null,
                    null);
        });
    }

    @Test
    @DisplayName("Deve lançar uma exception na tentativa de criar um UserBO sem name.")
    void exceptionWhenCreateUserBOWithIncorrectParamName() {
        assertThrows(AktieException.class, () -> {
            new UserBO(
                    new UuidVO(null),
                    null,
                    "10232434323",
                    null,
                    null,
                    null);
        });
    }

    public static UserBO createUserBOMock(String uuid) {
        return new UserBO(
                new UuidVO(uuid),
                "Sandrolax Test",
                "10232434323",
                null,
                null,
                null);
    }

}
