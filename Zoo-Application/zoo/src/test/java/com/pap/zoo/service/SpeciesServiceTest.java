package com.pap.zoo.service;

import com.pap.zoo.entity.Animal;
import com.pap.zoo.entity.Species;
import com.pap.zoo.repository.AnimalRepository;
import com.pap.zoo.repository.SpeciesRepository;
import com.pap.zoo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SpeciesServiceTest {

    @Mock
    private SpeciesRepository speciesRepository;
    @InjectMocks
    private SpeciesService underTest;
    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(underTest);
    }

    @Test
    void canSaveSpecies() {
        // given
        Time time = new Time(12);
        Species species = new Species("A12", time, "Very dangerous.", "lion");

        // when
        underTest.saveSpecies(species);

        // then
        ArgumentCaptor<Species> speciesArgumentCaptor = ArgumentCaptor.forClass(Species.class);
        verify(speciesRepository).save(speciesArgumentCaptor.capture());
        Species capturedSpecies = speciesArgumentCaptor.getValue();
        assertThat(capturedSpecies).isEqualTo(species);
    }

    @Test
    void cannotSaveSpeciesSpeciesAlreadyExists() {
        // given
        Time time = new Time(12);
        Species species = new Species("A12", time, "Very dangerous.", "lion");
        given(speciesRepository.findBySpecies(species.getSpecies())).willReturn(new Species());

        // when
        underTest.saveSpecies(species);

        // then
        assertThat( underTest.saveSpecies(species)).isEqualTo(Pair.of(false,"Ten gatunek już istnieje."));
    }

    @Test
    void cannotSaveSpeciesCageAlreadyTaken() {
        // given
        Time time = new Time(12);
        Species species = new Species("A12", time, "Very dangerous.", "lion");
        given(speciesRepository.findByCage(species.getCage())).willReturn(new Species());

        // when
        underTest.saveSpecies(species);

        // then
        assertThat( underTest.saveSpecies(species)).isEqualTo(Pair.of(false,"Klatka jest zajęta przez inny gatunek."));
    }

//    @Test
//    @Disabled
//    void createFeedingTasks() {
//        //given
//        Task task = new Task("Clean the cage", "B4", "len", LocalDateTime.now(), Time.valueOf(LocalTime.now()));
////        given(speciesRepository.findAll()).willReturn(new ArrayList<Species>((Collection<? extends Species>) new Species()));
//
//        //when
//        underTest.createFeedingTasks();
//
//        //then
////        verify(taskRepository).save(task);
//        assertThat(underTest.createFeedingTasks()).isEqualTo(Pair.of(false,"Klatka jest zajęta przez inny gatunek."));
//    }

    @Test
    void canGetSpecies() {
        //when
        underTest.getSpecies();

        //then
        verify(speciesRepository).findAll();
    }

    @Test
    void canGetSpeciesById() {
        //given
        Species species = new Species("A13", Time.valueOf(LocalTime.now()), "loves to eat", "seal");
        species.setId(1);

        //when
        underTest.getSpeciesById(1);

        //then
        verify(speciesRepository).findById(1);
    }

    @Test
    void cannotGetSpeciesByIdInvalidId() {
        //given
        Species species = new Species("A13", Time.valueOf(LocalTime.now()), "loves to eat", "seal");
        species.setId(1);

        //when
        underTest.getSpeciesById(10);

        //then
        verify(speciesRepository).findById(10);
        assertThat(underTest.getSpeciesById(10)).isEqualTo(null);
    }

    @Test
    void canGetSpeciesByCage() {
        //given
        String cage = "A2";

        //when
        underTest.getSpeciesByCage(cage);

        //then
        verify(speciesRepository).findByCage(cage);
    }

    @Test
    void cannotGetSpeciesByCageInvalidCage() {
        //given
        //when
        underTest.getSpeciesByCage("B12");

        //then
        verify(speciesRepository).findByCage("B12");
        assertThat(underTest.getSpeciesByCage("B12")).isEqualTo(null);
    }

    @Test
    void canGetAnimalBySpecies() {
        //given
        String speciesName = "seal";

        //when
        underTest.getAnimalBySpecies(speciesName);

        //then
        verify(speciesRepository).findBySpecies(speciesName);
    }

    @Test
    void canGetSpeciesNames() {
        //when
        underTest.getSpeciesNames();

        //then
        verify(speciesRepository).findAll();
    }

    @Test
    void getSpeciesCages() {
        //when
        underTest.getSpeciesCages();

        //then
        verify(speciesRepository).findAll();
    }

    @Test
    void canUpdateSpecies() {
        //given
        int id = 1;
        Species species = new Species("A2", Time.valueOf(LocalTime.now()), "Very dangerous", "tiger");
        species.setId(id);
        given(speciesRepository.findById(species.getId())).willReturn(java.util.Optional.of(species));

        //when
        underTest.updateSpecies(species);

        //then
        assertThat(underTest.updateSpecies(species)).isEqualTo(Pair.of(true,""));
    }

