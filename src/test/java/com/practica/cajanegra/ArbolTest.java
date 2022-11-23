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
    public void RemoveTest_NodoPresenteConHijo(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.search("2"), true);
        arbolPrueba.remove(arbolPrueba.search("1"));
        Assertions.assertEquals(1, arbolPrueba.size(),
                "eliminar un nodo intermedio debería eliminar todo el subarbol.");
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
                "el arbol tras 2 inserciones debería tener 3 nodos");
    }

    @Test
    public void DepthSinParametrosTest(){
        Assertions.assertEquals(0, arbolPrueba.depth(),
                "el arbol inicial de un solo nodo debería tener depth 0");
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        Assertions.assertEquals(1, arbolPrueba.depth(),
                "el arbol con 2 nodos debería tener depth 1");
        arbolPrueba.insert("3", arbolPrueba.search("2"), true);
        Assertions.assertEquals(2, arbolPrueba.depth(),
                "el arbol con 3 nodos debería tener depth 2");
    }

    @Test
    public void DepthConParametros(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.search("2"), true);
        arbolPrueba.insert("4", arbolPrueba.search("2"), false);
        Assertions.assertEquals(1, arbolPrueba.depth(arbolPrueba.search("2")));
    }

    @Test
    public void SearchTest_ContenidoEstaEnArbol(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.search("2"), true);
        Assertions.assertEquals("3", arbolPrueba.search("3").getContent(),
                "el nodo debería contener 3");
    }
    @Test
    public void SearchTest_ContenidoEnArbolDuplicado()
    {
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("2", arbolPrueba.search("2"), true);
        Assertions.assertEquals("2", arbolPrueba.search("2").getLeftChild().getContent());
    }

    @Test
    public void SearchTest_ContenidoNoPresente(){
        Assertions.assertNull(arbolPrueba.search("87642"));
    }
    @Test
    public void SearchTest_ContenidoNoPresent(){
        Assertions.assertNull(arbolPrueba.search("87642"));
    }

    @Test
    public void InsertTest_InsertEnLaIzquierda(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        Assertions.assertEquals("2", arbolPrueba.getRoot().getLeftChild().getContent());
    }
    @Test
    public void InsertTest_InsertEnLaDerecha(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), false);
        Assertions.assertEquals("2", arbolPrueba.getRoot().getRightChild().getContent());
    }







}
