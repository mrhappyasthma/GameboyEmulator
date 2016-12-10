# GameboyEmulator

This is a simple project in Java to emulate a Gameboy Color. Plenty of emulators already exist for
actual use, and this is not really intended one of them. The main goal here is building an
end-to-end emulator for learning purposes.

Once complete, I plan to document this pretty well for others to try to replicate this in "tutorial"
style fashion.

**NOTE: As of the the last update to this README, the emulator is INCOMPLETE! It does not yet work.**

## How to use the Gameboy Emulator

### Add BIOS

The emulator does not include any BIOS because it includes a copywritten boot-ROM by Nintendo. As
such, you will need to provide your own. I will **NOT** provide the BIOS or any explanation on how
to obtain it.

The BIOS should be named `gbc_bios.bin` and should be placed in the `/bios` subfolder.

### Compile/Run

To ease the process of building on Windows, I created some batch scripts to behave
similarly to a `Makefile`. For all other unix-based OSes, you can just use the `Makefile`.

- To build, simply execute `make` from the project directory. (You can also clean up the compiled
  files by running `make clean` on unix-based systems and `clean` on Windows.)

- To run the demo `/roms/hello_world.gb` game, execute `make sample` from the project directory on
  unix-based systems. (Execute `sample` on Windows.)

This will execute the hello_world ROM. It will prompt you with this screen:

![The landing screen for the hello_world ROM.](https://i.imgur.com/FCQaF15.png)

You can then press the controller key for your emulator that maps to the `start` button. This will
prompt a `you win!` text:

![Example text after pressing the `start` button on the hello_world ROM.](https://i.imgur.com/wmDLnYI.png)


- To run an arbitrary ROM from the `/roms/` subdirectory, use `make run file=<rom_name>`. (Execute
  `run <rom_name>` on Windows.)

That's it! :D

## How to add ROMs

To add ROMs, simply place the `.gb` binary files into the `roms/` subdirectory. You will already see a `hello_world.gb`
file there.

Note: Any commercial ROMs are intellectual property of their respective owners. They will **NOT**, under any circumstances,
be included in the project! I will not provide any commercial ROMs nor instructions on how to get them.

If you wish to create your own ROMs (similarly to my `hello_world.gb`), you can use the `GBDK` (GameBoy Development kit).
See here for more info: http://www.loirak.com/gameboy/gbprog.php.
