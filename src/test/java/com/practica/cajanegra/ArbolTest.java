package com.practica.cajanegra;

import com.binarytree.BinaryTree;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

public class ArbolTest {

    BinaryTree<String> arbolPrueba;

    @BeforeEach
    public void SetUp(){
        arbolPrueba = new BinaryTree<String>("1");
    }

    @Test
    public void ConstructorTest(){
        Assertions.assertEquals("1", arbolPrueba.getRoot().getContent(), "En la raiz debería haber un 1");
    }

    @Test()
    public void ConstructorTest_Null(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BinaryTree<String>(null);
        }, "el constructur con null debería dar una excepción de IllegalArgumentException");
    }

    @Test
    public void GetRootTest(){
        Assert.assertEquals("1", arbolPrueba.getRoot().getContent(),
                "la raiz debería contener un 1");
    }

    @Test
    public void InsertTest(){
        //arbolPrueba.insert("2", );
    }
/*
    @Test
    public void RemoveTest(){

    }

    @Test
    public void SizeTest() {
        int tamanoReal = arbolPrueba.size();
        Assertions.assertEquals(1, tamanoReal);
    }


    @Test
    public void SizeTest_ArbolInvalido(){
        BinaryTree<String> arbolPrueba = new BinaryTree<String>("1");
        ijnfd
    }



*/


}
