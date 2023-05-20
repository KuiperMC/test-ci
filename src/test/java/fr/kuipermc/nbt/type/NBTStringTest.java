package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTStringTest {

    static NBTString nbt = new NBTString("test", "value");

    @Test
    void getName() {
        assertEquals("test", nbt.getName());
    }

    @Test
    void getValue() {
        assertEquals("value", nbt.getValue());
    }

    @Test
    void getType() {
        assertEquals(NBTType.STRING, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTString{name='test', value='value'}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("TAG_String('test'): 'value'", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x08, // NBTType.STRING
                0x00, 0x04, // name length (4)
                0x74, 0x65, 0x73, 0x74, // name ("test")
                0x00, 0x05, // value length (5)
                0x76, 0x61, 0x6C, 0x75, 0x65, // value ("value")
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTString.fromBytes(new byte[]{
                    0x08, // NBTType.STRING
                    0x00, 0x04, // name length (4)
                    0x74, 0x65, 0x73, 0x74, // name ("test")
                    0x00, 0x05, // value length (5)
                    0x76, 0x61, 0x6C, 0x75, 0x65, // value ("value")
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}