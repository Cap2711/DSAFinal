package com.keyin.entities;

import com.keyin.entities.BinaryNode;

import java.util.List;

public class TreeDTO {
    private List<Integer> inputValues;
    private BinaryNode root;

    public TreeDTO() {
    }

    public TreeDTO(List<Integer> inputValues, BinaryNode root) {
        this.inputValues = inputValues;
        this.root = root;
    }

    public List<Integer> getInputValues() {
        return inputValues;
    }

    public void setInputValues(List<Integer> inputValues) {
        this.inputValues = inputValues;
    }

    public BinaryNode getRoot() {
        return root;
    }

    public void setRoot(BinaryNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "TreeDTO{" +
                "inputValues=" + inputValues +
                ", root=" + root +
                '}';
    }
}
