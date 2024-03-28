# 0x400
.data
A: .word -1
B: .word 0xFFFFFFFF

.text
add gp, x0, x0
lw x1, A(gp)
lw x2, B(gp)
add x4, x1, x2