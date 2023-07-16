package com.aktie.unit.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.useCases.CreateUser;
import com.aktie.domain.useCases.FindUserBy;
import com.aktie.unit.domain.bo.UserBOTest;
import com.aktie.unit.infra.InMemoryUserRepository;

public class CreateUserUseCase {

    InMemoryUserRepository memoryRepo = new InMemoryUserRepository();

    private void setupOpenBankAccount(String uuid) {
        final var createUser = new CreateUser(memoryRepo);

        createUser.execute(UserMapper.toDTO(UserBOTest.createUserBOMock(uuid)));
    }

    @Test
    @DisplayName("Deve ser possível persistir um User informando campos validos.")
    void mustBePossiblePersistUserWithValidInfo() throws Exception {
        setupOpenBankAccount(null);
    }

    @Test
    @DisplayName("Deve ser possível localizar uma conta informando uma chave pix.")
    void mustBePossibleLocateAccountWithPixKey() {
        var targetUUID = UUID.randomUUID().toString();
        setupOpenBankAccount(targetUUID);

        final var findUserBy = new FindUserBy(memoryRepo);

        var user = findUserBy.execute(List.of(new QueryFieldInfoVO("id", targetUUID)), false);

        assertEquals(targetUUID, user.getId());
    }

}
