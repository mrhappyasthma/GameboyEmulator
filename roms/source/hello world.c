// This file can generate a .gb game when compiled with the
// GBDK (GameBoy Development Kit). See roms/hello_world.gb for a
// precompiled version.
//
// For more details on GameBoy ROM programming, see:
//   http://www.loirak.com/gameboy/gbprog.php

#include <gb/gb.h>
#include <stdio.h>

void main() {
  printf("Press 'start'...\n\n");
  waitpad(J_START);
  printf("You win!\n");
}
