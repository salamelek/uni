    .data
A:  .word 3    # 0x400
B:  .word 4    # 0x404
C:  .word 0    # 0x408

    .text
    # prenesemo operande v registre
    lw x1, A(x0)
    lw x2, B(x0)

    # seštejemo
    add x4, x1, x2

    # store word
    sw x4, 0x408(x0)

    # jump and link (brezpogojni skok)
    jal x1, KO
    
    add x4,x4,x4
    sw x4, C(t0)

# to je neka funkcija, katere output bo zapisan v ?
KO: add x0, x0, x0