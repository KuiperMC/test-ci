package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTDoubleTest {

    static NBTDouble nbt = new NBTDouble("test", 1.0);

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertEquals(1.0, nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.DOUBLE, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTDouble{name='test', value=1.0}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_Double('test'): 1.0", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x06, // NBTType.DOUBLE
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x3F, (byte) 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // value (1.0)
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTDouble.fromBytes(new byte[]{
                    0x06, // NBTType.DOUBLE
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x3F, (byte) 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // value (1.0)
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}