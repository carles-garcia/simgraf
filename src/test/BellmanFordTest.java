/*
Copyright (C) 2015,2016  Carles Garcia Cabot  (github.com/carles-garcia)
This file is part of simgraf, a simple graph library written in Java. 
Released under the Mozilla Public License v2 (see COPYING.txt)
*/
package test;

import algorithms.BellmanFord;
import graphs.WeightedEdge;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BellmanFordTest extends GraphTest {
    BellmanFord<Integer, WeightedEdge<Integer>> bf;

    @Before
    public void setUp() throws Exception {
        bf = new BellmanFord<>(getDigraph1(), 3);

    }

    @Test
    public void testGetDistances() throws Exception {
        HashMap<Integer, Double> hs = bf.getDistances();
        assertEquals(1.0, hs.get(1), DELTA);
        assertEquals(1, hs.get(2),DELTA);
        assertEquals(0, hs.get(3),DELTA);
        assertEquals(5, hs.get(4),DELTA);
        assertEquals(4, hs.get(5),DELTA);

    }

    @Test
    public void testGetPredecessors() throws Exception {
        HashMap<Integer,Integer> hs = bf.getPredecessors();
        assertEquals(3, (int)hs.get(1));
        assertEquals(3, (int)hs.get(2));
        assertEquals(null, hs.get(3));
        assertEquals(3, (int)hs.get(4));
        assertEquals(1, (int)hs.get(5));

    }

    @Test
    public void testHasNegativeCycle() throws Exception {
        assertFalse(bf.hasNegativeCycle());
    }
}
