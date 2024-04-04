    .data
i:  .word 3    # 0x400

.text
    lw x1, i(x0)
    
    # pogledamo sodost z and 0x1
    andi x2, x1, 0x1
    
    # če je x2 == 0 (če je št. sodo), pojdi na IF
    beq x2, x0, IF
    
    # če pa x2 != 0 pa pojdi na EL
    jal x0, EL
    
IF: addi x1, x1, 1
    # če smo v if, nočemo it na else
    jal x0, KO
    
EL: slli x1, x1, 3

KO: sw x1, i(t0)