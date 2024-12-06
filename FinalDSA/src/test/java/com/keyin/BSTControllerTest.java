package com.keyin;

import com.keyin.controller.BTController;
import com.keyin.entities.PreviousTreeDTO;
import com.keyin.entities.TreeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BSTControllerTest {

    @Autowired
    private BTController btController;

    @Test
    public void contextLoads() {
        assertThat(btController).isNotNull();
    }

    @Test
    public void testProcessNumbersEndpoint() {
        List<Integer> numbers = Arrays.asList(10, 5, 15, 3, 7);
        ResponseEntity<TreeDTO> response = btController.processNumbers(numbers);
        assertEquals(200, response.getStatusCodeValue());
        assertThat(response.getBody()).isNotNull();
        assertEquals(numbers, response.getBody().getInputValues());
        assertEquals(10, response.getBody().getRoot().getValue());
    }

    @Test
    public void testGetAllPreviousTreesEndpoint() {
        ResponseEntity<List<PreviousTreeDTO>> response = btController.getAllPreviousTrees();
        assertEquals(200, response.getStatusCodeValue());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isInstanceOf(List.class);
    }
}
