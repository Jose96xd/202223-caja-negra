package com.practica.cajanegra;

import com.binarytree.BinaryTree;
import com.binarytree.Node;
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
        Assertions.assertEquals("1", arbolPrueba.getRoot().getContent(),
                "la raiz debería contener un 1");
    }

    @Test
    public void RemoveTest_NodoPresente(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        Node node = arbolPrueba.search("2");

        arbolPrueba.remove(node);
        Assertions.assertEquals(null, arbolPrueba.search("2"),
                "al buscar un nodo eliminado debería devolver null");
    }

    @Test
    public void RemoveTest_NodoNoPresente(){
        Node<String> nodoNoPresente = new Node<>("3");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arbolPrueba.remove(nodoNoPresente);
        }, "al eliminar un nodo que no está en el árbol, debería devolver un IllegalArgumentException exception");
    }

    @Test
    public void SizeTets(){
        Assertions.assertEquals(1, arbolPrueba.size(),
                "el arbol inicial debería tener un solo nodo");
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), false);
        Assertions.assertEquals(3, arbolPrueba.size(),
                "el arbol tras inserciones debería tener 3 nodos");
    }

    @Test
    public void DepthSinParametrosTest(){
        Assertions.assertEquals(0, arbolPrueba.depth(),
                "el arbol inicial de un solo nodo debería tener depth 0");
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        Assertions.assertEquals(1, arbolPrueba.depth(),
                "el arbol con 3 nodos debería tener depth 1");
    }

    @Test
    public void SearchTest_ContenidoEstaEnArbol(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        Assertions.assertEquals("2", arbolPrueba.search("2").getContent(),
                "el nodo debería contener 2");
    }

    @Test
    public void SearchTest_ContenidoNoPresente(){

    }

    /*
    @Test
    public void DepthConParametrosTest(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);

        Node<String> nodoHijo = arbolPrueba.search("2");

        Assertions.assertEquals(0, arbolPrueba.depth(nodoHijo),
                "el subarbol hoja debería tener depth 0");
        arbolPrueba.insert("3", nodoHijo, true);
        Assertions.assertEquals(1, arbolPrueba.depth(nodoHijo),
                "el subarbol hoja debería tener depth 1");

    }
    */






}
