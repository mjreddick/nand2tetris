// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// x * y = result

// x = RAM[0]
	@R0
	D=M
	@x
	M=D

// y = RAM[1]
	@R1
	D=M
	@y
	M=D

// absY = y
	@absY
	M=D

// i = 0
	@i
	M=0

// result = 0
	@result
	M=0

// if y >= 0 GOTO Loop, otherwise set absY = -y
	@y
	D=M
	@Loop
	D;JGE 
	@absY
	M=-M

(Loop)

// break out of loop if i >=  absY
	@i
	D=M
	@absY
	D=D-M
	@CalcSign
	D;JGE

// result = result + x
	@x
	D=M
	@result
	M=M+D

// i = i + 1
	@i
	M=M+1

	@Loop
	0;JMP

// if y >= 0 GOTO Return, otherwise result = -result
(CalcSign)
	@y
	D=M
	@Return
	D;JGE 
	@result
	M=-M

(Return)
	@result
	D=M
	@R2
	M=D

(End)
	@End
	0;JMP





