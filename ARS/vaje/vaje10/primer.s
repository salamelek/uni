.data # 0x400
TAB: .word 1, 2, 3, 4

.text # 0x0
addi x3, x0, TAB    # 0x0
addi x4, x0, 2      # 0x4
addi x17, x0, 10    # 0x8
jal x1, FUN         # 0xc
lw x2, 0x404(x0)    # 0x10
ecall

FUN: addi x4, x4, -1    # se bo 2x izvedla ta zanka
lw x2, 0(x3)
addi x2, x2, 0x123
sw x2, 36(x3)
addi x3, x3, 8
bne x4, x0, FUN    # 0x2c
jr x1              # 0x30

# skupaj je 7 + 6*2 ukazov --> 19 ukazov
# za vsak ukaz mora procesor dostopati do pomnilnika
# torej 19 dostopov do pomnilnika