package com.keyin.entities;

import javax.persistence.*;

@Entity
@Table(name = "binary_node")
public class BinaryNode {

    @Id
    @SequenceGenerator(name = "binarynode_sequence", sequenceName = "binarynode_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "binarynode_sequence")
    private Long id;

    private int value;

    @ManyToOne
    private UserInput userInput;

    @OneToOne
    private BinaryNode left;

    @OneToOne
    private BinaryNode right;

    public BinaryNode() {
    }

    public BinaryNode(int value) {
        this.value = value;
    }

    public BinaryNode(int value, BinaryNode left, BinaryNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public UserInput getUserInput() {
        return userInput;
    }

    public void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "id=" + id +
                ", value=" + value +
                ", userInput=" + userInput +
                ", left=" + (left != null ? left.getId() : null) +
                ", right=" + (right != null ? right.getId() : null) +
                '}';
    }
}


