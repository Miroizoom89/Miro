package com.pap.zoo.service;

import com.pap.zoo.entity.Animal;
import com.pap.zoo.entity.Species;
import com.pap.zoo.repository.AnimalRepository;
import com.pap.zoo.repository.SpeciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;
    @InjectMocks
    private AnimalService underTest;
    @Mock
    private SpeciesRepository speciesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(underTest);
    }

    @Test
    void canSaveAnimal() {
        // given
        LocalDate date = LocalDate.now();
        Animal animal = new Animal("Riko", date, "seal");
        given(speciesRepository.findBySpecies(animal.getSpecies())).willReturn(new Species());

        // when
        underTest.save(animal);

        // then
        ArgumentCaptor<Animal> animalArgumentCaptor = ArgumentCaptor.forClass(Animal.class);
        verify(animalRepository).save(animalArgumentCaptor.capture());
        Animal capturedAnimal = animalArgumentCaptor.getValue();
        assertThat(capturedAnimal).isEqualTo(animal);
    }

    @Test
    void cannotSaveAnimalInvalidSpecies() {
        // given
        LocalDate date = LocalDate.now();
        Animal animal = new Animal("Riko", date, "seal");
        given(speciesRepository.findBySpecies(animal.getSpecies())).willReturn(null);

        // when
        underTest.save(animal);

        // then
        assertThat( underTest.save(animal)).isEqualTo(Pair.of(false,"Podany gatunek jest niepoprawny."));
    }

    @Test
    void canGetAnimals() {
        //when
        underTest.getAnimals();

        //then
        verify(animalRepository).findAll();
    }

    @Test
    void canGetAnimalById() {
        //given
        //when
        underTest.getAnimalById(1);

        //then
        verify(animalRepository).findById(1);
    }

    @Test
    void canUpdateAnimal() {
        //given
        int id = 1;
        LocalDate date = LocalDate.now();
        Animal animal = new Animal("Riko", date, "seal");
        animal.setId(id);
        given(speciesRepository.findBySpecies(animal.getSpecies())).willReturn(new Species());
        given(animalRepository.findById(animal.getId())).willReturn(java.util.Optional.of(animal));

        //when
        underTest.updateAnimal(animal);

        //then
        assertThat(underTest.updateAnimal(animal)).isEqualTo(Pair.of(true,""));
    }

    @Test
    void cannotUpdateAnimalInvalidSpecies() {
        //given
        int id = 1;
        LocalDate date = LocalDate.now();
        Animal animal = new Animal("Riko", date, "seal");
        animal.setId(id);

        //when
        underTest.updateAnimal(animal);

        //then
        assertThat(underTest.updateAnimal(animal)).isEqualTo(Pair.of(false,"Podany gatunek jest błędny."));

    }

    @Test
    void canDeleteAnimal() {
        //given
        int id = 1;
        LocalDate date = LocalDate.now();
        Animal animal = new Animal("Riko", date, "seal");
        animal.setId(id);
        given(animalRepository.findById(id)).willReturn(java.util.Optional.of(animal));

        //when
        underTest.delete(id);

        //then
        verify(animalRepository).deleteById(id);
    }

    @Test
    void cannotDeleteNotExistingAnimal() {
        //given
        int id = 1;

        //when
        underTest.delete(id);

        //then
        assertThat(underTest.delete(id)).isEqualTo(Pair.of(false,"Nie ma zwierzęcie o takim ID."));

    }
}