package test;

import graphs.DirectedGraph;
import graphs.WeightedEdge;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/*
This class is for testing methods not tested in algorithm tests
 */
public class DirectedGraphTest extends GraphTest {
    DirectedGraph<Integer, WeightedEdge<Integer>> g;

    @Before
    public void setUp() throws Exception {
        g = getDigraph2LM();
    }

    @Test
    public void testRemove() throws Exception {
        // remove edge and vertex
        WeightedEdge<Integer> we = new WeightedEdge<Integer>(1,1,1.);
        g.add(we);
        g.remove(we);
        g.add(11);
        g.remove(11);
    }

    @Test
    public void testGetEdge() throws Exception {
        WeightedEdge<Integer> we = g.getEdge(1,1);
        assertTrue(we.getWeight() == 1 || we.getWeight() == -4);
    }

    @Test
    public void testAreAdjacent() throws Exception {
        assertFalse(g.areAdjacent(7,9));
        assertTrue(g.areAdjacent(6, 7));
        assertTrue(g.areAdjacent(9,10));
    }

    @Test
    public void testContains() throws Exception {
        assertTrue(g.contains(10));
        assertFalse(g.contains(11));
    }

    @Test
    public void testGetEdges() throws Exception {
        Set<WeightedEdge<Integer>> hs = g.getEdges(); //todo HashSet??
        for (WeightedEdge<Integer> e : hs) {
            if (e.getWeight() == 10) {
                if (e.getVertexA() != 4 || e.getVertexB() != 3) fail();
            }
        }
    }

    @Test
    public void testOrder() throws Exception {
        assertEquals(10,g.order());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(17,g.size());
    }

    @Test
    public void testIndegree() throws Exception {
        assertEquals(3,g.indegree(1));
        assertEquals(0,g.indegree(6));
        assertEquals(0,g.indegree(10));
        assertEquals(3,g.indegree(7));

    }

    @Test
    public void testOutdegree() throws Exception {
        assertEquals(7, g.outdegree(6));
        assertEquals(4, g.outdegree(1));
        assertEquals(0, g.outdegree(9));
        assertEquals(3, g.outdegree(3));

    }

    @Test
    public void testGetIncomingEdges() throws Exception {
        assertEquals(3, g.getIncomingEdges(7).size());
    }

    @Test
    public void testGetOutgoingEdges() throws Exception {
        assertEquals(0, g.getOutgoingEdges(7).size());
    }
}