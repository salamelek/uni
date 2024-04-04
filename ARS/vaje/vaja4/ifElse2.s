    .data # 0x400
i:  .word 3

.text
    lw x1, i(x0)
    andi x2, x1, 0x1
    beq x2, x0, IF
    slli x1, x1, 3
    jal x0, KO
IF: addi x1, x1, 1
KO: sw x1, i(t0)