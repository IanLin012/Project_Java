package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main m;

    @BeforeAll
    static void init_all() {
        System.out.println("Before all");
    }

    @BeforeEach
    void init() {
        m = new Main();
        System.out.println("m is created");
    }

    @Test
    void add_pp() {
        int x=10, y=20;
        int expectResult = 30;
        int realResult = m.add(x, y);

        assertEquals(expectResult, realResult);
    }
    @Test
    void add_nn() {
        int x=-10, y=-20;
        int expectResult = -30;
        int realResult = m.add(x, y);

        assertEquals(expectResult, realResult);
    }
    @Test
    void add_pn() {
        int x=10, y=-20;
        int expectResult = -10;
        int realResult = m.add(x, y);

        assertEquals(expectResult, realResult);
    }
    @Test
    void add_np() {
        int x=-10, y=20;
        int expectResult = 10;
        int realResult = m.add(x, y);

        assertEquals(expectResult, realResult);
    }

    @Test
    void arr_add() {
        int data[] = {0, 1, 2, 3, 4};
        int expectResult = 10;
        int realResult = m.add(data);
        assertEquals(expectResult, realResult);
    }

    @Test
    void arr_div() {
        int data1[] = {0, 5, 10, 15, 20};
        int data2[] = {5, 5, 5, 5, 5};
        int expectResult = 10;
        int realResult = m.div(data1, data2);

        assertEquals(expectResult, realResult);
    }
}