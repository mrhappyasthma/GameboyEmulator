// This class implements reusable functions to help map many opcodes that do
// repetitive work.
//
// Resources:
//   http://pastraiser.com/cpu/gameboy/gameboy_opcodes.html
//   http://marc.rawer.de/Gameboy/Docs/GBCPUman.pdf
//   https://github.com/drhelius/Gearboy/blob/master/src/Processor_inline.h

public class OpcodeHelper {
  public static void bit(Processor.EightBitRegister reg, Processor.FlagsRegister flags, int bit) {
    if (reg.checkBit(bit)) {
      flags.unsetZero();
    } else {
      flags.setZero();
    }
    flags.unsetSubtraction();
    flags.unsetHalfCarry();
  }

  public static void shiftRightLogical(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    flags.reset();
    if (reg.checkBit(0)) {
      flags.setCarry();
    }
    reg.shiftRightUnsigned();
    flags.setZeroFromRegister(reg);
  }

  public static void shiftRightArithmetic(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    flags.reset();
    if (reg.checkBit(0)) {
      flags.setCarry();
    }
    reg.shiftRightSigned();
    flags.setZeroFromRegister(reg);
  }

  public static void shiftLeftArithmetic(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    flags.reset();
    if (reg.checkBit(7)) {
      flags.setCarry();
    }
    reg.shiftLeft();
    flags.setZeroFromRegister(reg);
  }

  public static void swap(Processor.EightBitRegister reg, Processor.FlagsRegister flags) {
    flags.reset();
    reg.swapNibbles();
    flags.setZeroFromRegister(reg);
  }
}