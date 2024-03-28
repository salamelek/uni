# 0x400
.data
A: .word 4

.text
# dodelimo vrednosti registrom
addi x1, x0, -1
addi x2, x0, 0x2

# naredimo nekaj operacij
and x4, x1, x2
xor x5, x1, x2
ori x6, x1, 0x2bb