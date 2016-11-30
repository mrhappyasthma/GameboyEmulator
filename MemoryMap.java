// This class manages interactions with the memory-map of the Gameboy.
//
// It is well documented here:
//   http://imrannazar.com/GameBoy-Emulation-in-JavaScript:-Memory
//
// 0x0000-0x3FFF - Cartridge ROM (Fixed bank)
//    0x0000-0x00FF - BIOS, until an instruction 0x0100 or later is executed.
//    0x0100-0x014F - Cartridge header, contains special format data.
// 0x4000-0x7FFF - Cartridge Rom (Swapable bank)
// 0x8000-0x9FFF - Graphics RAM
// 0xA000-0xBFFF - Cartridge RAM (External RAM)
// 0xC000-0xDFFF - Working RAM (used by the CPU)
// 0xE000-0XFDFF - Working RAM (shadow) - mirrors the Working RAM (minus the last 512 bytes).
// 0xFE00-0xFE9F - Graphics Sprite Info
// 0xFF00-0xFF7F - Memory-mapped I/O
// 0xFF80-0xFFFF - Zero-page RAM - 128 bytes of fast memory, used quite frequently.

public class MemoryMap {
  // TODO(mrhappyasthma): add ability to load bios from file. And/or simulate behavior.
  int[] bios = new int[256];
  int[] rom = null;
  int[] workingRAM = new int[kRAMSize];
  int[] externalRAM = new int[kRAMSize];
  int[] zeroPageRAM = new int[kZeroPageRAMSize];

  boolean finishedBIOS = false;

  private static final int kRAMSize = 8192;
  private static final int kZeroPageRAMSize = 128;

  public MemoryMap(byte[] ROM) {
    reset();
    loadROM(ROM);
  }

  public void loadROM(byte[] bytes) {
    rom = new int[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      rom[i] = bytes[i] & 0xFF;  // Mask to 0-255 range.
    }
  }

  public void reset() {
    for (int i = 0; i < kRAMSize; i++) {
      workingRAM[i] = 0;
      externalRAM[i] = 0;
    }
    for (int i = 0; i < kZeroPageRAMSize; i++) {
      zeroPageRAM[i] = 0;
    }
    finishedBIOS = false;
    System.out.println("Note: MemoryMap reset.");
  }

  public int readByte(Processor processor) {
    int address = processor.pc.getValue();
    processor.pc.increment();
    return 1; // TODO(mrhappyasthma) get address from memory.
  }

  public int readWord(Processor processor) {
    int lowByte = readByte(processor);
    int highByte = (readByte(processor) << 8);
    return lowByte | highByte;
  }
}