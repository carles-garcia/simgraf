/*
Copyright (C) 2015,2016  Carles Garcia Cabot  (github.com/carles-garcia)
This file is part of simgraf, a simple graph library written in Java. 
Released under the Mozilla Public License v2 (see COPYING.txt)
*/
package test;

import algorithms.BFS;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BFSTest extends GraphTest {
    BFS<Integer> bfs;
    BFS<Integer> bfs2;

    @Before
    public void setUp() throws Exception {
        bfs = new BFS<Integer>(getGraph1(), 3);
        bfs2 = new BFS<Integer>(getGraph1(), 1, 2);
    }

    @Test
    public void testGetDistances() throws Exception {
        HashMap<Integer, AtomicInteger> hs = bfs.getDistances();
        assertEquals(1, hs.get(1).get());
        assertEquals(1, hs.get(2).get());
        assertEquals(0, hs.get(3).get());
        assertEquals(1, hs.get(4).get());
        assertEquals(2, hs.get(5).get());

        hs = bfs2.getDistances();
        assertEquals(-1, hs.get(4).get());
    }

    @Test
    public void testGetPredecessors() throws Exception {
        HashMap<Integer,Integer> hs = bfs.getPredecessors();
        assertEquals(3, (int)hs.get(1));
        assertEquals(3, (int)hs.get(2));
        assertEquals(null, hs.get(3));
        assertEquals(3, (int) hs.get(4));
        assertTrue(4 == hs.get(5) || 1 == hs.get(5));
    }

    @Test
    public void testGetVisited() throws Exception {
        HashSet<Integer> hs = bfs.getVisited();
        for (int i : getGraph1().getVertices()) {
            assertTrue(hs.contains(i));
        }

    }
}
