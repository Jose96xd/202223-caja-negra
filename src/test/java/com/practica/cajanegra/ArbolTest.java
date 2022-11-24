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

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public void InsertTest_InsertEnLaIzquierda(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        Assertions.assertEquals("2", arbolPrueba.getRoot().getLeftChild().getContent());
    }
    @Test
    public void InsertTest_InsertEnLaDerecha(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), false);
        Assertions.assertEquals("2", arbolPrueba.getRoot().getRightChild().getContent());
    }

    @Test
    public void InsertTest_ContenidoNoAlfaNumerico(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arbolPrueba.insert(null, arbolPrueba.getRoot(), false);
        }, "al insertar con contenido null se lanza una excepcion tipo IllegalArgumentException");
    }

    @Test
    public void InsertTest_NodoNoPresente(){
        Node<String> aux = new Node<>("5");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arbolPrueba.insert("2", aux, false);
        }, "al insertar en un nodo no presente en el arbol se lanza una excepcion tipo IllegalArgumentException");
    }

    @Test
    public void InsertTest_NodoYaPresenteIzquierda(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), true);
        Assertions.assertEquals("3", arbolPrueba.getRoot().getLeftChild().getContent());
    }

    @Test
    public void InsertTest_NodoYaPresenteDerecha(){
        arbolPrueba.insert("2", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), false);
        Assertions.assertEquals("3", arbolPrueba.getRoot().getRightChild().getContent());
    }

    @Test
    public void EqualsTest(){
        BinaryTree<String> aux = new BinaryTree<>("1");
        arbolPrueba.insert("2", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), true);
        aux.insert("2", aux.getRoot(), false);
        aux.insert("3", aux.getRoot(), true);
        Assertions.assertTrue(arbolPrueba.equals(aux));
    }

    @Test
    public void EqualsTest_ArbolesDiferentes(){
        BinaryTree<String> aux = new BinaryTree<>("5");
        arbolPrueba.insert("2", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), true);
        aux.insert("6", aux.getRoot(), true);
        aux.insert("7", aux.getRoot(), false);
        Assertions.assertFalse(arbolPrueba.equals(aux));
    }

    @Test
    public void EqualsTest_ArbolesDiferenteTopologia(){
        BinaryTree<String> aux = new BinaryTree<>("1");
        arbolPrueba.insert("2", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("4", arbolPrueba.search("2"), false);
        arbolPrueba.insert("5", arbolPrueba.search("2"), true);
        arbolPrueba.insert("6", arbolPrueba.search("3"), false);
        arbolPrueba.insert("7", arbolPrueba.search("3"), true);
        aux.insert("25", aux.getRoot(), true);
        aux.insert("453", aux.getRoot(), false);
        Assertions.assertFalse(arbolPrueba.equals(aux),
                "Deberia dar false, pero esta mal hecho y siempre da true si la raiz esta en el arbol grande");
    }

    @Test
    public void EqualsTest_Null() {
        arbolPrueba.insert("2", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("4", arbolPrueba.search("2"), false);
        arbolPrueba.insert("5", arbolPrueba.search("2"), true);
        arbolPrueba.insert("6", arbolPrueba.search("3"), false);
        arbolPrueba.insert("7", arbolPrueba.search("3"), true);
        Assertions.assertThrows(NullPointerException.class, () -> {
            arbolPrueba.equals(null);
        }, "al comparar con null se lanza una excepcion tipo NullPointerException");
    }

    @Test
    public void ToListTest() {
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("4", arbolPrueba.search("2"), true);
        arbolPrueba.insert("5", arbolPrueba.search("2"), false);
        arbolPrueba.insert("6", arbolPrueba.search("3"), true);
        arbolPrueba.insert("7", arbolPrueba.search("3"), false);
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("1");
        lista.add("2");
        lista.add("3");
        lista.add("4");
        lista.add("5");
        lista.add("6");
        lista.add("7");
        Assertions.assertEquals(lista, arbolPrueba.toList(),
                "Deberia salir true, pero esta mal hecho ya que lo hace en profundidad");
    }

    @Test
    public void GetSubTreeTest() {
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("4", arbolPrueba.search("2"), true);
        arbolPrueba.insert("5", arbolPrueba.search("2"), false);
        arbolPrueba.insert("6", arbolPrueba.search("3"), true);
        arbolPrueba.insert("7", arbolPrueba.search("3"), false);
        BinaryTree<String> aux = new BinaryTree<>("2");
        aux.insert("4", aux.search("2"), true);
        aux.insert("5", aux.search("2"), false);
        Assertions.assertEquals(aux.toList(), arbolPrueba.getSubTree(arbolPrueba.search("2")).toList());
    }

    @Test
    public void GetSubTreeTest_NodoNoPresente() {
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("4", arbolPrueba.search("2"), true);
        arbolPrueba.insert("5", arbolPrueba.search("2"), false);
        arbolPrueba.insert("6", arbolPrueba.search("3"), true);
        arbolPrueba.insert("7", arbolPrueba.search("3"), false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            arbolPrueba.getSubTree(arbolPrueba.search(null));
        }, "al poner como raiz un nodo con null se lanza una excepcion tipo IllegalArgumentException");
    }

    @Test
    public void toStringTest() {
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("4", arbolPrueba.search("2"), true);
        arbolPrueba.insert("5", arbolPrueba.search("2"), false);
        arbolPrueba.insert("6", arbolPrueba.search("3"), true);
        arbolPrueba.insert("7", arbolPrueba.search("3"), false);

        System.out.println(arbolPrueba.toString());
    }

    @Test
    public void IteratorTest() {
        arbolPrueba.insert("2", arbolPrueba.getRoot(), true);
        arbolPrueba.insert("3", arbolPrueba.getRoot(), false);
        arbolPrueba.insert("4", arbolPrueba.search("2"), true);
        arbolPrueba.insert("5", arbolPrueba.search("2"), false);
        arbolPrueba.insert("6", arbolPrueba.search("3"), true);
        arbolPrueba.insert("7", arbolPrueba.search("3"), false);
        Iterator<String> iterador = arbolPrueba.iterator();

        while (iterador.hasNext()){
            System.out.println(iterador.next());
        }
    }

}
