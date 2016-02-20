/*
Copyright (C) 2015,2016  Carles Garcia Cabot  (github.com/carles-garcia)
This file is part of simgraf, a simple graph library written in Java. 
Released under the Mozilla Public License v2 (see COPYING.txt)
*/
package test;

import algorithms.DFS;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class DFSTest extends GraphTest {
    DFS<Integer> dfs;
    DFS<Integer> dfs2;

    @Before
    public void setUp() throws Exception {
        dfs = new DFS<Integer>(getGraph2(), 3);
        dfs2 = new DFS<Integer>(getGraph2(), 6,1);
    }

    @Test
    public void testGetVisited() throws Exception {
        HashSet<Integer> hs = dfs.getVisited();
        for (int i : getGraph2().getVertices()) {
            if (i == 9 || i == 10) assertFalse(hs.contains(i));
            else assertTrue(hs.contains(i));
        }

    }

    @Test
    public void testGetDistances() throws Exception {
        HashMap<Integer, AtomicInteger> hs = dfs.getDistances();
        //Can't be tested, in DFS the distances are no not deterministic

        HashMap<Integer, AtomicInteger> hs2 = dfs2.getDistances();
        assertTrue(hs2.get(1).get() > 0);
    }

    @Test
    public void testGetPredecessors() throws Exception {
        HashMap<Integer,Integer> hs = dfs.getPredecessors();
        //Can't be tested

        HashMap<Integer,Integer> hs2 = dfs2.getPredecessors();
        assertEquals(null, hs2.get(6));
    }
}