//    @Test
//    @Disabled
//    void cannotUpdateSpeciesCauseItAlreadyExists() {
//        //given
//        int id = 1;
//        Species species = new Species("A2", Time.valueOf(LocalTime.now()), "Very dangerous", "tiger");
//        species.setId(id);
////        given(speciesRepository.findBySpecies(animal.getSpecies())).willReturn(new Species());
//        given(speciesRepository.findById(species.getId())).willReturn(java.util.Optional.of(null));
//
//        //when
//        underTest.updateSpecies(species);
//        System.out.println(underTest.updateSpecies(species));
//
//        //then
////        assertThat(underTest.updateSpecies(species)).isEqualTo(Pair.of(false,"Ten gatunek już istnieje."));
//        assertThatThrownBy(() -> underTest.updateSpecies(species))
//                .isInstanceOf(NullPointerException.class);
//
////        verify(studentRepository, never()).save(any());
//
//    }

    @Test
    void canDeleteSpecies() {
        //given
        int id = 1;
        Species species = new Species("A2", Time.valueOf(LocalTime.now()), "Very dangerous", "tiger");
        species.setId(id);
        given(speciesRepository.findById(id)).willReturn(java.util.Optional.of(species));
        given(animalRepository.findBySpecies(species.getSpecies())).willReturn(Collections.emptyList());
        given(taskRepository.findBySpeciesId(species.getId())).willReturn(Collections.emptyList());

        //when
        underTest.deleteSpecies(id);

        //then
        verify(speciesRepository).deleteById(id);
    }

    @Test
    void cannotDeleteSpeciesThatAlreadyExists() {
        //given
        int id = 1;
        Species species = new Species("A2", Time.valueOf(LocalTime.now()), "Very dangerous", "tiger");
        species.setId(id);
        LocalDate date = LocalDate.now();
        Animal animal = new Animal("Riko", date, "seal");
        animal.setId(id);
        given(speciesRepository.findById(id)).willReturn(java.util.Optional.of(species));
        given(animalRepository.findBySpecies(Objects.requireNonNull(speciesRepository.findById(id).orElse(null)).getSpecies())).willReturn(List.of(animal));

        //when
        underTest.deleteSpecies(id);

        //then
        assertThat(underTest.deleteSpecies(id)).isEqualTo(Pair.of(false, "Nie można usunąc tego gatunku, istnieją zwierzęta tego gatunku."));
    }

    @Test
    void cannotDeleteSpeciesInvalidId() {
        //given
        int id = 1;
        Species species = new Species("A2", Time.valueOf(LocalTime.now()), "Very dangerous", "tiger");
        species.setId(id);

        //when
        underTest.deleteSpecies(id);

        //then
        assertThat(underTest.deleteSpecies(id)).isEqualTo(Pair.of(false, "Gatunek o takim ID nie istnieje."));

    }
}