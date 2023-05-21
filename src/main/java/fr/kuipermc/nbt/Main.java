package fr.kuipermc.nbt;

import fr.kuipermc.nbt.type.NBTTag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: java -jar nbt.jar <file>");
            System.exit(1);
        }
        NBTTag<?> tag = NBTTag.fromBytes(Files.readAllBytes(Path.of(String.join(" ", args))));
        System.out.println(tag);
        System.out.println(Arrays.toString(tag.bytes()));
        System.out.println(tag.toUncompressed());
    }
}
