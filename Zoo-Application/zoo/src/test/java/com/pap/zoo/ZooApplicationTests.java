package com.pap.zoo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ZooApplicationTests {

    final Calculator underTest = new Calculator();

    @Test
    void itShouldAddTwoNumbers() {
        //given
        int numberOne = 25;
        int numberTwo = 34;

        //when
        int result = underTest.add(numberOne, numberTwo);

        //then
        int expected = 59;
        assertThat(result).isEqualTo(expected);
    }

    static class Calculator {
        int add(int a, int b) {return a + b;}
    }

}
