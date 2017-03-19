// This class manages interactions with the memory-map of the Gameboy.
//
// It is well documented here:
//   http://imrannazar.com/GameBoy-Emulation-in-JavaScript:-Memory
//
// Additional resources:
//   http://gameboy.mongenel.com/dmg/asmmemmap.html
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
  private static final int kBIOSSize = 256;
  private static final int kRAMSize = 8192;
  private static final int kZeroPageRAMSize = 128;

  // TODO(mrhappyasthma): add ability to load bios from file. And/or simulate behavior.
  int[] bios = new int[kBIOSSize];
  int[] rom = null;
  int[] workingRAM = new int[kRAMSize];
  int[] externalRAM = new int[kRAMSize];
  int[] zeroPageRAM = new int[kZeroPageRAMSize];

  boolean finishedBIOS = false;

  public MemoryMap(byte[] ROM, byte[] BIOS) {
    reset();
    loadROM(ROM);
    loadBIOS(BIOS);
  }

  private void loadROM(byte[] bytes) {
    rom = new int[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      rom[i] = bytes[i] & 0xFF;  // Mask to 0-255 range.
    }
  }

  private void loadBIOS(byte[] bytes) {
    if (bytes.length != kBIOSSize) {
      System.err.println("The BIOS must be 256 bytes. Not " + bytes.length);
    }
    for (int i = 0; i < kBIOSSize; i++) {
      bios[i] = bytes[i] & 0xFF;  // Mask to 0-255 range.
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

  public int readByte(int address) {
    int byteMask = 0xFF;  // Mask to 0-255 range.
    int RAMMask = 0x1FFF;  // Mask to 0-8191 range.
    int ZRAMMask = 0x7F;  // Mask from 0-127 range.

    // Switch by the upper most byte to determine which part of the memory
    // mapped to access.
    switch(address & 0xF000) {
      // ROM banks 0 and 1. (Note: for the first 256 bytes, read the BIOS.)
      case 0x0000:
      case 0x1000:
      case 0x2000:
      case 0x3000:
      case 0x4000:
      case 0x5000:
      case 0x6000:
      case 0x7000:
        if (!finishedBIOS) {
          if (address < 0x0100) {
            return bios[address] & byteMask;
          } else {  // We finished BIOS.
            finishedBIOS = true;
          }
        }
        return rom[address] & byteMask;

      // Graphics VRAM (8k)
      case 0x8000:
      case 0x9000:
        // TODO(GPU vram)
        return 0;

      // External RAM (8k)
      case 0xA000:
      case 0xB000:
        return externalRAM[address & RAMMask] & byteMask;

      // Working RAM Shadow
      case 0xC000:
      case 0xD000:
      case 0xE000:
        return workingRAM[address & RAMMask] & byteMask;

      // Working RAM shadow, I/O, Zero-page RAM
      case 0xF000:
        // Switch by the second most upper byte to determine which part of
        // memory to access.
        switch(address & 0x0F00) {
          // Working RAM Shadow
          case 0x000:
          case 0x100:
          case 0x200:
          case 0x300:
          case 0x400:
          case 0x500:
          case 0x600:
          case 0x700:
          case 0x800:
          case 0x900:
          case 0xA00:
          case 0xB00:
          case 0xC00:
          case 0xD00:
            return workingRAM[address & RAMMask] & byteMask;

          // Graphics sprite data.
          // Note: OAM is 160 bytes, remaining bytes read as 0.
          case 0xE00:
            if (address >= 0xFEA0) {
              return 0;
            }
            // TODO(GPU OAM)
            return 0;

          // Zero-page RAM, I/O
          case 0xF00:
            if (address < 0xFF80) {
              // TODO(I/O control handling)
              return 0;
            }
            return zeroPageRAM[address & ZRAMMask] & byteMask;
        }
    }

    // This should never be reached. If something went wrong, error and return 0.
    String hexAddress = Integer.toHexString(address);
    System.err.println("ERROR -> Unhandled address in readByte: " + hexAddress);
    return 0;
  }

  public int readWord(int address) {
    int lowByte = readByte(address);
    int highByte = (readByte(address+1) << 8);
    return lowByte | highByte;
  }

  public void writeByte(int address, int value) {
    int byteMask = 0xFF;  // Mask to 0-255 range.
    int RAMMask = 0x1FFF;  // Mask to 0-8191 range.
    int ZRAMMask = 0x7F;  // Mask from 0-127 range.

    // Switch by the upper most byte to determine which part of the memory
    // mapped to access.
    switch(address & 0xF000) {
      // ROM 0
      case 0x0000:
        if (!finishedBIOS && (address < 0x0100)) {
          return;  // You cannot write to BIOS.
        }
        // Intentional fall through.
      case 0x1000:
      case 0x2000:
      case 0x3000:
        return;

      // ROM 1
      case 0x4000:
      case 0x5000:
      case 0x6000:
      case 0x7000:
        return;

      // Graphics VRAM (8k)
      case 0x8000:
      case 0x9000:
        // TODO(VRAM)
        return;

      // External RAM (8k)
      case 0xA000:
      case 0xB000:
        externalRAM[address & RAMMask] = (value & byteMask);
        return;

      // Working RAM Shadow
      case 0xC000:
      case 0xD000:
      case 0xE000:
        workingRAM[address & RAMMask] = (value & byteMask);
        return;

      // Working RAM shadow, I/O, Zero-page RAM
      case 0xF000:
        // Switch by the second most upper byte to determine which part of
        // memory to access.
        switch(address & 0x0F00) {
          // Working RAM Shadow
          case 0x000:
          case 0x100:
          case 0x200:
          case 0x300:
          case 0x400:
          case 0x500:
          case 0x600:
          case 0x700:
          case 0x800:
          case 0x900:
          case 0xA00:
          case 0xB00:
          case 0xC00:
          case 0xD00:
            workingRAM[address & RAMMask] = (value & byteMask);
            return;

          // Graphics sprite data.
          // Note: OAM is 160 bytes, remaining bytes read as 0.
          case 0xE00:
            if (address >= 0xFEA0) {
              return;
            }
            // TODO(GPU OAM)
            return;

          // Zero-page RAM, I/O
          case 0xF00:
            if (address < 0xFF80) {
              // TODO(I/O control handling)
              return;
            }
            zeroPageRAM[address & ZRAMMask] = (value & byteMask);
            return;
        }
    }

    // This should never be reached. If something went wrong, error.
    String hexAddress = Integer.toHexString(address);
    System.err.println("ERROR -> Unhandled address in writeByte: " + hexAddress);
  }

  public void writeWord(int address, int value) {
    int lowerByte = (value & 0xFF);
    int upperByte = (value >>> 8);
    writeByte(address, lowerByte);
    writeByte(address + 1, upperByte);
  }
}