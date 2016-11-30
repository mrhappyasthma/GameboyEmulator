// This class mirrors the internal state of the Gameboy processor.
//
// You can read more about it here:
//   http://imrannazar.com/GameBoy-Emulation-in-JavaScript:-The-CPU
//
// The registers are also mapped out at the bottom of this page:
//   http://www.pastraiser.com/cpu/gameboy/gameboy_opcodes.html

public class Processor {
  // 8-bit registers:
  public EightBitRegister a = new EightBitRegister();  // Accumulator.
  public EightBitRegister b = new EightBitRegister();
  public EightBitRegister c = new EightBitRegister();
  public EightBitRegister d = new EightBitRegister();
  public EightBitRegister e = new EightBitRegister();
  public EightBitRegister h = new EightBitRegister();
  public EightBitRegister l = new EightBitRegister();
  public FlagsRegister flags = new FlagsRegister();

  // 16-bit registers:
  public SixteenBitRegister pc = new SixteenBitRegister();
  public SixteenBitRegister sp = new SixteenBitRegister();

  public void reset() {
    a.reset();
    b.reset();
    c.reset();
    d.reset();
    e.reset();
    h.reset();
    l.reset();
    flags.reset();
    pc.reset();
    sp.reset();
  }

  // 16-bit value getters from two combined registers containing high/low bits:
  public int af() {
    // Gets the combined a,f registers as a 16-bit value.
    return Processor.combinedRegistersValue(((EightBitRegister)a), flags);
  }

  public int bc() {
    // Gets the combined b,c registers as a 16-bit value.
    return Processor.combinedRegistersValue((EightBitRegister)b, (EightBitRegister)c);
  }

  public int de() {
    // Gets the combined d,e registers as a 16-bit value.
    return Processor.combinedRegistersValue((EightBitRegister)d, (EightBitRegister)e);
  }

  public int hl() {
    // Gets the combined h,l registers as a 16-bit value.
    return Processor.combinedRegistersValue((EightBitRegister)h, (EightBitRegister)l);
  }

  private static int combinedRegistersValue(EightBitRegister high, EightBitRegister low) {
    return (low.getValue() | (high.getValue() << 8));
  }

  private static int combinedRegistersValue(EightBitRegister high, FlagsRegister low) {
    return (low.getValue() | (high.getValue() << 8));
  }

  abstract class Register {
    // Returns an integer representing the binary state of the register.
    public abstract int getValue();

    // Resets the internal state of a register.
    public abstract void reset();
  }

  abstract class BitRegister extends Register {
    protected int realValue;

    // Returns the mask to determine the size of the register.
    protected abstract int mask();

    public void increment() {
      realValue = realValue + 1;
    }

    public void decrement() {
      realValue = realValue - 1;
    }

    public void setValue(int value) {
      realValue = value;
    }

    public int getValue() {
      return (realValue & mask());
    }

    public void reset() {
      realValue = 0;
    }
  }

  public class EightBitRegister extends BitRegister {
    protected int mask() {
      return 0xFF;
    }

    public void setBit(int bit) {
      if (bit < 0 || bit > 7) {
        System.out.println("Error setting bit " + bit + " on register " + this);
        return;
      }
      int mask = 1 << bit;
      realValue |= mask;
    }

    public void resetBit(int bit) {
      if (bit < 0 || bit > 7) {
        System.out.println("Error resetting bit " + bit + " on register " + this);
        return;
      }
      int mask = ~(1 << bit);
      realValue &= mask;
    }

    public void swapNibbles() {
      int val = getValue();
      int old_low = val & 0x0F;
      int old_high = (val >> 4) & 0x0F;
      realValue = old_high + (old_low << 4);
    }

    public void shiftRightSigned() {
      realValue >>>= 1;
    }
    public void shiftRightUnsigned() {
      realValue >>= 1;
    }

    public void shiftLeft() {
      realValue <<= 1;
    }

    public boolean checkBit(int bit) {
      if (bit < 0 || bit > 7) {
        System.out.println("Error checking bit " + bit + " on register " + this);
        return false;
      }
      int mask = 1 << bit;
      int bitValue = (realValue & mask) >> bit;
      return !(bitValue == 0);
    }

    public boolean hasCarry() {
      // This assumes the register already has the new value set, so the
      // carry can be determined.
      int carryMask = mask() + 1;
      return ((realValue & carryMask) == carryMask);
    }

    public boolean hasHalfCarry() {
      // This assumes the register already has the new value set, so the
      // half-carry can be determined.
      int halfCarryMask = (mask() + 1) / 2;
      return ((getValue() & halfCarryMask) == halfCarryMask);
    }
  }

  public class SixteenBitRegister extends BitRegister {
    protected int mask() {
      return 0xFFFF;
    }
  }

  public class FlagsRegister extends Register {
    // Set if the last operation produced a result of 0.
    private boolean zero;  // 0x80 (bit 7).
    // Set if the last operation was a subtraction.
    private boolean operation;  // 0x40 (bit 6).
    // Set if, in the result of the last operation, the lower half of the byte
    // overflowed. (That is 15 -> 16).
    private boolean halfCarry;  // 0x20 (bit 5).
    // Set if the last operation produced over 255 (for additions)
    // or under 0 (for subtractions).
    private boolean carry;  // 0x10 (bit 4).

    public int getValue() {
      // Returns the binary representation of the Flags register.
      int value = 0;
      if (zero) {
        value |= 0x80;
      }
      if (operation) {
        value |= 0x40;
      }
      if (halfCarry) {
        value |= 0x20;
      }
      if (carry) {
        value |= 0x10;
      }
      return value;
    }

    public void setHalfCarry() {
      halfCarry = true;
    }

    public void unsetHalfCarry() {
      halfCarry = false;
    }

    public void setCarry() {
      carry = true;
    }

    public void unsetCarry() {
      carry = false;
    }

    public void setSubtraction() {
      operation = true;
    }

    public void unsetSubtraction() {
      operation = false;
    }

    public void setZeroFromRegister(BitRegister reg) {
      if (reg.getValue() == 0) {
        setZero();
      } else {
        unsetZero();
      }
    }

    public void setZero() {
      zero = true;
    }

    public void unsetZero() {
      zero = false;
    }

    public void reset() {
      unsetZero();
      unsetSubtraction();
      unsetHalfCarry();
      unsetCarry();
    }
  }
}