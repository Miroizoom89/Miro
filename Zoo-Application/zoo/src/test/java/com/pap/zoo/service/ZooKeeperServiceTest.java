package com.pap.zoo.service;

import com.pap.zoo.entity.User;
import com.pap.zoo.entity.ZooKeeper;
import com.pap.zoo.repository.UserRepository;
import com.pap.zoo.repository.ZooKeeperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ZooKeeperServiceTest {

    @Mock
    private ZooKeeperRepository zooKeeperRepository;
    @InjectMocks
    private ZooKeeperService underTest;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(underTest);
    }

    @Test
    void canSaveZooKeeper() {
        // given
        ZooKeeper zooKeeper = new ZooKeeper("Lens", "Lawryn", "len");
        given(userRepository.findByLogin(zooKeeper.getLogin())).willReturn(new User());

        // when
        underTest.saveZooKeeper(zooKeeper);

        // then
        ArgumentCaptor<ZooKeeper> zooKeeperArgumentCaptor = ArgumentCaptor.forClass(ZooKeeper.class);
        verify(zooKeeperRepository).save(zooKeeperArgumentCaptor.capture());
        ZooKeeper capturedZooKeeper = zooKeeperArgumentCaptor.getValue();
        assertThat(capturedZooKeeper).isEqualTo(zooKeeper);
    }

    @Test
    void cannotSaveZooKeeperNoAccountWithSuchLogin() {
        // given
        ZooKeeper zooKeeper = new ZooKeeper("Lens", "Lawryn", "len");

        // when
        underTest.saveZooKeeper(zooKeeper);

        // then
        assertThat(underTest.saveZooKeeper(zooKeeper)).isEqualTo(Pair.of(false, "Nie można dodać pracownika, nie ma konta o podanym loginie."));
    }

    @Test
    void canGetZooKeepers() {
        //when
        underTest.getZooKeepers();

        //then
        verify(zooKeeperRepository).findAll();
    }

    @Test
    void getZooKeeperById() {
        //given
        int id = 2;
        ZooKeeper zooKeeper = new ZooKeeper("Len", "Walczi", "len");
        zooKeeper.setId(id);

        //when
        underTest.getZooKeeperById(id);

        //then
        verify(zooKeeperRepository).findById(id);
    }

    @Test
    void cannotGetZooKeepersByIdInvalidId() {
        //given
        int id = 2;
        ZooKeeper zooKeeper = new ZooKeeper("Len", "Walczi", "len");
        zooKeeper.setId(id);

        //when
        underTest.getZooKeeperById(10);

        //then
        assertThat(underTest.getZooKeeperById(10)).isEqualTo(null);
    }

    @Test
    void canGetZooKeepersLoginsNoUsers() {
        //given
        //when
        //then
        assertThat(underTest.getZooKeepersLogins()).isEqualTo(List.of());
    }

    @Test
    void canGetZooKeepersLogins() {
        //given
        String login = "len";
        ZooKeeper zooKeeper = new ZooKeeper("Len", "Walczi", login);
        User user = new User(login, "passlass", "USER");
        given(zooKeeperRepository.findAll()).willReturn(List.of(zooKeeper));
        given(userRepository.findByLogin(zooKeeper.getLogin())).willReturn(user);

        //when
        underTest.saveZooKeeper(zooKeeper);

        //then
        assertThat(underTest.getZooKeepersLogins()).isEqualTo(List.of(zooKeeper.getLogin()));
    }

    @Test
    void canUpdateZooKeeper() {
        //given
        int id = 1;
        ZooKeeper zooKeeper = new ZooKeeper("Lens", "Walczi", "len");
        zooKeeper.setId(id);
        given(zooKeeperRepository.findById(id)).willReturn(java.util.Optional.of(zooKeeper));

        //when
        underTest.updateZooKeeper(zooKeeper);

        //then
        assertThat(underTest.updateZooKeeper(zooKeeper)).isEqualTo(Pair.of(true,""));
    }

    @Test
    void cannotUpdateZooKeeperInvalidId() {
        //given
        int id = 1;
        ZooKeeper zooKeeper = new ZooKeeper("Lens", "Walczi", "len");
        zooKeeper.setId(id);
        given(zooKeeperRepository.findById(id)).willReturn(null);

        //when
        underTest.updateZooKeeper(zooKeeper);

        //then
        assertThat(underTest.updateZooKeeper(zooKeeper)).isEqualTo(Pair.of(false,"Podano niepoprawny login."));
    }

    @Test
    void canDeleteZooKeeper() {
        //given
        int id = 1;
        ZooKeeper zooKeeper = new ZooKeeper("Lens", "Walczi", "len");
        zooKeeper.setId(id);

        //when
        underTest.deleteZooKeeper(id);

        //then
        verify(zooKeeperRepository).deleteById(id);
    }
}