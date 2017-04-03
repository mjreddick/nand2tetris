// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.


(MainLoop)
// Reset i to the index of the last register in the screen map (8191)
	@8191
	D=A
	@i
	M=D

// Default screenVal to all black
	@screenVal
	M=-1

// Check if a key has been pressed, if not then set screenVal to all white (0)
	@KBD
	D=M
	@ScreenLoop
	D;JNE
	@screenVal
	M=0

(ScreenLoop)
// Break out back to the main loop if i < 0
	@i
	D=M
	@MainLoop
	D;JLT

// Calculate the address for the current part of the screenmap and store in address
	@i
	D=M
	@SCREEN
	D=D+A
	@address
	M=D

// RAM[address] = screenVal
	@screenVal
	D=M
	@address
	A=M
	M=D

// i = i - 1
	@i
	M=M-1

	@ScreenLoop
	0;JMP
