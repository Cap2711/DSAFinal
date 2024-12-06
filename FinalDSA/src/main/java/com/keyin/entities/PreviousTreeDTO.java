package com.keyin.entities;

import com.keyin.entities.BinaryNode;

import java.util.List;

public class PreviousTreeDTO {

    private Long recordId;
    private List<Integer> inputNumbers;
    private BinaryNode rootNode;

    public PreviousTreeDTO() {
    }

    public PreviousTreeDTO(Long recordId, List<Integer> inputNumbers, BinaryNode rootNode) {
        this.recordId = recordId;
        this.inputNumbers = inputNumbers;
        this.rootNode = rootNode;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public List<Integer> getInputNumbers() {
        return inputNumbers;
    }

    public void setInputNumbers(List<Integer> inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public BinaryNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(BinaryNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        return "PreviousTreeDTO{" +
                "recordId=" + recordId +
                ", inputNumbers=" + inputNumbers +
                ", rootNode=" + rootNode +
                '}';
    }
}
