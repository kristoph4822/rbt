package rbt;

import org.junit.Test;
import static org.junit.Assert.*;

public class MapTest {

    @Test(expected = NullKeyException.class)
    public void testNullKeySet() {

        Map<String, String> map = new Map();
        map.setValue(null, "2");
    }

    @Test(expected = NullKeyException.class)
    public void testNullKeyGet() {

        Map<String, String> map = new Map();
        map.getValue(null);
    }

    @Test
    public void testDifferentMapTypes() {

        Map<Character, Character> map_c = new Map();
        map_c.setValue('1', '1');
        assertEquals(map_c.getValue('1'), new Character('1'));

        Map<String, String> map_s = new Map();
        map_s.setValue("2", "2");
        assertEquals(map_s.getValue("2"), "2");

        Map<Double, Double> map_d = new Map();
        map_d.setValue(3., 3.);
        assertEquals(map_d.getValue(3.), new Double(3));

        Map<Integer, Integer> map_i = new Map();
        map_i.setValue(4, 4);
        assertEquals(map_i.getValue(4), new Integer(4));
    }

    @Test
    public void testNoKeyFound() {

        Map<Integer, Integer> map_i = new Map();
        map_i.setValue(1, 1);
        map_i.setValue(2, 2);
        map_i.setValue(3, 3);

        assertEquals(map_i.getValue(99), null);
    }

    @Test
    public void testRotation() {

        Map<String, String> map = new Map();

        map.setValue("2", "2");
        assertEquals(map.root.value, "2");

        //test przekolorowania
        map.setValue("1", "1");
        map.setValue("4", "4");
        assertEquals(map.root.color, Map.Node.BLACK);
        assertEquals(map.root.leftChild.color, Map.Node.BLACK);
        assertEquals(map.root.rightChild.color, Map.Node.BLACK);

        //test obrotu w lewo
        map.setValue("5", "5");
        assertEquals(map.root.rightChild.value, "5");
        assertEquals(map.root.rightChild.color, Map.Node.BLACK);
        assertEquals(map.root.rightChild.leftChild.value, "4");
        assertEquals(map.root.rightChild.leftChild.color, Map.Node.RED);

        //test obrotu w prawo + przekolorowania + obrotu w lewo
        map.setValue("3", "3");
        assertEquals(map.root.value, "4");
        assertEquals(map.root.rightChild.value, "5");
        assertEquals(map.root.leftChild.rightChild.value, "3");
        assertEquals(map.root.leftChild.color, Map.Node.RED);
        assertEquals(map.root.color, Map.Node.BLACK);

    }

    @Test
    public void testKeyOverride() {

        Map<Integer, String> map = new Map();

        map.setValue(1, "1");
        map.setValue(2, "2");
        map.setValue(3, "3");

        map.setValue(1, "11");
        assertEquals(map.getValue(1), "11");

        map.setValue(3, "33");
        assertEquals(map.getValue(3), "33");
    }

}
