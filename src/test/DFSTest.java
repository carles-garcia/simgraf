package test;

import algorithms.DFS;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFSTest extends GraphTest {
    DFS<Integer> dfs;

    @Before
    public void setUp() throws Exception {
        dfs = new DFS<Integer>(getGraph2(), 3);
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
    }

    @Test
    public void testGetPredecessors() throws Exception {
        HashMap<Integer,Integer> hs = dfs.getPredecessors();
        //Can't be tested
    }
}