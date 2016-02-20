/*
Copyright (C) 2015,2016  Carles Garcia Cabot  (github.com/carles-garcia)
This file is part of simgraf, a simple graph library written in Java. 
Released under the Mozilla Public License v2 (see COPYING.txt)
*/
package test;

import graphs.Edge;
import graphs.UndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UndirectedGraphTest extends GraphTest {
    UndirectedGraph<Integer, Edge<Integer>> g;

    @Before
    public void setUp() throws Exception {
        g = getGraph1L();
    }

    @Test
    public void testDegree() throws Exception {
        assertEquals(5, g.degree(1));
    }

    @Test
    public void testGetEdges() throws Exception {
        assertTrue(g.size() == g.getEdges().size());
    }

    @Test
    public void testGetEdges1() throws Exception {
        assertEquals(1, g.getEdges(3,4).size());
    }

    @Test
    public void testNumberOfEdges() throws Exception {
        assertEquals(1, g.numberOfEdges(1,1));

    }
}
