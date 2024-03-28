# 0x1000 0400
.data
A: .word 16

.text
# zgornji 20 bitov A damo v x1
lui x1, %hi(A)
# prištejemo 12 bitov
addi x1, x1, %lo(A)

# load word v x2 vrednost A-ja
lw x2, 0(x1)