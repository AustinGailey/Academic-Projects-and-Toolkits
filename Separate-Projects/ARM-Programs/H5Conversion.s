	; ***********************************************;
	;  File: HW5Conversion.s						 ;
	;  Programmer: 	Austin Gailey					 ;
	;  Description: A program to convert IEEE to TNS ;
	;				and vis versa              		 ;
	;  Date: 16 March 2018							 ;
	;************************************************;
	
	AREA H5Conversion, CODE, READONLY
	EXPORT main
TNSNumber		DCD		0x7F200107	;Enter TNS  # here
IEEENumber		DCD		0x433F9000	;Enter IEEE # here
	
		ENTRY
main
		LDR		r3,TNSNumber				;Load TNSNumber into r3
		LDR		r4,IEEENumber				;Load IEEENumber into r4
		BL		TNSToIEEE					;Branch with link to TNSToIEEE
		CMP		r4,r8						;Compares source IEEE with converted TNS
		MOVEQ	r10,#&FFFFFFFF				;Test equivalence for debugging
		BL		IEEEToTNS					;Branch with link to IEEEToTNS
		CMP		r3,r9						;Compares source TNS with converted IEEE
		MOVEQ	r11,#&FFFFFFFF				;Test equivalence for debugging
		B		exit						;Branch to exit
TNSToIEEE
		LDR		r10,maskTNSsign				;Load mask
		AND		r5,r3,r10					;Mask sign digit
		LDR		r10,maskTNSexponent			;Load mask
		AND		r6,r3,r10					;Mask Exponent
		SUB		r6,#&100					;Subtract 256
		ADD		r6,#&7F						;Add 127
		CMP		r6,#&FE						;Compare if Exponent is greater than 254
		MOVGT	r8,#&11111111				;For debugging
		BGT		exit						;Terminate if greater - cannot convert
		LSL		r6,r6,#23					;Logical shift left 23 bits
		LDR		r10,maskTNSsignificant		;Load mask
		AND		r7,r3,r10					;Mask Significant
		LSR		r7,r7,#9					;Logical shift right 9 bits
		ORR		r8,r5,r6					;Combine bits to create IEEE format
		ORR		r8,r7						;Combine bits to create IEEE format
		BX		lr							;Branch to program counter
IEEEToTNS
		LDR		r11,maskIEEEsign			;Load mask
		AND		r5,r4,r11					;Mask sign digit
		LDR		r11,maskIEEEexponent		;Load mask
		AND		r6,r4,r11					;Mask Exponent
		LSR		r6,r6,#23					;Logical shift right 23 bits
		ADD		r6,#&100					;Add 256
		SUB		r6,#&7F						;Subtract 127
		LDR		r11,maskIEEEsignificant		;Load mask
		AND		r7,r4,r11					;Mask Significant
		LSL		r7,r7,#9					;Logical shift left 9 bits
		ORR		r9,r5,r6					;Combine bits to create TNS format
		ORR		r9,r7						;Combine bits to create TNS format
		BX		lr							;Branch to program counter
exit
		B		.							;Branch to self
		ALIGN
maskTNSsign 		DCD	0x80000000
maskTNSexponent		DCD	0x000001FF
maskTNSsignificant  DCD 0x7FFFFE00
	
maskIEEEsign		DCD	0x80000000
maskIEEEexponent	DCD	0x7F800000
maskIEEEsignificant	DCD	0x007FFFFF
 		END	
			

;*********************************************;
;The program works and converts IEEE and TNS! ;
;*********************************************;