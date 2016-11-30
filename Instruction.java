// This class represents an instruction for the Gameboy CPU.
// The instruction set is mapped out nicely at:
//   http://www.pastraiser.com/cpu/gameboy/gameboy_opcodes.html
//   http://imrannazar.com/Gameboy-Z80-Opcode-Map
//
// Additional resources:
//   http://marc.rawer.de/Gameboy/Docs/GBCPUman.pdf
//   https://github.com/CTurt/Cinoop/blob/master/source/cpu.c
//   https://github.com/CTurt/Cinoop/blob/master/source/cb.c
//
// Note: gameboy is little endian.

public class Instruction {
  private interface Executable {
    // There are no function pointers in Java, so we use this interface as a
    // hacky way to have a different, yet generic, function pointer for each
    // instruction.
    public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap);
  }

  int opcode;
  String description;
  int numDataBytes;  // The number of bytes excluding the opcode.
  int clockCycles;  // Note: the processor clock uses clockCycles/4.
  Executable executeFunction;

  public void execute(Processor processor, MemoryMap memoryMap) {
    // A wrapper function to the instruction function pointer hack.
    executeFunction.execute(this, processor, memoryMap);
  }

  public Instruction(int opcode, String description, int numBytes, int clockCycles, Executable executeFunction) {
    this.opcode = opcode;
    this.description = description;
    this.numDataBytes = numBytes - 1;
    this.clockCycles = clockCycles;
    this.executeFunction = executeFunction;
  }

  private static boolean isByteValid(int b) {
    return (b < 0x00 || b > 0xFF) ? false : true;
  }

  public String toString() {
    return description;
  }

  public static Instruction instructionForOpcode(int opcode) {
    if (!isByteValid(opcode)) {
      throw new IllegalArgumentException("opcode must be between 0x00 and 0xFF.");
    }

    // Note: The bytes count should include the opcode. We adjust
    // internally to the class.
    switch (opcode) {
      case 0x00:
        return new Instruction(opcode, "NOP", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x01:
        return new Instruction(opcode, "LD BC, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x02:
        return new Instruction(opcode, "LD (BC), A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x03:
        return new Instruction(opcode, "INC BC", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x04:
        return new Instruction(opcode, "INC B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x05:
        return new Instruction(opcode, "DEC B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x06:
        return new Instruction(opcode, "LD B, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x07:
        return new Instruction(opcode, "RLCA", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x08:
        return new Instruction(opcode, "LD (0x%04X), SP", 3, 20, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x09:
        return new Instruction(opcode, "ADD HL, BC", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0A:
        return new Instruction(opcode, "LD A, (BC)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0B:
        return new Instruction(opcode, "DEC BC", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0C:
        return new Instruction(opcode, "INC C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0D:
        return new Instruction(opcode, "DEC C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0E:
        return new Instruction(opcode, "LD C, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0F:
        return new Instruction(opcode, "RRCA", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x10:
        // This technically takes 2 bytes for some reason... so skip the last one.
        return new Instruction(opcode, "STOP", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x11:
        return new Instruction(opcode, "LD DE, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x12:
        return new Instruction(opcode, "LD (DE), A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x13:
        return new Instruction(opcode, "INC DE", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x14:
        return new Instruction(opcode, "INC D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x15:
        return new Instruction(opcode, "DEC D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x16:
        return new Instruction(opcode, "LD D, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x17:
        return new Instruction(opcode, "RLA", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x18:
        return new Instruction(opcode, "JR 0x%02X", 2, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x19:
        return new Instruction(opcode, "ADD HL, DE", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1A:
        return new Instruction(opcode, "LD A, (DE)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1B:
        return new Instruction(opcode, "DEC DE", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1C:
        return new Instruction(opcode, "INC E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1D:
        return new Instruction(opcode, "DEC E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1E:
        return new Instruction(opcode, "LD E, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1F:
        return new Instruction(opcode, "RRA", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x20:
        return new Instruction(opcode, "JR NZ, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });  // Add +4 ticks if action is taken.
      case 0x21:
        return new Instruction(opcode, "LD HL, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x22:
        return new Instruction(opcode, "LD (HL+), A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x23:
        return new Instruction(opcode, "INC HL", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x24:
        return new Instruction(opcode, "INC H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x25:
        return new Instruction(opcode, "DEC H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x26:
        return new Instruction(opcode, "LD H, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x27:
        return new Instruction(opcode, "DAA", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x28:
        return new Instruction(opcode, "JR Z, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +4 ticks if condition is met.
      case 0x29:
        return new Instruction(opcode, "ADD HL, HL", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2A:
        return new Instruction(opcode, "LD A, (HL+)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2B:
        return new Instruction(opcode, "DEC HL", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2C:
        return new Instruction(opcode, "INC L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2D:
        return new Instruction(opcode, "DEC L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2E:
        return new Instruction(opcode, "LD L, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2F:
        return new Instruction(opcode, "CPL", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x30:
        return new Instruction(opcode, "JR NC, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });  // Adds +4 to the ticks if condition is met.
      case 0x31:
        return new Instruction(opcode, "LD SP, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x32:
        return new Instruction(opcode, "LD (HL-), A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x33:
        return new Instruction(opcode, "INC SP", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x34:
        return new Instruction(opcode, "INC (HL)", 1, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x35:
        return new Instruction(opcode, "DEC (HL)", 1, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x36:
        return new Instruction(opcode, "LD (HL), 0x%02X", 2, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x37:
        return new Instruction(opcode, "SCF", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x38:
        return new Instruction(opcode, "JR C, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });  // Adds +4 to the ticks if condition is met.
      case 0x39:
        return new Instruction(opcode, "ADD HL, SP", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3A:
        return new Instruction(opcode, "LD A, (HL-)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3B:
        return new Instruction(opcode, "DEC SP", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3C:
        return new Instruction(opcode, "INC A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3D:
        return new Instruction(opcode, "DEC A", 1 ,4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3E:
        return new Instruction(opcode, "LD A, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3F:
        return new Instruction(opcode, "CCF", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x40:
        return new Instruction(opcode, "LD B, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x41:
        return new Instruction(opcode, "LD B, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x42:
        return new Instruction(opcode, "LD B, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x43:
        return new Instruction(opcode, "LD B, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x44:
        return new Instruction(opcode, "LD B, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x45:
        return new Instruction(opcode, "LD B, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x46:
        return new Instruction(opcode, "LD B, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x47:
        return new Instruction(opcode, "LD B, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x48:
        return new Instruction(opcode, "LD C, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x49:
        return new Instruction(opcode, "LD C, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x4A:
        return new Instruction(opcode, "LD C, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x4B:
        return new Instruction(opcode, "LD C, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x4C:
        return new Instruction(opcode, "LD C, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x4D:
        return new Instruction(opcode, "LD C, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x4E:
        return new Instruction(opcode, "LD C, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x4F:
        return new Instruction(opcode, "LD C, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x50:
        return new Instruction(opcode, "LD D, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x51:
        return new Instruction(opcode, "LD D, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x52:
        return new Instruction(opcode, "LD D, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x53:
        return new Instruction(opcode, "LD D, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x54:
        return new Instruction(opcode, "LD D, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x55:
        return new Instruction(opcode, "LD D, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x56:
        return new Instruction(opcode, "LD D, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x57:
        return new Instruction(opcode, "LD D, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x58:
        return new Instruction(opcode, "LD E, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x59:
        return new Instruction(opcode, "LD E, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x5A:
        return new Instruction(opcode, "LD E, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x5B:
        return new Instruction(opcode, "LD E, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x5C:
        return new Instruction(opcode, "LD E, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x5D:
        return new Instruction(opcode, "LD E, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x5E:
        return new Instruction(opcode, "LD E, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x5F:
        return new Instruction(opcode, "LD E, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x60:
        return new Instruction(opcode, "LD H, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x61:
        return new Instruction(opcode, "LD H, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x62:
        return new Instruction(opcode, "LD H, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x63:
        return new Instruction(opcode, "LD H, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x64:
        return new Instruction(opcode, "LD H, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x65:
        return new Instruction(opcode, "LD H, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x66:
        return new Instruction(opcode, "LD H, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x67:
        return new Instruction(opcode, "LD H, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x68:
        return new Instruction(opcode, "LD L, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x69:
        return new Instruction(opcode, "LD L, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x6A:
        return new Instruction(opcode, "LD L, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x6B:
        return new Instruction(opcode, "LD L, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x6C:
        return new Instruction(opcode, "LD L, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x6D:
        return new Instruction(opcode, "LD L, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x6E:
        return new Instruction(opcode, "LD L, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x6F:
        return new Instruction(opcode, "LD L, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x70:
        return new Instruction(opcode, "LD (HL), B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x71:
        return new Instruction(opcode, "LD (HL), C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x72:
        return new Instruction(opcode, "LD (HL), D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x73:
        return new Instruction(opcode, "LD (HL), E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x74:
        return new Instruction(opcode, "LD (HL), H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x75:
        return new Instruction(opcode, "LD (HL), L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x76:
        return new Instruction(opcode, "HALT", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x77:
        return new Instruction(opcode, "LD (HL), A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x78:
        return new Instruction(opcode, "LD A, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x79:
        return new Instruction(opcode, "LD A, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x7A:
        return new Instruction(opcode, "LD A, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x7B:
        return new Instruction(opcode, "LD A, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x7C:
        return new Instruction(opcode, "LD A, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x7D:
        return new Instruction(opcode, "LD A, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x7E:
        return new Instruction(opcode, "LD A, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x7F:
        return new Instruction(opcode, "LD A, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x80:
        return new Instruction(opcode, "ADD A, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x81:
        return new Instruction(opcode, "ADD A, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x82:
        return new Instruction(opcode, "ADD A, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x83:
        return new Instruction(opcode, "ADD A, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x84:
        return new Instruction(opcode, "ADD A, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x85:
        return new Instruction(opcode, "ADD A, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x86:
        return new Instruction(opcode, "ADD A, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x87:
        return new Instruction(opcode, "ADD A, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x88:
        return new Instruction(opcode, "ADC A, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x89:
        return new Instruction(opcode, "ADC A, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x8A:
        return new Instruction(opcode, "ADC A, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x8B:
        return new Instruction(opcode, "ADC A, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x8C:
        return new Instruction(opcode, "ADC A, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x8D:
        return new Instruction(opcode, "ADC A, L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x8E:
        return new Instruction(opcode, "ADC A, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x8F:
        return new Instruction(opcode, "ADC A, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x90:
        return new Instruction(opcode, "SUB B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x91:
        return new Instruction(opcode, "SUB C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x92:
        return new Instruction(opcode, "SUB D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x93:
        return new Instruction(opcode, "SUB E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x94:
        return new Instruction(opcode, "SUB H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x95:
        return new Instruction(opcode, "SUB L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x96:
        return new Instruction(opcode, "SUB (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x97:
        return new Instruction(opcode, "SUB A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x98:
        return new Instruction(opcode, "SBC A, B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x99:
        return new Instruction(opcode, "SBC A, C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x9A:
        return new Instruction(opcode, "SBC A, D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x9B:
        return new Instruction(opcode, "SBC A, E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x9C:
        return new Instruction(opcode, "SBC A, H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x9D:
        return new Instruction(opcode, "SBC A, L", 1 ,4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x9E:
        return new Instruction(opcode, "SBC A, (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x9F:
        return new Instruction(opcode, "SBC A, A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA0:
        return new Instruction(opcode, "AND B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA1:
        return new Instruction(opcode, "AND C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA2:
        return new Instruction(opcode, "AND D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA3:
        return new Instruction(opcode, "AND E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA4:
        return new Instruction(opcode, "AND H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA5:
        return new Instruction(opcode, "AND L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA6:
        return new Instruction(opcode, "AND (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA7:
        return new Instruction(opcode, "AND A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA8:
        return new Instruction(opcode, "XOR B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA9:
        return new Instruction(opcode, "XOR C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xAA:
        return new Instruction(opcode, "XOR D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xAB:
        return new Instruction(opcode, "XOR E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xAC:
        return new Instruction(opcode, "XOR H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xAD:
        return new Instruction(opcode, "XOR L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xAE:
        return new Instruction(opcode, "XOR (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xAF:
        return new Instruction(opcode, "XOR A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB0:
        return new Instruction(opcode, "OR B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB1:
        return new Instruction(opcode, "OR C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB2:
        return new Instruction(opcode, "OR D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB3:
        return new Instruction(opcode, "OR E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB4:
        return new Instruction(opcode, "OR H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB5:
        return new Instruction(opcode, "OR L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB6:
        return new Instruction(opcode, "OR (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB7:
        return new Instruction(opcode, "OR A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB8:
        return new Instruction(opcode, "CP B", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB9:
        return new Instruction(opcode, "CP C", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xBA:
        return new Instruction(opcode, "CP D", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xBB:
        return new Instruction(opcode, "CP E", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xBC:
        return new Instruction(opcode, "CP H", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xBD:
        return new Instruction(opcode, "CP L", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xBE:
        return new Instruction(opcode, "CP (HL)", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xBF:
        return new Instruction(opcode, "CP A", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xC0:
        return new Instruction(opcode, "RET NZ", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +12 to ticks if condition is met.
      case 0xC1:
        return new Instruction(opcode, "POP BC", 1, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xC2:
        return new Instruction(opcode, "JP NZ, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +4 to ticks if condition is met.
      case 0xC3:
        return new Instruction(opcode, "JP 0x%04X", 3, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xC4:
        return new Instruction(opcode, "CALL NZ, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +12 to the ticks if condition is met.
      case 0xC5:
        return new Instruction(opcode, "PUSH BC", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xC6:
        return new Instruction(opcode, "ADD A, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xC7:
        return new Instruction(opcode, "RST 00H", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xC8:
        return new Instruction(opcode, "RET Z", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +12 to the ticks if condition is met.
      case 0xC9:
        return new Instruction(opcode, "RET", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xCA:
        return new Instruction(opcode, "JP Z, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +4 if the condition is met.
      case 0xCB:
        return new Instruction(opcode, "PREFIX CB", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xCC:
        return new Instruction(opcode, "CALL Z, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +12 if the confition is met.
      case 0xCD:
        return new Instruction(opcode, "CALL 0x%04X", 3, 24, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xCE:
        return new Instruction(opcode, "ADC A, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xCF:
        return new Instruction(opcode, "RST 08H", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xD0:
        return new Instruction(opcode, "RET NC", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +12 ticks if condition is met.
      case 0xD1:
        return new Instruction(opcode, "POP DE", 1, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xD2:
        return new Instruction(opcode, "JP NC, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +4 ticks if condition is met.
      case 0xD3:
        break;  // Unused.
      case 0xD4:
        return new Instruction(opcode, "CALL NC, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +12 ticks if condition is met.
      case 0xD5:
        return new Instruction(opcode, "PUSH DE", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xD6:
        return new Instruction(opcode, "SUB 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xD7:
        return new Instruction(opcode, "RST 10H", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xD8:
        return new Instruction(opcode, "RET C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +12 ticks if condition is met.
      case 0xD9:
        return new Instruction(opcode, "RETI", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xDA:
        return new Instruction(opcode, "JP C, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +4 if condition is met.
      case 0xDB:
        break;  // Unused.
      case 0xDC:
        return new Instruction(opcode, "CALL C, 0x%04X", 3, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        }); // Adds +12 if condition is met.
      case 0xDD:
        break;  // Unused.
      case 0xDE:
        return new Instruction(opcode, "SBC A, 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xDF:
        return new Instruction(opcode, "RST 18H", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE0:
        return new Instruction(opcode, "LDH (0x%02X), A", 2, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE1:
        return new Instruction(opcode, "POP HL", 1, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE2:
        return new Instruction(opcode, "LD (C), A", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE3:
        break;  // Unused.
      case 0xE4:
        break;  // Unused.
      case 0xE5:
        return new Instruction(opcode, "PUSH HL", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE6:
        return new Instruction(opcode, "AND 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE7:
        return new Instruction(opcode, "RST 20H", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE8:
        return new Instruction(opcode, "ADD SP, 0x%02X", 2, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE9:
        return new Instruction(opcode, "JP (HL)", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xEA:
        return new Instruction(opcode, "LD (0x%04X), A", 3, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xEB:
        break;  // Unused.
      case 0xEC:
        break;  // Unused.
      case 0xED:
        break;  // Unused.
      case 0xEE:
        return new Instruction(opcode, "XOR 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xEF:
        return new Instruction(opcode, "RST 28H", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF0:
        return new Instruction(opcode, "LDH A, (0x%02X)", 2, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF1:
        return new Instruction(opcode, "POP AF", 1, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF2:
        return new Instruction(opcode, "LD A, (C)", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF3:
        return new Instruction(opcode, "DI", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF4:
        break;  // Unused.
      case 0xF5:
        return new Instruction(opcode, "PUSH AF", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF6:
        return new Instruction(opcode, "OR 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF7:
        return new Instruction(opcode, "RST 30H", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF8:
        return new Instruction(opcode, "LD HL, SP + 0x%02X", 2, 12, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF9:
        return new Instruction(opcode, "LD SP, HL", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xFA:
        return new Instruction(opcode, "LD A, (0x%04X)", 3, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xFB:
        return new Instruction(opcode, "EI", 1, 4, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xFC:
        break;  // Unused.
      case 0xFD:
        break;  // Unused.
      case 0xFE:
        return new Instruction(opcode, "CP 0x%02X", 2, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xFF:
        return new Instruction(opcode, "RST 38H", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
    }
    return null;
  }

  // Two-byte opcodes used after encountering a 0xCB.
  public static Instruction prefixCBForOpcode(int opcode) {
    if (!isByteValid(opcode)) {
      throw new IllegalArgumentException("opcode must be between 0x00 and 0xFF.");
    }

    // Note: The bytes count should include the opcode. We adjust
    // internally to the class.

    // Note2: The 0xCB instructions are labeled as "2 bytes", but I believe
    // this includes the 0xCB prefix byte. So I set the bytes to "1".
    switch (opcode) {
      case 0x00:
        return new Instruction(opcode, "RLC B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x01:
        return new Instruction(opcode, "RLC C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x02:
        return new Instruction(opcode, "RLC D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x03:
        return new Instruction(opcode, "RLC E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x04:
        return new Instruction(opcode, "RLC H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x05:
        return new Instruction(opcode, "RLC L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x06:
        return new Instruction(opcode, "RLC (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x07:
        return new Instruction(opcode, "RLC A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x08:
        return new Instruction(opcode, "RRC B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x09:
        return new Instruction(opcode, "RRC C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0A:
        return new Instruction(opcode, "RRC D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0B:
        return new Instruction(opcode, "RRC E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0C:
        return new Instruction(opcode, "RRC H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0D:
        return new Instruction(opcode, "RRC L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0E:
        return new Instruction(opcode, "RRC (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x0F:
        return new Instruction(opcode, "RRC A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x10:
        return new Instruction(opcode, "RL B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x11:
        return new Instruction(opcode, "RL C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x12:
        return new Instruction(opcode, "RL D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x13:
        return new Instruction(opcode, "RL E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x14:
        return new Instruction(opcode, "RL H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x15:
        return new Instruction(opcode, "RL L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x16:
        return new Instruction(opcode, "RL (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x17:
        return new Instruction(opcode, "RL A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x18:
        return new Instruction(opcode, "RR B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x19:
        return new Instruction(opcode, "RR C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1A:
        return new Instruction(opcode, "RR D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1B:
        return new Instruction(opcode, "RR E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1C:
        return new Instruction(opcode, "RR H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1D:
        return new Instruction(opcode, "RR L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1E:
        return new Instruction(opcode, "RR (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x1F:
        return new Instruction(opcode, "RR A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x20:
        return new Instruction(opcode, "SLA B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x21:
        return new Instruction(opcode, "SLA C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x22:
        return new Instruction(opcode, "SLA D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x23:
        return new Instruction(opcode, "SLA E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x24:
        return new Instruction(opcode, "SLA H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x25:
        return new Instruction(opcode, "SLA L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x26:
        return new Instruction(opcode, "SLA (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x27:
        return new Instruction(opcode, "SLA A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x28:
        return new Instruction(opcode, "SRA B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x29:
        return new Instruction(opcode, "SRA C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2A:
        return new Instruction(opcode, "SRA D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2B:
        return new Instruction(opcode, "SRA E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2C:
        return new Instruction(opcode, "SRA H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2D:
        return new Instruction(opcode, "SRA L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2E:
        return new Instruction(opcode, "SRA (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x2F:
        return new Instruction(opcode, "SRA A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x30:
        return new Instruction(opcode, "SWAP B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x31:
        return new Instruction(opcode, "SWAP C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x32:
        return new Instruction(opcode, "SWAP D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x33:
        return new Instruction(opcode, "SWAP E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x34:
        return new Instruction(opcode, "SWAP H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x35:
        return new Instruction(opcode, "SWAP L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x36:
        return new Instruction(opcode, "SWAP (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x37:
        return new Instruction(opcode, "SWAP A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x38:
        return new Instruction(opcode, "SRL B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x39:
        return new Instruction(opcode, "SRL C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3A:
        return new Instruction(opcode, "SRL D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3B:
        return new Instruction(opcode, "SRL E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3C:
        return new Instruction(opcode, "SRL H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3D:
        return new Instruction(opcode, "SRL L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3E:
        return new Instruction(opcode, "SRL (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x3F:
        return new Instruction(opcode, "SRL A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x40:
        return new Instruction(opcode, "BIT 0, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.b, processor.flags, 0);
          }
        });
      case 0x41:
        return new Instruction(opcode, "BIT 0, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.c, processor.flags, 0);
          }
        });
      case 0x42:
        return new Instruction(opcode, "BIT 0, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.d, processor.flags, 0);
          }
        });
      case 0x43:
        return new Instruction(opcode, "BIT 0, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.e, processor.flags, 0);
          }
        });
      case 0x44:
        return new Instruction(opcode, "BIT 0, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.h, processor.flags, 0);
          }
        });
      case 0x45:
        return new Instruction(opcode, "BIT 0, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.l, processor.flags, 0);
          }
        });
      case 0x46:
        return new Instruction(opcode, "BIT 0, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x47:
        return new Instruction(opcode, "BIT 0, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.a, processor.flags, 0);
          }
        });
      case 0x48:
        return new Instruction(opcode, "BIT 1, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.b, processor.flags, 1);
          }
        });
      case 0x49:
        return new Instruction(opcode, "BIT 1, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.c, processor.flags, 1);
          }
        });
      case 0x4A:
        return new Instruction(opcode, "BIT 1, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.d, processor.flags, 1);
          }
        });
      case 0x4B:
        return new Instruction(opcode, "BIT 1, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.e, processor.flags, 1);
          }
        });
      case 0x4C:
        return new Instruction(opcode, "BIT 1, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.h, processor.flags, 1);
          }
        });
      case 0x4D:
        return new Instruction(opcode, "BIT 1, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.l, processor.flags, 1);
          }
        });
      case 0x4E:
        return new Instruction(opcode, "BIT 1, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x4F:
        return new Instruction(opcode, "BIT 1, A", 1 ,8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.a, processor.flags, 1);
          }
        });
      case 0x50:
        return new Instruction(opcode, "BIT 2, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.b, processor.flags, 2);
          }
        });
      case 0x51:
        return new Instruction(opcode, "BIT 2, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.c, processor.flags, 2);
          }
        });
      case 0x52:
        return new Instruction(opcode, "BIT 2, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.d, processor.flags, 2);
          }
        });
      case 0x53:
        return new Instruction(opcode, "BIT 2, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.e, processor.flags, 2);
          }
        });
      case 0x54:
        return new Instruction(opcode, "BIT 2, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.h, processor.flags, 2);
          }
        });
      case 0x55:
        return new Instruction(opcode, "BIT 2, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.l, processor.flags, 2);
          }
        });
      case 0x56:
        return new Instruction(opcode, "BIT 2, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x57:
        return new Instruction(opcode, "BIT 2, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.a, processor.flags, 2);
          }
        });
      case 0x58:
        return new Instruction(opcode, "BIT 3, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.b, processor.flags, 3);
          }
        });
      case 0x59:
        return new Instruction(opcode, "BIT 3, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.c, processor.flags, 3);
          }
        });
      case 0x5A:
        return new Instruction(opcode, "BIT 3, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.d, processor.flags, 3);
          }
        });
      case 0x5B:
        return new Instruction(opcode, "BIT 3, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.e, processor.flags, 3);
          }
        });
      case 0x5C:
        return new Instruction(opcode, "BIT 3, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.h, processor.flags, 3);
          }
        });
      case 0x5D:
        return new Instruction(opcode, "BIT 3, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.l, processor.flags, 3);
          }
        });
      case 0x5E:
        return new Instruction(opcode, "BIT 3, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x5F:
        return new Instruction(opcode, "BIT 3, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.a, processor.flags, 3);
          }
        });
      case 0x60:
        return new Instruction(opcode, "BIT 4, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.b, processor.flags, 4);
          }
        });
      case 0x61:
        return new Instruction(opcode, "BIT 4, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.c, processor.flags, 4);
          }
        });
      case 0x62:
        return new Instruction(opcode, "BIT 4, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.d, processor.flags, 4);
          }
        });
      case 0x63:
        return new Instruction(opcode, "BIT 4, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.e, processor.flags, 4);
          }
        });
      case 0x64:
        return new Instruction(opcode, "BIT 4, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.h, processor.flags, 4);
          }
        });
      case 0x65:
        return new Instruction(opcode, "BIT 4, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.l, processor.flags, 4);
          }
        });
      case 0x66:
        return new Instruction(opcode, "BIT 4, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x67:
        return new Instruction(opcode, "BIT 4, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.a, processor.flags, 4);
          }
        });
      case 0x68:
        return new Instruction(opcode, "BIT 5, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.b, processor.flags, 5);
          }
        });
      case 0x69:
        return new Instruction(opcode, "BIT 5, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.c, processor.flags, 5);
          }
        });
      case 0x6A:
        return new Instruction(opcode, "BIT 5, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.d, processor.flags, 5);
          }
        });
      case 0x6B:
        return new Instruction(opcode, "BIT 5, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.e, processor.flags, 5);
          }
        });
      case 0x6C:
        return new Instruction(opcode, "BIT 5, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.h, processor.flags, 5);
          }
        });
      case 0x6D:
        return new Instruction(opcode, "BIT 5, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.l, processor.flags, 5);
          }
        });
      case 0x6E:
        return new Instruction(opcode, "BIT 5, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x6F:
        return new Instruction(opcode, "BIT 5, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.a, processor.flags, 5);
          }
        });
      case 0x70:
        return new Instruction(opcode, "BIT 6, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.b, processor.flags, 6);
          }
        });
      case 0x71:
        return new Instruction(opcode, "BIT 6, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.c, processor.flags, 6);
          }
        });
      case 0x72:
        return new Instruction(opcode, "BIT 6, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.d, processor.flags, 6);
          }
        });
      case 0x73:
        return new Instruction(opcode, "BIT 6, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.e, processor.flags, 6);
          }
        });
      case 0x74:
        return new Instruction(opcode, "BIT 6, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.h, processor.flags, 6);
          }
        });
      case 0x75:
        return new Instruction(opcode, "BIT 6, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.l, processor.flags, 6);
          }
        });
      case 0x76:
        return new Instruction(opcode, "BIT 6, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x77:
        return new Instruction(opcode, "BIT 6, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.a, processor.flags, 6);
          }
        });
      case 0x78:
        return new Instruction(opcode, "BIT 7, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.b, processor.flags, 7);
          }
        });
      case 0x79:
        return new Instruction(opcode, "BIT 7, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.c, processor.flags, 7);
          }
        });
      case 0x7A:
        return new Instruction(opcode, "BIT 7, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.d, processor.flags, 7);
          }
        });
      case 0x7B:
        return new Instruction(opcode, "BIT 7, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.e, processor.flags, 7);
          }
        });
      case 0x7C:
        return new Instruction(opcode, "BIT 7, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.h, processor.flags, 7);
          }
        });
      case 0x7D:
        return new Instruction(opcode, "BIT 7, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.l, processor.flags, 7);
          }
        });
      case 0x7E:
        return new Instruction(opcode, "BIT 7, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x7F:
        return new Instruction(opcode, "BIT 7, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            OpcodeHelper.bit(processor.a, processor.flags, 7);
          }
        });
      case 0x80:
        return new Instruction(opcode, "RES 0, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.resetBit(0);
          }
        });
      case 0x81:
        return new Instruction(opcode, "RES 0, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.resetBit(0);
          }
        });
      case 0x82:
        return new Instruction(opcode, "RES 0, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.resetBit(0);
          }
        });
      case 0x83:
        return new Instruction(opcode, "RES 0, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.resetBit(0);
          }
        });
      case 0x84:
        return new Instruction(opcode, "RES 0, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.resetBit(0);
          }
        });
      case 0x85:
        return new Instruction(opcode, "RES 0, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.resetBit(0);
          }
        });
      case 0x86:
        return new Instruction(opcode, "RES 0, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x87:
        return new Instruction(opcode, "RES 0, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.resetBit(0);
          }
        });
      case 0x88:
        return new Instruction(opcode, "RES 1, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
           processor.b.resetBit(1);
          }
        });
      case 0x89:
        return new Instruction(opcode, "RES 1, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.resetBit(1);
          }
        });
      case 0x8A:
        return new Instruction(opcode, "RES 1, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.resetBit(1);
          }
        });
      case 0x8B:
        return new Instruction(opcode, "RES 1, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.resetBit(1);
          }
        });
      case 0x8C:
        return new Instruction(opcode, "RES 1, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.resetBit(1);
          }
        });
      case 0x8D:
        return new Instruction(opcode, "RES 1, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.resetBit(1);
          }
        });
      case 0x8E:
        return new Instruction(opcode, "RES 1, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x8F:
        return new Instruction(opcode, "RES 1, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.resetBit(1);
          }
        });
      case 0x90:
        return new Instruction(opcode, "RES 2, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.resetBit(2);
          }
        });
      case 0x91:
        return new Instruction(opcode, "RES 2, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.resetBit(2);
          }
        });
      case 0x92:
        return new Instruction(opcode, "RES 2, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.resetBit(2);
          }
        });
      case 0x93:
        return new Instruction(opcode, "RES 2, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.resetBit(2);
          }
        });
      case 0x94:
        return new Instruction(opcode, "RES 2, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.resetBit(2);
          }
        });
      case 0x95:
        return new Instruction(opcode, "RES 2, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.resetBit(2);
          }
        });
      case 0x96:
        return new Instruction(opcode, "RES 2, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x97:
        return new Instruction(opcode, "RES 2, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.resetBit(2);
          }
        });
      case 0x98:
        return new Instruction(opcode, "RES 3, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.resetBit(3);
          }
        });
      case 0x99:
        return new Instruction(opcode, "RES 3, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.resetBit(3);
          }
        });
      case 0x9A:
        return new Instruction(opcode, "RES 3, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.resetBit(3);
          }
        });
      case 0x9B:
        return new Instruction(opcode, "RES 3, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.resetBit(3);
          }
        });
      case 0x9C:
        return new Instruction(opcode, "RES 3, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.resetBit(3);
          }
        });
      case 0x9D:
        return new Instruction(opcode, "RES 3, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.resetBit(3);
          }
        });
      case 0x9E:
        return new Instruction(opcode, "RES 3, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0x9F:
        return new Instruction(opcode, "RES 3, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.resetBit(3);
          }
        });
      case 0xA0:
        return new Instruction(opcode, "RES 4, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.resetBit(4);
          }
        });
      case 0xA1:
        return new Instruction(opcode, "RES 4, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.resetBit(4);
          }
        });
      case 0xA2:
        return new Instruction(opcode, "RES 4, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.resetBit(4);
          }
        });
      case 0xA3:
        return new Instruction(opcode, "RES 4, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.resetBit(4);
          }
        });
      case 0xA4:
        return new Instruction(opcode, "RES 4, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.resetBit(4);
          }
        });
      case 0xA5:
        return new Instruction(opcode, "RES 4, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.resetBit(4);
          }
        });
      case 0xA6:
        return new Instruction(opcode, "RES 4, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xA7:
        return new Instruction(opcode, "RES 4, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.resetBit(4);
          }
        });
      case 0xA8:
        return new Instruction(opcode, "RES 5, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.resetBit(5);
          }
        });
      case 0xA9:
        return new Instruction(opcode, "RES 5, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.resetBit(5);
          }
        });
      case 0xAA:
        return new Instruction(opcode, "RES 5, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.resetBit(5);
          }
        });
      case 0xAB:
        return new Instruction(opcode, "RES 5, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.resetBit(5);
          }
        });
      case 0xAC:
        return new Instruction(opcode, "RES 5, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.resetBit(5);
          }
        });
      case 0xAD:
        return new Instruction(opcode, "RES 5, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.resetBit(5);
          }
        });
      case 0xAE:
        return new Instruction(opcode, "RES 5, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xAF:
        return new Instruction(opcode, "RES 5, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.resetBit(5);
          }
        });
      case 0xB0:
        return new Instruction(opcode, "RES 6, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.resetBit(6);
          }
        });
      case 0xB1:
        return new Instruction(opcode, "RES 6, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.resetBit(6);
          }
        });
      case 0xB2:
        return new Instruction(opcode, "RES 6, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.resetBit(6);
          }
        });
      case 0xB3:
        return new Instruction(opcode, "RES 6, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.resetBit(6);
          }
        });
      case 0xB4:
        return new Instruction(opcode, "RES 6, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.resetBit(6);
          }
        });
      case 0xB5:
        return new Instruction(opcode, "RES 6, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.resetBit(6);
          }
        });
      case 0xB6:
        return new Instruction(opcode, "RES 6, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xB7:
        return new Instruction(opcode, "RES 6, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.resetBit(6);
          }
        });
      case 0xB8:
        return new Instruction(opcode, "RES 7, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.resetBit(7);
          }
        });
      case 0xB9:
        return new Instruction(opcode, "RES 7, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.resetBit(7);
          }
        });
      case 0xBA:
        return new Instruction(opcode, "RES 7, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.resetBit(7);
          }
        });
      case 0xBB:
        return new Instruction(opcode, "RES 7, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.resetBit(7);
          }
        });
      case 0xBC:
        return new Instruction(opcode, "RES 7, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.resetBit(7);
          }
        });
      case 0xBD:
        return new Instruction(opcode, "RES 7, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.resetBit(7);
          }
        });
      case 0xBE:
        return new Instruction(opcode, "RES 7, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xBF:
        return new Instruction(opcode, "RES 7, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.resetBit(7);
          }
        });
      case 0xC0:
        return new Instruction(opcode, "SET 0, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.setBit(0);
          }
        });
      case 0xC1:
        return new Instruction(opcode, "SET 0, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.setBit(0);
          }
        });
      case 0xC2:
        return new Instruction(opcode, "SET 0, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.setBit(0);
          }
        });
      case 0xC3:
        return new Instruction(opcode, "SET 0, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.setBit(0);
          }
        });
      case 0xC4:
        return new Instruction(opcode, "SET 0, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.setBit(0);
          }
        });
      case 0xC5:
        return new Instruction(opcode, "SET 0, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.setBit(0);
          }
        });
      case 0xC6:
        return new Instruction(opcode, "SET 0, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xC7:
        return new Instruction(opcode, "SET 0, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.setBit(0);
          }
        });
      case 0xC8:
        return new Instruction(opcode, "SET 1, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.setBit(1);
          }
        });
      case 0xC9:
        return new Instruction(opcode, "SET 1, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.setBit(1);
          }
        });
      case 0xCA:
        return new Instruction(opcode, "SET 1, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.setBit(1);
          }
        });
      case 0xCB:
        return new Instruction(opcode, "SET 1, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.setBit(1);
          }
        });
      case 0xCC:
        return new Instruction(opcode, "SET 1, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.setBit(1);
          }
        });
      case 0xCD:
        return new Instruction(opcode, "SET 1, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.setBit(1);
          }
        });
      case 0xCE:
        return new Instruction(opcode, "SET 1, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xCF:
        return new Instruction(opcode, "SET 1, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.setBit(1);
          }
        });
      case 0xD0:
        return new Instruction(opcode, "SET 2, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.setBit(2);
          }
        });
      case 0xD1:
        return new Instruction(opcode, "SET 2, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.setBit(2);
          }
        });
      case 0xD2:
        return new Instruction(opcode, "SET 2, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.setBit(2);
          }
        });
      case 0xD3:
        return new Instruction(opcode, "SET 2, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.setBit(2);
          }
        });
      case 0xD4:
        return new Instruction(opcode, "SET 2, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.setBit(2);
          }
        });
      case 0xD5:
        return new Instruction(opcode, "SET 2, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.setBit(2);
          }
        });
      case 0xD6:
        return new Instruction(opcode, "SET 2, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xD7:
        return new Instruction(opcode, "SET 2, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.setBit(2);
          }
        });
      case 0xD8:
        return new Instruction(opcode, "SET 3, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.setBit(3);
          }
        });
      case 0xD9:
        return new Instruction(opcode, "SET 3, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.setBit(3);
          }
        });
      case 0xDA:
        return new Instruction(opcode, "SET 3, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.setBit(3);
          }
        });
      case 0xDB:
        return new Instruction(opcode, "SET 3, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.setBit(3);
          }
        });
      case 0xDC:
        return new Instruction(opcode, "SET 3, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.setBit(3);
          }
        });
      case 0xDD:
        return new Instruction(opcode, "SET 3, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.setBit(3);
          }
        });
      case 0xDE:
        return new Instruction(opcode, "SET 3, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xDF:
        return new Instruction(opcode, "SET 3, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.setBit(3);
          }
        });
      case 0xE0:
        return new Instruction(opcode, "SET 4, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.setBit(4);
          }
        });
      case 0xE1:
        return new Instruction(opcode, "SET 4, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.setBit(4);
          }
        });
      case 0xE2:
        return new Instruction(opcode, "SET 4, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.setBit(4);
          }
        });
      case 0xE3:
        return new Instruction(opcode, "SET 4, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.setBit(4);
          }
        });
      case 0xE4:
        return new Instruction(opcode, "SET 4, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.setBit(4);
          }
        });
      case 0xE5:
        return new Instruction(opcode, "SET 4, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.setBit(4);
          }
        });
      case 0xE6:
        return new Instruction(opcode, "SET 4, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xE7:
        return new Instruction(opcode, "SET 4, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.setBit(4);
          }
        });
      case 0xE8:
        return new Instruction(opcode, "SET 5, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.setBit(5);
          }
        });
      case 0xE9:
        return new Instruction(opcode, "SET 5, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.setBit(5);
          }
        });
      case 0xEA:
        return new Instruction(opcode, "SET 5, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.setBit(5);
          }
        });
      case 0xEB:
        return new Instruction(opcode, "SET 5, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.setBit(5);
          }
        });
      case 0xEC:
        return new Instruction(opcode, "SET 5, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.setBit(5);
          }
        });
      case 0xED:
        return new Instruction(opcode, "SET 5, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.setBit(5);
          }
        });
      case 0xEE:
        return new Instruction(opcode, "SET 5, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xEF:
        return new Instruction(opcode, "SET 5, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.setBit(5);
          }
        });
      case 0xF0:
        return new Instruction(opcode, "SET 6, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.setBit(6);
          }
        });
      case 0xF1:
        return new Instruction(opcode, "SET 6, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.setBit(6);
          }
        });
      case 0xF2:
        return new Instruction(opcode, "SET 6, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.setBit(6);
          }
        });
      case 0xF3:
        return new Instruction(opcode, "SET 6, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.setBit(6);
          }
        });
      case 0xF4:
        return new Instruction(opcode, "SET 6, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.setBit(6);
          }
        });
      case 0xF5:
        return new Instruction(opcode, "SET 6, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.setBit(6);
          }
        });
      case 0xF6:
        return new Instruction(opcode, "SET 6, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xF7:
        return new Instruction(opcode, "SET 6, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.setBit(6);
          }
        });
      case 0xF8:
        return new Instruction(opcode, "SET 7, B", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.b.setBit(7);
          }
        });
      case 0xF9:
        return new Instruction(opcode, "SET 7, C", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.c.setBit(7);
          }
        });
      case 0xFA:
        return new Instruction(opcode, "SET 7, D", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.d.setBit(7);
          }
        });
      case 0xFB:
        return new Instruction(opcode, "SET 7, E", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.e.setBit(7);
          }
        });
      case 0xFC:
        return new Instruction(opcode, "SET 7, H", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.h.setBit(7);
          }
        });
      case 0xFD:
        return new Instruction(opcode, "SET 7, L", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.l.setBit(7);
          }
        });
      case 0xFE:
        return new Instruction(opcode, "SET 7, (HL)", 1, 16, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            // Do work.
          }
        });
      case 0xFF:
        return new Instruction(opcode, "SET 7, A", 1, 8, new Executable() {
          public void execute(Instruction instruction, Processor processor, MemoryMap memoryMap) {
            processor.a.setBit(7);
          }
        });
    }
    return null;
  }
}
