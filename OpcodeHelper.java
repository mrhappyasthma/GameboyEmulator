// This class implements reusable functions to help map many opcodes that do
// repetitive work.
//
// Resources:
//   http://pastraiser.com/cpu/gameboy/gameboy_opcodes.html
//   http://marc.rawer.de/Gameboy/Docs/GBCPUman.pdf
//   https://github.com/drhelius/Gearboy/blob/master/src/Processor_inline.h

public class OpcodeHelper {

  // BIT(b,r)
  //
  // Test bit |bit| in resgister |reg|.
  //
  // bit -> The bit location from 0 to 7
  // reg -> The register (A, B, C, D, E, H, L, (HL))
  //
  // Flags affected:
  //   Z (zero) - Set if bit |bit| of register |reg| is 0.
  //   N (subtraction) - Reset.
  //   H (half carry) - Set.
  //   C (carry) - Not affected.
  public static void bit(Processor.EightBitRegister reg, Processor.FlagsRegister flags, int bit) {
    if (reg.checkBit(bit)) {
      flags.unsetZero();
    } else {
      flags.setZero();
    }
    flags.unsetSubtraction();
    flags.setHalfCarry();
  }

  // SET (b,r)
  //
  // Set bit |bit| in register |reg|.
  //
  // bit -> The bit location from 0 to 7.
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   None.
  public static void set(Processor.EightBitRegister reg, int bit) {
    reg.setBit(bit);
  }

  // RES (b,r)
  //
  // Reset bit |bit| in register |reg|.
  //
  // bit -> The bit location from 0 to 7.
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   None.
  public static void reset(Processor.EightBitRegister reg, int bit) {
    reg.resetBit(bit);
  }

  // SRL (n)
  //
  // Shift n (register |reg|) right into Carry. Most significant bit set to 0.
  //
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   Z (zero) - Set if result is zero.
  //   N (subtraction) - Reset.
  //   H (half carry) - Reset.
  //   C (carry) - Contains old bit 0 data.
  public static void shiftRightLogical(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    flags.reset();
    if (reg.checkBit(0)) {
      flags.setCarry();
    }
    reg.shiftRightUnsigned();
    flags.setZeroFromRegister(reg);
  }

  // SWAP (n)
  //
  // Swap upper and lower nibbles of register n (|reg|).
  //
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   Z (zero) - Set if result is zero.
  //   N (subtraction) - Reset.
  //   H (half carry) - Reset.
  //   C (carry) - Reset.
  public static void swap(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    flags.reset();
    reg.swapNibbles();
    flags.setZeroFromRegister(reg);
  }

  // SRA (n)
  //
  // Shift n (register |reg|) into Carry. Most significant bit does not change.
  //
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   Z (zero) - Set if result is zero.
  //   N (subtraction) - Reset.
  //   H (half carry) - Reset.
  //   C (carry) - Contains old bit 0 data.
  public static void shiftRightArithmetic(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    flags.reset();
    if (reg.checkBit(0)) {
      flags.setCarry();
    }
    reg.shiftRightSigned();
    flags.setZeroFromRegister(reg);
  }

  // SLA (n)
  //
  // Shift n (register |reg|) left into Carry. Least significant bit of n is set to 0.
  //
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   Z (zero) - Set if result is zero.
  //   N (subtraction) - Reset.
  //   H (half carry) - Reset.
  //   C (carry) - Contains old bit 7 data.
  public static void shiftLeftArithmetic(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    flags.reset();
    if (reg.checkBit(7)) {
      flags.setCarry();
    }
    reg.shiftLeft();
    flags.setZeroFromRegister(reg);
  }

  // RR (n)
  //
  // Rotate n (register |reg|) right through Carry flag.
  //
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   Z (zero) - Set if result is zero.
  //   N (subtraction) - Reset.
  //   H (half carry) - Reset.
  //   C (carry) - Contains old bit 0 data.
  public static void rotateRight(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    boolean hadCarry = flags.hasCarry();
    flags.reset();
    if (reg.checkBit(0)) {
      flags.setCarry();
    }
    reg.shiftRightUnsigned();
    if (hadCarry) {
      reg.setBit(7);
    }
    flags.setZeroFromRegister(reg);
  }

  // RL (n)
  //
  // Rotate n (register |reg|) left through Carry flag.
  //
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   Z (zero) - Set if result is zero.
  //   N (subtraction) - Reset.
  //   H (half carry) - Reset.
  //   C (carry) - Contains old bit 7 data.
  public static void rotateLeft(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    boolean hadCarry = flags.hasCarry();
    flags.reset();
    if (reg.checkBit(7)) {
      flags.setCarry();
    }
    reg.shiftLeft();
    if (hadCarry) {
      reg.setBit(0);
    }
    flags.setZeroFromRegister(reg);
  }

  // RRC (n)
  //
  // Rotate n (register |reg|) right. Old bit 0 to Carry flag.
  //
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   Z (zero) - Set if result is zero.
  //   N (subtraction) - Reset.
  //   H (half carry) - Reset.
  //   C (carry) - Contains old bit 0 data.
  public static void rotateRightAndCarry(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    boolean oldLeastSignificantBit = reg.checkBit(0);
    flags.reset();
    reg.shiftRightUnsigned();
    if (oldLeastSignificantBit) {
      flags.setCarry();
      reg.setBit(7);
    }
    flags.setZeroFromRegister(reg);
  }


  // RLC (n)
  //
  // Rotate n (register |reg|) left. Old bit 7 to Carry flag.
  //
  // reg -> The register (A, B, C, D, E, H, L, (HL)).
  //
  // Flags affected:
  //   Z (zero) - Set if result is zero.
  //   N (subtraction) - Reset.
  //   H (half carry) - Reset.
  //   C (carry) - Contains old bit 7 data.
  public static void rotateLeftAndCarry(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    boolean oldMostSignificantBit = reg.checkBit(7);
    flags.reset();
    reg.shiftLeft();
    if (oldMostSignificantBit) {
      flags.setCarry();
      reg.setBit(0);
    }
    flags.setZeroFromRegister(reg);
  }
}