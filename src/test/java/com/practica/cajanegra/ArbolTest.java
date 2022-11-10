package com.practica.cajanegra;

import com.binarytree.BinaryTree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

public class ArbolTest {


    @Test
    public void SizeTest() {
        BinaryTree<String> arbolPrueba = new BinaryTree<String>("1");
        int tamanoReal = arbolPrueba.size();
        Assertions.assertEquals(1, tamanoReal);
    }



}
