package com.keyin.controller;

import com.keyin.entities.BinaryNode;
import com.keyin.entities.PreviousTreeDTO;
import com.keyin.entities.TreeDTO;
import com.keyin.logger.CustomLogger;
import com.keyin.service.BTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trees")
public class BTController {

    private final BTService btService;
    private final CustomLogger customLogger = new CustomLogger();

    @Autowired
    public BTController(BTService btService) {
        this.btService = btService;
    }

    @GetMapping("/enter-numbers")
    public String getEnterNumbersPage() {
        customLogger.logAction("GET /trees/enter-numbers");
        return "enter"; // Returns the enter.html template
    }

    @PostMapping("/process")
    public ResponseEntity<TreeDTO> processNumbers(@RequestBody List<Integer> numbers) {
        try {
            BinaryNode root = btService.constructBinarySearchTree(numbers);
            btService.saveUserInputAndTree(numbers, root);

            TreeDTO responseDTO = new TreeDTO();
            responseDTO.setInputValues(numbers);
            responseDTO.setRoot(root);

            customLogger.logAction("POST /trees/process");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            customLogger.logError("Error in POST /trees/process");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/previous")
    public ResponseEntity<List<PreviousTreeDTO>> getAllPreviousTrees() {
        try {
            List<PreviousTreeDTO> previousTrees = btService.getAllPreviousTrees();

            customLogger.logAction("GET /trees/previous");
            return ResponseEntity.ok(previousTrees);
        } catch (Exception e) {
            customLogger.logError("Error in GET /trees/previous");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
