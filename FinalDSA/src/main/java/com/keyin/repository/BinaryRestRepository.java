package com.keyin.repository;

import com.keyin.entities.BinaryNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinaryRestRepository extends JpaRepository<BinaryNode, Long> {


    List<BinaryNode> findByUserInputId(Long userInputId);
}
