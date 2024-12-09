package com.keyin.service;

import com.keyin.entities.BinaryNode;
import com.keyin.entities.PreviousTreeDTO;
import com.keyin.entities.UserInput;
import com.keyin.logger.CustomLogger;
import com.keyin.repository.BinaryRestRepository;
import com.keyin.repository.UserInputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BTService {

    @Value("${app.debug}")
    private boolean debugMode;

    private final BinaryRestRepository binaryNodeRepository;
    private final UserInputRepository userInputRepository;
    private final CustomLogger customLogger = new CustomLogger();

    @Autowired
    public BTService(BinaryRestRepository binaryNodeRepository, UserInputRepository userInputRepository) {
        this.binaryNodeRepository = binaryNodeRepository;
        this.userInputRepository = userInputRepository;
    }

    public BinaryNode constructBinarySearchTree(List<Integer> numbers) {
        BinaryNode root = null;
        try {
            for (int num : numbers) {
                root = insertNode(root, num);
            }

            if (debugMode) {
                customLogger.logAction("constructBinarySearchTree()");
            }
        } catch (Exception e) {
            customLogger.logError("Failed to construct Binary Search Tree. METHOD: constructBinarySearchTree()");
            e.printStackTrace();
        }
        return root;
    }

    private BinaryNode insertNode(BinaryNode root, int value) {
        try {
            if (root == null) {
                return new BinaryNode(value);
            }
            if (value < root.getValue()) {
                root.setLeft(insertNode(root.getLeft(), value));
            } else if (value > root.getValue()) {
                root.setRight(insertNode(root.getRight(), value));
            }

            if (debugMode) {
                customLogger.logAction("insertNode()");
            }
        } catch (Exception e) {
            customLogger.logError("Failure to insert node. METHOD: insertNode()");
            e.printStackTrace();
        }
        return root;
    }

    public List<BinaryNode> getBinaryTreeNodes(BinaryNode root) {
        List<BinaryNode> nodes = new ArrayList<>();
        if (root != null) {
            nodes.add(root);
            nodes.addAll(getBinaryTreeNodes(root.getLeft()));
            nodes.addAll(getBinaryTreeNodes(root.getRight()));
        }

        if (debugMode) {
            customLogger.logAction("getBinaryTreeNodes()");
        }

        return nodes;
    }

    public void saveUserInputAndTree(List<Integer> numbers, BinaryNode root) {
        try {
            UserInput userInput = new UserInput();
            userInput.setInputs(numbers);
            userInput = userInputRepository.save(userInput);

            List<BinaryNode> treeNodes = getBinaryTreeNodes(root);
            for (BinaryNode node : treeNodes) {
                node.setUserInput(userInput);
            }

            binaryNodeRepository.saveAll(treeNodes);

            if (debugMode) {
                customLogger.logAction("saveUserInputAndTree()");
            }
        } catch (Exception e) {
            customLogger.logError("Failed to save user input and binary tree. METHOD: saveUserInputAndTree()");
            e.printStackTrace();
        }
    }

    public List<PreviousTreeDTO> getAllPreviousTrees() {
        try {
            List<UserInput> userInputs = userInputRepository.findAll();

            if (debugMode) {
                customLogger.logAction("getAllPreviousTrees()");
            }

            return userInputs.stream()
                    .map(userInput -> {
                        List<BinaryNode> treeNodes = binaryNodeRepository.findByUserInputId(userInput.getId());
                        PreviousTreeDTO dto = new PreviousTreeDTO();
                        dto.setRecordId(userInput.getId());
                        dto.setInputNumbers(userInput.getInputs());
                        dto.setRootNode(constructBinaryTreeFromNodes(treeNodes));

                        return dto;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            customLogger.logError("Failed to get all previous trees. METHOD: getAllPreviousTrees()");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private BinaryNode constructBinaryTreeFromNodes(List<BinaryNode> nodes) {
        BinaryNode root = null;
        try {
            for (BinaryNode node : nodes) {
                root = insertNode(root, node.getValue());
            }

            if (debugMode) {
                customLogger.logAction("constructBinaryTreeFromNodes()");
            }
        } catch (Exception e) {
            customLogger.logError("Failed to construct binary tree from nodes. METHOD: constructBinaryTreeFromNodes()");
            e.printStackTrace();
        }
        return root;
    }
}
