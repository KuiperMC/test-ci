package fr.kuipermc.nbt.type;

import fr.kuipermc.nbt.NBTType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NBTEndTest {

    static NBTEnd nbt = NBTEnd.INSTANCE;

    @Test
    void getName() {
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, nbt::getName);
        assertEquals("NBTEnd has no name", exception.getMessage());
    }

    @Test
    void getValue() {
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, nbt::getValue);
        assertEquals("NBTEnd has no value", exception.getMessage());
    }

    @Test
    void getType() {
        assertEquals(NBTType.END, nbt.getType());
    }

    @Test
    void testToString() {
        assertEquals("NBTEnd{}", nbt.toString());
    }

    @Test
    void toUncompressed() {
        assertEquals("", nbt.toUncompressed());
    }

    @Test
    void bytes() {
        assertArrayEquals(new byte[]{
                0x00 // NBTType.END
        }, nbt.bytes());
    }

    @Test
    void fromBytes() {
        try {
            assertEquals(nbt, NBTEnd.fromBytes(new byte[] {
                    0x00 // NBTType.END
            }));
        } catch (Exception e) {
            fail(e);
        }
    }
}