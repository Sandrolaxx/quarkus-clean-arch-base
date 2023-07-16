package com.aktie.unit.domain.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.aktie.domain.entities.dto.UserDTO;
import com.aktie.domain.entities.mappers.UserMapper;
import com.aktie.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.domain.useCases.CreateUser;
import com.aktie.domain.useCases.DisableUser;
import com.aktie.domain.useCases.FindUserBy;
import com.aktie.domain.useCases.ListUsersBy;
import com.aktie.domain.useCases.UpdateUserInfo;
import com.aktie.domain.utils.exception.AktieException;
import com.aktie.unit.domain.bo.UserBOTests;
import com.aktie.unit.infra.InMemoryUserRepository;

public class UserUseCases {

    InMemoryUserRepository memoryRepo = new InMemoryUserRepository();

    private void setupOpenBankAccount(String uuid) {
        final var createUser = new CreateUser(memoryRepo);

        createUser.execute(UserMapper.toDTO(UserBOTests.createUserBOMock(uuid)));
    }

    @Test
    @DisplayName("Deve ser possível persistir um User informando campos validos.")
    void mustBePossiblePersistUserWithValidInfo() {
        setupOpenBankAccount(null);
    }

    @Test
    @DisplayName("Deve ser lançada uma exceção ao tentar persistir um user duas vezes.")
    void mustBeThrowWhenTryPersistAnExitingUser() {
        var targetUUID = UUID.randomUUID();

        assertThrows(AktieException.class, () -> {
            setupOpenBankAccount(targetUUID.toString());
            setupOpenBankAccount(targetUUID.toString());
        });
    }

    @Test
    @DisplayName("Deve ser possível localizar uma user pelo id.")
    void mustBePossibleLocateUserWithIdError() {
        var targetUUID = UUID.randomUUID();
        setupOpenBankAccount(targetUUID.toString());

        final var findUserBy = new FindUserBy(memoryRepo);

        var user = findUserBy.execute(List.of(new QueryFieldInfoVO("id", targetUUID)), true);

        assertEquals(targetUUID.toString(), user.getId());
    }

    @Test
    @DisplayName("Deve ser lançada uma exceção ao tentar buscar um user inexistente")
    void mustBeThrowWhenFindWithIncorretId() {
        assertThrows(AktieException.class, () -> {
            final var findUserBy = new FindUserBy(memoryRepo);

            findUserBy.execute(List.of(new QueryFieldInfoVO("id", UUID.randomUUID())), true);
        });
    }

    @Test
    @DisplayName("Deve ser possível listar users pelo documento.")
    void mustBePossibleLocateUserWithId() {
        setupOpenBankAccount(null);

        final var listUsersBy = new ListUsersBy(memoryRepo);

        var users = listUsersBy.execute(List.of(new QueryFieldInfoVO("document", "10232434323")), true);

        assertEquals(1, users.size());
    }

    @Test
    @DisplayName("Deve ser lançada uma exceção ao tentar listar users inexistentes")
    void mustBeThrowWhenFindUsersWithIncorretDocument() {
        assertThrows(AktieException.class, () -> {
            final var listUsersBy = new ListUsersBy(memoryRepo);

            listUsersBy.execute(List.of(new QueryFieldInfoVO("document", "1111111")), true);
        });
    }

    @Test
    @DisplayName("Deve ser possível atualizar um user.")
    void mustBePossibleUpdateUserInfo() {
        var targetUUID = UUID.randomUUID();
        setupOpenBankAccount(targetUUID.toString());

        final var updateUserInfo = new UpdateUserInfo(memoryRepo);
        final var newUserInfo = new UserDTO();
        var newDoc = "23123132123";
        var newName = "23123132123";

        newUserInfo.setDocument(newDoc);
        newUserInfo.setName(newName);

        var updatedUser = updateUserInfo.execute(newUserInfo, targetUUID.toString());

        assertNotNull(updatedUser);
        assertEquals(newDoc, updatedUser.getDocument());
        assertEquals(newName, updatedUser.getName());
    }

    @Test
    @DisplayName("Deve ser possível desabilitar um user.")
    void mustBePossibleDisableUser() {
        var targetUUID = UUID.randomUUID();
        setupOpenBankAccount(targetUUID.toString());

        final var disableUser = new DisableUser(memoryRepo);

        var disabledUser = disableUser.execute(targetUUID.toString());

        assertNotNull(disabledUser.getDisabledAt());
    }

}
