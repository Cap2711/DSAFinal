package com.keyin;

import com.keyin.entities.BinaryNode;
import com.keyin.entities.PreviousTreeDTO;
import com.keyin.service.BTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BinaryTreeServiceTest {

    @Autowired
    private BTService btService;

    @Test
    public void testConstructBinarySearchTree() {
        BinaryNode root = btService.constructBinarySearchTree(Arrays.asList(10, 5, 15, 3, 7));
        assertNotNull(root, "Root should not be null");
        assertEquals(10, root.getValue());
        assertNotNull(root.getLeft(), "Left child should not be null");
        assertEquals(5, root.getLeft().getValue());
        assertNotNull(root.getRight(), "Right child should not be null");
        assertEquals(15, root.getRight().getValue());
        assertNotNull(root.getLeft().getLeft(), "Left-Left child should not be null");
        assertEquals(3, root.getLeft().getLeft().getValue());
        assertNotNull(root.getLeft().getRight(), "Left-Right child should not be null");
        assertEquals(7, root.getLeft().getRight().getValue());
    }

    @Test
    public void testSaveUserInputAndTree() {
        BinaryNode root = btService.constructBinarySearchTree(Arrays.asList(10, 5, 15));
        btService.saveUserInputAndTree(Arrays.asList(10, 5, 15), root);

        List<PreviousTreeDTO> previousTrees = btService.getAllPreviousTrees();
        assertFalse(previousTrees.isEmpty(), "Previous trees list should not be empty");
    }

}
