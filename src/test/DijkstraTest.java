package test;

import algorithms.Dijkstra;
import graphs.WeightedEdge;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class DijkstraTest extends GraphTest {
    Dijkstra<Integer, WeightedEdge<Integer>> dij;

    @Before
    public void setUp() throws Exception {
        dij = new Dijkstra<>(getDigraph2(), 3);
    }

    @Test
    public void testGetDistances() throws Exception {
        HashMap<Integer, Double> hs = dij.getDistances();
        assertEquals(1, hs.get(1), DELTA);
        assertEquals(1, hs.get(2),DELTA);
        assertEquals(0, hs.get(3),DELTA);
        assertEquals(5, hs.get(4),DELTA);
        assertEquals(4, hs.get(5),DELTA);
        assertEquals(Double.POSITIVE_INFINITY, hs.get(6), DELTA);
        assertEquals(Double.POSITIVE_INFINITY, hs.get(7),DELTA);
        assertEquals(Double.POSITIVE_INFINITY, hs.get(8),DELTA);
        assertEquals(Double.POSITIVE_INFINITY, hs.get(9),DELTA);
        assertEquals(Double.POSITIVE_INFINITY, hs.get(10),DELTA);
    }

    @Test
    public void testGetPredecessors() throws Exception {
        HashMap<Integer,Integer> hs = dij.getPredecessors();
        assertEquals(3, (int)hs.get(1));
        assertEquals(3, (int)hs.get(2));
        assertEquals(null, hs.get(3));
        assertEquals(3, (int)hs.get(4));
        assertEquals(1, (int) hs.get(5));
        assertEquals(null, hs.get(6));
        assertEquals(null, hs.get(7));
        assertEquals(null, hs.get(8));
        assertEquals(null, hs.get(9));
        assertEquals(null, hs.get(10));
    }

    @Test
    public void testGetVisited() throws Exception {
        HashSet<Integer> hs = dij.getVisited();
        for (int i = 1; i <= 10; ++i) {
            if (i <= 5) assertTrue(hs.contains(i));
            else assertFalse(hs.contains(i));
        }

    }
}