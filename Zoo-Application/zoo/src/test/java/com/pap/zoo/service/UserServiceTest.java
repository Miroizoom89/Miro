package com.pap.zoo.service;

import com.pap.zoo.entity.User;
import com.pap.zoo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(underTest);
    }

    @Test
    void canSaveUserRoleUser() {
        // given
        User user = new User("len1234", "passTass", "USER");

        // when
        underTest.saveUser(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canSaveUserRoleAdmin() {
        // given
        User user = new User("len1234", "passTass", "ADMIN");

        // when
        underTest.saveUser(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void cannotSaveUserInvalidRole() {
        // given
        User user = new User("len1234", "passTass", "GUEST");

        // when
        underTest.saveUser(user);

        // then
        assertThat(underTest.saveUser(user)).isEqualTo(Pair.of(false,"Podana rola użytownika jest niepoprawna."));
    }

    @Test
    void canGetUsers() {
        //when
        underTest.getUsers();

        //then
        verify(userRepository).findAll();
    }

    @Test
    void canGetUserById() {
        //given
        //when
        underTest.getUserById(1);

        //then
        verify(userRepository).findById(1);
    }

    @Test
    void checkIfUserIsNotValid() {
        //it is impossible to check if a user is valid due to encoded pass
        //given
        String login = "len1234";

        //then
        Pair<Boolean, String> expected = Pair.of(false, "");
        assertThat(underTest.isValidUser(login, "pap")).isEqualTo(expected);
    }

    @Test
    void canUpdateUserWithAdminRole() {
        //given
        int id = 1;
        User user = new User("len1234", "passTass", "ADMIN");
        user.setId(id);
        given(userRepository.findById(id)).willReturn(java.util.Optional.of(user));

        //when
        underTest.updateUser(user);

        //then
        assertThat(underTest.updateUser(user)).isEqualTo(Pair.of(true,""));
    }

    @Test
    void canUpdateUserWithUserRole() {
        //given
        int id = 1;
        User user = new User("len1234", "passTass", "USER");
        user.setId(id);
        User existing = new User("leva", "butterfly", "USER");
        user.setId(id);
        given(userRepository.findById(id)).willReturn(java.util.Optional.of(existing));

        //when
        underTest.updateUser(user);

        //then
        assertThat(underTest.updateUser(user)).isEqualTo(Pair.of(true,""));
    }

    @Test
    void cannotUpdateUserWithInvalidRole() {
        //given
        int id = 1;
        User user = new User("len1234", "passTass", "GUEST");
        user.setId(id);
        given(userRepository.findById(id)).willReturn(java.util.Optional.of(user));

        //when
        underTest.updateUser(user);

        //then
        assertThat(underTest.updateUser(user)).isEqualTo(Pair.of(false,"Niepoprawna rola."));
    }

    @Test
    void cannotUpdateUserInvalidId() {
        //given
        int id = 1;
        User user = new User("len1234", "passTass", "ADMIN");
        user.setId(id);

        //when
        underTest.updateUser(user);

        //then
        assertThat(underTest.updateUser(user)).isEqualTo(Pair.of(false,"Nie odnalezionono takiego użytkownika."));
    }



    @Test
    void canDeleteUser() {
        //given
        int id = 1;
        User user = new User("len1234", "passTass", "USER");
        user.setId(id);
        given(userRepository.findById(id)).willReturn(java.util.Optional.of(user));

        //when
        underTest.deleteUser(id);

        //then
        verify(userRepository).deleteById(id);
    }

    @Test
    void cannotDeleteUserInvalidId() {
        //given
        int id = 1;
        User user = new User("len1234", "passTass", "USER");
        user.setId(id);

        //when
        underTest.deleteUser(id);

        //then
        assertThat(underTest.deleteUser(id)).isEqualTo(Pair.of(false,"Użytkownik o takim ID nie został znaleziony."));
    }
}