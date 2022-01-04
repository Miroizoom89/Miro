package com.pap.zoo.service;

import com.pap.zoo.entity.Species;
import com.pap.zoo.entity.Task;
import com.pap.zoo.entity.ZooKeeper;
import com.pap.zoo.repository.SpeciesRepository;
import com.pap.zoo.repository.TaskRepository;
import com.pap.zoo.repository.ZooKeeperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService underTest;
    @Mock
    private ZooKeeperRepository zooKeeperRepository;
    @Mock
    private SpeciesRepository speciesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(underTest);
    }

//    @Test
//    @Disabled
//    void saveTask() {
//        // given
//        Task task = new Task("Clean the cage", "B4", "len", LocalDateTime.now(), Time.valueOf(LocalTime.now()));
//        User user = new User("len", "passlas", "USER");
//        ZooKeeper zooKeeper = new ZooKeeper("Lens", "Lawryn", "len");
//        Time time = new Time(12);
//        Species species = new Species("A12", time, "Very dangerous.", "lion");
//        task.setSpecies(species);
//        user.setZooKeeper(zooKeeper);
//        given(speciesRepository.findByCage(task.getCage())).willReturn(new Species());
//        given(zooKeeperRepository.findByLogin(task.getLogin())).willReturn(zooKeeper);
//        given(userRepository.findByLogin(task.getLogin())).willReturn(user);
//        given(animalRepository.findBySpecies(task.getSpecies().getSpecies())).willReturn(List.of(new Animal()));
//
//        // when
//        underTest.saveTask(task);
//        System.out.println(underTest.saveTask(task));
//
//        // then
//        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
//        verify(taskRepository).save(taskArgumentCaptor.capture());
//        Task capturedTask = taskArgumentCaptor.getValue();
//        assertThat(capturedTask).isEqualTo(task);
//    }

    @Test
    void cannotSaveTaskInvalidCage() {
        // given
        Task task = new Task("Clean the cage", "B4", "len", LocalDateTime.now(), Time.valueOf(LocalTime.now()));

        // when
        underTest.saveTask(task);
        System.out.println(underTest.saveTask(task));

        // then
        assertThat(underTest.saveTask(task)).isEqualTo(Pair.of(false, "Podana klataka jest nieprawidłowa., dostępni użytkownicy = "));
    }

    @Test
    void cannotSaveTaskInvalidLogin() {
        // given
        Task task = new Task("Clean the cage", "B4", "len", LocalDateTime.now(), Time.valueOf(LocalTime.now()));
        given(speciesRepository.findByCage(task.getCage())).willReturn(new Species());

        // when
        underTest.saveTask(task);

        // then
        assertThat(underTest.saveTask(task)).isEqualTo(Pair.of(false, "Podany login jest nieprawidłowy, dostępni użytkownicy = "));
    }

//    @Test
//    @Disabled
//    void chekIfZooKeeperIsAvailable() {
//        //given
//        String login = "len1234";
//        Task firstTask = new Task("Clean the cage", "B4", null, LocalDateTime.now(), Time.valueOf(LocalTime.now()));
////        Task secondTask = new Task("Feed animals", "A4", null, LocalDateTime.now(), Time.valueOf(LocalTime.now()));
//
//        underTest.chekIfZooKeeperIsAvailable(login, firstTask);
//
//
////        assertThatThrownBy(() -> underTest.chekIfZooKeeperIsAvailable(login, new Task("Feed animals", "A4", null, LocalDateTime.now(), Time.valueOf(LocalTime.now()))))
////                .isInstanceOf(SchemaManagementException.class)
////                .hasMessageContaining("Opiekun jest zajęty w tym czasie");
//
////        verify(taskRepository, never()).save(any());
//
//    }

    @Test
    void listOfAvailableZooKeepersWhenEveryoneOccupied() {
        //given
        ZooKeeper firstZooKeeper = new ZooKeeper("Len", "Walczi", "len");
        ZooKeeper secondZooKeeper = new ZooKeeper("Jean", "Fleur", "jean");
        ZooKeeper thirdZooKeeper = new ZooKeeper("Joseph", "Conrad", "joseph");

        Task firstTask = new Task("Clean the cage", "B4", "len", LocalDateTime.now(), Time.valueOf(LocalTime.now()));
        Task secondTask = new Task("Clean the cage", "B4", "jean", LocalDateTime.now(), Time.valueOf(LocalTime.now()));
        Task thirdTask = new Task("Clean the cage", "B4", "joseph", LocalDateTime.now(), Time.valueOf(LocalTime.now()));

        zooKeeperRepository.save(firstZooKeeper);
        zooKeeperRepository.save(secondZooKeeper);
        zooKeeperRepository.save(thirdZooKeeper);

        underTest.saveTask(firstTask);
        underTest.saveTask(secondTask);
        underTest.saveTask(thirdTask);
        //when
        //then
        assertThat(underTest.listOfAvailableZooKeepers(firstTask)).isEqualTo(List.of());
    }

//    @Test
//    @Disabled
//    void listOfAvailableZooKeepersWhenNotEveryoneOccupied() {
//        //given
//        Species species =  new Species("B4", Time.valueOf(LocalTime.now()), "loves to eat", "seal");
//
//        User firstUser = new User("len", "pass", "USER");
//        User secondUser = new User("jean", "pass", "USER");
//        User thirdUser = new User("joseph", "pass", "USER");
//
//        ZooKeeper firstZooKeeper = new ZooKeeper("Len", "Walczi", "len");
//        ZooKeeper secondZooKeeper = new ZooKeeper("Jean", "Fleur", "jean");
//        ZooKeeper thirdZooKeeper = new ZooKeeper("Joseph", "Conrad", "joseph");
//
//        Task task = new Task("Clean the cage", "B4", "len", LocalDateTime.now(), Time.valueOf(LocalTime.now()));
//
//        speciesRepository.save(species);
//
//        userRepository.save(firstUser);
//        userRepository.save(secondUser);
//        userRepository.save(thirdUser);
//
//        zooKeeperRepository.save(firstZooKeeper);
//        zooKeeperRepository.save(secondZooKeeper);
//        zooKeeperRepository.save(thirdZooKeeper);
//
//        underTest.saveTask(task);
//        //when
//        //then
//        assertThat(underTest.listOfAvailableZooKeepers(task)).isEqualTo(List.of("jean","joseph"));
//    }

//    @Test
//    @Disabled
//    void listWithoutBrackets() {
//    }

    @Test
    void getTasks() {
        //when
        underTest.getTasks();
        //then
        verify(taskRepository).findAll();
    }

    @Test
    void getTaskById() {
        //given
        //when
        underTest.getTaskById(1);

        //then
        verify(taskRepository).findById(1);
    }

    @Test
    void canGetTaskByLogin() {
        //given
        String login = "len1234";

        //when
        underTest.getTaskByLogin(login);

        //then
        verify(taskRepository).findByLogin(login);
    }

//    @Test
//    @Disabled
//    void updateTask() {
//    }

//    @Test
//    @Disabled
//    void deleteTask() {
//    }
}