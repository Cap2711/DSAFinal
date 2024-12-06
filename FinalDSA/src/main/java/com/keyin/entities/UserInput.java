package com.keyin.entities;

import com.keyin.entities.BinaryNode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_input")
public class UserInput {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "user_sequence")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "user_input_values", joinColumns = @JoinColumn(name = "user_input_id"))
    @Column(name = "input_numbers", nullable = true)
    private List<Integer> inputs;

    @OneToOne(cascade = CascadeType.ALL)
    private BinaryNode rootNode;

    public UserInput() {
    }

    public UserInput(List<Integer> inputs, BinaryNode rootNode) {
        this.inputs = inputs;
        this.rootNode = rootNode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getInputs() {
        return inputs;
    }

    public void setInputs(List<Integer> inputs) {
        this.inputs = inputs;
    }

    public BinaryNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(BinaryNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "id=" + id +
                ", inputs=" + inputs +
                ", rootNode=" + rootNode +
                '}';
    }
}
