;************************************************;
;Program: HW6									 ;
;		This program will generate a series of	 ;
;		randon words, sort them with bubble sort ;
;		then count the 1's and 0's				 ;
;												 ;
;Written By: Austin Gailey						 ;
;												 ;
;Date:  3/28/2018								 ;
;												 ;
;************************************************;
	AREA	HW6, CODE, READONLY	; name this block of code
	EXPORT	main
	IMPORT	randomnumber
	ENTRY							; mark first instruction
									; to execute
main
	MOV		r3,#0					;index counter
	MOV		r4,#0					;counter
	BL		fillArray				;Branch to fillArray subroutine
;	BL		hardcodeArray
	BL		viewArray				;Branch to viewArray subroutine
	BL		sortArray				;Branch to sortArray subroutine
	BL		viewArray				;Branch to viewArray subroutine
	BL		countOnes				;Branch to oneCount subroutine
	B		exit					;terminate program
	
fillArray
	ADR		r8,randoms				;Store randoms address in r5
	MOV		r11,lr					;Store link register in r11
	LDR		r10,mask				;Mask for easy reading
fillLoop
	BL		randomnumber			;Branch with Link to randomnumber file
	AND		r0,r0,r10				;Mask value for easy reading
	STR		r0,[r8],#4				;Store random number
	ADD		r3,r3,#1				;Increment index counter
	CMP		r3,#10					;Compare if end of array
	BLT		fillLoop				;Loop back to fillLoop subroutine
	MOV		r3,#0					;Clear index counter
	BX		r11						;Branch to r11
;	
;hardcodeArray
;	ADR		r8,randoms
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	MOV		r0, #0x1F
;	STR		r0,[r8],#4
;	BX		lr
;	
	
viewArray
	ADR		r8,randoms				;Store randoms address in r5
viewLoop
	LDR		r6,[r8],#4				;Show randoms value in r6
	MOV		r5,r6
	ADD		r3,r3,#1				;Increment index counter
	CMP		r3,#10					;Compare if end of array
	BNE		viewLoop				;Loop back to viewLoop subroutine
	MOV		r3,#0					;Clear index counter
	BX		lr
	
sortArray							;Begin BubbleSort of Array
	ADR		r8,randoms				;Store randoms address in r5
	MOV		r3,#0					;Clear index counter
	MOV		r4,#0					;Clear counter
sortLoop
	LDR		r6,[r8]					;value
	LDR		r7,[r8,#4]				;value + 1
	CMP		r6,r7					;Compare if value is greater than value + 1
	
	STRHI	r6,[r8,#4]				;swap
	STRHI	r7,[r8]					;swap
	ADDHI	r4,r4,#1				;increment swap counter
	
	ADD		r3,r3,#1				;Add 1 to index counter
	ADD		r5,r8,#4				;Get next value in r5
	CMP		r3,#9					;Compare if end of array
	BNE		sortLoop				;Branch to sortLoop subroutine
	TEQ		r4,#0					;Test if swap counter is zero
	BNE		sortArray				;Branch to sortArray subroutine
	MOV		r3,#0					;Clear index counter
	MOV		r4,#0					;Clear counter
	BX		lr						;Branch to link register
	
countOnes
	ADR		r8,randoms				;Stores first randoms value in r5
loopCount
	LDR		r6,[r8],#4				;Store value in r6
	ADD		r3,r3,#1				;Add 1 to index counter
	TEQ		r6,#0					;Test if word is all zeros
	ADDNE	r4,r4,#1				;increment 1's counter
count
	SUB		r7,r6,#1				;Subtract 1 from word
	AND		r6,r7,r6				;And the two words
	TEQ		r6,#0					;Test if word is all zeros
	ADDNE	r4,r4,#1				;Increment 1's counter
	BNE		count					;Branch if not equal to count subroutine
	CMP		r3,#10					;Compare if end of array
	BNE		loopCount				;Branch if not equal to loopCount subroutine
	MOV		r3,#0					;clear index counter
	BX		lr						;Branch to link register
	
	ALIGN
randoms %	10*4
mask	DCD	0x0000FFFF
	
exit
	B		.						;Branch to self to terminate program
	
	
	
	END				