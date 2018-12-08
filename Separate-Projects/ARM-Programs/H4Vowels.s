	; **********************************
	;  File: HW4Vowels.s
	;  Programmer: Austin Gailey
	;  Description: A Program to find vowels count them
	;               and change them to uppercase              
	;  Date: 5 March 2018
	;************************************
	
	AREA H4Vowels, CODE, READONLY
	EXPORT main
string		DCB		"The grey fox jumped over the wall",&0a,&0d,0
		ENTRY
main
		MOV r2,#0		;initializing vowel count to 0
		ADR r7,string	;initializing string register
		BL	Vowel
countVowel	
		ADD r2,#1
Vowel
		LDRB	r0,[r7],#1	;get a character
		CMP 	r0, #0		;end mark NULL?
		BLEQ	exit
		CMP		r0,#'A'
		CMPNE	r0,#'E' 
		CMPNE	r0,#'I' 
		CMPNE	r0,#'O' 
		CMPNE	r0,#'U' 
		BEQ		countVowel
		CMP		r0,#'a'
		CMPNE	r0,#'e' 
		CMPNE	r0,#'i' 
		CMPNE	r0,#'o' 
		CMPNE	r0,#'u' 
		SUBEQ	r0,#32
		STRB	r0,[r7]
		BEQ		countVowel
		BL		Vowel
exit
		B		.
 		END	
			

;*****************************
;The program works and counts vowels!
;***************************** 