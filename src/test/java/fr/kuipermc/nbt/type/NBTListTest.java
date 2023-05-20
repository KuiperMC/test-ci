package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NBTListTest {

    static NBTList<NBTInt> nbt = new NBTList<>("test", List.of(new NBTInt(null, 1)), NBTType.INT);

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertEquals(List.of(new NBTInt(null, 1)), nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.LIST, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTList{name='test', value=[NBTInt{name='null', value=1}]}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_List('test'): 1 entry" +
                "\n{" +
                "\n\tTAG_Int(None): 1" +
                "\n}", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x09, // NBTType.LIST
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x03, // elementType (NBTType.INT)
                0x00, 0x00, 0x00, 0x01, // size (1)
                0x00, 0x00, 0x00, 0x01, // element value (1)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTList.fromBytes(new byte[]{
                    0x09, // NBTType.LIST
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x03, // elementType (NBTType.INT)
                    0x00, 0x00, 0x00, 0x01, // size (1)
                    0x00, 0x00, 0x00, 0x01, // element value (1)
            }));
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void size() {
        assertEquals(1, nbt.size());
    }

    @Test
    void iterator() {
        Iterator<NBTInt> iterator = nbt.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(new NBTInt(null, 1), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void getElementType() {
        assertEquals(NBTType.INT, nbt.getElementType());
    }
}