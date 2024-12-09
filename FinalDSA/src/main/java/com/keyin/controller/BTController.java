package com.keyin.controller;

import com.keyin.entities.BinaryNode;
import com.keyin.entities.PreviousTreeDTO;
import com.keyin.entities.TreeDTO;
import com.keyin.service.BTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trees")
public class BTController {

    private final BTService btService;

    @Autowired
    public BTController(BTService btService) {
        this.btService = btService;
    }


    @GetMapping("/enter-numbers")
    public String getEnterNumbersPage() {
        return "enter-numbers";
    }


    @PostMapping("/process")
    public ResponseEntity<TreeDTO> processNumbers(@RequestParam List<Integer> numbers) {
        try {
            BinaryNode root = btService.constructBinarySearchTree(numbers);
            btService.saveUserInputAndTree(numbers, root);

            TreeDTO responseDTO = new TreeDTO();
            responseDTO.setInputValues(numbers);
            responseDTO.setRoot(root);

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/previous")
    public ResponseEntity<List<PreviousTreeDTO>> getAllPreviousTrees() {
        try {
            List<PreviousTreeDTO> previousTrees = btService.getAllPreviousTrees();
            return ResponseEntity.ok(previousTrees);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred.");
        return "error";  // This will render 'error.html'
    }
}
