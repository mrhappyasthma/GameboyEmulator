/*
 * Place any ROMS in the `../roms` directory.
 *
 * Execute the script like:
 *   java Emulator <rom_name>
 */
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Emulator {
  private static Instruction decode(int opcode, Processor processor, MemoryMap memoryMap) {
    Instruction instruction = Instruction.instructionForOpcode(opcode);
    if (instruction == null) {
      return null;
    }
    if (instruction.opcode == 0xCB) {  // Handle 0xCB two-byte opcodes.
      opcode = memoryMap.readByte(processor);
      instruction = Instruction.prefixCBForOpcode(opcode);
    }
    return instruction;
  }

  private static void runGameboy(byte[] ROM) {
    MemoryMap memoryMap = new MemoryMap(ROM);
    Processor processor = new Processor();
    while (true) {
      // 1. Fetch instruction (and increment program counter).
      int opcode = memoryMap.readByte(processor);

      // 2. Decode instruction.
      Instruction instruction = decode(opcode, processor, memoryMap);
      if (instruction == null) {  // Handle bad instruction without crashing.
        System.out.println(String.format("Unknown opcode %02x at: %04x", opcode, processor.pc.getValue()));
        continue;
      }

      // 3. Execute. (Updates program counter and clock implicitly.)
      instruction.execute(processor, memoryMap);
    }
  }

  private static String addExtension(String filePath) {
    String extension = filePath.substring(filePath.length()-3);
    String EXT = ".gb";
    if (!extension.equals(EXT)) {
      filePath += EXT;
    }
    return filePath;
  }

  private static byte[] readROM(String fileName) {
    DataInputStream dataInputStream = null;
    FileInputStream inputStream = null;
    byte[] bytes = null;
    try {
      try {
        String filePath = "roms/" + fileName;
        filePath = addExtension(filePath);
        File f = new File(filePath);
        bytes = new byte[(int)f.length()];
        inputStream = new FileInputStream(f);
        dataInputStream = new DataInputStream(inputStream);
        dataInputStream.readFully(bytes);
      } finally {
        if (inputStream != null) {
          inputStream.close();
        }
        if (dataInputStream != null) {
          dataInputStream.close();
        }
      }
    } catch (IOException e) {
      System.out.println(e.getStackTrace());
      bytes = null;
    }
    return bytes;
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Error: Please add the ROM file name as an argument.");
      return;
    }

    // TODO(mrhappyasthma): Read BIOS.

    System.out.println("Loading ROM...");
    byte[] ROM = readROM(args[0]);
    if (ROM == null) {
      System.out.println("Error reading ROM: " + args[0]);
      return;
    }
    System.out.println("ROM loaded successfully!");

    System.out.println("Starting Gameboy...");
    runGameboy(ROM);
  }
}