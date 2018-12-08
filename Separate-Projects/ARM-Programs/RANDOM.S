; Random number generator
;
; This uses a 33-bit feedback shift register to generate a pseudo-randomly
; ordered sequence of numbers which repeats in a cycle of length 2^33 - 1
; NOTE: randomseed should not be set to 0, otherwise a zero will be generated
; continuously (not particularly random!).
;
; This is a good application of direct ARM assembler, because the 33-bit
; shift register can be implemented using RRX (which uses reg + carry).
; An ANSI C version would be less efficient as the compiler would not use RRX.

	AREA 	|Random$$code|, CODE, READONLY

	EXPORT	randomnumber

randomnumber
; on exit:
;	r0 = low 32-bits of pseudo-random number
;	r1 = high bit (if you want to know it)

	LDR	r10, |seedpointer|
	LDMIA	r10, {r0, r1}
	TST	r1, r1, LSR#1		; to bit into carry
	MOVS	r2, r0, RRX		; 33-bit rotate right
	ADC	r1, r1, r1		; carry into LSB of a2
	EOR	r2, r2, r2, LSL#12	; (involved!)
	EOR	r0, r2, r2, LSR#20	; (similarly involved!)
	STMIA	r10, {r0, r1}
	
	BX	lr

|seedpointer|
	ALIGN
	DCD	seed
	
	AREA	|Random$$data|, DATA

	EXPORT	seed
seed
	DCD	&77777777
	DCD	&ffffffff
	END
